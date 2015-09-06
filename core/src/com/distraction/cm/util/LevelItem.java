package com.distraction.cm.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.CM;
import com.distraction.cm.game.Header;

public class LevelItem {
	
	public static int HEIGHT = 100;
	private static BitmapFont font;
	private static Color fontColor = new Color(0, 0, 0, 0.87f);
	private static final int TEXT_PADDING_X = 50;
	private static final int TEXT_PADDING_Y = 42;
	
	private String text;
	private int stars;
	private TextureRegion starOn;
	private TextureRegion starOff;
	private static final int STAR_PADDING = 160;
	private static final int STAR_SPACING = 52;
	
	private float y;
	
	private TextureRegion pixel;
	private Color color;
	
	public LevelItem(String text, int stars) {
		
		this.text = text;
		this.stars = stars;
		
		pixel = Res.getAtlas().findRegion("pixel");
		starOff = Res.getAtlas().findRegion("star_off");
		starOn = Res.getAtlas().findRegion("star_on");
		
		if(font == null) {
			font = Res.getFont("RobotoLevelItem");
		}
		
	}
	
	public int getStars() {
		return stars;
	}
	
	public void setIndex(int index) {
		if(index % 2 == 0) {
			color = null;
		}
		else {
			color = new Color(0x00000010);
		}
		y = CM.HEIGHT - Header.HEIGHT - index * HEIGHT;
	}
	
	public void render(SpriteBatch sb) {
		if(color != null) {
			sb.setColor(color);
			sb.draw(pixel, 0, y - HEIGHT, CM.WIDTH, HEIGHT);
		}
		font.setColor(fontColor);
		font.draw(sb, text, TEXT_PADDING_X, y - TEXT_PADDING_Y);
		sb.setColor(1, 1, 1, 1);
		if(stars >= 0) {
			for(int i = 0; i < 4; i++) {
				if(i < stars) {
					sb.draw(starOn, STAR_PADDING + i * STAR_SPACING, y - HEIGHT + (HEIGHT - starOn.getRegionHeight()) / 2);
				}
				else {
					sb.draw(starOff, STAR_PADDING + i * STAR_SPACING, y - HEIGHT + (HEIGHT - starOff.getRegionHeight()) / 2);
				}
			}
		}
	}
	
}
