package com.tkapps.Helpers;

import com.badlogic.gdx.graphics.Color;

public class ColorHelper {
	private static final Color colors[] = { 
		new Color(253.0f/255.0f, 5.0f/255.0f, 40f/255f, 1f), //brightest wine
		new Color(227f/255.0f, 8f/255.0f, 33f/255f, 1f), //bright wine
		new Color(189f/255.0f, 6f/255.0f, 31f/255f, 1f), //red wine
		new Color(142f/255.0f, 4f/255.0f, 11f/255f, 1f), //dark wine
		new Color(110.0f/255.0f, 2.0f/255.0f, 20f/255f, 1f)  //dark purple wine
	};
	
	public static Color getColor(float size) {
		return colors[(int) ((size-1)/5)];
		
	}
}
