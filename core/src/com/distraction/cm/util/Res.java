package com.distraction.cm.util;

import java.io.BufferedReader;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.distraction.cm.game.LevelData;

public class Res {
	
	private static TextureAtlas atlas;
	private static HashMap<String, BitmapFont> fontMap;
	public static Color PRIMARY_COLOR;
	
	public static LevelData[] data;
	public static LevelData[] global;
	
	public static void init() {
		fontMap = new HashMap<String, BitmapFont>();
		createFont("fonts/Roboto-Black.ttf", "RobotoTitle", "LevlSctTuoriaGob 0123456789", 32);
		createFont("fonts/RobotoCondensed-Regular.ttf", "RobotoLevelItem", "Levl 0123456789", 24);
		createFont("fonts/Roboto-Black.ttf", "RobotoLabel", "PlayTutoriOnegBsMv-d 0123456789", 20);
		createFont("fonts/RobotoCondensed-Regular.ttf", "RobotoDistraction", "distracon", 16);
		createFont("fonts/Roboto-Black.ttf", "RobotoText", 24);
//		PRIMARY_COLOR = new Color(0x594d40ff);
		PRIMARY_COLOR = new Color(0x303d39ff);
		data = new LevelData[LevelData.NUM_LEVELS];
		loadFiles();
	}
	
	private static void loadFiles() {
		for(int i = 1; i <= LevelData.NUM_LEVELS; i++) {
			String path = "level/" + i;
			FileHandle file = Gdx.files.internal(path);
			BufferedReader br = file.reader(1024);
			try {
				int minMoves = Integer.parseInt(br.readLine());
				int numRows = Integer.parseInt(br.readLine());
				int numCols = Integer.parseInt(br.readLine());
				int[][] g = new int[numRows][numCols];
				for(int row = 0; row < numRows; row++) {
					String line = br.readLine();
					String[] split = line.split(",");
					for(int col = 0; col < numCols; col++) {
						String s = split[col];
						int val = Integer.parseInt(s);
						g[row][col] = val;
					}
				}
				data[i - 1] = new LevelData(g, minMoves);
			}
			catch(Exception e) {
				e.printStackTrace();
				Gdx.app.exit();
			}
		}
		for(int i = 1; i <= 10; i++) {
			String path = "global/" + i;
			FileHandle file = Gdx.files.internal(path);
			BufferedReader br = file.reader(1024);
			try {
				int minMoves = Integer.parseInt(br.readLine());
				int numRows = Integer.parseInt(br.readLine());
				int numCols = Integer.parseInt(br.readLine());
				int[][] g = new int[numRows][numCols];
				for(int row = 0; row < numRows; row++) {
					String line = br.readLine();
					String[] split = line.split(",");
					for(int col = 0; col < numCols; col++) {
						String s = split[col];
						int val = Integer.parseInt(s);
						g[row][col] = val;
					}
				}
				data[i - 1] = new LevelData(g, minMoves);
			}
			catch(Exception e) {
				e.printStackTrace();
				Gdx.app.exit();
			}
		}
	}
	
	private static void createFont(String path, String key, int size) {
		String chars = FreeTypeFontGenerator.DEFAULT_CHARS;
		createFont(path, key, chars, size);
	}
	
	private static void createFont(String path, String key, String chars, int size) {
		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(path));
		FreeTypeFontParameter params = new FreeTypeFontParameter();
		params.characters = chars;
		params.minFilter = TextureFilter.Linear;
		params.magFilter = TextureFilter.Linear;
		params.size = size;
		BitmapFont font = gen.generateFont(params);
		fontMap.put(key, font);
		gen.dispose();
	}
	
	public static void loadAtlas(String path) {
		atlas = new TextureAtlas(path);
		atlas.getTextures().first().setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	public static TextureAtlas getAtlas() {
		return atlas;
	}
	
	public static BitmapFont getFont(String s) {
		return fontMap.get(s);
	}
	
}
