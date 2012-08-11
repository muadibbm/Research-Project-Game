package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

/**
 * This class contains all the image and game logic for a corresponding node
 */
public class Base
{
	private int population;//degree of the node
	//private ImageLayer infoLayer;
	private Tuple2f position;
	private ImageLayer baseLayer;

	/**
	 * Constructor of the Base
	 * @param graphLayer - the GroupLayer of the graph
	 * @param baseImage - the image of the base
	 */
	public Base(final GroupLayer graphLayer, Image baseImage)
	{
		this.population = 0;

		baseLayer = graphics().createImageLayer(baseImage);

		baseLayer.setDepth(Const.BASE_DEPTH);
		baseLayer.setAlpha(Const.BASE_ALPHA);

		//infoLayer = graphics().createImageLayer(Const.INFO_PANEL_IMAGE);
		//infoLayer.setDepth(Const.UI_DEPTH);

		baseImage.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				baseLayer.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(baseLayer);
			}

			@Override
			public void error(Throwable e) {
				log().error("error loading node", e);
			}
		});

		/*
		Const.INFO_PANEL_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                infoLayer.setOrigin(image.width()/2, image.height()/2);
				infoLayer.setScale(Const.INFO_PANEL_SCALE, Const.INFO_PANEL_SCALE);
                graphLayer.add(infoLayer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
		 */
	}

	/**
	 * Sets the population of this node to the given number
	 * @param population - int
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @return the population of this node
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * paints the base at the given coordinates
	 * @param x - float x coordinate
	 * @param y - float y coordinate
	 */
	public void paint(float x, float y) {
		if(this instanceof City) { 
			baseLayer.setScale(Const.BASE_CITY_SCALE, Const.BASE_CITY_SCALE);
		} else {
			baseLayer.setScale(Const.BASE_CAMP_SCALE, Const.BASE_CAMP_SCALE);			
		}
		baseLayer.setTranslation(x, y);

		//infoLayer.setTranslation(x + Const.INFO_PANEL_X, y + Const.INFO_PANEL_Y);
	}

	/**
	 * @return the baseLayer of type ImageLayer
	 */
	public ImageLayer getBaseLayer(){
		return baseLayer;
	}

	public Tuple2f getPosition() {
		return position;
	}

	public void setPosition(Tuple2f position) {
		this.position = position;
	}

	//	public float getScale

	/* OVERRIDEN METHODS IN SUBCLASSES*/
	//CITY METHODS
	public void buildPalace(GroupLayer graphLayer, Image image){};
	public void buildBazarFood(GroupLayer graphLayer, Image image){};
	public void buildBazarChina(GroupLayer graphLayer, Image image){};
	public void buildBazarCarpet(GroupLayer graphLayer, Image image){};
	public void buildGarden(GroupLayer graphLayer, Image image){};
	public void buildSmithy(GroupLayer graphLayer, Image image){};
	public void buildTower(GroupLayer graphLayer, Image image){};
	public void buildWall(GroupLayer graphLayer, Image image1, Image image2, Image image3, Image image4){};
	public int getCityLevel(){return 0;};
	public void setCityLevel(int level){};
	public int getBazarLevel(){return 0;};
	public void setBazarLevel(int level){};
	public int getGardenLevel(){return 0;};
	public void setGardenLevel(int level){};
	public int getSmithyLevel(){return 0;};
	public void setSmithyLevel(int level){};
	public int getTowerLevel(){return 0;};
	public void setTowerLevel(int level){};
	public int getWallLevel(){return 0;};
	public void setWallLevel(int level){};
	//CAMP METHODS
	public void buildSoldierTent(GroupLayer graphLayer, Image image){};
	public int getSoldierTentLevel(){return 0;};
	public void setSoldierTentLevel(int level){};
	public void buildCommandTent(GroupLayer graphLayer, Image image){};
	public int getCommandTentLevel(){return 0;};
	public void setCommandTentLevel(int level){};
	public void buildMageTent(GroupLayer graphLayer, Image image){};
	public int getMageTentLevel(){return 0;};
	public void setMageTentLevel(int level){};
	public void buildHealerTent(GroupLayer graphLayer, Image image){};
	public int getHealerTentLevel(){return 0;};
	public void setHealerTentLevel(int level){};
	public void buildSupplyTent(GroupLayer graphLayer, Image image){};
	public int getSupplyTentLevel(){return 0;};
	public void setSupplyTentLevel(int level){};
	public void buildDeplomaticTent(GroupLayer graphLayer, Image image){};
	public int getDeplomaticTentLevel(){return 0;};
	public void setDeplomaticTentLevel(int level){};
}