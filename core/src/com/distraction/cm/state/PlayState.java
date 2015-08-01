package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.game.Grid;
import com.distraction.cm.game.Header;
import com.distraction.cm.game.LevelData;
import com.distraction.cm.game.LevelFactory;
import com.distraction.cm.util.ClickListener;
import com.distraction.cm.util.GridListener;
import com.distraction.cm.util.Save;

public class PlayState extends State {
	
	private final int DRAG_DIST = 50;
	
	private Header header;
	private Grid grid;
	
	private float startx;
	private float starty;
	
	private int level;
	private int moves;
	
	public PlayState(final GSM gsm, int level, int moves) {
		
		super(gsm);
		this.level = level;
		this.moves = moves;
		
		createGrid();
		
		header = new Header();
		header.setTitle("Level " + level);
		header.setStars(Save.getNumStars(level - 1));
		header.setBackClickListener(new ClickListener() {
			@Override
			public void onClick() {
				LevelSelectState nextState = new LevelSelectState(gsm);
				CheckeredTransitionState state = new CheckeredTransitionState(gsm, PlayState.this, nextState);
				gsm.set(state);
			}
		});
		header.setRefreshClickListener(new ClickListener() {
			@Override
			public void onClick() {
				createGrid();
			}
		});
		
		Gdx.input.setInputProcessor(this);
		
	}
	
	private void createGrid() {
		final LevelData data = LevelFactory.getLevel(level);
		System.out.println("created level " + level);
		grid = new Grid(data.getGrid());
		grid.setListener(new GridListener() {
			@Override
			public void onFinished() {
				Save.set(level - 1, grid.getNumMoves());
				Save.save();
			}
		});
	}
	
	@Override
	public void update(float dt) {
		grid.update(dt);
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		grid.render(sb);
		header.render(sb);
		
		sb.end();
		
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		unproject(m, cam);
		grid.click(m.x, m.y);
		startx = m.x;
		starty = m.y;
		header.click(m.x, m.y);
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int p) {
		unproject(m, cam);
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
		return true;
	}
	
}
