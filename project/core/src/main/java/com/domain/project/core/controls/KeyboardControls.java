package com.domain.project.core.controls;

import playn.core.Keyboard;

import com.domain.project.core.Const;
import com.domain.project.core.Environment;
import com.domain.project.core.enums.Zoom;

public class KeyboardControls implements Keyboard.Listener {

	public static boolean scrollUp = false;
	public static boolean scrollDown = false;
	public static boolean scrollLeft = false;
	public static boolean scrollRight = false;

	private float panRate = 25.0f;

	private Environment env;

	public KeyboardControls(Environment env) {
		this.env = env;
	}

	@Override
	public void onKeyUp(Keyboard.Event event) {
		switch(event.key()) {
		case W: {
			scrollUp = false;
			break;
		}
		case S: {
			scrollDown = false;
			break;
		}
		case A: {
			scrollLeft = false;
			break;
		}
		case D: {
			scrollRight = false;
			break;
		}
		case UP: {
			scrollUp = false;
			break;
		}
		case DOWN: {
			scrollDown = false;
			break;
		}
		case LEFT: {
			scrollLeft = false;
			break;
		}
		case RIGHT: {
			scrollRight = false;
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void onKeyDown(Keyboard.Event event) {
		switch(event.key()) {
		case W: {
			scrollUp = true;
			break;
		}
		case S: {
			scrollDown = true;
			break;
		}
		case A: {
			scrollLeft = true;
			break;
		}
		case D: {
			scrollRight = true;
			break;
		}
		case UP: {
			scrollUp = true;
			break;
		}
		case DOWN: {
			scrollDown = true;
			break;
		}
		case LEFT: {
			scrollLeft = true;
			break;
		}
		case RIGHT: {
			scrollRight = true;
			break;
		}
		case ESCAPE: {
			break;
		}
		case SPACE: {
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void onKeyTyped(Keyboard.TypedEvent event){

	}

	public void parse(float mX, float mY) {
		//parse keyboard input
		if(scrollUp || mY <= Const.WINDOW_HEIGHT/100) {
			if (env.getY() >= Const.WORLD_ORIGIN_Y && env.getZoomLevel() != Zoom.OUT) {
				if(env.getY() - panRate >= Const.WORLD_ORIGIN_Y)
					env.setY(env.getY() - panRate);
			}
		}
		
		if(scrollDown || mY >= 99*Const.WINDOW_HEIGHT/100) {
			if (env.getY() <= (Const.WORLD_HEIGHT - Const.WINDOW_HEIGHT) && env.getZoomLevel() != Zoom.OUT) {
				if(env.getY() + panRate <= Const.WORLD_HEIGHT)
					env.setY(env.getY() + panRate);
			}
		}
		
		if(scrollLeft || mX <= Const.WINDOW_WIDTH/100) {
			if (env.getX() >= (Const.WORLD_ORIGIN_X) && env.getZoomLevel() != Zoom.OUT) {
				if(env.getX() - panRate >= Const.WORLD_ORIGIN_X)
					env.setX(env.getX() - panRate);
			}
		}
		
		if(scrollRight || mX >= 99*Const.WINDOW_WIDTH/100) {
			if (env.getX() <= (Const.WORLD_WIDTH - Const.WINDOW_WIDTH) && env.getZoomLevel() != Zoom.OUT) {
				if(env.getX() + panRate <= Const.WORLD_WIDTH)
					env.setX(env.getX() + panRate);
			}
		}
	}
}
