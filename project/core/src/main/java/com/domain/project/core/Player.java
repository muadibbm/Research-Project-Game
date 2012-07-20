package com.domain.project.core;

import com.domain.project.core.graph.Node;

public class Player
{
	private int player_id;
	private String name;
	private int gold;
	private Node selected_node;
	private Node nodeToBeMapped;
	
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
		gold = 0;
		selected_node = null;
		nodeToBeMapped = null;
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold(int amount) {
		gold = amount;
	}
	
	public int getId() {
		return player_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void selectNode(Node node) {
		selected_node = node;
	}
	
	public void setNodeToBeMapped(Node node) {
		nodeToBeMapped = node;
	}
	
	public Node getSelectedNode() {
		return selected_node;
	}
	
	public Node getNodeToBeMapped() {
		return nodeToBeMapped;
	}
}