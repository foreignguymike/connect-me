package com.distraction.cm.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.CM;
import com.distraction.cm.util.ClickListener;
import com.distraction.cm.util.Res;
import com.distraction.cm.util.ImageButton;

public class Header {
	
	private TextureRegion pixel;
	private Color color = new Color(0x594d40ff);
	public static int HEIGHT = 70;
	
	private ImageButton back;
	private ClickListener backListener;
	private static final int BACK_PADDING = 35;
	
	private boolean refreshVisible;
	private ImageButton refresh;
	private ClickListener refreshListener;
	private static final int REFRESH_PADDING = CM.WIDTH - 35;
	
	private String title;
	
	private BitmapFont titleFont;
	
	private int stars = -1;
	private static final float STAR_SIZE = 32;
	private static final int STAR_PADDING = 250;
	private TextureRegion starOn;
	private TextureRegion starOff;
	
	public Header() {
		
		TextureAtlas atlas = Res.getAtlas();
		
		pixel = atlas.findRegion("pixel");
		back = new ImageButton(atlas.findRegion("back"), BACK_PADDING, CM.HEIGHT - HEIGHT / 2);
		refresh = new ImageButton(atlas.findRegion("refresh"), REFRESH_PADDING, CM.HEIGHT - HEIGHT / 2);
		
		titleFont = Res.getFont("RobotoTitle");
		
		refreshVisible = true;
		
		starOn = atlas.findRegion("star_on");
		starOff = atlas.findRegion("star_off");
		
	}
	
	public void setBackClickListener(ClickListener backListener) {
		this.backListener = backListener;
	}
	
	public void setRefreshClickListener(ClickListener refreshListener) {
		this.refreshListener = refreshListener;
	}
	
	public void setRefreshVisible(boolean b) {
		refreshVisible = b;
	}
	
	public void setStars(int stars) {
		this.stars = stars;
	}
	
	public void click(float mx, float my) {
		if(back.contains(mx, my)) {
			if(backListener != null) {
				backListener.onClick();
			}
		}
		if(refreshVisible && refresh.contains(mx, my)) {
			if(refreshListener != null) {
				refreshListener.onClick();
			}
		}
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(color);
		sb.draw(pixel, 0, CM.HEIGHT, CM.WIDTH, -HEIGHT);
		back.render(sb);
		if(refreshVisible) {
			refresh.render(sb);
		}
		titleFont.draw(sb, title, 80, CM.HEIGHT - 25);
		if(stars != -1) {
			for(int i = 0; i < 4; i++) {
				if(i < stars) {
					sb.draw(starOn, STAR_PADDING + i * STAR_SIZE, CM.HEIGHT - HEIGHT + (HEIGHT - STAR_SIZE) / 2, STAR_SIZE, STAR_SIZE);
				}
				else {
					sb.draw(starOff, STAR_PADDING + i * STAR_SIZE, CM.HEIGHT - HEIGHT + (HEIGHT - STAR_SIZE) / 2, STAR_SIZE, STAR_SIZE);
				}
			}
		}
	}
	
}
