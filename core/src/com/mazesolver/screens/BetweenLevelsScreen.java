package com.mazesolver.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mazesolver.MazeSolverMain;
import com.mazesolver.util.Assets;
import com.mazesolver.util.Constants;
import com.mazesolver.util.Helpers;

public class BetweenLevelsScreen extends AbstractScreen {
	public static final String TAG = BetweenLevelsScreen.class.getName();

	
	private SpriteBatch batch;
	private Stage stage;
	private Sprite background;

	public BetweenLevelsScreen(MazeSolverMain game) {
		super(game);
		
		Gdx.app.debug(TAG, "in Between levels screen");
		this.batch = new SpriteBatch();
		this.background = Assets.instance.spriteBackground;
		initStage();
	}
	
	private void initStage(){
		this.stage = new Stage();

		int titleFontSize = Helpers.getTitleFontSize(stage.getCamera().viewportHeight);
		Label title = Assets.instance.uiElements.getLemonMilkLabel("Mazesolver", titleFontSize, Color.RED);
		title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2, 
				Gdx.graphics.getHeight()/2 +  Gdx.graphics.getHeight()*0.1f);
		
		TextButton nextLevel = Assets.instance.uiElements.get9PatchButton("Next level");
		nextLevel.setWidth(nextLevel.getWidth()*1.5f);
		nextLevel.setX(stage.getCamera().viewportWidth/2 - nextLevel.getWidth()/2);
		
		nextLevel.addListener(new ClickListener(){
			@Override
		    public void clicked(InputEvent event, float x, float y) {
				Gdx.app.debug(TAG, "pressed next level button");
				game.nextLevel();
				dispose();
		    };
		});
		
		//add all actors (ui elements)
		stage.addActor(title);
		stage.addActor(nextLevel);
	}
	
	private void update(float dt){
		stage.act(dt);
	}

	@Override
	public void render(float deltaTime) {
		update(deltaTime);
		draw(batch);
	}
	
	private void draw(SpriteBatch batch){
	    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    //always update camera matrices before drawing
	    //just in case we introduce zoom or some camera changes later on.
	    camera.update();
	    
	    //This tells the SpriteBatch renderer to use our virtual world units
	    // specified in under Constants.WORLD_WIDTH and HEIGHT
	    batch.setProjectionMatrix(camera.combined);
	    
	    // Everything is drawn in batches strong performance gains, so all drawing
	    // should be done between batch.begin() and batch.end()
	    batch.begin();
	    background.draw(batch);
		batch.end();
		//the stage has its own SpriteBatch so place outside batch begin, end
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = (Constants.WORLD_WIDTH / width) * height;
		camera.update();	
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
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
