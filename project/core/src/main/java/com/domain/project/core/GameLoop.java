package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

import playn.core.Keyboard;
import playn.core.Key;

public class GameLoop implements Game {

    GroupLayer graphLayer; //contains background and graph

    @Override
    public void init() {
        //set window size
        graphics().setSize(Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);

        //create group layer containing background and graph
        graphLayer = graphics().createGroupLayer();
        graphics().rootLayer().add(graphLayer);

        // create and add background image layer
        final Image bgImage = assets().getImage("images/sample_environment.jpg");
        final Camera camera = new Camera(0,0, bgImage);
        final ImageLayer bgLayer = graphics().createImageLayer(camera.getView());
        bgLayer.setSize(3200,1800);
//        graphics().rootLayer().add(bgLayer);
        graphLayer.add(bgLayer);

        

        System.out.println(bgImage.width() + " " + bgImage.height());

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
          /*@Override
          public void onKeyUp(Keyboard.Event event) {
            if(event.key().equals(Key.A))
                camera.setX(camera.getX() - 20);
            if(event.key().equals(Key.W))
                camera.setY(camera.getY() - 20);
            if(event.key().equals(Key.D))
                camera.setX(camera.getX() + 20);
            if(event.key().equals(Key.S))
                camera.setY(camera.getY() + 20);
          }*/
        });
    
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
    }

    @Override
    public void update(float delta) {
    
    }

    @Override
    public int updateRate() {
        return 25;
    }
}
