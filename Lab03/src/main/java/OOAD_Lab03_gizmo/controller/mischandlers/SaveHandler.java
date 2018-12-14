package OOAD_Lab03_gizmo.controller.mischandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Utilities.Logger;
import OOAD_Lab03_gizmo.Utilities.SL_Handler;
import OOAD_Lab03_gizmo.controller.BuildViewController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveHandler implements EventHandler<ActionEvent>
{
	private Stage stage;
	private BuildViewController buildViewController;
	private GizmoModel model;

	public SaveHandler(Stage stage,BuildViewController buildViewController,GizmoModel model)
	{
		this.buildViewController=buildViewController;
		this.stage=stage;
		this.model=model;
	}

//	public SaveHandler(GizmoModel model)
//	{
//		this.model=model;
//	}

	@Override
	public void handle(ActionEvent event)
	{
		FileChooser fileChooser=new FileChooser();

		FileChooser.ExtensionFilter eFilter=new FileChooser.ExtensionFilter("XML Files (*.XML)","*.xml");

		fileChooser.getExtensionFilters().add(eFilter);

		fileChooser.setSelectedExtensionFilter(eFilter);
		fileChooser.setInitialFileName("Untitled");
		fileChooser.setTitle("Save Board");
		File file=fileChooser.showSaveDialog(stage);
		if(file!=null)
		{
			saveGame(file);
		}
	}


	public void saveGame(File file)
	{

		SL_Handler.SaveXMLToFile(SL_Handler.SaveBoard(model.getBoard()),file);
		Logger.setSavedBoard(true);
		buildViewController.setLog("Saved!");

	}


}
