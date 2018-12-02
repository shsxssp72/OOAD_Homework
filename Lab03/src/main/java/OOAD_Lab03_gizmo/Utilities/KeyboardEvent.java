package OOAD_Lab03_gizmo.Utilities;

import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardEvent
{
	private final KeyCode code;
	private final EventType<KeyEvent> type;

	public KeyboardEvent(KeyCode code,EventType<KeyEvent> type)
	{
		this.code=code;
		this.type=type;
	}

	public KeyCode getCode()
	{
		return code;
	}

	public EventType<KeyEvent> getType()
	{
		return type;
	}

	@Override
	public boolean equals(Object o)
	{

		if(o==this)
		{
			return true;
		}
		if(!(o instanceof KeyboardEvent))
		{
			return false;
		}

		KeyboardEvent keyboardEvent=(KeyboardEvent)o;
		return keyboardEvent.code.equals(code)&&keyboardEvent.type.equals(type);
	}

}
