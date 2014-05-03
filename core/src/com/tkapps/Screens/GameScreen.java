package com.tkapps.Screens;

import com.badlogic.gdx.Screen;

public class GameScreen implements Screen{
	
	@Override
	public void render(float delta) {
		
		
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
