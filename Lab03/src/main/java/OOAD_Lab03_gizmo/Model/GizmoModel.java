package OOAD_Lab03_gizmo.Model;

import OOAD_Lab03_gizmo.Model.Widget.*;
import OOAD_Lab03_gizmo.Utilities.Logger;
import OOAD_Lab03_gizmo.Utilities.TriggerConnector;
import physics.Geometry;
import physics.Vect;

import java.util.List;

import static OOAD_Lab03_gizmo.config.Constants.*;
import static OOAD_Lab03_gizmo.Utilities.IO_Interface.DebugWriteLine;

public class GizmoModel
{
	private CollisionEngine collisionEngine;
	private static int widgetCount=0;
	private static int ballCount=0;

	private String errorMessage="";

	public GizmoModel()
	{
		collisionEngine=new CollisionEngine();
	}

	public void clearBoard()
	{
		collisionEngine.clear();
		widgetCount=0;
		ballCount=0;
		Logger.LogWriteLine("Board Cleared.");
	}

	public void setGravity(double gravity)
	{
		Logger.LogWriteLine("Setting gravity to "+gravity);
		collisionEngine.setGravity(new Vect(0,gravity));
	}

	public void setFrictionFactors(double inFrictionFactor,double inFrictionFactor2)
	{
		Logger.LogWriteLine("Setting frictionFactors to ("+inFrictionFactor+","+inFrictionFactor2+")");
		collisionEngine.setFrictionFactor(inFrictionFactor);
		collisionEngine.setFrictionFactor2(inFrictionFactor2);
	}

	private void activateWidgetActions()
	{
		for(GizmoWidget widget: getWidgetList())
		{
			widget.activateAction();
		}
	}

	public void runBalls()
	{
		collisionEngine.collide(SECOND_PER_FRAME);

//		listen for triggers
		sendTriggers();

		activateWidgetActions();
	}

	//TODO 对于所有Displayable添加观察者
	public boolean addWidget(double xpos,double ypos,String type)
	{
		GizmoWidget widget=null;
		if(type.equals("Circle"))
		{
			widget=new CircleWidget("C"+widgetCount,xpos,ypos);
		}
		else if(type.equals("Square"))
		{
			widget=new SquareWidget("S"+widgetCount,xpos,ypos);
		}
		else if(type.equals("Triangle"))
		{
			widget=new TriangleWidget("T"+widgetCount,xpos,ypos);
		}
		else if(type.equals("RIGHT_FLIPPER"))
		{
			widget=new FlipperWidget(xpos,ypos,0,"F"+widgetCount,"RIGHT_FLIPPER");
		}
		else if(type.equals("LEFT_FLIPPER"))
		{
			widget=new FlipperWidget(xpos,ypos,0,"F"+widgetCount,"LEFT_FLIPPER");
		}
		else if(type.equals("Absorber"))
		{
			widget=new AbsorberWidget("A"+widgetCount,xpos,ypos);
		}
		else //不匹配处理
		{
			//Inner Error
			DebugWriteLine("Inner Error from GizmoModel::addWidget, no matching type");
			return false;
		}

		if(isOutside(widget))
		{
			//通过返回值判定
			setMessage("Gizmo cannot be placed outside of the board!");
			return false;
		}
		if(isIntersecting(widget))
		{
			//通过返回值判定
			setMessage("Gizmo cannot be placed here!");
			return false;
		}


		widgetCount++;
		collisionEngine.addWidget(widget);
		return true;
	}

	public boolean addBall(double xpos,double ypos,double velocityX,double velocityY)
	{
		Ball ball=new Ball("B"+ballCount,xpos,ypos,velocityX,velocityY);
		collisionEngine.addBall(ball);
		//TODO LOG
		if(isBallIntersecting(ball))
		{
			setMessage("Ball cannot be intersecting with any gizmos!");
			collisionEngine.removeBall(ball);
			return false;
		}
		Logger.LogWriteLine("New Ball "+ball.getName()+" "+xpos+" "+ypos+" "+velocityX+" "+velocityY);
		ballCount++;
		return true;
	}

	public boolean removeWidget(double xpos,double ypos)
	{
		GizmoWidget widget=getWidgetByPosition(xpos,ypos);
		if(widget==null)
			return false;
		collisionEngine.removeWidget(widget);
		Logger.LogWriteLine("Delete "+widget.getName());
		return true;
	}

	public boolean removeBall(double xpos,double ypos)
	{
		Ball ball=getBallByPosition(xpos,ypos);
		if(ball==null)
			return false;
		collisionEngine.removeBall(ball);
		Logger.LogWriteLine("Delete "+ball.getName());
		return true;
	}


	public boolean moveWidget(double xpos,double ypos,double xposNew,double yposNew)
	{
		GizmoWidget widget=getWidgetByPosition(xpos,ypos);
		if(widget==null)
			return false;
		widget.setXpos(xposNew);
		widget.setYpos(yposNew);
		if(isOutside(widget))
		{
			//TODO LOG
			setMessage("Cannot move a gizmo outside of the playable board!");
			widget.setXpos(xpos);
			widget.setYpos(ypos);
			return false;
		}
		if(isIntersecting(widget))
		{
			//TODO LOG
			setMessage("Cannot move gizmo within another gizmo!");
			widget.setXpos(xpos);
			widget.setYpos(ypos);
			return false;
		}
		Logger.LogWriteLine("Move "+widget.getName()+" "+xposNew+" "+yposNew);
		return true;
	}

	public boolean moveBall(double xpos,double ypos,double xposNew,double yposNew)
	{
		Ball ball=getBallByPosition(xpos,ypos);
		if(ball==null)
			return false;
		ball.setXpos(xposNew);
		ball.setYpos(yposNew);
		if(isBallIntersecting(ball))
		{
			//TODO LOG
			setMessage("Cannot move ball within another ball!");
			ball.setXpos(xpos);
			ball.setYpos(ypos);
			return false;
		}
		Logger.LogWriteLine("Move "+ball.getName()+" "+xposNew+" "+yposNew);
		return true;
	}

	public boolean rotateWidget(double xpos,double ypos)
	{
		GizmoWidget widget=getWidgetByPosition(xpos,ypos);
		if(widget==null)
		{
			return false;
		}

		Logger.LogWriteLine("Rotate "+widget.getName());
		widget.rotate();
		return true;
	}


	public GizmoWidget getWidgetByPosition(double xpos,double ypos)
	{
		for(GizmoWidget widget: collisionEngine.getWidgetList())
		{
			if(widget.getXpos()==xpos&&widget.getYpos()==ypos)
				return widget;
		}
		return null;
	}

	public Ball getBallByPosition(double xpos,double ypos)
	{
		for(Ball ball: collisionEngine.getBallList())
		{
			if(ball.getXpos()==xpos&&ball.getYpos()==ypos)
				return ball;
		}
		return null;
	}

	public GizmoWidget getWidgetByName(String name)
	{
		for(GizmoWidget widget: getWidgetList())
		{
			if(widget.getName().equals(name))
			{
				return widget;
			}
		}
		return null;
	}

	public Ball getBallByName(String name)
	{
		for(Ball ball: getBallList())
		{
			if(ball.getName().equals(name))
			{
				return ball;
			}
		}
		return null;
	}


	private boolean ballVelocityIntersectionCheck(double x,double y,Ball ball)
	{
		ball.setVelocity(new Vect(x,y));
		collisionEngine.getAllCollisionTime();
		if(collisionEngine.getCollisionDetailByBall(ball).getTimeBeforeCollision()<SECOND_PER_FRAME)
		{
			return true;
		}

		GizmoWidget widget=getWidgetByPosition(Math.floor(ball.getXpos()),Math.floor(ball.getYpos()));

		if(widget!=null)
		{
			if(widget.getType().equals("SquareWidget"))
			{
				return true;
			}
			if(widget.getType().equals("CircleWidget"))
			{
				double distance=Math
						.sqrt(Geometry.distanceSquared(ball.getCenter(),((CircleWidget)widget).getCenter()));

				if(distance<((ball.getWidth()/2)+(ONE_L/2)))
				{
					return true;
				}
			}
		}
		return false;
	}

	private boolean isBallIntersecting(Ball ball)
	{
		Vect orig=new Vect(ball.getVelocity().x(),ball.getVelocity().y());
		//LEFT
		if(ballVelocityIntersectionCheck(0.01,0.0,ball))
		{
			return true;
		}
		//RIGHT
		if(ballVelocityIntersectionCheck(-0.01,0.0,ball))
		{
			return true;
		}
		//DOWN
		if(ballVelocityIntersectionCheck(0.0,-0.01,ball))
		{
			return true;
		}
		//DOWN
		if(ballVelocityIntersectionCheck(0.0,0.01,ball))
		{
			return true;
		}

		ball.setVelocity(orig);

		return false;
	}

	private boolean isIntersecting(GizmoWidget widget)
	{
		for(GizmoWidget other: collisionEngine.getWidgetList())
		{
			if(widget.getBoundingBox().isIntersecting(other.getBoundingBox()))
			{
				if(widget!=other)
					return true;
			}
		}
		collisionEngine.addWidget(widget);
		for(Ball ball: collisionEngine.getBallList())
		{
			if(isBallIntersecting(ball))
			{
				collisionEngine.removeWidget(widget);
				return true;
			}
		}
		collisionEngine.removeWidget(widget);
		return false;
	}

	private boolean isOutside(GizmoWidget widget)
	{
		if(widget==null)
			return false;
		return widget.getBoundingBox().isOutside();
	}

	public Board getBoard()
	{
		return collisionEngine.getBoard();
	}

	public List<Ball> getBallList()
	{
		return collisionEngine.getBallList();
	}

	public List<GizmoWidget> getWidgetList()
	{
		return collisionEngine.getWidgetList();
	}

	private void sendTriggers()
	{
		for(Ball ball: getBallList())
		{
			Displayable collidedBoardObject=collisionEngine.getCollisionDetailByBall(ball).getCollidedBoardObject();
			if(collidedBoardObject!=null&&!collidedBoardObject.getType().equals("Wall")&&!collidedBoardObject.getType()
					.equals("Ball"))
			{
				for(GizmoWidget widget: TriggerConnector.getTriggeredGizmos((GizmoWidget)collidedBoardObject))
				{    //TODO: change to include walls
					widget.trigger(false,true);
				}
			}
		}
	}

	public void setMessage(String s)
	{
		errorMessage=s;
	}

	public String getMessage()
	{
		return errorMessage;
	}

}
