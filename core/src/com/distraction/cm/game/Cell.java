package com.distraction.cm.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.util.AnimationListener;
import com.distraction.cm.util.Content;

public class Cell {
	
	public enum CellType {
		RED(Color.RED, "cell_red"),
		GREEN(Color.GREEN, "cell_green"),
		BLUE(Color.BLUE, "cell_blue");
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
	public static int PADDING = 10;
	
	private CellType cellType;
	
	private float x;
	private float y;
	private float xdest;
	private float ydest;
	private float dx;
	private float dy;
	
	private static float speed = 1000;
	
	private TextureRegion bg;
	
	private AnimationListener listener;
	
	public Cell(int type, float x, float y) {
		cellType = cellTypeValues[type];
		this.x = x;
		this.y = y;
		bg = Content.getInstance().getAtlas().findRegion(cellType.getName());
	}
	
	public void setListener(AnimationListener listener) {
		this.listener = listener;
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
		if(x == xdest && y == ydest) {
			listener.onFinished();
		}
	}
	
	public void render(SpriteBatch sb) {
		sb.setColor(Color.WHITE);
		sb.draw(bg,
				x + PADDING,
				y + PADDING,
				SIZE - PADDING * 2,
				SIZE - PADDING * 2);
	}
	
}
