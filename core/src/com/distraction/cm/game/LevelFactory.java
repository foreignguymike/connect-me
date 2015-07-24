package com.distraction.cm.game;

public abstract class LevelFactory {
	private LevelFactory() {}
	
	private static final int R = 0;
	private static final int G = 1;
	private static final int B = 2;
	
	public static LevelData getLevel(int level) {
		switch(level) {
		case 1:
			return new LevelData(new int[][] {
					{R, G, R},
					{G, B, B},
					{B, R, B}},
					2);
		case 2:
			return new LevelData(new int[][] {
					{B, G, G, R},
					{R, R, G, B},
					{B, B, R, B}},
					5);
		case 3:
			return new LevelData(new int[][] {
					{B, G, G, R},
					{R, R, G, R},
					{B, B, R, G},
					{B, R, B, G}},
					5);
		case 4:
			return new LevelData(new int[][] {
					{B, G, G, R, G},
					{R, R, G, R, R},
					{B, B, R, G, B},
					{B, R, B, G, B},
					{R, G, G, B, R},},
					6);
		case 5:
			return new LevelData(new int[][] {
					{B, G, G, R, G, B, R},
					{R, R, G, B, R, G, B},
					{B, B, R, B, B, R, R},
					{G, G, R, R, B, B, R},
					{G, R, G, B, R, G, R},
					{B, R, G, G, B, G, B},
					{B, B, R, B, G, R, G}},
					-1);
		default:
			return new LevelData(new int[][] {
					{B, G, G, R},
					{R, R, G, R},
					{B, B, R, G},
					{B, R, B, G}},
					5);
		}
	}
}
