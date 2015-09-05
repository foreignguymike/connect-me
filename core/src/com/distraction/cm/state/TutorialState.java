package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.game.Grid;
import com.distraction.cm.game.Header;
import com.distraction.cm.util.CenteredText;
import com.distraction.cm.util.ClickListener;
import com.distraction.cm.util.Res;

public class TutorialState extends State {
	
	private final int DRAG_DIST = 50;
	private float startx;
	private float starty;
	
	private Header header;
	
	private BitmapFont font;
	private CenteredText[] strings;
	
	private Grid grid;
	
	public TutorialState(final GSM gsm) {
		
		super(gsm);
		
		header = new Header();
		header.setTitle("Tutorial");
		header.setBackClickListener(new ClickListener() {
			@Override
			public void onClick() {
				MenuState nextState = new MenuState(gsm);
				CheckeredTransitionState state = new CheckeredTransitionState(gsm, TutorialState.this, nextState);
				gsm.set(state);
			}
		});
		header.setRefreshClickListener(new ClickListener() {
			@Override
			public void onClick() {
				createGrid();
			}
		});
		
		font = Res.getFont("RobotoText");
		
		int h = 720;
		strings = new CenteredText[] {
				new CenteredText("Solve the grid by putting all same", font, h -= 30),
				new CenteredText("colored blocks adjacent to", font, h -= 30),
				new CenteredText("each other.", font, h -= 30),
				new CenteredText("Blocks can be swapped by", font, h -= 530),
				new CenteredText("dragging them.", font, h -= 30),
		};
		
		createGrid();
		
	}
	
	private void createGrid() {
		int[][] g = new int[][] {
				{0, 1, 0},
				{1, 2, 2},
				{2, 1, 0}
		};
		grid = new Grid(g);
	}
	
	public void update(float dt) {
		grid.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		header.render(sb);
		
		font.setColor(0, 0, 0, 0.87f);
		for(int i = 0; i < strings.length; i++) {
			strings[i].render(sb);
		}
		
		grid.render(sb);
		
		sb.end();
		
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		unproject(m, cam);
		header.click(m.x, m.y);
		grid.click(m.x, m.y);
		startx = m.x;
		starty = m.y;
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
