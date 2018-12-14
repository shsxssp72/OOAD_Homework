package OOAD_Lab03_gizmo.controller.buildhandlers;

import OOAD_Lab03_gizmo.Model.GizmoModel;
import OOAD_Lab03_gizmo.Model.Widget.Ball;
import OOAD_Lab03_gizmo.controller.BoardController;
import OOAD_Lab03_gizmo.controller.BuildViewController;
import OOAD_Lab03_gizmo.view.visual.BallVisual;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import physics.Vect;

import static OOAD_Lab03_gizmo.config.Constants.ONE_L_IN_PIXELS;

public class AddBallHandler extends AddHandler {
	private double velocityX;
	private double velocityY;


	public AddBallHandler(GizmoModel model, BoardController boardController, BuildViewController buildViewController,
	                      Label infoLabel) {
		super(model, boardController, buildViewController, "Ball", infoLabel);
		velocityX = 0;
		velocityY = 0;
	}

	@Override
	public void handle(Event event) {

		if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			MouseEvent mouseEvent = (MouseEvent) event;

			double x = mouseEvent.getX() / ONE_L_IN_PIXELS;
			double y = mouseEvent.getY() / ONE_L_IN_PIXELS;

			Node clicked = ((Node) event.getTarget());

			if (model.addBall(x, y, 0, 0)) {
				Ball ball = model.getBallByPosition(x, y);
				Vect vect = new Vect(0, 0);

				ball.setVelocity(vect);

				boardController.addToBoardView(new BallVisual(ball));
			}


			if (!model.getMessage().equals("")) {
				buildViewController.setLog(model.getMessage());
				model.setMessage("");
			} else {
				buildViewController.setLog("Ball added at Position" + "(" + x + ", " + y + ").");
			}
		}
	}

	@Override
	public void handle(String name) {
		Ball ball = model.getBallByName(name);
		boardController.addToBoardView(new BallVisual(ball));
		if (!model.getMessage().equals("")) {
			buildViewController.setLog(model.getMessage());
			model.setMessage("");
		} else {
			buildViewController.setLog(" ");
		}
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
}
