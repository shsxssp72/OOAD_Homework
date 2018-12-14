package OOAD_Lab03_gizmo.controller.buildhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import OOAD_Lab03_gizmo.Utilities.KeyboardEvent;
import OOAD_Lab03_gizmo.Utilities.TriggerConnector;
import OOAD_Lab03_gizmo.controller.BoardController;
import OOAD_Lab03_gizmo.view.visual.GizmoVisual;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class AssignKeyHandler implements BoardHandler {
	private final GizmoModel model;
	private final ToggleButton assignButton;
	private final BoardController boardController;
	private final Stage stage;
	private boolean triggeredSelected;
	private GizmoWidget triggeredWidget;
	private final Label infoLabel;
	private GizmoVisual gizmoVisual;

	public AssignKeyHandler(GizmoModel model, BoardController boardController, Stage stage, Label log,
	                        ToggleButton assignButton) {
		this.model = model;
		this.assignButton = assignButton;
		triggeredSelected = false;
		this.boardController = boardController;
		this.stage = stage;
		this.infoLabel = log;
		assignButton.selectedProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue) {
				if (gizmoVisual != null) {
					gizmoVisual.setSelected(false);
				}
			}
		});
	}

	@Override
	public void handle(Event event) {

		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			Node clicked = ((Node) event.getTarget());


			System.out.println("DEBUG" + clicked);
			double nodeX = Math.floor(clicked.getTranslateX() / ONE_L_IN_PIXELS);
			double nodeY = Math.floor(clicked.getTranslateY() / ONE_L_IN_PIXELS);

			GizmoWidget gizmo = model.getWidgetByPosition(nodeX, nodeY);

			if (!triggeredSelected) {
				if (gizmo != null) {
					triggeredSelected = true;
					triggeredWidget = gizmo;
					boardController.getBoardVisual().requestFocus();
					infoLabel.setText(triggeredWidget
							.getName() + " selected! Please press a key.");
					gizmoVisual = (GizmoVisual) clicked;
					gizmoVisual.setSelected(true);
				}
			}
		} else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			KeyEvent keyEvent = (KeyEvent) event;
			KeyCode keyCode = keyEvent.getCode();

			infoLabel.setText("");

			if (keyCode != KeyCode.ESCAPE) {

				TriggerConnector
						.addTrigger(new KeyboardEvent(keyCode, KeyEvent.KEY_PRESSED), triggeredWidget);


				infoLabel.setText(keyCode + " and " + triggeredWidget.getName() + " are connected!");
			}

			triggeredSelected = false;
			gizmoVisual.setSelected(false);
			gizmoVisual = null;
			assignButton.requestFocus();
		}
	}
}
