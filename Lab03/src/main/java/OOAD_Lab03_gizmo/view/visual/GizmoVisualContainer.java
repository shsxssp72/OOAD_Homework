package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;


public class GizmoVisualContainer extends Pane implements GizmoVisual, Toggle {

	final GizmoWidget gizmoWidget;

	//	default useless
	private boolean canFocus;

	protected Shape shape;
	private final BooleanProperty selected;
	private final ObjectProperty<ToggleGroup> toggleGroup;

	GizmoVisualContainer(GizmoWidget gizmoWidget) {
		this.gizmoWidget = gizmoWidget;

		if (gizmoWidget != null) {
			this.gizmoWidget.RegisterObserver(this);
			update();
		}

		this.canFocus = false;

		this.getStyleClass().add("gizmoContainer");
		this.setPickOnBounds(true);

		selected = new SimpleBooleanProperty(false);
		toggleGroup = new SimpleObjectProperty<>(null);
	}

	@Override
	public double getXPos() {
		return this.getTranslateX();
	}

	@Override
	public double getYPos() {
		return this.getTranslateY();
	}

	@Override
	public void setCoordinates(double x, double y) {
		this.setTranslateX(x);
		this.setTranslateY(y);
	}

	@Override
	public Node getNode() {
		return this;
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
		return selected.get();
	}

	@Override
	public void setSelected(boolean isSelected) {
		if (isSelected) {
			this.getStyleClass().add("selected");
		} else {
			this.getStyleClass().remove("selected");
		}
		this.selected.setValue(isSelected);
	}

	@Override
	public BooleanProperty selectedProperty() {
		return selected;
	}

	@Override
	public void update() {
		double xPosition = gizmoWidget.getXpos() * ONE_L_IN_PIXELS;
		double yPosition = gizmoWidget.getYpos() * ONE_L_IN_PIXELS;

		this.setCoordinates(xPosition, yPosition);
	}

	public void setCanFocus(boolean canFocus) {
		this.canFocus = canFocus;
	}
}
