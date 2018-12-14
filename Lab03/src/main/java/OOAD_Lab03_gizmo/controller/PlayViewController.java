package OOAD_Lab03_gizmo.controller;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.mischandlers.KeepSafeHandler;
import OOAD_Lab03_gizmo.controller.mischandlers.LoadHandler;
import OOAD_Lab03_gizmo.controller.mischandlers.SwitchHandler;
import OOAD_Lab03_gizmo.controller.playhandlers.ResetHandler;
import OOAD_Lab03_gizmo.controller.playhandlers.StartHandler;
import OOAD_Lab03_gizmo.controller.playhandlers.StopHandler;
import OOAD_Lab03_gizmo.controller.playhandlers.TriggerHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import static OOAD_Lab03_gizmo.config.Constants.MILLIS_PER_FRAME;

public class PlayViewController {
	@FXML
	private BorderPane playgroundRoot;
	@FXML
	private Button resetButton;
	@FXML
	private Button startButton;
	@FXML
	private Button stopButton;
	@FXML
	private Button switchButton;

	private TriggerHandler triggerHandler;
	private BoardController boardController;
	private Timeline timeline;

	private KeepSafeHandler keepSafeHandler;

	public void setup(GizmoModel model, BoardController boardController, SwitchHandler switchToBuild, Stage stage, LoadHandler loadHandler) {
		this.boardController = boardController;
		this.keepSafeHandler = new KeepSafeHandler();

		triggerHandler = new TriggerHandler();

		this.timeline = new Timeline(
				new KeyFrame(
						//keyframes allow for something to happen at a given time
						Duration.millis(MILLIS_PER_FRAME),
						//keyframe that has duration depending on framerate, or it happens immediately
						actionEvent -> model.runBalls()
				)
		);
		//keeps running until stop is called
		this.timeline.setCycleCount(Timeline.INDEFINITE);

		stopButton.setDisable(true);


		startButton.setOnAction(event ->
		{
			GizmoballController.DISABLE.set(true);
			stopButton.requestFocus();
			new StartHandler(timeline, boardController.getBoardVisual()).handle(event);
		});
		stopButton.setOnAction(event ->
		{
			GizmoballController.DISABLE.set(false);
			startButton.requestFocus();
			new StopHandler(timeline).handle(event);
		});
		resetButton.setOnAction(new ResetHandler(model, loadHandler));
		switchButton.setOnAction(switchToBuild);

		startButton.disableProperty().bind(GizmoballController.DISABLE);
		stopButton.disableProperty().bind(GizmoballController.DISABLE.not());
		resetButton.disableProperty().bind(GizmoballController.DISABLE);
		switchButton.disableProperty().bind(GizmoballController.DISABLE);

	}


	public Pane getRoot() {
		return playgroundRoot;
	}


	public void toggleBoard() {
		playgroundRoot.setCenter(boardController.getBoardVisual());
	}

	public void setDoNothing(boolean doNothing) {
		if (doNothing) {
			playgroundRoot.addEventFilter(Event.ANY, keepSafeHandler);
			boardController.getBoardVisual().removeEventHandler(KeyEvent.ANY, triggerHandler);
		} else {
			playgroundRoot.removeEventFilter(Event.ANY, keepSafeHandler);
			boardController.getBoardVisual().addEventHandler(KeyEvent.ANY, triggerHandler);
		}
	}
}
