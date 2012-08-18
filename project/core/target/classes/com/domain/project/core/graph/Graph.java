package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import java.lang.Integer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import com.domain.project.core.Const;
import com.domain.project.core.enums.EdgeType;
import com.domain.project.core.enums.Isomer;
import com.domain.project.core.enums.Nucleotide;

import playn.core.GroupLayer;
import playn.core.ResourceCallback;


/**
 * This class contains the nodes and edges of the graph read from the database
 */
public class Graph {

	private Random r = new Random();

	private final Map<Integer, Node> nodes;
	private final Map<Integer, Edge> edges;
	private final String PATH = "graph_data/";
	private float xOffset;
	private float yOffset;
	private float width;
	private float height;
	private ArrayList<Caravan> caravanList;
	private boolean isCityGraph;
	private int id;

	/**
	 * Constructs a graph instance 
	 * @param isCityGraph - defines if it is a graph of cities or camps
	 * @param xOffset - float value representing the top left origin of the graph on the x axis
	 * @param yOffset - float value representing the top left origin of the graph in the y axis
	 * @param widht - float value is the width of the graph
	 * @param height - float value is the height of the graph
	 * @param id - the unique integer value assigned to this instance
	 */
	public Graph(boolean isCityGraph, float xOffset, float yOffset, float width, float height, int id) {
		this.isCityGraph = isCityGraph;
		this.nodes = new HashMap<Integer, Node>();
		this.edges = new HashMap<Integer, Edge>();
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
		this.id = id;
		caravanList = new ArrayList<Caravan>();
	}

	/**
	 * Constructs a graph instance 
	 * @param filename - the path to the database file containing the graph raw data
	 * @param graphLayer - the GroupLayer that all the imageLayers in this graph instance are added to
	 * @param player_id - the unique integer value of the player assigned to this graph
	 */
	public void generateGraph(String filename, GroupLayer graphLayer, int player_id) {
		parseGraphFile(filename, graphLayer, player_id);
		placeNodes();
		setNodeLevels();
		placeEdges();		
	}

	/**
	 * @return the graph id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the list of nodes in this graph
	 */
	public Map<Integer, Node> getNodes() {
		return nodes;
	}

	/**
	 * @return the list of edges in this graph
	 */
	public Map<Integer, Edge> getEdges() {
		return edges;
	}

	/**
	 * updates all the positions of the nodes and edges in the graph
	 */
	public void updateAll() {
		placeNodes();
		placeEdges();
		movingCaravans();
	}

	/**
	 * parses the file containing the raw graph data and creates all the nodes and edges instances
	 */
	private void parseGraphFile(String filename, final GroupLayer graphLayer, final int player_id) {

		assets().getText(PATH + filename, new ResourceCallback<String>() {
			@Override
			public void done(String resource) {
				String[] entries = null;
				String[] subEntries = null;

				Node n1 = null;
				Node n2 = null;
				Nucleotide nucl = null;

				Edge e = null;
				String edgeComponents = null;
				Isomer iso = null;
				EdgeType et1 = null, et2 = null;

				int nodeID, neighborID;
				int edgeID; //line number in file

				entries = resource.split("\n");
				for(String s1 : entries) {
					if(s1.startsWith("%")) continue;

					s1 = s1.replaceAll("\\s+", "");
					s1 = s1.replaceAll("([0-9]*)([ATUGCatugc])([0-9]*)(\\([A-Z0-9]\\))-([ATUGCatugc])([0-9]*)(\\([A-Z0-9]\\))-([a-zA-Z]*)-([0-9]*)", "$1 $2 $3 $4 $5 $6 $7 $8 $9"); 
					//99 A 99 (A) A 99 (A) cWW 99
					subEntries = s1.split(" ");

					//parse edge
					edgeID = Integer.parseInt(subEntries[0]);
					edgeComponents = subEntries[7];
					switch(edgeComponents.charAt(0)) {
					case 'c':
						iso = Isomer.c;
						break;
					case 't':
						iso = Isomer.t;
						break;
					}
					switch(edgeComponents.charAt(1)) {
					case 'W':
						et1 = EdgeType.W;
						break;

					case 'w':
						et1 = EdgeType.w;
						break;

					case 'H':
						et1 = EdgeType.H;
						break;

					case 'h':
						et1 = EdgeType.h;
						break;

					case 'S':
						et1 = EdgeType.S;
						break;

					case 's':
						et1 = EdgeType.s;
						break;
					}
					switch(edgeComponents.charAt(2)) {
					case 'W':
						et2 = EdgeType.W;
						break;

					case 'w':
						et2 = EdgeType.w;
						break;

					case 'H':
						et2 = EdgeType.H;
						break;

					case 'h':
						et2 = EdgeType.h;
						break;

					case 'S':
						et2 = EdgeType.S;
						break;

					case 's':
						et2 = EdgeType.s;
						break;
					}

					nodeID = Integer.parseInt(subEntries[2]);
					neighborID = Integer.parseInt(subEntries[5]);

					e = new Edge(edgeID, iso, nodeID, neighborID, et1, et2, graphLayer);
					addEdge(e);

					//parse first node
					switch(subEntries[1].charAt(0)) {
					case 'A':
						nucl = Nucleotide.A;
						break;

					case 'T':
						nucl = Nucleotide.T;
						break;

					case 'U':
						nucl = Nucleotide.U;
						break;

					case 'G':
						nucl = Nucleotide.G;
						break;

					case 'C':
						nucl = Nucleotide.C;
						break;
					}

					//                    nodeID = Integer.parseInt(subEntries[2]);

					if(!contains(nodeID)) {
						n1 = new Node(nodeID, nucl, isCityGraph, graphLayer, id, player_id);
						addNode(n1);
					} else {
						n1 = getNode(nodeID);
					}

					//parse second node (neighbor) and assign neighbor
					switch(subEntries[4].charAt(0)) {
					case 'A':
						nucl = Nucleotide.A;
						break;

					case 'T':
						nucl = Nucleotide.T;
						break;

					case 'U':
						nucl = Nucleotide.U;
						break;

					case 'G':
						nucl = Nucleotide.G;
						break;

					case 'C':
						nucl = Nucleotide.C;
						break;
					}

					//                    neighborID = Integer.parseInt(subEntries[5]);

					if(!contains(neighborID)) {
						n2 = new Node(neighborID, nucl, isCityGraph, graphLayer, id, player_id);
						addNode(n2);
					} else {
						n2 = getNode(neighborID);
					}
					n1.addNeighbor(n2);

				}

			}

			@Override
			public void error(Throwable e) {
				log().error("error loading graph", e);
			}
		});
	}

	/** Paints all the imageLayers in this graph instance
	 */
	public void paintAll() {
		for(Entry<Integer, Node> entry : nodes.entrySet()) {
			entry.getValue().paint();
			if(entry.getValue().getMapping() != null) {
				entry.getValue().getMapping().paint();
				entry.getValue().getMapping().paintVisibility(entry.getValue().getMapping().isVisible());
			}
		}

		for(Entry<Integer, Edge> entry : edges.entrySet())
			entry.getValue().paint();
	}

	private void placeNodes() {
		float tempNodeX = 0;
		float tempNodeY = 0;
		ArrayList<Node> placedNodesList = new ArrayList<Node>();

		// First, place the nodes onto the graph
		for (Entry<Integer, Node> entry : nodes.entrySet()) {
			Node node = entry.getValue();

			while (!node.isPlaced()) {
				tempNodeX = r.nextFloat() * this.width + this.xOffset;
				tempNodeY = r.nextFloat() * this.height + this.yOffset;

				if (this.isSeperated(new Tuple2f(tempNodeX, tempNodeY))) {
					node.placeNode(tempNodeX, tempNodeY);
				}
			}
			placedNodesList.add(node);
		}
		/*
		// Second, reallocate any node which is on any edge
		for (Node node: placedNodesList) {
			float scaledWidth = node.getBase().getBaseLayer().scaledWidth() / 10;
			float scaledHeight = node.getBase().getBaseLayer().scaledHeight() / 10;
			boolean shouldContinue = true;

			while (shouldContinue) {
				for (Entry<Integer, Edge> entry: edges.entrySet()) {
					Edge edge = entry.getValue();
					Node node1 = getNode1(edge);
					Node node2 = getNode2(edge);

					// Check whether the node to compare with the edge does not equal any of the nodes of the edge
					if (!node.equals(node1) && !node.equals(node2)) {
						float x1 = getNode1(edge).getPos().getX();
						float y1 = getNode1(edge).getPos().getY();
						float x2 = getNode2(edge).getPos().getX();
						float y2 = getNode2(edge).getPos().getY();

						Line2D.Float line = new Line2D.Float(x1, y1, x2, y2);
						Rectangle2D.Float rectangle = new Rectangle2D.Float(node.getPos().getX(), node.getPos().getY(), scaledWidth, scaledHeight);

						// Check for an intersection exists between the current node and the current edge
						if (rectangle.intersectsLine(line)) {
							// If there is such in intersection, then move the current node to a new position and check against all edges, again
							tempNodeX = r.nextFloat() * this.width + this.xOffset;
							tempNodeY = r.nextFloat() * this.height + this.yOffset;
							node.placeNode(tempNodeX, tempNodeY);
							break;
						} else {
							shouldContinue = false;
						}
					}
				}
			}
		}*/
		for (Node node: placedNodesList) {
			for (Node node2: placedNodesList) {
				if (!node.equals(node2)) {
					float distance = node.getPos().getDistanceFrom(node2.getPos());
					if (isCityGraph) {
						if (distance < Const.MIN_CITY_DISTANCE) {
							tempNodeX = r.nextFloat() * this.width + this.xOffset;
							tempNodeY = r.nextFloat() * this.height + this.yOffset;
							node.placeNode(tempNodeX, tempNodeY);
						}
					} else if (distance < Const.MIN_CAMP_DISTANCE) {
						tempNodeX = r.nextFloat() * this.width + this.xOffset;
						tempNodeY = r.nextFloat() * this.height + this.yOffset;
						node.placeNode(tempNodeX, tempNodeY);
					}
				}
			}
			
			if (node.getMapping() != null) {
				node.getMapping().setVisible(node.getMapping().isVisible());
				node.getMapping().setScore(node.getMapping().getScore());
			}
		}
	}

	private void setNodeLevels() {
		for(Entry<Integer, Node> entry : nodes.entrySet()) {
			entry.getValue().setNodeLevel();
		}
	}

	private void placeEdges() {
		Node n1;
		Node n2;
		for(Entry<Integer, Edge> entry : edges.entrySet()) {
			if(!entry.getValue().getRoad().isPlaced()) {
				n1 = getNode1(entry.getValue());
				n2 = getNode2(entry.getValue());
				entry.getValue().getRoad().placeRoad(n1.getPos(), n2.getPos());
			}
			entry.getValue().getRoad().setVisible(entry.getValue().getRoad().isVisible());
		}
	}

	/**
	 * return the Node instance of the node1 of the given edge
	 * @param edge - a given Edge instance
	 * @return node - the Node instance
	 */
	public Node getNode1(Edge edge) {
		return getNode(edge.getN1());
	}

	/**
	 * return the Node instance of the node2 of the given edge
	 * @param edge - a given Edge instance
	 * @return node - the Node instance
	 */
	public Node getNode2(Edge edge) {
		return getNode(edge.getN2());
	}

	private boolean isSeperated(Tuple2f test_coordinates) {
		float distance = 0.0f;
		for(Entry<Integer, Node> entry : nodes.entrySet()) {
			if(entry.getValue().isPlaced())
			{
				distance = entry.getValue().getPos().getDistanceFrom(test_coordinates);
				if(isCityGraph)
				{
					if(distance < Const.MIN_CITY_DISTANCE)// || distance > Const.MAX_CITY_DISTANCE
						return false;
				}
				else
				{
					if(distance < Const.MIN_CAMP_DISTANCE)// || distance > Const.MAX_CAMP_DISTANCE
						return false;
				}
			}
		}
		return true;
	}

	private void movingCaravans() {
		for (Entry<Integer, Node> entry: nodes.entrySet()) {
			Node node = entry.getValue();
			if (node.getBase() instanceof City) {
				City c1 = (City) node.getBase(); 
				if (c1.hasBazaar()) {
					List<Node> neighbours = node.getNeighbors();
					for (Node neighbour: neighbours) {
						City c2 = (City) neighbour.getBase();
						if (c2.hasBazaar()) {
							if (!c1.hasCaravan() || !c2.hasCaravan()) {
								c1.setHasCaravan(true);
								c2.setHasCaravan(true);
								caravanList.add(new Caravan(node.getGraphLayer(), node.getPos(), neighbour.getPos(), node.getBase().getBaseLayer().scaledWidth() / 10));
							}
						}
					}
				}
			}
		}
	}

	private void addNode(Node n) {
		nodes.put(n.getID(), n);
	}

	private Node getNode(int nodeID) {
		return nodes.get(nodeID);
	}

	private void addEdge(Edge e) {
		edges.put(e.getID(), e);
	}

	private boolean contains(int id) {
		if(nodes.containsKey(id)) {
			return true;
		}
		return false;
	}

	/**
	 * @return true if this graph instance is a city type and false otherwise
	 */
	public boolean isCityGraph() {
		return isCityGraph;
	}

	public ArrayList<Caravan> getCaravanList() {
		return caravanList;
	}

	public void setCaravanList(ArrayList<Caravan> caravanList) {
		this.caravanList = caravanList;
	}

	@Override
	public String toString() {
		String ret = "";
		for (Entry<Integer, Node> entry : nodes.entrySet()) {
			ret = ret + entry.getValue() + "\n";
		}
		ret = ret + "Total number of nodes: " + nodes.size() + "\n\n";

		for (Entry<Integer, Edge> entry : edges.entrySet()) {
			ret = ret + entry.getValue() + "\n";
		}
		ret = ret + "Total number of edges: " + edges.size();
		return ret;
	}
}
