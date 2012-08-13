package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import com.domain.project.core.Const;
import com.domain.project.core.Digits;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

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
	
	private int score;
	private Digits scoreImage;
	
	/**
	* Constructor of the Mapping
	* @param graphLayer - the GroupLayer of the graph
	* @param pos1 - the coordinates of the node the mapping is starting from
	* @param pos2 - the coordinates of the node the mapping goes to
	* @param score - the mapping score
	*/
    public Mapping(final GroupLayer graphLayer, Tuple2f pos1, Tuple2f pos2, int score)
    {
		mapImage = Const.MAP_IMAGE;
        mapLayer = graphics().createImageLayer(mapImage);
		visible = true;
		this.score = score;
		scoreImage = new Digits(graphLayer, Const.MAPPING_POINT_X + (pos2.getX() + pos1.getX())/2.0f, Const.MAPPING_POINT_Y + (pos2.getY() + pos1.getY())/2.0f, Const.MAPPING_POINT_SCALE, Const.MAPPING_DEPTH+1.0f);
		scoreImage.setAlpha(Const.VISIBLE);
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
	* sets the visibility(transparency level) of the Mapping image according to the given visibility flag
	* @param visible - the boolean flag
	*/
	public void paintVisibility(boolean visible) {
		if(visible) {
			mapLayer.setAlpha(Const.VISIBLE);//mapLayer.setVisible(true);
			scoreImage.setAlpha(Const.VISIBLE);
		}
		else {
			mapLayer.setAlpha(Const.HIDDEN_MAPPING);//mapLayer.setVisible(false);
			scoreImage.setAlpha(Const.HIDDEN_MAPPING);
		}
	}
	
	private void paintScore() {
		if(!scoreImage.destroyed()) {
			scoreImage.setDigits(this.score);
		}
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
		paintScore();
		if(!mapLayer.destroyed()) {
			mapLayer.setRotation((float)(Math.atan2(pos2.getY() - pos1.getY(), pos2.getX() - pos1.getX())));
			mapLayer.setScale(pos1.getDistanceFrom(pos2)/mapImage.width(), Const.MAPPING_WIDTH);
			mapLayer.setTranslation(pos1.getX(), pos1.getY());
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
	public int getScore() {
		return score;
	}
	
	/**
	* Sets the points of this mapping to the given value
	* @param points - integer
	*/
	public void setScore(int score) {
		if(score < 0)
			this.score = 0;
		else
			this.score = score;
	}
	
	/**
	* destroys the mapping layer
	*/
	public void destroy() {
		this.visible = false;
		mapLayer.destroy();
		scoreImage.destroy();
		mapLayer = null;
		scoreImage = null;
	}
}
