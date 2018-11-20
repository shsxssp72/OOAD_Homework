package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Utilities.IO_Interface;
import OOAD_Lab03_gizmo.physics.Circle;
import OOAD_Lab03_gizmo.physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SquareWidget implements GizmoWidget
{
	private ArrayList<LineSegment> edges= new ArrayList<>();
	private ArrayList<Circle> vertices= new ArrayList<>();
	private ArrayList<String> connections = new ArrayList<>();
	private String name="";
	private double xpos=0;
	private double ypos=0;
	private double width=1;
	private int rotateTime=0;
	private Color color=Color.cyan;


	public SquareWidget(String name,double xpos,double ypos)
	{
		this.name=name;
		this.xpos=xpos;
		this.ypos=ypos;
		InitCircles();
		InitLines();
	}

	private void InitCircles()
	{
		vertices.clear();
		vertices.add(new Circle(xpos,ypos+width,0));//TopLeft
		vertices.add(new Circle(xpos+width,ypos+width,0));//TopRight
		vertices.add(new Circle(xpos,ypos,0));//BottomLeft
		vertices.add(new Circle(xpos+width,ypos,0));//BottomRight
	}
	private void InitLines()
	{
		edges.clear();
		edges.add(new LineSegment(xpos,ypos,xpos,ypos+width));//Left
		edges.add(new LineSegment(xpos+width,ypos,xpos+width,ypos+width));//Right
		edges.add(new LineSegment(xpos,ypos+width,xpos+width,ypos+width));//Top
		edges.add(new LineSegment(xpos,ypos,xpos+width,ypos));//Bottom

	}



	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public double getXpos()
	{
		return xpos;
	}

	@Override
	public double getYpos()
	{
		return ypos;
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
	public void rotate()
	{
		IO_Interface.ConsoleWriteLine("squares do not need to rotate.");
	}

	@Override
	public List<String> getConnection()
	{
		return connections;
	}

	@Override
	public Color getColor()
	{
		return color;
	}

	@Override
	public void setXpos(double x)
	{
		xpos=x;
	}

	@Override
	public void setYpos(double y)
	{
		ypos=y;
	}

	@Override
	public void setName(String inName)
	{
		name=inName;
	}
}
