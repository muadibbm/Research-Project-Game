package com.domain.project.core.graph;

import java.util.List;
import java.util.ArrayList;

import playn.core.GroupLayer;

import com.domain.project.core.Const;

enum Nucleotide {A, ADENINE, T, THYMINE, U, URACIL, G, GUANINE, C, CYTOSINE};

public class Node {

    private Base base;

    private int id;
    private Nucleotide nucleotide;
    private List<Node> neighbors;
    private Tuple2f coordinates;

    private boolean placed;

    public Node(int id, Nucleotide nucl) {
        this.id = id;
        this.nucleotide = nucl;
        neighbors = new ArrayList<Node>();
        coordinates = new Tuple2f();
        placed = false;
    }

    public Node(int id, Nucleotide nucl, boolean isCity, GroupLayer graphLayer) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        coordinates = new Tuple2f();
        placed = false;
        if(isCity)
            base = new City(graphLayer);
        else
            base = new Camp(graphLayer);
    }

    public void placeNode(float x, float y) {
        setPos(x, y);    
		placed = true;
    }
	
	public void paint() {
		base.paint(coordinates.x, coordinates.y);
	}
    
    public boolean isPlaced() {
        return placed;
    }

    public void addNeighbor(Node n) {
        neighbors.add(n);
    }
    
    public List<Node> getNeighbors() {
        return neighbors;
    }
	
	public void setNodeLevel() {
		base.setLevel(neighbors.size());
	}

    public int getID() {
        return id;
    }
    
    public Tuple2f getPos() {
        return coordinates;
    }
    
    public void setPos(float x, float y) {
        coordinates.x = x;
        coordinates.y = y;
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
