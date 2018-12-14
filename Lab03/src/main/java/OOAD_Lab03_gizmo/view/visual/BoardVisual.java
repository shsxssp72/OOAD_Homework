package OOAD_Lab03_gizmo.view.visual;

import javafx.scene.Group;
import javafx.scene.layout.Pane;

import static OOAD_Lab03_gizmo.config.Constants.*;

public class BoardVisual extends Pane {
	private final Group cellGroup;
	private final Group objectGroup;


	public BoardVisual() {
		double cellSize = ONE_L_IN_PIXELS;
		this.setPrefSize(cellSize * BOARD_WIDTH, cellSize * BOARD_HEIGHT);

		cellGroup = new Group();

		for (int i = 0; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				Pane cell = new Pane();

//				add css
				cell.getStyleClass().add("grid");
				cell.setPrefSize(cellSize, cellSize);

//				set position
				cell.setTranslateX(j * ONE_L_IN_PIXELS);
				cell.setTranslateY(i * ONE_L_IN_PIXELS);
				cellGroup.getChildren().add(cell);
				cellGroup.setMouseTransparent(true);
			}
		}
		this.getChildren().add(cellGroup);

		objectGroup = new Group();
		this.getChildren().add(objectGroup);


		this.getStyleClass().add("board");

	}


	/**
	 * add gizmoVisual to Board
	 * Board stores all gizmos
	 *
	 * @param gizmoVisual any gizmoWidget on the board
	 */
	public void add(GizmoVisual gizmoVisual) {
		objectGroup.getChildren().add(gizmoVisual.getNode());
		cellGroup.toFront();
	}

	/**
	 * add ballVisual to Board
	 * Board stores all balls
	 *
	 * @param ballVisual any ball on the board
	 */
	public void add(BallVisual ballVisual) {
		objectGroup.getChildren().add(ballVisual);
		cellGroup.toFront();
	}

	public void remove(GizmoVisual gizmoVisual) {
		objectGroup.getChildren().remove(gizmoVisual.getNode());
	}

	public void remove(BallVisual ballVisual) {
		objectGroup.getChildren().remove(ballVisual);
	}

	public void clearBoard() {
		objectGroup.getChildren().clear();
	}
}
