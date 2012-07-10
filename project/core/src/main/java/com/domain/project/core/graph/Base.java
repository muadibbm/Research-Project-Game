package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.CanvasLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;
import playn.core.TextFormat;

import com.domain.project.core.Const;

public class Base
{
	private int population;//degree of the node
	private ImageLayer infoLayer;
	
	private ImageLayer baseLayer;
	
	public Base(final GroupLayer graphLayer, Image baseImage)
	{
		this.population = 0;
        baseLayer = graphics().createImageLayer(baseImage);
		baseLayer.setDepth(Const.BASE_DEPTH);
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
	
	public void setPopulation(int population) {
		this.population = population;
	}
		
	public int getPopulation() {
		return population;
	}
	
	public void paint(float x, float y) {
		if(this instanceof City) 
			baseLayer.setScale(Const.BASE_CITY_SCALE, Const.BASE_CITY_SCALE);
		else
			baseLayer.setScale(Const.BASE_CAMP_SCALE, Const.BASE_CAMP_SCALE);
        baseLayer.setTranslation(x, y);
		//infoLayer.setTranslation(x + Const.INFO_PANEL_X, y + Const.INFO_PANEL_Y);
	}
	
	public ImageLayer getBaseLayer(){
		return baseLayer;
	}
}