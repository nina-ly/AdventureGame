package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Christian on 12/8/2016.
 */

public class GameOver extends Activity {
    public static final String Data = "Progress_Data";
    SharedPreferences progressData;
    int points, evilPoints, progress;
    boolean lantern, sword, emerald, key, treasure;
    public void onCreate(Bundle savedInstanceState){
        progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", R.layout.cavebeginning);
        lantern = progressData.getBoolean("lantern", true);
        sword = progressData.getBoolean("sword", true);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", true);
        treasure = progressData.getBoolean("treasure", false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        setDeathScreen(progress);

    }

    public void setDeathScreen(int death){
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.gameoverscreen);
        TextView goDesc = (TextView)findViewById(R.id.gameovertext);
        TextView goPoints = (TextView)findViewById(R.id.gameoverpoints);
        switch(death){
            case 1://goblins kill you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.forest));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 2://sword pubishment
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.forest_path));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 3://ogre kills you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ogrecave));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 4://jump to your death
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.bridge));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 5://eaten by bugs
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ogrecave2));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 6://trapped in the cave
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.door));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 7://eaten by the alligator
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.castle));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 8://too gready in the castle
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 9://princess kills you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 10://wizard kills you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.castle));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 11://evil win
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 12:// good win
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 13:// boring end
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
        }
    }
}
