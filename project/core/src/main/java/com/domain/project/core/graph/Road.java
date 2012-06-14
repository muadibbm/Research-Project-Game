package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class Road
{
	private ImageLayer roadLayer;
	private boolean visible;
	
	//private Node node1;
	//private Node node2;
	
	public Road(GroupLayer graphLayer, Tuple2f posOne, Tuple2f posTwo, Image roadImage)
	{
		visible = false;
		//this.node1 = node1;
		//this.node2 = node2;
		//node1.addRoad(this);
		//node2.addRoad(this);
		
		final Tuple2f pos1 = posOne;
		final Tuple2f pos2 = posTwo;
		final GroupLayer copy_graphlayer = graphLayer;
        roadLayer = graphics().createImageLayer(roadImage);
		//TODO : should not be visible at this stage
		roadImage.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                roadLayer.setOrigin(image.width(), image.height());
				roadLayer.setTranslation(pos1.x, pos1.y);
				roadLayer.setScale(pos1.getDistanceFrom(pos2)/image.width(), 1.0f);
				roadLayer.setRotation((float)Math.tan(pos1.y - pos2.y/pos1.x - pos2.x));
                copy_graphlayer.add(roadLayer); //TODO: move to setVisible or ?
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
		//TODO: how to add or remove image layer ?
	}
	
	public ImageLayer getLayer()	{
		return roadLayer;
	}
}