package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.Utilities.Observer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Circle;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;


public class BallVisual extends Group implements Observer, Toggle {

	private final Circle ball;
	private final double x;
	private final double y;
	private final Ball ballModel;
	private final BooleanProperty isSelected;
	private final ObjectProperty<ToggleGroup> toggleGroup;

	public BallVisual(Ball ballModel) {
		super();
		this.x = ballModel.getXpos() * ONE_L_IN_PIXELS;
		this.y = ballModel.getYpos() * ONE_L_IN_PIXELS;
		this.ballModel = ballModel;

//		this is the size in the board
		double radius = ONE_L_IN_PIXELS / 4;
		ball = new Circle(radius);

		this.getChildren().add(ball);
		ball.getStyleClass().add("ball");
		ball.setMouseTransparent(true);
		this.setTranslateX(x);
		this.setTranslateY(y);

		ballModel.RegisterObserver(this);

		isSelected = new SimpleBooleanProperty(false);
		toggleGroup = new SimpleObjectProperty<>(null);
	}

	public BallVisual() {
		this.x = 0;
		this.y = 0;
		this.ballModel = null;

//		this is the size in the buttons
		double radius = ONE_L_IN_PIXELS / 4;
		ball = new Circle(radius);

		this.getChildren().add(ball);
		ball.getStyleClass().add("ball");
		this.getStyleClass().add("ballContainer");

		isSelected = new SimpleBooleanProperty(false);
		toggleGroup = new SimpleObjectProperty<>(null);
	}

	@Override
	public void update() {
		this.setTranslateX(ballModel.getXpos() * ONE_L_IN_PIXELS);
		this.setTranslateY(ballModel.getYpos() * ONE_L_IN_PIXELS);

		this.toBack();
	}


	@Override
	public ToggleGroup getToggleGroup() {
		return toggleGroup.getValue();
	}

	@Override
	public void setToggleGroup(ToggleGroup toggleGroup) {
		this.toggleGroup.setValue(toggleGroup);
	}

	@Override
	public ObjectProperty<ToggleGroup> toggleGroupProperty() {
		return toggleGroup;
	}

	@Override
	public boolean isSelected() {
		return isSelected.get();
	}

	@Override
	public void setSelected(boolean selected) {
		if (selected) {
			this.getStyleClass().add("isSelected");
		} else {
			this.getStyleClass().remove("isSelected");
		}
		this.isSelected.setValue(selected);
	}

	@Override
	public BooleanProperty selectedProperty() {
		return isSelected;
	}
}
