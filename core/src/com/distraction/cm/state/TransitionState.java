package com.distraction.cm.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TransitionState extends State {
	
	protected float timer;
	protected float totalTime;
	
	protected State prevState;
	protected State nextState;
	
	protected TransitionState(GSM gsm, State prevState, State nextState) {
		super(gsm);
		Gdx.input.setInputProcessor(null);
		this.prevState = prevState;
		this.nextState = nextState;
	}
	
	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}
	
	protected void finish() {
		gsm.set(nextState);
		Gdx.input.setInputProcessor(nextState);
	}
	
	@Override
	public void update(float dt) {
		timer += dt;
	}
	
	@Override
	public void render(SpriteBatch sb) {}
	
}
