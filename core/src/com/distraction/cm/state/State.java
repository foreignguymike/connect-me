package com.distraction.cm.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.distraction.cm.CM;

/**
 * Base State class. Holds reference to:
 * - GSM
 * - main camera
 * - mouse vector
 */
public abstract class State {
	
	protected GSM gsm;
	protected OrthographicCamera cam;
	protected Vector3 m;
	
	protected State(GSM gsm) {
		this.gsm = gsm;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, CM.WIDTH, CM.HEIGHT);
		m = new Vector3();
	}
	
	public abstract void update(float dt);
	public abstract void render(SpriteBatch sb);
	
}
