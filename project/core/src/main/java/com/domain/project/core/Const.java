package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;

public final class Const 
{
	//graphics().setSize(graphics().screenWidth(),graphics().screenHeight());
    //Each device has different screen parameters (see http://playn-2011.appspot.com/slides/index.html#19)
	
    public static final int UPDATE_RATE = 25;

	public static int WINDOW_WIDTH = 3 * graphics().screenWidth() / 4;
    public static int WINDOW_HEIGHT = 3 * graphics().screenHeight() / 4;
    //public static int WINDOW_WIDTH = 1024;
    //public static int WINDOW_HEIGHT = 768;

	public static int WORLD_WIDTH = WINDOW_WIDTH * 4;
    public static int WORLD_HEIGHT = WINDOW_HEIGHT * 4;
	public static int WORLD_ORIGIN_X = 0;
    public static int WORLD_ORIGIN_Y = 0;
	
	public static int BACKGROUND_WORLD_WIDTH = 7 * WORLD_WIDTH/3;
	public static int BACKGROUND_WORLD_HEIGHT = 7 * WORLD_HEIGHT/3;
	public static int BACKGROUND_WORLD_ORIGIN_X = WORLD_ORIGIN_X - WORLD_WIDTH/3;
	public static int BACKGROUND_WORLD_ORIGIN_Y = WORLD_ORIGIN_Y - WORLD_HEIGHT/3;

    public static float BACKGROUND_DEPTH = 1.0f;
	//public static float graphXOffset = 200;
	public static float graphYOffset = WORLD_WIDTH / 30;
	public static float campCityGraphGap = WORLD_WIDTH / 30;
	public static int STARTING_GOLD = 1000;
	
    public static float BASE_CITY_SCALE = 0.1f;
	public static float BASE_CAMP_SCALE = 0.075f;
	public static float CITY_SELECTION_SCALE1 = 0.35f;
	public static float CITY_SELECTION_SCALE2 = 0.36f;
	public static float CAMP_SELECTION_SCALE = 0.14f;
	public static float BASE_DEPTH = 3.0f;
	public static float BASE_ALPHA = 0.45f;
	public static float SELECTION_ALPHA = 0.75f;
	public static float SELECTED_BASE_ALPHA = 1.0f;

    public static float MIN_CITY_DISTANCE = 150;
    //public static float MAX_CITY_DISTANCE = WORLD_WIDTH-100;
    public static float CITY_GRAPH_WIDTH = WORLD_WIDTH/(5.0f);
    public static float CITY_GRAPH_HEIGHT = WORLD_HEIGHT-WORLD_HEIGHT/10;
	public static float PALACE_X = 0.0f;
	public static float PALACE_Y = -23.0f;
	public static float PALACE_DEPTH = 3.2f;
	public static float PALACE_SCALE = 0.048f;
	public static float BAZAR_FOOD_X = -17.5f;
	public static float BAZAR_FOOD_Y = -2.0f;
	public static float BAZAR_FOOD_DEPTH = 3.42f;
	public static float BAZAR_FOOD_SCALE = 0.015f;
	public static float BAZAR_CHINA_X = -16.0f;
	public static float BAZAR_CHINA_Y = -9.5f;
	public static float BAZAR_CHINA_DEPTH = 3.41f;
	public static float BAZAR_CHINA_SCALE = 0.014f;
	public static float BAZAR_CARPET_X = -20.0f;
	public static float BAZAR_CARPET_Y = 7.0f;
	public static float BAZAR_CARPET_DEPTH = 3.43f;
	public static float BAZAR_CARPET_SCALE = 0.016f;
	public static float GARDEN_X = 18.8f;
	public static float GARDEN_Y = -5.0f;
	public static float CARAVAN_DEPTH = 4.0f;
	public static float CARAVAN_ALPHA = 0.93f;
	public static float CARAVAN_SCALE = 0.023f;
	public static int CARAVAN_TRADING_TIME = 5000; // in milliseconds
	public static int CARAVAN_SPEED = 100;
	public static int BASE_SELECTION_SPEED = 1000;
	public static float GARDEN_DEPTH = 3.3f;
	public static float GARDEN_SCALE = 0.029f;
	public static float SMITHY_X = 21.0f;
	public static float SMITHY_Y = 15.0f;
	public static float SMITHY_DEPTH = 3.4f;
	public static float SMITHY_SCALE = 0.021f;
	public static float TOWER1_X = 29.0f;
	public static float TOWER1_Y = 19.0f;
	public static float TOWER2_X = -29.0f;
	public static float TOWER2_Y = 19.0f;
	public static float TOWER3_X = 20.0f;
	public static float TOWER3_Y = -24.0f;
	public static float TOWER4_X = -20.0f;
	public static float TOWER4_Y = -24.0f;
	public static float TOWER_GATE1_X = 12.0f;
	public static float TOWER_GATE1_Y = 19.0f;
	public static float TOWER_GATE2_X = -11.0f;
	public static float TOWER_GATE2_Y = 19.0f;
	public static float TOWER_GATE_SCALE = 0.017f;
	public static float TOWER_FRONT_DEPTH = 3.7f;
	public static float TOWER_FRONT_SCALE = 0.015f;
	public static float TOWER_BACK_DEPTH = 3.2f;
	public static float TOWER_BACK_SCALE = 0.0072f;
	public static float WALL_FRONT_X = 0.0f;
	public static float WALL_FRONT_Y = 22.0f;
	public static float WALL_BACK_X = 0.0f;
	public static float WALL_BACK_Y = -23.2f;
	public static float WALL_LEFT_X = -29.29f;
	public static float WALL_LEFT_Y = 1.67f;
	public static float WALL_RIGHT_X = 29.29f;
	public static float WALL_RIGHT_Y = 1.67f;
	public static float WALL_FRONT_DEPTH = 3.9f;
	public static float WALL_FRONT_SCALE = 0.01333f*3;
	public static float WALL_FRONT_SCALE_VERTICAL = 0.0223f*3;
	public static float WALL_BACK_DEPTH = 3.1f;
	public static float WALL_BACK_SCALE = 0.00812f*3;
	public static float WALL_BACK_SCALE_VERTICAL = 0.0109f*3;
	public static float WALL_SIDE_SCALE = 0.016f;
	public static float WALL_SIDE_SCALE_VERTICAL = 0.029f;
	
	public static int PALACE_COST = 100;
	public static int BAZAR_FOOD_COST = 40;
	public static int BAZAR_CHINA_COST = 50;
	public static int BAZAR_CARPET_COST = 60;
	public static int GARDEN_COST = 80;
	public static int SMITHY_COST = 70;
	public static int TOWER_COST = 60;
	public static int WALL_COST = 50;
	
	//Tower base = camp
    public static float MIN_CAMP_DISTANCE = 60;
	public static float CAMP_SCALE = 1.0f;
    //public static float MAX_CITY_DISTANCE = WORLD_WIDTH-100;
    public static float CAMP_GRAPH_WIDTH = WORLD_WIDTH/(5.0f);
    public static float CAMP_GRAPH_HEIGHT = WORLD_HEIGHT-WORLD_HEIGHT/10;
	/*public static float SOLDIER_TENT_X = 23.0f;
	public static float SOLDIER_TENT_Y = 3.2f;
	public static float SOLDIER_TENT_DEPTH = 3.3f;
	public static float SOLDIER_TENT_SCALE = 0.066f*CAMP_SCALE;
	public static float MAGE_TENT_X = 0.0f;
	public static float MAGE_TENT_Y = -10.0f;
	public static float MAGE_TENT_DEPTH = 3.1f;
	public static float MAGE_TENT_SCALE = 0.043f*CAMP_SCALE;
	public static float HEALER_TENT_X = -17.9f;
	public static float HEALER_TENT_Y = 0.9f;
	public static float HEALER_TENT_DEPTH = 3.3f;
	public static float HEALER_TENT_SCALE = 0.066f*CAMP_SCALE;
	public static float SUPPLY_TENT_X = 0.0f;
	public static float SUPPLY_TENT_Y = 23.0f;
	public static float SUPPLY_TENT_DEPTH = 3.5f;
	public static float SUPPLY_TENT_SCALE = 0.067f*CAMP_SCALE;
	public static float DEPLOMATIC_TENT_X = 0.0f;
	public static float DEPLOMATIC_TENT_Y = 10.0f;
	public static float DEPLOMATIC_TENT_DEPTH = 3.4f;
	public static float DEPLOMATIC_TENT_SCALE = 0.068f*CAMP_SCALE;
	
	public static int SOLDIER_TENT_COST = 60;
	public static int MAGE_TENT_COST = 70;
	public static int HEALER_TENT_COST = 60;
	public static int SUPPLY_TENT_COST = 50;
	public static int DEPLOMATIC_TENT_COST = 80;*/
	public static float COMMAND_TENT_X = 0.0f;
	public static float COMMAND_TENT_Y = 0.0f;
	public static float COMMAND_TENT_DEPTH = 3.3f;
	public static float COMMAND_TENT_SCALE = 0.075f*CAMP_SCALE;
	public static int COMMAND_TENT_COST = 90;

	public static float TREE_OF_LIFE_SCALE = 240.0f;
	public static float TREE_OF_LIFE_X = WORLD_WIDTH/2 - TREE_OF_LIFE_SCALE/2;
	public static float TREE_OF_LIFE_Y = WORLD_HEIGHT - WORLD_HEIGHT/10;
	
	public static float DEEVE_CAVE_SCALE = 250.0f;
	public static int DEEVE_WAVE_WAITING_TIME = 5000;
	public static float DEEVE_CAVE_X = WORLD_WIDTH/2 - DEEVE_CAVE_SCALE/2;
	public static float DEEVE_CAVE_Y = WORLD_HEIGHT/10000;
	public static float DEEVE_INITIAL_POS_X = WORLD_WIDTH/2;
	public static float DEEVE_INITIAL_POS_Y = WORLD_HEIGHT/10;
	public static float DEEVE_SCALE = 0.17f;
	public static float DEEVE_ALPHA = 1.0f;
	public static float DEEVE_DEPTH = 3.0f;
	public static int DEEVE_SPEED = 10;
	
    public static float ROAD_DEPTH = 2.4f;
    public static float ROAD_WIDTH = 0.5f;
	
	public static float MAPPING_DEPTH = 2.5f;
	public static float MAPPING_WIDTH = 0.4f;
	public static float MAPPING_POINT_X = 0.0f;
	public static float MAPPING_POINT_Y = 0.0f;
	public static float MAPPING_POINT_SCALE = 1.0f;
	
	public static float UI_DEPTH = 3.0f;
	public static float CONSTRUCTION_PANEL_X = 0.0f;
	public static float CONSTRUCTION_PANEL_Y = WINDOW_WIDTH/13;
	public static float CONSTRUCTION_PANEL_SCALE = 0.15f;
	public static float INFO_PANEL_X = 0.0f;
	public static float INFO_PANEL_Y = 0.0f;
	public static float INFO_PANEL_SCALE = 0.3f/1.9f;
	public static float POPULATION_SCALE = 0.7f/1.9f;
	public static float POPULATION_X = INFO_PANEL_X + 2*assets().getImage("images/UI/infoPanel.png").width()/3*INFO_PANEL_SCALE;
	public static float POPULATION_Y = assets().getImage("images/UI/infoPanel.png").height()/3*INFO_PANEL_SCALE;
	public static float GOLD_SCALE = 0.6f/1.9f;
	public static float GOLD_X = INFO_PANEL_X + assets().getImage("images/UI/infoPanel.png").width()/3*INFO_PANEL_SCALE;
	public static float GOLD_Y = assets().getImage("images/UI/infoPanel.png").height()/3*INFO_PANEL_SCALE;
	//public static float MAP_BUTTON_X = INFO_PANEL_X + 2*WINDOW_WIDTH/3;
	//public static float MAP_BUTTON_Y = WINDOW_HEIGHT-90;
	//public static float BUTTON_SCALE = 0.3f;
	//public static float UNMAP_BUTTON_X = 2*WINDOW_WIDTH/3+110;
	//public static float UNMAP_BUTTON_Y = WINDOW_HEIGHT-90;
	
	public static float TREE_SHADOW_DEPTH = 2.65f;
	public static float TREE_DEPTH = 2.7f;
	public static float TREE_SCALE = 0.13f;
	public static int MAX_TREE_NUMBER = 5000;
	public static float MIN_INBETWEEN_TREE_DISTANCE = 10.0f;
	public static float MIN_NODE_TREE_DISTANCE = 100.0f;

	public static Image FRAME_IMAGE;
    public static Image BACKGROUND_IMAGE;
	public static Image BACKGROUND_PATH_IMAGE;
	public static Image CITY_BASE_IMAGE;
	public static Image CITY_BASE_SELECTED_IMAGE1;
	public static Image CITY_BASE_SELECTED_IMAGE2;
	public static Image CITY_BASE_SELECTED_IMAGE3;
	public static Image CITY_BASE_SELECTED_IMAGE4;
	public static Image CITY_BASE_SELECTED_IMAGE5;
	public static Image CITY_BASE_SELECTED_IMAGE6;
	public static Image CAMP_BASE_IMAGE;
    public static Image ROAD_IMAGE;
    public static Image CARAVAN_IMAGE;
    public static Image DEEVE_IMAGE;
	public static Image MAP_IMAGE;
	public static Image CONSTRUCTION_PANEL_EMPTY;
	public static Image CONSTRUCTION_PANEL_SELECTED;
	public static Image CONSTRUCTION_PALACE_LEVEL1;
	public static Image CONSTRUCTION_TOWER_LEVEL1;
	public static Image CONSTRUCTION_BAZAR_FOOD_LEVEL1;
	public static Image CONSTRUCTION_BAZAR_CHINA_LEVEL1;
	public static Image CONSTRUCTION_BAZAR_CARPET_LEVEL1;
	public static Image CONSTRUCTION_SMITHY_LEVEL1;
	public static Image CONSTRUCTION_WALL_LEVEL1;
	public static Image CONSTRUCTION_GARDEN_LEVEL1;
	public static Image CONSTRUCTION_COMMAND_TENT_LEVEL1;
	public static Image CONSTRUCTION_SOLDIER_TENT_LEVEL1;
	public static Image CONSTRUCTION_MAGE_TENT_LEVEL1;
	public static Image CONSTRUCTION_HEALER_TENT_LEVEL1;
	public static Image CONSTRUCTION_SUPPLY_TENT_LEVEL1;
	public static Image CONSTRUCTION_DEPLOMATIC_TENT_LEVEL1;
	//public static Image MAP_BUTTON_IMAGE;
	//public static Image UNMAP_BUTTON_IMAGE;
	public static Image INFO_PANEL_IMAGE;
	public static Image N0_IMAGE;
	public static Image N1_IMAGE;
	public static Image N2_IMAGE;
	public static Image N3_IMAGE;
	public static Image N4_IMAGE;
	public static Image N5_IMAGE;
	public static Image N6_IMAGE;
	public static Image N7_IMAGE;
	public static Image N8_IMAGE;
	public static Image N9_IMAGE;
	public static Image TREE1_IMAGE;
	public static Image TREE1_SHADOW_IMAGE;
	public static Image PALACE_LEVEL1;
	public static Image TOWER_LEVEL1;
	public static Image BAZAR_FOOD_LEVEL1;
	public static Image BAZAR_CHINA_LEVEL1;
	public static Image BAZAR_CARPET_LEVEL1;
	public static Image GARDEN_LEVEL1;
	public static Image SMITHY_LEVEL1;
	public static Image WALL_FRONT_LEVEL1;
	public static Image WALL_BACK_LEVEL1;
	public static Image WALL_LEFT_LEVEL1;
	public static Image WALL_RIGHT_LEVEL1;
	public static Image COMMAND_TENT_LEVEL1;
	/*public static Image SOLDIER_TENT_LEVEL1;
	public static Image MAGE_TENT_LEVEL1;
	public static Image HEALER_TENT_LEVEL1;
	public static Image SUPPLY_TENT_LEVEL1;
	public static Image DEPLOMATIC_TENT_LEVEL1;*/
	public static Image DEEVE_CAVE;
	public static Image TREE_OF_LIFE;
	
	public static float VISIBLE = 1.0f;
	public static float HIDDEN_ROAD = 0.07f;
	public static float HIDDEN_MAPPING = 0.5f;
	public static float SHADOW = 0.45f;

    private Const() {
        //prevents the caller from creating an instance of this class
        throw new AssertionError();
    }

    public static void loadImages() {
		FRAME_IMAGE = assets().getImage("images/Background/sampleFrame.png");
        BACKGROUND_IMAGE = assets().getImage("images/Background/texture_wall_painted15.png");
		BACKGROUND_PATH_IMAGE = assets().getImage("images/Deeve/texture_wall_painted16.png");
        CAMP_BASE_IMAGE = assets().getImage("images/Tower/towerBase.png");
        CITY_BASE_IMAGE = assets().getImage("images/City/cityBase.png");        
        CITY_BASE_SELECTED_IMAGE1 = assets().getImage("images/City/buildingSelection1.png");
        CITY_BASE_SELECTED_IMAGE2 = assets().getImage("images/City/buildingSelection2.png");
		CITY_BASE_SELECTED_IMAGE3 = assets().getImage("images/City/buildingSelection3.png");
        CITY_BASE_SELECTED_IMAGE4 = assets().getImage("images/City/buildingSelection4.png");
		CITY_BASE_SELECTED_IMAGE5 = assets().getImage("images/City/buildingSelection5.png");
        CITY_BASE_SELECTED_IMAGE6 = assets().getImage("images/City/buildingSelection6.png");
        ROAD_IMAGE = assets().getImage("images/Road/sampleRoad.png");
        CARAVAN_IMAGE = assets().getImage("images/Caravan/Caravan.png");
        DEEVE_IMAGE = assets().getImage("images/Deeve/texture_deeve1.png");
        MAP_IMAGE = assets().getImage("images/Road/sampleMapping.png");
		CONSTRUCTION_PANEL_EMPTY = assets().getImage("images/UI/constructionPanel.png");
		CONSTRUCTION_PANEL_SELECTED = assets().getImage("images/UI/constructionPanelSelected.png");
		CONSTRUCTION_PALACE_LEVEL1 = assets().getImage("images/UI/constructionPanel_palace.png");
		CONSTRUCTION_BAZAR_CARPET_LEVEL1 = assets().getImage("images/UI/constructionPanel_carpetMarket.png");
		CONSTRUCTION_BAZAR_CHINA_LEVEL1 = assets().getImage("images/UI/constructionPanel_chinaMarket.png");
		CONSTRUCTION_BAZAR_FOOD_LEVEL1 = assets().getImage("images/UI/constructionPanel_foodMarket.png");
		CONSTRUCTION_GARDEN_LEVEL1 = assets().getImage("images/UI/constructionPanel_garden.png");
		CONSTRUCTION_SMITHY_LEVEL1 = assets().getImage("images/UI/constructionPanel_smithy.png");
		CONSTRUCTION_TOWER_LEVEL1 = assets().getImage("images/UI/constructionPanel_tower.png");
		CONSTRUCTION_WALL_LEVEL1 = assets().getImage("images/UI/constructionPanel_wall.png");
		CONSTRUCTION_COMMAND_TENT_LEVEL1 = assets().getImage("images/UI/constructionPanel_commandTent.png");
		/*CONSTRUCTION_SOLDIER_TENT_LEVEL1 = assets().getImage("images/UI/constructionPanel_soldierTent.png");
		CONSTRUCTION_MAGE_TENT_LEVEL1 = assets().getImage("images/UI/constructionPanel_mageTent.png");
		CONSTRUCTION_HEALER_TENT_LEVEL1 = assets().getImage("images/UI/constructionPanel_healerTent.png");
		CONSTRUCTION_SUPPLY_TENT_LEVEL1 = assets().getImage("images/UI/constructionPanel_supplyTent.png");
		CONSTRUCTION_DEPLOMATIC_TENT_LEVEL1 = assets().getImage("images/UI/constructionPanel_deplomaticTent.png");*/
		//MAP_BUTTON_IMAGE = assets().getImage("images/UI/mapButton.png");
		//UNMAP_BUTTON_IMAGE = assets().getImage("images/UI/unmapButton.png");
		INFO_PANEL_IMAGE = assets().getImage("images/UI/infoPanel.png");
		N0_IMAGE = assets().getImage("images/UI/0.png");
		N1_IMAGE = assets().getImage("images/UI/1.png");
		N2_IMAGE = assets().getImage("images/UI/2.png");
		N3_IMAGE = assets().getImage("images/UI/3.png");
		N4_IMAGE = assets().getImage("images/UI/4.png");
		N5_IMAGE = assets().getImage("images/UI/5.png");
		N6_IMAGE = assets().getImage("images/UI/6.png");
		N7_IMAGE = assets().getImage("images/UI/7.png");
		N8_IMAGE = assets().getImage("images/UI/8.png");
		N9_IMAGE = assets().getImage("images/UI/9.png");
		TREE1_IMAGE = assets().getImage("images/Background/texture_trees1.png");
		TREE1_SHADOW_IMAGE = assets().getImage("images/Background/texture_trees1_shadow.png");
		PALACE_LEVEL1 = assets().getImage("images/City/Palace_level1.png");
		TOWER_LEVEL1 = assets().getImage("images/City/Tower_level1.png");
		BAZAR_FOOD_LEVEL1 = assets().getImage("images/City/Food_Bazar_Level1.png");
		BAZAR_CHINA_LEVEL1 = assets().getImage("images/City/China_Bazar_Level1.png");
		BAZAR_CARPET_LEVEL1 = assets().getImage("images/City/Carpet_Bazar_Level1.png");
		WALL_FRONT_LEVEL1 = assets().getImage("images/City/Wall_front_level1.png");
		WALL_BACK_LEVEL1 = assets().getImage("images/City/Wall_back_level1.png");
		WALL_LEFT_LEVEL1 = assets().getImage("images/City/Wall_left_level1.png");
		WALL_RIGHT_LEVEL1 = assets().getImage("images/City/Wall_right_level1.png");
		GARDEN_LEVEL1 = assets().getImage("images/City/Garden_Level1.png");
		SMITHY_LEVEL1 = assets().getImage("images/City/Smithie_Level1.png");
		COMMAND_TENT_LEVEL1 = assets().getImage("images/Tower/Tower_level1.png");
		/*MAGE_TENT_LEVEL1 = assets().getImage("images/Camp/mageTent.png");
		SOLDIER_TENT_LEVEL1 = assets().getImage("images/Camp/soldierTent.png"); 
		HEALER_TENT_LEVEL1 = assets().getImage("images/Camp/healerTent.png");
		SUPPLY_TENT_LEVEL1 = assets().getImage("images/Camp/supplyTent.png");
		DEPLOMATIC_TENT_LEVEL1 = assets().getImage("images/Camp/deplomaticTent.png");*/
		DEEVE_CAVE = assets().getImage("images/Deeve/texture_div_base.png");
		TREE_OF_LIFE = assets().getImage("images/Deeve/texture_trees1.png");
    }
}
