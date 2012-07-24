package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

/**
* This class contains all the image and game logic for a Mapping
*/
public class Mapping
{	
	private Image mapImage;
    private ImageLayer mapLayer;
    private boolean visible;
	
	private Tuple2f pos1;
	private Tuple2f pos2;
	
	private int points;
	
	/**
	* Constructor of the Mapping
	* @param graphLayer - the GroupLayer of the graph
	* @param pos1 - the coordinates of the node the mapping is starting from
	* @param pos2 - the coordinates of the node the mapping goes to
	*/
    public Mapping(final GroupLayer graphLayer, Tuple2f pos1, Tuple2f pos2)
    {
		mapImage = Const.MAP_IMAGE;
        mapLayer = graphics().createImageLayer(mapImage);
		visible = true;
		points = 0;
		mapLayer.setDepth(Const.MAPPING_DEPTH);
		mapLayer.setAlpha(Const.VISIBLE);//mapLayer.setVisible(true);
		this.pos1 = pos1;
		this.pos2 = pos2;

        mapImage.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                graphLayer.add(mapLayer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });

    }

	/**
	* @return the visible flag
	*/
    public boolean isVisible() {
        return visible;
    }

	/**
	* Set the visibility flag to true
	* @param visible - the boolean flag
	*/
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
	
	/**
	* sets the visibiliy(transparency level) of the Mapping image according to the given vibility flag
	* @param visible - the boolean flag
	*/
	public void paintVisibility(boolean visible) {
		if(visible)
			mapLayer.setAlpha(Const.VISIBLE);//mapLayer.setVisible(true);
		else
			mapLayer.setAlpha(Const.HIDDEN_MAPPING);//mapLayer.setVisible(false);
	}

	/**
	* places the positions of the mapping
	* @param pos1 - the coordinates of node1
	* @param pos2 - the coordinates of node2
	*/
	public void setMapping(Tuple2f pos1, Tuple2f pos2)	{
		this.pos1 = pos1;
		this.pos2 = pos2;
	}
	
	/**
	* paints the mapping image and applies all the transformations
	*/
	public void paint() {
		//mapLayer.setTranslation(0, 0);
		if(!mapLayer.destroyed()) {
			mapLayer.setRotation((float)(Math.atan2(pos2.y - pos1.y, pos2.x - pos1.x)));
			mapLayer.setScale(pos1.getDistanceFrom(pos2)/mapImage.width(), Const.MAPPING_WIDTH);
			mapLayer.setTranslation(pos1.x, pos1.y);
		}
	}
	
	/**
	* @return the mapping layer of type ImageLayer
	*/
    public ImageLayer getLayer()    {
        return mapLayer;
    }
	
	/**
	* @return the points this mapping has
	*/
	public int getPoints() {
		return points;
	}
	
	/**
	* Sets the points of this mapping to the given value
	* @param points - integer
	*/
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	* destroys the mapping layer
	*/
	public void destroy() {
		this.visible = false;
		mapLayer.destroy();
	}
}
