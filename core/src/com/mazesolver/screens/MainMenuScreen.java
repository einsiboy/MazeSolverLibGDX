package com.mazesolver.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mazesolver.MazeSolverMain;
import com.mazesolver.util.Assets;
import com.mazesolver.util.Constants;

public class MainMenuScreen extends AbstractScreen {
	public static final String TAG = MainMenuScreen.class.getName();

	
	private SpriteBatch batch;
	private Stage stage;
	private Sprite background;
	private Skin skin;
	private Pixmap playBtnPixmap;
	
	public MainMenuScreen(Game game) {
		super(game);

		Gdx.app.debug(TAG, "in MainMenuScreen");
		this.batch = new SpriteBatch();
		background = Assets.instance.spriteBackground;
		initStage(); //temp testing
	}
	
	private void initStage(){
		//TODO: note that the stage has its own orthographic camera, so
		//it has its "own" coordinate system, need to figure out how to apply our
		// virtual world coordinate system to the stage as well.
		
		this.stage = new Stage();
		//stage.setViewport(new StretchViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, camera));
		
		Label label = new Label("Test Label", new LabelStyle(new BitmapFont(), Color.BLUE ) );
		
		TextButtonStyle style = new TextButtonStyle();
		style.font = new BitmapFont();
		TextButton btn = new TextButton("Play Game", style);
		
		btn.setX(Constants.WORLD_WIDTH/2.0f);
		btn.setY(Constants.WORLD_HEIGHT/2.0f);
		btn.addListener(new ClickListener(){
			@Override
		    public void clicked(InputEvent event, float x, float y) {
				//game.setScreen( new PlayScreen(game) );
				//dispose();
				//switchScreen(new PlayScreen(game));
				((MazeSolverMain) game).nextLevel();
				dispose();
		    };
		});
		
		TextButton playButton = makePlayButton();
		
		stage.addActor(label);
		stage.addActor(btn);
		stage.addActor(playButton);
	}
	
	private TextButton makePlayButton(){
		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
				// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
				skin = new Skin();
				// Generate a 1x1 white texture and store it in the skin named "white".
				this.playBtnPixmap = new Pixmap(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20, Format.RGBA8888);
				playBtnPixmap.setColor(Color.GREEN);
				playBtnPixmap.fill();
				

				skin.add("white", new Texture(playBtnPixmap));

				// Store the default libgdx font under the name "default".
				BitmapFont bfont=new BitmapFont();
				//bfont.getData().scale(1.0f);;
				skin.add("default",bfont);

				// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
				TextButtonStyle textButtonStyle = new TextButtonStyle();
				textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
				textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
				textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
				textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

				textButtonStyle.font = skin.getFont("default");

				skin.add("default", textButtonStyle);
				
				TextButton btn = new TextButton("Play game! dada", textButtonStyle);
				return btn;
	}
	
	
	private void update(float deltaTime){
		//level.update();
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
	    
		stage.draw();
	    // Everything is drawn in batches strong performance gains, so all drawing
	    // should be done between batch.begin() and batch.end()
	    batch.begin();
	    //background.setPosition(Constants.WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2);
	    background.draw(batch);
	    //batch.draw(Assets.instance.textureBackground, 0, 0);
	    Gdx.app.debug(TAG, background.getX() + " " +  background.getY());
		
		batch.end();
		
		
		
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
		skin.dispose();
		playBtnPixmap.dispose();
	}


}
