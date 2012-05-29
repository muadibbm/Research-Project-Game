package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;

import playn.core.Keyboard;
import playn.core.Key;

public class GameLoop implements Game {
  @Override
  public void init() {
	//set window size
	graphics().setSize(Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);
    // create and add background image layer
    final Image bgImage = assets().getImage("images/temp/giant_cat.jpg");
    final ImageLayer bgLayer = graphics().createImageLayer(bgImage);
	bgLayer.setSize(1000,1000);
    graphics().rootLayer().add(bgLayer);

    System.out.println(bgImage.width() + " " + bgImage.height());
    //bgLayer.setSize(graphics().width(), graphics().height());
	
	// add a listener for Keyboard input
    keyboard().setListener(new Keyboard.Adapter() {
      @Override
      public void onKeyDown(Keyboard.Event event) {
	  /*float x = 100;
		float y = 0;
        if(event.key().equals(Key.A))
		    x = x + 10;
			bgLayer.subImage(x,y,Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);
		if(event.key().equals(Key.B))
			y = y - 10;
			bgLayer.subImage(x,y,Const.WINDOW_WIDTH,Const.WINDOW_HEIGHT);*/
		if(event.key().equals(Key.ESCAPE))
			System.exit(0);
      }
    });
    
  }

  @Override
  public void paint(float alpha) {
    // the background automatically paints itself, so no need to do anything here!
  }

  @Override
  public void update(float delta) {
  
  }

  @Override
  public int updateRate() {
    return 25;
  }
}
