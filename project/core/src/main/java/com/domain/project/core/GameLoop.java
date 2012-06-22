package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.controls.KeyboardControls;
import com.domain.project.core.controls.MouseControls;

import com.domain.project.core.Const;

public class GameLoop implements Game {

    private Environment environment;

    private KeyboardControls kbControls;
    private MouseControls mControls;
	
	private Graph cityGraphA;
	private Graph cityGraphB;
	private Graph campGraphA;
	private Graph campGraphB;

    @Override
    public void init() { 

        Const.loadImages();
        environment = new Environment();

		float graphXOffset = 30;
		float graphYOffset = 50;
		
        cityGraphA = new Graph(true, graphXOffset, graphYOffset, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT);
        cityGraphA.generateGraph("3IZ9", environment.getGraphLayer());
		campGraphA = new Graph(false, graphXOffset, Const.WORLD_HEIGHT/4 + graphYOffset/2, Const.CAMP_GRAPH_WIDTH, Const.CAMP_GRAPH_HEIGHT);
        campGraphA.generateGraph("2LDZ", environment.getGraphLayer());
		
		campGraphB = new Graph(false, graphXOffset, Const.WORLD_HEIGHT/2 + graphYOffset, Const.CAMP_GRAPH_WIDTH, Const.CAMP_GRAPH_HEIGHT);
        campGraphB.generateGraph("2LDZ", environment.getGraphLayer());
		cityGraphB = new Graph(true, graphXOffset, Const.WORLD_HEIGHT - Const.CAMP_GRAPH_HEIGHT - graphYOffset, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT);
        cityGraphB.generateGraph("3IZ9", environment.getGraphLayer());
		
		//environment.getGraphLayer().setScale(0.5f,0.5f);

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
		cityGraphA.paintAll();
		cityGraphB.paintAll();
		campGraphA.paintAll();
		campGraphB.paintAll();
    }

    @Override
    public void update(float delta) {
        kbControls.parse();
        environment.update(delta);
		cityGraphA.updateAll();
		cityGraphB.updateAll();
		campGraphA.updateAll();
		campGraphB.updateAll();
    }

    @Override
    public int updateRate() {
        return Const.UPDATE_RATE;
    }

}
