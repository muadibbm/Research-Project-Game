package com.domain.project.core;

import com.domain.project.core.graph.Node;

public class Player
{
	private int player_id;
	private String name;
	private Node selected_node;
	private Node nodeToBeMapped;
	
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
		selected_node = null;
		nodeToBeMapped = null;
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