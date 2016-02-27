package com.mazesolver.objects;

import java.util.Arrays;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.mazesolver.objects.Tile.TileType;

public final class LevelArray {
	private LevelArray(){} //Class should not be instantiated
	
	@SuppressWarnings("serial")
	private static HashMap<Integer, TileType> levelDecoder = new HashMap<Integer, TileType>() {{ 
		put(0, TileType.EMPTY);
		put(1, TileType.STRAIGHT);
		put(2, TileType.TURN);
		put(3, TileType.T_SECTION);
		put(4, TileType.CROSS);
		put(5, TileType.START);
		put(6,TileType.END);
		}};
		
	private static int[][][] levels = {
	    {	// level for testing quick completion
	        {0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
	        {0, 1, 1, 1, 2, 4, 2, 1, 1, 0},
	        {0, 1, 1, 1, 6, 3, 1, 1, 1, 0}, //3
	        {0, 1, 1, 1, 0, 3, 1, 1, 1, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //5
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //7
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //9
	        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	    },
		
	    {
	        {0, 0, 0, 0, 0, 5, 0, 0, 0, 0},
	        {0, 1, 1, 1, 2, 3, 2, 1, 1, 0},
	        {0, 1, 1, 1, 1, 3, 1, 1, 1, 0}, //3
	        {0, 1, 1, 1, 1, 3, 1, 1, 1, 0},
	        {0, 1, 1, 1, 1, 3, 1, 1, 1, 0}, //5
	        {0, 1, 1, 1, 1, 3, 1, 1, 1, 0},
	        {0, 1, 1, 1, 1, 3, 1, 1, 1, 0}, //7
	        {0, 1, 1, 1, 1, 3, 1, 1, 1, 0},
	        {0, 1, 1, 2, 2, 3, 2, 1, 1, 0}, //9
	        {0, 0, 0, 6, 0, 3, 0, 0, 0, 0}
	    },
		
		/*{ // LEVEL 1 -- Bjarna borð
	          {2, 1, 1, 1, 2, 5, 2, 6, 6, 0},
	          {1, 2, 1, 2, 2, 3, 2, 2, 3, 2},
	          {1, 1, 0, 2, 2, 2, 1, 3, 2, 1}, //3
	          {3, 3, 2, 0, 3, 3, 2, 6, 2, 6},
	          {3, 2, 2, 2, 6, 1, 6, 0, 3, 2}, //5
	          {1, 1, 0, 1, 2, 3, 1, 6, 1, 1},
	          {1, 1, 2, 2, 6, 0, 6, 1, 2, 1}, //7
	          {1, 6, 2, 2, 2, 3, 6, 0, 6, 1},
	          {2, 2, 0, 2, 2, 1, 0, 0, 2, 3}, //9
	          {0, 2, 1, 6, 0, 2, 1, 1, 1, 2}
		}*/
		
	};
	
	public static TileType[][] getLevel2dArray(int level){
		return decodeLevel(levels[level]);
	}
	
	private static TileType[][] decodeLevel(int[][] level){
		TileType[][] retLevel = new TileType[level.length][level[0].length];
		
		for(int i = 0; i < level.length; i++){
			for(int j = 0; j < level[0].length; j++){
				retLevel[i][j] = levelDecoder.get(level[i][j]);
			}
		}
		
		Gdx.app.debug("LEVELARRAY", Arrays.deepToString(retLevel));
		
		return retLevel;
	}
	
	public static int getLevelsLength(){
		return levels.length;
	}
	
	
	
}
