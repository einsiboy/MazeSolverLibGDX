package com.mazesolver.desktop;

import com.badlogic.gdx.Gdx;
import com.mazesolver.util.IPlayServices;

/**
 * Created by Einar Örn on 24.3.2016.
 */
public class DesktopGooglePlayServices implements IPlayServices {
    public static final String TAG = DesktopGooglePlayServices.class.getName();

    @Override
    public void signIn() {
        Gdx.app.debug(TAG, "desktop signing in google play services");
    }

    @Override
    public void signOut() {
        Gdx.app.debug(TAG, "desktop signing out google play services");
    }

    @Override
    public void rateGame() {
        Gdx.app.debug(TAG, "desktop rating game in google play services");
    }

    @Override
    public void unlockAchievement() {
        Gdx.app.debug(TAG, "desktop unlocking achievement in google play services");
    }

    @Override
    public void submitScore(int highScore) {
        Gdx.app.debug(TAG, "desktop submitting score in google play services");
    }

    @Override
    public void showAchievement() {
        Gdx.app.debug(TAG, "desktop showing achievements in google play services");
    }

    @Override
    public void showScore() {
        Gdx.app.debug(TAG, "desktop showing score in google play services");
    }

    @Override
    public boolean isSignedIn() {
        return false;
    }
}
