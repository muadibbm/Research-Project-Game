package com.domain.project.core.graph;

//import static playn.core.PlayN.*;

import playn.core.GroupLayer;

import com.domain.project.core.enums.Isomer;
import com.domain.project.core.enums.EdgeType;

public class Edge {
    
	private Road road;

    private int id; //line number in file
    private Isomer isomer;
    private int n1;
    private int n2;
    private EdgeType e1;
    private EdgeType e2;

    public Edge(int id, Isomer isomer, int n1, int n2, EdgeType e1, EdgeType e2, GroupLayer graphLayer) {
        this.id = id;
        this.n1 = n1;
        this.n2 = n2;
        this.isomer = isomer;
        this.e1 = e1;
        this.e2 = e2;
		road = new Road(graphLayer);
    }
	
	public void paint() {
		road.paint();
		road.paintVisibility(road.isVisible());
	}

    public int getID() {
        return id;
    }
	
	public int getN1() {
		return n1;
	}
	
	public int getN2() {
		return n2;
	}
	
	public Road getRoad() {
		return road;
	}

    @Override
    public String toString() {
        return "Edge ID: " + id + "\t(" + isomer + e1 + e2 + " between " + n1 + " - " + n2 + ")";
    }

}
