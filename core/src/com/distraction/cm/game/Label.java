package com.distraction.cm.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.util.Res;

public class Label {
	
	private float x;
	private float y;
	
	private String text;
	private String subtext;
	
	private TextureRegion bg;
	private float width;
	private float height;
	private Color bgColor = Res.PRIMARY_COLOR;
	
	private BitmapFont font;
	private float fontWidth;
	private float fontHeight;
	
	private float subtextWidth;
	private float subtextHeight;
	
	public Label(String text, float x, float y) {
		setText(text);
		this.x = x;
		this.y = y;
		
		bg = Res.getAtlas().findRegion("label");
		width = bg.getRegionWidth();
		height = bg.getRegionHeight();
		
		setSubtext("0");
	}
	
	public void setText(String text) {
		this.text = text;
		font = Res.getFont("RobotoLabel");
		GlyphLayout gl = new GlyphLayout(font, text);
		fontWidth = gl.width;
		fontHeight = gl.height;
	}
	
	public void setSubtext(String subtext) {
		this.subtext = subtext;
		GlyphLayout gl = new GlyphLayout(font, subtext);
		subtextWidth = gl.width;
		subtextHeight = gl.height;
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(bgColor);
		sb.draw(bg, x - width / 2, y - height / 2);
		font.setColor(Color.WHITE);
		font.draw(sb, text, x - fontWidth / 2, y + fontHeight * 1.5f);
		font.draw(sb, subtext, x - subtextWidth / 2, y - subtextHeight / 2);
	}
	
}
