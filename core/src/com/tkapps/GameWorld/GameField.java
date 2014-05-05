package com.tkapps.GameWorld;

import java.util.ArrayList;
import java.util.Random;

import com.tkapps.GameObjects.Dot;
import com.tkapps.GameObjects.Hero;
import com.tkapps.Helpers.SoundHandler;

public class GameField {
	//a hero and a few enemies
	private Hero hero;
	private Dot bigBoss;
	private ArrayList<Dot> enemies = new ArrayList<Dot>();
	private ArrayList<Dot> untouchables = new ArrayList<Dot>();
	private Random r = new Random();
	
	//for spawning another enemy bigger than you are
	private int sizeLvl = 1;
	
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
		enemies.add(new Dot(100, 50, 4f, 280, 50, 0f));
		enemies.add(new Dot(240, 350, 4f, 280, 50, 0f));
		
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
		runTime += delta;
		hero.update(delta);
		bigBoss.update(delta);
		for (Dot e : enemies)
			e.update(delta);
		
		//make sure nothing collides with the columns
		for (Dot column : untouchables) {
			for (Dot e : enemies) {
				if (e.getCircle().overlaps(column.getCircle())) {
					e.setVelocity(e.getVelocity().scl(-1));
					SoundHandler.bounce.play();
				}
			}
			if (hero.getCircle().overlaps(column.getCircle())){
				hero.setVelocity(hero.getVelocity().scl(-1f));
				hero.update(delta);
				hero.setVelocity(hero.getVelocity().scl(0.5f));
				SoundHandler.bounce.play();
			}
		}
		
		//see if player has collided with enemies or big boss
		if (hero.isAlive()) {
			Dot[] eArr = getEnemies().toArray(new Dot[] {});
			for (Dot e : eArr) {
				if (hero.handleCollision(e)) {
					boolean canAdd = true;
					enemies.remove(e);
					if (hero.getCircle().radius < 26) {
						do {
							int newSize = r
									.nextInt((int) hero.getCircle().radius - 2) + 3;
							Dot temp = new Dot(r.nextInt((int) width - 2
									* newSize)
									+ newSize, r.nextInt((int) height - 2
									* newSize)
									+ newSize, newSize, r.nextFloat() * 360,
									r.nextInt((int)(25*newSize/4)) + 50, 0);
							canAdd = true;
							for (Dot column : untouchables)
								canAdd = canAdd
										&& !temp.getCircle().overlaps(
												column.getCircle());
							if (canAdd)
								enemies.add(temp);
						} while (!canAdd);
					}
				}
			}
		}
		//if player's size level is high, spawn another enemy 
		if (sizeLvl < 4 && (int)(hero.getCircle().radius / 5) > sizeLvl)
		{
			sizeLvl++;
			boolean canAdd = true;
			do {
				int newSize = 5*(sizeLvl+1);
				Dot temp = new Dot(
						r.nextInt((int)width-2*newSize)+newSize,
						r.nextInt((int)height-2*newSize)+newSize,
						newSize, r.nextFloat()*360, r.nextInt(30)+30, 0
						);
				canAdd = true;
				for (Dot column : untouchables)
					canAdd = canAdd && !temp.getCircle().overlaps(column.getCircle());
				if (canAdd)
					enemies.add(temp);
			}while(!canAdd);
		}
		if (bigBoss.isAlive() && hero.isAlive() && hero.handleCollision(bigBoss)){
			bigBoss.die();
		}
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

	public float getRunTime() {
		return runTime;
	}

	
	
}