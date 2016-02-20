package com.mazesolver.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

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
	
	
	//singleton, Assets instance provides "global" access
	private Assets(){
		//init();
		this.assetManager = new AssetManager();
		
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
