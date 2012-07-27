package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import java.lang.Integer;

import java.util.Map;
import java.util.HashMap;

import playn.core.GroupLayer;
import playn.core.ResourceCallback;

import com.domain.project.core.Const;
import com.domain.project.core.enums.Nucleotide;
import com.domain.project.core.enums.Isomer;
import com.domain.project.core.enums.EdgeType;

/**
* This class contains the nodes and edges of the graph read from the database
*/
public class Graph {

	private java.util.Random r = new java.util.Random();
	
    private final Map<Integer, Node> nodes;
    private final Map<Integer, Edge> edges;

    private final String PATH = "graph_data/";
    
    private float xOffset;
    private float yOffset;
    private float width;
    private float height;
    
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
	}

	/**
	* parses the file containing the raw graph data and create all the nodes and edges instances
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

                int nodeID, neighborID, lineNumber;
                int edgeID; //line number in file
                int data;

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

                    //parse second node (neighor) and assign neighbor
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
	
	/**
	* paints all the imageLayers in this graph instance
	*/
	public void paintAll() {
		for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			entry.getValue().paint();
			if(entry.getValue().getMapping() != null) {
				entry.getValue().getMapping().paint();
				entry.getValue().getMapping().paintVisibility(entry.getValue().getMapping().isVisible());
			}
		}
		for(Map.Entry<Integer, Edge> entry : edges.entrySet())
			entry.getValue().paint();
	}

    private void placeNodes() {
    //TODO: finish the placement algorithm
        float tmpX = 0;
        float tmpY = 0;
        for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            while(!entry.getValue().isPlaced())
            {
                tmpX = r.nextFloat()*this.width + this.xOffset;
                tmpY = r.nextFloat()*this.height + this.yOffset;
                if(this.isSeperated(new Tuple2f(tmpX, tmpY)))
                    entry.getValue().placeNode(tmpX, tmpY);
					
            }
			if(entry.getValue().getMapping() != null) {
				entry.getValue().getMapping().setVisible(entry.getValue().getMapping().isVisible());
				entry.getValue().getMapping().setScore(entry.getValue().getMapping().getScore());
			}
        }
    }
	
	private void setNodeLevels() {
		for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			entry.getValue().setNodeLevel();
		}
	}
    
    private void placeEdges() {
		Node n1;
        Node n2;
		//Road road;
        for(Map.Entry<Integer, Edge> entry : edges.entrySet()) {
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
        for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
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
		//System.out.println(distance);
        return true;
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
    
    @Override
    public String toString() {
        String ret = "";
        for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            ret = ret + entry.getValue() + "\n";
        }
        ret = ret + "Total number of nodes: " + nodes.size() + "\n\n";

        for(Map.Entry<Integer, Edge> entry : edges.entrySet()) {
            ret = ret + entry.getValue() + "\n";
        }
        ret = ret + "Total number of edges: " + edges.size();
        return ret;
    }

}
