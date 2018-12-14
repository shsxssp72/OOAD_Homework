package OOAD_Lab03_gizmo.controller.mischandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import OOAD_Lab03_gizmo.Utilities.Logger;
import OOAD_Lab03_gizmo.Utilities.SL_Handler;
import OOAD_Lab03_gizmo.controller.BoardController;
import OOAD_Lab03_gizmo.controller.BuildViewController;
import OOAD_Lab03_gizmo.controller.buildhandlers.AddBallHandler;
import OOAD_Lab03_gizmo.controller.buildhandlers.AddHandler;
import OOAD_Lab03_gizmo.controller.buildhandlers.ClearBoardHandler;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.*;
import java.util.Optional;

public class LoadHandler implements EventHandler<ActionEvent> {
	private final Stage stage;
	private final GizmoModel model;
	private final BoardController boardController;
	private final BuildViewController buildViewController;
	private final SaveHandler saveHandler;
	private String errorMessages = "";
	private int errorCount = 0;
	private final Label log;

	private ActionEvent event;

	public LoadHandler(Stage stage, BoardController boardController, BuildViewController buildViewController,
	                   GizmoModel model,
	                   Label log) {
		this.boardController = boardController;
		this.buildViewController = buildViewController;
		this.log = log;
		this.stage = stage;
		this.model = model;
		saveHandler = new SaveHandler(stage, buildViewController, model);
//		saveHandler = new SaveHandler();
	}

	@Override
	public void handle(ActionEvent event) {

		this.event = event;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter eFilter = new FileChooser.ExtensionFilter("XML Files (*.xml)", "*.xml");

		fileChooser.getExtensionFilters().add(eFilter);
		fileChooser.setSelectedExtensionFilter(eFilter);
		if (Logger.getSavedBoard()) {
			fileChooser.setTitle("Load Board");
			File file = fileChooser.showOpenDialog(stage);

			if (file != null) {
				loadGame(file);
			}
		} else {
			ButtonType yes = new ButtonType("Yes");
			ButtonType no = new ButtonType("No");
			ButtonType cancel = new ButtonType("Cancel");

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save current layout?", yes, no, cancel);
			alert.setTitle("Board Layout Not Saved");

			Window window = alert.getDialogPane().getScene().getWindow();
			window.setOnCloseRequest(someEvent -> window.hide());
			Optional<ButtonType> result = alert.showAndWait();
			result.ifPresent(buttonType ->
			{
				if (buttonType == yes) {
					saveHandler.handle(event);
					fileChooser.setTitle("Load Board");
					File file = fileChooser.showOpenDialog(stage);

					if (file != null) {
						loadGame(file);
					}
				} else if (buttonType == no) {
					fileChooser.setTitle("Load Board");
					File file = fileChooser.showOpenDialog(stage);

					if (file != null) {
						loadGame(file);
					}
				} else {
					event.consume();
				}
			});

			stage.getScene().getRoot().setEffect(null);
		}

	}

	private void clearBoard() {
		new ClearBoardHandler(boardController, model, log).handle(event);
	}

	public void loadGame(File file) {
		try {
			clearBoard();

			errorCount = 0;
			errorMessages = "";

			SL_Handler.LoadBoard(SL_Handler.LoadXMLFromFile(file), model);

			for (GizmoWidget gizmo : model.getWidgetList()) {
				new AddHandler(model, boardController, buildViewController, gizmo.getType(), log)
						.handle(gizmo.getName());
			}
			for (Ball ball : model.getBallList()) {
				if (ball != null) {
					new AddBallHandler(model, boardController, buildViewController, log).handle(ball.getName());
				}
			}
			if (errorMessages.equals("")) {
				log.setText("Board loaded successfully.");
				Logger.setSavedBoard(true);
			} else {
				log.setText(errorMessages);
				Logger.setSavedBoard(true);
			}
		} catch (FileNotFoundException e) {
			log.setText("Error: No file found.");
			System.err.println("Error: No file found." + e);
		} catch (IOException e) {
			log.setText("Error: Wrong file format.");
			System.err.println("Error: Wrong file format.");
		} catch (Exception e) {
			log.setText("Error: Wrong File.");
			System.err.println("Error: Wrong File.");
		}
	}

//	private void checkAction(String[] string) {
//
//		double x;
//		double y;
//		double x2;
//		double y2;
//	}

	private void errorHandler(String s) {
		errorMessages = "Error found at: (" + s + ")";
		if (errorCount == 1) {
			errorMessages += "+ 1 other issue";
		} else if (errorCount > 1) {
			errorMessages += " + " + errorCount + " other issues";
		}
		errorCount++;
	}
}
