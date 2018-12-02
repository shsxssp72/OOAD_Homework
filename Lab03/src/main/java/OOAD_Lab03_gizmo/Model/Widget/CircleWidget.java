package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Utilities.IO_Interface;
import OOAD_Lab03_gizmo.Utilities.Observer;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CircleWidget implements GizmoWidget
{
	private ArrayList<LineSegment> edges=new ArrayList<>();
	private ArrayList<Circle> vertices=new ArrayList<>();
	private ArrayList<String> connections=new ArrayList<>();
	private String name="";
	private double xpos=0;
	private double ypos=0;
	private double width=1;
	private Color color=Color.gray;
	private final List<Observer> observerList=new ArrayList<>();

	@Override
	public String getType()
	{
		return "CircleWidget";
	}

	public CircleWidget(String name,double xpos,double ypos)
	{
		this.name=name;
		this.xpos=xpos;
		this.ypos=ypos;
		InitCircles();
//		InitLines();
	}

	void InitCircles()
	{
		vertices.clear();
		vertices.add(new Circle(xpos+width/2,ypos+width/2,width/2));
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
	public java.util.List<LineSegment> getEdges()
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
		IO_Interface.ConsoleWriteLine("circles do not need to rotate.");
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
	public void setName(String inName)
	{
		name=inName;
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		return new BoundingBox(xpos,ypos,xpos+width,ypos+width);
	}
	public Vect getCenter()
	{
		return new Vect(xpos+width/2,ypos+width/2);
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

	@Override
	public void trigger(boolean keyPressed,boolean keyReleased)
	{

	}
}
