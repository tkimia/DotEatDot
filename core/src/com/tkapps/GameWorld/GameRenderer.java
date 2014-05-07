package com.tkapps.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tkapps.GameObjects.Dot;
import com.tkapps.GameObjects.Hero;
import com.tkapps.Helpers.AssetHandler;
import com.tkapps.Helpers.ColorHelper;

public class GameRenderer {
	private GameField gameField;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch sprite;
		
	//objects for convenience
	private Hero hero;
	private Dot bigBoss;
	
	public GameRenderer(GameField gameField) {
		this.gameField = gameField;
		hero = gameField.getHero();
		bigBoss = gameField.getBigBoss();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameField.getWidth(), gameField.getHeight());
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		sprite = new SpriteBatch();
		sprite.setProjectionMatrix(cam.combined);
	}
	
	public void render(float runTime) {
		hero = gameField.getHero();
		bigBoss = gameField.getBigBoss();
		//setup
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//put columns down
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(Color.GRAY);
		for (Dot e : gameField.getUntouchables()) 
			shapeRenderer.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
		
		shapeRenderer.end();
		
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(Color.GRAY);
		for (Dot e : gameField.getUntouchables()) 
			shapeRenderer.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
		//put hero down
		if (hero.isAlive()) {
			shapeRenderer.setColor(ColorHelper.heroColor);
			shapeRenderer.circle(hero.getCircle().x, hero.getCircle().y, hero.getCircle().radius);
		}

		//put enemies down
		Dot[] enemies = gameField.getEnemies().toArray(new Dot[]{}); 
		for (Dot e :  enemies){
			if (e.getCircle().radius <= hero.getCircle().radius)
				shapeRenderer.setColor(Color.WHITE);
			else
				shapeRenderer.setColor(1f, 99f/255f, 71f/255f, (e.isPresent()) ? 1f : 0.5f);
			shapeRenderer.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
		}
		
		//put big boss down
		if (bigBoss.isAlive()) {
			shapeRenderer.setColor(ColorHelper.getColor(bigBoss.getCircle().radius));
			shapeRenderer.circle(bigBoss.getCircle().x, bigBoss.getCircle().y, bigBoss.getCircle().radius);
		}
		shapeRenderer.end();
		
		drawText();

	}

	private void drawText() {
		//use the sprite batch to draw the text depending on the currentState
		sprite.begin();
		sprite.enableBlending();
		if (gameField.getCurrentState() == GameState.RUNNING) {
			int mins = (int)(gameField.getRunTime() / 60);
			String time = String.format("Time: %02d:%02d", mins, (int)(gameField.getRunTime()-60*mins));
			AssetHandler.font.draw(sprite, time, 150, 15);
		}
		else if (gameField.getCurrentState() == GameState.READY) {
			AssetHandler.font.draw(sprite, "Tap to Start", 75, gameField.getHeight()/2);
		}
		else if (gameField.getCurrentState() == GameState.GAMEOVER) {
			if (hero.isAlive()){
				AssetHandler.font.draw(sprite, "YOU WIN!", 75, gameField.getHeight()/6);
				AssetHandler.font.draw(sprite, "Play Again", 75, gameField.getHeight()/6*5);
			}
			else {
				AssetHandler.font.draw(sprite, "You Lose", 85, gameField.getHeight()/6);
				AssetHandler.font.draw(sprite, "Tap to Play Again", 55, gameField.getHeight()/6*5);
			}
			int i = 1;
			AssetHandler.font.draw(sprite, "High Scores Today:", 55, gameField.getHeight()/20 * (5));
			for (String s : gameField.highScores) {
				String[] temp = s.split("\\|");
				AssetHandler.font.draw(sprite, temp[0], 55, gameField.getHeight()/20 * (5+i));
				int mins = Integer.parseInt(temp[1])/60;
				temp[1] = String.format("%02d:%02d", mins, Integer.parseInt(temp[1])-60*mins);
				AssetHandler.font.draw(sprite, temp[1], 175, gameField.getHeight()/20 * (5+i));
				i++;
			}
				
		}
		
		sprite.end();
		
	}

}
