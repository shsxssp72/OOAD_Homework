package OOAD_Lab03_gizmo.Model.Widget;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	private Wall wall;
	private List<Ball> ballList;
	private List<GizmoWidget> widgetList;

	public Board()
	{
		wall=new Wall();
		ballList=new ArrayList<>();
		widgetList=new ArrayList<>();
	}

	public Wall getWall()
	{
		return wall;
	}

	public List<Ball> getBallList()
	{
		return ballList;
	}

	public List<GizmoWidget> getWidgetList()
	{
		return widgetList;
	}
	public boolean addWidget(GizmoWidget widget)
	{
		return widgetList.add(widget);
	}
	public boolean addBall(Ball ball)
	{
		return ballList.add(ball);
	}
	public void clear()
	{
		widgetList.clear();
		ballList.clear();
	}
	public void removeWidget(GizmoWidget widget)
	{
		widgetList.remove(widget);
	}
	public void removeBall(Ball ball)
	{
		ballList.remove(ball);
	}
}
