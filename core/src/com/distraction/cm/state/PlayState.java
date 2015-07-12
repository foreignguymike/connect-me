package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.game.Grid;

public class PlayState extends State {
	
	private final int DRAG_DIST = 50;
	
	private Grid grid;
	
	private float startx;
	private float starty;
	
	public PlayState(GSM gsm) {
		
		super(gsm);
		
		int[][] g = {
				{0, 1, 0, 2},
				{1, 2, 2, 1},
				{2, 0, 2, 0},
				{1, 1, 0, 2}
		};
		grid = new Grid(g);
		
	}
	
	public void update(float dt) {
		
		if(Gdx.input.justTouched()) {
			m.x = Gdx.input.getX();
			m.y = Gdx.input.getY();
			cam.unproject(m);
			grid.click(m.x, m.y);
			startx = m.x;
			starty = m.y;
		}
		else if(Gdx.input.isTouched()) {
			m.x = Gdx.input.getX();
			m.y = Gdx.input.getY();
			cam.unproject(m);
			if(m.x > startx + DRAG_DIST) {
				grid.move(1, 0);
			}
			else if(m.x < startx - DRAG_DIST) {
				grid.move(-1, 0);
			}
			else if(m.y > starty + DRAG_DIST) {
				grid.move(0, 1);
			}
			else if(m.y < starty - DRAG_DIST) {
				grid.move(0, -1);
			}
		}
		
		grid.update(dt);
		
	}
	
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		grid.render(sb);
		sb.end();
		
	}
	
}
