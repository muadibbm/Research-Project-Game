package com.domain.project.core;

import playn.core.Keyboard;
import playn.core.Key;

public class KeyboardControls implements Keyboard.Listener {

    public static boolean scrollUp = false;
    public static boolean scrollDown = false;
    public static boolean scrollLeft = false;
    public static boolean scrollRight = false;

    public float panRate = 5.0f;
    
    public KeyboardControls() {

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
                System.exit(0);
                break;
            }
        }
    }

    @Override
    public void onKeyTyped(Keyboard.TypedEvent event){

    }

}
