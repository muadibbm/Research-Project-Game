package com.domain.project.core.enums;

public enum Zoom {
    IN_3(4.0f), IN_2(3.0f), IN_1(2.0f), DEFAULT(1.0f), OUT(0.0f);
    
    private final float scale;
    
    Zoom(float scale) {
        this.scale = scale;
    }
    
    public float getScale() {
        return scale;
    }
}