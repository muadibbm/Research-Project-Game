package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

public final class Const 
{
    public static final int UPDATE_RATE = 25;

    public static int WINDOW_WIDTH = 1024;
    public static int WINDOW_HEIGHT = 768;

    public static int WORLD_WIDTH = 2200;
    public static int WORLD_HEIGHT = 1500;

	public static int FRAME_SIZE = 60;//Adjust this only
    public static int WORLD_ORIGIN_X = 0 - 5*FRAME_SIZE;
    public static int WORLD_ORIGIN_Y = 0 - 5*FRAME_SIZE;
    public static int WORLD_END_WIDTH = WORLD_WIDTH + FRAME_SIZE;
    public static int WORLD_END_HEIGHT = WORLD_HEIGHT + FRAME_SIZE;

    public static float BACKGROUND_DEPTH = 1.0f;
    public static float GRAPH_DEPTH = 2.0f;

    public static float BASE_CITY_SCALE = 0.05f;
	public static float BASE_CAMP_SCALE = 0.1f;
	public static float BASE_DEPTH = 3.0f;

    public static float MIN_CITY_DISTANCE = 60;
    //public static float MAX_CITY_DISTANCE = WORLD_WIDTH-100;
    public static float CITY_GRAPH_WIDTH = WORLD_WIDTH/(4.5f);
    public static float CITY_GRAPH_HEIGHT = WORLD_HEIGHT-80;

    public static float MIN_CAMP_DISTANCE = 40;
    //public static float MAX_CITY_DISTANCE = WORLD_WIDTH-100;
    public static float CAMP_GRAPH_WIDTH = WORLD_WIDTH/(4.7f);
    public static float CAMP_GRAPH_HEIGHT = WORLD_HEIGHT-100;

    public static float ROAD_VISIBLE_DEPTH = 2.5f;
    public static float ROAD_HIDDEN_DEPTH = 0.0f;
    public static float ROAD_WIDTH = 0.4f;
	
	public static float MAPPING_VISIBLE_DEPTH = 2.4f;
    public static float MAPPING_HIDDEN_DEPTH = 0.0f;
	public static float MAPPING_WIDTH = 0.3f;
	
	public static float UI_DEPTH = 3.0f;
	public static float CONSTRUCTION_PANEL_X = WINDOW_WIDTH/4;
	public static float CONSTRUCTION_PANEL_Y = 0.0f;
	public static float CONSTRUCTION_PANEL_SCALE = 0.2f;
	public static float BUTTON_SCALE = 0.3f;
	public static float MAP_BUTTON_X = WINDOW_WIDTH/3;
	public static float MAP_BUTTON_Y = WINDOW_HEIGHT-90;
	public static float UNMAP_BUTTON_X = 2*WINDOW_WIDTH/3-100;
	public static float UNMAP_BUTTON_Y = WINDOW_HEIGHT-90;

	public static Image FRAME_IMAGE;
    public static Image BACKGROUND_IMAGE;
	public static Image CITY_BASE_IMAGE;
	public static Image CAMP_BASE_IMAGE;
    public static Image ROAD_IMAGE;
	public static Image MAP_IMAGE;
	public static Image CONSTRUCTION_PANEL_IMAGE;
	public static Image MAP_BUTTON_IMAGE;
	public static Image UNMAP_BUTTON_IMAGE;

    private Const() {
        //prevents the caller from creating an instantce of this class
        throw new AssertionError();
    }

    public static void loadImages() {
//		FRAME_IMAGE = assets().getImage("images/Background/texture_sky1.png");
		FRAME_IMAGE = assets().getImage("images/Background/sampleFrame.png");
        BACKGROUND_IMAGE = assets().getImage("images/Background/texture_wall_painted15.png");
        CAMP_BASE_IMAGE = assets().getImage("images/Camp/campBase.png");
        CITY_BASE_IMAGE = assets().getImage("images/City/cityBase.png");
        ROAD_IMAGE = assets().getImage("images/Road/sampleRoad.png");
		MAP_IMAGE = assets().getImage("images/Road/sampleMapping.png");
		CONSTRUCTION_PANEL_IMAGE = assets().getImage("images/UI/costructionPanel.png");
		MAP_BUTTON_IMAGE = assets().getImage("images/UI/mapbutton.png");
		UNMAP_BUTTON_IMAGE = assets().getImage("images/UI/unmapbutton.png");
    }
}
