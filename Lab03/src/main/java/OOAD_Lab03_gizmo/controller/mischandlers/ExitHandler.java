package OOAD_Lab03_gizmo.controller.mischandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Utilities.Logger;
import OOAD_Lab03_gizmo.controller.BuildViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

public class ExitHandler implements EventHandler<ActionEvent> {
	private final Stage stage;
	private final SaveHandler saveHandler;
	private final KeepSafeHandler keepSafeHandler;

	public ExitHandler(BuildViewController buildViewController, Stage stage, GizmoModel model) {
		saveHandler = new SaveHandler(stage, buildViewController, model);
		keepSafeHandler = new KeepSafeHandler();
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent event) {
		if (Logger.getSavedBoard()) {
			System.exit(0);
		} else {
			ButtonType yes = new ButtonType("Yes");
			ButtonType no = new ButtonType("No");
			ButtonType cancel = new ButtonType("Cancel");

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save before exiting?", yes, no, cancel);


			Window window = alert.getDialogPane().getScene().getWindow();
			window.setOnCloseRequest(someEvent -> window.hide());
			Optional<ButtonType> result = alert.showAndWait();
			result.ifPresent(buttonType ->
			{
				if (buttonType == yes) {
					saveHandler.handle(event);
					System.exit(0);
				} else if (buttonType == no) {
					System.exit(0);
				} else {
					keepSafeHandler.handle(event);
				}
			});

		}
	}
}
