package com.domain.project.core;

import com.domain.project.core.Const;

public class Player
{
	private int player_id;
	private String name;
	
	public Player(int player_id, String name) {
		this.player_id = player_id;
		this.name = name;
	}
	
	public int getId() {
		return player_id;
	}
	
	public String getName() {
		return name;
	}
}