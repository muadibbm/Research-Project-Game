package com.domain.project.core.graph;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.domain.project.core.Const;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

/** This class contains the attributes for the caravan object
 * @author Andrey
 */
public class Caravan {
	
	private Image caravanImage = Const.CARAVAN_IMAGE;
	private ImageLayer caravanLayer;
	private Tuple2f position;
	private int caravanLevel;
	
	public Caravan(final GroupLayer graphLayer, City city) {
		caravanLayer = graphics().createImageLayer(caravanImage);
		caravanLayer.setDepth(Const.CARAVAN_DEPTH);
		caravanLayer.setAlpha(Const.CARAVAN_ALPHA);
		caravanLevel = 0;
		position = new Tuple2f(city.getPosition().getX(), city.getPosition().getY());
		
		caravanImage.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				caravanLayer.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(caravanLayer);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading the caravan!", e);
			}
		});
		
		caravanLayer.setScale(Const.CARAVAN_SCALE, Const.CARAVAN_SCALE);
		caravanLayer.setTranslation(position.getX(), position.getY());
		
		Timer timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				moveCaravan();
				paint(position.getX(), position.getY());
			}
		});
		
		timer.start();
	}
	
	public void paint(float x, float y) {
		caravanLayer.setScale(Const.CARAVAN_SCALE, Const.CARAVAN_SCALE);
		caravanLayer.setTranslation(x, y);
	}
	
	/** Move the caravan from city to city to gain resources. I will implement a path finding algorithm
	 * to make the camel/caravan find the right city and going around the cities the current node is not connected to.
	 */
	public void moveCaravan() {
		position.setX(position.getX() + 1);
		position.setY(position.getY() + 1);
		
	}
	
	public int getCaravanLevel() {
		return caravanLevel;
	}

	public void setCaravanLevel(int caravanLevel) {
		this.caravanLevel = caravanLevel;
	}

	public ImageLayer getCaravanLayer() {
		return caravanLayer;
	}

	public void setCaravanLayer(ImageLayer caravanLayer) {
		this.caravanLayer = caravanLayer;
	}

	public Tuple2f getPosition() {
		return position;
	}

	public void setPosition(Tuple2f position) {
		this.position = position;
	}
}
