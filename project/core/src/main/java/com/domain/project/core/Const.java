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
    
    public static int WORLD_WIDTH = 5600;
    public static int WORLD_HEIGHT = 3600;
    
    public static float MIN_CITY_DISTANCE = 100;
    public static float CITY_GRAPH_X = 100;
    public static float CITY_GRAPH_Y = 100;
    public static float CITY_GRAPH_WIDTH = 3400;
    public static float CITY_GRAPH_HEIGHT = 1200;
    
    public static float MIN_CAMP_DISTANCE = 60;

    public static Image BASE_IMAGE;
    public static Image BACKGROUND_IMAGE;

    private Const() {
        //prevents the caller from creating an instantce of this class
        throw new AssertionError();
    }
    
    public static void loadImages() {
//        BACKGROUND_IMAGE = assets().getImage("images/Background/texture_wall_painted15.png");
        BACKGROUND_IMAGE = assets().getImage("images/temp/cat.jpg");
        BASE_IMAGE = assets().getImage("images/City/texture_trees2.png");
    }
}
