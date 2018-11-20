package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Model.Displayable;
import OOAD_Lab03_gizmo.physics.Circle;
import OOAD_Lab03_gizmo.physics.LineSegment;
import OOAD_Lab03_gizmo.physics.Vect;

import java.awt.*;
import java.util.List;

public interface GizmoWidget extends Displayable
{

	void rotate();

	List<String> getConnection();

	Color getColor();

}
