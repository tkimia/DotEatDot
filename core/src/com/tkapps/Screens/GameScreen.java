package com.tkapps.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tkapps.GameWorld.GameField;
import com.tkapps.GameWorld.GameRenderer;

public class GameScreen implements Screen{
	private GameField gameField;
	private GameRenderer gameRenderer;
	private float runTime = 0;
	
	public GameScreen() {
		//set the height and width of the game window
		float screenH = Gdx.graphics.getHeight();
		float screenW = Gdx.graphics.getWidth();
		float gameW = 240;
		float gameH = screenH / (screenW/gameW);
		
		gameField = new GameField();
		gameRenderer = new GameRenderer();
	}
	
	@Override
	public void render(float delta) {
		runTime += delta;
		gameField.update(delta);
		gameRenderer.render(runTime);

	}

	@Override
	public void resize(int width, int height) {
		 System.out.println("GameScreen - resizing");
		
	}

	@Override
	public void show() {
		 System.out.println("GameScreen - showing");
		
	}

	@Override
	public void hide() {
		 System.out.println("GameScreen - hiding");
		
	}

	@Override
	public void pause() {
		 System.out.println("GameScreen - pausing");
		
	}

	@Override
	public void resume() {
		 System.out.println("GameScreen - resuming");
		
	}

	@Override
	public void dispose() {
	
	}

}
