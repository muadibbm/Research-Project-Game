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
    private List<Road> roads;
    private Tuple2f coordinates;

    private boolean placed;

    public Node(int id, Nucleotide nucl) {
        this.id = id;
        this.nucleotide = nucl;
        neighbors = new ArrayList<Node>();
        roads = new ArrayList<Road>();
        coordinates = new Tuple2f();
        placed = false;
    }

    public Node(int id, Nucleotide nucl, boolean isCity, final GroupLayer graphLayer) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        roads = new ArrayList<Road>();
        coordinates = new Tuple2f();
        placed = false;
        if(isCity)
            base = new City(graphLayer);
        else
            base = new Camp(graphLayer);
    }

    public void placeNode(float x, float y) {
        this.setPos(x, y);
        base.getBaseLayer().setScale(0.3f, 0.3f);
        base.getBaseLayer().setTranslation(coordinates.x, coordinates.y);
        placed = true;
    }

    public void addRoad(Road road) {
        roads.add(road);
    }

    public void removeRoad(Road road) {
        roads.remove(road);
    }

    public boolean roadAlreadyExists(Road newRoad) {
        for(Road road : roads) {
            if(newRoad.equals(road))
                return true;
        }
        return false;
    }
    
    public boolean roadAlreadyExists(Node node) {
        for(Road road : roads) {
            if(node.roadAlreadyExists(road))
                return true;
        }
        return false;
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
