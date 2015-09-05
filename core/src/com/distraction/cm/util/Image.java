package com.distraction.cm.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Image {
	
	private TextureRegion image;
	private float x;
	private float y;
	private float w;
	private float h;
	
	public Image(TextureRegion image, float x, float y) {
		this.image = image;
		this.x = x;
		this.y = y;
		w = image.getRegionWidth();
		h = image.getRegionHeight();
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(image, x - w / 2, y - h / 2);
	}
	
}
