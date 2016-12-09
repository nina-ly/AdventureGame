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
        editor.putInt("evilPoints", 0);
        editor.putInt("player-health", 10);
        editor.putInt("princess-health", 7);
        editor.putInt("wizard-health", 10);
        editor.putBoolean("lantern", false);
        editor.putBoolean("sword", false);
        editor.putBoolean("emerald", false);
        editor.putBoolean("key", false);
        editor.putBoolean("treasure", false);
        editor.putString("magic", "none");
        editor.putInt("storyProgress", R.layout.part1_intro);

        editor.commit();

        Intent startNewGame = new Intent(StartScreen.this, PlayScreen.class);
        startActivity(startNewGame);

    }

    public void onContinueClick(View view){
        Intent resumeGame = new Intent(StartScreen.this, PlayScreen.class);
        startActivity(resumeGame);


    }

    public void onExitClick(View view){
        System.exit(0);
    }
}
