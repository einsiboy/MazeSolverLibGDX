package com.mazesolver.objects;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Tile {
	enum TileType {
		//Note logic in Level class does not yet support cross type
		STRAIGHT, TURN, T_SECTION, CROSS, START, END, EMPTY, ERROR; //TODO: just remove the error type
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
			exits.put(TileType.EMPTY, new int[]{});
		}
	}
	
	private final static String TAG = Tile.class.getName();
	
	private float x, y, width, height;
	private float cx, cy; //center x an y
	
	private TileType type;
	private int orientation;
	float angle = 0f;
	
	private boolean connected = false;
	
	private Color fillColor, borderColor, pathColor, connectedPathColor;
	private float borderSize = 1.5f;

	public Tile(float x, float y, float width, float height, TileType type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		
		this.cx = this.x + this.width/2.0f;
		this.cy = this.y + this.height/2.0f;

		fillColor = new Color(0, 0, 1, 1);
		pathColor = new Color(1, 0, 0, 1);
		borderColor = new Color(0, 1, 0, 1);
		connectedPathColor = Color.valueOf("#F0C51A2");
		
		initExits();
		
		if(this.type == TileType.START){
			this.connected = true;
		}
	}
	
	private void initExits(){
	}
	
	/**direction; 1 is clockwise and -1 is counter clockwise*/
	private void rotate(int direction){
		Gdx.app.debug(TAG, "rotating");
		if (direction == 1 || direction == -1){
			this.orientation = (this.orientation + direction + 4) % 4;
		} else{
			throw new RuntimeException("Tile invalid direction");
		}
		Gdx.app.debug(TAG, "This.orientation: " + this.orientation);
		Gdx.app.debug(TAG, "angle: " + angle);
		
	}
	
	
	private void drawStraightTile(ShapeRenderer renderer){
		//Gdx.gl.glLineWidth(5);
		
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		
		//renderer.set(ShapeType.Line);
		renderer.end();
		
		//
		renderer.begin(ShapeType.Line);
		
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y, cx, y+height);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);
		
		renderer.end();
	}
	
	private Color getPathColor(){
		return this.connected ? this.connectedPathColor : this.pathColor;
	}
	
	
	
	/** helper method to set centre the ShapeRenderer on the current tile and 
	 * rotate relative to the current tile rotation
	 */
	private void setupTransformationMatrix(ShapeRenderer renderer){
		angle = (this.orientation % 4) * 90;
		
		renderer.identity();
		renderer.translate(cx, cy, 0);
		renderer.rotate(0, 0, 1, angle); //TODO delete angle and base on current orientation
		renderer.translate(-cx, -cy, 0);
	}
	
	private void drawTurnTile(ShapeRenderer renderer){
		
		renderer.begin(ShapeType.Line);

		renderer.setColor(1, 0, 0, 1);
		renderer.rect(x, y, width, height);

		renderer.end();
		
		renderer.begin(ShapeType.Line);
		
		renderer.setColor(this.getPathColor());
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);
		
		renderer.end();
	}
	
	private void drawErrorTile(ShapeRenderer renderer){
		
		angle += Gdx.graphics.getDeltaTime()*60;
		float tmp = width * 0.2f;
		renderer.begin(ShapeType.Filled);

		renderer.setColor(1, 0, 0, 1);
		renderer.rect(x, y, width, height);
		
		renderer.setColor(1, 0.2f, 0.2f, 1);
		renderer.rect(x+tmp, y+tmp, width-tmp*2, height-tmp*2);

		renderer.end();
		
		renderer.begin(ShapeType.Line);
		
		renderer.setColor(this.getPathColor());
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);
		
		renderer.end();
		
	}
	

	private void drawTSectionTile(ShapeRenderer renderer) {
		// TODO Auto-generated method stub
		
	}

	private void drawStartTile(ShapeRenderer renderer) {
		// TODO Auto-generated method stub
		
	}

	private void drawEndTile(ShapeRenderer renderer) {
		// TODO Auto-generated method stub
		
	}

	private void drawEmptyTile(ShapeRenderer renderer) {
		// TODO Auto-generated method stub
		
	}

	private void drawCrossTile(ShapeRenderer renderer) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void update(float dt){
		if(Gdx.input.justTouched()){
			this.rotate(1);
		}
	}
	
	//might switch to SpriteBatch if / when we make actual graphics for the game
	public void render(ShapeRenderer renderer){
		
		setupTransformationMatrix(renderer);

		switch(this.type){
		case STRAIGHT:
			drawStraightTile(renderer);
			break;
		case TURN:
			drawTurnTile(renderer);
			break;
		case CROSS:
			drawCrossTile(renderer);
			break;
		case EMPTY:
			drawEmptyTile(renderer);
			break;
		case END:
			drawEndTile(renderer);
			break;
		case START:
			drawStartTile(renderer);
			break;
		case T_SECTION:
			drawTSectionTile(renderer);
			break;
		default:
			drawErrorTile(renderer);
		}
		//restore transformation matrix
		renderer.identity();

	}

	
}
