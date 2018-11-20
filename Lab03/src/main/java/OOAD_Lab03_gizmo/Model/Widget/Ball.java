package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.physics.Vect;

import java.awt.*;

public class Ball
{
	private String name;
	private double xpos;
	private double ypos;
	private double width=1;
	private Color color=Color.gray;
	boolean stopped=false;
	private Vect velocity;

	public Ball(String inName,double x,double y,double velocityX,double velocityY)
	{
		name=inName;
		xpos = x;
		ypos = y;
		velocity = new Vect(velocityX,velocityY);
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

	public boolean isStopped()
	{
		return stopped;
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

	public void stop()
	{
		stopped=true;
	}
	public void start()
	{
		stopped=false;
	}

	public void setName(String name)
	{
		this.name=name;
	}

	public void setXpos(double xpos)
	{
		this.xpos=xpos;
	}

	public void setYpos(double ypos)
	{
		this.ypos=ypos;
	}

	public void setVelocity(Vect velocity)
	{
		this.velocity=velocity;
	}
}
