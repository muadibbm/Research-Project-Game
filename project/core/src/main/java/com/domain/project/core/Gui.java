package com.domain.project.core;

import static playn.core.PlayN.*;

import java.util.ArrayList;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import playn.core.Mouse;

import com.domain.project.core.graph.Base;
import com.domain.project.core.graph.Camp;
import com.domain.project.core.graph.City;
import com.domain.project.core.graph.Node;

public class Gui
{
	private ImageLayer option1_empty;
	private ImageLayer option2_empty;
	private ImageLayer option3_empty;
	private ImageLayer option4_empty;
	private ImageLayer option5_empty;
	private ImageLayer option6_empty;
	private ImageLayer option7_empty;

	private ImageLayer option1_selected;
	private ImageLayer option2_selected;
	private ImageLayer option3_selected;
	private ImageLayer option4_selected;
	private ImageLayer option5_selected;
	private ImageLayer option6_selected;
	private ImageLayer option7_selected;
	private ImageLayer option8_selected;

	private ImageLayer option1_city;//TOWN_HALL
	private ImageLayer option2_city;//FOOD_BAZAR
	private ImageLayer option3_city;//CHINA_BAZAR
	private ImageLayer option4_city;//CARPET_BAZAR
	private ImageLayer option5_city;//GARDEN
	private ImageLayer option6_city;//SMITHY
	private ImageLayer option7_city;//TOWER AND WALL

	private ImageLayer option1_tower;//
	/*private ImageLayer option2_camp;//
	private ImageLayer option3_camp;//
	private ImageLayer option4_camp;//
	private ImageLayer option5_camp;//
	private ImageLayer option6_camp;//
	*/

	private ImageLayer infoPanel;
	private ImageLayer populationLayer;

	private ArrayList<ImageLayer> emptyOptionsList;
	private ArrayList<ImageLayer> selectedOptionsList;
	private Digits gold;

	public Gui(final GroupLayer uiLayer)
	{
		emptyOptionsList = new ArrayList<ImageLayer>();
		selectedOptionsList = new ArrayList<ImageLayer>();

		option1_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option1_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y);
		option2_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option2_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option3_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option3_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*2*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option4_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option4_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*3*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option5_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option5_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*4*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option6_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option6_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*5*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option7_empty = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_EMPTY);
		createImage(uiLayer, option7_empty, Const.CONSTRUCTION_PANEL_EMPTY, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*6*Const.CONSTRUCTION_PANEL_EMPTY.height());

		emptyOptionsList.add(option1_empty);
		emptyOptionsList.add(option2_empty);
		emptyOptionsList.add(option3_empty);
		emptyOptionsList.add(option4_empty);
		emptyOptionsList.add(option5_empty);
		emptyOptionsList.add(option6_empty);
		emptyOptionsList.add(option7_empty);
		
		for (ImageLayer option: emptyOptionsList)
			option.setVisible(false);

		option1_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option1_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y);
		option2_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option2_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option3_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option3_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*2*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option4_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option4_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*3*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option5_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option5_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*4*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option6_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option6_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*5*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option7_selected = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_SELECTED);
		createImage(uiLayer, option7_selected, Const.CONSTRUCTION_PANEL_SELECTED, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*6*Const.CONSTRUCTION_PANEL_EMPTY.height());

		// Add all the selected options to a list
		selectedOptionsList.add(option1_selected);
		selectedOptionsList.add(option2_selected);
		selectedOptionsList.add(option3_selected);
		selectedOptionsList.add(option4_selected);
		selectedOptionsList.add(option5_selected);
		selectedOptionsList.add(option6_selected);
		selectedOptionsList.add(option7_selected);
		
		clearSelectedPanels();

		option1_city = graphics().createImageLayer(Const.CONSTRUCTION_PALACE_LEVEL1);
		createImage(uiLayer, option1_city, Const.CONSTRUCTION_PALACE_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y);
		option2_city = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_FOOD_LEVEL1);
		createImage(uiLayer, option2_city, Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option3_city = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_CHINA_LEVEL1);
		createImage(uiLayer, option3_city, Const.CONSTRUCTION_BAZAR_CHINA_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*2*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option4_city = graphics().createImageLayer(Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1);
		createImage(uiLayer, option4_city, Const.CONSTRUCTION_BAZAR_CARPET_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*3*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option5_city = graphics().createImageLayer(Const.CONSTRUCTION_GARDEN_LEVEL1);
		createImage(uiLayer, option5_city, Const.CONSTRUCTION_GARDEN_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*4*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option6_city = graphics().createImageLayer(Const.CONSTRUCTION_SMITHY_LEVEL1);
		createImage(uiLayer, option6_city, Const.CONSTRUCTION_SMITHY_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*5*Const.CONSTRUCTION_PANEL_EMPTY.height());
		option7_city = graphics().createImageLayer(Const.CONSTRUCTION_TOWER_LEVEL1);
		createImage(uiLayer, option7_city, Const.CONSTRUCTION_TOWER_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2,Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*6*Const.CONSTRUCTION_PANEL_EMPTY.height());

		option1_tower = graphics().createImageLayer(Const.CONSTRUCTION_COMMAND_TENT_LEVEL1);
		createImage(uiLayer, option1_tower, Const.CONSTRUCTION_COMMAND_TENT_LEVEL1, 
			Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.width()/2, Const.CONSTRUCTION_PANEL_Y+Const.CONSTRUCTION_PANEL_SCALE*Const.CONSTRUCTION_PANEL_EMPTY.height());
		/*option2_camp = graphics().createImageLayer(Const.CONSTRUCTION_SOLDIER_TENT_LEVEL1);
		createImage(uiLayer, option2_camp, Const.CONSTRUCTION_SOLDIER_TENT_LEVEL1, 0.6f);
		option3_camp = graphics().createImageLayer(Const.CONSTRUCTION_MAGE_TENT_LEVEL1);
		createImage(uiLayer, option3_camp, Const.CONSTRUCTION_MAGE_TENT_LEVEL1, 0.9f);
		option4_camp = graphics().createImageLayer(Const.CONSTRUCTION_HEALER_TENT_LEVEL1);
		createImage(uiLayer, option4_camp, Const.CONSTRUCTION_HEALER_TENT_LEVEL1, 1.2f);
		option5_camp = graphics().createImageLayer(Const.CONSTRUCTION_SUPPLY_TENT_LEVEL1);
		createImage(uiLayer, option5_camp, Const.CONSTRUCTION_SUPPLY_TENT_LEVEL1, 1.5f);
		option6_camp = graphics().createImageLayer(Const.CONSTRUCTION_DEPLOMATIC_TENT_LEVEL1);
		createImage(uiLayer, option6_camp, Const.CONSTRUCTION_DEPLOMATIC_TENT_LEVEL1, 1.8f);*/

		infoPanel = graphics().createImageLayer(Const.INFO_PANEL_IMAGE);
		gold = new Digits(uiLayer, Const.GOLD_X, Const.GOLD_Y, Const.GOLD_SCALE, 1.0f);

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

	private void clearSelectedPanels() {
		for (ImageLayer imageL: selectedOptionsList) {
			imageL.setVisible(false);
		}
	}

	public void addListener(final Player player, final GroupLayer graphLayer) {

		/* Building TOWN HALL */
		option1_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.PALACE_COST) {
									player.setGold(player.getGold()-Const.PALACE_COST);
									//base.setCityLevel(base.getCityLevel() + 1);
									base.buildPalace(graphLayer, Const.PALACE_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 0) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building BAZAR FOOD */
		option2_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.BAZAR_FOOD_COST) {
									player.setGold(player.getGold()-Const.BAZAR_FOOD_COST);
									//TODO : check level
									base.buildBazarFood(graphLayer, Const.BAZAR_FOOD_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 1) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building BAZAR CHINA */
		option3_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.BAZAR_CHINA_COST) {
									player.setGold(player.getGold()-Const.BAZAR_CHINA_COST);
									//TODO : check level
									base.buildBazarChina(graphLayer, Const.BAZAR_CHINA_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 2) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building BAZAR CARPET */
		option4_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.BAZAR_CARPET_COST) {
									player.setGold(player.getGold()-Const.BAZAR_CARPET_COST);
									//TODO : check level
									base.buildBazarCarpet(graphLayer, Const.BAZAR_CARPET_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 3) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building GARDEN */
		option5_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.GARDEN_COST) {
									player.setGold(player.getGold()-Const.GARDEN_COST);
									//TODO : check garden level
									base.buildGarden(graphLayer, Const.GARDEN_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 4) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building SMITHY */
		option6_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.SMITHY_COST) {
									player.setGold(player.getGold()-Const.SMITHY_COST);
									//TODO : check smithy level
									base.buildSmithy(graphLayer, Const.SMITHY_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 5) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building WALL and TOWER for city */
		option7_city.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null) {
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof City) {
								if(player.getGold() >= Const.TOWER_COST) {
									player.setGold(player.getGold()-Const.TOWER_COST);
									//base.setTowerLevel(base.getTowerLevel() + 1);
									base.buildTower(graphLayer, Const.TOWER_LEVEL1);
									base.buildWall(graphLayer, Const.WALL_FRONT_LEVEL1, Const.WALL_BACK_LEVEL1, Const.WALL_RIGHT_LEVEL1, Const.WALL_LEFT_LEVEL1);
									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 6) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(true);
											selectedOptionsList.get(i).setVisible(false);
										}
									}
								}
							}
						}
					}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
			}
		});

		/* Building DEFENSIVE TOWER */
		option1_tower.addListener(new Mouse.LayerListener() {
			Base base;
			@Override
			public void onMouseDown(Mouse.ButtonEvent event) {
				if(event.button() == Mouse.BUTTON_LEFT) {
					if(player.getSelectedNode() != null)
						if(player.getId() == player.getSelectedNode().getPlayer()) {
							base = player.getSelectedNode().getBase();
							if(base instanceof Camp) {
								if(player.getGold() >= Const.COMMAND_TENT_COST) {
									//player.setGold(player.getGold()-Const.COMMAND_TENT_COST);
									//base.setCommandTentLevel(base.getCommandTentLevel() + 1);
									base.buildCommandTent(graphLayer, Const.COMMAND_TENT_LEVEL1);

									for (int i = 0; i < selectedOptionsList.size(); i++) {
										if (i == 1) {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(true);
										} else {
											emptyOptionsList.get(i).setVisible(false);
											selectedOptionsList.get(i).setVisible(false);
											
											if (i == 0 || i == (emptyOptionsList.size() - 1)) {
												emptyOptionsList.get(i).setVisible(false);
											}
										}
									}
								}
							}
						}
				}
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseDrag(Mouse.MotionEvent event) {
				
			}
			@Override //Called when the mouse is dragged.
			public void	onMouseOut(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse leaves a Layer.
			public void	onMouseOver(Mouse.MotionEvent event) {
			
			}
			@Override //Called when the mouse enters a Layer.
			public void	onMouseUp(Mouse.ButtonEvent event) {
			
			}
			@Override //Called when the mouse is released.
			public void	onMouseWheelScroll(Mouse.WheelEvent event) {
			
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
	
	public void hidePopulation() {
		if(populationLayer != null)
			populationLayer.destroy();
	}

	public void setGold(int amount) {
		gold.setDigits(amount);
	}

	//	/**
	//	 * Activates the empty Construction panel
	//	 * @param active - boolean
	//	 */
	//	public void setEmptyConstruction(boolean active) {
	//		option1_empty.setVisible(active);
	//		option2_empty.setVisible(active);
	//		option3_empty.setVisible(active);
	//		option4_empty.setVisible(active);
	//		option5_empty.setVisible(active);
	//		option6_empty.setVisible(active);
	//		option7_empty.setVisible(active);
	//		option8_empty.setVisible(active);
	//	}

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
		option1_city.setInteractive(active);
		option2_city.setInteractive(active);
		option3_city.setInteractive(active);
		option4_city.setInteractive(active);
		option5_city.setInteractive(active);
		option6_city.setInteractive(active);
		option7_city.setInteractive(active);
	}

	/**
	 * Activates the camp Construction panel
	 * @param active - boolean
	 */
	public void setCampConstruction(boolean active) {
		option1_tower.setVisible(active);
		option1_tower.setInteractive(active);
		/*option2_camp.setVisible(active);
		option3_camp.setVisible(active);
		option4_camp.setVisible(active);
		option5_camp.setVisible(active);
		option6_camp.setVisible(active);
		option2_camp.setInteractive(active);
		option3_camp.setInteractive(active);
		option4_camp.setInteractive(active);
		option5_camp.setInteractive(active);
		option6_camp.setInteractive(active);*/
	}

	/**
	 * Control the layers of the UI construction panel based on the given attributes
	 * @param node 
	 */
	public void showConstructions(Node node, boolean isCity, boolean belongsToPlayer) {
		for (ImageLayer option: selectedOptionsList)
			option.setVisible(false);
		for (ImageLayer option: emptyOptionsList)
				option.setVisible(false);
		
		if(belongsToPlayer) {
			if(isCity) {
				// City
				for (ImageLayer option: emptyOptionsList)
					option.setVisible(true);
				setCityConstruction(true);
				setCampConstruction(false);
			} else {
				// Camp
				emptyOptionsList.get(1).setVisible(true);
				setCityConstruction(false);
				setCampConstruction(true);
			}
		} else {
			setCityConstruction(false);
			setCampConstruction(false);
		}
	}

	private void createImage(final GroupLayer uiLayer, final ImageLayer layerImage, Image optionImage, final float x_offset, final float y_offset) {
		optionImage.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				layerImage.setOrigin(image.width()/2f, image.height()/2f);
				layerImage.setTranslation(x_offset, y_offset);
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