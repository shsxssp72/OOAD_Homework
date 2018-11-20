package OOAD_Lab03_gizmo.Model;

import OOAD_Lab03_gizmo.physics.Vect;

public class CollisionDetails
{
	private Displayable potentialCollision;
	private Displayable collidedBoardObject;
	private double timeBeforeCollision;
	private Vect potentialVelocity;

	public CollisionDetails()
	{
	}

	public Displayable getPotentialCollision()
	{
		return potentialCollision;
	}

	public void setPotentialCollision(Displayable potentialCollision)
	{
		this.potentialCollision=potentialCollision;
	}

	public Displayable getCollidedBoardObject()
	{
		return collidedBoardObject;
	}

	public void setCollidedBoardObject(Displayable collidedBoardObject)
	{
		this.collidedBoardObject=collidedBoardObject;
	}

	public double getTimeBeforeCollision()
	{
		return timeBeforeCollision;
	}

	public void setTimeBeforeCollision(double timeBeforeCollision)
	{
		this.timeBeforeCollision=timeBeforeCollision;
	}

	public Vect getPotentialVelocity()
	{
		return potentialVelocity;
	}

	public void setPotentialVelocity(Vect potentialVelocity)
	{
		this.potentialVelocity=potentialVelocity;
	}
}
