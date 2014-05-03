package com.tkapps.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Dot {
	private Vector2 velocity, acceleration;
	private Circle circle;
	private boolean isAlive;
	private float friction;
	
	/**
	 * Makes a new Dot object to be used in the game.
	 * @param x the starting position's X value
	 * @param y the starting position's Y value
	 * @param radius the size of the Dot
	 * @param direction the direction of the dot's initial velocity in degrees.
	 * @param initSpeed the initial speed of the dot
	 * @param friction slows down the dot, should be between 0 and 0.5
	 */
	public Dot(int x, int y, float radius, float direction, float initSpeed, float friction) {
		direction = (float) Math.toRadians(direction);
		circle = new Circle(new Vector2(x, y), radius);
		velocity = new Vector2((float) Math.cos(direction), (float) Math.sin(direction)).scl(initSpeed);
		
		if (friction < 0)
			friction = 0;
		else if (friction > 0.75)
			friction = 0.75f;
		
		this.friction = friction;
		
		acceleration = new Vector2(0, 0);
	}
	
	/**
	 * Updates the dot's position, velocity, and acceleration.
	 */
	private void update(float delta) {
		//change velocity based on acceleration
		acceleration.set(friction * -velocity.x, friction * -velocity.y);
		
		velocity.add(acceleration.cpy().scl(delta));
		
		Vector2 temp = velocity.cpy().scl(delta);
		
		circle.setPosition(circle.x + temp.x, circle.y + temp.y);
		
		if (circle.x - circle.radius < 0) {
			circle.x = circle.radius;
			velocity.x = Math.abs(velocity.x);
		}
		
		
		if (circle.y - circle.radius < 0) {
			circle.y = circle.radius;
			velocity.y = Math.abs(velocity.y);
		}
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public Circle getCircle() {
		return circle;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public float getFriction() {
		return friction;
	}
	
	
	
}
