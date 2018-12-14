package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Model.Displayable;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;

import static OOAD_Lab03_gizmo.config.Constants.BOARD_HEIGHT;
import static OOAD_Lab03_gizmo.config.Constants.BOARD_WIDTH;

public class Wall implements Displayable
{
	private ArrayList<LineSegment> edges= new ArrayList<>();
	private ArrayList<Circle> vertices= new ArrayList<>();

	public Wall()
	{
		edges.add(new LineSegment(0,0,0, BOARD_HEIGHT));
		edges.add(new LineSegment(0, BOARD_HEIGHT,BOARD_WIDTH, BOARD_HEIGHT));
		edges.add(new LineSegment(BOARD_WIDTH, BOARD_HEIGHT,BOARD_WIDTH,0));
		edges.add(new LineSegment(BOARD_WIDTH,0,0,0));

		vertices.add(new Circle(0,0,0));
		vertices.add(new Circle(BOARD_WIDTH,0,0));
		vertices.add(new Circle(0, BOARD_HEIGHT,0));
		vertices.add(new Circle(BOARD_WIDTH, BOARD_HEIGHT,0));
	}

	@Override
	public String getName()
	{
		return "Wall";
	}

	@Override
	public double getXpos()
	{
		return -1;
	}

	@Override
	public double getYpos()
	{
		return -1;
	}

	@Override
	public List<LineSegment> getEdges()
	{
		return edges;
	}

	@Override
	public List<Circle> getVertices()
	{
		return vertices;
	}

	@Override
	public String getType()
	{
		return "Wall";
	}

	@Override
	public void setXpos(double x)
	{
		//Leave empty
	}

	@Override
	public void setYpos(double y)
	{
		//Leave empty
	}

	@Override
	public void setName(String inName)
	{
		//Leave empty
	}
}
