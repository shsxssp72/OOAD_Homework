package OOAD_Lab03_gizmo.controller.playhandlers;

import OOAD_Lab03_gizmo.Model.Widget.GizmoWidget;
import OOAD_Lab03_gizmo.Utilities.KeyboardEvent;
import OOAD_Lab03_gizmo.Utilities.TriggerConnector;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class TriggerHandler implements EventHandler<KeyEvent> {

	public TriggerHandler() {

	}

	@Override
	public void handle(KeyEvent event) {
		for (GizmoWidget gizmo : TriggerConnector.getTriggeredGizmo(new KeyboardEvent(event.getCode(), event.getEventType()))) {
			if (event.getEventType() == KeyEvent.KEY_PRESSED) {
				gizmo.trigger(true, false);
			} else {
				if (gizmo.getKeyPressed()) {
					gizmo.trigger(true, true);
				} else {
					gizmo.trigger(false, true);
				}
			}
		}
	}
}