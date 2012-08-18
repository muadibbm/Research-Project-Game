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

	private ImageLayer healerTent;
	private int healerTent_level;
	
	private ImageLayer supplyTent;
	private int supplyTent_level;
	
	private ImageLayer deplomaticTent;
	private int deplomaticTent_level;
	
	private ImageLayer commandTent;
	private int commandTent_level;
	
	private ImageLayer mageTent;
	private int mageTent_level;

	public Camp(GroupLayer graphLayer){
		super(graphLayer, Const.CAMP_BASE_IMAGE);
		soldierTent_level = 0;
		commandTent_level = 0;
		mageTent_level = 0;
		healerTent_level = 0;
		supplyTent_level = 0;
		deplomaticTent_level = 0;
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
	public int getCommandTentLevel() {
		return commandTent_level;
	}
	
	@Override
	public void setCommandTentLevel(int level) {
		commandTent_level = level;
	}
	
	@Override
	public void buildMageTent(final GroupLayer graphLayer, Image mageTent_image) {
		if(mageTent != null)
			mageTent.destroy();
		
		mageTent = graphics().createImageLayer(mageTent_image);
		mageTent.setDepth(Const.MAGE_TENT_DEPTH);
		
		mageTent_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                mageTent.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(mageTent);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getMageTentLevel() {
		return mageTent_level;
	}
	
	@Override
	public void setMageTentLevel(int level) {
		mageTent_level = level;
	}
	
	@Override
	public void buildHealerTent(final GroupLayer graphLayer, Image healerTent_image) {
		if(healerTent != null)
			healerTent.destroy();
		
		healerTent = graphics().createImageLayer(healerTent_image);
		healerTent.setDepth(Const.HEALER_TENT_DEPTH);
		
		healerTent_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                healerTent.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(healerTent);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getHealerTentLevel() {
		return healerTent_level;
	}
	
	@Override
	public void setHealerTentLevel(int level) {
		healerTent_level = level;
	}
	
	@Override
	public void buildSupplyTent(final GroupLayer graphLayer, Image supplyTent_image) {
		if(supplyTent != null)
			supplyTent.destroy();
		
		supplyTent = graphics().createImageLayer(supplyTent_image);
		supplyTent.setDepth(Const.SUPPLY_TENT_DEPTH);
		
		supplyTent_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                supplyTent.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(supplyTent);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getSupplyTentLevel() {
		return supplyTent_level;
	}
	
	@Override
	public void setSupplyTentLevel(int level) {
		supplyTent_level = level;
	}
	
	@Override
	public void buildDeplomaticTent(final GroupLayer graphLayer, Image deplomaticTent_image) {
		if(deplomaticTent != null)
			deplomaticTent.destroy();
		
		deplomaticTent = graphics().createImageLayer(deplomaticTent_image);
		deplomaticTent.setDepth(Const.DEPLOMATIC_TENT_DEPTH);
		
		deplomaticTent_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                deplomaticTent.setOrigin(image.width()/2f, image.height()/2f);
                graphLayer.add(deplomaticTent);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	@Override
	public int getDeplomaticTentLevel() {
		return deplomaticTent_level;
	}
	
	@Override
	public void setDeplomaticTentLevel(int level) {
		deplomaticTent_level = level;
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
		if(mageTent != null) {
			mageTent.setScale(Const.MAGE_TENT_SCALE, Const.MAGE_TENT_SCALE);
			mageTent.setTranslation(x + Const.MAGE_TENT_X, y + Const.MAGE_TENT_Y);
		}
		if(healerTent != null) {
			healerTent.setScale(Const.HEALER_TENT_SCALE, Const.HEALER_TENT_SCALE);
			healerTent.setTranslation(x + Const.HEALER_TENT_X, y + Const.HEALER_TENT_Y);
		}
		if(supplyTent != null) {
			supplyTent.setScale(Const.SUPPLY_TENT_SCALE, Const.SUPPLY_TENT_SCALE);
			supplyTent.setTranslation(x + Const.SUPPLY_TENT_X, y + Const.SUPPLY_TENT_Y);
		}
		if(deplomaticTent != null) {
			deplomaticTent.setScale(Const.DEPLOMATIC_TENT_SCALE, Const.DEPLOMATIC_TENT_SCALE);
			deplomaticTent.setTranslation(x + Const.DEPLOMATIC_TENT_X, y + Const.DEPLOMATIC_TENT_Y);
		}
	}
}