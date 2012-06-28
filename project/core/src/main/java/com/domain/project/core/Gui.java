package com.domain.project.core;

import static playn.core.PlayN.*;

import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;
import playn.core.GroupLayer;

import playn.core.Mouse;

import com.domain.project.core.graph.Mapping;
import com.domain.project.core.graph.Camp;
import com.domain.project.core.graph.City;

public class Gui
{
	private ImageLayer option1;
	private ImageLayer option2;
	private ImageLayer option3;
	private ImageLayer option4;
	private ImageLayer option5;
	
	private ImageLayer map;
	private ImageLayer unmap;
	
	public Gui(final GroupLayer uiLayer)
	{
        option1 = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_IMAGE);
		option2 = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_IMAGE);
		option3 = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_IMAGE);
		option4 = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_IMAGE);
		option5 = graphics().createImageLayer(Const.CONSTRUCTION_PANEL_IMAGE);
		
		map = graphics().createImageLayer(Const.MAP_BUTTON_IMAGE);
		unmap = graphics().createImageLayer(Const.UNMAP_BUTTON_IMAGE);
		
		Const.MAP_BUTTON_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				map.setTranslation(Const.MAP_BUTTON_X, Const.MAP_BUTTON_Y);
				map.setScale(Const.BUTTON_SCALE);
				uiLayer.add(map);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
		
		Const.UNMAP_BUTTON_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				unmap.setTranslation(Const.UNMAP_BUTTON_X, Const.UNMAP_BUTTON_Y);
				unmap.setScale(Const.BUTTON_SCALE);
				uiLayer.add(unmap);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
			
        Const.CONSTRUCTION_PANEL_IMAGE.addCallback(new ResourceCallback<Image>() {
            @Override
            public void done(Image image) {
				option1.setTranslation(Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y);
				option2.setTranslation(Const.CONSTRUCTION_PANEL_X + 100, Const.CONSTRUCTION_PANEL_Y);
				option3.setTranslation(Const.CONSTRUCTION_PANEL_X + 200, Const.CONSTRUCTION_PANEL_Y);
				option4.setTranslation(Const.CONSTRUCTION_PANEL_X + 300, Const.CONSTRUCTION_PANEL_Y);
				option5.setTranslation(Const.CONSTRUCTION_PANEL_X + 400, Const.CONSTRUCTION_PANEL_Y);
				option1.setScale(Const.CONSTRUCTION_PANEL_SCALE);
				option2.setScale(Const.CONSTRUCTION_PANEL_SCALE);
				option3.setScale(Const.CONSTRUCTION_PANEL_SCALE);
				option4.setScale(Const.CONSTRUCTION_PANEL_SCALE);
				option5.setScale(Const.CONSTRUCTION_PANEL_SCALE);
                uiLayer.add(option1);
				uiLayer.add(option2);
				uiLayer.add(option3);
				uiLayer.add(option4);
				uiLayer.add(option5);
            }

            @Override
            public void error(Throwable e) {
                log().error("error loading node", e);
            }
        });
	}
	
	public void addListener(final Player player) {
		map.addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode1() != null & player.getSelectedNode2() != null)
							if(player.getId() == player.getSelectedNode1().getPlayer() & player.getId() == player.getSelectedNode2().getPlayer() & 
								player.getSelectedNode1().getMapping() == null & player.getSelectedNode2().getMapping() == null) {
								if(player.getSelectedNode1().getBase() instanceof City & player.getSelectedNode2().getBase() instanceof Camp)
									player.getSelectedNode1().setMapping(player.getSelectedNode2());
								else if(player.getSelectedNode1().getBase() instanceof Camp & player.getSelectedNode2().getBase() instanceof City)
									player.getSelectedNode2().setMapping(player.getSelectedNode1());
							}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
		
		unmap.addListener(new Mouse.Listener() {
				@Override
				public void onMouseDown(Mouse.ButtonEvent event) {
					if(event.button() == Mouse.BUTTON_LEFT) {
						if(player.getSelectedNode1() != null & player.getSelectedNode2() != null)
							if(player.getSelectedNode1().getMapping() != null & player.getSelectedNode2().getMapping() != null)
								if(player.getId() == player.getSelectedNode1().getPlayer() & player.getId() == player.getSelectedNode2().getPlayer() & 
									player.getSelectedNode1().getMapping().equals(player.getSelectedNode2().getMapping())) {
									if(player.getSelectedNode1().getBase() instanceof City & player.getSelectedNode2().getBase() instanceof Camp)
										player.getSelectedNode1().unMap();
									else if(player.getSelectedNode1().getBase() instanceof Camp & player.getSelectedNode2().getBase() instanceof City)
										player.getSelectedNode2().unMap();
								}
					}
				}
				@Override
				public void onMouseMove(Mouse.MotionEvent event) {
					//TODO
				}
				@Override
				public void onMouseUp(Mouse.ButtonEvent event) {
					//TODO
				}
				@Override
				public void onMouseWheelScroll(Mouse.WheelEvent event) {
					//TODO
				}
		});
	}
}