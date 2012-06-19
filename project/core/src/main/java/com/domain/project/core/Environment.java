package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

import com.domain.project.core.Const;

import tripleplay.anim.Animator;

public class Environment
{
    final private GroupLayer mainLayer;
    final private GroupLayer graphLayer; //contains graph layer objects(city graphs, camp graphs, etc)
    final private GroupLayer pathLayer; //contains all the roads and mappings
    final private ImageLayer bgLayer;
    final private GroupLayer uiLayer; //contains all the objects and layers for the game UI
    //TODO : any more layers ?

/*
    - root
        - mainLayer
            - graphLayer
            - pathlayer
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
        graphics().setSize(Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);
        //graphics().setSize(graphics().screenWidth(),graphics().screenHeight());
        //TODO : Each device has different screen parameters (see http://playn-2011.appspot.com/slides/index.html#19)

        bgLayer = graphics().createImageLayer(Const.BACKGROUND_IMAGE);//TODO: camera.getView()
        bgLayer.setSize(Const.WORLD_WIDTH, Const.WORLD_HEIGHT);
        bgLayer.setRepeatX(true);
        bgLayer.setRepeatY(true);

        mainLayer = graphics().createGroupLayer();
        //create group layer containing the graphs
        graphLayer = graphics().createGroupLayer();
        //create group layer containing the edges
        pathLayer = graphics().createGroupLayer();
        //create group layer containing the UI
        uiLayer = graphics().createGroupLayer();
        //add all the layer to the main Layer and then the root Layer
        mainLayer.add(pathLayer);
        mainLayer.add(graphLayer);
        mainLayer.add(bgLayer);
        graphics().rootLayer().add(uiLayer);
        graphics().rootLayer().add(mainLayer);

        bgLayer.setDepth(Const.BACKGROUND_DEPTH);
        graphLayer.setDepth(Const.GRAPH_DEPTH);
        pathLayer.setDepth(Const.PATH_DEPTH);

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

    public GroupLayer getPathLayer()
    {
        return pathLayer;
    }

    public GroupLayer getMainLayer() {
        return mainLayer;
    }

//    public void update(float x, float y) {
//        mainLayer.setOrigin(x, y);
//    }
    public void update(float delta) {
        elapsed += delta;
        mainLayer.setOrigin(xOffset, yOffset);
    }

    public void paint(float alpha) {
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
