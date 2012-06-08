package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;

public class Environment
{
	private GroupLayer mainLayer;
    private GroupLayer graphLayer; //contains graph objects(city graphs, camp graphs, etc)
    private ImageLayer bgLayer;
    private GroupLayer uiLayer; //contains all the objects and layers for the game UI
    //TODO : any more layers ?

    public Environment()
    {
        //set window size
        graphics().setSize(Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);
		//graphics().setSize(graphics().screenWidth(),graphics().screenHeight());
        //TODO : Each device has different screen parameters (see http://playn-2011.appspot.com/slides/index.html#19)


       // create background image layer
        Image bgImage = assets().getImage("images/texture_wall_painted11.png");
        //Image image = assets().getImage("images/sample_environment.jpg");
        bgLayer = graphics().createImageLayer(bgImage);//TODO: camera.getView()
        bgLayer.setSize(Const.WORLD_WIDTH,Const.WORLD_HEIGHT);
        bgLayer.setRepeatX(true);
        bgLayer.setRepeatY(true);

		mainLayer = graphics().createGroupLayer();
        //create group layer containing the graphs
        graphLayer = graphics().createGroupLayer();
        //create group layer containing the UI
        uiLayer = graphics().createGroupLayer();
        //add all the layer to the main Layer and then the root Layer
		mainLayer.add(graphLayer);
		mainLayer.add(bgLayer);
        graphics().rootLayer().add(uiLayer);
        graphics().rootLayer().add(mainLayer);
    }

    public Image getBaseImage()
    {
        return bgLayer.image();
    }

    public GroupLayer getGraphLayer()
    {
        return graphLayer;
    }

    public void updateView(float x, float y) {
        mainLayer.setOrigin(x, y);
    }
}
