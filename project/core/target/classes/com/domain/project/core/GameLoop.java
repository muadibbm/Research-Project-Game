package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Keyboard;
import playn.core.Key;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.graph.Node;
import com.domain.project.core.graph.Edge;

import com.domain.project.core.controls.KeyboardControls;
import com.domain.project.core.controls.MouseControls;
import playn.core.Mouse;

import java.lang.Integer;
import java.util.Map;
import java.util.HashMap;

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
		float graphYOffset = 80;
		
		//TODO : read two graphs from database and put into these 4 graph instances
		
        cityGraphA = new Graph(true, graphXOffset, graphYOffset/4, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT, 1);
        cityGraphA.generateGraph("3IZ9", environment.getGraphLayer());
		campGraphA = new Graph(false, graphXOffset, Const.WORLD_HEIGHT/4 + graphYOffset, Const.CAMP_GRAPH_WIDTH, Const.CAMP_GRAPH_HEIGHT, 2);
        campGraphA.generateGraph("2LDZ", environment.getGraphLayer());
		
		campGraphB = new Graph(false, graphXOffset, Const.WORLD_HEIGHT/2 + graphYOffset, Const.CAMP_GRAPH_WIDTH, Const.CAMP_GRAPH_HEIGHT, 3);
        campGraphB.generateGraph("2LDZ", environment.getGraphLayer());
		cityGraphB = new Graph(true, graphXOffset, Const.WORLD_HEIGHT - Const.CAMP_GRAPH_HEIGHT - graphYOffset, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT, 4);
        cityGraphB.generateGraph("3IZ9", environment.getGraphLayer());
		
		//environment.getGraphLayer().setScale(0.5f,0.5f);

        //create and set controls
        kbControls = new KeyboardControls(environment);
        keyboard().setListener(kbControls);
        mControls = new MouseControls(environment);
        mouse().setListener(mControls);
		addAllListeners(cityGraphA);
		addAllListeners(campGraphA);
		addAllListeners(campGraphB);
		addAllListeners(cityGraphB);
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
	
	/* Any interatcion with the game and the responding game logic goes here */
	
	//TODO : fix this
	private void addAllListeners(final Graph graph) {
		for(Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
			final Node node = entry.getValue();
			entry.getValue().getBase().getBaseLayer().addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
							if(node.equals(graph.getNode1(edge.getValue())) || node.equals(graph.getNode2(edge.getValue()))) 
								edge.getValue().getRoad().setVisible(true);
							else
								edge.getValue().getRoad().setVisible(false);
							
						}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
			});
		}
	}
}
