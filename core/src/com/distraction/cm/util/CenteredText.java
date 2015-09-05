package com.distraction.cm.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.CM;

public class CenteredText {
	
	private String text;
	private BitmapFont font;
	private float x;
	private float y;
	private float w;
	
	public CenteredText(String text, BitmapFont font, float y) {
		this.text = text;
		this.font = font;
		this.y = y;
		GlyphLayout gl = new GlyphLayout(font, text);
		w = gl.width;
		x = CM.WIDTH / 2;
	}
	
	public void render(SpriteBatch sb) {
		font.draw(sb, text, x - w / 2, y);
	}
	
}
