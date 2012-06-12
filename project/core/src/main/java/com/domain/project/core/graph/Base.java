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
	private ImageLayer baseLayer;
	
	public Base(GroupLayer graphLayer)
	{
		this.level = 0;
		final GroupLayer copy_graphlayer = graphLayer;
		
		Image image = assets().getImage(Const.BASE_IMAGE);
        baseLayer = graphics().createImageLayer(image);

        image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                baseLayer.setOrigin(image.width() / 2.0f, image.height() / 2.0f);
                copy_graphlayer.add(baseLayer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	public ImageLayer getBaseLayer(){
		return baseLayer;
	}
}