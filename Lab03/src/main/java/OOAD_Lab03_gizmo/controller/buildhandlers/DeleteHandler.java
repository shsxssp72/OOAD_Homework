package OOAD_Lab03_gizmo.controller.buildhandlers;


import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.BoardController;
import OOAD_Lab03_gizmo.view.visual.BallVisual;
import OOAD_Lab03_gizmo.view.visual.GizmoVisual;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class DeleteHandler implements BoardHandler {
	private final BoardController boardController;
	private final GizmoModel model;

	public DeleteHandler(BoardController boardController, GizmoModel model) {
		this.boardController = boardController;
		this.model = model;
	}

	@Override
	public void handle(Event event) {
		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {

			Node clicked = ((Node) event.getTarget());
			double x = (int) (clicked.getTranslateX() / ONE_L_IN_PIXELS);
			double y = (int) (clicked.getTranslateY() / ONE_L_IN_PIXELS);

			if (model.removeBall(clicked.getParent().getTranslateX() / ONE_L_IN_PIXELS, clicked.getParent()
					.getTranslateY() / ONE_L_IN_PIXELS)) {
				boardController.removeFromBoardView((BallVisual) clicked.getParent());
			} else if (model.removeWidget(x, y)) {
				boardController.removeFromBoardView((GizmoVisual) clicked);
			}
		}
	}
}
