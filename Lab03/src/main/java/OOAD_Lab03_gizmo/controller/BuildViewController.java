package OOAD_Lab03_gizmo.controller;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.controller.buildhandlers.*;
import OOAD_Lab03_gizmo.controller.mischandlers.KeepSafeHandler;
import OOAD_Lab03_gizmo.controller.mischandlers.SwitchHandler;
import OOAD_Lab03_gizmo.view.visual.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class BuildViewController {

	@FXML
	private BorderPane buildgroundRoot;
	@FXML
	private Button switchButton;
	@FXML
	private SquareWidgetVisual squareButton;
	@FXML
	private TriangleWidgetVisual triangleButton;
	@FXML
	private CircleWidgetVisual circleButton;
	@FXML
	private FlipperWidgetVisual leftFlipperButton;
	@FXML
	private FlipperWidgetVisual rightFlipperButton;
	@FXML
	private AbsorberWidgetVisual absorberButton;
	@FXML
	private BallVisual ballButton;
	@FXML
	private ToggleButton assignButton;
	@FXML
	private Button clearBoardButton;
	@FXML
	private ToggleButton moveButton;
	@FXML
	private ToggleButton rotateButton;
	@FXML
	private ToggleButton deleteButton;
	@FXML
	private Label log;

	private GizmoModel model;
	private BoardController boardController;
	private KeepSafeHandler keepSafeHandler;
	private SwitchHandler switchToPlay;
	private Stage stage;
	private Ball selectedBall;
	private ToggleGroup toggleGroup;
	private AddBallHandler addBallHandler;
	private boolean addBallSelected;

	public void setup(GizmoModel model, BoardController boardController, SwitchHandler switchToPlay, Stage stage) {
		this.model = model;
		this.boardController = boardController;
		this.keepSafeHandler = new KeepSafeHandler();
		this.switchToPlay = switchToPlay;
		this.stage = stage;
		this.toggleGroup = new ToggleGroup();

		buildgroundRoot.setCenter(boardController.getBoardVisual());


		switchButton.setOnAction(event ->
		{
			toggleGroup.selectToggle(null);
			this.switchToPlay.handle(event);
		});

		buildgroundRoot.setFocusTraversable(false);

		setupHandlers();

	}


	private void toggleButton(Toggle toggle) {
		if (toggle.isSelected()) {
			toggleGroup.selectToggle(null);
		} else {
			toggleGroup.selectToggle(toggle);
		}
	}

	private void setupHandlers() {
		squareButton.setOnMouseClicked(event ->
		{
			toggleButton(squareButton);
			boardController.setBoardHandler(new AddHandler(model, boardController, this, "Square", log));
		});
		triangleButton.setOnMouseClicked(event ->
		{
			toggleButton(triangleButton);
			boardController.setBoardHandler(new AddHandler(model, boardController, this, "Triangle", log));

		});
		circleButton.setOnMouseClicked(event ->
		{
			toggleButton(circleButton);
			boardController.setBoardHandler(new AddHandler(model, boardController, this, "Circle", log));
		});
		leftFlipperButton.setOnMouseClicked(event ->
		{
			toggleButton(leftFlipperButton);
			boardController.setBoardHandler(new AddHandler(model, boardController, this, "LEFT_FLIPPER", log));
		});
		rightFlipperButton.setOnMouseClicked(event ->
		{
			toggleButton(rightFlipperButton);
			boardController.setBoardHandler(new AddHandler(model, boardController, this, "RIGHT_FLIPPER", log));
		});
		absorberButton.setOnMouseClicked(event ->
		{
			toggleButton(absorberButton);
			boardController.setBoardHandler(new AddHandler(model, boardController, this, "Absorber", log));
		});
		ballButton.setOnMouseClicked(event ->
		{
			toggleButton(ballButton);
			addBallHandler = new AddBallHandler(model, boardController, this, log);
			boardController.setBoardHandler(addBallHandler);
		});


		moveButton.setOnAction(event ->
		{
			if (!moveButton.isSelected()) {
				boardController.setDoNothing();
			} else {
				boardController.setBoardHandler(new MoveHandler(model, moveButton));
			}
		});
		rotateButton.setOnAction(event ->
		{
			if (!rotateButton.isSelected()) {
				boardController.setDoNothing();
			} else {
				boardController.setBoardHandler(new RotateHandler(model, this));
			}
		});
		deleteButton.setOnAction(event ->
		{
			if (!deleteButton.isSelected()) {
				boardController.setDoNothing();
			} else {
				boardController.setBoardHandler(new DeleteHandler(boardController, model));
			}
		});
		assignButton.setOnAction(event ->
		{
			if (!assignButton.isSelected()) {
				boardController.setDoNothing();
			} else {
				boardController
						.setBoardHandler(new AssignKeyHandler(model, boardController, stage, log, assignButton));
			}
		});


		clearBoardButton.setOnAction(event ->
		{
			boardController.setBoardHandler(new ClearBoardHandler(boardController, model, log));
			boardController.handle(event);
			boardController.setDoNothing();
			toggleGroup.selectToggle(null);
		});

		moveButton.setToggleGroup(toggleGroup);
		rotateButton.setToggleGroup(toggleGroup);
		deleteButton.setToggleGroup(toggleGroup);
		assignButton.setToggleGroup(toggleGroup);

		squareButton.setToggleGroup(toggleGroup);
		triangleButton.setToggleGroup(toggleGroup);
		circleButton.setToggleGroup(toggleGroup);
		leftFlipperButton.setToggleGroup(toggleGroup);
		rightFlipperButton.setToggleGroup(toggleGroup);
		absorberButton.setToggleGroup(toggleGroup);
		ballButton.setToggleGroup(toggleGroup);
	}

	public void setDoNothing(boolean doNothing) {
		if (doNothing) {
			buildgroundRoot.addEventFilter(Event.ANY, keepSafeHandler);

		} else {
			buildgroundRoot.removeEventFilter(Event.ANY, keepSafeHandler);
		}

		boardController.setDoNothing();
	}

	public void setLog(String text) {
		log.setText(text);

	}

	public Label getLog() {
		return log;
	}

	public Pane getRoot() {
		return buildgroundRoot;
	}

	public void toggleBoard() {
		buildgroundRoot.setCenter(boardController.getBoardVisual());
	}


	public Ball getSelecedBall() {
		return selectedBall;
	}

	public boolean isAddBallSelected() {
		return addBallSelected;
	}
}
