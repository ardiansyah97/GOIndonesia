package com.mobcom.goindonesia.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mobcom.goindonesia.GOIndonesia;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1024;
		config.height = 512;
		config.vSyncEnabled = true;
		new LwjglApplication(new GOIndonesia(), config);
	}
}
