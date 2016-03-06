package com.mazesolver.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mazesolver.MazeSolverMain;
import com.mazesolver.util.Assets;
import com.mazesolver.util.Constants;

public class MainMenuScreen extends AbstractScreen {
	public static final String TAG = MainMenuScreen.class.getName();

	
	private SpriteBatch batch;
	private Sprite background;

	
	private Stage stage;
	private Table table;
	
	

	
	public MainMenuScreen(MazeSolverMain game) {
		super(game);

		Gdx.app.debug(TAG, "in MainMenuScreen");
		this.batch = new SpriteBatch();
		this.background = Assets.instance.spriteBackground;
		initStage(); 
	}
	
	private void initStage(){
		this.stage = new Stage();
		this.table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		//Label title = Assets.instance.uiElements.getTitle();
		Label title = Assets.instance.uiElements.getLemonMilkLabel("Mazesolver", 36, Color.RED);
		title.setPosition(Gdx.graphics.getWidth()/2 - title.getWidth()/2, 
				Gdx.graphics.getHeight()/2 +  Gdx.graphics.getHeight()*0.1f);
		
		//Label testLabel = Assets.instance.uiElements.getDefaultUILabel("test label");
		//testLabel.getStyle().font = Assets.instance.fonts.getLemonMilk();
		
		TextButton playButton = Assets.instance.uiElements.get9PatchButton("Play game!");
		playButton.setWidth(playButton.getWidth()*1.5f);
		playButton.setX(stage.getCamera().viewportWidth/2 - playButton.getWidth()/2);
		
		//btn9patch.setPosition(200, 150);
		
		playButton.addListener(new ClickListener(){
			@Override
		    public void clicked(InputEvent event, float x, float y) {
				game.nextLevel();
				dispose();
		    };
		});
		
		//add all actors (ui elements)
		table.addActor(title);
		table.addActor(playButton);
	}
	
	
	
	private void update(float deltaTime){
		stage.act();
	}
	
	private void draw(SpriteBatch batch){
		//just temporary clear with blueish colour since no background is present yet
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
	public void render(float deltaTime) {
		//Gdx.app.debug(TAG, "main menu screen rendering");
		update(deltaTime);
		draw(batch);
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = (Constants.WORLD_WIDTH / width) * height;
		camera.update();
		stage.getViewport().update(width, height, true);
		
		//stage.setViewport(new StretchViewport(camera.viewportWidth, camera.viewportHeight, camera));
		//stage.getViewport().update((int)camera.viewportWidth, (int)camera.viewportHeight, true);
		
		/*
		stage.getCamera().viewportHeight = camera.viewportHeight;
		stage.getCamera().viewportWidth = camera.viewportWidth;
		stage.getCamera().position.set(camera.position); //center the camera
		*/
		
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
		batch.dispose();
		stage.dispose();
	}


}
