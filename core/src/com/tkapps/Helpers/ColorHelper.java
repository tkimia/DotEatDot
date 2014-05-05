package com.tkapps.Helpers;

import com.badlogic.gdx.graphics.Color;

public class ColorHelper {
	public static final Color heroColor = new Color(255.0f/255.0f, 244.0f/255.0f, 66f/255f, 1f);
	private static final Color enemyColors[] = { 
		new Color(255.0f/255.0f, 138.0f/255.0f, 131/255f, 1f), 
		new Color(232f/255.0f, 51f/255.0f, 172f/255f, 1f), 
		new Color(201f/255.0f, 69f/255.0f, 255f/255f, 1f), 
		new Color(105f/255.0f, 65f/255.0f, 232f/255f, 1f), 
		new Color(31.0f/255.0f, 58.0f/255.0f, 148f/255f, 1f)  
	};
	
	public static Color getColor(float size) {
		return enemyColors[(int) ((size-1)/6)];
		
	}
}
