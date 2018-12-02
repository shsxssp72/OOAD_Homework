package OOAD_Lab03_gizmo.Model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class testRotateGizmo
{

	private GizmoModel gizmoballModel;

	@Before
	public void setUp()
	{
		gizmoballModel=new GizmoModel();
	}

	@Test
	public void testRotate90()
	{
		gizmoballModel.addWidget(1,1,"TriangleWidget");
		gizmoballModel.rotateWidget(1,1);
		assertEquals((gizmoballModel.getWidgetByPosition(1,1).getRotateTime()),1,0);
	}

	@Test
	public void testRotate360()
	{
		gizmoballModel.addWidget(1,1,"TriangleWidget");
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		assertEquals((gizmoballModel.getWidgetByPosition(1,1).getRotateTime()),0,0);
	}

	@Test
	public void testRotateOver360()
	{
		gizmoballModel.addWidget(1,1,"TriangleWidget");
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		gizmoballModel.rotateWidget(1,1);
		assertEquals((gizmoballModel.getWidgetByPosition(1,1).getRotateTime()),3,0);
	}

//	@Test
//	public void testRotateAbsorber()
//	{
//		gizmoballModel.addAbsorber(1.0,20.0,3.0,17.0,"A1");
//		assertFalse(gizmoballModel.rotateGizmo("A1"));
//	}

	@Test
	public void testRotateNoGizmoLocation()
	{
		assertFalse(gizmoballModel.rotateWidget(0,0));
	}


}
