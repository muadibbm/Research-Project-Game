
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

/** This class contains the attributes for the army object
 * @author Mehrdad
 */
public class Army {

	private Base current_base;
	private Base destination;
	private Tuple2f currentPosition;
	private ImageLayer armyLayer;
	private float stoppingDist;
	private int armyLevel;
	private boolean hasArrived;
	private int dx, dy, err, sx, sy;
	private int speed;
	private Timer timer;

	public Army(final GroupLayer graphLayer, Base base, float depth, float alpha, Image armyImage, float stoppingDistance) {
		current_base = base;
		currentPosition = base.getPosition();
		armyLevel = 1;
		stoppingDist = stoppingDistance;
		armyLayer = graphics().createImageLayer(armyImage);
		armyLayer.setDepth(depth);
		armyLayer.setAlpha(alpha);
		
		hasArrived = false;
		
		armyImage.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				armyLayer.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(armyLayer);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading the caravan!", e);
			}
		});

		armyLayer.setScale(Const.ARMY_SCALE, Const.ARMY_SCALE);
		armyLayer.setTranslation(currentPosition.getX() + Const.AMRY_INITIAL_POS_X, 
								 currentPosition.getY() + Const.AMRY_INITIAL_POS_Y);
								 
		speed = Const.ARMY_SPEED;
	}
	
	public void move(int speed, final Base destination) {
		this.destination = destination;
		timer = new Timer(speed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (currentPosition.getDistanceFrom(destination.getPosition()) > stoppingDist) {
					moveArmy();
				} else {
					hasArrived = true;
					current_base = destination;
					stop();
				}
				paint(currentPosition.getX(), currentPosition.getY());
			}
		});
		timer.start();
	}
	
	private void moveArmy() {
		dx = (int) Math.abs(destination.getPosition().getX() - current_base.getPosition().getX());
		dy = (int) Math.abs(destination.getPosition().getY() - current_base.getPosition().getY());
		err = dx - dy;
		int e2 = 2 * err;
		Tuple2f newPosition = new Tuple2f(currentPosition.getX(), currentPosition.getY());
		
		if (e2 > -dy) {
			err -= dy;
			newPosition.setX(newPosition.getX() + sx);
		}

		if (e2 < dx) {
			err += dx;
			newPosition.setY(newPosition.getY() + sy);
		}
		currentPosition = newPosition;
	}

	public void paint(float x, float y) {
		armyLayer.setScale(Const.ARMY_SCALE, Const.ARMY_SCALE);
		armyLayer.setTranslation(x, y);
	}

	public void stop() {
		this.destination = null;
		timer.stop();
	}

	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getArmyLevel() {
		return armyLevel;
	}

	public void setArmyLevel(int level) {
		this.armyLevel = level;
	}

	public ImageLayer getArmyLayer() {
		return armyLayer;
	}

	public Base getBase() {
		return current_base;
	}

	public void setBase(Camp base) {
		current_base = base;
	}
}
