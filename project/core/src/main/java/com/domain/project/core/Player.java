package com.domain.project.core;

import com.domain.project.core.graph.Node;

public class Player
{
	private int player_id;
	private String name;
	private Node [] selected_nodes;
	
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
		selected_nodes = new Node [2];
	}
	
	public int getId() {
		return player_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void selectNode(Node node) {
		selected_nodes[1] = selected_nodes[0];
		selected_nodes[0] = node;
	}
	
	public Node getSelectedNode1() {
		return selected_nodes[0];
	}
	
	public Node getSelectedNode2() {
		return selected_nodes[1];
	}
}