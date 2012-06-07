package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

import playn.core.ResourceCallback;

import playn.core.Keyboard;
import playn.core.Key;

public class GameLoop implements Game {

    GroupLayer graphLayer; //contains background and graph

    Camera camera;

    KeyboardControls kbControls;

    @Override
    public void init() {
        //set window size
        graphics().setSize(Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);

        //create group layer containing background and graph
        graphLayer = graphics().createGroupLayer();
        graphics().rootLayer().add(graphLayer);

        // create and add background image layer
        Image bgImage = assets().getImage("images/sample_environment.jpg");
        camera = new Camera(0,0, bgImage);
//        ImageLayer bgLayer = graphics().createImageLayer(camera.getView());
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        graphLayer.add(bgLayer);
        bgLayer.setSize(Const.WORLD_WIDTH,Const.WORLD_HEIGHT);
        bgLayer.setRepeatX(true);
        bgLayer.setRepeatY(true);

        graphLayer.setTranslation(500,500);
        //graphics().rootLayer().setScale(graphics().screenWidth(),graphics().screenHeight());
        //graphics().rootLayer().add(bgLayer);
        //graphLayer.add(bgLayer);

        //create and set keyboard controls
        kbControls = new KeyboardControls();
        keyboard().setListener(kbControls);

        Graph g = new Graph();
        g.generateGraph("3IZ9", graphLayer);
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
    }

    @Override
    public void update(float delta) {
        parseKB();
    }

    @Override
    public int updateRate() {
        return 25;
    }

    private void parseKB() {
        //parse keyboard inputs
        if(kbControls.scrollUp) {
            if(camera.getY() > 0)
                camera.setY(camera.getY() - kbControls.panRate);
        }
        if(kbControls.scrollDown) {
            if(camera.getY() < Const.WORLD_HEIGHT)
                camera.setY(camera.getY() + kbControls.panRate);
        }
        if(kbControls.scrollLeft) {
            if(camera.getX() > 0)
                camera.setX(camera.getX() - kbControls.panRate);
        }
        if(kbControls.scrollRight) {
            if(camera.getX() < Const.WORLD_WIDTH)
                camera.setX(camera.getX() + kbControls.panRate);
        }
    }

}
