package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class Mapping
{	
	private Image mapImage;
    private ImageLayer mapLayer;
    private boolean visible;
	
	private Tuple2f pos1;
	private Tuple2f pos2;
	
	private int points;

    public Mapping(final GroupLayer graphLayer, Tuple2f pos1, Tuple2f pos2)
    {
		mapImage = Const.MAP_IMAGE;
        mapLayer = graphics().createImageLayer(mapImage);
		visible = true;
		points = 0;
		mapLayer.setDepth(Const.MAPPING_VISIBLE_DEPTH);
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
	
	public void paintVisibility(boolean visible) {
		if(visible)
			mapLayer.setDepth(Const.MAPPING_VISIBLE_DEPTH);
		else
			mapLayer.setDepth(Const.MAPPING_HIDDEN_DEPTH);
	}

	public void setMapping(Tuple2f pos1, Tuple2f pos2)	{
		this.pos1 = pos1;
		this.pos2 = pos2;
	}
	
	public void paint() {
		//mapLayer.setTranslation(0, 0);
		if(!mapLayer.destroyed()) {
			mapLayer.setRotation((float)(Math.atan2(pos2.y - pos1.y, pos2.x - pos1.x)));
			mapLayer.setScale(pos1.getDistanceFrom(pos2)/mapImage.width(), Const.MAPPING_WIDTH);
			mapLayer.setTranslation(pos1.x, pos1.y);
		}
	}
	
    public ImageLayer getLayer()    {
        return mapLayer;
    }
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void destroy() {
		this.visible = false;
		mapLayer.destroy();
	}
}
