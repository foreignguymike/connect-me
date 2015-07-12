package com.distraction.cm.util;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Content {
	
	private static Content res;
	public static Content getInstance() {
		if(res == null) {
			res = new Content();
		}
		return res;
	}
	
	private TextureAtlas atlas;
	private Content() {}
	
	public void loadAtlas(String path) {
		atlas = new TextureAtlas(path);
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
}
