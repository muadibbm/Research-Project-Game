package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import com.domain.project.core.Const;

import java.lang.Integer;

public class Tree
{
	private Tuple2f coordinate;
	private ImageLayer treeLayer;
	private ImageLayer shadowLayer;
	
	public Tree(final GroupLayer graphLayer, float posX, float posY, Image tree_image, Image tree_shadow_image)
	{
		treeLayer = graphics().createImageLayer(tree_image);
		shadowLayer = graphics().createImageLayer(tree_shadow_image);
		shadowLayer.setDepth(Const.TREE_SHADOW_DEPTH);
		treeLayer.setDepth(Const.TREE_DEPTH);
		coordinate = new Tuple2f(posX, posY);
		
        tree_image.addCallback(new ResourceCallback<Image>() {
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
		
		tree_shadow_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                shadowLayer.setOrigin(0, 0);
				shadowLayer.setAlpha(Const.SHADOW);
                graphLayer.add(shadowLayer);
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
		shadowLayer.setScale(Const.TREE_SCALE, Const.TREE_SCALE);
		shadowLayer.setTranslation(coordinate.x, coordinate.y + treeLayer.image().height()/100);
	}
	
	public ImageLayer getLayer(){
		return treeLayer;
	}
	
	public ImageLayer getShadow(){
		return shadowLayer;
	}
}