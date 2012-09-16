package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import com.domain.project.core.Const;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.GroupLayer;
import playn.core.ResourceCallback;

public class Camp extends Base
{	
	private ImageLayer commandTent;
	private int level;

	public Camp(GroupLayer graphLayer){
		super(graphLayer, Const.CAMP_BASE_IMAGE);
		level = 0;
	}
	
	@Override
	public void buildCommandTent(final GroupLayer graphLayer, Image commandTent_image) {
		if(commandTent != null)
			commandTent.destroy();
		
		commandTent = graphics().createImageLayer(commandTent_image);
		commandTent.setDepth(Const.COMMAND_TENT_DEPTH);
		
		commandTent_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                commandTent.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(commandTent);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
		@Override
	public int getLevel() {
		return level;
	}
	
	@Override
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public void paint(float x, float y) {
		super.paint(x, y);
		if(commandTent != null) {
			commandTent.setScale(Const.COMMAND_TENT_SCALE, Const.COMMAND_TENT_SCALE);
			commandTent.setTranslation(x + Const.COMMAND_TENT_X, y + Const.COMMAND_TENT_Y);
		}
	}
}