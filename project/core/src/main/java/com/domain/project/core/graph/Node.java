package com.domain.project.core.graph;

import java.util.List;
import java.util.ArrayList;

import playn.core.GroupLayer;

enum Nucleotide {A, ADENINE, T, THYMINE, U, URACIL, G, GUANINE, C, CYTOSINE};
    

public class Node {

	private Base base;

    private int id;
    private Nucleotide nucleotide;
    private List<Node> neighbors;
    private Tuple2f coordinates;
	
    public Node(int id, Nucleotide nucl) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        this.coordinates = new Tuple2f();
    }

    public Node(int id, Nucleotide nucl, boolean isCity, final GroupLayer graphLayer) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        this.coordinates = new Tuple2f();
		if(isCity)
			this.base = new City(graphLayer);
		else
			this.base = new Camp(graphLayer);
    }

    public void placeNode(float x, float y) {
        coordinates.x = x;
        coordinates.y = y;
		base.getBaseLayer().setScale(0.3f, 0.3f);
        base.getBaseLayer().setTranslation(coordinates.x, coordinates.y);
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }

    public int getID() {
        return id;
    }

    public Nucleotide getNucleotide() {
        return nucleotide;
    }

    @Override
    public String toString() {
        String ret = "Node ID: " + Integer.toString(id) + "(" + nucleotide + ") \t- Neighbors:";
        for(Node n : neighbors) {
            ret = ret + " " + Integer.toString(n.getID()) + "(" + n.getNucleotide() + ")";
        }
        return ret;
    }


}
