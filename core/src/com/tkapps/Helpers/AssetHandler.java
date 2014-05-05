package com.tkapps.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetHandler {
	public static BitmapFont font;
	
	public static void load() {
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.setScale(.2f,  -.2f);

	}
	
	public static void dispose(){
		font.dispose();
	}
}
