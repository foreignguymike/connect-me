package com.distraction.cm.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cell {
	
	public enum CellType {
		RED(Color.RED),
		GREEN(Color.GREEN),
		BLUE(Color.BLUE);
		Color color;
		private CellType(Color color) {
			this.color = color;
		}
		public Color getColor() {
			return color;
		}
	}
	public static CellType[] cellTypeValues = CellType.values();
	
	public static int SIZE;
	public static int PADDING = 10;
	
	private CellType cellType;
	private Texture tex;
	
	private float x;
	private float y;
	private float xdest;
	private float ydest;
	private float dx;
	private float dy;
	
	private static float speed = 2000;
	
	public Cell(int type, float x, float y) {
		cellType = cellTypeValues[type];
		tex = new Texture("pixel.png");
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void setDestination(float xdest, float ydest) {
		this.xdest = xdest;
		this.ydest = ydest;
		dx = xdest - x;
		dy = ydest - y;
		float dist = (float) Math.sqrt(dx * dx + dy * dy);
		dx /= dist;
		dy /= dist;
		
	}
	
	public boolean contains(float mx, float my) {
		return mx > x && mx < x + SIZE &&
				my > y && my < y + SIZE;
	}
	
	public void update(float dt) {
		x += dx * speed * dt;
		y += dy * speed * dt;
		if((dx < 0 && x <= xdest) || (dx > 0 && x >= xdest)) {
			dx = 0;
			x = xdest;
		}
		if((dy < 0 && y <= ydest) || (dy > 0 && y >= ydest)) {
			dy = 0;
			y = ydest;
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(cellType.getColor());
		sb.draw(tex,
				x + PADDING,
				y + PADDING,
				SIZE - PADDING * 2,
				SIZE - PADDING * 2);
	}
	
}
