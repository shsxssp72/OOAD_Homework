package OOAD_Lab03_gizmo.Model;

import OOAD_Lab03_gizmo.Config.Constants;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.Model.Widget.TriangleWidget;
import OOAD_Lab03_gizmo.Utilities.SL_Handler;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ModelTest
{
	@Test
	public void simpleTest()
	{
		GizmoModel model=new GizmoModel();
		model.addBall(10,10,0,0);
		model.addBall(10,8,0,0);
		model.addWidget(10,6,"CircleWidget");

		SL_Handler handler=new SL_Handler();
		handler.SaveXMLToFile(handler.SaveBoard(model.getBoard()),"Test.xml");
		try
		{
			handler.LoadBoard(handler.LoadXMLFromFile("Test.xml"),model);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		for(int j=0;j<10;j++)
		{
			Ball ball=model.getBoard().getBallList().get(0);
			System.out.println("POS:["+ball.getXpos()+"/"+ball.getYpos()+"]");
			System.out.println("	VEL:["+ball.getVelocityX()+"/"+ball.getVelocityY()+"]");
			for(int i=0;i<Constants.FRAME_PER_SECOND;i++)
			{
				model.runBalls();
			}
		}
		assertTrue(true);
	}
}
