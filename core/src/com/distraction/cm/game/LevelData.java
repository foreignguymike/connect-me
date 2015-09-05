package com.distraction.cm.game;

public class LevelData {
	public static final int NUM_LEVELS = 300;
	private int[][] grid;
	private int minMoves;
	public LevelData(int[][] grid, int minMoves) {
		this.grid = grid;
		this.minMoves = minMoves;
	}
	public int[][] getGrid() {
		return grid;
	}
	public int getMinMoves() {
		return minMoves;
	}
}
