package com.mazesolver.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mazesolver.objects.Tile.TileType;
import com.mazesolver.util.Constants;

public class Level {

	Tile[][] tiles;
	
	public Level(){
		tiles = new Tile[1][3];
		
		float x = Constants.WORLD_WIDTH/2;
		float y = Constants.WORLD_WIDTH/2;
		float tmpSize = 10.0f;
		
		tiles[0][0] = new Tile(x, y, tmpSize, tmpSize, TileType.STRAIGHT);
		x += tmpSize;
		tiles[0][1] = new Tile(x, y, tmpSize, tmpSize, TileType.TURN);
		x -= tmpSize;
		y += tmpSize;
		tiles[0][2] = new Tile(x, y, tmpSize, tmpSize, TileType.ERROR);
	}
	
	private void renderDebug(ShapeRenderer renderer){

	}
	
	public void render(ShapeRenderer renderer){
		
		if(Constants.DEBUG){
			renderDebug(renderer);
		}
		
		//render all tiles
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				tiles[i][j].render(renderer);
			}
		}
	}
	
	public void update(float dt){
		//update all tiles
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				tiles[i][j].update(dt);
			}
		}
	}
}
