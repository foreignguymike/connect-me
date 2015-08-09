package com.distraction.cm.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.util.AnimationListener;
import com.distraction.cm.util.Res;

public class Cell {
	
	public enum CellType {
		RED(new Color(0xff4d4dff), "cell_red"),
		GREEN(new Color(0x4dff4dff), "cell_green"),
		BLUE(new Color(0x4d4dffff), "cell_blue");
		Color color;
		String name;
		private CellType(Color color, String name) {
			this.color = color;
			this.name = name;
		}
		public Color getColor() {
			return color;
		}
		public String getName() {
			return name;
		}
	}
	public static CellType[] cellTypeValues = CellType.values();
	
	public static int SIZE;
	public static int PADDING;
	
	private CellType cellType;
	
	private float x;
	private float y;
	private float xdest;
	private float ydest;
	private float dx;
	private float dy;
	
	private static float speed = 1000;
	
	private static float introTime = 0.2f;
	private float timer = 0;
	private float size = 0;
	
	private TextureRegion bg;
	
	private AnimationListener listener;
	
	public Cell(int type, float x, float y) {
		cellType = cellTypeValues[type];
		this.x = x;
		this.y = y;
		bg = Res.getAtlas().findRegion("grid_cell");
	}
	
	public void setListener(AnimationListener listener) {
		this.listener = listener;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setTimer(float f) {
		timer = f;
	}
	
	public void setDestination(float xdest, float ydest) {
		this.xdest = xdest;
		this.ydest = ydest;
		dx = xdest - x;
		dy = ydest - y;
		float dist = (float) Math.sqrt(dx * dx + dy * dy);
		dx /= dist;
		dy /= dist;
		listener.onStarted();
	}
	
	public CellType getType() {
		return cellType;
	}
	
	public boolean contains(float mx, float my) {
		return mx > x && mx < x + SIZE &&
				my > y && my < y + SIZE;
	}
	
	public void update(float dt) {
		if(timer < introTime) {
			timer += dt;
			if(timer >= introTime) {
				timer = introTime;
			}
			if(timer < 0) {
				size = 0;
			}
			else {
				size = (timer / introTime) * (SIZE - PADDING * 2);
			}
		}
		
		if(dx != 0 || dy != 0) {
			x += dx * speed * dt;
			y += dy * speed * dt;
			if((dx < 0 && x <= xdest) || (dx > 0 && x >= xdest)) {
				dx = 0;
				x = xdest;
				if(y == ydest) {
					listener.onFinished();
				}
			}
			if((dy < 0 && y <= ydest) || (dy > 0 && y >= ydest)) {
				dy = 0;
				y = ydest;
				if(x == xdest) {
					listener.onFinished();
				}
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(cellType.getColor());
		sb.draw(bg,
				x + Cell.SIZE / 2 - size / 2,
				y + Cell.SIZE / 2 - size / 2,
				size, size);
	}
	
}
