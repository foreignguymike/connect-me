package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.game.Grid;

public class PlayState extends State {
	
	private Grid grid;
	
	public PlayState(GSM gsm) {
		
		super(gsm);
		
		int[][] g = {
				{0, 1, 0},
				{1, 2, 2},
				{2, 0, 2}
		};
		grid = new Grid(g);
		
	}
	
	public void update(float dt) {
		
	}
	
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		grid.render(sb);
		sb.end();
		
	}
	
}
