package OOAD_Lab03_gizmo.Model.Widget;

import static OOAD_Lab03_gizmo.config.Constants.*;

public class BoundingBox
{
	private final double xTopLeft;
	private final double yTopLeft;
	private final double xBottomRight;
	private final double yBottomRight;

	public BoundingBox(double xTopLeft,double yTopLeft,double xBottomRight,double yBottomRight)
	{

		this.xTopLeft=xTopLeft;
		this.yTopLeft=yTopLeft;
		this.xBottomRight=xBottomRight;
		this.yBottomRight=yBottomRight;
	}

	public boolean isIntersecting(BoundingBox other)
	{
		return this.xTopLeft<other.xBottomRight
				&&other.xTopLeft<this.xBottomRight
				&&this.yTopLeft<other.yBottomRight
				&&other.yTopLeft<this.yBottomRight;
	}

	public boolean isOutside()
	{
		if(this.xTopLeft>BOARD_WIDTH-WIDGET_LENGTH||this.xTopLeft<0)
			return true;
		if(this.xBottomRight>BOARD_WIDTH||this.xBottomRight<WIDGET_LENGTH)
			return true;
		if(this.yTopLeft> BOARD_HEIGHT -WIDGET_LENGTH||this.yTopLeft<0)
			return true;
		if(this.yBottomRight> BOARD_HEIGHT ||this.yBottomRight<WIDGET_LENGTH)
			return true;

		return false;
	}
}
