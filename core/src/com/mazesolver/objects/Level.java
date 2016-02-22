package com.mazesolver.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mazesolver.objects.Tile.TileType;
import com.mazesolver.util.Constants;
import com.mazesolver.util.Input;

public class Level {
	

	Tile[][] tiles;
	Tile startTile;
	
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
	
	public Level(TileType tileTypeArr[][]){
		initTilesFromTileType(tileTypeArr);
	}
	
	private void initTilesFromTileType(TileType tileTypeArr[][]){
		float tileSize = Constants.WORLD_WIDTH / tileTypeArr.length;
		this.tiles = new Tile[tileTypeArr.length][tileTypeArr[0].length];
		for(int i = 0; i < tileTypeArr.length; i++){
			for(int j = 0; j < tileTypeArr[i].length; j++){
				float xPos = j*tileSize;
				//float yPos = i*tileSize;
				float yPos = tileTypeArr.length* tileSize - (i+1)*tileSize;
				this.tiles[i][j] = new Tile(xPos, yPos, tileSize, tileSize, tileTypeArr[i][j]);
				
				if(this.tiles[i][j].getType() == TileType.END){
					//push finish point
				} else if(this.tiles[i][j].getType() == TileType.START){
					this.startTile = this.tiles[i][j];
				}
			}
		}
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
	
	public void update(float dt, Input input){
		
		//update all tiles
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				tiles[i][j].setConnection(false);
				tiles[i][j].update(dt, input);
				
			}
		}
		
		checkConnections(startTile);
	}

	public boolean isSolved() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getType() == TileType.END)
					if (!tiles[i][j].getConnection())
						return false;
			}
		}
		return true;
	}
		
	private boolean equals(Tile tile1, Tile tile2) {
		return tile1.row == tile2.row && tile1.col == tile2.col;
	}
		
	private boolean isLinked(Tile tile1, Tile tile2) {
		int[] exits = tile1.getExits();
		for (int exit : exits) {
			if (equals(getTile(tile1, (exit+tile1.getOrientation())%4), tile2))
				return true;
		}
		return false;
	}
	
	private void checkConnections(Tile tile) {
		if (tile.getConnection())
			return;
		tile.setConnection(true);
		int[] exits = tile.getExits();
		for (int exit : exits) {
			Tile tmp = getTile(tile, (exit+tile.getOrientation())%4);
			if(isLinked(tile, tmp))
				checkConnections(tmp);
		}
	}
}
