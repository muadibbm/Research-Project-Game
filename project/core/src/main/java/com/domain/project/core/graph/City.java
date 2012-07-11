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
	private int city_level;
	//TODO : add houses
	
	//private ImageLayer tavern;
	//private int tavern_level;
	
	private ImageLayer bazar_food;
	private ImageLayer bazar_china;
	private ImageLayer bazar_carpet;
	private int bazar_level;
	
	private ImageLayer garden;
	private int garden_level;
	
	private ImageLayer tower1;
	private ImageLayer tower2;
	private int tower_level;
	
	private ImageLayer wall;
	private int wall_level;
	
	private ImageLayer smithy;
	private int smithy_level;

	public City(GroupLayer graphLayer){
		super(graphLayer, Const.CITY_BASE_IMAGE);
		city_level = 0;
		bazar_level = 0;
		garden_level = 0;
		tower_level = 0;
		wall_level = 0;
		smithy_level = 0;
	}
	
	@Override
	public void buildPalace(final GroupLayer graphLayer, Image palace_image) {
		if(palace != null)
			palace.destroy();
		
		palace = graphics().createImageLayer(palace_image);
		palace.setDepth(Const.PALACE_DEPTH);
		
		palace_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                palace.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(palace);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getCityLevel() {
		return city_level;
	}
	
	@Override
	public void setCityLevel(int level) {
		city_level = level;
	}
	
	@Override
	public void buildBazarFood(final GroupLayer graphLayer, Image bazar_food_image) {
		if(bazar_food != null)
			bazar_food.destroy();
		
		bazar_food = graphics().createImageLayer(bazar_food_image);
		bazar_food.setDepth(Const.BAZAR_FOOD_DEPTH);
		
		bazar_food_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                bazar_food.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(bazar_food);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public void buildBazarChina(final GroupLayer graphLayer, Image bazar_china_image) {
		if(bazar_china != null)
			bazar_china.destroy();
		
		bazar_china = graphics().createImageLayer(bazar_china_image);
		bazar_china.setDepth(Const.BAZAR_CHINA_DEPTH);
		
		bazar_china_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                bazar_china.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(bazar_china);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public void buildBazarCarpet(final GroupLayer graphLayer, Image bazar_carpet_image) {
		if(bazar_carpet != null)
			bazar_carpet.destroy();
		
		bazar_carpet = graphics().createImageLayer(bazar_carpet_image);
		bazar_carpet.setDepth(Const.BAZAR_CARPET_DEPTH);
		
		bazar_carpet_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                bazar_carpet.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(bazar_carpet);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getBazarLevel() {
		return bazar_level;
	}
	
	@Override
	public void setBazarLevel(int level) {
		bazar_level = level;
	}
	
	@Override
	public void buildTower(final GroupLayer graphLayer, Image tower_image) {
		if(tower1 != null)
			tower1.destroy();
		if(tower2 != null)
			tower2.destroy();
		
		tower1 = graphics().createImageLayer(tower_image);
		tower1.setDepth(Const.TOWER_DEPTH);
		tower2 = graphics().createImageLayer(tower_image);
		tower2.setDepth(Const.TOWER_DEPTH);
		
		tower_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                tower1.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(tower1);
				tower2.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(tower2);
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
	public void setTowerLevel(int level) {
		tower_level = level;
	}
	
	@Override
	public void paint(float x, float y) {
		super.paint(x, y);
		if(palace != null) {
			palace.setScale(Const.PALACE_SCALE, Const.PALACE_SCALE);
			palace.setTranslation(x + Const.PALACE_X, y + Const.PALACE_Y);
		}
		if(bazar_food != null) {
			bazar_food.setScale(Const.BAZAR_FOOD_SCALE, Const.BAZAR_FOOD_SCALE);
			bazar_food.setTranslation(x + Const.BAZAR_FOOD_X, y + Const.BAZAR_FOOD_Y);
		}
		if(bazar_china != null) {
			bazar_china.setScale(Const.BAZAR_CHINA_SCALE, Const.BAZAR_CHINA_SCALE);
			bazar_china.setTranslation(x + Const.BAZAR_CHINA_X, y + Const.BAZAR_CHINA_Y);
		}
		if(bazar_carpet != null) {
			bazar_carpet.setScale(Const.BAZAR_CARPET_SCALE, Const.BAZAR_CARPET_SCALE);
			bazar_carpet.setTranslation(x + Const.BAZAR_CARPET_X, y + Const.BAZAR_CARPET_Y);
		}
		if(tower1 != null) {
			tower1.setScale(Const.TOWER_SCALE, Const.TOWER_SCALE);
			tower1.setTranslation(x + Const.TOWER1_X, y + Const.TOWER1_Y);
		}
		if(tower2 != null) {
			tower2.setScale(Const.TOWER_SCALE, Const.TOWER_SCALE);
			tower2.setTranslation(x + Const.TOWER2_X, y + Const.TOWER2_Y);
		}
	}
}