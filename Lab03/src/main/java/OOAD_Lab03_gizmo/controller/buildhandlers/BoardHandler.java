package OOAD_Lab03_gizmo.controller.buildhandlers;

import javafx.event.Event;
import javafx.event.EventHandler;

public interface BoardHandler extends EventHandler<Event>
{
	@Override
	void handle(Event event);
}
