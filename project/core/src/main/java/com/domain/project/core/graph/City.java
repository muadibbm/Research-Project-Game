package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.GroupLayer;
import playn.core.ResourceCallback;

import com.domain.project.core.Const;

public class City extends Base
{
	private ImageLayer palace;
	private ImageLayer tavern;
	private ImageLayer bazar;
	private ImageLayer garden;
	private ImageLayer tower;
	private int tower_level;
	private ImageLayer wall;
	private int wall_level;
	private ImageLayer smithy;
	//TODO: how to position the city and its componants

	public City(GroupLayer graphLayer){
		super(graphLayer, Const.CITY_BASE_IMAGE);
		tower_level = 0;
	}
	
	@Override
	public void buildTower(final GroupLayer graphLayer, Image tower_image) {
		tower_level++;
		
		if(tower != null)
			tower.destroy();
		
		tower = graphics().createImageLayer(tower_image);
		tower.setDepth(Const.TOWER_DEPTH);
		
		tower_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                tower.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(tower);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getTowerLevel() {
		return tower_level;
	}
	
	@Override
	public void paint(float x, float y) {
		super.paint(x, y);
		if(tower!= null) {
			tower.setScale(Const.TOWER_SCALE, Const.TOWER_SCALE);
			tower.setTranslation(x + Const.TOWER_X, y + Const.TOWER_Y);
		}
	}
}