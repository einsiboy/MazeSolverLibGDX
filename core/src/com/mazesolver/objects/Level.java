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
		
		tiles[0][0] = new Tile(x, y, tmpSize, tmpSize, TileType.STRAIGHT, 0, 0);
		x += tmpSize;
		tiles[0][1] = new Tile(x, y, tmpSize, tmpSize, TileType.TURN, 0, 1);
		x -= tmpSize;
		y += tmpSize;
		tiles[0][2] = new Tile(x, y, tmpSize, tmpSize, TileType.ERROR, 0, 2);
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
				this.tiles[i][j] = new Tile(xPos, yPos, tileSize, tileSize, tileTypeArr[i][j], i, j);
				
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
				if (tiles[i][j].getType() != TileType.START)
					tiles[i][j].setConnection(false);
				tiles[i][j].update(dt, input);
				
			}
		}
		
		checkConnections(startTile);
	}

	public void randomize() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].setOrientation((int) Math.floor(Math.random()*4));
			}
		}
	}
	
	public boolean isSolved() {
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				if (tiles[i][j].getType() == TileType.END && !tiles[i][j].getConnection())
					return false;
			}
		}
		return true;
	}
	
	// Returns the tile adjacent to tile1 in the direction dir
	// where 0 is up, 1 is right etc. E.g. with dir=0 getTile
	// returns the tile above tile1, if it exists, else null.
	private Tile getTile(Tile tile1, int dir) {
		int newrow = tile1.getRow();
		int newcol = tile1.getCol();
		switch(dir) {
			case 0:
				newrow--;
				break;
			case 1:
				newcol++;
				break;
			case 2:
				newrow++;
				break;
			case 3:
				newcol--;
				break;
		}
		if (newrow >= 0 && newrow < tiles.length && newcol >= 0 && newcol < tiles[0].length)
			return tiles[newrow][newcol];
		else
			return null;
	}
	
	// Returns true if tile1 is linked to the adjacent tile indicated by the 
	// direction dir, else false. Two tiles are linked if they have touching exits.
	private boolean isLinked(Tile tile1, int dir) {
		Tile tile2 = getTile(tile1, dir);
		if (tile2 != null) {
			if (tile2.hasExit((dir+2)%4)) {
				return true;
			}
		}
		return false;
	}
	
	private void checkConnections(Tile tile) {
		if (tile.getConnection() && tile.getType() != TileType.START)
			return;
		tile.setConnection(true);
		int[] exits = tile.getExits();
		for (int exit : exits) {
			int tmp = (exit+tile.getOrientation())%4;
				if (isLinked(tile, tmp))
					checkConnections(getTile(tile,tmp));
		}
	}
}
