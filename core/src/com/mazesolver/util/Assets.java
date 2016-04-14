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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Assets klasinn ser um ad loada ollum assets og bua til sprites
 * til ad hafa audvelt adgengi allstadar ad sprites, og einn staur
 * fyrir allt loading
 */
public class Assets {
	//Constatnts
	public static final String TAG = Assets.class.getName();
	public static final String ATLAS_UI = "ui_starterpack/uiskin.atlas";
	public static final String SKIN_UI = "ui_starterpack/uiskin.json";
	public static final String ATLAS_UI_BUTTONS = "ui/9patch_buttons.atlas";
	public static final String SKIN_UI_BUTTONS = "ui/9patch_buttons.json";

	

	public static final Assets instance = new Assets();
	
	/*private AssetManager assetManager;
	public AssetFonts fonts;
	public AssetBackground background;
	public AssetPlayer player;
	public AssetEnemy enemy;
	public AssetBoundary boundary;*/
	
	private AssetManager assetManager;
	private Skin skinUI;
	private Skin skinButton;
	private TextureAtlas textureAtlasUI;
	private TextureAtlas textureAtlasButtons;
	
	//Background
	private Texture textureBackground;
	public Sprite spriteBackground;
	
	public UIElements uiElements;
	public Fonts fonts;

	
	
	//singleton, Assets instance provides "global" access
	private Assets(){
		//this.assetManager = new AssetManager(); will be used for loading graphics
		this.uiElements = new UIElements();
		this.fonts = new Fonts();
		
		//Background
		textureBackground = new Texture(Gdx.files.internal("images/hedgemaze_alltiles.jpg"));
		spriteBackground = new Sprite(textureBackground);
		spriteBackground.setSize(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT);
		
		this.assetManager = new AssetManager();
		init();
	}
	
	public void init(){
		assetManager.load(ATLAS_UI, TextureAtlas.class);
		assetManager.load(SKIN_UI, Skin.class);
		
		assetManager.load(ATLAS_UI_BUTTONS, TextureAtlas.class);
		//assetManager.load(SKIN_UI_BUTTONS, Skin.class);
		//TODO: load game graphics atlas here
		assetManager.finishLoading();
		
		skinUI = assetManager.get(SKIN_UI);
		textureAtlasUI = assetManager.get(ATLAS_UI);


		textureAtlasButtons = assetManager.get(ATLAS_UI_BUTTONS, TextureAtlas.class);

		//Stopped loading the skinButton skin in the Assetmanager to generate font size on the fly
		skinButton = new Skin();
		skinButton.add("default-font", this.fonts.getLemonMilk(Helpers.getFontSize(20, 720.0f, Gdx.graphics.getHeight())));
		skinButton.addRegions(new TextureAtlas(Gdx.files.internal("ui/9patch_buttons.atlas")));
		skinButton.load(Gdx.files.internal("ui/9patch_buttons.json"));

		//this.assetManager = assetManager;
		
		//assetManager.load(Constants.ATLAS_GAME, TextureAtlas.class);
		//assetManager.finishLoading();
		//TextureAtlas atlas = assetManager.get(Constants.ATLAS_GAME);
		
		//initAssets(atlas);
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
		textureBackground.dispose();
		assetManager.clear();
		assetManager.dispose();
		fonts.dispose();
		uiElements.dispose();
		//fonts.dispose();
	}
	
	
	
	public class Fonts {
		
		public Fonts(){
		}
		
		public BitmapFont getLemonMilk(int size){
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/LemonMilk.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = size;
			BitmapFont lemonMilk = generator.generateFont(parameter);
			generator.dispose();
			
			return lemonMilk;
		}
		
		public void dispose(){
			
		}
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
		
		public Label getDefaultUILabel(String labelText){
			return new Label(labelText, skinUI);
		}
		
		public Label getLemonMilkLabel(String labelText, int size, Color color){
			LabelStyle style = new LabelStyle(fonts.getLemonMilk(size), color);
			return new Label(labelText, style);
		}
		
		public TextButton getDefaultUITextButton(String buttonText){
			return new TextButton(buttonText, skinUI);
		}
		
		public TextButton get9PatchButton(String buttonText){
			//Drawable tmpDrawable = skinButton.newDrawable(skinButton.getDrawable("default"));
			TextButton btn = new TextButton(buttonText, skinButton);
			//TextButton btn = new TextButton(buttonText, tmpDrawable);
			btn.setHeight(btn.getHeight()*0.75f);
			
			btn.setX(Gdx.graphics.getWidth()/2 - btn.getWidth()/2);
			btn.setY(Gdx.graphics.getHeight() / 2);
			
			return btn;
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
