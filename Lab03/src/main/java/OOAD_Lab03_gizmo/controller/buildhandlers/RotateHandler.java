package OOAD_Lab03_gizmo.controller.buildhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.BuildViewController;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class RotateHandler implements BoardHandler {
	private final GizmoModel model;
	private final BuildViewController buildViewController;

	public RotateHandler(GizmoModel model, BuildViewController buildViewController) {
		this.model = model;
		this.buildViewController = buildViewController;
	}

	@Override
	public void handle(Event event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {

			Node clicked = ((Node) event.getTarget());
			double nodeX = Math.floor(clicked.getTranslateX() / ONE_L_IN_PIXELS);
			double nodeY = Math.floor(clicked.getTranslateY() / ONE_L_IN_PIXELS);


			model.rotateWidget(nodeX, nodeY);
			if (!model.getMessage().equals("")) {
				buildViewController.setLog(model.getMessage());
				model.setMessage("");
			} else {
				buildViewController.setLog("Rotated.");
			}
		}
	}
}
