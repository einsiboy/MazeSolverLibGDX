package com.mazesolver.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mazesolver.objects.Level;
import com.mazesolver.util.Constants;

public class PlayScreen extends AbstractScreen {
	public static final String TAG = PlayScreen.class.getName();
	
	private Level level;
	private ShapeRenderer renderer;

	public PlayScreen(Game game) {
		super(game);
		Gdx.app.debug(TAG, "switched to play screen");
		
		level = new Level();
		renderer = new ShapeRenderer();
		
	}
	
	public PlayScreen(Game game, Level level){
		super(game);
		Gdx.app.debug(TAG, "switched to play screen");

		this.level = level;
		this.renderer = new ShapeRenderer();
	}
	
	private void drawDebug(){
		renderer.begin(ShapeType.Line);
		
		renderer.setColor(Color.BLACK);
		//x-axis
		renderer.line(0, camera.position.y,
				camera.position.x*2, camera.position.y);
		//y-axis
		renderer.line(camera.position.x, 0,
						camera.position.x, camera.position.y*2);
		renderer.end();
	}
	
	private void draw(){
	    //update camera and set renderer projection matrix
	    camera.update();
	    renderer.setProjectionMatrix(camera.combined);
	    
	    level.render(renderer);
	    
	    if(Constants.DEBUG){
	    	drawDebug();
	    }
	}
	
	private void update(float dt){
		level.update(dt);
	}

	@Override
	public void render(float deltaTime) {
		//Gdx.app.debug(TAG, "PlayScreen rendering");
		//just temporary clear colour since no background is present yet
	    Gdx.gl.glClearColor(0, 0.2f, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    update(deltaTime);
	    draw();
	    

		
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		camera.viewportHeight = (Constants.WORLD_WIDTH / width) * height;
		camera.update();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
