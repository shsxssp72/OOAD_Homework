package OOAD_Lab03_gizmo.Model.Widget;

import OOAD_Lab03_gizmo.Utilities.IO_Interface;
import OOAD_Lab03_gizmo.Utilities.Observer;
import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static OOAD_Lab03_gizmo.config.Constants.WIDGET_LENGTH;


public class AbsorberWidget implements GizmoWidget {
	private ArrayList<LineSegment> edges = new ArrayList<>();
	private ArrayList<Circle> vertices = new ArrayList<>();
	private ArrayList<String> connections = new ArrayList<>();
	private String name = "";
	private double xpos = 0;
	private double ypos = 0;
	private double width = WIDGET_LENGTH;
	private Color color = Color.magenta;
	private final List<Observer> observerList = new ArrayList<>();
	private final boolean triggered = false;


	@Override
	public String getType() {
		return "Absorber";
	}

	public AbsorberWidget(String name, double xpos, double ypos) {
		this.name = name;
		this.xpos = xpos;
		this.ypos = ypos;
		InitCircles();
		InitLines();
	}

	private void InitCircles() {
		SquareWidget.InitSquareVertices(vertices, xpos, ypos, width);
	}

	private void InitLines() {
		SquareWidget.InitSquareEdges(edges, xpos, ypos, width);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getXpos() {
		return xpos;
	}

	@Override
	public double getYpos() {
		return ypos;
	}

	@Override
	public List<LineSegment> getEdges() {
		return edges;
	}

	@Override
	public List<Circle> getVertices() {
		return vertices;
	}

	@Override
	public void rotate() {
		IO_Interface.ConsoleWriteLine("absorbers do not need to rotate.");
	}

	@Override
	public List<String> getConnection() {
		return connections;
	}

	@Override
	public void setXpos(double x) {
		xpos = x;
		NotifyAll();
	}

	@Override
	public void setYpos(double y) {
		ypos = y;
		NotifyAll();
	}

	@Override
	public void setName(String inName) {
		name = inName;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(xpos, ypos, xpos + width, ypos + width);
	}

	@Override
	public int getRotateTime() {
		return 0;
	}

	@Override
	public List<Observer> getObserverList() {
		return observerList;
	}

	@Override
	public void trigger(boolean keyPressed, boolean keyReleased) {
//  default ignore...
	}

	@Override
	public void activateAction() {
		NotifyAll();
	}

	@Override
	public boolean isTriggered() {
		return triggered;
	}
}

