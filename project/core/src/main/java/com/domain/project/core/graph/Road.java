package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import com.domain.project.core.Const;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

/**
* This class contains all the image and game logic for a corresponding edge
*/
public class Road
{	
	private Image roadImage;
    private ImageLayer roadLayer;
    private boolean visible;
	
	private Tuple2f pos1;
	private Tuple2f pos2;
	
	private boolean placed;
	
	/**
	* Constructor of the Road
	* @param graphLayer - the GroupLayer of the graph
	*/
    public Road(final GroupLayer graphLayer)
    {
        visible = false;
		placed = false;
		//TODO: randomly choose from different road images
		roadImage = Const.ROAD_IMAGE;
        roadLayer = graphics().createImageLayer(roadImage);
		roadLayer.setDepth(Const.ROAD_DEPTH);
		roadLayer.setAlpha(Const.HIDDEN_ROAD);//roadLayer.setVisible(false);

        roadImage.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                graphLayer.add(roadLayer);
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
	* sets the visibility(transparency level) of the Road image according to the given visibility flag
	* @param visible - the boolean flag
	*/
	public void paintVisibility(boolean visible) {
		if(visible)
            roadLayer.setAlpha(Const.VISIBLE);//roadLayer.setVisible(true);
		else
            roadLayer.setAlpha(Const.HIDDEN_ROAD);//roadLayer.setVisible(false);
	}

	/**
	* places the positions of the road and set the placed flag to true
	* @param pos1 - the coordinates of node1
	* @param pos2 - the coordinates of node2
	*/
	public void placeRoad(Tuple2f pos1, Tuple2f pos2)	{
		this.pos1 = pos1;
		this.pos2 = pos2;
		placed = true;
	}
	
	/**
	* paints the road image and applies all the transformations
	*/
	public void paint() {
		//roadLayer.setTranslation(0, 0);
		roadLayer.setRotation((float)(Math.atan2(pos2.getY() - pos1.getY(), pos2.getX() - pos1.getX())));
        roadLayer.setScale(pos1.getDistanceFrom(pos2)/roadImage.width(), Const.ROAD_WIDTH);
		roadLayer.setTranslation(pos1.getX(), pos1.getY());
	}
	
	/**
	* @return the placed flag
	*/
	public Boolean isPlaced() {
		return placed;
	}
	
	/**
	* @return the road layer of type ImageLayer
	*/
    public ImageLayer getLayer()    {
        return roadLayer;
    }
}
