package OOAD_Lab03_gizmo.controller.playhandlers;

import OOAD_Lab03_gizmo.view.visual.BoardVisual;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StartHandler implements EventHandler<ActionEvent> {
	private final Timeline timeline;
	private final BoardVisual boardVisual;

	public StartHandler(Timeline timeline, BoardVisual boardVisual) {
		this.timeline = timeline;
		this.boardVisual = boardVisual;
	}

	@Override
	public void handle(ActionEvent event) {
		timeline.play();
		boardVisual.requestFocus();
	}
}
