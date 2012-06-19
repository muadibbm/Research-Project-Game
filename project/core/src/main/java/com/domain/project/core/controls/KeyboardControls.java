package com.domain.project.core.controls;

import playn.core.Keyboard;
import playn.core.Key;

import com.domain.project.core.Environment;
import com.domain.project.core.Camera;

import com.domain.project.core.Const;

public class KeyboardControls implements Keyboard.Listener {

    public static boolean scrollUp = false;
    public static boolean scrollDown = false;
    public static boolean scrollLeft = false;
    public static boolean scrollRight = false;

    public float panRate = 5.0f;

    private Camera camera;

    public KeyboardControls(Camera cam) {
        this.camera = cam;
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
//                System.exit(0);
                break;
            }

            case SPACE: {

                break;
            }
        }
    }

    @Override
    public void onKeyTyped(Keyboard.TypedEvent event){

    }

    public void parse() {
        //parse keyboard input
        if(scrollUp) {
            if(camera.getY() > Const.WORLD_ORIGIN_Y) {
                camera.setY(camera.getY() - panRate);
            }
        }
        if(scrollDown) {
            if(camera.getY() < Const.WORLD_END_Y - Const.WINDOW_HEIGHT) {
                camera.setY(camera.getY() + panRate);
            }
        }
        if(scrollLeft) {
            if(camera.getX() > Const.WORLD_ORIGIN_X) {
                camera.setX(camera.getX() - panRate);
            }
        }
        if(scrollRight) {
            if(camera.getX() < Const.WORLD_END_X - Const.WINDOW_WIDTH) {
                camera.setX(camera.getX() + panRate);
            }
        }
    }

}
