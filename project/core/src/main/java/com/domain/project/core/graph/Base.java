package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class Base
{
	private int level;
	private ImageLayer levelLayer;
	
	private ImageLayer baseLayer;
	
	public Base(final GroupLayer graphLayer)
	{
		this.level = 0;
		
        baseLayer = graphics().createImageLayer(Const.BASE_IMAGE);
		baseLayer.setDepth(Const.BASE_DEPTH);
		
        Const.BASE_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                baseLayer.setOrigin(image.width()/2, image.height()/2);
                graphLayer.add(baseLayer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
		
	public int getLevel() {
		return level;
	}
	
	public void paint(float x, float y) {
		baseLayer.setScale(Const.BASE_SCALE, Const.BASE_SCALE);
        baseLayer.setTranslation(x, y);
	}
	
	public ImageLayer getBaseLayer(){
		return baseLayer;
	}
}