package com.domain.project.core.graph;

//import static playn.core.PlayN.*;

import com.domain.project.core.enums.EdgeType;
import com.domain.project.core.enums.Isomer;

import playn.core.GroupLayer;

public class Edge {
    
	private Road road;

    private int id; //line number in file
    private Isomer isomer;
    private int n1;
    private int n2;
    private EdgeType e1;
    private EdgeType e2;

	/**
	* Constructor of the Edge
	* @param id - the unique integer associated with this edge read from the raw data
	* @param isomer - type of Isomer read from raw data
	* @param n1 - the unique integer id of node1 read from raw data
	* @param n2 - the unique integer id of node2 read from raw data
	* @param e1 - the EdgeType read from raw data
	* @param e2 - the EdgeType read from raw data
	* @param graphLayer - the GroupLayer of the graph
	*/
    public Edge(int id, Isomer isomer, int n1, int n2, EdgeType e1, EdgeType e2, GroupLayer graphLayer) {
        this.id = id;
        this.n1 = n1;
        this.n2 = n2;
        this.isomer = isomer;
        this.e1 = e1;
        this.e2 = e2;
		road = new Road(graphLayer);
    }
    
    public Edge(GroupLayer graphLayer) {
        road = new Road(graphLayer);
    }
	
	/**
	* paints the road image for this edge
	*/
	public void paint() {
		road.paint();
		road.paintVisibility(road.isVisible());
	}

	/**
	* @return the unique integer id
	*/
    public int getID() {
        return id;
    }
	
	/**
	* @return the unique integer id of node1 of this edge
	*/
	public int getN1() {
		return n1;
	}
	
	/**
	* @return the unique integer id of node2 of this edge
	*/
	public int getN2() {
		return n2;
	}
	
	/**
	* @return the road instance which contains all the image for this edge
	*/
	public Road getRoad() {
		return road;
	}

    @Override
    public String toString() {
        return "Edge ID: " + id + "\t(" + isomer + e1 + e2 + " between " + n1 + " - " + n2 + ")";
    }

}
