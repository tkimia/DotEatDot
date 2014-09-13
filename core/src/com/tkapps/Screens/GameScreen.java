package com.tkapps.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.tkapps.GameWorld.GameField;
import com.tkapps.GameWorld.GameRenderer;
import com.tkapps.Helpers.InputHandler;

public class GameScreen implements Screen{
	public static float gameWidth = 0;
	public static float gameHeight = 0;
	private GameField gameField;
	private GameRenderer gameRenderer;
	private float runTime = 0;
	
	public GameScreen() {
		//set the height and width of the game window
		float screenH = Gdx.graphics.getHeight();
		float screenW = Gdx.graphics.getWidth();
		gameWidth = 240;
		gameHeight = screenH / (screenW/gameWidth);
		
		gameField = new GameField(gameHeight, gameWidth);
		gameRenderer = new GameRenderer(gameField);
		Gdx.input.setInputProcessor(new GestureDetector(new InputHandler(gameField)));
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
