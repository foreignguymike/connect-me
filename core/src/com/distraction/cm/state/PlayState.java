package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.distraction.cm.CM;
import com.distraction.cm.game.FinishStar;
import com.distraction.cm.game.Grid;
import com.distraction.cm.game.Header;
import com.distraction.cm.game.Label;
import com.distraction.cm.util.ClickListener;
import com.distraction.cm.util.GridListener;
import com.distraction.cm.util.ImageButton;
import com.distraction.cm.util.Res;
import com.distraction.cm.util.Save;

public class PlayState extends State {
	
	private final int DRAG_DIST = 50;
	
	private Header header;
	private Grid grid;
	
	private float startx;
	private float starty;
	
	private int level;
	
	private Label targetLabel;
	private Label bestLabel;
	private Label movesLabel;
	
	private Array<FinishStar> finishStars;
	private ImageButton next;
	
	public PlayState(final GSM gsm, int level) {
		
		super(gsm);
		this.level = level;
		
		createGrid();
		
		header = new Header();
		header.setTitle("Level " + level);
		header.setStars(Save.getNumStars(level - 1, Res.data[level - 1].getMinMoves()));
		header.setBackClickListener(new ClickListener() {
			@Override
			public void onClick() {
				LevelSelectState nextState = new LevelSelectState(gsm, PlayState.this.level - 1);
				CheckeredTransitionState state = new CheckeredTransitionState(gsm, PlayState.this, nextState);
				gsm.set(state);
			}
		});
		header.setRefreshClickListener(new ClickListener() {
			@Override
			public void onClick() {
				createGrid();
				movesLabel.setSubtext("0");
				finishStars.clear();
				next = null;
			}
		});
		
		Gdx.input.setInputProcessor(this);
		
		targetLabel = new Label("Target", 90, CM.HEIGHT - 150);
		targetLabel.setSubtext(Res.data[level - 1].getMinMoves() + "");
		bestLabel = new Label("Best", CM.WIDTH / 2, CM.HEIGHT - 150);
		int best = Save.getNumMoves(level - 1);
		bestLabel.setSubtext(best == 0 ? "-" : best + "");
		movesLabel = new Label("Moves", CM.WIDTH - 90, CM.HEIGHT - 150);
		movesLabel.setSubtext("0");
		
		finishStars = new Array<FinishStar>();
		
	}
	
	private void createGrid() {
		int[][] g = Res.data[level - 1].getGrid();
		grid = new Grid(g);
		grid.setListener(new GridListener() {
			@Override
			public void onFinished() {
				Save.set(level - 1, grid.getNumMoves());
				Save.save();
				bestLabel.setSubtext(Save.getNumMoves(level - 1) + "");
				int newStars = Save.getNumStars(level - 1, grid.getNumMoves(), Res.data[level - 1].getMinMoves());
				for(int i = 0; i < newStars; i++) {
					FinishStar star = new FinishStar(CM.WIDTH / 2 - (newStars * FinishStar.SIZE) / 2 + FinishStar.SIZE * (i + 0.5f), 80, 0.2f * i);
					finishStars.add(star);
				}
				header.setStars(Save.getNumStars(level - 1, Res.data[level - 1].getMinMoves()));
				next = new ImageButton(Res.getAtlas().findRegion("next"), CM.WIDTH - 55, 80);
			}
		});
	}
	
	@Override
	public void update(float dt) {
		grid.update(dt);
		for(int i = 0; i < finishStars.size; i++) {
			finishStars.get(i).update(dt);
		}
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		grid.render(sb);
		targetLabel.render(sb);
		bestLabel.render(sb);
		movesLabel.render(sb);
		header.render(sb);
		if(next != null) {
			next.render(sb);
		}
		
		for(int i = 0; i < finishStars.size; i++) {
			finishStars.get(i).render(sb);
		}
		
		sb.end();
		
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		unproject(m, cam);
		grid.click(m.x, m.y);
		startx = m.x;
		starty = m.y;
		header.click(m.x, m.y);
		if(next != null && next.contains(m.x, m.y)) {
			PlayState newState = new PlayState(gsm, level + 1);
			CheckeredTransitionState state = new CheckeredTransitionState(gsm, this, newState);
			gsm.set(state);
		}
		return true;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int p) {
		unproject(m, cam);
		if(m.x > startx + DRAG_DIST) {
			if(grid.move(1, 0)) {
				movesLabel.setSubtext(grid.getNumMoves() + "");
			}
		}
		else if(m.x < startx - DRAG_DIST) {
			if(grid.move(-1, 0)) {
				movesLabel.setSubtext(grid.getNumMoves() + "");
			}
		}
		else if(m.y > starty + DRAG_DIST) {
			if(grid.move(0, 1)) {
				movesLabel.setSubtext(grid.getNumMoves() + "");
			}
		}
		else if(m.y < starty - DRAG_DIST) {
			if(grid.move(0, -1)) {
				movesLabel.setSubtext(grid.getNumMoves() + "");
			}
		}
		return true;
	}
	
}
