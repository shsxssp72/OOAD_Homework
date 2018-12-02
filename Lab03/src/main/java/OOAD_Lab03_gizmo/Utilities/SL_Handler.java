package OOAD_Lab03_gizmo.Utilities;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.Model.Widget.Board;
import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SL_Handler
{
	public Document SaveBoard(Board board)
	{
		Document document=DocumentHelper.createDocument();
		Element root=document.addElement("GizmoBoard");
		Element Widgets=root.addElement("Widgets");

		for(GizmoWidget widget: board.getWidgetList())
		{
			Element currentWidget=Widgets.addElement("Widget");
			currentWidget.addAttribute("Type",widget.getType());
			currentWidget.addAttribute("Name",widget.getName());
			currentWidget.addAttribute("Xpos",widget.getXpos()+"");
			currentWidget.addAttribute("Ypos",widget.getYpos()+"");
			currentWidget.addAttribute("RotateTime",widget.getRotateTime()+"");
		}

		Element Balls=root.addElement("Balls");
		for(Ball ball: board.getBallList())
		{
			Element currentBall=Balls.addElement("Ball");
			currentBall.addAttribute("Name",ball.getName());
			currentBall.addAttribute("Xpos",ball.getXpos()+"");
			currentBall.addAttribute("Ypos",ball.getYpos()+"");
			currentBall.addAttribute("VelocityX",ball.getVelocityX()+"");
			currentBall.addAttribute("VelocityY",ball.getVelocityY()+"");

		}
		return document;
	}

	public void SaveXMLToFile(Document document,String filename)
	{
		try
		{
			OutputFormat format=OutputFormat.createPrettyPrint();
			XMLWriter output=new XMLWriter(new FileWriter(new File(filename)),format);
			output.write(document);
			output.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public Document LoadXMLFromFile(String filename) throws Exception
	{
		SAXReader reader=new SAXReader();
		return reader.read(new File(filename));
	}

	public void LoadBoard(Document document,GizmoModel model) throws IllegalArgumentException
	{
		if(model==null||document==null)
		{
			throw new IllegalArgumentException("document or model is null");
		}
		model.clearBoard();

		Element root=document.getRootElement();
		Element Widgets=root.element("Widgets");
		for(Element widgetItem: Widgets.elements("Widget"))
		{
			String Type=widgetItem.attributeValue("Type");
			String Name=widgetItem.attributeValue("Name");
			String Xpos=widgetItem.attributeValue("Xpos");
			String Ypos=widgetItem.attributeValue("Ypos");
			String RotateTime=widgetItem.attributeValue("RotateTime");

			model.addWidget(Double.parseDouble(Xpos),Double.parseDouble(Ypos),Type);
		}

		Element Balls=root.element("Balls");
		for(Element ballItem: Balls.elements("Ball"))
		{
			String Name=ballItem.attributeValue("Name");
			String Xpos=ballItem.attributeValue("Xpos");
			String Ypos=ballItem.attributeValue("Ypos");
			String VelocityX=ballItem.attributeValue("VelocityX");
			String VelocityY=ballItem.attributeValue("VelocityY");

			model.addBall(Double.parseDouble(Xpos),Double.parseDouble(Ypos),
					Double.parseDouble(VelocityX),Double.parseDouble(VelocityY));
		}

	}
}
