package com.tkapps.GameWorld;

import com.badlogic.gdx.Gdx;
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
	
	public GameRenderer(GameField gameField) {
		this.gameField = gameField;
		hero = gameField.getHero();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameField.getWidth(), gameField.getHeight());
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}
	
	public void render(float runTime) {
		//setup
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		//put hero down
		shapeRenderer.begin(ShapeType.Filled);
		
		shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		shapeRenderer.circle(hero.getCircle().x, hero.getCircle().y, hero.getCircle().radius);
		
		for (Dot e : gameField.getEnemies()) {
			shapeRenderer.setColor(ColorHelper.getColor(e.getCircle().radius));
			shapeRenderer.circle(e.getCircle().x, e.getCircle().y, e.getCircle().radius);
		}
		
		shapeRenderer.end();
	}

}
