package com.mazesolver.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mazesolver.MazeSolverMain;
import com.mazesolver.util.Constants;
import com.mazesolver.util.Input;

public abstract class AbstractScreen implements Screen {
	
	//protected Game game;
	protected MazeSolverMain game;
	protected OrthographicCamera camera;
	protected Input input;
	
	public AbstractScreen(MazeSolverMain game){
		this.game = game;
		this.camera = new OrthographicCamera();
		initCamera();
		
		input = new Input(this);
		Gdx.input.setInputProcessor(input);
	}
	
	protected void initCamera(){
		this.camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		this.camera.position.set(Constants.WORLD_WIDTH/2.0f, Constants.WORLD_HEIGHT/2.0f, 0f); //center the camera
		
		this.camera.update();
	}
	
	
	public OrthographicCamera getCamera(){
		return this.camera;
	}

	public abstract void render(float deltaTime);

	public abstract void resize(int width, int height);

	public abstract void show();
	
	public abstract void hide();
	
	public abstract void pause();

	public abstract void resume();

	public abstract void dispose();

}
