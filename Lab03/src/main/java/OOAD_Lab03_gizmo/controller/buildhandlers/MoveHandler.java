package OOAD_Lab03_gizmo.controller.buildhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.view.visual.BallVisual;
import OOAD_Lab03_gizmo.view.visual.GizmoVisual;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class MoveHandler implements BoardHandler {

	private final GizmoModel model;
	private boolean firstClick;
	private double firstx;
	private double firsty;
	private boolean gizmo;
	private GizmoVisual gizmoView;
	private BallVisual ballView;
	private final Label log;

	public MoveHandler(GizmoModel model, ToggleButton moveButton) {
		this.model = model;
		this.log = new Label();
		firstClick = false;

		moveButton.selectedProperty().addListener((observable, oldValue, newValue) ->
		{
			if (!newValue) {
				if (gizmoView != null) {
					gizmoView.setSelected(false);
				}
				if (ballView != null) {
					ballView.setSelected(false);
				}
			}
		});
	}

	@Override
	public void handle(Event event) {

		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			MouseEvent mouseEvent = (MouseEvent) event;
			System.out.println(mouseEvent);
			double mouseXPos = mouseEvent.getX() / ONE_L_IN_PIXELS;
			double mouseYPos = mouseEvent.getY() / ONE_L_IN_PIXELS;

			Node clicked = ((Node) event.getTarget());
			double nodeXPos = clicked.getTranslateX() / ONE_L_IN_PIXELS;
			double nodeYPos = clicked.getTranslateY() / ONE_L_IN_PIXELS;

			if (!firstClick) {

				Ball ball = model.getBallByPosition(mouseXPos, mouseYPos);

				if (ball != null) {
					firstClick = true;
					gizmo = false;
					firstx = mouseXPos;
					firsty = mouseYPos;
					ballView = (BallVisual) clicked.getParent();
					ballView.setSelected(true);

				} else {
					gizmo = true;
					firstClick = model.getWidgetByPosition(Math.floor(nodeXPos), Math.floor(nodeYPos)) != null;
					firstx = Math.floor(nodeXPos);
					firsty = Math.floor(nodeYPos);

					if (firstClick) {
						gizmoView = (GizmoVisual) clicked;
						gizmoView.setSelected(true);
					}
				}

			} else {
				firstClick = false;
				if (gizmo) {
					model.moveWidget(firstx, firsty, Math.floor(mouseXPos), Math.floor(mouseYPos));
					gizmoView.setSelected(false);
					gizmoView = null;
				} else {
					model.moveBall(firstx, firsty, mouseXPos, mouseYPos);
					ballView.setSelected(false);
					ballView = null;
				}
			}
			if (!model.getMessage().equals("")) {
				log.setText(model.getMessage());
				model.setMessage("");
			} else {
				log.setText("Object Moved.");
			}
		}
	}
}
