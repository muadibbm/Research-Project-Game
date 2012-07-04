package com.domain.project.core.graph;

import playn.core.ImageLayer;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class City extends Base
{
	private ImageLayer palace;
	private ImageLayer bazar;
	private ImageLayer garden;
	private ImageLayer tower;
	private ImageLayer wall;
	private ImageLayer smithy;
	//TODO: how to position the city and its componants

	public City(GroupLayer graphLayer){
		super(graphLayer, Const.CITY_BASE_IMAGE);
	}
	
	//TODO: add setters and getters
}