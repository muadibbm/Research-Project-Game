package com.domain.project.core.graph;

import java.lang.Math;

public class Tuple2f {
    public float x;
    public float y;

    public Tuple2f() {
        this.x = 0.0f;
        this.y = 0.0f;
    }
    
    public Tuple2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
	
	public float getDistanceFrom(Tuple2f point) {
		return (float)Math.sqrt((this.x - point.x)*(this.x - point.x) + (this.y - point.y)*(this.y - point.y));
	}
}
