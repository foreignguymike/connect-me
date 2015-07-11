package com.distraction.cm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.distraction.cm.CM;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = CM.TITLE;
		config.width = CM.WIDTH / 1;
		config.height = CM.HEIGHT / 1;
		new LwjglApplication(new CM(), config);
	}
}
