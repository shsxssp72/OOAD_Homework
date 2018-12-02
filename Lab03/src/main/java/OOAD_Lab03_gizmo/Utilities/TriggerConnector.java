package OOAD_Lab03_gizmo.Utilities;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TriggerConnector
{
	private static final Map<GizmoWidget,Set<GizmoWidget>> widgetTriggers=new HashMap<>();
	private static final Map<KeyboardEvent,Set<GizmoWidget>> keyTriggers=new HashMap<>();

	public static void addTrigger(GizmoWidget trigger,GizmoWidget triggered)
	{
		if(trigger!=null&&triggered!=null)
		{
			if(!widgetTriggers.containsKey(trigger))
			{
				widgetTriggers.put(trigger,new HashSet<>());
			}
			widgetTriggers.get(trigger).add(triggered);
			Logger.LogWriteLine("Connect "+trigger.getName()+" "+triggered.getName());
		}
	}

	public static void addTrigger(KeyboardEvent trigger,GizmoWidget triggered)
	{
		if(trigger!=null&&triggered!=null)
		{
			if(!keyTriggers.containsKey(trigger))
			{
				keyTriggers.put(trigger,new HashSet<>());
			}
			keyTriggers.get(trigger).add(triggered);

			String type=null;
			if(trigger.getType()==KeyEvent.KEY_PRESSED)
			{
				type="up";
			}
			else if(trigger.getType()==KeyEvent.KEY_RELEASED)
			{
				type="down";
			}
			Logger.LogWriteLine("KeyConnect key "+"\""+trigger.getCode().getName()+"\" "+type+" "+triggered.getName());
		}
	}

	public static void removeTriggers(GizmoWidget widget)
	{
		for(Set<GizmoWidget> triggeredSet: widgetTriggers.values())
		{
			triggeredSet.remove(widget);
		}

		for(Set<GizmoWidget> triggeredSet: keyTriggers.values())
		{
			triggeredSet.remove(widget);
		}

		widgetTriggers.keySet().remove(widget);
	}

	public static void clear()
	{
		widgetTriggers.clear();
		keyTriggers.clear();
	}

	public static Set<GizmoWidget> getTriggeredGizmos(GizmoWidget trigger)
	{
		if(widgetTriggers.containsKey(trigger))
		{
			return widgetTriggers.get(trigger);
		}
		else
		{
			return new HashSet<>();
		}
	}

	public static Set<GizmoWidget> getTriggeredGizmo(KeyboardEvent trigger)
	{
		if(keyTriggers.containsKey(trigger))
		{
			return keyTriggers.get(trigger);
		}
		else
		{
			return new HashSet<>();
		}
	}

}