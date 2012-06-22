package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

import playn.core.Mouse;

import com.domain.project.core.Const;

import tripleplay.anim.Animator;

public class Environment
{
    final private GroupLayer mainLayer;
    final private GroupLayer graphLayer; //contains graph layer objects(city graphs, camp graphs, etc)
    final private ImageLayer bgLayer;
    final private ImageLayer frameLayer;
    final private GroupLayer uiLayer; //contains all the objects and layers for the game UI
    //TODO : any more layers ?

/*
    - root
        - mainLayer
            - graphLayer
                - bgLayer
        - uiLayer
*/

    public final Animator animator = Animator.create();

    private float xOffset;
    private float yOffset;

    private float elapsed;

    public Environment()
    {
        //set window size
        graphics().setSize(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
        //graphics().setSize(graphics().screenWidth(),graphics().screenHeight());
        //TODO : Each device has different screen parameters (see http://playn-2011.appspot.com/slides/index.html#19)

        bgLayer = graphics().createImageLayer(Const.BACKGROUND_IMAGE);
        bgLayer.setSize(Const.WORLD_WIDTH, Const.WORLD_HEIGHT);
        bgLayer.setRepeatX(true);
        bgLayer.setRepeatY(true);
        
        frameLayer = graphics().createImageLayer(Const.FRAME_IMAGE);
        frameLayer.setTranslation(Const.WORLD_ORIGIN_X*2, Const.WORLD_ORIGIN_Y*2);
        frameLayer.setSize(Const.WORLD_END_WIDTH*2 - Const.WORLD_ORIGIN_X, Const.WORLD_END_HEIGHT*2 - Const.WORLD_ORIGIN_Y);
        frameLayer.setRepeatX(true);
        frameLayer.setRepeatY(true);

        mainLayer = graphics().createGroupLayer();
        //create group layer containing the graphs
        graphLayer = graphics().createGroupLayer();
        //create group layer containing the UI
        uiLayer = graphics().createGroupLayer();
        //add all the layer to the main Layer and then the root Layer
        graphLayer.add(bgLayer);
        mainLayer.add(graphLayer);
        mainLayer.add(frameLayer);
        graphics().rootLayer().add(uiLayer);
        graphics().rootLayer().add(mainLayer);

        frameLayer.setDepth(Const.BACKGROUND_DEPTH-1);
        bgLayer.setDepth(Const.BACKGROUND_DEPTH);
        graphLayer.setDepth(Const.GRAPH_DEPTH);
        xOffset = 0.0f;
        yOffset = 0.0f;
        
    }

    public Image getBaseImage()
    {
        return bgLayer.image();
    }

    public GroupLayer getGraphLayer()
    {
        return graphLayer;
    }

    public GroupLayer getMainLayer() {
        return mainLayer;
    }

//    public void update(float x, float y) {
//        mainLayer.setOrigin(x, y);
//    }
    public void update(float delta) {
        elapsed += delta;
//        mainLayer.setOrigin(xOffset, yOffset);
    }

    public void paint(float alpha) {
        mainLayer.setOrigin(xOffset, yOffset);
        animator.update(elapsed + Const.UPDATE_RATE * alpha);
    }

    public void setX(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setY(float yOffset) {
        this.yOffset = yOffset;
    }

    public float getX() {
        return xOffset;
    }

    public float getY() {
        return yOffset;
    }
}
