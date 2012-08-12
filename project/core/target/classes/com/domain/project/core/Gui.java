package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import playn.core.Mouse;

import com.domain.project.core.graph.Base;
import com.domain.project.core.graph.Camp;
import com.domain.project.core.graph.City;

public class Gui
{
	private ImageLayer option1_empty;
	private ImageLayer option2_empty;
	private ImageLayer option3_empty;
	private ImageLayer option4_empty;
	private ImageLayer option5_empty;
	private ImageLayer option6_empty;
	private ImageLayer option7_empty;
	private ImageLayer option8_empty;

	private ImageLayer option1_city;//TOWN_HALL
	private ImageLayer option2_city;//FOOD_BAZAR
	private ImageLayer option3_city;//CHINA_BAZAR
	private ImageLayer option4_city;//CARPET_BAZAR
	private ImageLayer option5_city;//GARDEN
	private ImageLayer option6_city;//SMITHY
	private ImageLayer option7_city;//TOWER
	private ImageLayer option8_city;//WALL

	private ImageLayer option1_camp;//
	private ImageLayer option2_camp;//
	private ImageLayer option3_camp;//
	private ImageLayer option4_camp;//
	private ImageLayer option5_camp;//
	private ImageLayer option6_camp;//
	private ImageLayer option7_camp;//
	private ImageLayer option8_camp;//

	private ImageLayer infoPanel;
	private ImageLayer populationLayer;
	private Digits gold;

	private ImageLayer map;
	private ImageLayer unmap;

	public Gui(final GroupLayer uiLayer)
	{
		option1_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option1_empty, Const.CONSTRUCTION_PANEL_EMPTY, 0.0f);
		option2_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option2_empty, Const.CONSTRUCTION_PANEL_EMPTY, 0.3f);
		option3_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option3_empty, Const.CONSTRUCTION_PANEL_EMPTY, 0.6f);
		option4_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option4_empty, Const.CONSTRUCTION_PANEL_EMPTY, 0.9f);
		option5_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option5_empty, Const.CONSTRUCTION_PANEL_EMPTY, 1.2f);
		option6_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option6_empty, Const.CONSTRUCTION_PANEL_EMPTY, 1.5f);
		option7_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option7_empty, Const.CONSTRUCTION_PANEL_EMPTY, 1.8f);
		option8_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option8_empty, Const.CONSTRUCTION_PANEL_EMPTY, 2.1f);

		option1_city = graphics().createImageLayer(Const.CONSTRUCTION_PALACE_LEVEL1);
		createImage(uiLayer, option1_city, Const.CONSTRUCTION_PALACE_LEVEL1, 0.0f);
		option2_city = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_FOOD_LEVEL1);
		createImage(uiLayer, option2_city, Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1, 0.3f);
		option3_city = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_CHINA_LEVEL1);
		createImage(uiLayer, option3_city, Const.CONSTRUCTION_BAZAR_CHINA_LEVEL1, 0.6f);
		option4_city = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1);
		createImage(uiLayer, option4_city, Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1, 0.9f);
		option5_city = graphics().createImageLayer(Const.CONSTRUCTION_GARDEN_LEVEL1);
		createImage(uiLayer, option5_city, Const.CONSTRUCTION_GARDEN_LEVEL1, 1.2f);
		option6_city = graphics().createImageLayer(Const.CONSTRUCTION_SMITHY_LEVEL1);
		createImage(uiLayer, option6_city, Const.CONSTRUCTION_SMITHY_LEVEL1, 1.5f);
		option7_city = graphics().createImageLayer(Const.CONSTRUCTION_TOWER_LEVEL1);
		createImage(uiLayer, option7_city, Const.CONSTRUCTION_TOWER_LEVEL1, 1.8f);
		option8_city = graphics().createImageLayer(Const.CONSTRUCTION_WALL_LEVEL1);
		createImage(uiLayer, option8_city, Const.CONSTRUCTION_WALL_LEVEL1, 2.1f);
		
		//option1_camp = graphics().createImageLayer(Const.CONSTRUCTION_COMMAND_TENT_LEVEL1);
		//createImage(uiLayer, option1_camp, Const.CONSTRUCTION_COMMAND_TENT_LEVEL1, 0.0f);
		option2_camp = graphics().createImageLayer(Const.CONSTRUCTION_COMMAND_TENT_LEVEL1);
		createImage(uiLayer, option2_camp, Const.CONSTRUCTION_COMMAND_TENT_LEVEL1, 0.3f);
		option3_camp = graphics().createImageLayer(Const.CONSTRUCTION_SOLDIER_TENT_LEVEL1);
		createImage(uiLayer, option3_camp, Const.CONSTRUCTION_SOLDIER_TENT_LEVEL1, 0.6f);
		option4_camp = graphics().createImageLayer(Const.CONSTRUCTION_MAGE_TENT_LEVEL1);
		createImage(uiLayer, option4_camp, Const.CONSTRUCTION_MAGE_TENT_LEVEL1, 0.9f);
		option5_camp = graphics().createImageLayer(Const.CONSTRUCTION_HEALER_TENT_LEVEL1);
		createImage(uiLayer, option5_camp, Const.CONSTRUCTION_HEALER_TENT_LEVEL1, 1.2f);
		option6_camp = graphics().createImageLayer(Const.CONSTRUCTION_SUPPLY_TENT_LEVEL1);
		createImage(uiLayer, option6_camp, Const.CONSTRUCTION_SUPPLY_TENT_LEVEL1, 1.5f);
		option7_camp = graphics().createImageLayer(Const.CONSTRUCTION_DEPLOMATIC_TENT_LEVEL1);
		createImage(uiLayer, option7_camp, Const.CONSTRUCTION_DEPLOMATIC_TENT_LEVEL1, 1.8f);
		//option8_camp = graphics().createImageLayer(Const.CONSTRUCTION_MAGE_TENT_LEVEL1);
		//createImage(uiLayer, option8_camp, Const.CONSTRUCTION_MAGE_TENT_LEVEL1, 2.1f);

		infoPanel = graphics().createImageLayer(Const.INFO_PANEL_IMAGE);
		gold = new Digits(uiLayer, Const.GOLD_X, Const.GOLD_Y, Const.GOLD_SCALE, 1.0f);

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
					if(player.getSelectedNode() != null & player.getNodeToBeMapped() == null) {
						if(player.getId() == player.getSelectedNode().getPlayer() & player.getSelectedNode().getMapping() == null) {
							player.setNodeToBeMapped(player.getSelectedNode());
							//TODO : add some graphical indication
						}
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
					if(player.getSelectedNode() != null) {
						if(player.getSelectedNode().getMapping() != null & player.getId() == player.getSelectedNode().getPlayer()) {
							if(player.getSelectedNode().getBase() instanceof City)
								player.getSelectedNode().unMap();
							else if(player.getSelectedNode().getBase() instanceof Camp) {
								player.getSelectedNode().getMappedNode().unMap();
							}
						}
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
		option1_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.PALACE_COST) {
									player.setGold(player.getGold()-Const.PALACE_COST);
									//base.setCityLevel(base.getCityLevel() + 1);
									base.buildPalace(graphLayer, Const.PALACE_LEVEL1);
								}
							}
						}
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
		option2_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.BAZAR_FOOD_COST) {
									player.setGold(player.getGold()-Const.BAZAR_FOOD_COST);
									//TODO : check level
									base.buildBazarFood(graphLayer, Const.BAZAR_FOOD_LEVEL1);
								}
							}
						}
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
		option3_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.BAZAR_CHINA_COST) {
									player.setGold(player.getGold()-Const.BAZAR_CHINA_COST);
									//TODO : check level
									base.buildBazarChina(graphLayer, Const.BAZAR_CHINA_LEVEL1);
								}
							}
						}
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
		option4_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.BAZAR_CARPET_COST) {
									player.setGold(player.getGold()-Const.BAZAR_CARPET_COST);
									//TODO : check level
									base.buildBazarCarpet(graphLayer, Const.BAZAR_CARPET_LEVEL1);
								}
							}
						}
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

		/* Building GARDEN */
		option5_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.GARDEN_COST) {
									player.setGold(player.getGold()-Const.GARDEN_COST);
									//TODO : check garden level
									base.buildGarden(graphLayer, Const.GARDEN_LEVEL1);
								}
							}
						}
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
		
		/* Building SMITHY */
		option6_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.SMITHY_COST) {
									player.setGold(player.getGold()-Const.SMITHY_COST);
									//TODO : check smithy level
									base.buildSmithy(graphLayer, Const.SMITHY_LEVEL1);
								}
							}
						}
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
		option7_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.TOWER_COST) {
									player.setGold(player.getGold()-Const.TOWER_COST);
									//base.setTowerLevel(base.getTowerLevel() + 1);
									base.buildTower(graphLayer, Const.TOWER_LEVEL1);
								}
							}
						}
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

		/* Building WALL */
		option8_city.addListener(new Mouse.Listener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City){
								if(player.getGold() >= Const.WALL_COST) {
									player.setGold(player.getGold()-Const.WALL_COST);
									//base.setWallLevel(base.getWallLevel() + 1);
									base.buildWall(graphLayer, Const.WALL_FRONT_LEVEL1, Const.WALL_BACK_LEVEL1, Const.WALL_RIGHT_LEVEL1, Const.WALL_LEFT_LEVEL1);
								}
							}
						}
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

		/* Building COMMAND TENT */
		option2_camp.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.COMMAND_TENT_COST) {
										player.setGold(player.getGold()-Const.COMMAND_TENT_COST);
										//base.setCommandTentLevel(base.getCommandTentLevel() + 1);
										base.buildCommandTent(graphLayer, Const.COMMAND_TENT_LEVEL1);
									}
								}
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

		/* Building SOLDIER TENT */
		option3_camp.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.SOLDIER_TENT_COST) {
										player.setGold(player.getGold()-Const.SOLDIER_TENT_COST);
										base.setSoldierTentLevel(base.getSoldierTentLevel() + 1);
										base.buildSoldierTent(graphLayer, Const.SOLDIER_TENT_LEVEL1);
									}
								}
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
	
		/* Building MAGE TENT */
		option4_camp.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.MAGE_TENT_COST) {
										player.setGold(player.getGold()-Const.MAGE_TENT_COST);
										//base.setMageTentLevel(base.getMageTentLevel() + 1);
										base.buildMageTent(graphLayer, Const.MAGE_TENT_LEVEL1);
									}
								}
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
	
		/* Building HEALER TENT */
		option5_camp.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.HEALER_TENT_COST) {
										player.setGold(player.getGold()-Const.HEALER_TENT_COST);
										//base.setHealerTentLevel(base.getHealerTentLevel() + 1);
										base.buildHealerTent(graphLayer, Const.HEALER_TENT_LEVEL1);
									}
								}
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
	
		/* Building SUPPLY TENT */
		option6_camp.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.SUPPLY_TENT_COST) {
										player.setGold(player.getGold()-Const.SUPPLY_TENT_COST);
										//base.setSupplyTentLevel(base.getSupplyTentLevel() + 1);
										base.buildSupplyTent(graphLayer, Const.SUPPLY_TENT_LEVEL1);
									}
								}
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
	
		/* Building DEPLOMATIC TENT */
		option7_camp.addListener(new Mouse.Listener() {
				Base base;
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode() != null)
							if(player.getId() == player.getSelectedNode().getPlayer()) {
								base = player.getSelectedNode().getBase();
								if(base instanceof Camp){
									if(player.getGold() >= Const.DEPLOMATIC_TENT_COST) {
										player.setGold(player.getGold()-Const.DEPLOMATIC_TENT_COST);
										//base.setDeplomaticTentLevel(base.getDeplomaticTentLevel() + 1);
										base.buildDeplomaticTent(graphLayer, Const.DEPLOMATIC_TENT_LEVEL1);
									}
								}
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

	public void setGold(int amount) {
		gold.setDigits(amount);
	}

	/**
	 * Activates the empty Construction panel
	 * @param active - boolean
	 */
	public void setEmptyConstruction(boolean active) {
		option1_empty.setVisible(active);
		option2_empty.setVisible(active);
		option3_empty.setVisible(active);
		option4_empty.setVisible(active);
		option5_empty.setVisible(active);
		option6_empty.setVisible(active);
		option7_empty.setVisible(active);
		option8_empty.setVisible(active);
	}

	/**
	 * Activates the city Construction panel
	 * @param active - boolean
	 */
	public void setCityConstruction(boolean active) {
		option1_city.setVisible(active);
		option2_city.setVisible(active);
		option3_city.setVisible(active);
		option4_city.setVisible(active);
		option5_city.setVisible(active);
		option6_city.setVisible(active);
		option7_city.setVisible(active);
		option8_city.setVisible(active);
		option1_city.setInteractive(active);
		option2_city.setInteractive(active);
		option3_city.setInteractive(active);
		option4_city.setInteractive(active);
		option5_city.setInteractive(active);
		option6_city.setInteractive(active);
		option7_city.setInteractive(active);
		option8_city.setInteractive(active);
	}

	/**
	 * Activates the camp Construction panel
	 * @param active - boolean
	 */
	public void setCampConstruction(boolean active) {
		//option1_camp.setVisible(active);
		option2_camp.setVisible(active);
		option3_camp.setVisible(active);
		option4_camp.setVisible(active);
		option5_camp.setVisible(active);
		option6_camp.setVisible(active);
		option7_camp.setVisible(active);
		//option8_camp.setVisible(active);
		//option1_camp.setInteractive(active);
		option2_camp.setInteractive(active);
		option3_camp.setInteractive(active);
		option4_camp.setInteractive(active);
		option5_camp.setInteractive(active);
		option6_camp.setInteractive(active);
		option7_camp.setInteractive(active);
		//option8_camp.setInteractive(active);
	}

	/**
	 * Control the layers of the UI construction panel based on the given attributes
	 */
	public void showConstructions(boolean isCity, boolean belongsToPlayer) {
		if(belongsToPlayer) {
			if(isCity) {
				setCityConstruction(true);
				setCampConstruction(false);
				setEmptyConstruction(false);
			}
			else
			{
				setCityConstruction(false);
				setCampConstruction(true);
				setEmptyConstruction(false);
			}
		}
		else {
			setCityConstruction(false);
			setCampConstruction(false);
			setEmptyConstruction(true);
		}
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