package OOAD_Lab03_gizmo.controller.mischandlers;

import javafx.event.Event;
import javafx.event.EventHandler;

public class KeepSafeHandler implements EventHandler<Event>
{
	@Override
	public void handle(Event event)
	{
		event.consume();
	}
}
