package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

public final class Const 
{
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;
    
    public static int WORLD_WIDTH = 1600;
    public static int WORLD_HEIGHT = 1200;
	
	public static int WORLD_ORIGIN_X = -100;
	public static int WORLD_ORIGIN_Y = -100;
	public static int WORLD_END_X = WORLD_WIDTH + 100;
	public static int WORLD_END_Y = WORLD_HEIGHT + 100;
	
	public static float BACKGROUND_DEPTH = 1.0f;
	public static float GRAPH_DEPTH = 2.0f;
	public static float PATH_DEPTH = 1.5f;
    
	public static float BASE_SCALE = 0.3f;
	
    public static float MIN_CITY_DISTANCE = 100;
    public static float MAX_CITY_DISTANCE = 800;
    public static float CITY_GRAPH_X = 50;
    public static float CITY_GRAPH_Y = 50;
    public static float CITY_GRAPH_WIDTH = 1400;
    public static float CITY_GRAPH_HEIGHT = 1000;
    
    public static float MIN_CAMP_DISTANCE = 0;
    public static float MAX_CAMP_DISTANCE = 0;

	public static float ROAD_VISIBLE_DEPTH = 2.0f;
	public static float ROAD_HIDDEN_DEPTH = 0.0f;
	public static float ROAD_WIDTH = 0.5f;
	
    public static Image BASE_IMAGE;
    public static Image BACKGROUND_IMAGE;
    public static Image ROAD_IMAGE;

    private Const() {
        //prevents the caller from creating an instantce of this class
        throw new AssertionError();
    }
    
    public static void loadImages() {
        BACKGROUND_IMAGE = assets().getImage("images/Background/texture_wall_painted15.png");
//        BACKGROUND_IMAGE = assets().getImage("images/temp/cat.jpg");
        BASE_IMAGE = assets().getImage("images/City/texture_trees2.png");
        ROAD_IMAGE = assets().getImage("images/Road/sampleRoad.png");
    }
}
