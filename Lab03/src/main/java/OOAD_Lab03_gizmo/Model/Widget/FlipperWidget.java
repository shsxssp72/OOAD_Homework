package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Utilities.Observer;
import physics.*;

import java.util.ArrayList;
import java.util.List;

import static OOAD_Lab03_gizmo.config.Constants.*;

public class FlipperWidget implements GizmoWidget
{

	private ArrayList<String> connections=new ArrayList<>();
	private String name="";
	private double xpos=0;
	private double ypos=0;
	private double xWithOffset;
	private double yWithOffset;
	private double offset=0;
	private Vect pivot;
	private double width=WIDGET_LENGTH;
	private double length=FLIPPER_LENGTH;
	private int rotateTime=0;
	private boolean flipUp;
	private double angle;
	private double startingAngle;
	private double oldAngle;
	private double angularVelocity;
	private boolean keyPressed;
	private boolean keyReleased;
	private final List<Observer> observerList=new ArrayList<>();
	private boolean triggered=false;
	private String flipperType;


	public FlipperWidget(double xpos,double ypos,double angle,String type,String name)
	{

		this.xpos=xpos;
		this.ypos=ypos;
		this.xWithOffset=xpos+offset;
		this.yWithOffset=ypos;
		this.name=name;
		if(type.equals("RIGHT_FLIPPER"))
		{
			offset=ONE_L+width;
			this.flipperType=type;
			this.rotateTime=1;
		}
		else
		{
			this.flipperType="LEFT_FLIPPER";
			this.rotateTime=0;
		}


		this.angle=angle;
		this.oldAngle=angle;

		angularVelocity=Math.toRadians(FLIPPER_ANGULAR_VELOCITY);

		double radius=width/2;
		pivot=new Vect(this.xpos+offset+radius,this.ypos+radius);

	}


	@Override
	public String getType()
	{
		return flipperType;
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
	public List<String> getConnection()
	{
		return connections;
	}

	@Override
	public void setXpos(double x)
	{
		this.xWithOffset+=x-this.xpos;
		xpos=x;
		NotifyAll();
	}

	@Override
	public void setYpos(double y)
	{
		this.yWithOffset+=y-this.ypos;
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

	@Override
	public int getRotateTime()
	{
		return rotateTime;
	}

	@Override
	public List<Observer> getObserverList()
	{
		return observerList;
	}

	public double getAngle()
	{
		return angle;
	}

	public double getOldAngle()
	{
		return oldAngle;
	}

	@Override
	public boolean getKeyPressed()
	{
		return keyPressed;
	}


	private void flip()
	{
		oldAngle=angle;
		if(this.flipperType.equals("LEFT_FLIPPER"))
		{
			if(flipUp)
			{
				angle=Math.max(angle-DELTA_ANGLE,startingAngle-90);
				angularVelocity=Math.toRadians(-FLIPPER_ANGULAR_VELOCITY);
			}
			else
			{
				angle=Math.min(angle+DELTA_ANGLE,startingAngle);
				angularVelocity=Math.toRadians(FLIPPER_ANGULAR_VELOCITY);
			}
			if(angle<=-90)
			{
				if(keyPressed&&!keyReleased)
				{
					flipUp=true;
				}
				else if(keyPressed)
				{
					flipUp=false;
				}
				else if(keyReleased)
				{
					flipUp=false;
				}
			}
		}
		else if(this.flipperType.equals("RIGHT_FLIPPER"))
		{
			if(flipUp)
			{
				angle=Math.min(angle+DELTA_ANGLE,startingAngle+90);
				angularVelocity=Math.toRadians(FLIPPER_ANGULAR_VELOCITY);
			}
			else
			{
				angle=Math.max(angle-DELTA_ANGLE,startingAngle);
				angularVelocity=Math.toRadians(-FLIPPER_ANGULAR_VELOCITY);
			}

			if(angle >= 90)
			{
				if(keyPressed&&!keyReleased)
				{
					flipUp=true;
				}
				else if(keyPressed)
				{
					flipUp=false;
				}
				else if(keyReleased)
				{
					flipUp=false;
				}
			}
		}
		if(angle==0||angle==90||angle==-90)
		{
			angularVelocity=0;
		}


		this.NotifyAll();
	}

	@Override
	public void rotate()
	{
//        angle += 90;

		if(rotateTime<3)
		{
			if(rotateTime==0)
			{
				xWithOffset=xpos+ONE_L+width;
				flipperType="RIGHT_FLIPPER";
				angle=0;
				startingAngle=0;
			}
			else if(rotateTime==1)
			{
				yWithOffset=ypos+ONE_L+width;
				flipperType="RIGHT_FLIPPER";
				angle=90;
				startingAngle=90;
			}
			else if(rotateTime==2)
			{
				xWithOffset=xpos;
				flipperType="LEFT_FLIPPER";
				angle=-90;
				startingAngle=-90;
			}
			rotateTime++;
		}
		else//rotateTime==3
		{
			yWithOffset=ypos;
			flipperType="LEFT_FLIPPER";
			angle=0;
			startingAngle=0;
			rotateTime=0;
		}

		oldAngle=angle;
		NotifyAll();
	}

	@Override
	public void trigger(boolean keyPressed,boolean keyReleased)
	{
		if(!this.keyPressed||this.keyReleased||keyPressed||!keyReleased)
		{
			this.keyReleased=keyReleased;
			this.keyPressed=keyPressed;
		}

		if(keyPressed&&!keyReleased)
		{
			flipUp=true;
		}
		else if(!keyPressed&&keyReleased)
		{
			flipUp=true;
		}

	}


	@Override
	public List<LineSegment> getEdges()
	{
		pivot=new Vect(this.xWithOffset+width/2,this.yWithOffset+width/2);
		double lineLength=length-width;
		double radianAngle=Math.toRadians(oldAngle);
		LineSegment leftSide=new LineSegment(xWithOffset,pivot.y(),xWithOffset,pivot.y()+lineLength);
		LineSegment rightSide=new LineSegment(xWithOffset+width,pivot.y(),xWithOffset+width,pivot.y()+lineLength);

		List<LineSegment> lines=new ArrayList<>();

		lines.add(Geometry.rotateAround(leftSide,pivot,new Angle(radianAngle)));
		lines.add(Geometry.rotateAround(rightSide,pivot,new Angle(radianAngle)));


		return lines;
	}

	@Override
	public List<Circle> getVertices()
	{
		pivot=new Vect(this.xWithOffset+width/2,this.yWithOffset+width/2);
		double lineLength=length-width;
		double radianAngle=Math.toRadians(oldAngle);
		double radius=width/2;

		Circle circleOne=new Circle(pivot,radius);
		Circle circleTwo=new Circle(pivot.plus(new Vect(0,lineLength)),radius);

		List<Circle> circles=new ArrayList<>();
		circles.add(Geometry.rotateAround(circleOne,pivot,new Angle(radianAngle)));
		circles.add(Geometry.rotateAround(circleTwo,pivot,new Angle(radianAngle)));

		return circles;
	}

	public double getOffset()
	{
		return offset;
	}

	public Vect getPivot()
	{
		return pivot;
	}

	public double getAngularVelocity()
	{
		return angularVelocity;
	}

	public Vect getCenter()
	{
		return pivot;
	}

	@Override
	public boolean isTriggered()
	{
		return triggered;
	}

	@Override
	public void activateAction()
	{
		flip();
	}

	public double getxWithOffset()
	{
		return xWithOffset;
	}

	public double getyWithOffset()
	{
		return yWithOffset;
	}

}
