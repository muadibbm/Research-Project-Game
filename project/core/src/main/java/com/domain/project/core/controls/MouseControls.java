package com.domain.project.core.controls;

import playn.core.Mouse;

import com.domain.project.core.Environment;
import com.domain.project.core.Camera;

import com.domain.project.core.Const;

public class MouseControls implements Mouse.Listener {

    public boolean clickScroll = false;

    private Camera camera;

    private float xOld = 0.0f;
    private float yOld = 0.0f;
    private float xOffset = 0.0f;
    private float yOffset = 0.0f;

    public MouseControls(Camera cam) {
        this.camera = cam;
    }

    @Override
    public void onMouseDown(Mouse.ButtonEvent event) {
        if(event.button() == Mouse.BUTTON_MIDDLE) {
            clickScroll = true;
            xOld = event.x();
            yOld = event.y();
        }
    }

    @Override
    public void onMouseUp(Mouse.ButtonEvent event) {
        if(event.button() == Mouse.BUTTON_MIDDLE) {
            clickScroll = false;
        }

    }

    @Override
    public void onMouseMove(Mouse.MotionEvent event) {
        if(clickScroll) {
            xOffset = event.x() - xOld;
            yOffset = event.y() - yOld;
//            System.out.println(xOffset + " " + yOffset);

            //set x bounds
            if(camera.getX() >= Const.WORLD_ORIGIN_X && camera.getX() <= Const.WORLD_END_X - Const.WINDOW_WIDTH) {
                camera.setX(camera.getX() + xOffset);
            }
            if(camera.getX() < Const.WORLD_ORIGIN_X) {
                camera.setX(Const.WORLD_ORIGIN_X);
            }
            if(camera.getX() > Const.WORLD_END_X - Const.WINDOW_WIDTH) {
                camera.setX(Const.WORLD_END_X - Const.WINDOW_WIDTH);
            }

            //set y bounds
            if(camera.getY() >= Const.WORLD_ORIGIN_Y && camera.getY() <= Const.WORLD_END_Y - Const.WINDOW_HEIGHT) {
                camera.setY(camera.getY() + yOffset);
            }
            if(camera.getY() < Const.WORLD_ORIGIN_Y) {
                camera.setY(Const.WORLD_ORIGIN_Y);
            }
            if(camera.getY() > Const.WORLD_END_Y - Const.WINDOW_HEIGHT) {
                camera.setY(Const.WORLD_END_Y - Const.WINDOW_HEIGHT);
            }
            xOld = event.x();
            yOld = event.y();
        }
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
