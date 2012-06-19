package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Keyboard;
import playn.core.Key;

import tripleplay.anim.Animator;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.controls.KeyboardControls;
import com.domain.project.core.controls.MouseControls;

import com.domain.project.core.Const;

public class GameLoop implements Game {

    private Animator animator = Animator.create();

    private Camera camera;

    private Environment environment;

    private KeyboardControls kbControls;
    private MouseControls mControls;

    @Override
    public void init() { 

        Const.loadImages();
        environment = new Environment();
        camera = new Camera(0,0);

        Graph cityGraph = new Graph(true, Const.CITY_GRAPH_X, Const.CITY_GRAPH_Y, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT);
        cityGraph.generateGraph("3IZ9", environment.getGraphLayer(), environment.getPathLayer());

        System.out.println(cityGraph);

        //create and set controls
        kbControls = new KeyboardControls(environment);
        keyboard().setListener(kbControls);
        mControls = new MouseControls(environment);
        mouse().setListener(mControls);
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
            if(camera.getY() > Const.WORLD_ORIGIN_Y) {
                camera.setY(camera.getY() - kbControls.panRate);
            }
        }
        if(kbControls.scrollDown) {
            if(camera.getY() < Const.WORLD_END_Y - Const.WINDOW_HEIGHT) {
                camera.setY(camera.getY() + kbControls.panRate);
            }
        }
        if(kbControls.scrollLeft) {
            if(camera.getX() > Const.WORLD_ORIGIN_X) {
                camera.setX(camera.getX() - kbControls.panRate);
            }
        }
        if(kbControls.scrollRight) {
            if(camera.getX() < Const.WORLD_END_X - Const.WINDOW_WIDTH) {
                camera.setX(camera.getX() + kbControls.panRate);
            }
        }
    }

}
