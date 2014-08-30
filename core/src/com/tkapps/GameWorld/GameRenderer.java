package com.tkapps.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
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
	
	private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	                           'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
	public static int[] initialIndexes = {19, 10, 26};
	
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
		Array<Dot> enemies = gameField.getEnemies();
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
		if (gameField.getCurrentState() == GameState.READY) drawInstructions();
		if (gameField.getCurrentState() == GameState.GAMEOVER && hero.isAlive())
			enterInitials();
			
	}

	private void drawText() {
		//use the sprite batch to draw the text depending on the currentState
		sprite.begin();
		sprite.enableBlending();
			int mins = (int)(gameField.getRunTime() / 60);
			String time = String.format("Time: %02d:%02d", mins, (int)(gameField.getRunTime()-60*mins));
			AssetHandler.font.draw(sprite, time, 150, 15);
		if (gameField.getCurrentState() == GameState.READY) {
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
				
		}
		
		sprite.end();
	}
	
	private void drawInstructions() {
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(new Color(210f/255f, 180f/255f, 140f/255f, 1f));
		shapeRenderer.rect(20, gameField.getHeight()*0.6f, gameField.getWidth()-40, gameField.getHeight()*0.35f);
		
		shapeRenderer.end();
		
		sprite.begin();
		sprite.enableBlending();
		AssetHandler.font.draw(sprite, "Instructions:", 25, gameField.getHeight()*0.61f);
		AssetHandler.font.draw(sprite, "Swipe up/down/left/right", 25, gameField.getHeight()*0.65f);
		AssetHandler.font.draw(sprite, "to move", 25, gameField.getHeight()*0.69f);
		AssetHandler.font.draw(sprite, "Eat white dots to grow.", 25, gameField.getHeight()*0.73f);
		AssetHandler.font.draw(sprite, "Beware of big dots!", 25, gameField.getHeight()*0.77f);
		AssetHandler.font.draw(sprite, "You'll bounce off grey.", 25, gameField.getHeight()*0.81f);
		AssetHandler.font.draw(sprite, "Win by eating \"big boss\",", 25, gameField.getHeight()*0.85f);
		AssetHandler.font.draw(sprite, "the floating blue dot.", 25, gameField.getHeight()*0.89f);
		sprite.end();
	}

	private void enterInitials() {
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(new Color(210f/255f, 180f/255f, 140f/255f, 1f));
		shapeRenderer.rect(20, gameField.getHeight()*0.7f, gameField.getWidth()-40, gameField.getHeight()*0.1f);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(50, gameField.getHeight()*0.71f, gameField.getWidth()*0.1f, gameField.getHeight()*0.08f);
		shapeRenderer.rect(110, gameField.getHeight()*0.71f, gameField.getWidth()*0.1f, gameField.getHeight()*0.08f);
		shapeRenderer.rect(170, gameField.getHeight()*0.71f, gameField.getWidth()*0.1f, gameField.getHeight()*0.08f);
		shapeRenderer.end();
		
		sprite.begin();
		sprite.enableBlending();
		AssetHandler.font.draw(sprite, ""+alphabet[initialIndexes[0]], 60, gameField.getHeight()*0.73f);
		AssetHandler.font.draw(sprite, ""+alphabet[initialIndexes[1]], 120, gameField.getHeight()*0.73f);
		AssetHandler.font.draw(sprite, ""+alphabet[initialIndexes[2]], 180, gameField.getHeight()*0.73f);
		sprite.end();
	}
}
