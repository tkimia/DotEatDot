package com.tkapps.Helpers;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.tkapps.GameObjects.Hero;
import com.tkapps.GameWorld.GameField;

public class InputHandler implements GestureListener {
	public static final int HERO_SPEED = 60;
	private GameField gameField;
	private Hero hero;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (Math.abs(velocityX) > Math.abs(velocityY)) {
			if (velocityX > 0) {
				hero.pushRight(HERO_SPEED);
			}
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
