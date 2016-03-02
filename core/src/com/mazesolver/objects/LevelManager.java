package com.mazesolver.objects;

import com.badlogic.gdx.Gdx;
import com.mazesolver.objects.Tile.TileType;

public class LevelManager {
	
	Level currentLevel;
	int currentLevelIdx;

	public LevelManager(){
		currentLevelIdx = -1;
	}

	public Level getNextLevel() {
		Gdx.app.debug("level managar", "getting next level. Current level: " + currentLevelIdx);
		currentLevelIdx++;
		TileType[][] levelInfo = LevelArray.getLevel2dArray(currentLevelIdx);
		currentLevel = new Level(levelInfo);
		return currentLevel;
	}

	public boolean allLevelsCompleted() {
		int allLevelsLenght = LevelArray.getLevelsLength();
		Gdx.app.debug("level manager all levels length: ", ""+ allLevelsLenght);
		Gdx.app.debug("level manager currentlvl idx", ""+currentLevelIdx);
		return (allLevelsLenght - 1) == currentLevelIdx;
	}
}
