package com.domain.project.core.graph;

import playn.core.ImageLayer;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class Camp extends Base
{
	private ImageLayer tent;
	private ImageLayer barracks;
	private ImageLayer watchTower;
	//TODO: how to position the camp and its components

	public Camp(GroupLayer graphLayer){
		super(graphLayer, Const.CAMP_BASE_IMAGE);
	}
	
	//TODO: add setters and getters
}