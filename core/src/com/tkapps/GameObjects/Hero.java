package com.tkapps.GameObjects;

public class Hero extends Dot {
	
	public Hero(int x, int y, float radius, float direction, float initSpeed,
			float friction) {
		super(x, y, radius, direction, initSpeed, friction);
	}
	
	public void pushUp(int speed) {
		velocity.y -= speed;
	}
	
	public void pushDown(int speed) {
		velocity.y += speed;
	}
	
	public void pushRight(int speed) {
		velocity.x += speed;
	}
	
	public void pushLeft(int speed) {
		velocity.x -= speed;
	}

	
}
