package com.tkapps.GameObjects;

import com.tkapps.Helpers.SoundHandler;

public class Hero extends Dot {
	private static final float TURN_SHARPNESS = 0.6f;
	
	public Hero(int x, int y, float radius, float direction, float initSpeed,
			float friction) {
		super(x, y, radius, direction, initSpeed, friction);
	}
	
	public void pushUp(int speed) {
		velocity.x *= TURN_SHARPNESS;
		velocity.y -= speed;
	}
	
	public void pushDown(int speed) {
		velocity.x *= TURN_SHARPNESS;
		velocity.y += speed;
	}
	
	public void pushRight(int speed) {
		velocity.y *= TURN_SHARPNESS;
		velocity.x += speed;
	}
	
	public void pushLeft(int speed) {
		velocity.y *= TURN_SHARPNESS;
		velocity.x -= speed;
	}
	
	/**
	 * Handles collision between Hero and other Dots.
	 * @param d the other dot in the collision handling
	 * @return true if other dot is dead. false if no collision or hero is dead.
	 */
	public boolean handleCollision(Dot d){
		if (circle.overlaps(d.getCircle())) {
			if (circle.radius > d.getCircle().radius){
				SoundHandler.crunch.play();
				circle.radius += d.getCircle().radius / circle.radius;
				return true;
			}
			else {
				SoundHandler.bossCrunch.play();
				die();
			}
		}
		return false;
	}
	
}
