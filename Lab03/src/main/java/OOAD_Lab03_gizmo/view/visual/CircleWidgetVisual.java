package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class CircleWidgetVisual extends GizmoVisualContainer {

	private final Circle circle;

	public CircleWidgetVisual(GizmoWidget circleWidget) {
		super(circleWidget);

		double radius = ONE_L_IN_PIXELS / 2;
		circle = new Circle(radius);
		circle.setCenterX(radius);
		circle.setCenterY(radius);
		circle.getStyleClass().add("circleWidget");
		circle.setMouseTransparent(true);
		this.getChildren().add(circle);

		this.setMaxSize(radius * 2, radius * 2);
	}

	public CircleWidgetVisual() {
		this(null);
	}

	@Override
	public void update() {
		super.update();
		if (circle != null) {
			if (gizmoWidget.isTriggered()) {
				circle.setFill(Color.rgb(255, 64, 0));
			} else {
				circle.setFill(Color.rgb(0, 200, 155));
			}
		}
	}
}
