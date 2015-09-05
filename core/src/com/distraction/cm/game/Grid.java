package com.distraction.cm.game;

import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.distraction.cm.CM;
import com.distraction.cm.game.Cell.CellType;
import com.distraction.cm.util.AnimationListener;
import com.distraction.cm.util.GridListener;
import com.distraction.cm.util.Res;

public class Grid implements AnimationListener {
	
	public static int PADDING = 20;
	public static int WIDTH = CM.WIDTH - PADDING * 2;
	public static int HEIGHT;
	
	private TextureRegion bg;
	private Color bgColor = Res.PRIMARY_COLOR;
	
	private Cell[][] grid;
	private int numRows;
	private int numCols;
	
	private float x;
	private float y;
	
	private int clickedRow;
	private int clickedCol;
	private Cell clickedCell;
	
	private int numMoves;
	
	private GridListener listener;
	
	private boolean finished;
	
	public Grid(int[][] types) {
		
		numRows = types.length;
		numCols = types[0].length;
		grid = new Cell[numRows][numCols];
		
		Cell.SIZE = WIDTH / numCols;
		Cell.PADDING = 2 * Cell.SIZE / 32;
		HEIGHT = numRows * Cell.SIZE;
		
		x = PADDING;
		y = 2 * (CM.HEIGHT - HEIGHT) / 5;
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				Cell cell = new Cell(
						types[row][col],
						x + col * Cell.SIZE,
						y + (numRows - row - 1) * Cell.SIZE);
				cell.setListener(this);
				cell.setTimer((row + col) * -0.1f);
				grid[row][col] = cell;
			}
		}
		
		bg = Res.getAtlas().findRegion("grid_bg");
		
	}
	
	public void setListener(GridListener listener) {
		this.listener = listener;
	}
	
	public int getNumMoves() {
		return numMoves;
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
	
	public boolean move(int dx, int dy) {
		
		if(finished) {
			return false;
		}
		
		if(animationCount > 0) {
			return false;
		}
		
		if(clickedCell == null) {
			return false;
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
			}
		}
		
		return true;
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
		finished = true;
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
		
		sb.setColor(bgColor);
		sb.draw(bg, x - Cell.PADDING, y - Cell.PADDING, WIDTH + Cell.PADDING * 2, HEIGHT + Cell.PADDING * 2);
		
		for(int row = 0; row < numRows; row++) {
			for(int col = 0; col < numCols; col++) {
				grid[row][col].render(sb);
			}
		}
		
	}
	
	private int animationCount;
	
	@Override
	public void onStarted() {
		animationCount++;
	}
	
	@Override
	public void onFinished() {
		animationCount--;
		if(animationCount == 0 && isFinished() && listener != null) {
			listener.onFinished();
		}
	}
	
}
