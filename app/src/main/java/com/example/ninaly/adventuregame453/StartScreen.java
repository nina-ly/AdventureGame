package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartScreen extends Activity {

    public static final String progressData = "Progress_Data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void onNewGameClick(View view){
        Intent startNewGame = new Intent(StartScreen.this, Part1.class);
        startActivity(startNewGame);
        SharedPreferences points = getSharedPreferences(progressData, 0);
        SharedPreferences.Editor editor = points.edit();

        editor.putInt("points", 0);
        editor.putInt("evil-points", 0);
        editor.putBoolean("lantern", false);
        editor.putBoolean("sword", false);
        editor.putBoolean("emerald", false);
        editor.putInt("part", 1);
        editor.putInt("storyProgress", 1);

        editor.commit();
    }

    public void onContinueClick(View view){
        Toast.makeText(StartScreen.this,"Continue click", Toast.LENGTH_SHORT).show();

    }

    public void onExitClick(View view){
        System.exit(0);
    }
}
