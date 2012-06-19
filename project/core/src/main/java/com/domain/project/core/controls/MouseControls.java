package com.domain.project.core.controls;

import playn.core.Mouse;

import com.domain.project.core.Environment;

public class MouseControls implements Mouse.Listener {

    private Environment env;

    private boolean middlePressed = false;

    public MouseControls(Environment env) {
        this.env = env;
    }

    @Override
    public void onMouseDown(Mouse.ButtonEvent event) {
        if(event.button() == Mouse.BUTTON_MIDDLE) {
            middlePressed = true;
        }
    }

    @Override
    public void onMouseUp(Mouse.ButtonEvent event) {
        if(event.button() == Mouse.BUTTON_MIDDLE) {
            middlePressed = false;
        }

    }

    @Override
    public void onMouseMove(Mouse.MotionEvent event) {
        if(middlePressed) {
            System.out.println(event.x() + " " + event.y());
        }
    }

    @Override
    public void onMouseWheelScroll(Mouse.WheelEvent event) {
        if(event.velocity() > 0) {
            System.out.println("up");
            env.getMainLayer().setScale(1.5f);
        }
        if(event.velocity() < 0) {
            System.out.println("down");
            env.getMainLayer().setScale(-1.5f);
        }
    }

}
