package com.domain.project.core.controls;

import com.domain.project.core.Const;
import com.domain.project.core.Environment;
import com.domain.project.core.enums.Zoom;

import playn.core.GroupLayer;
import playn.core.Mouse;

public class MouseControls implements Mouse.Listener {

	public boolean clickScroll = false;
	private float zoomSpeed = 1000.0f; // in milliseconds
	private Environment env;
	private float xOld = 0.0f;
	private float yOld = 0.0f;
	private float xOffset = 0.0f;
	private float yOffset = 0.0f;
	private float xCurrent = 0.0f;
	private float yCurrent = 0.0f;
	private float xNext = 0.0f;
	private float yNext = 0.0f;

	/** Constructor
	 * @param env
	 */
	public MouseControls(Environment env) {
		this.env = env;
	}

	@Override
	public void onMouseDown(Mouse.ButtonEvent event) {
		if(event.button() == Mouse.BUTTON_RIGHT) {
			clickScroll = true;
			xOld = event.x();
			yOld = event.y();
		}
	}

	@Override
	public void onMouseUp(Mouse.ButtonEvent event) {
		if(event.button() == Mouse.BUTTON_RIGHT) {
			clickScroll = false;
		}
	}

	@Override
	public void onMouseMove(Mouse.MotionEvent event) {
		xCurrent = event.x(); // with respect to window coordinates
		yCurrent = event.y();

		if (clickScroll && env.getZoomLevel() != Zoom.OUT) {
			xOffset = xCurrent - xOld;
			yOffset = yCurrent - yOld;

			// set x bounds
			if (env.getX() >= Const.WORLD_ORIGIN_X && env.getX() <= Const.WORLD_WIDTH) {
				env.setX(env.getX() - xOffset);
			}

			if (env.getX() < Const.WORLD_ORIGIN_X) {
				env.setX(Const.WORLD_ORIGIN_X);
			}

			if (env.getX() > Const.WORLD_WIDTH - Const.WINDOW_WIDTH) {
				env.setX(Const.WORLD_WIDTH - Const.WINDOW_WIDTH);
			}

			//set y bounds
			if (env.getY() >= Const.WORLD_ORIGIN_Y && env.getY() <= Const.WORLD_HEIGHT) {
				env.setY(env.getY() - yOffset);
			}

			if (env.getY() < Const.WORLD_ORIGIN_Y) {
				env.setY(Const.WORLD_ORIGIN_Y);
			}

			if (env.getY() > Const.WORLD_HEIGHT - Const.WINDOW_HEIGHT) {
				env.setY(Const.WORLD_HEIGHT - Const.WINDOW_HEIGHT);
			}

			xOld = xCurrent;
			yOld = yCurrent;
		}
	}

	@Override
	public void onMouseWheelScroll(Mouse.WheelEvent event) {
		// Scroll in
		if (event.velocity() > 0) {
			if (env.getZoomLevel() == Zoom.DEFAULT) {
				env.setZoomLevel(Zoom.IN_1);
				zoomIn(env.getMainLayer(), Zoom.IN_1.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_1) {
				env.setZoomLevel(Zoom.IN_2);
				zoomIn(env.getMainLayer(), Zoom.IN_2.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_2) {
				env.setZoomLevel(Zoom.IN_3);
				zoomIn(env.getMainLayer(), Zoom.IN_3.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_3) {
				env.setZoomLevel(Zoom.IN_4);
				zoomIn(env.getMainLayer(), Zoom.IN_4.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_4) {
				env.setZoomLevel(Zoom.IN_5);
				zoomIn(env.getMainLayer(), Zoom.IN_5.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_5) {
				env.setZoomLevel(Zoom.IN_6);
				zoomIn(env.getMainLayer(), Zoom.IN_6.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_6) {
				env.setZoomLevel(Zoom.IN_7);
				zoomIn(env.getMainLayer(), Zoom.IN_7.getScale());
			} else if (env.getZoomLevel() == Zoom.OUT) {
				env.setZoomLevel(Zoom.DEFAULT);
				zoomIn(env.getMainLayer(), Zoom.DEFAULT.getScale());
			}
		}

		// Scroll out
		if (event.velocity() < 0) {
			if (env.getZoomLevel() == Zoom.DEFAULT) {
				env.setZoomLevel(Zoom.OUT);
				zoomOut(env.getMainLayer(), Zoom.OUT.getScale()); // 0.0f to zoom all the way out
			} else if (env.getZoomLevel() == Zoom.IN_1) {
				env.setZoomLevel(Zoom.DEFAULT);
				zoomOut(env.getMainLayer(), Zoom.DEFAULT.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_2) {
				env.setZoomLevel(Zoom.IN_1);
				zoomOut(env.getMainLayer(), Zoom.IN_1.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_3) {
				env.setZoomLevel(Zoom.IN_2);
				zoomOut(env.getMainLayer(), Zoom.IN_2.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_4) {
				env.setZoomLevel(Zoom.IN_3);
				zoomOut(env.getMainLayer(), Zoom.IN_3.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_5) {
				env.setZoomLevel(Zoom.IN_4);
				zoomOut(env.getMainLayer(), Zoom.IN_4.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_6) {
				env.setZoomLevel(Zoom.IN_5);
				zoomOut(env.getMainLayer(), Zoom.IN_5.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_7) {
				env.setZoomLevel(Zoom.IN_6);
				zoomOut(env.getMainLayer(), Zoom.IN_6.getScale());
			}
		}
	}

	private void zoomIn(GroupLayer layer, float scale) {
		xNext = -(scale * xCurrent * 4);
		yNext = -(scale * yCurrent * 4);
		
		if (scale > 1.0f) {
			xNext += -xCurrent;
			yNext += -yCurrent;
		}

		env.animator.tweenScale(layer).to(scale).in(zoomSpeed).easeIn();
		env.animator.tweenXY(layer).to(xNext, yNext).in(zoomSpeed).easeIn();
	}

	private void zoomOut(GroupLayer layer, float scale) {
		if (scale == 0.0f) {
			float xScale = (float)Const.WINDOW_WIDTH / (float)(Const.WORLD_WIDTH - Const.WORLD_ORIGIN_X);
			float yScale = (float)Const.WINDOW_HEIGHT / (float)(Const.WORLD_HEIGHT - Const.WORLD_ORIGIN_Y);

			if (xScale < yScale) {
				scale = xScale;
			} else {
				scale = yScale;
			}

			float scaledWidth = (Const.WORLD_WIDTH) * scale;
			float scaledHeight = (Const.WORLD_HEIGHT) * scale;
			float newOffsetX = ((Const.WINDOW_WIDTH - scaledWidth) / 2.0f) + (env.getX() * scale);
			float newOffsetY = ((Const.WINDOW_HEIGHT - scaledHeight) / 2.0f) + (env.getY() * scale);

			env.animator.tweenScale(layer).to(scale).in(zoomSpeed).easeInOut();
			env.animator.tweenXY(layer).to(newOffsetX, newOffsetY).in(zoomSpeed).easeInOut();
		} else {
			env.animator.tweenScale(layer).to(scale).in(zoomSpeed).easeIn();
			env.animator.tweenXY(layer).to(xNext, xNext).in(zoomSpeed).easeIn();
		}
	}
}
