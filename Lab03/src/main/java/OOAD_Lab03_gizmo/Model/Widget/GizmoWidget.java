package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Model.Displayable;
import OOAD_Lab03_gizmo.Utilities.Observable;

import java.awt.*;
import java.util.List;

public interface GizmoWidget extends Displayable, Observable
{

	void rotate();

	List<String> getConnection();

	BoundingBox getBoundingBox();

	int getRotateTime();

	void trigger(boolean keyPressed,boolean keyReleased);

	default boolean getKeyPressed()
	{
		return false;
	}

	boolean isTriggered();

	void activateAction();

}
