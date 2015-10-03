package com.distraction.cm.android;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.distraction.cm.CM;
import com.distraction.cm.util.PlatformAdapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class AndroidLauncher extends AndroidApplication implements PlatformAdapter {
	
	private Handler handler;
	
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new CM(this), config);
		
		handler = new Handler(Looper.getMainLooper());
		
	}
	
}
