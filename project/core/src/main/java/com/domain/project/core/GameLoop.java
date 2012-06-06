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
        ImageLayer bgLayer = graphics().createImageLayer(camera.getView());
        bgLayer.setSize(3200,1800);
//        graphics().rootLayer().add(bgLayer);
        graphLayer.add(bgLayer);

        //create and set keyboard controls
        kbControls = new KeyboardControls();
        keyboard().setListener(kbControls);

        Graph g = new Graph();
        g.generateGraph("3IZ9", graphLayer);
        System.out.println(g);

/*
        // add a listener for Keyboard input
        keyboard().setListener(new Keyboard.Adapter() {
            @Override
            public void onKeyDown(Keyboard.Event event) {
                if(event.key().equals(Key.A))
                    camera.setX(camera.getX() - 20);
                if(event.key().equals(Key.W))
                    camera.setY(camera.getY() - 20);
                if(event.key().equals(Key.D))
                    camera.setX(camera.getX() + 20);
                if(event.key().equals(Key.S))
                    camera.setY(camera.getY() + 20);
                if(event.key().equals(Key.ESCAPE))
                    System.exit(0);
            }

          @Override
          public void onKeyUp(Keyboard.Event event) {
            if(event.key().equals(Key.A))
                camera.setX(camera.getX() - 20);
            if(event.key().equals(Key.W))
                camera.setY(camera.getY() - 20);
            if(event.key().equals(Key.D))
                camera.setX(camera.getX() + 20);
            if(event.key().equals(Key.S))
                camera.setY(camera.getY() + 20);
          }
        });
*/
    
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
            camera.setY(camera.getY() - kbControls.panRate);
//            if(camera.getY() < 0.0f) {
//                camera.setY(0.0f);
//            }
        }
        if(kbControls.scrollDown) {
            camera.setY(camera.getY() + kbControls.panRate);
        }
        if(kbControls.scrollLeft) {
            camera.setX(camera.getX() - kbControls.panRate);
        }
        if(kbControls.scrollRight) {
            camera.setX(camera.getX() + kbControls.panRate);
        }
    }

}
