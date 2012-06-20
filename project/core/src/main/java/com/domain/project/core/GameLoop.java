package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Keyboard;
import playn.core.Key;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.controls.KeyboardControls;
import com.domain.project.core.controls.MouseControls;

import com.domain.project.core.Const;

public class GameLoop implements Game {

    private Environment environment;

    private KeyboardControls kbControls;
    private MouseControls mControls;
	
	private Graph cityGraph;

    @Override
    public void init() { 

        Const.loadImages();
        environment = new Environment();

        cityGraph = new Graph(true, 50, 50, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT);
        cityGraph.generateGraph("3IZ9", environment.getGraphLayer());
		//environment.getGraphLayer().setScale(0.5f,0.5f);
        //System.out.println(cityGraph);

        //create and set controls
        kbControls = new KeyboardControls(environment);
        keyboard().setListener(kbControls);
        mControls = new MouseControls(environment);
        mouse().setListener(mControls);
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
        environment.paint(alpha);
		cityGraph.paintAll();
    }

    @Override
    public void update(float delta) {
        kbControls.parse();
        environment.update(delta);
		cityGraph.updateAll();
    }

    @Override
    public int updateRate() {
        return Const.UPDATE_RATE;
    }

}
