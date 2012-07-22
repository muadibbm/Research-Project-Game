package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class Road
{	
	private Image roadImage;
    private ImageLayer roadLayer;
    private boolean visible;
	
	private Tuple2f pos1;
	private Tuple2f pos2;
	
	private boolean placed;

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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
	
	public void paintVisibility(boolean visible) {
		if(visible)
            roadLayer.setAlpha(Const.VISIBLE);//roadLayer.setVisible(true);
		else
            roadLayer.setAlpha(Const.HIDDEN_ROAD);//roadLayer.setVisible(false);
	}

	public void placeRoad(Tuple2f pos1, Tuple2f pos2)	{
		this.pos1 = pos1;
		this.pos2 = pos2;
		placed = true;
	}
	
	public void paint() {
		//roadLayer.setTranslation(0, 0);
		roadLayer.setRotation((float)(Math.atan2(pos2.y - pos1.y, pos2.x - pos1.x)));
        roadLayer.setScale(pos1.getDistanceFrom(pos2)/roadImage.width(), Const.ROAD_WIDTH);
		roadLayer.setTranslation(pos1.x, pos1.y);
	}
	
	public Boolean isPlaced() {
		return placed;
	}
	
    public ImageLayer getLayer()    {
        return roadLayer;
    }
}
