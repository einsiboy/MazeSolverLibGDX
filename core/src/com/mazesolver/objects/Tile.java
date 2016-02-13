package com.mazesolver.objects;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;

public class Tile {
	enum TileType {
		//Note logic in Level class does not yet support cross type
		STRAIGHT, TURN, T_SECTION, CROSS, START, END;
	}
	
	public class Exits{
		public Exits instance = new Exits();
		public Hashtable<TileType, int[]> exits = new Hashtable();

		private Exits(){
			exits.put(TileType.TURN, new int[]{0,1} );
			exits.put(TileType.STRAIGHT, new int[]{0,2});
			exits.put(TileType.T_SECTION, new int[]{0,1,3});
			exits.put(TileType.CROSS, new int[]{0,1,2,3});
			exits.put(TileType.START, new int[]{0});
			exits.put(TileType.END, new int[]{0});
		}
	}
	
	private float x, y, width, height;
	private float cx, cy; //center x an y
	
	private TileType type;
	private int orientation;
	
	private boolean connected = false;
	
	//private int[] exits;

	public Tile(float x, float y, float width, float height, TileType type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
		this.cx = this.x + this.width/2.0f;
		this.cy = this.y + this.height/2.0f;
		
		//drawing stuff
		/*this.fillStyle = "#0000ff";
		this.pathColor = "#ff0000";
		this.borderColor = "#00ff00";
		this.connectedPathColor = "#F0C51A";
		this.borderSize = 1.5;*/
		
		
		initExits();
		
		if(this.type == TileType.START){
			this.connected = true;
		}
	}
	
	private void initExits(){
	}
	
	/**direction; 1 is clockwise and -1 is counter clockwise*/
	private void rotate(int direction){
		
	}
	
	private void drawStraightTile(ShapeRenderer renderer){
		 
		renderer.begin(ShapeType.Filled);
		renderer.setColor(0, 1, 0, 1);
		renderer.rect(x, y, width, height);
		renderer.end();
	}
	
	float speed = 60.0f;
	float angle = 0f;
	
	private void drawTurnTile(ShapeRenderer renderer){
		
		angle += Gdx.graphics.getDeltaTime()*speed;
		
		//set transform matrix to the center of the tile
		renderer.identity();
		renderer.translate(cx, cy, 0);
		renderer.rotate(0, 0, 1, angle);
		renderer.translate(-cx, -cy, 0);

		
		renderer.begin(ShapeType.Line);

		renderer.setColor(1, 0, 0, 1);

		
		renderer.rect(x, y, width, height);

		renderer.end();
		
		//restore transform matrix
		renderer.identity();
		
		

	}
	
	private void drawErrorTile(ShapeRenderer renderer){
		
	}
	
	public void update(float dt){
		
	}
	
	//might switch to SpriteBatch if / when we make actual graphics for the game
	public void render(ShapeRenderer renderer){
		
		switch(this.type){
		case STRAIGHT:
			drawStraightTile(renderer);
			break;
		case TURN:
			drawTurnTile(renderer);
			break;
		default:
			drawErrorTile(renderer);
		}
	}
	
	
	
}
