package OOAD_Lab03_gizmo.view.visual;

import OOAD_Lab03_gizmo.Utilities.Observer;
import javafx.scene.Node;

public interface GizmoVisual extends Observer
{
	double getXPos();

	double getYPos();

	void setCoordinates(double x,double y);

	Node getNode();

	void setSelected(boolean isSelected);
}
