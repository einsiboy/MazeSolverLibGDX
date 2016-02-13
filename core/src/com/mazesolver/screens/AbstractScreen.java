package com.mazesolver.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mazesolver.util.Constants;

public abstract class AbstractScreen implements Screen {
	
	protected Game game;
	protected OrthographicCamera camera;
	
	public AbstractScreen(Game game){
		this.game = game;
		this.camera = new OrthographicCamera();
		
		initCamera();
	}
	
	protected void initCamera(){
		this.camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		this.camera.position.set(Constants.WORLD_WIDTH/2.0f, Constants.WORLD_HEIGHT/2.0f, 0f); //center the camera
		
		this.camera.update();
	}
	
	protected void switchScreen(AbstractScreen screen){
		//just a convenience method which calls dispose in addition to setScreen()
		game.setScreen(screen);
		dispose();
	}

	public abstract void render(float deltaTime);

	public abstract void resize(int width, int height);

	public abstract void show();
	
	public abstract void hide();
	
	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

}
