package com.domain.project.core;

import com.domain.project.core.graph.Node;
import com.domain.project.core.graph.Army;

import java.util.List;
import java.util.ArrayList;

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
	
	private List <Army> armies;
	
	/**
	* Constructor of the Player
	* @param player_id - the unique integer associated with this player
	* @param name - the name of the player
	*/
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
		gold = Const.STARTING_GOLD;
		selected_node = null;
		nodeToBeMapped = null;
		armies = new ArrayList <Army> ();
	}
	
	//public Army getArmy(Army army) {
	//	return armies.get(army);
	//}
	
	public void removeArmy(Army army) {
		armies.remove(army);
	}
	
	public void addArmy(Army army) {
		armies.add(army);
		//addArmyListener(army);
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
	/*
	private void addArmyListener() {
		armyLayer.addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.SOLDIER_TENT_COST) {
										player.setGold(player.getGold()-Const.SOLDIER_TENT_COST);
										base.setSoldierTentLevel(base.getSoldierTentLevel() + 1);
										base.buildSoldierTent(graphLayer, Const.SOLDIER_TENT_LEVEL1);
										player.addArmy(new Army(graphLayer, base, Const.ARMY_DEPTH, Const.ARMY_ALPHA, Const.ARMY_LEVEL1, base.getBaseLayer().scaledWidth() / 10));
									}
								}
							}
						}
				}
			@Override
			public void onMouseMove(Mouse.MotionEvent event) {
				//TODO
			}
			@Override
			public void onMouseUp(Mouse.ButtonEvent event) {
				//TODO
			}
			@Override
			public void onMouseWheelScroll(Mouse.WheelEvent event) {
				//TODO
			}
		});
	}*/
}