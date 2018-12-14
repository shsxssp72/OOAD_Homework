package OOAD_Lab03_gizmo.controller.playhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.mischandlers.LoadHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ResetHandler implements EventHandler<ActionEvent> {
	private final GizmoModel model;

	public static final File SAVE_STATE = new File(System
			.getProperty("user.home") + File.separator + "Documents" + File.separator + "Gizmoball" + File.separator + "tmp_state.xml");
	private final LoadHandler loadHandler;

	public ResetHandler(GizmoModel model, LoadHandler loadHandler) {
		this.model = model;
		this.loadHandler = loadHandler;

		try {
			Files.createDirectories(SAVE_STATE.getParentFile().toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handle(ActionEvent event) {
		loadHandler.loadGame(SAVE_STATE);
	}
}
