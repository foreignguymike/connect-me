package com.distraction.cm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.distraction.cm.CM;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = CM.TITLE;
		config.height = (int) (600);
		config.width = (int) (config.height * (450f / 800));
		new LwjglApplication(new CM(), config);
	}
}
