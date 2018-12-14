package OOAD_Lab03_gizmo.config;

public class Constants {
	public static final double ONE_L = 1.0;
	public static final double ONE_L_IN_PIXELS = ONE_L * 20;
	public static final double GRAVITY = 25 * ONE_L;
	public static final double FRICTION_FACTOR = 0.025;
	public static final double FRICTION_FACTOR_2 = 0.025 * ONE_L;
	public static final int BOARD_HEIGHT = 20;
	public static final int BOARD_WIDTH = 20;
	public static final double BALL_MASS = 1;
	public static final double FRAME_PER_SECOND = 60;
	public static final double SECOND_PER_FRAME = 1 / FRAME_PER_SECOND;
	public static final double MILLIS_PER_FRAME = 1000 / FRAME_PER_SECOND;
	public static final int WIDGET_LENGTH = 1;
	public static final int FLIPPER_LENGTH = 2;
	public static final double FLIPPER_ANGULAR_VELOCITY = 1080 * ONE_L;
	public static final double DELTA_ANGLE = FLIPPER_ANGULAR_VELOCITY / FRAME_PER_SECOND;

}
