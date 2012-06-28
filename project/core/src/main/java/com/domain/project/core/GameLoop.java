package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.graph.Node;
import com.domain.project.core.graph.Edge;
import com.domain.project.core.graph.Mapping;
import com.domain.project.core.Gui;
import com.domain.project.core.Const;

import com.domain.project.core.controls.KeyboardControls;
import com.domain.project.core.controls.MouseControls;
import playn.core.Mouse;

import java.lang.Integer;
import java.util.Map;
import java.util.HashMap;

public class GameLoop implements Game {

    private Environment environment;

    private KeyboardControls kbControls;
    private MouseControls mControls;
	
	private Graph cityGraphA;
	private Graph cityGraphB;
	private Graph campGraphA;
	private Graph campGraphB;
    
    private String graphA = "1FUF_modified";
    private String graphB = "1FUF_modified";
	
	private Player player1;
	private Gui gui1;
	private Player player2;
	private Gui gui2;

    @Override
    public void init() { 

        Const.loadImages();
        environment = new Environment();
		
		player1 = new Player(1, "player 1");
		gui1 = new Gui(environment.getUILayer());
		player2 = new Player(2, "player 2");
		//TODO : gui2 = new Gui(environment.getUILayer());
		
		float graphXOffset = 90;
		float graphYOffset = 30;
		//TODO : read two graphs from database and put into these 4 graph instances
		
        cityGraphA = new Graph(true, graphXOffset/3, graphYOffset, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT, 1);
        cityGraphA.generateGraph(graphA, environment.getGraphLayer(), player1.getId());
		campGraphA = new Graph(false, Const.WORLD_WIDTH/4 + graphXOffset/2, graphYOffset, Const.CAMP_GRAPH_WIDTH, Const.CAMP_GRAPH_HEIGHT, 2);
        campGraphA.generateGraph(graphB, environment.getGraphLayer(), player1.getId());
		
		campGraphB = new Graph(false, Const.WORLD_WIDTH/2 + graphXOffset/2, graphYOffset, Const.CAMP_GRAPH_WIDTH, Const.CAMP_GRAPH_HEIGHT, 3);
        campGraphB.generateGraph(graphA, environment.getGraphLayer(), player2.getId());
		cityGraphB = new Graph(true, Const.WORLD_WIDTH - Const.CAMP_GRAPH_WIDTH - graphXOffset, graphYOffset, Const.CITY_GRAPH_WIDTH, Const.CITY_GRAPH_HEIGHT, 4);
        cityGraphB.generateGraph(graphB, environment.getGraphLayer(), player2.getId());
		
		//environment.getGraphLayer().setScale(0.5f,0.5f);

        //create and set controls
        kbControls = new KeyboardControls(environment);
        keyboard().setListener(kbControls);
        mControls = new MouseControls(environment);
        mouse().setListener(mControls);
		gui1.addListener(player1);
		//TODO : gui2.addListener(player2);
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
	
	//TODO : Multiplayer aspect, mouse should be associated with a player
	//TODO : add Networking, where does the players interations diverge ?
	
	/* Any interatcion with the game "Graph" layers and the responding game logic */
	private void addAllListeners(final Graph graph) {
		for(Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
			final Node node = entry.getValue();
			entry.getValue().getBase().getBaseLayer().addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						/* Select Node */
						player1.selectNode(node);
						/* Show all roads/edges */
						for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
							if(node.equals(graph.getNode1(edge.getValue()))) // || node.equals(graph.getNode2(edge.getValue())) 
								edge.getValue().getRoad().setVisible(true);
							else
								edge.getValue().getRoad().setVisible(false);
						}
						/* show the available Constructions */
						//TODO : gui1.paint(Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y); ???
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
