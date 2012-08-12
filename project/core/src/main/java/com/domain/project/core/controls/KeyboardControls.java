package com.domain.project.core.controls;

import playn.core.Keyboard;
import com.domain.project.core.Environment;
import com.domain.project.core.Const;

public class KeyboardControls implements Keyboard.Listener {

    public static boolean scrollUp = false;
    public static boolean scrollDown = false;
    public static boolean scrollLeft = false;
    public static boolean scrollRight = false;

    private float panRate = 5.0f;

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
//        System.out.println(env.getX() + " " + env.getY());
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
//                System.exit(0);
                break;
            }
            case SPACE: {
                System.out.println(env.getX() + " " + env.getY());
                System.out.println(env.zLevel());
                break;
            }
		default:
			break;
        }
    }

    @Override
    public void onKeyTyped(Keyboard.TypedEvent event){

    }

    public void parse() {
        //parse keyboard input
        if(scrollUp) {
            if(env.getY() > Const.WORLD_ORIGIN_Y && env.zLevel() != Zoom.OUT) {
                env.setY(env.getY() - panRate);
            }
        }
        if(scrollDown) {
            if(env.getY() < (Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT) && env.zLevel() != Zoom.OUT) {
                env.setY(env.getY() + panRate);
            }
        }
        if(scrollLeft) {
            if(env.getX() > Const.WORLD_ORIGIN_X && env.zLevel() != Zoom.OUT) {
                env.setX(env.getX() - panRate);
            }
        }
        if(scrollRight) {
            if(env.getX() < (Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH) && env.zLevel() != Zoom.OUT) {
                env.setX(env.getX() + panRate);
            }
        }

    }

}
