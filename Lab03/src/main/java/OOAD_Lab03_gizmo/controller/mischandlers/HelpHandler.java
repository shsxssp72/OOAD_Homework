package OOAD_Lab03_gizmo.controller.mischandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

public class HelpHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("This is a demo Gizmoball Project. (Ver. 0.03b)");

		alert.showAndWait();
	}
}
