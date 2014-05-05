package com.tkapps.DotEatDot;

import com.badlogic.gdx.Game;
import com.tkapps.Helpers.AssetHandler;
import com.tkapps.Helpers.SoundHandler;
import com.tkapps.Screens.*;


public class MyGdxGame extends Game {
	
	@Override
	public void create () {
		SoundHandler.load();
		AssetHandler.load();
		setScreen(new GameScreen());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		SoundHandler.dispose();
		AssetHandler.dispose();
	}
	
	
}
