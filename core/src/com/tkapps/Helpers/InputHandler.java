package com.tkapps.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector.GestureListener;

import com.badlogic.gdx.math.Vector2;
import com.tkapps.GameObjects.Hero;
import com.tkapps.GameWorld.GameField;
import com.tkapps.GameWorld.GameRenderer;
import com.tkapps.GameWorld.GameState;

public class InputHandler implements GestureListener {
	public static final int HERO_SPEED = 100;
	
	private int taps;
	private GameField gameField;
	private Hero hero;
	private boolean submitted = false;
	
	private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
	
	public InputHandler(GameField gameField) {
		this.gameField = gameField;
		hero = gameField.getHero();
		
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		
		if (gameField.getCurrentState() == GameState.READY) {
			gameField.setCurrentState(GameState.RUNNING);
		}
		else if (gameField.getCurrentState() == GameState.RUNNING) {
			taps++;
			//stop hero
			if (taps > 1 ) hero.setVelocity(new Vector2(0, 0));
		}
		else {
			System.out.printf("%f %f", x, y);
			if (hero.isAlive() && y >=  Gdx.graphics.getHeight()*0.7f && y <=  Gdx.graphics.getHeight()*0.8f ) {
				float width = Gdx.graphics.getWidth();
			 if (x > width*0.1 && x < width*0.39)
				 GameRenderer.initialIndexes[0] = (GameRenderer.initialIndexes[0]+1)%27;
			 else if (x > width*0.4 && x < width*0.65)
				 GameRenderer.initialIndexes[1] = (GameRenderer.initialIndexes[1]+1)%27;
			 else if (x > width*0.7 && x < width*0.9)
				 GameRenderer.initialIndexes[2] = (GameRenderer.initialIndexes[2]+1)%27;
			}
			else {
				gameField.restart();
				gameField.setCurrentState(GameState.READY);
				submitted = false;
			}
			
		}
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (gameField.getCurrentState() == GameState.READY) {
			return false;
		}
		else if (gameField.getCurrentState() == GameState.RUNNING) {
			taps = 0;
			hero = gameField.getHero();
			if (Math.abs(velocityX) > Math.abs(velocityY)) {
				if (velocityX > 0)
					hero.pushRight(HERO_SPEED);
				else
					hero.pushLeft(HERO_SPEED);
			}
			else {
				if (velocityY > 0)
					hero.pushDown(HERO_SPEED);
				else
					hero.pushUp(HERO_SPEED);
			}
			return true;
		}
		else {
			return true;
		}
		
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
