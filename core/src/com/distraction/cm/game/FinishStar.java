package com.distraction.cm.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.util.Res;

public class FinishStar {
	
	private TextureRegion star;
	private float x;
	private float y;
	private float width;
	private float height;
	public static float SIZE = 60;
	private static float START_SIZE = 2000;
	private static float speed = START_SIZE * 4;
	
	private float timer;
	
	public FinishStar(float x, float y, float timer) {
		this.x = x;
		this.y = y;
		this.timer = timer;
		width = height = START_SIZE;
		star = Res.getAtlas().findRegion("star_on");
	}
	
	public void update(float dt) {
		timer -= dt;
		if(timer < 0 && width > SIZE) {
			width -= speed * dt;
			height -= speed * dt;
			if(width < SIZE) {
				width = height = SIZE;
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		if(timer < 0) {
			sb.draw(star, x - width / 2, y - height / 2, width, height);
		}
	}
	
}
