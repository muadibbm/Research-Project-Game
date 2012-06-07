package com.domain.project.core;

import playn.core.Image;
import playn.core.Image.Region;

public class Camera
{
    private float xOffset;
    private float yOffset;
    private float width;
    private float height;

    private Region view;

    public Camera(float xOffset, float yOffset, Image bgImage)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        width = bgImage.width();
        height = bgImage.height();
        view = bgImage.subImage(xOffset, yOffset, width, height);
    }

    public void setX(float xOffset)
    {
        this.xOffset = xOffset;
        view.setBounds(xOffset, yOffset, width, height);
    }

    public void setY(float yOffset)
    {
        this.yOffset = yOffset;
        view.setBounds(xOffset, yOffset, width, height);
    }

    public Region getView()
    {
        return view;
    }

    public float getX()
    {
        return xOffset;
    }

    public float getY()
    {
        return yOffset;
    }

}
