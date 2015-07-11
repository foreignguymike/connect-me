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
	
	public Cell(int type, float x, float y) {
		cellType = cellTypeValues[type];
		tex = new Texture("pixel.png");
		this.x = x;
		this.y = y;
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
