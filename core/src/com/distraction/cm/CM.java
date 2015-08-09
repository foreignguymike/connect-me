package com.distraction.cm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.distraction.cm.state.GSM;
import com.distraction.cm.state.MenuState;
import com.distraction.cm.util.Res;
import com.distraction.cm.util.Save;

public class CM extends ApplicationAdapter {
	
	public static final String TITLE = "Connect Me!";
	public static int WIDTH = 450;
	public static int HEIGHT = 800;
	
	private SpriteBatch sb;
	private GSM gsm;
	
	@Override
	public void resume() {
		Res.init();
		Res.loadAtlas("pack/pack.pack");
		super.resume();
	}
	
	@Override
	public void create () {
		
		Gdx.gl.glClearColor(1f, 0.99f, 0.98f, 1);
		
		Save.load();
		
		Res.init();
		Res.loadAtlas("pack/pack.pack");
		
		sb = new SpriteBatch();
		
		gsm = new GSM();
		gsm.push(new MenuState(gsm));
		
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
}
