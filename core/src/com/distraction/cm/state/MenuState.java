package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.CM;
import com.distraction.cm.util.Image;
import com.distraction.cm.util.Res;
import com.distraction.cm.util.TextButton;

public class MenuState extends State {
	
	private TextButton play;
	private TextButton tutorial;
	private TextButton online;
	
	private BitmapFont font;
	private String distraction;
	
	private Image title;
	
	public MenuState(GSM gsm) {
		
		super(gsm);
		Gdx.input.setInputProcessor(this);
		
		title = new Image(Res.getAtlas().findRegion("title"), CM.WIDTH / 2, CM.HEIGHT / 2 + 150);
		
		play = new TextButton("Play", CM.WIDTH / 2, CM.HEIGHT / 2 - 90);
		tutorial = new TextButton("Tutorial", CM.WIDTH / 2, CM.HEIGHT / 2 - 150);
		online = new TextButton("Online", CM.WIDTH / 2, CM.HEIGHT / 2 - 210);
		
		font = Res.getFont("RobotoDistraction");
		distraction = "distraction";
		
	}
	
	@Override
	public void update(float dt) {
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.setColor(Res.PRIMARY_COLOR);
		title.render(sb);
		play.render(sb);
		tutorial.render(sb);
		online.render(sb);
		font.setColor(0, 0, 0, 1);
		font.draw(sb, distraction, CM.WIDTH / 2 - 30, 40);
		sb.end();
		
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		unproject(m, cam);
		if(play.contains(m.x, m.y)) {
			LevelSelectState nextState = new LevelSelectState(gsm);
			CheckeredTransitionState state = new CheckeredTransitionState(gsm, this, nextState);
			gsm.set(state);
		}
		else if(tutorial.contains(m.x, m.y)) {
			TutorialState nextState = new TutorialState(gsm);
			CheckeredTransitionState state = new CheckeredTransitionState(gsm, this, nextState);
			gsm.set(state);
		}
		return true;
	}
	
}
