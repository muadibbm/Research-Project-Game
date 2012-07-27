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

/**
* This is the main game loop where are the calculations and drawing takes place.
*/
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

	/**
	* Initilizes all the game variables before starting the game.
	* @see http://docs.playn.googlecode.com/git/javadoc/index.html
	*/
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
		gui1.addListener(player1, environment.getGraphLayer());
		gui1.showConstructions(false, false);
		/*TODO : gui2.addListener(player2, environment.getGraphLayer());
		gui2.showConstructions(false, false); */
		addAllListeners(cityGraphA, player1, gui1);
		addAllListeners(campGraphA, player1, gui1);
		addAllListeners(campGraphB, player1, gui1);
		addAllListeners(cityGraphB, player1, gui1);
    }
	
	/**
	* Paints all the images contained in the scene graph layers.
	* Any transformation applied to the layers should be called here.
	* @param alpha - a value between 0 and 1, representing the proportion of time passed between the last two update ticks
	* @see http://docs.playn.googlecode.com/git/javadoc/index.html
	*/
    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything here!
        environment.paint(alpha);
		cityGraphA.paintAll();
		cityGraphB.paintAll();
		campGraphA.paintAll();
		campGraphB.paintAll();
		//paintTrees(); <-- for html it has to be here
		gui1.setGold(player1.getGold());
    }

	/**
	* All the updates to the game logic should be called here.
	* @param delta - time, in ms, passed since the previous update(float)
	* @see http://docs.playn.googlecode.com/git/javadoc/index.html
	*/
    @Override
    public void update(float delta) {
        kbControls.parse();
        environment.update(delta);
		cityGraphA.updateAll();
		cityGraphB.updateAll();
		campGraphA.updateAll();
		campGraphB.updateAll();
		/*gold test*/
		player1.setGold(player1.getGold()+1);
    }

	/**
	* Return the update rate of the main game loop, in ms.
	* @return update rate of the main game loop, in ms
	* @see http://docs.playn.googlecode.com/git/javadoc/index.html
	*/
    @Override
    public int updateRate() {
        return Const.UPDATE_RATE;
    }
	
	//TODO : Multiplayer aspect, mouse should be associated with a player
	//TODO : add Networking, where does the players interations diverge ?
	
	/** 
	* Contains any interatcion with the layers in the game's graphs and the corresponding game logic. 
	* TODO : This does not affect paint method in html version of the game.
	* @param graph - the associated graph object
	* @param player - the assciated player with the mouse click
	* @param gui - the user interface corresponding to the player
	*/
	private void addAllListeners(final Graph graph, final Player player, final Gui gui) {
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
									if(player.getSelectedNode().getBase() instanceof City & player.getNodeToBeMapped().getBase() instanceof Camp) {
										/* Inefficient mapping occurs when the city population is less than the capacity of a camp, thus the city lacks the required resources for the camp
											mapping score : 0 , mapping propagation score : -1 */
										if(player.getSelectedNode().getNodeLevel() < player.getNodeToBeMapped().getNodeLevel()) {
											player.getSelectedNode().setMapping(player.getNodeToBeMapped(), 0);
										}
										/* Acceptable mapping occurs when the city population is more than the capacity of a camp, thus the has the resources for the camp but the camp is too small for the city
											mapping score : 1 , mapping propagation score : 0 */
										else if(player.getSelectedNode().getNodeLevel() > player.getNodeToBeMapped().getNodeLevel()) {
											player.getSelectedNode().setMapping(player.getNodeToBeMapped(), 1);
										}
										/* Efficient mapping occurs when the city population is exactly matches the capacity of a camp
											mapping score : 2 , mapping propagation score : 1 */
										else {
											player.getSelectedNode().setMapping(player.getNodeToBeMapped(), 2);
										}
									}
									else if(player.getSelectedNode().getBase() instanceof Camp & player.getNodeToBeMapped().getBase() instanceof City) {
										/* Inefficient mapping occurs when the city population is less than the capacity of a camp, thus the city lacks the required resources for the camp
											mapping score : 0 , mapping propagation score : -1 */
										if(player.getNodeToBeMapped().getNodeLevel() < player.getSelectedNode().getNodeLevel()) {
											player.getNodeToBeMapped().setMapping(player.getSelectedNode(), 0);
										}
										/* Acceptable mapping occurs when the city population is more than the capacity of a camp, thus the has the resources for the camp but the camp is too small for the city
											mapping score : 1 , mapping propagation score : 0 */
										else if(player.getNodeToBeMapped().getNodeLevel() > player.getSelectedNode().getNodeLevel()) {
											player.getNodeToBeMapped().setMapping(player.getSelectedNode(), 1);
										}
										/* Efficient mapping occurs when the city population is exactly matches the capacity of a camp
											mapping score : 2 , mapping propagation score : 1 */
										else {
											player.getNodeToBeMapped().setMapping(player.getSelectedNode(), 2);
										}
									}
								System.out.println("remove graphical indication");
								//TODO : remove graphical indication (not yet added)
								player.setNodeToBeMapped(null);
								}
							}
							/* Set the mapping of the selected node visible */
							if(node.getMapping() != null)
								node.getMapping().setVisible(true);
								
							/* Set the edges of the mapped_node of the selected node visible */ //TODO : too much ???
							//if(node.getMappedNode()!= null)
								//for(Map.Entry<Integer, Edge> edge : getGraph(node.getMappedNode().getGraphId()).getEdges().entrySet())
									//if(node.getMappedNode().equals(getGraph(node.getMappedNode().getGraphId()).getNode1(edge.getValue())))
										//edge.getValue().getRoad().setVisible(true);
										
							/* show the available Constructions for this node*/
							gui.showConstructions(graph.isCityGraph(), true);
						}
						else {//This node does not belong to the player
							/* show the available Constructions for this node*/
							gui.showConstructions(graph.isCityGraph(), false);
						}
						/* Show population */
						switch(node.getNodeLevel()) {
							case 1 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N1_IMAGE); break;
							case 2 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N2_IMAGE); break;
							case 3 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N3_IMAGE); break;
							case 4 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N4_IMAGE); break;
							case 5 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N5_IMAGE); break;
							case 6 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N6_IMAGE); break;
							case 7 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N7_IMAGE); break;
							case 8 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N8_IMAGE); break;
							case 9 : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N9_IMAGE); break;
							default : gui.setPopulation(node.getNodeLevel(), environment.getUILayer(), Const.N0_IMAGE); break;
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
	
	/**
	* Returns the graph object that has the given graphId
	* @param graphId - the unique integer id associated with the graph at the time of construction
	* @return the graph instance
	*/
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
	
	/**
	* sets all the edges invisible
	*/
	private void HideAllEdges() {
		HideEdges(cityGraphA);
		HideEdges(campGraphA);
		HideEdges(campGraphB);
		HideEdges(cityGraphB);
	}
	
	/**
	* sets the edges of the given graph invisible
	*/
	private void HideEdges(Graph graph) {
		for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet())
			edge.getValue().getRoad().setVisible(false);
	}
	
	/**
	* sets all the mappings invisible
	*/
	private void HideAllMappings() {
		HideMappings(cityGraphA);
		HideMappings(campGraphA);
		HideMappings(campGraphB);
		HideMappings(cityGraphB);
	}
	
	/**
	* sets the mappings of the nodes in the given graph invisible
	*/
	private void HideMappings(Graph graph) {
		for(Map.Entry<Integer, Node> node : graph.getNodes().entrySet())
			if(node.getValue().getMapping() != null) {
				node.getValue().getMapping().setVisible(false);
			}
	}
	
	/**
	* draws all the background tree imagesLayers
	*/
	private void plantTrees() {
		int number = 0;
		float tmpX = 0.0f;
		float tmpY = 0.0f;
		while(number < Const.MAX_TREE_NUMBER) {
			tmpX = r.nextFloat()*Const.WORLD_WIDTH;
            tmpY = r.nextFloat()*Const.WORLD_HEIGHT;
			if(isTreeSeperated(cityGraphA, tmpX, tmpY) & isTreeSeperated(campGraphA, tmpX, tmpY) &
				isTreeSeperated(campGraphB, tmpX, tmpY) & isTreeSeperated(cityGraphB, tmpX, tmpY)) {
				Tree tree = new Tree(environment.getGraphLayer(), tmpX, tmpY, Const.TREE1_IMAGE, Const.TREE1_SHADOW_IMAGE);
				/*switch(r.nextInt(4))
				{
					case 0 : tree = new Tree(environment.getGraphLayer(), tmpX, tmpY, Const.TREE1_IMAGE); break;
					case 1 : tree = new Tree(environment.getGraphLayer(), tmpX, tmpY, Const.TREE2_IMAGE); break;
					case 2 : tree = new Tree(environment.getGraphLayer(), tmpX, tmpY, Const.TREE3_IMAGE); break;
					case 3 : tree = new Tree(environment.getGraphLayer(), tmpX, tmpY, Const.TREE4_IMAGE); break;
					default : tree = new Tree(environment.getGraphLayer(), tmpX, tmpY, Const.TREE5_IMAGE); break;
				}*/
				trees.add(tree);
				number++;
			}
		}
	}
	
	/**
	* Checks if a tree is as far as a given distance from all the other already placed trees
	*/
	private boolean isTreeSeperated(Graph graph, float posX, float posY) {
        float distance = 0.0f;
        for(Map.Entry<Integer, Node> entry : graph.getNodes().entrySet()) {
			distance = entry.getValue().getPos().getDistanceFrom(new Tuple2f(posX, posY));
			if(distance < Const.MIN_NODE_TREE_DISTANCE)
				return false;
        }
		//for(Tree tree : trees) {
		//	distance = entry.getValue().getPos().getDistanceFrom(new Tuple2f(posX, posY));
		//	if(distance < Const.MIN_NODE_TREE_DISTANCE)
		//		return false;
		//}
        return true;
    }
	
	/**
	* paints all the trees
	*/
	private void paintTrees() {
		for(Tree tree : trees)
			tree.paint();
	}
}
