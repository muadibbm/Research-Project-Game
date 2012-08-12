package com.domain.project.core.controls;

import playn.core.Mouse;
import playn.core.GroupLayer;

import com.domain.project.core.Environment;
import com.domain.project.core.Const;

public class MouseControls implements Mouse.Listener {

    public boolean clickScroll = false;
    
    private float zoomSpeed = 600f; // in mseconds

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
        

        if(clickScroll && env.zLevel() != Zoom.OUT) {
            xOffset = xCurrent - xOld;
            yOffset = yCurrent - yOld;

            //set x bounds
            if(env.getX() >= Const.WORLD_ORIGIN_X && env.getX() <= Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH) {
                env.setX(env.getX() - xOffset);
            }
            if(env.getX() < Const.WORLD_ORIGIN_X) {
                env.setX(Const.WORLD_ORIGIN_X);
            }
            if(env.getX() > Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH) {
                env.setX(Const.WORLD_END_WIDTH - Const.WINDOW_WIDTH);
            }

            //set y bounds
            if(env.getY() >= Const.WORLD_ORIGIN_Y && env.getY() <= Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT) {
                env.setY(env.getY() - yOffset);
            }
            if(env.getY() < Const.WORLD_ORIGIN_Y) {
                env.setY(Const.WORLD_ORIGIN_Y);
            }
            if(env.getY() > Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT)  {
                env.setY(Const.WORLD_END_HEIGHT - Const.WINDOW_HEIGHT);
            }
            
            //System.out.println(env.getX() + " " + env.getY());
            xOld = xCurrent;
            yOld = yCurrent;

        }
    }

    @Override
    public void onMouseWheelScroll(Mouse.WheelEvent event) {
        if(event.velocity() > 0) {
            if(env.zLevel() == Zoom.DEFAULT) {
                env.setZoomLevel(Zoom.IN);
                zoomIn(env.getMainLayer(), Zoom.IN.getScale());
            } else if(env.zLevel() == Zoom.OUT) {
                env.setZoomLevel(Zoom.DEFAULT);
                zoomIn(env.getMainLayer(), Zoom.DEFAULT.getScale());
            }
        }
        if(event.velocity() < 0) {
            if(env.zLevel() == Zoom.DEFAULT) {
                env.setZoomLevel(Zoom.OUT);
                zoomOut(env.getMainLayer(), Zoom.OUT.getScale()); //0.0f to zoom all the way out
            } else if(env.zLevel() == Zoom.IN) {
                env.setZoomLevel(Zoom.DEFAULT);
                zoomIn(env.getMainLayer(), Zoom.DEFAULT.getScale());
            }
        }
    }


    private void zoomIn(GroupLayer layer, float scale) {
//        if(env.zLevel() == Zoom.DEFAULT) {
//            env.animator.tweenScale(layer).in(400f).easeInOut().to(scale);
//            env.animator.tweenXY(layer).in(400f).easeInOut().to(-(scale * xCurrent) + (Const.WINDOW_WIDTH / 2.0f) - scale * env.getX(), -(scale * yCurrent) + (Const.WINDOW_HEIGHT / 2.0f) - env.getY() * scale);
//        } else {
            env.animator.tweenScale(layer).in(zoomSpeed).easeInOut().to(scale);
            env.animator.tweenXY(layer).in(zoomSpeed).easeInOut().to(-(scale * Const.WINDOW_WIDTH / 2.0f) + (Const.WINDOW_WIDTH / 2.0f) , -(scale * Const.WINDOW_HEIGHT / 2.0f) + (Const.WINDOW_HEIGHT / 2.0f));
            //env.animator.tweenXY(layer).in(400f).easeInOut().to(-(scale * xCurrent) + (Const.WINDOW_WIDTH / 2.0f) , -(scale * yCurrent) + (Const.WINDOW_HEIGHT / 2.0f));
//        }

    }
    
    private void zoomOut(GroupLayer layer, float scale) {
        if(scale == 0.0f) {
            float xScale = (float)Const.WINDOW_WIDTH / (float)(Const.WORLD_END_WIDTH - Const.WORLD_ORIGIN_X);
            float yScale = (float)Const.WINDOW_HEIGHT / (float)(Const.WORLD_END_HEIGHT - Const.WORLD_ORIGIN_Y);
            if(xScale < yScale) {
                scale = xScale;
            } else {
                scale = yScale;
            }

            float scaledWidth = (Const.WORLD_END_WIDTH - Const.FRAME_SIZE) * scale;
            float scaledHeight = (Const.WORLD_END_HEIGHT - Const.FRAME_SIZE) * scale;
            float newOffsetX = ((Const.WINDOW_WIDTH - scaledWidth ) / 2.0f) + (env.getX() * scale);
            float newOffsetY = ((Const.WINDOW_HEIGHT - scaledHeight) / 2.0f) + (env.getY() * scale);

            env.animator.tweenScale(layer).in(zoomSpeed).easeInOut().to(scale);
            env.animator.tweenXY(layer).in(zoomSpeed).easeInOut().to(newOffsetX, newOffsetY);
        } else {
            env.animator.tweenScale(layer).in(zoomSpeed).easeInOut().to(scale);
            env.animator.tweenXY(layer).in(zoomSpeed).easeInOut().to(xOffset, yOffset);       
        }
    }

}
