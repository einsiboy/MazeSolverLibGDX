package com.mazesolver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.mazesolver.util.IPlayServices;


public class AndroidLauncher extends AndroidApplication implements IPlayServices {
	private static final String TAG = AndroidLauncher.class.getName();

	private GameHelper gameHelper;
	private final static int requestCode = 1;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setLogLevel(LOG_DEBUG);

		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);

		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInFailed(){ }

			@Override
			public void onSignInSucceeded(){ }
		};

		gameHelper.setup(gameHelperListener);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MazeSolverMain(this), config);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		gameHelper.onStop();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn() {
		Gdx.app.log(TAG, "android attemting google play services sign in");
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		}
		catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void signOut() {
		Gdx.app.debug(TAG, "android attemting google play services sign out");
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.signOut();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
		}
	}

	@Override
	public void rateGame() {
		Gdx.app.debug(TAG, "android attemting google play services rate game");

		String str = "Your PlayStore Link";
		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
	}

	@Override
	public void unlockAchievement() {
		Gdx.app.debug(TAG, "android attemting google play services unlocking achievement");

		//Games.Achievements.unlock(gameHelper.getApiClient(),
				//getString(R.string.achievement_dum_dum));
	}

	@Override
	public void submitScore(int highScore) {
		Gdx.app.log(TAG, "android attemting google play services submit score");

		if (isSignedIn()) {
			Gdx.app.log(TAG, "submitting");
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_all_time_highscore), highScore);
			Gdx.app.log(TAG, "done submitting");
		}
	}

	@Override
	public void showAchievement() {
		Gdx.app.debug(TAG, "android attemting google play services show achievement");

		if (isSignedIn() == true) {
			//startActivityForResult(Games.Achievements.getAchievementsIntent(gameHelper.getApiClient(),
			//		getString(R.string.achievement_dum_dum)), requestCode);
		} else {
			signIn();
		}
	}

	@Override
	public void showScore() {
		Gdx.app.debug(TAG, "android attemting google play services show score");

		if (isSignedIn() == true) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_all_time_highscore)), requestCode);
		} else {
			signIn();
		}
	}

	@Override
	public boolean isSignedIn() {
		Gdx.app.log(TAG, "getting sign in status");
		Gdx.app.log(TAG, "android getting signed in status: " + gameHelper.isSignedIn());
		return gameHelper.isSignedIn();
	}
}
