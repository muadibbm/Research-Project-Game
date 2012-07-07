package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;

import com.domain.project.core.graph.Graph;
import com.domain.project.core.graph.Node;
import com.domain.project.core.graph.Tree;
import com.domain.project.core.graph.Tuple2f;
import com.domain.project.core.graph.City;
import com.domain.project.core.graph.Camp;
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

import java.util.List;
import java.util.ArrayList;

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
	
	private java.util.Random r = new java.util.Random();
	private List <Tree> trees;;

    @Override
    public void init() { 

        Const.loadImages();
        environment = new Environment();
		trees = new ArrayList <Tree> ();
		
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
		plantTrees();
		paintTrees();//performance ?

        //create and set controls
        kbControls = new KeyboardControls(environment);
        keyboard().setListener(kbControls);
        mControls = new MouseControls(environment);
        mouse().setListener(mControls);
		gui1.addListener(player1);
		//TODO : gui2.addListener(player2);
		addAllListeners(cityGraphA, player1);
		addAllListeners(campGraphA, player1);
		addAllListeners(campGraphB, player1);
		addAllListeners(cityGraphB, player1);
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
        environment.paint(alpha);
		cityGraphA.paintAll();
		cityGraphB.paintAll();
		campGraphA.paintAll();
		campGraphB.paintAll();
		//paintTrees(); <-- for html it has to be here
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
	private void addAllListeners(final Graph graph, final Player player) {
		for(Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
			final Node node = entry.getValue();
			entry.getValue().getBase().getBaseLayer().addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						/* Hide previous selections */
						HideAllEdges();
						HideAllMappings();
						/* Select New Node */
						player.selectNode(node);
						/* Set all edges of the selected node visible */
						for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet())
							if(node.equals(graph.getNode1(edge.getValue())))
								edge.getValue().getRoad().setVisible(true);
						if(node.getPlayer() == player.getId()) {
							/* Create Mapping if a Node is already set to be mapped */
							if(player.getNodeToBeMapped() != null) {
								if(player.getId() == player.getSelectedNode().getPlayer() & player.getSelectedNode().getMapping() == null) {
									if(player.getSelectedNode().getBase() instanceof City & player.getNodeToBeMapped().getBase() instanceof Camp)
										player.getSelectedNode().setMapping(player.getNodeToBeMapped());
									else if(player.getSelectedNode().getBase() instanceof Camp & player.getNodeToBeMapped().getBase() instanceof City)
										player.getNodeToBeMapped().setMapping(player.getSelectedNode());
								}
								System.out.println("remove graphical indication");
								//TODO : remove graphical indication
								player.setNodeToBeMapped(null);
							}
							/* Set the mapping of the selected node visible */
							if(node.getMapping() != null)
								node.getMapping().setVisible(true);
							/* Set the edges of the mapped_node of the selected node visible */ //TODO : too much ???
							//if(node.getMappedNode()!= null)
								//for(Map.Entry<Integer, Edge> edge : getGraph(node.getMappedNode().getGraphId()).getEdges().entrySet())
									//if(node.getMappedNode().equals(getGraph(node.getMappedNode().getGraphId()).getNode1(edge.getValue())))
										//edge.getValue().getRoad().setVisible(true);
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
	
	private Graph getGraph(int graphId) {
		if(cityGraphA.getId() == graphId)
			return cityGraphA;
		else if(campGraphA.getId() == graphId)
			return campGraphA;
		else if(cityGraphB.getId() == graphId)
			return cityGraphB;
		else if(campGraphB.getId() == graphId)
			return campGraphB;
		else
			return null; //Is this safe ? may cause bug !!
	}
	
	private void HideAllEdges() {
		HideEdges(cityGraphA);
		HideEdges(campGraphA);
		HideEdges(campGraphB);
		HideEdges(cityGraphB);
	}
	
	private void HideEdges(Graph graph) {
		for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet())
			edge.getValue().getRoad().setVisible(false);
	}
	
	private void HideAllMappings() {
		HideMappings(cityGraphA);
		HideMappings(campGraphA);
		HideMappings(campGraphB);
		HideMappings(cityGraphB);
	}
	
	private void HideMappings(Graph graph) {
		for(Map.Entry<Integer, Node> node : graph.getNodes().entrySet())
			if(node.getValue().getMapping() != null) {
				node.getValue().getMapping().setVisible(false);
			}
	}
	
	private void plantTrees() {
		int number = 0;
		float tmpX = 0.0f;
		float tmpY = 0.0f;
		while(number < Const.MAX_TREE_NUMBER) {
			tmpX = r.nextFloat()*Const.WORLD_WIDTH;
            tmpY = r.nextFloat()*Const.WORLD_HEIGHT;
			if(isTreeSeperated(cityGraphA, tmpX, tmpY) & isTreeSeperated(campGraphA, tmpX, tmpY) &
				isTreeSeperated(campGraphB, tmpX, tmpY) & isTreeSeperated(cityGraphB, tmpX, tmpY)) {
				Tree tree = new Tree(environment.getGraphLayer(), tmpX, tmpY);
				trees.add(tree);
				number++;
			}
		}
	}
	
	private boolean isTreeSeperated(Graph graph, float posX, float posY) {
        float distance = 0.0f;
        for(Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
			distance = entry.getValue().getPos().getDistanceFrom(new Tuple2f(posX, posY));
				if(distance < Const.MIN_NODE_TREE_DISTANCE)
						return false;
        }
        return true;
    }
	
	private void paintTrees() {
		for(Tree tree : trees)
			tree.paint();
	}
}
