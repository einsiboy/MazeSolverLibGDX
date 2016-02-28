package com.mazesolver;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mazesolver.objects.Level;
import com.mazesolver.objects.LevelManager;
import com.mazesolver.screens.AbstractScreen;
import com.mazesolver.screens.BetweenLevelsScreen;
import com.mazesolver.screens.MainMenuScreen;
import com.mazesolver.screens.PlayScreen;
import com.mazesolver.screens.SubmitHighscoreScreen;
import com.mazesolver.util.Assets;
import com.mazesolver.util.Constants;

public class MazeSolverMain extends Game {
	//SpriteBatch batch;
	//Texture img;
	
	private LevelManager levelManager;
	
	@Override
	public void create () {
		if(Constants.DEBUG){
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		}
		Gdx.app.log("tag", "log message");
		
		//batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		
		levelManager = new LevelManager();
		
		//setToScreen(new PlayScreen(this));
		//Level nextLevel = levelManager.getNextLevel();
		
		setToScreen(new MainMenuScreen(this));
		
		//setToScreen(new PlayScreen(this, levelManager.getNextLevel()));
		//setToScreen(new SubmitHighscoreScreen(this));
		
		//setToScreen(new BetweenLevelsScreen(this));
		Gdx.app.debug("temp", "in main");
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
	
	@Override
	public void dispose(){
		Assets.instance.dispose();
	}
	
	public void setToScreen(AbstractScreen screen){
		setScreen(screen);
	}
	
	public void nextLevel(){
		Level nextLevel = levelManager.getNextLevel();
		setToScreen(new PlayScreen(this, nextLevel));
	}
	
	public void handleLevelComplete() {
		if(levelManager.allLevelsCompleted()){
			setToScreen(new SubmitHighscoreScreen(this));
		} else {
			setToScreen(new BetweenLevelsScreen(this));
		}
	}
}
