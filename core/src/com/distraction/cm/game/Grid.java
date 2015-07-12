package com.distraction.cm.game;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.CM;
import com.distraction.cm.game.Cell.CellType;
import com.distraction.cm.util.Content;

public class Grid {
	
	public static int PADDING = 20;
	public static int SIZE = CM.WIDTH - PADDING * 2;
	
	private TextureRegion bg;
	
	private Cell[][] grid;
	private int numRows;
	private int numCols;
	
	private float x;
	private float y;
	
	private int clickedRow;
	private int clickedCol;
	private Cell clickedCell;
	
	private int numMoves;
	
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
		
		bg = Content.getInstance().getAtlas().findRegion("grid");
		
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
				numMoves++;
				if(isFinished()) {
					System.out.println("numMoves: " + numMoves);
					System.out.println("finished");
				}
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
				numMoves++;
				if(isFinished()) {
					System.out.println("numMoves: " + numMoves);
					System.out.println("finished");
				}
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
				numMoves++;
				if(isFinished()) {
					System.out.println("numMoves: " + numMoves);
					System.out.println("finished");
				}
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
				numMoves++;
				if(isFinished()) {
					System.out.println("numMoves: " + numMoves);
					System.out.println("finished");
				}
			}
		}
	}
	
	public boolean isFinished() {
		boolean[][] visited = new boolean[numRows][numCols];
		boolean[] checkedColors = new boolean[Cell.cellTypeValues.length];
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				int type = grid[row][col].getType().ordinal();
				if(checkedColors[type] && !visited[row][col]) {
					return false;
				}
				checkedColors[type] = true;
				bfs(grid, visited, row, col);
			}
		}
		return true;
	}
	
	private static class Index {
		public int row;
		public int col;
		public Index(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private void bfs(Cell[][] grid, boolean[][] visited, int row, int col) {
		Queue<Index> queue = new LinkedList<Index>();
		queue.add(new Index(row, col));
		visited[row][col] = true;
		while(!queue.isEmpty()) {
			Index currentCell = queue.poll();
			CellType type = grid[currentCell.row][currentCell.col].getType();
			row = currentCell.row;
			col = currentCell.col;
			if(col > 0) {
				if(!visited[row][col - 1] && grid[row][col - 1].getType() == type) {
					queue.add(new Index(row, col - 1));
					visited[row][col - 1] = true;
				}
			}
			if(col < numCols - 1 && grid[row][col + 1].getType() == type) {
				if(!visited[row][col + 1]) {
					queue.add(new Index(row, col + 1));
					visited[row][col + 1] = true;
				}
			}
			if(row > 0 && grid[row - 1][col].getType() == type) {
				if(!visited[row - 1][col]) {
					queue.add(new Index(row - 1, col));
					visited[row - 1][col] = true;
				}
			}
			if(row < numRows - 1 && grid[row + 1][col].getType() == type) {
				if(!visited[row + 1][col]) {
					queue.add(new Index(row + 1, col));
					visited[row + 1][col] = true;
				}
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
		
		sb.setColor(Color.WHITE);
		sb.draw(bg,
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
