package OOAD_Lab03_gizmo.controller.buildhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import OOAD_Lab03_gizmo.controller.BoardController;
import OOAD_Lab03_gizmo.controller.BuildViewController;
import OOAD_Lab03_gizmo.view.visual.*;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class AddHandler implements BoardHandler {
	final GizmoModel model;
	final BoardController boardController;
	final BuildViewController buildViewController;
	private final String type;
	private final Label log;

	public AddHandler(GizmoModel model, BoardController boardController, BuildViewController buildViewController,
	                  String type, Label log) {
		this.model = model;
		this.boardController = boardController;
		this.buildViewController = buildViewController;
		this.type = type;
		this.log = log;
	}

	@Override
	public void handle(Event event) {

		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			MouseEvent mouseEvent = (MouseEvent) event;

			double x = (int) (mouseEvent.getX() / ONE_L_IN_PIXELS);
			double y = (int) (mouseEvent.getY() / ONE_L_IN_PIXELS);

			if (model.addWidget(x, y, type)) {
				GizmoWidget gizmo = model.getWidgetByPosition(x, y);
				GizmoVisual gizmoView;
				switch (type) {
					case "Circle":
						gizmoView = new CircleWidgetVisual(gizmo);
						break;
					case "Triangle":
						gizmoView = new TriangleWidgetVisual(gizmo);
						break;
					case "Square":
						gizmoView = new SquareWidgetVisual(gizmo);
						break;
					case "LEFT_FLIPPER":
						gizmoView = new FlipperWidgetVisual(gizmo);
						break;
					case "RIGHT_FLIPPER":
						gizmoView = new FlipperWidgetVisual(gizmo);
						break;
					case "Absorber":
						gizmoView = new AbsorberWidgetVisual(gizmo);
						break;
					default:
						gizmoView = null;
						break;
				}


				boardController.addToBoardView(gizmoView);
			}
			if (!model.getMessage().equals("")) {
				log.setText(model.getMessage());
				model.setMessage("");
			} else {
				log.setText(type + " added at position" + "(" + x + ", " + y + ").");
			}
		}
	}

	public void handle(String name) {
		GizmoWidget gizmo = model.getWidgetByName(name);
		GizmoVisual gizmoView;

//		a mock of constructor
		switch (type) {
			case "Circle":
				gizmoView = new CircleWidgetVisual(gizmo);
				break;
			case "Triangle":
				gizmoView = new TriangleWidgetVisual(gizmo);
				break;
			case "Square":
				gizmoView = new SquareWidgetVisual(gizmo);
				break;
			case "LEFT_FLIPPER":
				gizmoView = new FlipperWidgetVisual(gizmo);
				break;
			case "RIGHT_FLIPPER":
				gizmoView = new FlipperWidgetVisual(gizmo);
				break;
			case "Absorber":
				gizmoView = new AbsorberWidgetVisual(gizmo);
				break;
			default:
				gizmoView = null;
				break;
		}

		boardController.addToBoardView(gizmoView);
		if (!model.getMessage().equals("")) {
			log.setText(model.getMessage());
			model.setMessage("");
		} else {
			log.setText(" ");
		}

	}
}
