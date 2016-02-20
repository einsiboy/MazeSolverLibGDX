package com.mazesolver.util;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.mazesolver.screens.AbstractScreen;

public class Input extends InputAdapter {
	

	
	private AbstractScreen screen;
	Vector3 worldTouchPos;
	Vector3 screenTouchPos;
	
	
	
	
	
	public Input(AbstractScreen screen){
		this.screen = screen;
		
		this.worldTouchPos = new Vector3();
		this.screenTouchPos = new Vector3();
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
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button){
		screenTouchPos.set(screenX, screenY, 0);
		worldTouchPos = screen.getCamera().unproject(screenTouchPos);
		//screen.touchUp(worldTouchPos);
		return false;
	}
	
}
