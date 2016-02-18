package com.mazesolver.objects;

import com.mazesolver.objects.Tile.TileType;

public class LevelManager {
	
	Level currentLevel;
	int currentLevelIdx;

	public LevelManager(){
		currentLevelIdx = 1;
		//currentLevel = 
	}

	public Level getNextLevel() {
		TileType[][] levelInfo = LevelArray.getLevel2dArray(currentLevelIdx);
		currentLevel = new Level(levelInfo);
		return currentLevel;
	}
}
