package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

public final class Const 
{

    public static final int UPDATE_RATE = 25;

    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;
	
    public static int WORLD_WIDTH = 1800;
    public static int WORLD_HEIGHT = 1400;

	public static int FRAME_SIZE = 90;//Adjust this only
    public static int WORLD_ORIGIN_X = 0 - FRAME_SIZE;
    public static int WORLD_ORIGIN_Y = 0 - FRAME_SIZE;
    public static int WORLD_END_WIDTH = WORLD_WIDTH + FRAME_SIZE;
    public static int WORLD_END_HEIGHT = WORLD_HEIGHT + FRAME_SIZE;

    public static float BACKGROUND_DEPTH = 1.0f;
    public static float GRAPH_DEPTH = 2.0f;

    public static float BASE_SCALE = 0.05f;
	public static float BASE_DEPTH = 3.0f;

    public static float MIN_CITY_DISTANCE = 60;
    //public static float MAX_CITY_DISTANCE = WORLD_WIDTH-100;
    public static float CITY_GRAPH_WIDTH = WORLD_WIDTH-80;
    public static float CITY_GRAPH_HEIGHT = WORLD_HEIGHT/(4.5f);

    public static float MIN_CAMP_DISTANCE = 40;
    //public static float MAX_CITY_DISTANCE = WORLD_WIDTH-100;
    public static float CAMP_GRAPH_WIDTH = WORLD_WIDTH-100;
    public static float CAMP_GRAPH_HEIGHT = WORLD_HEIGHT/(4.7f);

    public static float ROAD_VISIBLE_DEPTH = 2.5f;
    public static float ROAD_HIDDEN_DEPTH = 0.0f;
    public static float ROAD_WIDTH = 0.4f;

	public static Image FRAME_IMAGE;
    public static Image BACKGROUND_IMAGE;
	public static Image CITY_BASE_IMAGE;
	public static Image CAMP_BASE_IMAGE;
    public static Image ROAD_IMAGE;

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
    }
}
