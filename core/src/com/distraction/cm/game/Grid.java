package com.distraction.cm.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.CM;

public class Grid {
	
	public static int PADDING = 20;
	public static int SIZE = CM.WIDTH - PADDING * 2;
	
	private Color bgColor = new Color(0, 0, 0, 1);
	private Texture tex;
	
	private Cell[][] grid;
	private int numRows;
	private int numCols;
	
	private float x;
	private float y;
	
	public Grid(int[][] types) {
		
		numRows = types.length;
		numCols = types[0].length;
		grid = new Cell[numRows][numCols];
		
		Cell.SIZE = SIZE / numCols;
		
		x = PADDING;
		y = (CM.HEIGHT - SIZE) / 2;
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				Cell cell = new Cell(
						types[row][col],
						x + col * Cell.SIZE,
						y + (numRows - row - 1) * Cell.SIZE);
				grid[row][col] = cell;
			}
		}
		
		tex = new Texture("pixel.png");
		
	}
	
	public void render(SpriteBatch sb) {
		
		sb.setColor(Color.BLACK);
		sb.draw(tex,
				x - Cell.PADDING,
				y - Cell.PADDING,
				SIZE + Cell.PADDING * 2,
				SIZE + Cell.PADDING * 2);
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				grid[row][col].render(sb);
			}
		}
		
	}
	
}
