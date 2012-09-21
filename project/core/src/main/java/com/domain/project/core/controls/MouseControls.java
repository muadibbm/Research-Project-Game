package com.domain.project.core.controls;

import com.domain.project.core.Const;
import com.domain.project.core.Environment;
import com.domain.project.core.enums.Zoom;

import playn.core.GroupLayer;
import playn.core.Mouse;

import pythagoras.f.Transform;

public class MouseControls implements Mouse.Listener{

	public boolean clickScroll = false;
	private float zoomSpeed = 900.0f; // in milliseconds
	private Environment env;
	
	private float xNext = 0.0f;
	private float yNext = 0.0f;
	private float xCurrent = 0.0f;
	private float yCurrent = 0.0f;
	
	private float nextScaleX = 0.0f;
	private float nextScaleY = 0.0f;

	/** Constructor
	 * @param env
	 */
	public MouseControls(Environment env) {
		this.env = env;
		env.setZoomLevel(Zoom.OUT);
		zoomOut(env.getMainLayer(), Zoom.OUT.getScale()); // 0.0f to zoom all the way out
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
			} /*else if (env.getZoomLevel() == Zoom.DEFAULT) {
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
			} /*else if (env.getZoomLevel() == Zoom.IN_1) {
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
		if(scale == 1.0f) {
			nextScaleX = Const.WORLD_WIDTH/Const.WINDOW_WIDTH;
			nextScaleY = Const.WORLD_HEIGHT/Const.WINDOW_HEIGHT;
			xNext = xCurrent*nextScaleX - Const.WINDOW_WIDTH/nextScaleX/2;
			yNext = yCurrent*nextScaleY - Const.WINDOW_HEIGHT/nextScaleY/2;
			if(xNext + Const.WINDOW_WIDTH >= Const.WORLD_WIDTH)
				xNext = xNext - Const.WINDOW_WIDTH/nextScaleX/4;
			if(yNext + Const.WINDOW_HEIGHT >= Const.WORLD_HEIGHT)
				yNext = yNext  - Const.WINDOW_HEIGHT/nextScaleY/4;
			if(xNext < Const.WORLD_ORIGIN_X)
				xNext = Const.WORLD_ORIGIN_X;
			if(yNext < Const.WORLD_ORIGIN_Y)
				yNext = Const.WORLD_ORIGIN_Y;
		}/*
		else if(scale == 2.0f) {
			nextScaleX = Const.WINDOW_WIDTH/nextScaleX;
			nextScaleY = Const.WINDOW_HEIGHT/nextScaleY;
			xNext = xCurrent*nextScaleX - Const.WINDOW_WIDTH/nextScaleX/2;
			yNext = yCurrent*nextScaleY - Const.WINDOW_HEIGHT/nextScaleY/2;
			if(xNext + Const.WINDOW_WIDTH/4 >= Const.WORLD_WIDTH)
				xNext = xNext - Const.WINDOW_WIDTH/nextScaleX/4;
			if(yNext + Const.WINDOW_HEIGHT/4 >= Const.WORLD_HEIGHT)
				yNext = yNext  - Const.WINDOW_HEIGHT/nextScaleY/4;
			if(xNext < Const.WORLD_ORIGIN_X)
				xNext = Const.WORLD_ORIGIN_X;
			if(yNext < Const.WORLD_ORIGIN_Y)
				yNext = Const.WORLD_ORIGIN_Y;
		}*/
		env.animator.tweenScaleX(layer).to(nextScaleX).in(zoomSpeed).easeIn();
		env.animator.tweenScaleY(layer).to(nextScaleY).in(zoomSpeed).easeIn();
		env.setX(xNext);
		env.setY(yNext);
	}

	private void zoomOut(GroupLayer layer, float scale) {
		if (scale == 0.0f) {
			xNext = Const.WORLD_ORIGIN_X;
			yNext = Const.WORLD_ORIGIN_Y;
			nextScaleX = 0.25f;
			nextScaleY = 0.25f;
		}/*
		else if(scale == 1.0f) {
			nextScaleX = Const.WINDOW_WIDTH/Const.WORLD_WIDTH;
			nextScaleY = Const.WINDOW_HEIGHT/Const.WORLD_HEIGHT;
			xNext = xCurrent*nextScaleX - Const.WINDOW_WIDTH/nextScaleX/2;
			yNext = yCurrent*nextScaleY - Const.WINDOW_HEIGHT/nextScaleY/2;
			if(xNext + Const.WINDOW_WIDTH >= Const.WORLD_WIDTH)
				xNext = xNext - Const.WINDOW_WIDTH/nextScaleX/4;
			if(yNext + Const.WINDOW_HEIGHT >= Const.WORLD_HEIGHT)
				yNext = yNext  - Const.WINDOW_HEIGHT/nextScaleY/4;
			if(xNext < Const.WORLD_ORIGIN_X)
				xNext = Const.WORLD_ORIGIN_X;
			if(yNext < Const.WORLD_ORIGIN_Y)
				yNext = Const.WORLD_ORIGIN_Y;
		}*/
		env.animator.tweenScaleX(layer).to(nextScaleX).in(zoomSpeed).easeOut();
		env.animator.tweenScaleY(layer).to(nextScaleY).in(zoomSpeed).easeOut();
		env.setX(xNext);
		env.setY(yNext);
	}
}
