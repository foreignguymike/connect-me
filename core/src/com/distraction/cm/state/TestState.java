package com.distraction.cm.state;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * placeholder state
 */
public class TestState extends State {
	
	public TestState(GSM gsm) {
		super(gsm);
	}
	
	public void update(float dt) {
		System.out.println("test state updating");
	}
	
	public void render(SpriteBatch sb) {
		System.out.println("test state rendering");
	}
	
}
