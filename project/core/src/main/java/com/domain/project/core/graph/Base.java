package com.domain.project.core.graph;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import com.domain.project.core.Const;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

/**
 * This class contains all the image and game logic for a corresponding node
 */
public class Base
{
	private int population;//degree of the node
	//private ImageLayer infoLayer;
	private Tuple2f position;
	private ImageLayer baseLayer;
	private ImageLayer selectionLayer1;
	private ImageLayer selectionLayer2;
	private ImageLayer selectionLayerM1;//selection for mapping node
	private ImageLayer selectionLayerM2;//selection for mapping node

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

		selectionLayer1 = graphics().createImageLayer(Const.CITY_BASE_SELECTED_IMAGE1);
		selectionLayer1.setDepth(Const.BASE_DEPTH);
		selectionLayer1.setAlpha(Const.SELECTION_ALPHA);

		selectionLayer2 = graphics().createImageLayer(Const.CITY_BASE_SELECTED_IMAGE2);
		selectionLayer2.setDepth(Const.BASE_DEPTH);
		selectionLayer2.setAlpha(Const.SELECTION_ALPHA);
		
		selectionLayerM1 = graphics().createImageLayer(Const.CITY_BASE_SELECTED_IMAGE3);
		selectionLayerM1.setDepth(Const.BASE_DEPTH);
		selectionLayerM1.setAlpha(Const.SELECTION_ALPHA);

		selectionLayerM2 = graphics().createImageLayer(Const.CITY_BASE_SELECTED_IMAGE4);
		selectionLayerM2.setDepth(Const.BASE_DEPTH);
		selectionLayerM2.setAlpha(Const.SELECTION_ALPHA);

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

		Const.CITY_BASE_SELECTED_IMAGE1.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				selectionLayer1.setOrigin(image.width() / 2, image.height() / 2);
				selectionLayer1.setScale(Const.CITY_SELECTION_SCALE1);
				graphLayer.add(selectionLayer1);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading node", e);
			}
		});

		Const.CITY_BASE_SELECTED_IMAGE2.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				selectionLayer2.setOrigin(image.width() / 2, image.height() / 2);
				selectionLayer2.setScale(Const.CITY_SELECTION_SCALE2);
				graphLayer.add(selectionLayer2);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading node", e);
			}
		});
		
		Const.CITY_BASE_SELECTED_IMAGE3.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				selectionLayerM1.setOrigin(image.width() / 2, image.height() / 2);
				selectionLayerM1.setScale(Const.CITY_SELECTION_SCALE1);
				graphLayer.add(selectionLayerM1);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading node", e);
			}
		});
		
		Const.CITY_BASE_SELECTED_IMAGE4.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				selectionLayerM2.setOrigin(image.width() / 2, image.height() / 2);
				selectionLayerM2.setScale(Const.CITY_SELECTION_SCALE2);
				graphLayer.add(selectionLayerM2);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading node", e);
			}
		});

		setSelection(false);
		setMappingSelection(false);
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

	/** Toggle the selection around a city or a camp.
	 * @param action
	 * @author Andrey
	 */
	public void setSelection(boolean action) {
		selectionLayer1.setVisible(action);
		selectionLayer2.setVisible(action);
	}

	/** Position the selection around a city or a camp.
	 * @param position
	 * @author Andrey
	 */
	public void positionSelection(Tuple2f position) {
		selectionLayer1.setTranslation(position.getX(), position.getY());
		selectionLayer2.setTranslation(position.getX(), position.getY());
	}
	
	/** Toggle the mapping selection around a city or a camp.
	 * @param action
	 * @author Mehrdad
	 */
	public void setMappingSelection(boolean action) {
		selectionLayerM1.setVisible(action);
		selectionLayerM2.setVisible(action);
	}

	/** Position the mapping selection around a city or a camp.
	 * @param position
	 * @author Mehrdad
	 */
	public void positionMappingSelection(Tuple2f position) {
		selectionLayerM1.setTranslation(position.getX(), position.getY());
		selectionLayerM2.setTranslation(position.getX(), position.getY());
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
	//TOWER METHODS
	public void buildCommandTent(GroupLayer graphLayer, Image image){};
	public int getLevel(){return 0;};
	public void setLevel(int level){};
	/*
	public void buildSoldierTent(GroupLayer graphLayer, Image image){};
	public int getSoldierTentLevel(){return 0;};
	public void setSoldierTentLevel(int level){};
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
	public void setDeplomaticTentLevel(int level){};*/
}