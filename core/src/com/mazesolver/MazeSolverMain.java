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
	
	private LevelManager levelManager;
	
	@Override
	public void create () {
		// messages logged with Gdx.app.debug(tag, message) will only be output
		// if log level is set to LOG_DEBUG
		if(Constants.DEBUG){
			Gdx.app.setLogLevel(Application.LOG_DEBUG);
		}
		
		levelManager = new LevelManager();
		
		setScreen(new MainMenuScreen(this));
		//setScreen(new PlayScreen(this, levelManager.getNextLevel()));
		//setScreen(new SubmitHighscoreScreen(this));
		//setScreen(new BetweenLevelsScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose(){
		Assets.instance.dispose();
	}
	
	public void nextLevel(){
		Level nextLevel = levelManager.getNextLevel();
		setScreen(new PlayScreen(this, nextLevel));
	}
	
	public void handleLevelComplete() {
		if(levelManager.allLevelsCompleted()){
			setScreen(new SubmitHighscoreScreen(this));
		} else {
			setScreen(new BetweenLevelsScreen(this));
		}
	}
}
