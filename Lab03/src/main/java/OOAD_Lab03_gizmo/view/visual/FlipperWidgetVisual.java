package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.FlipperWidget;
import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.beans.NamedArg;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class FlipperWidgetVisual extends GizmoVisualContainer {

	private Circle pivotCircle;
	private double pivotX;
	private double pivotY;
	private Rotate flip;
	private Rectangle rectangle;
	private FlipperWidget flipper;

	public FlipperWidgetVisual(GizmoWidget flipperWidget) {
		super(flipperWidget);

		flipper = (FlipperWidget) flipperWidget;
		double width = ONE_L_IN_PIXELS / 2;
		double length = ONE_L_IN_PIXELS * 2;

		rectangle = new Rectangle(width, length);
		rectangle.setArcWidth(width);
		rectangle.setArcHeight(width);
		rectangle.getStyleClass().add("flipperWidget");
		rectangle.setMouseTransparent(true);

		String RIGHTFLIPPER = "RIGHT_FLIPPER";

		if (flipperWidget.getType().equals(RIGHTFLIPPER)) {
			rectangle.setX(length - width);
		}

		this.getChildren().add(rectangle);
		this.setPrefSize(length, length);

		flip = new Rotate(0, pivotX, pivotY);
		rectangle.getTransforms().add(flip);

		pivotCircle = new Circle(pivotX, pivotY, 0.66 * width / 2);
		pivotCircle.getStyleClass().add("flipperPivot");

		pivotCircle.setMouseTransparent(true);
		pivotCircle.getTransforms().add(flip);
		this.getChildren().add(pivotCircle);
		update();
	}

	private void setPivots() {
		pivotX = (flipper.getxWithOffset() - flipper.getXpos()) * ONE_L_IN_PIXELS + ONE_L_IN_PIXELS / 4;
		pivotY = (flipper.getyWithOffset() - flipper.getYpos()) * ONE_L_IN_PIXELS + ONE_L_IN_PIXELS / 4;
	}

	public FlipperWidgetVisual(@NamedArg("type") String flipperType) {
		super(null);

		double width = ONE_L_IN_PIXELS / 2;
		double length = ONE_L_IN_PIXELS * 2;

		Rectangle rectangleVisual = new Rectangle(width, length);
		rectangleVisual.setArcWidth(width);
		rectangleVisual.setArcHeight(width);
		rectangleVisual.getStyleClass().add("flipperGizmo");

		String RIGHTFLIPPER = "RIGHT_FLIPPER";

		if (flipperType.equals(RIGHTFLIPPER)) {
			rectangleVisual.setX(length - width);
		}

		this.getChildren().add(rectangleVisual);

		Circle pivotCircleVisual = new Circle(rectangleVisual.getX() + width / 2, rectangleVisual.getY() + width / 2, 0.66 * width / 2);
		pivotCircleVisual.getStyleClass().add("flipperPivot");
//		pivotCircle.setMouseTransparent(true);

		this.getChildren().add(pivotCircleVisual);
	}

	@Override
	public void update() {
		super.update();

		if (rectangle != null) {
			FlipperWidget flipperWidget = (FlipperWidget) gizmoWidget;

			double rectangleXPos = (flipperWidget.getxWithOffset() - flipperWidget.getXpos()) * ONE_L_IN_PIXELS;
			double rectangleYPos = (flipperWidget.getyWithOffset() - flipperWidget.getYpos()) * ONE_L_IN_PIXELS;

			rectangle.setX(rectangleXPos);
			rectangle.setY(rectangleYPos);

			double pivotXPos = (flipperWidget.getxWithOffset() - flipperWidget.getXpos()) * ONE_L_IN_PIXELS + ONE_L_IN_PIXELS / 4;
			double pivotYPos = (flipperWidget.getyWithOffset() - flipperWidget.getYpos()) * ONE_L_IN_PIXELS + ONE_L_IN_PIXELS / 4;

			pivotCircle.setCenterX(pivotXPos);
			pivotCircle.setCenterY(pivotYPos);
		}

		if (flip != null) {
			setPivots();
			flip.setPivotX(pivotX);
			flip.setPivotY(pivotY);
			flip.setAngle(((FlipperWidget) gizmoWidget).getOldAngle());
		}
	}
}
