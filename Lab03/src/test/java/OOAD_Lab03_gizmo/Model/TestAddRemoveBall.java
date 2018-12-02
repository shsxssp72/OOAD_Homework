package OOAD_Lab03_gizmo.Model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAddRemoveBall {
    private GizmoModel gizmoballModel;
    @Before
    public void setUp() {
        gizmoballModel = new GizmoModel();
        gizmoballModel.addBall(5, 5, 10, 10);
    }

    @Test
    public void actuallyHasBall() {
//        assertNotNull(gizmoballModel.getBall("B1"));
        gizmoballModel.getBallByPosition(5,5);
    }

    @Test
    public void testRemoveBall() {
        assertTrue(gizmoballModel.removeBall(5,5 ));
    }

    @Test
    public void testRemoveBallNotNull() {
        gizmoballModel = new GizmoModel();
        gizmoballModel.addBall(5, 5, 10, 10);
        assertTrue(gizmoballModel.removeBall(5,5));
    }

    @Test
    public void testRemoveWrongBall(){
        assertFalse(gizmoballModel.removeBall(0,0));

    }

    @Test
    public void testAddBallOccupiedLocation() {
        gizmoballModel.addWidget(7,7,"CircleWidget");
        assertFalse(gizmoballModel.addBall(7,7,5,5));
    }

    @Test
    public void testAddSecondBall() {
        assertTrue(gizmoballModel.addBall(8,9, 10,10));
        assertEquals(gizmoballModel.getBallList().size(), 2);
    }


    @Test
    public void testMoveBall1() {
        gizmoballModel.addBall(1, 1, 0, 0);
        gizmoballModel.addWidget(5, 5, "TriangleWidget");

        assertTrue(gizmoballModel.moveBall(1,1, 6, 6));
    }

    @Test
    public void testMoveBall2() {
        gizmoballModel.addBall(1, 1, 0, 0);
        gizmoballModel.addWidget(5, 5, "TriangleWidget");

        assertTrue(gizmoballModel.moveBall(1,1, 5.9, 5.9));
    }

    @Test
    public void testMoveBallWrong1() {
        gizmoballModel.addBall(1, 1, 0, 0);
        gizmoballModel.addWidget(5, 5, "TriangleWidget");
        assertFalse(gizmoballModel.moveBall(1,1, 5.5, 5.5));
    }
    @Test
    public void testMoveBallWrong2() {
        gizmoballModel.addBall(1, 1, 0, 0);
        gizmoballModel.addWidget(5, 5, "TriangleWidget");
        assertFalse(gizmoballModel.moveBall(1,1, 5.6, 5.6));
    }
    @Test
    public void testMoveBallWrong3() {
        gizmoballModel.addBall(1, 1, 0, 0);
        gizmoballModel.addWidget(5, 5, "TriangleWidget");
        assertFalse(gizmoballModel.moveBall(1,1, 5.4, 5.4));
    }
    @Test
    public void testMoveBallCircle() {
        gizmoballModel.addBall(1, 1, 0, 0);
        gizmoballModel.addWidget(5, 5, "CircleWidget");
        gizmoballModel.addWidget(4, 4, "TriangleWidget");
        assertFalse(gizmoballModel.moveBall(1,1, 5.5, 5.5));
    }


    @Test
    public void testRemoveNoBallLocation() {
        assertFalse(gizmoballModel.removeBall(0, 0));
    }



}
