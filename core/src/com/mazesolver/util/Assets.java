package com.mazesolver.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Assets klasinn sér um að loada öllum assets og búa til sprites
 * til að hafa auðvelt aðgengi allstaðar að sprites, og einn staður
 * fyrir allt loading
 */
public class Assets {
	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets();
	
	/*private AssetManager assetManager;
	public AssetFonts fonts;
	public AssetBackground background;
	public AssetPlayer player;
	public AssetEnemy enemy;
	public AssetBoundary boundary;*/
	
	private AssetManager assetManager;
	
	//Background
	private Texture textureBackground;
	public Sprite spriteBackground;
	public UIElements uiElements;

	
	
	//singleton, Assets instance provides "global" access
	private Assets(){
		//init();
		this.assetManager = new AssetManager();
		this.uiElements = new UIElements();

		
		textureBackground = new Texture(Gdx.files.internal("images/hedgemaze_alltiles.jpg"));
		spriteBackground = new Sprite(textureBackground);
		spriteBackground.setSize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		//spriteBackground.setPosition(-Constants.WORLD_WIDTH / 2,  -Constants.WORLD_HEIGHT /2);
	}
	
	public void init(){
		//this.assetManager = assetManager;
		
		//assetManager.load(Constants.ATLAS_GAME, TextureAtlas.class);
		//assetManager.finishLoading();
		//TextureAtlas atlas = assetManager.get(Constants.ATLAS_GAME);
		
		initFonts();
		//initAssets(atlas);
	}
	
	private void initFonts(){
		//fonts = new AssetFonts();
	}
	
	/**
	 * Initialize assets in the atlas, implementations will vary
	 * @param atlas the TextureAtlas with all images for this game
	 */
	private void initAssets(TextureAtlas atlas){
		/*background = new AssetBackground(atlas);
		player = new AssetPlayer(atlas);
		enemy = new AssetEnemy(atlas);
		boundary = new AssetBoundary(atlas);*/
	}
	
	public void dispose(){
		assetManager.dispose();
		
		textureBackground.dispose();
		
		//fonts.dispose();
	}
	
	
	public class UIElements{
		
		private Skin skin;
		private Pixmap playBtnPixmap;
		
		public UIElements(){
			// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
			// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
			skin = new Skin();
			this.playBtnPixmap = new Pixmap(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() / 20, Format.RGBA8888);

		}
		
		
		public TextButton getPlayButton(){
			// Generate a 1x1 white texture and store it in the skin named "white".
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
			
			TextButton btn = new TextButton("Play game! \nTada", textButtonStyle);
			btn.setX(Gdx.graphics.getWidth()/2 - btn.getWidth()/2);
			btn.setY(Gdx.graphics.getHeight() / 2);
			
			return btn;
		}
		
		/** returns the title and positions it in the default position */
		public Label getTitle(){
			Label title = new Label(Constants.TITLE, new LabelStyle(new BitmapFont(), Color.RED ) );
			title.setFontScale(2);
			title.setX(Gdx.graphics.getWidth()/2 - title.getWidth());
			float offsetTitle = Gdx.graphics.getHeight() * 0.1f;
			title.setY(Gdx.graphics.getHeight()/ 2 + offsetTitle);
			return title;
		}
		
		public void dispose(){
			this.skin.dispose();
			this.playBtnPixmap.dispose();
		}
	}
	
	/*
	
	////////////////////////////////////////////////
	/////// Helper asset classes for grouping //////
	////////////////////////////////////////////////
	
	public class AssetFonts{
		//add custom fonts and load if desired
		
		//public final BitmapFont neonGlow;
		public final BitmapFont neonLike;
		
		public AssetFonts(){
			
			neonLike = new BitmapFont(Gdx.files.internal("fonts/neonLike.fnt"));
			
			/* Use the FreeTypeFontGenerator for quickly testing fonts with various settings on desktop
			 * It can provide some problems on android devices, so just use bitmap fonts with predetermined settings
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/neonlights.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = 36;
			parameter.minFilter = TextureFilter.Linear;
			parameter.magFilter = TextureFilter.Linear;
			
			FreeTypeFontGenerator veselka4FGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Veselka4F.ttf"));
			veselka4F = veselka4FGen.generateFont(parameter);
			
			generator.dispose(); // don't forget to dispose to avoid memory leaks!
			
			veselka4F.getRegion().getTexture().setFilter(
					TextureFilter.Linear, TextureFilter.Linear);*/
			
		/*}
		
		public void dispose(){
			neonLike.dispose();
		}
	}
	
	public class AssetBackground{
		public final Sprite background;
		
		public AssetBackground(TextureAtlas atlas){
			background = UtilFunctions.spriteFromRegion(atlas.findRegion("background"));
			background.setSize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		}
		
	}
	
	public class AssetPlayer{
		public final Sprite circlePlayer;
		
		public AssetPlayer(TextureAtlas atlas){
			circlePlayer = UtilFunctions.spriteFromRegion(atlas.findRegion("player"));
		}
	}
	
	public class AssetEnemy{
		public final Sprite circleEnemy;
		
		public AssetEnemy(TextureAtlas atlas){
			circleEnemy = UtilFunctions.spriteFromRegion(atlas.findRegion("enemy115px"));
		}
	}

	public class AssetBoundary{
		public final Sprite circleBoundary;
		
		public AssetBoundary(TextureAtlas atlas){
			circleBoundary = UtilFunctions.spriteFromRegion(atlas.findRegion("boundary"));
		}
	}
	*/
}
