package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;


public class TriangleWidgetVisual extends GizmoVisualContainer {

	private final Polygon triangle;

	public TriangleWidgetVisual(GizmoWidget triangleWidget) {
		super(triangleWidget);

		double side = ONE_L_IN_PIXELS;
		triangle = new Polygon(0.0, 0.0, side, 0.0, 0.0, side);
		triangle.getStyleClass().add("triangleWidget");
		triangle.setMouseTransparent(true);
		if (gizmoWidget != null) {
			double angle = gizmoWidget.getRotateTime() * 90;
			rotate(angle);
		}
		this.setMaxSize(side, side);

		this.getChildren().add(triangle);
	}

	public TriangleWidgetVisual() {
		this(null);
	}

	public void rotate(double someAngle) {
		triangle.setRotate(someAngle);
	}

	@Override
	public void update() {
		super.update();
		if (triangle != null) {
			double angle = gizmoWidget.getRotateTime() * 90;
			rotate(angle);
			if (gizmoWidget.isTriggered()) {
				triangle.setFill(Color.rgb(68, 0, 255));
			} else {
				triangle.setFill(Color.rgb(187, 255, 0));
			}
		}
	}

}
