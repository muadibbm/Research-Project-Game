package com.domain.project.core.graph;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.domain.project.core.Const;

import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ResourceCallback;

/** This class contains the attributes for the deeve object.
 * @author Andrey
 *
 */
public class Deeve {
	private Image deeveImage = Const.DEEVE_IMAGE;
	private ImageLayer deeveLayer;
	private Tuple2f initialPosition;
	private Tuple2f currentPosition;
	private Tuple2f finalPosition;
	private float stoppingDist;
	private byte deeveLevel;
	private boolean hasArrived;
	private int dx, dy, err, sx, sy;
	private int i;
	private Timer timer;

	public Deeve(final GroupLayer graphLayer, final ArrayList<Tuple2f> pointsList) {
		deeveLayer = graphics().createImageLayer(deeveImage);
		deeveLayer.setDepth(Const.DEEVE_DEPTH);
		deeveLayer.setAlpha(Const.DEEVE_ALPHA);
		deeveLevel = 1;
		i = 0;
		initialPosition = pointsList.get(i);
		currentPosition = initialPosition;
		finalPosition = pointsList.get(i + 1);
		stoppingDist = 15;
		hasArrived = false;
		reeval();

		deeveImage.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image image) {
				deeveLayer.setOrigin(image.width()/2f, image.height()/2f);
				graphLayer.add(deeveLayer);
			}

			@Override
			public void error(Throwable e) {
				log().error("Error loading the deeve!", e);
			}
		});

		deeveLayer.setScale(Const.DEEVE_SCALE, Const.DEEVE_SCALE);
		deeveLayer.setTranslation(currentPosition.getX(), currentPosition.getY());

		timer = new Timer(Const.DEEVE_SPEED, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (currentPosition.getDistanceFrom(finalPosition) > stoppingDist) {
					moveDeeve();
				} else {
					if (i == (pointsList.size() - 1)) {
						hasArrived = true;
						deeveStopMoving();
					} else {
						initialPosition = finalPosition;
						currentPosition = initialPosition;
						i++;
						finalPosition = pointsList.get(i);
						reeval();
					}					
				}

				paint(currentPosition.getX(), currentPosition.getY());
			}
		});

		timer.start();
	}

	public void paint(float x, float y) {
		deeveLayer.setScale(Const.DEEVE_SCALE, Const.DEEVE_SCALE);
		deeveLayer.setTranslation(x, y);
	}

	private void reeval() {
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
	}

	/** Move the deeve from the cave to the tree of life to destroy it.
	 */
	public void moveDeeve() {		
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

	public boolean hasArrived() {
		return hasArrived;
	}

	public void setHasArrived(boolean hasArrived) {
		this.hasArrived = hasArrived;
	}

	public void deeveStopMoving() {
		timer.stop();
		deeveLayer.setVisible(false);
	}

	public int getDeeveLevel() {
		return deeveLevel;
	}

	public void setDeeveLevel(byte deeveLevel) {
		this.deeveLevel = deeveLevel;
	}

	public ImageLayer getDeeveLayer() {
		return deeveLayer;
	}

	public void setDeeveLayer(ImageLayer deeveLayer) {
		this.deeveLayer = deeveLayer;
	}

	public Tuple2f getPosition() {
		return currentPosition;
	}

	public void setPosition(Tuple2f position) {
		this.currentPosition = position;
	}
}
