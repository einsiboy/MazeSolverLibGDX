package com.mazesolver.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mazesolver.screens.AbstractScreen;

public class Input extends InputAdapter {
	
	/** Container for Mouse / Touch events */
	/*public class TouchState{
	    //this.mouse = {};  
		//the input.mouse object has the following properties: 
		//x, y, justClicked, isDown, clickedX, clickedY, upX, downX
		public boolean justTouched, isDown;
		public Vector2 moveMousePos; // current mouse position
		public Vector2 downPos;
		public Vector2 upPos;
		
		TouchState(){
			this.justTouched = false;
			this.isDown = false;
			this.downPos = new Vector2();
			this.upPos = new Vector2();
			this.moveMousePos = new Vector2();
		}

		void clearState() {
			this.justTouched = false;
		}
	}*/
	
	private AbstractScreen screen;
	Vector3 worldTouchPos;
	Vector3 screenTouchPos;
	
	Vector3 worldMovePos;
	Vector3 screenMovePos;
	
	// public for convenience polling
	//public TouchState touchState;
	
	
	
	public Input(AbstractScreen screen){
		this.screen = screen;
		
		this.worldTouchPos = new Vector3();
		this.screenTouchPos = new Vector3();
		
		this.worldMovePos = new Vector3();
		this.screenMovePos = new Vector3();
		
		//this.touchState = new TouchState();
	}
	
	private void handleKeyUpDebugInput(int keycode){
		
	}
	
	@Override
	public boolean keyUp(int keycode){
		if(Constants.DEBUG){
			handleKeyUpDebugInput(keycode);
		}
		
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button){
		screenTouchPos.set(screenX, screenY, 0);
		worldTouchPos = screen.getCamera().unproject(screenTouchPos);
		//screen.touchDown(worldTouchPos);
		
		/*touchState.justTouched = true;
		touchState.isDown = true;
		touchState.downPos.set(worldTouchPos.x, worldTouchPos.y);*/
		
		
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button){
		screenTouchPos.set(screenX, screenY, 0);
		worldTouchPos = screen.getCamera().unproject(screenTouchPos);
		//screen.touchUp(worldTouchPos);
		
		//touchState.isDown = false;
		//touchState.upPos.set(worldTouchPos.x, worldTouchPos.y);
		return false;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY){
		screenMovePos.set(screenX, screenY, 0);
		worldMovePos = screen.getCamera().unproject(screenMovePos);
		//touchState.moveMousePos.set(worldMovePos.x, worldMovePos.y);
		return false;
	}
	
	public Vector3 getWorldTouchPos(){
		return this.worldTouchPos;
	}
	
	/** clears the state of this.touch */
	public void clearInputState(){
		//this.touchState.clearState();
	}
	
}
