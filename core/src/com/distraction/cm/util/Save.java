package com.distraction.cm.util;

import java.io.BufferedReader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.distraction.cm.game.LevelData;
import com.distraction.cm.game.LevelFactory;

public abstract class Save {
	
	private static final String path = "save.dat";
	private static int[] moves;
	
	public static void save() {
		if(moves == null) {
			moves = new int[LevelData.NUM_LEVELS];
		}
		FileHandle file = Gdx.files.local(path);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < moves.length; i++) {
			sb.append(moves[i]);
			sb.append("\n");
		}
		file.writeString(sb.toString(), false);
	}
	
	public static void load() {
		
		FileHandle file = Gdx.files.internal(path);
		
		if(!file.exists()) {
			save();
		}
		
		moves = new int[LevelData.NUM_LEVELS];
		BufferedReader br = file.reader(1024);
		for(int i = 0; i < moves.length; i++) {
			try {
				int move = Integer.parseInt(br.readLine());
				moves[i] = move;
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void set(int i, int move) {
		if(moves[i] == 0 || move < moves[i]) {
			moves[i] = move;
		}
	}
	
	public static int getNumStars(int i) {
		LevelData data = LevelFactory.getLevel(i + 1);
		int minMoves = data.getMinMoves();
		int move = moves[i];
		if(move == 0) {
			return 0;
		}
		else if(move == minMoves) {
			return 4;
		}
		else if(move <= (int) (minMoves * 1.5f)) {
			return 3;
		}
		else if(move <= minMoves * 2) {
			return 2;
		}
		else if(move > minMoves * 2) {
			return 1;
		}
		return 0;
	}
	
}
