package OOAD_Lab03_gizmo.Model;

import OOAD_Lab03_gizmo.Model.Widget.*;
import OOAD_Lab03_gizmo.Utilities.IO_Interface;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static OOAD_Lab03_gizmo.Config.Constants.*;

public class CollisionEngine
{

	private double frictionFactor;
	private double frictionFactor2;
	private Vect gravity;
	private Map<Ball,CollisionDetails> collisionDetailsMap;

	private Board board;


	CollisionEngine()
	{
		this.board=new Board();
		gravity=new Vect(0,GRAVITY);
		frictionFactor=FRICTION_FACTOR;
		frictionFactor2=FRICTION_FACTOR_2;
		collisionDetailsMap=new HashMap<>();
	}

	public void clear()
	{
		board.clear();
		collisionDetailsMap.clear();
	}

	public Wall getWall()
	{
		return board.getWall();
	}

	public List<Ball> getBallList()
	{
		return board.getBallList();
	}

	public List<GizmoWidget> getWidgetList()
	{
		return board.getWidgetList();
	}

	public void removeWidget(GizmoWidget widget)
	{
		board.removeWidget(widget);
	}

	public boolean addWidget(GizmoWidget widget)
	{
		return board.addWidget(widget);
	}

	void addBall(Ball ball)
	{
		collisionDetailsMap.put(ball,new CollisionDetails());
		board.addBall(ball);
	}

	void removeBall(Ball ball)
	{
		collisionDetailsMap.remove(ball);
		board.removeBall(ball);
	}

	private void applyGravity(Ball ball,double moveTime)
	{
		ball.setVelocity(ball.getVelocity().plus(gravity.times(moveTime)));
	}

	private void applyFriction(Ball ball,double moveTime)
	{
		double previousVelocityX=ball.getVelocityX();
		double previousVelocityY=ball.getVelocityY();
		double currentVelocityX=previousVelocityX*(1-(frictionFactor*moveTime)
				-(frictionFactor2*Math.abs(previousVelocityX)*moveTime));
		double currentVelocityY=previousVelocityY*(1-(frictionFactor*moveTime)
				-(frictionFactor2*Math.abs(previousVelocityY)*moveTime));

		ball.setVelocity(new Vect(currentVelocityX,currentVelocityY));
	}

	CollisionDetails getCollisionDetailByBall(Ball ball)
	{
		return collisionDetailsMap.get(ball);
	}

	private void updateCollisionDetailStatic(Displayable displayable,Ball ball)
	{

		List<LineSegment> edges=displayable.getEdges();
		List<Circle> vertices=displayable.getVertices();
		CollisionDetails details=getCollisionDetailByBall(ball);
		Circle ballEdge=ball.getEdge().get(0);
		double collisionTime=details.getTimeBeforeCollision();

		for(LineSegment lineSegment: edges)
		{
			double time=Geometry.timeUntilWallCollision(lineSegment,ballEdge,ball.getVelocity());
			if(time<collisionTime)
			{
				collisionTime=time;
				//注意reflexCoefficient
				details.setPotentialVelocity(Geometry.reflectWall(lineSegment,ball.getVelocity()));
				details.setCollidedBoardObject(displayable);
			}
		}
		for(Circle circle: vertices)
		{
			double time=Geometry.timeUntilCircleCollision(circle,ballEdge,ball.getVelocity());
			if(time<collisionTime)
			{
				collisionTime=time;
				details.setPotentialVelocity(Geometry
						.reflectCircle(circle.getCenter(),ball.getCenter(),ball.getVelocity()));
				details.setCollidedBoardObject(displayable);
			}
		}
		details.setTimeBeforeCollision(collisionTime);
	}

	private void getCollisionTimeWithGizmo(GizmoWidget gizmoWidget,Ball ball)
	{

		if(gizmoWidget.getType().equals("LEFT_FLIPPER")||
				gizmoWidget.getType().equals("RIGHT_FLIPPER"))
		{
			FlipperWidget flipperWidget=(FlipperWidget)gizmoWidget;

			List<LineSegment> edges=flipperWidget.getEdges();
			List<Circle> vertices=flipperWidget.getVertices();
			CollisionDetails details=getCollisionDetailByBall(ball);
			Circle ballEdge=ball.getEdge().get(0);
			double collisionTime=details.getTimeBeforeCollision();

			for(LineSegment line: edges)//碰撞到边的情况
			{
				double time=Geometry.timeUntilRotatingWallCollision(line,flipperWidget.getCenter(),flipperWidget
						.getAngularVelocity(),ballEdge,ball.getVelocity());
				if(time<collisionTime)
				{
					collisionTime=time;//从MAX
					details.setPotentialVelocity(Geometry
							.reflectRotatingWall(line,flipperWidget.getCenter(),flipperWidget
									.getAngularVelocity(),ballEdge,ball.getVelocity()));
					details.setPotentialCollision(flipperWidget);
				}
			}
			for(Circle circle: vertices)//碰撞到顶点的情况
			{
				double time=Geometry.timeUntilRotatingCircleCollision(circle,flipperWidget.getCenter(),flipperWidget
						.getAngularVelocity(),ballEdge,ball.getVelocity());
				if(time<collisionTime)
				{
					collisionTime=time;
					details.setPotentialVelocity(Geometry
							.reflectRotatingCircle(circle,flipperWidget.getCenter(),flipperWidget
									.getAngularVelocity(),ballEdge,ball.getVelocity()));
					details.setPotentialCollision(flipperWidget);
				}
			}
		}
		else
		{
			updateCollisionDetailStatic(gizmoWidget,ball);
		}
	}

	private void getCollisionTimeWithWall(Wall wall,Ball ball)
	{
		updateCollisionDetailStatic(wall,ball);
	}

	private void getCollisionTimeWithBall(Ball another,Ball current)
	{
		CollisionDetails anotherDetail=getCollisionDetailByBall(another);
		CollisionDetails currentDetail=getCollisionDetailByBall(current);
		Circle anotherBallEdge=another.getEdge().get(0);
		Circle currentBallEdge=current.getEdge().get(0);
		double anotherCollisionTime=anotherDetail.getTimeBeforeCollision();
		double currentCollisionTime=currentDetail.getTimeBeforeCollision();

		double time=Geometry.timeUntilBallBallCollision(currentBallEdge,
				current.getVelocity(),anotherBallEdge,another.getVelocity());
		Geometry.VectPair velocityPair=Geometry.reflectBalls(current.getCenter(),
				BALL_MASS,current.getVelocity(),another.getCenter(),
				BALL_MASS,another.getVelocity());


		if(time<currentCollisionTime)
		{
			currentCollisionTime=time;
			currentDetail.setPotentialVelocity(velocityPair.v1);
			currentDetail.setPotentialCollision(another);
		}
		currentDetail.setTimeBeforeCollision(currentCollisionTime);
		if(time<anotherCollisionTime)
		{
			anotherCollisionTime=time;
			anotherDetail.setPotentialVelocity(velocityPair.v2);
			anotherDetail.setPotentialCollision(current);
		}
		anotherDetail.setTimeBeforeCollision(anotherCollisionTime);
	}

	void getAllCollisionTime()
	{
		Map<Ball,List<Ball>> checked=new HashMap<>();
		for(Ball ball: board.getBallList())
		{
			getCollisionDetailByBall(ball).setTimeBeforeCollision(Double.MAX_VALUE);
		}
		for(Ball ball: board.getBallList())
		{
			for(GizmoWidget widget: board.getWidgetList())
			{
				getCollisionTimeWithGizmo(widget,ball);
			}
			getCollisionTimeWithWall(board.getWall(),ball);

			checked.put(ball,new ArrayList<>());
			checked.get(ball).add(ball);

			for(Ball others: board.getBallList())
			{
				checked.putIfAbsent(others,new ArrayList<>());
				checked.get(others).add(others);
				if(!checked.get(ball).contains(others)&&
						!checked.get(others).contains(ball))
				{
					getCollisionTimeWithBall(others,ball);
				}
				checked.get(ball).add(others);
				checked.get(others).add(ball);
			}
		}
	}

	void collide(double moveTime)
	{
		IO_Interface.DebugWriteLine("CollisionEngine collide moveTime "+moveTime);
		getAllCollisionTime();
		List<Ball> ballsInsideAbsorber=new ArrayList<>();
		for(Ball ball: getBallList())
		{
			CollisionDetails details=getCollisionDetailByBall(ball);

			IO_Interface.DebugWriteLine("CollisionEngine collide object: "+details.getPotentialCollision());

			details.setCollidedBoardObject(null);

			if(details.getTimeBeforeCollision()>moveTime)
			{
				IO_Interface.DebugWriteLine("CollisionEngine collide exec "+moveTime);
				ball.move(moveTime);
				applyGravity(ball,moveTime);
				applyFriction(ball,moveTime);
			}
			else
			{
				if(details.getPotentialCollision()!=null)
				{
					if(details.getPotentialCollision().getType().equals("AbsorberWidget"))
					{
						ballsInsideAbsorber.add(ball);
					}
					details.setCollidedBoardObject(details.getPotentialCollision());
				}
				IO_Interface.DebugWriteLine("CollisionEngine collide exec (shorten) "+details.getTimeBeforeCollision());
				IO_Interface.DebugWriteLine("CollisionEngine collide change V: "+details.getPotentialVelocity());
				ball.move(details.getTimeBeforeCollision());
				ball.setVelocity(details.getPotentialVelocity());
				applyGravity(ball,details.getTimeBeforeCollision());
				applyFriction(ball,details.getTimeBeforeCollision());
			}
		}
		for(Ball ball: ballsInsideAbsorber)
		{
			getBallList().remove(ball);
		}
	}

	void setFrictionFactor(double frictionFactor)
	{
		this.frictionFactor=frictionFactor;
	}

	void setFrictionFactor2(double frictionFactor2)
	{
		this.frictionFactor2=frictionFactor2;
	}

	void setGravity(Vect gravity)
	{
		this.gravity=gravity;
	}

	public Board getBoard()
	{
		return board;
	}
}