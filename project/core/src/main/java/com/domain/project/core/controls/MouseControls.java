package com.domain.project.core.controls;

import playn.core.Mouse;

import com.domain.project.core.Environment;

public class MouseControls implements Mouse.Listener {

    private Environment env;

    public MouseControls(Environment env) {
        this.env = env;
    }

    @Override
    public void onMouseDown(Mouse.ButtonEvent event) {
        if(event.button() == Mouse.BUTTON_MIDDLE) {
            System.out.println(event.x() + " " + event.y());
            System.out.println("local: " + event.localX() + " " + event.localY());
        }
    }

    @Override
    public void onMouseUp(Mouse.ButtonEvent event) {

    }

    @Override
    public void onMouseMove(Mouse.MotionEvent event) {

    }

    @Override
    public void onMouseWheelScroll(Mouse.WheelEvent event) {
        if(event.velocity() > 0) {
            System.out.println("up");
        }
        if(event.velocity() < 0) {
            System.out.println("down");
        }
    }

}
