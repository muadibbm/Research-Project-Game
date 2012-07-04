package com.domain.project.core.graph;



import java.util.List;
import java.util.ArrayList;

import playn.core.GroupLayer;

import com.domain.project.core.Const;
import com.domain.project.core.graph.Mapping;
import com.domain.project.core.enums.Nucleotide;

public class Node {

    private Base base;
	private GroupLayer graphLayer;

    private int id;
    private Nucleotide nucleotide;
    private List<Node> neighbors;
    private Tuple2f coordinates;
	
	private int graph_id;
	private int player_id;

    private boolean placed;
	
	private Node mapped_node;
	private Mapping mapping;

    public Node(int id, Nucleotide nucl) {
        this.id = id;
        this.nucleotide = nucl;
        neighbors = new ArrayList<Node>();
        coordinates = new Tuple2f();
        placed = false;
		mapped_node = null;
		mapping = null;
    }

    public Node(int id, Nucleotide nucl, boolean isCity, GroupLayer graphLayer, int graph_id, int player_id) {
        this.id = id;
        this.nucleotide = nucl;
        this.neighbors = new ArrayList<Node>();
        coordinates = new Tuple2f();
        placed = false;
		this.graphLayer = graphLayer;
        if(isCity)
            base = new City(this.graphLayer);
        else
            base = new Camp(this.graphLayer);
		this.graph_id = graph_id;
		mapped_node = null;
		mapping = null;
		this.player_id = player_id;
    }

	public void setMapping(Node node) {
		mapped_node = node;
		mapping = new Mapping(this.graphLayer, this.coordinates, mapped_node.getPos());
		mapped_node.setMapping(mapping);
	}
	
	private void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}
	
	public Mapping getMapping() {
		return mapping;
	}

	public void unMap() {
		mapped_node.removeMapping();
		mapping.destroy();
		removeMapping();
	}
	
	private void removeMapping() {
		this.mapped_node = null;
		this.mapping = null;
	}
	
	public Node getMappedNode() {
		return mapped_node;
	}
	
	public int getGraphId() {
		return graph_id;
	}
	
	public int getPlayer() {
		return player_id;
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
		base.setPopulation(neighbors.size());
	}
	
	public int getNodeLevel() {
		return base.getPopulation();
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
	
	public Base getBase() {
		return base;
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
