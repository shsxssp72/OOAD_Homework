package OOAD_Lab03_gizmo.Model;

import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.physics.Vect;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static OOAD_Lab03_gizmo.Config.Constants.FRICTION_FACTOR;
import static OOAD_Lab03_gizmo.Config.Constants.FRICTION_FACTOR_2;
import static OOAD_Lab03_gizmo.Config.Constants.GRAVITY;

public class CollisionEngine
{

	private double frictionFactor;
	private double frictionFactor2;
	private Vect gravity;
	private Map<Ball,CollisionDetails> collisionDetailsMap;

	//! only for early build
	private List<Ball> ballList;


	CollisionEngine()
	{
		gravity=new Vect(0,GRAVITY);
		frictionFactor=FRICTION_FACTOR;
		frictionFactor2=FRICTION_FACTOR_2;
		collisionDetailsMap=new TreeMap<>();
	}

	public void addBall(Ball ball)
	{
		collisionDetailsMap.put(ball,new CollisionDetails());
	}

	public void removeBall(Ball ball)
	{
		collisionDetailsMap.remove(ball);
	}



}

