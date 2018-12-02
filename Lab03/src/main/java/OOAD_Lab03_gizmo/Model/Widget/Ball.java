package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Model.Displayable;
import OOAD_Lab03_gizmo.Utilities.IO_Interface;
import OOAD_Lab03_gizmo.Utilities.Observable;
import OOAD_Lab03_gizmo.Utilities.Observer;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Ball implements Displayable, Observable
{
	private String name;
	private double xpos;
	private double ypos;
	private double width=0.5;
	private Color color=Color.gray;
	boolean remove=false;
	private Vect velocity;
	private final List<Observer> observerList=new ArrayList<>();

	public Ball(String inName,double x,double y,double velocityX,double velocityY)
	{
		name=inName;
		xpos=x;
		ypos=y;
		velocity=new Vect(velocityX,velocityY);
	}

	public String getName()
	{
		return name;
	}

	public double getXpos()
	{
		return xpos;
	}

	public double getYpos()
	{
		return ypos;
	}

	public double getWidth()
	{
		return width;
	}

	public Color getColor()
	{
		return color;
	}

	public boolean isRemove()
	{
		return remove;
	}

	public void setRemove(boolean remove)
	{
		this.remove=remove;
		NotifyAll();
	}

	public Vect getVelocity()
	{
		return velocity;
	}

	public double getVelocityX()
	{
		return velocity.getXVect();
	}

	public double getVelocityY()
	{
		return velocity.getYVect();
	}


	public void setName(String name)
	{
		this.name=name;
	}

	public void setXpos(double xpos)
	{
		this.xpos=xpos;
		NotifyAll();

	}

	public void setYpos(double ypos)
	{
		this.ypos=ypos;
		NotifyAll();
	}

	public void setVelocity(Vect velocity)
	{
		double velX=velocity.x();
		double signX=Math.signum(velX);
		double velY=velocity.y();
		double signY=Math.signum(velY);

		velX=Math.max(Math.abs(velX),0.1);
		velX=Math.min(Math.abs(velX),200);
		velY=Math.max(Math.abs(velY),0.1);
		velY=Math.min(Math.abs(velY),200);

		this.velocity=new Vect(velX*signX,velY*signY);

	}

	public List<Circle> getEdge()
	{
		List<Circle> edge=new ArrayList<>();
		edge.add(new Circle(xpos,ypos,width/2));
		return edge;
	}

	@Override
	public List<LineSegment> getEdges()
	{
		return null;
	}

	@Override
	public List<Circle> getVertices()
	{
		return null;
	}

	@Override
	public String getType()
	{
		return "Ball";
	}

	public Vect getCenter()
	{
		return new Vect(xpos,ypos);
	}

	public void move(double moveTime)
	{
		xpos+=moveTime*velocity.x();
		ypos+=moveTime*velocity.y();
		NotifyAll();
	}

	@Override
	public List<Observer> getObserverList()
	{
		return observerList;
	}
}
