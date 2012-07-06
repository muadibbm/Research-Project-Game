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
				option2.setTranslation(Const.CONSTRUCTION_PANEL_X + (0.5f)*Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y);
				option3.setTranslation(Const.CONSTRUCTION_PANEL_X + (1.0f)*Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y);
				option4.setTranslation(Const.CONSTRUCTION_PANEL_X + (1.5f)*Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y);
				option5.setTranslation(Const.CONSTRUCTION_PANEL_X + (2.0f)*Const.CONSTRUCTION_PANEL_X, Const.CONSTRUCTION_PANEL_Y);
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
						if(player.getSelectedNode() != null & player.getNodeToBeMapped() == null)
							if(player.getId() == player.getSelectedNode().getPlayer() & player.getSelectedNode().getMapping() == null) {
								player.setNodeToBeMapped(player.getSelectedNode());
								//TODO : add some graphical indication
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
						if(player.getSelectedNode() != null)
							if(player.getSelectedNode().getMapping() != null & player.getId() == player.getSelectedNode().getPlayer()) {
								if(player.getSelectedNode().getBase() instanceof City)
									player.getSelectedNode().unMap();
								else if(player.getSelectedNode().getBase() instanceof Camp)
									player.getSelectedNode().getMappedNode().unMap();
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