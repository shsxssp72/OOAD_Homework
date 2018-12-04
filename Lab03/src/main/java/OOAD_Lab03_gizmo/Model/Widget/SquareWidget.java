package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Utilities.IO_Interface;
import OOAD_Lab03_gizmo.Utilities.Observer;
import physics.Circle;
import physics.LineSegment;

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
	private Color color=Color.cyan;
	private final List<Observer> observerList=new ArrayList<>();
	private boolean triggered=false;

	@Override
	public String getType()
	{
		return "SquareWidget";
	}
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
		InitSquareVertices(vertices,xpos,ypos,width);
	}


	private void InitLines()
	{
		InitSquareEdges(edges,xpos,ypos,width);
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
		NotifyAll();
	}

	@Override
	public void setYpos(double y)
	{
		ypos=y;
		NotifyAll();
	}

	@Override
	public void trigger(boolean keyPressed,boolean keyReleased)
	{

	}

	@Override
	public void setName(String inName)
	{
		name=inName;
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		return new BoundingBox(xpos,ypos,xpos+width,ypos+width);
	}

	@Override
	public int getRotateTime()
	{
		return 0;
	}

	@Override
	public List<Observer> getObserverList()
	{
		return observerList;
	}
	static void InitSquareEdges(ArrayList<LineSegment> edges,double xpos,double ypos,double width)
	{
		edges.clear();
		edges.add(new LineSegment(xpos,ypos,xpos,ypos+width));//Left
		edges.add(new LineSegment(xpos+width,ypos,xpos+width,ypos+width));//Right
		edges.add(new LineSegment(xpos,ypos+width,xpos+width,ypos+width));//Top
		edges.add(new LineSegment(xpos,ypos,xpos+width,ypos));//Bottom
	}
	static void InitSquareVertices(ArrayList<Circle> vertices,double xpos,double ypos,double width)
	{
		vertices.clear();
		vertices.add(new Circle(xpos,ypos+width,0));//TopLeft
		vertices.add(new Circle(xpos+width,ypos+width,0));//TopRight
		vertices.add(new Circle(xpos,ypos,0));//BottomLeft
		vertices.add(new Circle(xpos+width,ypos,0));//BottomRight
	}
	@Override
	public boolean isTriggered()
	{
		return triggered;
	}
}
