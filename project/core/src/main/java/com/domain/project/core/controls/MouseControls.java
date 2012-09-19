package com.domain.project.core.controls;

import com.domain.project.core.Const;
import com.domain.project.core.Environment;
import com.domain.project.core.enums.Zoom;

import playn.core.GroupLayer;
import playn.core.Mouse;

import pythagoras.f.Transform;

public class MouseControls implements Mouse.Listener{

	public boolean clickScroll = false;
	private float zoomSpeed = 390.0f; // in milliseconds
	private Environment env;
	
	private float xNext = 0.0f;
	private float yNext = 0.0f;
	private float xCurrent = 0.0f;
	private float yCurrent = 0.0f;
	
	private float nextScale = 0.0f;

	/** Constructor
	 * @param env
	 */
	public MouseControls(Environment env) {
		this.env = env;
	}

	public float getX() {
		return xCurrent;
	}
	
	public float getY() {
		return yCurrent;
	}
	
	@Override
	public void onMouseDown(Mouse.ButtonEvent event) {
	}
	
	@Override
	public void onMouseUp(Mouse.ButtonEvent event) {
	}
	

	@Override
	public void onMouseMove(Mouse.MotionEvent event) {
		// with respect to window coordinates
		xCurrent = event.x();
		yCurrent = event.y();
	}

	@Override
	public void onMouseWheelScroll(Mouse.WheelEvent event) {
		
		// Scroll in
		if (event.velocity() > 0) {
			if (env.getZoomLevel() == Zoom.OUT) {
				env.setZoomLevel(Zoom.DEFAULT);
				zoomIn(env.getMainLayer(), Zoom.DEFAULT.getScale());
			}/* else if (env.getZoomLevel() == Zoom.DEFAULT) {
				env.setZoomLevel(Zoom.IN_1);
				zoomIn(env.getMainLayer(), Zoom.IN_1.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_1) {
				env.setZoomLevel(Zoom.IN_2);
				zoomIn(env.getMainLayer(), Zoom.IN_2.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_2) {
				env.setZoomLevel(Zoom.IN_3);
				zoomIn(env.getMainLayer(), Zoom.IN_3.getScale());
			}*/
		}

		// Scroll out
		if (event.velocity() < 0) {
			if (env.getZoomLevel() == Zoom.DEFAULT) {
				env.setZoomLevel(Zoom.OUT);
				zoomOut(env.getMainLayer(), Zoom.OUT.getScale()); // 0.0f to zoom all the way out
			}/* else if (env.getZoomLevel() == Zoom.IN_1) {
				env.setZoomLevel(Zoom.DEFAULT);
				zoomOut(env.getMainLayer(), Zoom.DEFAULT.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_2) {
				env.setZoomLevel(Zoom.IN_1);
				zoomOut(env.getMainLayer(), Zoom.IN_1.getScale());
			} else if (env.getZoomLevel() == Zoom.IN_3) {
				env.setZoomLevel(Zoom.IN_2);
				zoomOut(env.getMainLayer(), Zoom.IN_2.getScale());
			}*/
		}
	}

	private void zoomIn(GroupLayer layer, float scale) {
		xNext = 0.0f;
		yNext = 0.0f;
		if(scale == 1.0f) {
			nextScale = scale-0.25f;
			xNext = xCurrent*Const.WORLD_WIDTH/Const.WINDOW_WIDTH*nextScale;
			yNext = yCurrent*Const.WORLD_HEIGHT/Const.WINDOW_HEIGHT*nextScale;
			//TODO from HERE
			//if(xNext + Const.WINDOW_WIDTH >= Const.WORLD_WIDTH)
			//	xNext = (xNext - (xNext + Const.WINDOW_WIDTH - Const.WORLD_WIDTH)))*nextScale;
			//if(yNext + Const.WINDOW_HEIGHT >= Const.WORLD_HEIGHT)
			//	yNext = (yNext - (yNext + Const.WINDOW_HEIGHT - Const.WORLD_HEIGHT))*nextScale;
			/*if(xCurrent <= Const.WINDOW_WIDTH/2 & yCurrent <= Const.WINDOW_HEIGHT/2) {
				xNext = Const.WORLD_ORIGIN_X+Const.WORLD_WIDTH/4;
				yNext = Const.WORLD_ORIGIN_Y+Const.WORLD_HEIGHT/4;
			}
			else if(xCurrent > Const.WINDOW_WIDTH/2 & yCurrent <= Const.WINDOW_HEIGHT/2) {
				xNext = Const.WORLD_ORIGIN_X+3*Const.WORLD_WIDTH/4;
				yNext = Const.WORLD_ORIGIN_Y+Const.WORLD_HEIGHT/4;
			}
			else if(xCurrent > Const.WINDOW_WIDTH/2 & yCurrent > Const.WINDOW_HEIGHT/2) {
				xNext = Const.WORLD_ORIGIN_X+3*Const.WORLD_WIDTH/4;
				yNext = Const.WORLD_ORIGIN_Y+3*Const.WORLD_HEIGHT/4;
			}
			else if(xCurrent <= Const.WINDOW_WIDTH/2 & yCurrent > Const.WINDOW_HEIGHT/2) {
				xNext = Const.WORLD_ORIGIN_X+Const.WORLD_WIDTH/4;
				yNext = Const.WORLD_ORIGIN_Y+3*Const.WORLD_HEIGHT/4;
			}
			nextScale = scale-0.25f;*/
		}/* else if(scale == 4.0f) {
			xNext = -xCurrent*Const.WORLD_WIDTH/Const.WINDOW_WIDTH/4/4;
			yNext = -yCurrent*Const.WORLD_HEIGHT/Const.WINDOW_HEIGHT/4/4;
			nextScale = scale;
		} else if(scale == 8.0f) {
			xNext = -xCurrent*Const.WORLD_WIDTH/Const.WINDOW_WIDTH/16/4;
			yNext = -yCurrent*Const.WORLD_HEIGHT/Const.WINDOW_HEIGHT/16/4;
			nextScale = scale;
		} else if(scale == 16.0f) {
			xNext = -xCurrent*Const.WORLD_WIDTH/Const.WINDOW_WIDTH/16/16;
			yNext = -yCurrent*Const.WORLD_HEIGHT/Const.WINDOW_HEIGHT/16/16;
			nextScale = scale;
		}*/
		//env.animator.tweenTranslation(layer).to(-xNext, -yNext).in(zoomSpeed).easeIn();
		env.animator.tweenScale(layer).to(nextScale).in(zoomSpeed).easeIn();
		env.setX(xNext);
		env.setY(yNext);
	}

	private void zoomOut(GroupLayer layer, float scale) {
		xNext = 0.0f;
		yNext = 0.0f;
		if (scale == 0.0f) {
			xNext = Const.WORLD_ORIGIN_X;
			yNext = Const.WORLD_ORIGIN_Y;
			nextScale = 0.25f;
		}
		/*else if{
			xNext = xCurrent/Const.WORLD_WIDTH*Const.WINDOW_WIDTH;
			yNext = yCurrent/Const.WORLD_HEIGHT*Const.WINDOW_HEIGHT;
			nextScale = scale;
		}*/
		//env.animator.tweenTranslation(layer).to(xNext, yNext).in(zoomSpeed).easeIn();
		env.animator.tweenScale(layer).to(nextScale).in(zoomSpeed).easeIn();
		env.setX(xNext);
		env.setY(yNext);
	}
}
