package com.distraction.cm.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class Screenshot {
	private static boolean debug = false;
	public static void save() {
		if(!debug) return;
		try {
			FileHandle file = new FileHandle("screenshot.png");
			Pixmap pix = ScreenUtils.getFrameBufferPixmap(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			PixmapIO.writePNG(file, pix);
			pix.dispose();
		}
		catch(Exception e) {}
	}
}
