package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {
	
	public MenuState(GSM gsm) {
		super(gsm);
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public void update(float dt) {
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	@Override
	public boolean touchDown(int x, int y, int p, int b) {
		unproject(m, cam);
		LevelSelectState nextState = new LevelSelectState(gsm);
		CheckeredTransitionState state = new CheckeredTransitionState(gsm, this, nextState);
		gsm.set(state);
		return true;
	}
	
}
