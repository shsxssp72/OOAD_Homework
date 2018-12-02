package OOAD_Lab03_gizmo.Model;

import physics.Circle;
import physics.LineSegment;

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

	String getType();
}
