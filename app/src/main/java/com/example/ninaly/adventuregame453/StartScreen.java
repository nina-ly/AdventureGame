package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartScreen extends Activity {

    public static final String progressData = "Progress_Data";
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        mp = MediaPlayer.create(this, R.raw.instrumental);
        mp.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

    public void onNewGameClick(View view){
        SharedPreferences points = getSharedPreferences(progressData, 0);
        SharedPreferences.Editor editor = points.edit();

        editor.putInt("points", 0);
        editor.putInt("evil-points", 1);
        editor.putBoolean("lantern", true);
        editor.putBoolean("sword", true);
        editor.putBoolean("emerald", true);
        editor.putInt("part", 1);
        editor.putInt("storyProgress", R.layout.part1_intro);

        editor.commit();

        Intent startNewGame = new Intent(StartScreen.this, PlayScreen.class);
        startActivity(startNewGame);

    }

    public void onContinueClick(View view){
        SharedPreferences points = getSharedPreferences(progressData, 0);
        Intent resumeGame;
        if(points.getInt("part",1) == 1){
            resumeGame = new Intent(StartScreen.this, Part1.class);
            startActivity(resumeGame);
        }else if (points.getInt("part",1) == 2){
            resumeGame = new Intent(StartScreen.this, Cave.class);
            startActivity(resumeGame);
        }else{
            resumeGame = new Intent(StartScreen.this, Part3Castle.class);
            startActivity(resumeGame);
        }

    }

    public void onExitClick(View view){
        System.exit(0);
    }
}
