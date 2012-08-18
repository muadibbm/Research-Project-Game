package com.domain.project.core;

import static playn.core.PlayN.graphics;

import com.domain.project.core.enums.Zoom;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import tripleplay.anim.Animator;

/**
 *  Holds playn layers
 *  - root
 *      - mainLayer
 *          - graphLayer
 *              - bgLayer
 *			- uiLayer
 *			- frameLayer
 */
public class Environment
{
    final private GroupLayer mainLayer;
    final private GroupLayer graphLayer; //contains graph layer objects(city graphs, camp graphs, etc)
    final private ImageLayer bgLayer;
    final private ImageLayer frameLayer;
    final private GroupLayer uiLayer; //contains all the objects and layers for the game UI

    public final Animator animator = Animator.create();
    private float xOffset;
    private float yOffset;
    private Zoom zLevel;
    private float elapsed;

	/** 
	* Constructor of the Environment
	*/
    public Environment()
    {
        //set window size
        graphics().setSize(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);

        bgLayer = graphics().createImageLayer(Const.BACKGROUND_IMAGE);
        bgLayer.setSize(Const.WORLD_WIDTH, Const.WORLD_HEIGHT);
        bgLayer.setRepeatX(true);
        bgLayer.setRepeatY(true);
        
        frameLayer = graphics().createImageLayer(Const.FRAME_IMAGE);
        frameLayer.setTranslation(Const.WORLD_ORIGIN_X*2, Const.WORLD_ORIGIN_Y*2);
        frameLayer.setSize(Const.WORLD_END_WIDTH*2 - Const.WORLD_ORIGIN_X, Const.WORLD_END_HEIGHT*2 - Const.WORLD_ORIGIN_Y);
        frameLayer.setRepeatX(true);
        frameLayer.setRepeatY(true);

        mainLayer = graphics().createGroupLayer();
        //create group layer containing the graphs
        graphLayer = graphics().createGroupLayer();
        //create group layer containing the UI
        uiLayer = graphics().createGroupLayer();
        //add all the layer to the main Layer and then the root Layer
        graphLayer.add(bgLayer);
        mainLayer.add(graphLayer);
        mainLayer.add(frameLayer);
		//mainLayer.add(uiLayer);
		graphics().rootLayer().add(uiLayer);
        graphics().rootLayer().add(mainLayer);

        frameLayer.setDepth(Const.BACKGROUND_DEPTH-1);
        bgLayer.setDepth(Const.BACKGROUND_DEPTH);
        graphLayer.setDepth(Const.GRAPH_DEPTH);
		uiLayer.setDepth(Const.UI_DEPTH);
        
        xOffset = 0.0f;
        yOffset = 0.0f;
        
        zLevel = Zoom.DEFAULT;
    }

	/**
	* @return the background layer of ImageLayer type
	*/
    public Image getBaseImage()
    {
        return bgLayer.image();
    }

	/**
	* @return the graph layer of GroupLayer type
	*/
    public GroupLayer getGraphLayer()
    {
        return graphLayer;
    }

	/**
	* @return the main layer of the GroupLayer type
	*/
    public GroupLayer getMainLayer() {
        return mainLayer;
    }
	
	/**
	* @return the user interface layer of the GroupLayer type
	*/
	public GroupLayer getUILayer() {
        return uiLayer;
    }

//    public void update(float x, float y) {
//        mainLayer.setOrigin(x, y);
//    }
    public void update(float delta) {
        elapsed += delta;
//        mainLayer.setOrigin(xOffset, yOffset);
    }

	/**
	* paints the environment
	*/
    public void paint(float alpha) {
        mainLayer.setOrigin(xOffset, yOffset);
        animator.update(elapsed + Const.UPDATE_RATE * alpha);
    }

	/**
     * Set x offset to the given value
	 * @param xOffset - float
     */
    public void setX(float xOffset) {
        this.xOffset = xOffset;
    }

	/**
     * Set y offset to the given value
	 * @param yOffset - float
     */
    public void setY(float yOffset) {
        this.yOffset = yOffset;
    }

    /**
     * @return current x offset
     */
    public float getX() {
        return xOffset;
    }

    /**
     * @return current y offset
     */
    public float getY() {
        return yOffset;
    }
  
    /**
	* Sets the zoom level the the given level
	* @param zlevel - zoom level of type Zoom
	*/
    public void setZoomLevel(Zoom zLevel) {
        this.zLevel = zLevel;
    }
	
	/**
     * @return zlevel of type Zoom
     */
    public Zoom zLevel() {
        return zLevel;
    }
}
