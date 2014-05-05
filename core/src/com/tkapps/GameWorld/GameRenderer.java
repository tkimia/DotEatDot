package com.tkapps.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tkapps.GameObjects.Dot;
import com.tkapps.GameObjects.Hero;
import com.tkapps.Helpers.ColorHelper;

public class GameRenderer {
	private GameField gameField;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
		
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
	}
	
	public void render(float runTime) {
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
		
		shapeRenderer.setColor(Color.LIGHT_GRAY);
		for (Dot e : gameField.getUntouchables()) 
			shapeRenderer.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
		//put hero down
		if (hero.isAlive()) {
			shapeRenderer.setColor(ColorHelper.heroColor);
			shapeRenderer.circle(hero.getCircle().x, hero.getCircle().y, hero.getCircle().radius);
		}
		
		//put big boss down
		if (bigBoss.isAlive()) {
			shapeRenderer.setColor(ColorHelper.getColor(bigBoss.getCircle().radius));
			shapeRenderer.circle(bigBoss.getCircle().x, bigBoss.getCircle().y, bigBoss.getCircle().radius);
		}
		//put enemies down
		Dot[] enemies = gameField.getEnemies().toArray(new Dot[]{}); 
		for (Dot e :  enemies){
			shapeRenderer.setColor(ColorHelper.getColor(e.getCircle().radius));
			shapeRenderer.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
		}
		
		shapeRenderer.end();
		

	}

}
