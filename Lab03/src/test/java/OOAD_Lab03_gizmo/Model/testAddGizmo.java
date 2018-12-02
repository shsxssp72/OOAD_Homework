package OOAD_Lab03_gizmo.Model;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class testAddGizmo
{

	private GizmoModel gizmoballModel;


	@Before
	public void setUp()
	{
		gizmoballModel=new GizmoModel();
	}

	@Test
	public void testAddCGizmo()
	{
		assertTrue(gizmoballModel.addWidget(5,5,"CircleWidget"));
	}

	@Test
	public void testAddSGizmo()
	{
		assertTrue(gizmoballModel.addWidget(10,10,"SquareWidget"));
	}

	@Test
	public void testAddTGizmo()
	{
		assertTrue(gizmoballModel.addWidget(15,15,"TriangleWidget"));
	}

	@Test
	public void testAddWrongGizmo()
	{
		assertFalse(gizmoballModel.addWidget(15,15,""));
	}


	@Test
	public void testAddGizmoz()
	{
		for(int x=0;x<20;x++)
		{
			for(int y=0;y<20;y++)
			{
				assertTrue(gizmoballModel.addWidget(x,y,"SquareWidget"));
			}
		}
		assertEquals(gizmoballModel.getBoard().getWidgetList().size(),400);
	}

	@Test
	public void testTwoGizmoSameLocation()
	{
		gizmoballModel.addWidget(1,1,"SquareWidget");
		gizmoballModel.addWidget(1,1,"CircleWidget");
		assertFalse("Should not have added gizmo in same location.",gizmoballModel
				.addWidget(1,1,"CircleWidget"));
	}


	@Test
	public void testRemoveGizmo()
	{
		gizmoballModel.addWidget(1,1,"SquareWidget");
		assertTrue(gizmoballModel.removeWidget(1,1));

	}

	@Test
	public void testRemoveGizmoNotExists()
	{
		assertFalse(gizmoballModel.removeWidget(0,0));
	}


	@Test
	public void testGetCoordGizmo()
	{
		gizmoballModel.addWidget(1,1,"SquareWidget");
		assertEquals(gizmoballModel.getWidgetByPosition(1,1),gizmoballModel.getBoard().getWidgetList().get(0));
	}



//	@Test
//	public void testAddGizmoInAbsorber()
//	{
//		gizmoballModel.addAbsorber(0,16,19,19,"A1");
//		assertFalse(gizmoballModel.addWidget(4,17,"C417",BoardObjectType.CIRCLE));
//	}
//
//	@Test
//	public void testClearBoard()
//	{
//		gizmoballModel.addAbsorber(0,16,19,19,"A1");
//		gizmoballModel.addWidget(0,5,"",BoardObjectType.TRIANGLE);
//		assertEquals(gizmoballModel.getGizmos().size(),2);
//		gizmoballModel.clearBoard();
//		assertEquals(gizmoballModel.getGizmos().size(),0);
//	}
}
