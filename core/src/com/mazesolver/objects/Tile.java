package com.mazesolver.objects;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mazesolver.util.Helpers;
import com.mazesolver.util.Input;

public class Tile {
	enum TileType {
		//Note logic in Level class does not yet support cross type
		STRAIGHT, TURN, T_SECTION, CROSS, START, END, EMPTY, ERROR; //TODO: just remove the error type
	}
	
	public static class Exits{
		//public static final Exits instance = new Exits();
		public static Hashtable<TileType, int[]> exits = new Hashtable<TileType, int[]>() 
		{{
			put(TileType.TURN, new int[]{0,1});
			put(TileType.STRAIGHT, new int[]{0,2});
			put(TileType.T_SECTION, new int[]{0,1,3});
			put(TileType.CROSS, new int[]{0,1,2,3});
			put(TileType.START, new int[]{0});
			put(TileType.END, new int[]{0});
			put(TileType.EMPTY, new int[]{});
		}};
	}
	
	private final static String TAG = Tile.class.getName();
	
	private float x, y, width, height;
	private float cx, cy; //center x an y
	public final int row, col;

	private TileType type;
	private int orientation;
	float angle = 0f;
	
	private boolean connected = false;
	
	private Color fillColor, borderColor, pathColor, connectedPathColor;
	private float borderWidth;
	private float pathWidth;

	private Vector3 pos; //keep a reference to one, to avoid GC
	private Rectangle collisionRect;
	
	int[] exits;

	public Tile(float x, float y, float width, float height, TileType type, int row, int col) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		this.row = row;
		this.col = col;

		this.cx = this.x + this.width/2.0f;
		this.cy = this.y + this.height/2.0f;
		
		this.pos = new Vector3();
		this.collisionRect = new Rectangle(x, y, width, height);


		fillColor = new Color(0, 0, 1, 1);
		pathColor = new Color(1, 0, 0, 1);
		borderColor = new Color(0, 1, 0, 1);
		connectedPathColor = Color.valueOf("#F0C51A2");
		this.borderWidth = this.width / 10.0f;
		this.pathWidth = this.width / 2.0f;
		
		initExits();
		
		if(this.type == TileType.START){
			this.connected = true;
		}
	}
	private void initExits(){
		this.exits = Exits.exits.get(this.type);
	}
	
	/**direction; 1 is clockwise and -1 is counter clockwise*/
	private void rotate(int direction){
		Gdx.app.debug(TAG, "rotating, this.x: " + this.x + ", this.y: " + this.y);
		if (direction == 1 || direction == -1){
			this.orientation = (this.orientation + direction + 4) % 4;
		} else{
			throw new RuntimeException("Tile invalid direction");
		}
		Gdx.app.debug(TAG, "This.orientation: " + this.orientation);
		Gdx.app.debug(TAG, "angle: " + angle);
		
	}
	
	
	private void drawStraightTile(ShapeRenderer renderer){
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 
		// draw the path
		Gdx.gl.glLineWidth(this.pathWidth);
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y, cx, y+height); //path line
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
		
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 
		
		// draw the path
		Gdx.gl.glLineWidth(this.pathWidth);
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y + height, cx, cy); // top to middle
		renderer.line(cx, cy, x + width, cy); // middle to right
		
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
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 
		
		// draw the path
		Gdx.gl.glLineWidth(this.pathWidth);
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y + height, cx, cy); // top to middle
		renderer.line(x, cy, x + width, cy); // left to right
		
		renderer.end();
	}

	private void drawStartTile(ShapeRenderer renderer) {
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 
		
		// draw the path
		Gdx.gl.glLineWidth(this.pathWidth);
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y + height, cx, cy); // top to middle
		
		renderer.end();
	}

	private void drawEndTile(ShapeRenderer renderer) {
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		
		renderer.setColor(this.getPathColor());
		renderer.circle(cx, cy, this.width/6.0f, 12);
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 
		
		// draw the path
		Gdx.gl.glLineWidth(this.pathWidth);
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y + height, cx, cy); // top to middle
		
		renderer.end();
	}

	private void drawEmptyTile(ShapeRenderer renderer) {
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		renderer.setColor(Helpers.colorLuminance(this.fillColor, -0.2f));
		float offset = this.width * 0.1f;
		renderer.rect(x + offset, y + offset, width - offset*2, height - offset*2);
		
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 	
		renderer.end();		
	}

	private void drawCrossTile(ShapeRenderer renderer) {
		// === Filled ===
		renderer.begin(ShapeType.Filled);
		renderer.setColor(this.fillColor);
		renderer.rect(x, y, width, height);
		renderer.end();
		
		// === Line ===
		renderer.begin(ShapeType.Line);
		// draw the border
		Gdx.gl.glLineWidth(this.borderWidth);
		renderer.setColor(this.borderColor);
		renderer.rect(x,y,width,height);//border
		renderer.flush(); 
		
		// draw the path
		Gdx.gl.glLineWidth(this.pathWidth);
		renderer.setColor(this.getPathColor());
		renderer.line(cx, y + height, x, cy); // top to bottom
		renderer.line(x, cy, x + width, cy); // left to right
		
		renderer.end();
	}
	
	private boolean clickedOn(Input input){
		if(Gdx.input.justTouched()){
			pos = input.getWorldTouchPos();
			//Gdx.app.debug(TAG, "worldTouch pos: " + pos);
			if(this.collisionRect.contains(pos.x,pos.y)){
				return true;
			}
		}
		return false;
	}
	
	
	public void update(float dt, Input input){

		if(this.clickedOn(input)){
			this.rotate(1);
		}
		
		//if(Gdx.input.justTouched()){
		//	this.rotate(1);
		//}
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

	public TileType getType() {
		return this.type;
	}

	public void setConnection(boolean bool) {
		this.connected = bool;
	}
	
	public boolean getConnection() {
		return connected;
	}
	    
    public int getOrientation() {
        return orientation;
    }
}
