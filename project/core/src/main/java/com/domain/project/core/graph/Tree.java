package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

public class Tree
{
	private Tuple2f coordinate;
	private ImageLayer treeLayer;
	
	public Tree(final GroupLayer graphLayer, float posX, float posY)
	{
        treeLayer = graphics().createImageLayer(Const.TREE1_IMAGE);
		treeLayer.setDepth(Const.TREE_DEPTH);
		coordinate = new Tuple2f(posX, posY);
		
        Const.TREE1_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                treeLayer.setOrigin(image.width()/2, image.height()/2);
                graphLayer.add(treeLayer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	public void paint() {
		treeLayer.setScale(Const.TREE_SCALE, Const.TREE_SCALE);
        treeLayer.setTranslation(coordinate.x, coordinate.y);
	}
	
	public ImageLayer getLayer(){
		return treeLayer;
	}
}