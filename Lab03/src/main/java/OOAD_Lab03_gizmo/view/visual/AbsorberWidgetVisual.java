package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.scene.shape.Rectangle;


import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class AbsorberWidgetVisual extends GizmoVisualContainer {

	private final Rectangle rectangle;

	public AbsorberWidgetVisual(GizmoWidget absorberWidget) {
		super(absorberWidget);

		double side = ONE_L_IN_PIXELS;

		rectangle = new Rectangle(side, side);
		rectangle.getStyleClass().add("absorberWidget");
		rectangle.setMouseTransparent(true);

		this.setMaxSize(side, side);

		this.getChildren().add(rectangle);
	}

	public AbsorberWidgetVisual() {
		this(null);
	}

	@Override
	public void setWidth(double width) {
		rectangle.setWidth(width);
	}

	@Override
	public void setHeight(double height) {
		rectangle.setHeight(height);
	}
}