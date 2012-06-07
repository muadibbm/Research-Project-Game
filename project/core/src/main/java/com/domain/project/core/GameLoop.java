package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;

import playn.core.ResourceCallback;

import playn.core.Keyboard;
import playn.core.Key;

public class GameLoop implements Game {
	
    Camera camera;
	
	Environment environmet;
    
    KeyboardControls kbControls;

    @Override
    public void init() {
		
		environmet = new Environment();
        camera = new Camera(0,0, environmet.getBaseImage());
		
		Graph g = new Graph();
        g.generateGraph("3IZ9", environmet.getGraphLayer());
		
        //create and set keyboard controls
        kbControls = new KeyboardControls();
        keyboard().setListener(kbControls);
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
