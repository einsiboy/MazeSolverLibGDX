package com.mazesolver.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mazesolver.MazeSolverMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//config.width = 1080;
		//config.height = 720;
		
		config.width = 480;
		config.height = 720;
		
		//config.width = 1920;
		//config.height = 1080;
		
		//config.width = 480;
		//config.height = 320;
		
		
		new LwjglApplication(new MazeSolverMain(), config);
	}
}
