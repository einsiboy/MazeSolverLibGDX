package com.mazesolver.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
import com.mazesolver.util.Helpers;

public class SubmitHighscoreScreen extends AbstractScreen {
	public static final String TAG = SubmitHighscoreScreen.class.getName();
	
	private SpriteBatch batch;
	private Stage stage;
	private Sprite background;

	private int highScore;
	private boolean highScoreSubmitted = false;

	//** just for debugging from MazeSolverMain */
	public SubmitHighscoreScreen(MazeSolverMain game) {
		this(game, MathUtils.random(1, 1000));
	}

	public SubmitHighscoreScreen(MazeSolverMain game, int highScore) {
		super(game);
		Gdx.app.debug(TAG, "in SubitHighscoreScreen");
		this.highScore = highScore;

		this.batch = new SpriteBatch();
		this.background = Assets.instance.spriteBackground;
		initStage();
	}
	
	private void initStage(){
		this.stage = new Stage();
		
		float stageWidth = stage.getCamera().viewportWidth;
		float stageHeight = stage.getCamera().viewportHeight;
		
		String scoreText = "score: " + this.highScore;
		
		//Label score = Assets.instance.uiElements.getDefaultUILabel(scoreText);
		int scoreFontSize = Helpers.getFontSize(16, 720, stage.getCamera().viewportHeight);
		Label score = Assets.instance.uiElements.getLemonMilkLabel(scoreText, scoreFontSize, Color.RED);
		score.setColor(Color.RED);
		score.setX(stageWidth / 2 - score.getWidth() / 2);
		score.setY(stageHeight / 2 + stageHeight * 0.03f);

		int titleFontSize = Helpers.getTitleFontSize(stage.getCamera().viewportHeight);
		Label title = Assets.instance.uiElements.getLemonMilkLabel("Mazesolver", titleFontSize, Color.RED);
		title.setPosition(stageWidth / 2 - title.getWidth() / 2,
				stageHeight / 2 + stageHeight * 0.1f);
		
		TextButton submitBtn = Assets.instance.uiElements.get9PatchButton("Submit Highscore");
		submitBtn.setWidth(submitBtn.getWidth() * 1.5f);
		submitBtn.setX(stageWidth / 2 - submitBtn.getWidth() / 2);
		submitBtn.setY(stageHeight / 2 - stageHeight * 0.05f);
		submitBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				//submit highscore
				submitHighScore();
				Gdx.app.debug(TAG, "submitting highscore");
			}

			;
		});

		TextButton showHighscoreBtn = Assets.instance.uiElements.get9PatchButton("View Highscore");
		showHighscoreBtn.setWidth(showHighscoreBtn.getWidth()*1.5f);
		showHighscoreBtn.setX(stageWidth / 2 - submitBtn.getWidth() / 2);
		showHighscoreBtn.setY(stageHeight / 2 - stageHeight * 0.05f - submitBtn.getHeight());
		showHighscoreBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.showScore();
			};
		});


		//add all actors (ui elements)
		stage.addActor(score);
		stage.addActor(title);
		stage.addActor(submitBtn);
		stage.addActor(showHighscoreBtn);
	}

	private void submitHighScore(){
		if(!this.highScoreSubmitted){
			game.submitHighScore(this.highScore);
			this.highScoreSubmitted = true;
		}
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
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		Gdx.app.debug(TAG, "in submit screen");
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
