package com.tkapps.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundHandler {

	//sounds
	public static Sound crunch, bossCrunch, bounce;
	
	/**
	 * Loads sounds from their place in the file heirarchy.
	 */
	public static void load() {
		crunch = Gdx.audio.newSound(Gdx.files.internal("sounds/crunch.wav"));
		bossCrunch = Gdx.audio.newSound(Gdx.files.internal("sounds/bosscrunch.wav"));
		bounce = Gdx.audio.newSound(Gdx.files.internal("sounds/bounce.wav"));
	}
	
	/**
	 * gets rid of sound objects. Must be called if load was called.
	 */
	public static void dispose() {
		crunch.dispose();
		bossCrunch.dispose();
		bounce.dispose();
	}
}
