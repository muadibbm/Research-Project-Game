package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Keyboard;
import playn.core.Key;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.controls.KeyboardControls;

import com.domain.project.core.Const;

public class GameLoop implements Game {
    
    Camera camera;
    
    Environment environment;
    
    KeyboardControls kbControls;

    @Override
    public void init() { 
	
		Const.loadImages();
        environment = new Environment();
        camera = new Camera(0,0);
        
        Graph g = new Graph(true);
        g.generateGraph("3IZ9", environment.getGraphLayer());
        
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
        environment.updateView(camera.getX(), camera.getY());
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
            if(camera.getY() < Const.WORLD_HEIGHT - Const.WINDOW_HEIGHT)
                camera.setY(camera.getY() + kbControls.panRate);
        }
        if(kbControls.scrollLeft) {
            if(camera.getX() > 0)
                camera.setX(camera.getX() - kbControls.panRate);
        }
        if(kbControls.scrollRight) {
            if(camera.getX() < Const.WORLD_WIDTH - Const.WINDOW_WIDTH)
                camera.setX(camera.getX() + kbControls.panRate);
        }
    }

}
