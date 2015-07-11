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
	
	private int clickedRow;
	private int clickedCol;
	private Cell clickedCell;
	
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
	
	public void click(float mx, float my) {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				if(grid[row][col].contains(mx, my)) {
					clickedRow = row;
					clickedCol = col;
					clickedCell = grid[row][col];
					break;
				}
			}
		}
	}
	
	public void move(int dx, int dy) {
		if(clickedCell == null) {
			return;
		}
		
		if(dx > 0) {
			if(clickedCol < numCols - 1) {
				Cell temp = grid[clickedRow][clickedCol];
				grid[clickedRow][clickedCol] = grid[clickedRow][clickedCol + 1];
				grid[clickedRow][clickedCol + 1] = temp;
				grid[clickedRow][clickedCol].setDestination(
						x + clickedCol * Cell.SIZE,
						y + (numRows - clickedRow - 1) * Cell.SIZE);
				grid[clickedRow][clickedCol + 1].setDestination(
						x + (clickedCol + 1) * Cell.SIZE,
						y + (numRows - clickedRow - 1) * Cell.SIZE);
				clickedCell = null;
			}
		}
		else if(dx < 0) {
			if(clickedCol > 0) {
				Cell temp = grid[clickedRow][clickedCol];
				grid[clickedRow][clickedCol] = grid[clickedRow][clickedCol - 1];
				grid[clickedRow][clickedCol - 1] = temp;
				grid[clickedRow][clickedCol].setDestination(
						x + clickedCol * Cell.SIZE,
						y + (numRows - clickedRow - 1) * Cell.SIZE);
				grid[clickedRow][clickedCol - 1].setDestination(
						x + (clickedCol - 1) * Cell.SIZE,
						y + (numRows - clickedRow - 1) * Cell.SIZE);
				clickedCell = null;
			}
		}
		else if(dy > 0) {
			if(clickedRow > 0) {
				Cell temp = grid[clickedRow][clickedCol];
				grid[clickedRow][clickedCol] = grid[clickedRow - 1][clickedCol];
				grid[clickedRow - 1][clickedCol] = temp;
				grid[clickedRow][clickedCol].setDestination(
						x + clickedCol * Cell.SIZE,
						y + (numRows - clickedRow - 1) * Cell.SIZE);
				grid[clickedRow - 1][clickedCol].setDestination(
						x + clickedCol * Cell.SIZE,
						y + (numRows - clickedRow - 0) * Cell.SIZE);
				clickedCell = null;
			}
		}
		else if(dy < 0) {
			if(clickedRow < numRows - 1) {
				Cell temp = grid[clickedRow][clickedCol];
				grid[clickedRow][clickedCol] = grid[clickedRow + 1][clickedCol];
				grid[clickedRow + 1][clickedCol] = temp;
				grid[clickedRow][clickedCol].setDestination(
						x + clickedCol * Cell.SIZE,
						y + (numRows - clickedRow - 1) * Cell.SIZE);
				grid[clickedRow + 1][clickedCol].setDestination(
						x + clickedCol * Cell.SIZE,
						y + (numRows - clickedRow - 2) * Cell.SIZE);
				clickedCell = null;
			}
		}
	}
	
	public void update(float dt) {
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				grid[row][col].update(dt);
			}
		}
	}
	
	public void render(SpriteBatch sb) {
		
		sb.setColor(bgColor);
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
