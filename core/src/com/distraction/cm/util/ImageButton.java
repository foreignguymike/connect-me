package com.distraction.cm.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ImageButton {
	
	private float x;
	private float y;
	private float width;
	private float height;
	
	private TextureRegion image;
	
	public ImageButton(TextureRegion image, float x, float y) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.width = image.getRegionWidth();
		this.height = image.getRegionHeight();
	}
	
	public boolean contains(float mx, float my) {
		return mx > x - width / 2 && mx < x + width / 2 &&
				my > y - height / 2 && my < y + height / 2;
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(Color.WHITE);
		sb.draw(image, x - width / 2, y - height / 2);
	}
	
}
