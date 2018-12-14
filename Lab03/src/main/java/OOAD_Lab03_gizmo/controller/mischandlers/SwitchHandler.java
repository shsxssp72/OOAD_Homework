package OOAD_Lab03_gizmo.controller.mischandlers;

import OOAD_Lab03_gizmo.controller.BuildViewController;
import OOAD_Lab03_gizmo.controller.PlayViewController;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HorizontalDirection;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static OOAD_Lab03_gizmo.controller.playhandlers.ResetHandler.SAVE_STATE;
import static javafx.geometry.HorizontalDirection.LEFT;
import static javafx.geometry.HorizontalDirection.RIGHT;

/**
 * ActionEvent handler for switching between two modes
 */
public class SwitchHandler implements EventHandler<ActionEvent> {

	private final Pane from;
	private final Pane to;
	private final BuildViewController buildViewController;
	private final PlayViewController playViewController;
	private final HorizontalDirection direction;
	private final TranslateTransition moveFrom;
	private final TranslateTransition moveTo;
	private final ParallelTransition switchTransition;
	private final SaveHandler saveHandler;
	private final LoadHandler loadHandler;


	public SwitchHandler(BuildViewController buildViewController, PlayViewController playViewController, HorizontalDirection direction,
	                     LoadHandler loadHandler, SaveHandler saveHandler) {
		this.buildViewController = buildViewController;
		this.playViewController = playViewController;
		this.direction = direction;
		this.saveHandler = saveHandler;
		this.loadHandler = loadHandler;

		if (direction == RIGHT) {
			from = buildViewController.getRoot();
			to = playViewController.getRoot();
		} else {
			from = playViewController.getRoot();
			to = buildViewController.getRoot();
		}

		moveFrom = new TranslateTransition(Duration.millis(5), from);
		moveFrom.setOnFinished(event -> from.toBack());
		moveTo = new TranslateTransition(Duration.millis(5), to);

		switchTransition = new ParallelTransition(moveFrom, moveTo);
	}


	@Override
	public void handle(ActionEvent event) {
		if (direction == RIGHT) {
//		switch to run
			buildViewController.setDoNothing(true);
			playViewController.setDoNothing(false);
			playViewController.toggleBoard();

			saveHandler.saveGame(SAVE_STATE);

		} else {
//			switch to build
			buildViewController.setDoNothing(false);
			playViewController.setDoNothing(true);
			buildViewController.toggleBoard();

			loadHandler.loadGame(SAVE_STATE);
		}


		int negate = direction == LEFT ? -1 : 1;
		moveFrom.setFromX(0);
		moveFrom.setToX(from.getWidth() * negate);
		moveTo.setFromX(-to.getWidth() * negate);
		moveTo.setToX(0);
		switchTransition.play();
	}
}
