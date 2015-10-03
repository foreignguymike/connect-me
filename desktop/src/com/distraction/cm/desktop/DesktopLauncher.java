package com.distraction.cm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.distraction.cm.CM;
import com.distraction.cm.util.PlatformAdapter;

public class DesktopLauncher implements PlatformAdapter {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = CM.TITLE;
		config.height = (int) (600);
		config.width = (int) (config.height * (450f / 800));
		new LwjglApplication(new CM(new DesktopLauncher()), config);
	}
	
}
