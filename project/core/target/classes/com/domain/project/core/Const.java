package com.domain.project.core;

public final class Const 
{
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;
	
	public static int WORLD_WIDTH = 1600;
	public static int WORLD_HEIGHT = 1200;

    private Const() {
        //prevents the caller from creating an instantce of this class
        throw new AssertionError();
    }
}
