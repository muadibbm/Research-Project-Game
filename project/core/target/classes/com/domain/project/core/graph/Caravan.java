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
	private Tuple2f initialPosition;
	private Tuple2f currentPosition;
	private Tuple2f finalPosition;
	private float stoppingDist;
	private byte caravanLevel;
	private boolean hasArrived;
	private int dx, dy, err, sx, sy;
	private Timer timer;

	public Caravan(final GroupLayer graphLayer, Tuple2f city1, Tuple2f city2, float stoppingDistance) {
		caravanLayer = graphics().createImageLayer(caravanImage);
		caravanLayer.setDepth(Const.CARAVAN_DEPTH);
		caravanLayer.setAlpha(Const.CARAVAN_ALPHA);
		caravanLevel = 1;
		initialPosition = city1;
		currentPosition = city1;
		finalPosition = city2;
		stoppingDist = stoppingDistance;
		hasArrived = false;
		dx = (int) Math.abs(finalPosition.getX() - initialPosition.getX());
		dy = (int) Math.abs(finalPosition.getY() - initialPosition.getY());
		err = dx - dy;
		
		if (currentPosition.getX() < finalPosition.getX()) { 
			sx = 1;
		} else { 
			sx = -1;
		}

		if (currentPosition.getY() < finalPosition.getY()) {
			sy = 1;
		} else {
			sy = -1;
		}

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
		caravanLayer.setTranslation(currentPosition.getX(), currentPosition.getY());

		timer = new Timer(Const.CARAVAN_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (currentPosition.getDistanceFrom(finalPosition) > stoppingDist) {
					moveCaravan();
				} else {
					hasArrived = true;
					caravanStopMoving();
				}

				paint(currentPosition.getX(), currentPosition.getY());
			}
		});

		timer.start();
	}

	public void paint(float x, float y) {
		caravanLayer.setScale(Const.CARAVAN_SCALE, Const.CARAVAN_SCALE);
		caravanLayer.setTranslation(x, y);
	}

	/** Move the caravan from city to city to gain resources. I will implement a path finding algorithm
	 * to make the camel/caravan find the right city and going around the cities, the current node is connected to.
	 */
	public void moveCaravan() {		
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

		/*Tuple2f upperLeftCorner = new Tuple2f(currentPosition.getX() - 1, currentPosition.getY() - 1);
		Tuple2f top = new Tuple2f(currentPosition.getX(), currentPosition.getY() - 1);
		Tuple2f upperRightCorner = new Tuple2f(currentPosition.getX() + 1, currentPosition.getY() - 1);
		Tuple2f left = new Tuple2f(currentPosition.getX() - 1, currentPosition.getY());
		Tuple2f right = new Tuple2f(currentPosition.getX() + 1, currentPosition.getY());
		Tuple2f lowerLeftCorner = new Tuple2f(currentPosition.getX() - 1, currentPosition.getY() + 1);
		Tuple2f bottom = new Tuple2f(currentPosition.getX(), currentPosition.getY() + 1);
		Tuple2f lowerRightCorner = new Tuple2f(currentPosition.getX(), currentPosition.getY());

		float[] distances = new float[8];
		distances[0] = upperLeftCorner.getDistanceFrom(finalPosition);
		distances[1] = top.getDistanceFrom(finalPosition);
		distances[2] = upperRightCorner.getDistanceFrom(finalPosition);
		distances[3] = left.getDistanceFrom(finalPosition);
		distances[4] = right.getDistanceFrom(finalPosition);
		distances[5] = lowerLeftCorner.getDistanceFrom(finalPosition);
		distances[6] = bottom.getDistanceFrom(finalPosition);
		distances[7] = lowerRightCorner.getDistanceFrom(finalPosition);

		float minDistance = Float.POSITIVE_INFINITY;
		int counter = 0;

		for (int i = 0; i < distances.length; i++) {
			if (distances[i] < minDistance) {
				minDistance = distances[i];
				counter = i;
			}
		}

		switch (counter) {
		case 0:
			currentPosition = upperLeftCorner;
			break;
		case 1:
			currentPosition = top;
			break;
		case 2:
			currentPosition = upperRightCorner;
			break;
		case 3:
			currentPosition = left;
			break;
		case 4:
			currentPosition = right;
			break;
		case 5:
			currentPosition = lowerLeftCorner;
			break;
		case 6:
			currentPosition = bottom;
			break;
		case 7:
			currentPosition = lowerRightCorner;
			break;
		}*/
		
		currentPosition = newPosition;
	}

	public boolean hasArrived() {
		return hasArrived;
	}

	public void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}

	public void swapDestination() {
		Tuple2f temp = finalPosition;
		finalPosition = initialPosition;
		initialPosition = temp;
		
		dx = (int) Math.abs(finalPosition.getX() - initialPosition.getX());
		dy = (int) Math.abs(finalPosition.getY() - initialPosition.getY());
		err = dx - dy;
		
		if (currentPosition.getX() < finalPosition.getX()) { 
			sx = 1;
		} else { 
			sx = -1;
		}

		if (currentPosition.getY() < finalPosition.getY()) {
			sy = 1;
		} else {
			sy = -1;
		}
		
		timer.setInitialDelay(Const.CARAVAN_TRADING_TIME);
		timer.start();
	}

	public void caravanStopMoving() {
		timer.stop();
	}

	public int getCaravanLevel() {
		return caravanLevel;
	}

	public void setCaravanLevel(byte caravanLevel) {
		this.caravanLevel = caravanLevel;
	}

	public ImageLayer getCaravanLayer() {
		return caravanLayer;
	}

	public void setCaravanLayer(ImageLayer caravanLayer) {
		this.caravanLayer = caravanLayer;
	}

	public Tuple2f getPosition() {
		return currentPosition;
	}

	public void setPosition(Tuple2f position) {
		this.currentPosition = position;
	}
}
