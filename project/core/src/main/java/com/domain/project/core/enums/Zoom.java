package com.domain.project.core.enums;

public enum Zoom {
    IN(12.0f), OUT(0.0f), DEFAULT(1.0f);
    
    private final float scale;
    
    Zoom(float scale) {
        this.scale = scale;
    }
    
    public float getScale() {
        return scale;
    }
}