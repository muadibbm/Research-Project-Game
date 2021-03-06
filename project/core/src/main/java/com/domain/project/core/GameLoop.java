package com.domain.project.core;

import static playn.core.PlayN.keyboard;
import static playn.core.PlayN.mouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.domain.project.core.controls.KeyboardControls;
import com.domain.project.core.controls.MouseControls;
import com.domain.project.core.graph.Camp;
import com.domain.project.core.graph.Caravan;
import com.domain.project.core.graph.City;
import com.domain.project.core.graph.Deeve;
import com.domain.project.core.graph.Edge;
import com.domain.project.core.graph.Graph;
import com.domain.project.core.graph.Node;
import com.domain.project.core.graph.Road;
import com.domain.project.core.graph.Tree;
import com.domain.project.core.graph.Tuple2f;

import playn.core.Game;
import playn.core.Mouse;


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
	//	private Gui gui2;

	private Random r = new Random();
	private List <Tree> trees;
	private boolean isTreeUpdated = false;
	private boolean isTreePainted = false;

	private ArrayList<Tuple2f> deeveMovesList;
	private ArrayList<Deeve> deeveList;
	
	//private float mouseX;
	//private float mouseY;
	//private Road mouseRoad;

	/**
	 * Initializes all the game variables before starting the game.
	 * @see http://docs.playn.googlecode.com/git/javadoc/index.html
	 */
	@Override
	public void init() { 

		Const.loadImages();
		environment = new Environment();
		trees = new ArrayList<Tree> ();
		player1 = new Player(1, "player 1");
		gui1 = new Gui(environment.getUILayer());
		player2 = new Player(2, "player 2");
		//TODO : gui2 = new Gui(environment.getUILayer());

		//TODO : read two graphs from database and put into these 4 graph instances

		cityGraphA = new Graph(true, Const.WORLD_WIDTH/7, Const.graphYOffset, Const.WORLD_WIDTH/7, Const.CITY_GRAPH_HEIGHT, 1);
		cityGraphA.generateGraph(graphA, environment.getGraphLayer(), player1.getId());
		campGraphA = new Graph(false, 2*Const.WORLD_WIDTH/7 + Const.campCityGraphGap, 2*Const.graphYOffset, Const.WORLD_WIDTH/7, Const.CAMP_GRAPH_HEIGHT-Const.graphYOffset, 2);
		campGraphA.generateGraph(graphB, environment.getGraphLayer(), player1.getId());

		campGraphB = new Graph(false, 4*Const.WORLD_WIDTH/7 - Const.campCityGraphGap, 2*Const.graphYOffset, Const.WORLD_WIDTH/7, Const.CAMP_GRAPH_HEIGHT-Const.graphYOffset, 3);
		campGraphB.generateGraph(graphA, environment.getGraphLayer(), player2.getId());
		cityGraphB = new Graph(true, 5*Const.WORLD_WIDTH/7, Const.graphYOffset, Const.WORLD_WIDTH/7, Const.CITY_GRAPH_HEIGHT, 4);
		cityGraphB.generateGraph(graphB, environment.getGraphLayer(), player2.getId());

		//environment.getGraphLayer().setScale(0.5f,0.5f);

		//create and set controls
		kbControls = new KeyboardControls(environment);
		keyboard().setListener(kbControls);
		mControls = new MouseControls(environment);
		mouse().setListener(mControls);
		gui1.addListener(player1, environment.getGraphLayer());
		gui1.showConstructions(player1.getSelectedNode(), false, false);
		/*TODO : gui2.addListener(player2, environment.getGraphLayer());
		gui2.showConstructions(false, false); */
		addAllListeners(cityGraphA, player1, gui1);
		addAllListeners(campGraphA, player1, gui1);
		addAllListeners(campGraphB, player1, gui1);
		addAllListeners(cityGraphB, player1, gui1);

		// This is just for testing. The Deeves might not be created here.
		deeveMovesList = new ArrayList<Tuple2f>();
		deeveList = new ArrayList<Deeve>();
		deeveMovesList.add(new Tuple2f(environment.getDeeveCaveLayer().transform().tx() + environment.getDeeveCaveLayer().scaledWidth() / 2, environment.getDeeveCaveLayer().transform().ty() + environment.getDeeveCaveLayer().scaledHeight()));
		deeveMovesList.add(new Tuple2f(environment.getTreeOfLifeLayer().transform().tx() + environment.getTreeOfLifeLayer().scaledWidth() / 2, environment.getTreeOfLifeLayer().transform().ty() + environment.getTreeOfLifeLayer().scaledHeight()));
		deeveList.add(new Deeve(environment.getGraphLayer(), deeveMovesList));
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
		if (isTreeUpdated & !isTreePainted) {
			paintTrees();// <-- for html it has to be here
			isTreePainted = true;
		}
		gui1.setGold(player1.getGold());
	}

	/**
	 * All the updates to the game logic should be called here.
	 * @param delta - time, in ms, passed since the previous update(float)
	 * @see http://docs.playn.googlecode.com/git/javadoc/index.html
	 */
	@Override
	public void update(float delta) {

		//System.out.println(Const.WORLD_WIDTH + " " + Const.WORLD_HEIGHT);
		//TODO
		//System.out.println("Mx : " + mControls.getX());
		//System.out.println("My : " + mControls.getY());
		//System.out.println("new Mx : " + mControls.getX()*Const.WORLD_WIDTH/Const.WINDOW_WIDTH);
		//System.out.println("new My : " + mControls.getY()*Const.WORLD_HEIGHT/Const.WINDOW_HEIGHT);
		/*
		System.out.println("ENVx : " + environment.getX());
		System.out.println("ENVy : " + environment.getY());
		*/
		kbControls.parse(mControls.getX(), mControls.getY());
		environment.update(delta);
		cityGraphA.updateAll();
		cityGraphB.updateAll();
		campGraphA.updateAll();
		campGraphB.updateAll();
		/* gold test */
		for (Caravan caravan: cityGraphA.getCaravanList()) {
			if (caravan.hasArrived()) {
				caravan.setHasArrived(false);

				switch (caravan.getCaravanLevel()) {
				case 1:
					player1.setGold(player1.getGold() + 10);
					break;
				case 2:
					player1.setGold(player1.getGold() + 15);
					break;
				case 3:
					player1.setGold(player1.getGold() + 20);
					break;
				}
				caravan.swapDestination();
			}
		}

		for (Deeve deeve: deeveList) {
			if (deeve.hasArrived()) {
				deeve.setHasArrived(false);
			}
		}

		if (!isTreeUpdated & cityGraphA.isAllPlaced() & cityGraphB.isAllPlaced() & campGraphA.isAllPlaced() & campGraphB.isAllPlaced()) {
			plantTrees(0, 0, 
					cityGraphA.getWidth()+Const.WORLD_WIDTH/7, Const.WORLD_HEIGHT, Const.MAX_TREE_NUMBER);
			plantTrees(cityGraphB.getX(), 0,
					cityGraphB.getWidth()+Const.WORLD_WIDTH/7, Const.WORLD_HEIGHT, Const.MAX_TREE_NUMBER);
			plantTrees(campGraphA.getX()-Const.campCityGraphGap, 0, 
					campGraphA.getWidth()/2, Const.WORLD_HEIGHT, Const.MAX_TREE_NUMBER/10);
			plantTrees(campGraphB.getX()+campGraphB.getWidth()/2+Const.campCityGraphGap, 0, 
					campGraphB.getWidth()/2, Const.WORLD_HEIGHT, Const.MAX_TREE_NUMBER/10);	
			//plantTrees(0, Const.WORLD_HEIGHT, Const.WORLD_WIDTH, Const.WORLD_HEIGHT, Const.MAX_TREE_NUMBER*2);
			//plantTrees(Const.WORLD_WIDTH, 0, Const.WORLD_WIDTH, Const.WORLD_HEIGHT, Const.MAX_TREE_NUMBER*2);
			isTreeUpdated = true;
		}
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
	//TODO : add Networking, where does the players interactions diverge ?

	/** 
	 * Contains any interaction with the layers in the game's graphs and the corresponding game logic. 
	 * TODO : This does not affect paint method in html version of the game.
	 * @param graph - the associated graph object
	 * @param player - the associated player with the mouse click
	 * @param gui - the user interface corresponding to the player
	 */
	private void addAllListeners(final Graph graph, final Player player, final Gui gui) {
		for(Map.Entry<Integer, Node> entry: graph.getNodes().entrySet()) {
			final Node node = entry.getValue();
			node.getBase().getBaseLayer().addListener(new Mouse.LayerListener() {
				Node hover_node = null;
				@Override //Called when the mouse is pressed.
				public void	onMouseDown(Mouse.ButtonEvent event) {
					if (event.button() == Mouse.BUTTON_LEFT) {
						/* Hide previous selections */
						HideAllNodes();
						HideAllEdges();
						HideAllMappings();
						/* Select New Node and set new neighbors visible */
						player.selectNode(node);
						node.getBase().getBaseLayer().setAlpha(Const.SELECTED_BASE_ALPHA);
						for(Node neigbor : node.getNeighbors())
							neigbor.getBase().getBaseLayer().setAlpha(Const.SELECTED_BASE_ALPHA);
						/* Set all edges of the selected node visible */
						for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
							if (node.equals(graph.getNode1(edge.getValue()))) {
								edge.getValue().getRoad().setVisible(true);
								node.getBase().positionSelection(node.getPos());
								node.getBase().setSelection(true);
							}
						}

						if (node.getPlayer() == player.getId()) {
							/* Create Mapping if a Node is already set to be mapped */
							if (player.getNodeToBeMapped() != null) {
								if (player.getId() == player.getSelectedNode().getPlayer() & player.getSelectedNode().getMapping() == null) {
									if (player.getSelectedNode().getBase() instanceof City & player.getNodeToBeMapped().getBase() instanceof Camp) {
										/* Inefficient mapping occurs when the city population is less than the capacity of a camp, thus the city lacks the required resources for the camp
											mapping score : 0 , mapping propagation score : -1 */
										if (player.getSelectedNode().getNodeLevel() < player.getNodeToBeMapped().getNodeLevel()) {
											player.getSelectedNode().setMapping(player.getNodeToBeMapped(), 0);
										}
										/* Acceptable mapping occurs when the city population is more than the capacity of a camp, thus the has the resources for the camp but the camp is too small for the city
											mapping score : 1 , mapping propagation score : 0 */
										else if (player.getSelectedNode().getNodeLevel() > player.getNodeToBeMapped().getNodeLevel()) {
											player.getSelectedNode().setMapping(player.getNodeToBeMapped(), 1);
										}
										/* Efficient mapping occurs when the city population is exactly matches the capacity of a camp
											mapping score : 2 , mapping propagation score : 1 */
										else {
											player.getSelectedNode().setMapping(player.getNodeToBeMapped(), 2);
										}
									} else if (player.getSelectedNode().getBase() instanceof Camp & player.getNodeToBeMapped().getBase() instanceof City) {
										/* Inefficient mapping occurs when the city population is less than the capacity of a camp, thus the city lacks the required resources for the camp
											mapping score : 0 , mapping propagation score : -1 */
										if (player.getNodeToBeMapped().getNodeLevel() < player.getSelectedNode().getNodeLevel()) {
											player.getNodeToBeMapped().setMapping(player.getSelectedNode(), 0);
										}
										/* Acceptable mapping occurs when the city population is more than the capacity of a camp, thus the has the resources for the camp but the camp is too small for the city
											mapping score : 1 , mapping propagation score : 0 */
										else if (player.getNodeToBeMapped().getNodeLevel() > player.getSelectedNode().getNodeLevel()) {
											player.getNodeToBeMapped().setMapping(player.getSelectedNode(), 1);
										}
										/* Efficient mapping occurs when the city population is exactly matches the capacity of a camp
											mapping score : 2 , mapping propagation score : 1 */
										else {
											player.getNodeToBeMapped().setMapping(player.getSelectedNode(), 2);
										}
									}
								}//else --> Andrey remove drag mapping indication HERE <-- 
								//System.out.println("remove graphical indication");
								//TODO : remove graphical indication (not yet added)
								player.setNodeToBeMapped(null);
							}
							/* Set the mapping of the selected node visible */
							if (node.getMapping() != null)
								node.getMapping().setVisible(true);

							/* Set the edges of the mapped_node of the selected node visible */ //TODO : too much ???
							//if (node.getMappedNode()!= null)
							//for(Map.Entry<Integer, Edge> edge : getGraph(node.getMappedNode().getGraphId()).getEdges().entrySet())
							//if (node.getMappedNode().equals(getGraph(node.getMappedNode().getGraphId()).getNode1(edge.getValue())))
							//edge.getValue().getRoad().setVisible(true);

							/* show the available Constructions for this node*/
							gui.showConstructions(player.getSelectedNode(), graph.isCityGraph(), true);
						} else {//This node does not belong to the player
							/* show the available Constructions for this node*/
							gui.showConstructions(player.getSelectedNode(), graph.isCityGraph(), false);
							//System.out.println("remove graphical indication");
							//TODO : remove graphical indication (not yet added)
							player.setNodeToBeMapped(null);
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
					else if (event.button() == Mouse.BUTTON_RIGHT) {
						/* Hide previous selections */
						HideAllNodes();
						HideAllEdges();
						HideAllMappings();
						/* Select New Node and set new neighbors visible */
						player.selectNode(node);
						node.getBase().getBaseLayer().setAlpha(Const.SELECTED_BASE_ALPHA);
						for(Node neigbor : node.getNeighbors())
							neigbor.getBase().getBaseLayer().setAlpha(Const.SELECTED_BASE_ALPHA);
						/* Set all edges of the selected node visible */
						for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
							if (node.equals(graph.getNode1(edge.getValue()))) {
								edge.getValue().getRoad().setVisible(true);
								if (player.getSelectedNode().getMapping() != null) {
									node.getBase().positionSelection(node.getPos());
									node.getBase().setSelection(true);
								} else {
									node.getBase().positionMappingSelection(node.getPos());
									node.getBase().setMappingSelection(true);
								}
							}
						}

						if (player.getId() == player.getSelectedNode().getPlayer()) {
							//remove the previous node to be mapped
							player.setNodeToBeMapped(null);

							if (player.getSelectedNode().getMapping() != null) {
								if (player.getSelectedNode().getBase() instanceof City)
									player.getSelectedNode().unMap(player.getSelectedNode().getMapping().getScore());
								else if (player.getSelectedNode().getBase() instanceof Camp)
									player.getSelectedNode().getMappedNode().unMap(player.getSelectedNode().getMapping().getScore());
							} else if (player.getNodeToBeMapped() == null) {
								if (player.getSelectedNode().getMapping() == null) {
									player.setNodeToBeMapped(player.getSelectedNode());
									//TODO : add some graphical indication - Andrey add drag mapping HERE <--
									//mouseRoad = new Road(environment.getGraphLayer());
									//mouseRoad.placeRoad(player.getSelectedNode().getPos(), new Tuple2f(mouseX, mouseY));
									//mouseRoad.setVisible(true);
									//mouseRoad.paint();
									//mouseRoad.paintVisibility(true);
								}
							}
							/* Set the mapping of the selected node visible */
							if (node.getMapping() != null)
								node.getMapping().setVisible(true);

							/* show the available Constructions for this node*/
							gui.showConstructions(player.getSelectedNode(), graph.isCityGraph(), true);

						} else {//This node does not belong to the player
							/* show the available Constructions for this node*/
							gui.showConstructions(player.getSelectedNode(), graph.isCityGraph(), false);
							//TODO : remove the graphical indication - Andrey add removal drag mapping HERE <--
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

				@Override //Called when the mouse is dragged.
				public void	onMouseDrag(Mouse.MotionEvent event) {

				}

				@Override //Called when the mouse leaves a Layer.
				public void	onMouseOut(Mouse.MotionEvent event) {
					if (hover_node != null) {
						if ((player.getSelectedNode() == null &  player.getNodeToBeMapped() == null) || 
								(player.getSelectedNode()!=null & player.getSelectedNode() != hover_node) || 
								(player.getNodeToBeMapped()!=null & player.getNodeToBeMapped()!=hover_node)) {
							hover_node.getBase().getBaseLayer().setAlpha(Const.BASE_ALPHA);
							for(Node neigbor : node.getNeighbors())
								if(player.getSelectedNode()!=neigbor & player.getNodeToBeMapped()!=neigbor)
									neigbor.getBase().getBaseLayer().setAlpha(Const.BASE_ALPHA);
							/* Set all edges of the selected node visible */
							for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
								if (node.equals(graph.getNode1(edge.getValue()))) {
									edge.getValue().getRoad().setVisible(false);
									node.getBase().setHoverSelection(false);
								}
							}
							hover_node = null;
							gui.hidePopulation();
						}
					}
					if (player.getSelectedNode()!=null)
						/* Show population */
						switch(player.getSelectedNode().getNodeLevel()) {
						case 1 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N1_IMAGE); break;
						case 2 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N2_IMAGE); break;
						case 3 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N3_IMAGE); break;
						case 4 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N4_IMAGE); break;
						case 5 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N5_IMAGE); break;
						case 6 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N6_IMAGE); break;
						case 7 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N7_IMAGE); break;
						case 8 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N8_IMAGE); break;
						case 9 : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N9_IMAGE); break;
						default : gui.setPopulation(player.getSelectedNode().getNodeLevel(), environment.getUILayer(), Const.N0_IMAGE); break;
						}
					if (player.getNodeToBeMapped() != null)
						/* Show population */
						switch(player.getNodeToBeMapped().getNodeLevel()) {
						case 1 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N1_IMAGE); break;
						case 2 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N2_IMAGE); break;
						case 3 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N3_IMAGE); break;
						case 4 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N4_IMAGE); break;
						case 5 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N5_IMAGE); break;
						case 6 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N6_IMAGE); break;
						case 7 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N7_IMAGE); break;
						case 8 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N8_IMAGE); break;
						case 9 : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N9_IMAGE); break;
						default : gui.setPopulation(player.getNodeToBeMapped().getNodeLevel(), environment.getUILayer(), Const.N0_IMAGE); break;
						}
				}
				@Override //Called when the mouse enters a Layer.
				public void	onMouseOver(Mouse.MotionEvent event) {
					//mouseX = event.x();
					//mouseY = event.y();
					//System.out.println(mouseX + ", " + mouseY);
					
					if ((player.getSelectedNode() == null &  player.getNodeToBeMapped() == null) || 
							(player.getSelectedNode() != null & player.getSelectedNode()!=node) ||
							(player.getNodeToBeMapped()!=null & player.getNodeToBeMapped()!=node)) {
						hover_node = node;
						/* Set the node and neighbors visible */
						node.getBase().getBaseLayer().setAlpha(Const.SELECTED_BASE_ALPHA);
						for(Node neigbor : node.getNeighbors())
							neigbor.getBase().getBaseLayer().setAlpha(Const.SELECTED_BASE_ALPHA);
						/* Set all edges of the selected node visible */
						for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
							if (node.equals(graph.getNode1(edge.getValue()))) {
								edge.getValue().getRoad().setVisible(true);
								node.getBase().positionHoverSelection(node.getPos());
								node.getBase().setHoverSelection(true);
							}
						}
						/* Set the mapping of the selected node visible */
						if (node.getMapping() != null)
							node.getMapping().setVisible(true);

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
				@Override //Called when the mouse is released.
				public void	onMouseUp(Mouse.ButtonEvent event) {
					deeveList.add(new Deeve(environment.getGraphLayer(), deeveMovesList));
				}
				@Override //Called when the mouse is scrolled.
				public void	onMouseWheelScroll(Mouse.WheelEvent event) {
				
				}
			});
		}
	}

	/**
	 * Returns the graph object that has the given graphId
	 * @param graphId - the unique integer id associated with the graph at the time of construction
	 * @return the graph instance
	 */
	/*private Graph getGraph(int graphId) {
		if (cityGraphA.getId() == graphId)
			return cityGraphA;
		else if (campGraphA.getId() == graphId)
			return campGraphA;
		else if (cityGraphB.getId() == graphId)
			return cityGraphB;
		else if (campGraphB.getId() == graphId)
			return campGraphB;
		else
			return null; //Is this safe ? may cause bug !!
	}*/

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
		for(Map.Entry<Integer, Edge> edge : graph.getEdges().entrySet()) {
			edge.getValue().getRoad().setVisible(false);
		}
	}

	/**
	 * sets all the nodes invisible
	 */
	private void HideAllNodes() {
		HideNodes(cityGraphA);
		HideNodes(campGraphA);
		HideNodes(campGraphB);
		HideNodes(cityGraphB);
	}

	/**
	 * sets the edges of the given nodes invisible
	 */
	private void HideNodes(Graph graph) {
		for(Map.Entry<Integer, Node> node : graph.getNodes().entrySet())
			node.getValue().getBase().getBaseLayer().setAlpha(Const.BASE_ALPHA);
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
		for(Map.Entry<Integer, Node> node : graph.getNodes().entrySet()) {
			if (node.getValue().getMapping() != null) {
				node.getValue().getMapping().setVisible(false);
			}
			node.getValue().getBase().setSelection(false);
			node.getValue().getBase().setMappingSelection(false);
			node.getValue().getBase().setHoverSelection(false);
		}
	}

	/**
	 * draws all the background tree imagesLayers
	 */
	private void plantTrees(float x, float y, float w, float h, int max_number) {
		int number = 0;
		float tmpX = 0.0f;
		float tmpY = 0.0f;
		while(number < max_number) {
			tmpX = r.nextFloat()*w + x;
			tmpY = r.nextFloat()*h + y;
			if (isTreeSeperated(cityGraphA, tmpX, tmpY) & isTreeSeperated(campGraphA, tmpX, tmpY) &
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
			if (distance < Const.MIN_NODE_TREE_DISTANCE)
				return false;
		}
		//for(Tree tree : trees) {
		//	distance = entry.getValue().getPos().getDistanceFrom(new Tuple2f(posX, posY));
		//	if (distance < Const.MIN_NODE_TREE_DISTANCE)
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