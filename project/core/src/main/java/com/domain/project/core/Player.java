package com.domain.project.core;

import com.domain.project.core.graph.Node;

/**
* The Class contains all the game logic required for the player 
*/
public class Player
{
	private int player_id;
	private String name;
	private int gold;
	private Node selected_node;
	private Node nodeToBeMapped;
	
	/**
	* Constructor of the Player
	* @param player_id - the unique integer associated with this player
	* @param name - the name of the player
	*/
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
		gold = 0;
		selected_node = null;
		nodeToBeMapped = null;
	}
	
	/**
	* @return the amount of gold the player has
	*/
	public int getGold() {
		return gold;
	}
	
	/**
	* Sets player's gold to the given amount
	* @param amount - integer
	*/
	public void setGold(int amount) {
		gold = amount;
	}
	
	/**
	* @return the unique integer id
	*/
	public int getId() {
		return player_id;
	}
	
	/**
	* @return the name of the player
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Sets the selected node to the given node
	* @param node - instance of Node
	*/
	public void selectNode(Node node) {
		selected_node = node;
	}
	
	/**
	* Sets the node to be mapping to the given node
	* @ node - instance of Node
	*/
	public void setNodeToBeMapped(Node node) {
		nodeToBeMapped = node;
	}
	
	/**
	* @return the node instance selected by the player
	*/
	public Node getSelectedNode() {
		return selected_node;
	}
	
	/**
	* @return the node instance the player has selected from mapping
	*/
	public Node getNodeToBeMapped() {
		return nodeToBeMapped;
	}
}