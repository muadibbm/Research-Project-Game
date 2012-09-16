package com.domain.project.core.enums;

public enum Zoom {
    IN_7(4.5f), IN_6(4.0f), IN_5(3.5f), IN_4(3.0f), IN_3(2.5f), IN_2(2.0f), IN_1(1.5f), DEFAULT(1.0f), OUT(0.0f);
    
    private final float scale;
    
    Zoom(float scale) {
        this.scale = scale;
    }
    
    public float getScale() {
        return scale;
    }
}