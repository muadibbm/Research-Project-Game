package com.domain.project.core.controls;

import playn.core.Mouse;
import playn.core.GroupLayer;

import com.domain.project.core.Environment;

import com.domain.project.core.Const;

public class MouseControls extends Controls implements Mouse.Listener {

    public boolean clickScroll = false;

    private Environment env;

    private float xOld = 0.0f;
    private float yOld = 0.0f;
    private float xOffset = 0.0f;
    private float yOffset = 0.0f;

    private float xCurrent = 0.0f;
    private float yCurrent = 0.0f;
   
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
        xCurrent = event.x(); //wrt window coords
        yCurrent = event.y();
        if(clickScroll && zLevel != Zoom.OUT) {
            xOffset = event.x() - xOld;
            yOffset = event.y() - yOld;
//            System.out.println(xOffset + " " + yOffset);

            //set x bounds
            if(env.getX() >= Const.WORLD_ORIGIN_X && env.getX() <= (Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH)) {
                env.setX(env.getX() - xOffset);
            }
            if(env.getX() < Const.WORLD_ORIGIN_X) {
                env.setX(Const.WORLD_ORIGIN_X);
            }
            if(env.getX() > (Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH)) {
                env.setX((Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH));
            }

            //set y bounds
            if(env.getY() >= Const.WORLD_ORIGIN_Y && env.getY() <= Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT) {
                env.setY(env.getY() - yOffset);
            }
            if(env.getY() < Const.WORLD_ORIGIN_Y) {
                env.setY(Const.WORLD_ORIGIN_Y);
            }
            if(env.getY() > Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT) {
                env.setY(Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT);
            }
            xOld = event.x();
            yOld = event.y();
        }
    }

    @Override
    public void onMouseWheelScroll(Mouse.WheelEvent event) {
        if(event.velocity() > 0) {
//            if(scaleFactor < scaleFactorMax) {
//                scaleFactor += scaleRate;
//                zoomIn(env.getMainLayer(), scaleFactor);
//            }
            if(zLevel == Zoom.DEFAULT) {
                zLevel = Zoom.IN;
                zoomIn(env.getMainLayer(), 2.0f);
            } else if(zLevel == Zoom.OUT) {
                zLevel = Zoom.DEFAULT;
                zoomIn(env.getMainLayer(), 1.0f);
            }
        }
        if(event.velocity() < 0) {
//            scaleFactor = 1.0f;
//            zoomOut(env.getMainLayer(), scaleFactor);
            if(zLevel == Zoom.DEFAULT) {
                zLevel = Zoom.OUT;
                zoomOut(env.getMainLayer(), 0.5f);
            } else if(zLevel == Zoom.IN) {
                zLevel = Zoom.DEFAULT;
                zoomIn(env.getMainLayer(), 1.0f);
            }
        }

    }


    private void zoomIn(GroupLayer layer, float scale) {
        env.animator.tweenScale(layer).in(400f).easeInOut().to(scale);
//        env.animator.tweenXY(layer).in(250f).easeInOut().to(-(scaleFactor * xCurrent) + (Const.WINDOW_WIDTH / 2.0f) , -(scaleFactor * yCurrent) + (Const.WINDOW_HEIGHT / 2.0f));
        env.animator.tweenXY(layer).in(400f).easeInOut().to(-(scale * Const.WINDOW_WIDTH) / 2.0f + (Const.WINDOW_WIDTH / 2.0f) , -(scale * Const.WINDOW_HEIGHT) / 2.0f + (Const.WINDOW_HEIGHT / 2.0f));
    }
    private void zoomOut(GroupLayer layer, float scale) {
        if(zLevel == Zoom.OUT) {
            env.animator.tweenScale(layer).in(400f).easeInOut().to(scale);
            env.animator.tweenXY(layer).in(400f).easeInOut().to(xOffset, yOffset);
        } else {
            env.animator.tweenScale(layer).in(400f).easeInOut().to(scale);
            env.animator.tweenXY(layer).in(400f).easeInOut().to(xOffset, yOffset);       
        }
    }

}
