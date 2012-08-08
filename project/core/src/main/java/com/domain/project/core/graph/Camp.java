package com.domain.project.core.graph;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.GroupLayer;
import playn.core.ResourceCallback;

import com.domain.project.core.Const;

public class Camp extends Base
{
	private ImageLayer soldierTent;
	private int soldierTent_level;
	
//	private ImageLayer healerTent;
//	private ImageLayer supplyTent;
//	private ImageLayer deplomaticTent;
	
	private ImageLayer commandTent;
	private int commandTent_level;

	public Camp(GroupLayer graphLayer){
		super(graphLayer, Const.CAMP_BASE_IMAGE);
		soldierTent_level = 0;
		commandTent_level = 0;
	}
	
	@Override
	public void buildSoldierTent(final GroupLayer graphLayer, Image soldierTent_image) {
		if(soldierTent != null)
			soldierTent.destroy();
		
		soldierTent = graphics().createImageLayer(soldierTent_image);
		soldierTent.setDepth(Const.SOLDIER_TENT_DEPTH);
		
		soldierTent_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                soldierTent.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(soldierTent);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getSoldierTentLevel() {
		return soldierTent_level;
	}
	
	@Override
	public void setSoldierTentLevel(int level) {
		soldierTent_level = level;
	}
	
	@Override
	public void buildCommandTent(final GroupLayer graphLayer, Image commandTent_image) {
		if(commandTent != null)
			commandTent.destroy();
		
		commandTent = graphics().createImageLayer(commandTent_image);
		commandTent.setDepth(Const.SOLDIER_TENT_DEPTH);
		
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
	public int getCommandTentLevel() {
		return commandTent_level;
	}
	
	@Override
	public void setCommandTentLevel(int level) {
		commandTent_level = level;
	}
	
	@Override
	public void paint(float x, float y) {
		super.paint(x, y);
		if(soldierTent != null) {
			soldierTent.setScale(Const.SOLDIER_TENT_SCALE, Const.SOLDIER_TENT_SCALE);
			soldierTent.setTranslation(x + Const.SOLDIER_TENT_X, y + Const.SOLDIER_TENT_Y);
		}
		if(commandTent != null) {
			commandTent.setScale(Const.COMMAND_TENT_SCALE, Const.COMMAND_TENT_SCALE);
			commandTent.setTranslation(x + Const.COMMAND_TENT_X, y + Const.COMMAND_TENT_Y);
		}
	}
}