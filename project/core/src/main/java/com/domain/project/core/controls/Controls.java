package com.domain.project.core.controls;

public class Controls {

    enum Zoom {IN, OUT, DEFAULT}; 
 
    protected static float scaleFactor = 1.0f;
//    protected static float scaleRate = 0.1f;
    protected static final float scaleFactorMax = 5.0f;

    Zoom zLevel = Zoom.DEFAULT;

}