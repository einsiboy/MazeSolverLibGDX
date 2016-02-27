package com.mazesolver.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mazesolver.MazeSolverMain;
import com.mazesolver.util.Assets;
import com.mazesolver.util.Constants;

public class SubmitHighscoreScreen extends AbstractScreen {
	public static final String TAG = SubmitHighscoreScreen.class.getName();
	
	private SpriteBatch batch;
	private Stage stage;
	private Sprite background;

	public SubmitHighscoreScreen(MazeSolverMain game) {
		super(game);
		Gdx.app.debug(TAG, "in SubitHighscoreScreen");
		
		this.batch = new SpriteBatch();
		this.background = Assets.instance.spriteBackground;
		initStage();
	}
	
	private void initStage(){
		// The stage uses temporary uiElements to create a mockup
		// to be able to implement the screen transitions 
		
		this.stage = new Stage();
		
		Label title = Assets.instance.uiElements.getTitle();
		Label score = Assets.instance.uiElements.getTitle();
		score.setText("score: " + MathUtils.random()*100); 
		score.setFontScale(1.0f);
		score.setY(score.getY() - 50);
		
		
		TextButton nextLevel = Assets.instance.uiElements.getPlayButton();
		nextLevel.setY(nextLevel.getY() - 50);
		
		nextLevel.setText("Submit HighScore");
		nextLevel.addListener(new ClickListener(){
			@Override
		    public void clicked(InputEvent event, float x, float y) {
				//submit highscore
			};
		});
		
		//add all actors (ui elements)
		stage.addActor(score);
		stage.addActor(title);
		stage.addActor(nextLevel);
	}
	
	private void draw(SpriteBatch batch){
	    Gdx.gl.glClearColor(0, 0, 0.2f, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    camera.update();
	    
	    //This tells the SpriteBatch renderer to use our virtual world units
	    // specified in under Constants.WORLD_WIDTH and HEIGHT
	    batch.setProjectionMatrix(camera.combined);
	    
	    // Everything is drawn in batches, strong performance gains, so all drawing
	    // should be done between batch.begin() and batch.end()
	    batch.begin();
	    background.draw(batch);
		batch.end();
		//the stage has its own SpriteBatch so place outside batch begin, end
		stage.draw();
	}
	
	private void update(float dt){
		stage.act(dt);
	}

	@Override
	public void render(float deltaTime) {
		update(deltaTime);
		draw(batch);
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = (Constants.WORLD_WIDTH / width) * height;
		camera.update();
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
