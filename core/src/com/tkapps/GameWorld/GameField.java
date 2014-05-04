package com.tkapps.GameWorld;

import java.util.ArrayList;
import java.util.Random;

import com.tkapps.GameObjects.Dot;
import com.tkapps.GameObjects.Hero;

public class GameField {
	//a hero and a few enemis
	private Hero hero;
	private Dot bigBoss;
	private ArrayList<Dot> enemies = new ArrayList<Dot>();
	private ArrayList<Dot> untouchables = new ArrayList<Dot>();
	private Random r = new Random();
	
	//the score and runTime, to be displayed up top.
	private float runTime = 0;
	
	private float height, width;
	
	/**
	 * 
	 * @param height the height of the game screen
	 * @param width the width of the game screen
	 */
	public GameField(float height, float width){
		hero = new Hero(102, 200, 5f, 0, 0, 0.85f);
		
		//add big boss
		bigBoss = new Dot(50, 50, 25f, 260, 50, 0f);
		
		//add fish food
		enemies.add(new Dot(100, 50, 4f, 280, 100, 0f));
		enemies.add(new Dot(240, 350, 4f, 280, 100, 0f));
		
		//add untouchables
		untouchables.add(new Dot(120, 120, 10f, 90, 0, 0f));
		untouchables.add(new Dot(120, (int)height-120, 10f, 90, 0, 0f));
		
		this.height = height;
		this.width = width;
	}
	
	public ArrayList<Dot> getUntouchables() {
		return untouchables;
	}
	/**
	 * This update method updates all circle's positions. If there are collisions
	 * this method resets the circle's attributes, or removes it from the map entirely.
	 * See inner comments for detail
	 * @param delta times elapsed since last update
	 */
	public void update(float delta) {
		//update position for moving circles
		hero.update(delta);
		bigBoss.update(delta);
		for (Dot e : enemies)
			e.update(delta);
		
		//make sure nothing collides with the columns
		for (Dot column : untouchables) {
			for (Dot e : enemies) {
				if (e.getCircle().overlaps(column.getCircle()))
					e.setVelocity(e.getVelocity().scl(-1));
			}
			if (hero.getCircle().overlaps(column.getCircle())){
				hero.setVelocity(hero.getVelocity().scl(-1f));
				hero.update(delta);
				hero.setVelocity(hero.getVelocity().scl(0.5f));
			}	
		}
		
		//see if player has collided with enemies or big boss
		for(Dot e : enemies) {
			if (hero.handleCollision(e)){
				enemies.remove(e);
				
			}
		}
		hero.handleCollision(bigBoss);
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}

	public Hero getHero() {
		return hero;
	}

	public ArrayList<Dot> getEnemies() {
		return enemies;
	}
	
	public Dot getBigBoss(){
		return bigBoss;
	}

	
	
}