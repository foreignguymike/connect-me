package com.distraction.cm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.state.GSM;
import com.distraction.cm.state.PlayState;
import com.distraction.cm.util.Content;

public class CM extends ApplicationAdapter {
	
	public static final String TITLE = "Connect Me!";
	public static int WIDTH = 480;
	public static int HEIGHT = 800;
	
	private SpriteBatch sb;
	private GSM gsm;
	
	@Override
	public void create () {
		
		Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
		
		Content.getInstance().loadAtlas("pack/pack.pack");
		
		sb = new SpriteBatch();
		
		gsm = new GSM();
		gsm.push(new PlayState(gsm));
		
		
		
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
}
