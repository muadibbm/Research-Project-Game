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

    public Road(GroupLayer graphLayer)
    {
        visible = false;
		//TODO: randomly choose from diffrent road images
		roadImage = Const.ROAD_IMAGE;
        roadLayer = graphics().createImageLayer(roadImage);
		roadLayer.setDepth(Const.ROAD_HIDDEN_DEPTH);
		
		final GroupLayer copy_graphlayer = graphLayer;
        roadImage.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                copy_graphlayer.add(roadLayer);
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
		if(visible)
			roadLayer.setDepth(Const.ROAD_VISIBLE_DEPTH);
		else
			roadLayer.setDepth(Const.ROAD_HIDDEN_DEPTH);
    }

	public void placeRoad(Tuple2f pos1, Tuple2f pos2)	{
		//roadLayer.setTranslation(0, 0);
		roadLayer.setRotation((float)(Math.atan2(pos2.y - pos1.y, pos2.x - pos1.x)));
        roadLayer.setScale(pos1.getDistanceFrom(pos2)/roadImage.width(), Const.ROAD_WIDTH);
		roadLayer.setTranslation(pos1.x, pos1.y);
	}
	
    public ImageLayer getLayer()    {
        return roadLayer;
    }
}
