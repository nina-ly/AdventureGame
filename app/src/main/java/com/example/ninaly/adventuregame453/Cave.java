package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Cave extends Activity {
    public static final String Data = "Progress_Data";
    int points, evilPoints, progress;
    boolean lantern, sword, emerald;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", 0);
        lantern = progressData.getBoolean("lantern", false);
        sword = progressData.getBoolean("sword", false);
        emerald = progressData.getBoolean("emerald", false);
        setContentView(progress);

    }

    public void onClick(View view){

    }
}
