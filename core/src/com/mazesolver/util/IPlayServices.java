package com.mazesolver.util;

/**
 * Created by Einar Örn on 24.3.2016.
 */
public interface IPlayServices
{
    public void signIn();
    public void signOut();
    public void rateGame();
    public void unlockAchievement();
    public void submitScore(int highScore);
    public void showAchievement();
    public void showScore();
    public boolean isSignedIn();
}