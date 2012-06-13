package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import java.lang.Integer;

import java.util.Map;
import java.util.HashMap;

import playn.core.GroupLayer;
import playn.core.ResourceCallback;

import com.domain.project.core.Const;

public class Graph {

    private final Map<Integer, Node> nodes;
    private final Map<Integer, Edge> edges;

    private final String PATH = "graph_data/";
	
	private float xOffset;
	private float yOffset;
	private float width;
	private float height;
	
	private boolean isCityGraph;

    public Graph(boolean isCityGraph, float xOffset, float yOffset, float width, float height) {
		this.isCityGraph = isCityGraph;
		this.nodes = new HashMap<Integer, Node>();
        this.edges = new HashMap<Integer, Edge>();
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
    }

    public void generateGraph(String filename, GroupLayer graphLayer) {
        parseGraphFile(filename, graphLayer);
        placeNodes();
    }

    private void parseGraphFile(String filename, final GroupLayer graphLayer) {

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
                    s1 = s1.replaceAll("([0-9]*)([ATUGC])([0-9]*)(\\([A-Z0-9]\\))-([ATUGC])([0-9]*)(\\([A-Z0-9]\\))-([a-zA-Z]*)-([0-9]*)", "$1 $2 $3 $4 $5 $6 $7 $8 $9"); 
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
                    e = new Edge(edgeID, iso, et1, et2);
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

                    nodeID = Integer.parseInt(subEntries[2]);

                    if(!contains(nodeID)) {
						n1 = new Node(nodeID, nucl, isCityGraph, graphLayer);
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

                    neighborID = Integer.parseInt(subEntries[5]);

                    if(!contains(neighborID)) {
                        n2 = new Node(neighborID, nucl, isCityGraph, graphLayer);
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

    public void placeNodes() {
	//TODO: finish the placement algorithm
        java.util.Random r = new java.util.Random();
		float tmpX;
		float tmpY;
        for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
			while(!entry.getValue().isPlaced())
			{
				tmpX = r.nextFloat()*this.width + this.xOffset;
				tmpY = r.nextFloat()*this.height + this.yOffset;
				if(this.isSeperated(entry.getValue(), new Tuple2f(tmpX, tmpY)))
					entry.getValue().placeNode(tmpX+this.xOffset, tmpY+this.yOffset);
			}
        }
    }
	
	public void drawRoads() {
		//TODO
	}
	
	//TODO : check for all nodes not only neighbors
	private boolean isSeperated(Node node, Tuple2f test_coordinates) {
		float distance;
		for(Node neighbour : node.getNeighbors()) {
			distance = neighbour.getPos().getDistanceFrom(test_coordinates);
			if((isCityGraph & distance < Const.MIN_CITY_DISTANCE) || (!isCityGraph & distance < Const.MIN_CAMP_DISTANCE))
				return false;
		}
		return true;
	}

    public void addNode(Node n) {
        nodes.put(n.getID(), n);
    }

    public Node getNode(int nodeID) {
        return nodes.get(nodeID);
    }

    public void addEdge(Edge e) {
        edges.put(e.getID(), e);
    }

    public boolean contains(int id) {
        if(nodes.containsKey(id)) {
            return true;
        }
        return false;
    }

	public boolean isCityGraph() {
		return isCityGraph;
	}
	
    @Override
    public String toString() {
        String ret = "";
        for(Map.Entry<Integer, Node> entry : nodes.entrySet()) {
            ret = ret + entry.getValue() + "\n";
        }
        ret = ret + "Total number of nodes: " + nodes.size() + "\n";
        ret = ret + "Total number of edges: " + edges.size();
        return ret;
    }

}
