package OOAD_Lab03_gizmo.Model;

import OOAD_Lab03_gizmo.physics.Circle;
import OOAD_Lab03_gizmo.physics.LineSegment;

import java.util.List;

public interface Displayable
{
	String getName();

	double getXpos();

	double getYpos();

	List<LineSegment> getEdges();

	List<Circle> getVertices();

	void setXpos(double x);

	void setYpos(double y);

	void setName(String inName);
}
