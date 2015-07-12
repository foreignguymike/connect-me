package com.distraction.cm.util;

import com.badlogic.gdx.graphics.Texture.TextureFilter;
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
		atlas.getTextures().first().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	
}
