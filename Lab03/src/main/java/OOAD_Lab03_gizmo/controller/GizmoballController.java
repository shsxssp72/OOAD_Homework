package OOAD_Lab03_gizmo.controller;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.mischandlers.*;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static javafx.geometry.HorizontalDirection.LEFT;
import static javafx.geometry.HorizontalDirection.RIGHT;

public class GizmoballController {
	public StackPane centerPane;
	public BorderPane rootLayout;
	@FXML
	private MenuItem saveButton;
	@FXML
	private MenuItem loadButton;
	@FXML
	private MenuItem exitButton;
	@FXML
	private MenuItem helpButton;

	@FXML
	private BorderPane playground;
	@FXML
	private BorderPane buildground;

	@FXML
	private PlayViewController playgroundController;
	@FXML
	private BuildViewController buildgroundController;


	public static final SimpleBooleanProperty DISABLE = new SimpleBooleanProperty(false);

	private SaveHandler saveHandler;
	private LoadHandler loadHandler;
	private ExitHandler exitHandler;
	private HelpHandler helpHandler;

	public void setup(Stage stage, GizmoModel model) {

		BoardController boardController = new BoardController();

		saveHandler = new SaveHandler(stage, buildgroundController,model);
		loadHandler = new LoadHandler(stage, boardController, buildgroundController, model, buildgroundController.getLog());
		exitHandler = new ExitHandler(buildgroundController, stage,model);
		helpHandler = new HelpHandler();

		SwitchHandler switchToRun = new SwitchHandler(buildgroundController, playgroundController, RIGHT, loadHandler, saveHandler);
		SwitchHandler switchToBuild = new SwitchHandler(buildgroundController, playgroundController, LEFT, loadHandler, saveHandler);

		buildgroundController.setup(model, boardController, switchToRun, stage);
		playgroundController.setup(model, boardController, switchToBuild, stage, loadHandler);

		saveButton.disableProperty().bind(DISABLE);
		loadButton.disableProperty().bind(DISABLE);

		setupHandlers();
	}

	private void setupHandlers() {
		saveButton.setOnAction(saveHandler);
		loadButton.setOnAction(loadHandler);
		exitButton.setOnAction(exitHandler);
		helpButton.setOnAction(helpHandler);
	}
}
