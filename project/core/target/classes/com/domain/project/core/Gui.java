package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import playn.core.Mouse;

import com.domain.project.core.graph.Mapping;
import com.domain.project.core.graph.Base;
import com.domain.project.core.graph.Camp;
import com.domain.project.core.graph.City;

public class Gui
{
	private ImageLayer option1;//TOWN_HALL
	private ImageLayer option2;//FOOD_BAZAR
	private ImageLayer option3;//CHINA_BAZAR
	private ImageLayer option4;//CARPET_BAZAR
	private ImageLayer option5;//GARDEN
	private ImageLayer option6;//SMITHY
	private ImageLayer option7;//TOWER
	private ImageLayer option8;//WALL
	
	private ImageLayer infoPanel;
	private ImageLayer populationLayer;
	
	private ImageLayer map;
	private ImageLayer unmap;
	
	public Gui(final GroupLayer uiLayer)
	{
        option1 = graphics().createImageLayer(Const.CONSTRUCTION_PALACE_LEVEL1);
		createImage(uiLayer, option1, Const.CONSTRUCTION_PALACE_LEVEL1, 0.0f);
		option2 = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_FOOD_LEVEL1);
		createImage(uiLayer, option2, Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1, 0.3f);
		option3 = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_CHINA_LEVEL1);
		createImage(uiLayer, option3, Const.CONSTRUCTION_BAZAR_CHINA_LEVEL1, 0.6f);
		option4 = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1);
		createImage(uiLayer, option4, Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1, 0.9f);
		option5 = graphics().createImageLayer(Const.CONSTRUCTION_GARDEN_LEVEL1);
		createImage(uiLayer, option5, Const.CONSTRUCTION_GARDEN_LEVEL1, 1.2f);
		option6 = graphics().createImageLayer(Const.CONSTRUCTION_SMITHY_LEVEL1);
		createImage(uiLayer, option6, Const.CONSTRUCTION_SMITHY_LEVEL1, 1.5f);
		option7 = graphics().createImageLayer(Const.CONSTRUCTION_TOWER_LEVEL1);
		createImage(uiLayer, option7, Const.CONSTRUCTION_TOWER_LEVEL1, 1.8f);
		option8 = graphics().createImageLayer(Const.CONSTRUCTION_WALL_LEVEL1);
		createImage(uiLayer, option8, Const.CONSTRUCTION_WALL_LEVEL1, 2.1f);
		
		infoPanel = graphics().createImageLayer(Const.INFO_PANEL_IMAGE);
		
		map = graphics().createImageLayer(Const.MAP_BUTTON_IMAGE);
		unmap = graphics().createImageLayer(Const.UNMAP_BUTTON_IMAGE);
		
		Const.MAP_BUTTON_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				map.setTranslation(Const.MAP_BUTTON_X, Const.MAP_BUTTON_Y);
				map.setScale(Const.BUTTON_SCALE);
				uiLayer.add(map);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
		
		Const.UNMAP_BUTTON_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				unmap.setTranslation(Const.UNMAP_BUTTON_X, Const.UNMAP_BUTTON_Y);
				unmap.setScale(Const.BUTTON_SCALE);
				uiLayer.add(unmap);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
		
		Const.INFO_PANEL_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				infoPanel.setTranslation(Const.INFO_PANEL_X, Const.INFO_PANEL_Y);
				infoPanel.setScale(Const.INFO_PANEL_SCALE);
				uiLayer.add(infoPanel);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	public void addListener(final Player player, final GroupLayer graphLayer) {
		map.addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null & player.getNodeToBeMapped() == null)
							if(player.getId() == player.getSelectedNode().getPlayer() & player.getSelectedNode().getMapping() == null) {
								player.setNodeToBeMapped(player.getSelectedNode());
								//TODO : add some graphical indication
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		unmap.addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getSelectedNode().getMapping() != null & player.getId() == player.getSelectedNode().getPlayer()) {
								if(player.getSelectedNode().getBase() instanceof City)
									player.getSelectedNode().unMap();
								else if(player.getSelectedNode().getBase() instanceof Camp)
									player.getSelectedNode().getMappedNode().unMap();
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		/* Building TOWN HALL */
		option1.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof City){
								    //TODO : check level, check resources, update gui itself
									base.setCityLevel(base.getCityLevel() + 1);
									base.buildPalace(graphLayer, Const.PALACE_LEVEL1);
								}
								//else if(player.getSelectedNode().getBase() instanceof Camp)
									//TODO
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		/* Building BAZAR FOOD */
		option2.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof City){
									//TODO : check level, check resources, update gui itself
									//TODO : check bazar level
									base.buildBazarFood(graphLayer, Const.BAZAR_FOOD_LEVEL1);
								}
								//else if(player.getSelectedNode().getBase() instanceof Camp)
									//TODO
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		/* Building BAZAR CHINA */
		option3.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof City){
									//TODO : check level, check resources, update gui itself
									//TODO : check bazar level
									base.buildBazarChina(graphLayer, Const.BAZAR_CHINA_LEVEL1);
								}
								//else if(player.getSelectedNode().getBase() instanceof Camp)
									//TODO
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		/* Building BAZAR CARPET */
		option4.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof City){
									//TODO : check level, check resources, update gui itself
									//TODO : check bazar level
									base.buildBazarCarpet(graphLayer, Const.BAZAR_CARPET_LEVEL1);
								}
								//else if(player.getSelectedNode().getBase() instanceof Camp)
									//TODO
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		/* Building TOWER */
		option7.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof City){
									//TODO : check level, check resources, update gui itself
									base.setTowerLevel(base.getTowerLevel() + 1);
									base.buildTower(graphLayer, Const.TOWER_LEVEL1);
								}
								//else if(player.getSelectedNode().getBase() instanceof Camp)
									//TODO
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
	}
	
	public void setPopulation(int population, final GroupLayer uiLayer, Image number_image) {
		if(populationLayer != null)
			populationLayer.destroy();

		populationLayer = graphics().createImageLayer(number_image);
		
		number_image.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				populationLayer.setTranslation(Const.POPULATION_X, Const.POPULATION_Y);
				populationLayer.setScale(Const.POPULATION_SCALE);
				populationLayer.setDepth(1.0f);
				uiLayer.add(populationLayer);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	public void paint(boolean isCity) {
		
	}
	
	private void createImage(final GroupLayer uiLayer, final ImageLayer layerImage, Image optionImage, final float x_offset) {
		optionImage.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
                layerImage.setOrigin(image.width()/2f, image.height()/2f);
				layerImage.setTranslation(Const.CONSTRUCTION_PANEL_X + x_offset*Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y + image.height()/2f * Const.CONSTRUCTION_PANEL_SCALE);
				layerImage.setScale(Const.CONSTRUCTION_PANEL_SCALE);
                uiLayer.add(layerImage);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
}