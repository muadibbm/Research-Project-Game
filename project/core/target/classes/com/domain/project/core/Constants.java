package com.domain.project.core;

public final class Constants 
{
	public static float WINDOW_WIDTH = 800;
	public static float WINDOW_HEIGHT = 600;
	
    private Constants(){
    //prevents the caller from creating an instantce of this class
    throw new AssertionError();
  }
}
