package com.domain.project.core;

public final class Const 
{
    public static int WINDOW_WIDTH = 800;
    public static int WINDOW_HEIGHT = 600;
    
    private Const() {
        //prevents the caller from creating an instantce of this class
        throw new AssertionError();
     }
}
