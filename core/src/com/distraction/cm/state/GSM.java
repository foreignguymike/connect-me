package com.distraction.cm.state;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.util.PlatformAdapter;

/**
 * Manages States in a Stack structure.
 */
public class GSM {
	
	private PlatformAdapter platformAdapter;
	private LinkedList<State> states;
	
	public GSM(PlatformAdapter platformAdapter) {
		this.platformAdapter = platformAdapter;
		states = new LinkedList<State>();
	}
	
	public void update(float dt) {
		states.peek().update(dt);
	}
	
	public void render(SpriteBatch sb) {
		states.peek().render(sb);
	}
	
	public void pop() {
		states.pop();
	}
	
	public void push(State s) {
		states.push(s);
	}
	
	public void set(State s) {
		states.pop();
		states.push(s);
	}
	
	public PlatformAdapter getPlatformAdapter() {
		return platformAdapter;
	}
	
}
