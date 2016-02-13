package com.mazesolver;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mazesolver.screens.AbstractScreen;
import com.mazesolver.screens.MainMenuScreen;
import com.mazesolver.util.Constants;

public class MazeSolverMain extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		
		if(Constants.DEBUG){
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		}
		Gdx.app.log("tag", "log message");
		
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		setToScreen(new MainMenuScreen(this));
		Gdx.app.debug("temp", "in main");
		//setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
		/*Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	public void setToScreen(AbstractScreen screen){
		setScreen(screen);
	}
}
