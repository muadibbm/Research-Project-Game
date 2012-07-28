package com.domain.project.core.graph;

import java.util.List;
import java.util.ArrayList;

import playn.core.GroupLayer;

import com.domain.project.core.graph.Mapping;
import com.domain.project.core.enums.Nucleotide;

/**
* Contains all the research-based data for a node and also the game logic required for every node
*/
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

	/**
	* Constructor of the Node
	* @param id - the unique integer associated with this node read from the raw data
	* @param nucl - type of Nucleotide read from raw data
	*/
    public Node(int id, Nucleotide nucleotide) {
        this.id = id;
        this.nucleotide = nucleotide;
        neighbors = new ArrayList<Node>();
        coordinates = new Tuple2f();
        placed = false;
		mapped_node = null;
		mapping = null;
    }

	/**
	* Constructor of the Node(Complete version)
	* @param id - the unique integer associated with this node read from the raw data
	* @param nucl - type of Nucleotide read from raw data
	* @param isCity - boolean value determining whether this node is city or camp
	* @param graphLayer - the GroupLayer of the graph
	* @param player_id - the unique integer value of the player assigned to this graph
	*/
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
    
    public GroupLayer getGraphLayer() {
		return graphLayer;
	}

	/**
	* Sets a mapping from this node to the given node
	* @param node - the node to be mapped to
	* @param score - the mapping score
	*/
	public void setMapping(Node node, int score) {
		mapped_node = node;
		mapped_node.setMappedNode(this);
		mapping = new Mapping(this.graphLayer, this.coordinates, mapped_node.getPos(), score);
		mapped_node.setMapping(mapping);
		propagateMapping(score-1);
	}
	
	/* Mapping Score Propagation Logic */
	//TODO add more gameplay scoring elements
	private void propagateMapping(int score) {
		for(Node neighbor : neighbors) {
			if(neighbor.getMapping() != null) {
				if(mapped_node!=null & neighbor.getMappedNode() != null) {
					if(neighbor.getMappedNode().isNeighbor(mapped_node))
						neighbor.getMapping().setScore(neighbor.getMapping().getScore()+score+1);
				}
				else
					neighbor.getMapping().setScore(neighbor.getMapping().getScore()+score);
			}
		}
	}
	
	private void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}
	
	/**
	* Returns the mapping this node has
	* @return mapping - the instance of Mapping
	*/
	public Mapping getMapping() {
		return mapping;
	}

	/**
	* Removes the existing mapping from this node to the given node
	* Note : If used on a node with no mapping will cause a NullPointerException
	*/
	public void unMap() {
		mapped_node.removeMapping();
		mapping.destroy();
		removeMapping();
	}
	
	private void removeMapping() {
		this.mapped_node = null;
		this.mapping = null;
	}
	
	/**
	* Returns the node which this instance is mapped to
	* @return mapped_node - the instance of Node
	*/
	public Node getMappedNode() {
		return mapped_node;
	}
	
	/**
	* Sets the mapped_node to be the given node
	* @param node - the instance of Node
	*/
	public void setMappedNode(Node node) {
		mapped_node = node;
	}
	
	/**
	* @return the graph id
	*/
	public int getGraphId() {
		return graph_id;
	}
	
	/**
	* @return the player id
	*/
	public int getPlayer() {
		return player_id;
	}

	/**
	* places the node at the given (x,y) coordinates and flags placed to true
	* @param x - float x coordinate
	* @param y - float y coordinate
	*/
    public void placeNode(float x, float y) {
        setPos(x, y);
		placed = true;
    }
	
	/**
	* paints all the images contained in this node
	*/
	public void paint() {
		base.paint(coordinates.getX(), coordinates.getY());
	}
    
	/**
	* @return true if node is place and false otherwise
	*/
    public boolean isPlaced() {
        return placed;
    }

	/**
	* Adds the specific node to the list of neighbors
	* @param n - instance of Node
	*/
    public void addNeighbor(Node n) {
        neighbors.add(n);
    }
    
	/**
	* @return list of neighbors of type List<Node>
	*/
    public List<Node> getNeighbors() {
        return neighbors;
    }
	
	/**
<<<<<<< HEAD
	* Automatically sets the population of this node to its degree
=======
	* @param node - of type Node
	* @return true if the given node is one of this node's neighbors
	*/
	public boolean isNeighbor(Node node) {
		return neighbors.contains(node);
	}
	
	
	/**
	* automaticly sets the population of this node to its degree
>>>>>>> 0c7824458e2950eacb20524019a47175f5a3d1a9
	*/
	public void setNodeLevel() {
		base.setPopulation(neighbors.size());
	}
	
	/**
	* @return the degree(population) of this node
	*/
	public int getNodeLevel() {
		return base.getPopulation();
	}
	
	/**
	* @return the unique integer id
	*/
    public int getID() {
        return id;
    }
    
	/**
	* @return the coordinates of this node of type Tuple2f
	*/
    public Tuple2f getPos() {
        return coordinates;
    }
    
	/**
	* Sets the coordinates of this node to the given values
	* @param x - float x coordinate
	* @param y - float y coordinate
	*/
    public void setPos(float x, float y) {
        coordinates.setX(x);
        coordinates.setY(y);
    }

	/**
	* @return the nucleotide value
	*/
    public Nucleotide getNucleotide() {
        return nucleotide;
    }
	
	/**
	* @return the base instance of this node
	*/
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
