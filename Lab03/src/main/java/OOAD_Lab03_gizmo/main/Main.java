package OOAD_Lab03_gizmo.main;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.controller.GizmoballController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * This is the main() entrance of this project
 *
 * @author Kimisecond
 */
public class Main extends Application {

	private Stage primaryStage;

	private BorderPane rootLayout;


	public static void main(String[] args) {
		launch(args);
	}


	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(getClass().getResource("/OOAD_Lab03_gizmo/view/fxml/GizmoballRootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();

			GizmoballController gizmoballController = loader.getController();
			GizmoModel model = new GizmoModel();


//			Give the controller access to the main app.
			gizmoballController.setup(primaryStage, model);

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);

			scene.getStylesheets().add("/OOAD_Lab03_gizmo/view/css/SolarizedLight.css");

			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}


//		// Try to load last opened person file.
//		File file = getPersonFilePath();
//		if (file != null) {
//			loadPersonDataFromFile(file);
//		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Gizmoball v0.03b");

		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/OOAD_Lab03_gizmo/view/image/icon.png")));

		initRootLayout();
	}


}
