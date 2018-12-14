package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;


public class SquareWidgetVisual extends GizmoVisualContainer {

	private final Rectangle square;

	public SquareWidgetVisual(GizmoWidget squareWidget) {
		super(squareWidget);

		double side = ONE_L_IN_PIXELS;
		square = new Rectangle(side, side);

		square.getStyleClass().add("squareWidget");
		square.setMouseTransparent(true);

		this.setMaxSize(side, side);

		this.getChildren().add(square);
	}

	public SquareWidgetVisual() {
		this(null);
	}

	@Override
	public void update() {
		super.update();

		if (square != null) {
			if (gizmoWidget.isTriggered()) {
				square.setFill(Color.rgb(0, 255, 96));
			} else {
				square.setFill(Color.rgb(255, 0, 159));
			}
		}
	}
}
