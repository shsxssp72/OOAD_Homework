package OOAD_Lab03_gizmo.controller.buildhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.BoardController;
import javafx.event.Event;
import javafx.scene.control.Label;

public class ClearBoardHandler implements BoardHandler
{
	private final BoardController boardController;
	private final GizmoModel model;
	private final Label log;

	public ClearBoardHandler(BoardController boardController, GizmoModel model, Label log)
	{
		this.log = log;
		this.boardController=boardController;
		this.model=model;
	}

	@Override
	public void handle(Event event)
	{
		this.boardController.clearBoardView();
		this.model.clearBoard();
		log.setText("Board Cleared! Feel free to edit this board!");
	}
}
