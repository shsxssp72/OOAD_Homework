package OOAD_Lab03_gizmo.controller;

import OOAD_Lab03_gizmo.controller.buildhandlers.BoardHandler;
import OOAD_Lab03_gizmo.view.visual.BallVisual;
import OOAD_Lab03_gizmo.view.visual.BoardVisual;
import OOAD_Lab03_gizmo.view.visual.GizmoVisual;
import javafx.event.Event;
import javafx.event.EventHandler;

public class BoardController implements EventHandler<Event> {

	private final BoardVisual boardVisual;
	private BoardHandler boardHandler;
	private boolean doNothing;

	public BoardController() {
		this.boardVisual = new BoardVisual();
		boardVisual.addEventHandler(Event.ANY, this);
		doNothing = true;
	}

	public void setBoardHandler(BoardHandler boardHandler) {
		this.boardHandler = boardHandler;
		doNothing = false;
	}

	public void setDoNothing() {
		this.doNothing = true;
	}

	@Override
	public void handle(Event event) {
		if (doNothing) {
			event.consume();
		} else {
			boardHandler.handle(event);
		}
	}

	public void addToBoardView(GizmoVisual gizmoView) {
		boardVisual.add(gizmoView);
	}

	public void addToBoardView(BallVisual ballView) {
		boardVisual.add(ballView);
	}

	public void removeFromBoardView(GizmoVisual gizmoView) {
		boardVisual.remove(gizmoView);
	}

	public void removeFromBoardView(BallVisual ballView) {
		boardVisual.remove(ballView);
	}

	public void clearBoardView() {
		boardVisual.clearBoard();
	}

	public BoardVisual getBoardVisual() {
		return boardVisual;
	}
}
