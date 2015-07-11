package com.distraction.cm.state;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Manages States in a Stack structure.
 */
public class GSM {
	
	private LinkedList<State> states;
	
	public GSM() {
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
	
}
