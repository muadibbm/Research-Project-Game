package com.domain.project.core.graph;

import java.lang.Math;

public class Tuple2f {
	private float x;
	private float y;

	public Tuple2f() {
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public Tuple2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getDistanceFrom(Tuple2f point) {
		return (float)Math.sqrt((this.x - point.x)*(this.x - point.x) + (this.y - point.y)*(this.y - point.y));
	}

	public float getSlope(Tuple2f point) {
		float infinityCheck = this.x - point.x;

		if (Math.floor(infinityCheck) != 0) {
			return (float) Math.floor((this.y - point.y) / (this.x - point.x));
		}
		return 0.0f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return ("(" + this.x + ", " + this.y + ")");
	}
}
