package OOAD_Lab03_gizmo.controller.playhandlers;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class StopHandler implements EventHandler<ActionEvent> {
	private final Timeline timeline;

	public StopHandler(Timeline timeline) {
		this.timeline = timeline;
	}

	@Override
	public void handle(ActionEvent event) {
		timeline.stop();
	}
}
