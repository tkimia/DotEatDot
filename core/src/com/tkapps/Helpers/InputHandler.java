package com.tkapps.Helpers;

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
			if (hero.isAlive() && y >= 285 && y <= 325 ) {
			 if (x > 35 && x < 100)
				 GameRenderer.initialIndexes[0] = (GameRenderer.initialIndexes[0]+1)%27;
			 else if (x > 100 && x < 170)
				 GameRenderer.initialIndexes[1] = (GameRenderer.initialIndexes[1]+1)%27;
			 else if (x > 170 && x < 240)
				 GameRenderer.initialIndexes[2] = (GameRenderer.initialIndexes[2]+1)%27;
			}
			else {
				gameField.restart();
				gameField.setCurrentState(GameState.READY);
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
			if (Math.abs(velocityX) < Math.abs(velocityY) && velocityY < 0) {
				DBHandler.submitScore(""+alphabet[GameRenderer.initialIndexes[0]]
						+alphabet[GameRenderer.initialIndexes[1]]+alphabet[GameRenderer.initialIndexes[2]], (int) gameField.getRunTime());
				gameField.highScores = DBHandler.getHighScores();
			}
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
