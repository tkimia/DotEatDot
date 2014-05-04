package com.tkapps.GameWorld;

import java.util.ArrayList;

import com.tkapps.GameObjects.Dot;
import com.tkapps.GameObjects.Hero;

public class GameField {
	//a hero and a few enemis
	private Hero hero;
	private ArrayList<Dot> enemies = new ArrayList<Dot>();
	private ArrayList<Dot> untouchables = new ArrayList<Dot>();
	
	//the score and runTime, to be displayed up top.
	private float runTime = 0;
	
	private float height, width;
	
	/**
	 * 
	 * @param height the height of the game screen
	 * @param width the width of the game screen
	 */
	public GameField(float height, float width){
		hero = new Hero(102, 200, 5f, 0, 0, 0.65f);
		
		//add big boss
		enemies.add(new Dot(50, 50, 25f, 50, 20, 0));
		
		//add fish food
		enemies.add(new Dot(100, 50, 4f, 280, 100, 0f));
		
		//add untouchables
		
		this.height = height;
		this.width = width;
	}
	
	public void update(float delta) {
		hero.update(delta);
		
		for (Dot e : enemies)
			e.update(delta);
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

	
	
}