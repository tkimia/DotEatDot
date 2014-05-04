package com.tkapps.GameWorld;

import java.util.ArrayList;

import com.tkapps.GameObjects.Dot;
import com.tkapps.GameObjects.Hero;

public class GameField {
	//a hero and a few enemis
	private Hero hero;
	private Dot bigBoss;
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
		hero = new Hero(102, 200, 5f, 0, 0, 0.85f);
		
		//add big boss
		bigBoss = new Dot(50, 50, 25f, 50, 20, 0f);
		
		//add fish food
		enemies.add(new Dot(100, 50, 4f, 280, 100, 0f));
		enemies.add(new Dot(240, 350, 4f, 280, 100, 0f));
		
		//add untouchables
		untouchables.add(new Dot(120, 120, 10f, 90, 0, 0f));
		untouchables.add(new Dot(120, 240, 10f, 90, 0, 0f));
		
		this.height = height;
		this.width = width;
	}
	
	public ArrayList<Dot> getUntouchables() {
		return untouchables;
	}

	public void update(float delta) {
		hero.update(delta);
		bigBoss.update(delta);
		
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
	
	public Dot getBigBoss(){
		return bigBoss;
	}

	
	
}