package OOAD_Lab03_gizmo.Utilities;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.Model.Widget.Board;
import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SL_Handler
{
	public static Document SaveBoard(Board board)
	{
		Document document=DocumentHelper.creat·eDocument();
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


		Element WidgetConnections=root.addElement("WidgetConnections");
		for(GizmoWidget widget: board.getWidgetList())
		{

			List<GizmoWidget> reverseWidgetList=TriggerConnector.getTriggerGizmosReverse(widget);
			if(reverseWidgetList!=null)
			{
				for(GizmoWidget gizmoWidget: reverseWidgetList)
				{
					Element currentWidgetConnection=WidgetConnections.addElement("WidgetConnection");
					currentWidgetConnection.addAttribute("InitiatorX",gizmoWidget.getXpos()+"");
					currentWidgetConnection.addAttribute("InitiatorY",gizmoWidget.getYpos()+"");

					currentWidgetConnection.addAttribute("ReceiverX",widget.getXpos()+"");
					currentWidgetConnection.addAttribute("ReceiverY",widget.getYpos()+"");
				}
			}
		}

		Element KeyConnections=root.addElement("KeyConnections");
		for(GizmoWidget widget: board.getWidgetList())
		{
			List<KeyboardEvent> reverseKeyBoardList=TriggerConnector.getTriggerGizmoReverse(widget);

			if(reverseKeyBoardList!=null)
			{
				for(KeyboardEvent keyboardEvent: reverseKeyBoardList)
				{
					Element currentKeyConnection=KeyConnections.addElement("KeyConnection");
					currentKeyConnection.addAttribute("KeyCodeName",keyboardEvent.getCode().getName());

					currentKeyConnection.addAttribute("ReceiverX",widget.getXpos()+"");
					currentKeyConnection.addAttribute("ReceiverY",widget.getYpos()+"");
				}
			}
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

	public static void SaveXMLToFile(Document document,String filename)
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

	public static void SaveXMLToFile(Document document,File file)
	{
		try
		{
			OutputFormat format=OutputFormat.createPrettyPrint();
			XMLWriter output=new XMLWriter(new FileWriter(file),format);
			output.write(document);
			output.close();
		}
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static Document LoadXMLFromFile(File file) throws Exception
	{
		SAXReader reader=new SAXReader();
		return reader.read(file);
	}


	public static Document LoadXMLFromFile(String filename) throws Exception
	{
		SAXReader reader=new SAXReader();
		return reader.read(new File(filename));
	}

	public static void LoadBoard(Document document,GizmoModel model) throws IllegalArgumentException
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
			for(int i=0;i<Integer.parseInt(RotateTime);i++)
			{
				model.getWidgetByPosition(Double.parseDouble(Xpos),Double.parseDouble(Ypos)).rotate();
			}
		}

		TriggerConnector.clear();
		Element WidgetConnections=root.element("WidgetConnections");
		for(Element widgetConnectionItem: WidgetConnections.elements("WidgetConnection"))
		{
			String InitiatorX=widgetConnectionItem.attributeValue("InitiatorX");
			String InitiatorY=widgetConnectionItem.attributeValue("InitiatorY");

			String ReceiverX=widgetConnectionItem.attributeValue("ReceiverX");
			String ReceiverY=widgetConnectionItem.attributeValue("ReceiverY");

			GizmoWidget initiator=model
					.getWidgetByPosition(Double.parseDouble(InitiatorX),Double.parseDouble(InitiatorY));
			GizmoWidget receiver=model.getWidgetByPosition(Double.parseDouble(ReceiverX),Double.parseDouble(ReceiverY));
			TriggerConnector.addTrigger(initiator,receiver);
		}

		Element KeyConnections=root.element("KeyConnections");
		for(Element keyConnection: KeyConnections.elements("KeyConnection"))
		{
			String KeyCodeName=keyConnection.attributeValue("keyConnection");
			KeyCode keyCode=KeyCode.getKeyCode(KeyCodeName);
			EventType<KeyEvent> eventType=KeyEvent.KEY_PRESSED;
			KeyboardEvent keyboardEvent=new KeyboardEvent(keyCode,eventType);

			String ReceiverX=keyConnection.attributeValue("ReceiverX");
			String ReceiverY=keyConnection.attributeValue("ReceiverY");

			GizmoWidget receiver=model.getWidgetByPosition(Double.parseDouble(ReceiverX),Double.parseDouble(ReceiverY));
			TriggerConnector.addTrigger(keyboardEvent,receiver);

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
