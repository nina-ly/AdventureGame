package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
        Intent intent = getIntent();
        int gameover = intent.getIntExtra("gameover",13);
        setDeathScreen(gameover);

    }

    public void setDeathScreen(int death){
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.gameoverscreen);
        ImageView image = (ImageView)findViewById(R.id.endingImage);
        TextView goDesc = (TextView)findViewById(R.id.gameovertext);
        TextView goPoints = (TextView)findViewById(R.id.gameoverpoints);
        switch(death){
            case 1://goblins kill you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.forest));
                image.setImageDrawable(getResources().getDrawable(R.drawable.goblingo));
                goDesc.setText("The goblins tear you and the fairy apart for killing their friend");
                goPoints.setText("Points: " + points);
                break;
            case 2://sword pubishment
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.forest_path));
                image.setImageDrawable(getResources().getDrawable(R.drawable.swordgo));
                goDesc.setText("Lightning rained down from the heavens and vaporized you and your evil heart!");
                goPoints.setText("Points: " + points);
                break;
            case 3://ogre kills you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ogrecave));
                image.setImageDrawable(getResources().getDrawable(R.drawable.ogre3));
                goDesc.setText("The ogre bashed your head in and that was that");
                goPoints.setText("Points: " + points);
                break;
            case 4://jump to your death
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.bridge));
                image.setImageDrawable(null);
                goDesc.setText("You fall to the bottom of the pit and leave a giant red stain");
                goPoints.setText("Points: " + points);
                break;
            case 5://eaten by bugs
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.ogrecave2));
                image.setImageDrawable(getResources().getDrawable(R.drawable.bones));
                goDesc.setText("You feel a couple of bugs crawling up your legs... then a lot.. they eat your flesh and leave nothing but bone behind");
                goPoints.setText("Points: " + points);
                break;
            case 6://trapped in the cave
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.door));
                image.setImageDrawable(getResources().getDrawable(R.drawable.bones));
                goDesc.setText("The bridge has fallen down behind you and you're trapped forever!");
                goPoints.setText("Points: " + points);
                break;
            case 7://eaten by the alligator
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.castle));
                image.setImageDrawable(getResources().getDrawable(R.drawable.crock));
                goDesc.setText("You hear the guard yell something at you but you can't understand it over the screams as the aligator rips you apart");
                goPoints.setText("Points: " + points);
                break;
            case 8://too gready in the castle
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                image.setImageDrawable(getResources().getDrawable(R.drawable.zeldawalkingaway));
                goDesc.setText("Suddenly the room is upside down, you see a figure walk away muttering... failure");
                goPoints.setText("Points: " + points);
                break;
            case 9://princess kills you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                image.setImageDrawable(getResources().getDrawable(R.drawable.zelda));
                goDesc.setText("You may have been evil but you were not strong enough to defeat this princess");
                goPoints.setText("Points: " + points);
                break;
            case 10://wizard kills you
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.castle));
                image.setImageDrawable(getResources().getDrawable(R.drawable.darkwizard));
                goDesc.setText("You tried your hardest as the hero but the wizard over powered you vaporizing you with a powerful spell leaving no remains");
                goPoints.setText("Points: " + points);
                break;
            case 11://evil win
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                goDesc.setText("The princess has been defeated after taking care of the guards and executing the king you stay in the castle as its ruler");
                goPoints.setText("Points: " + points);
                break;
            case 12:// good win
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.throneroom));
                image.setImageDrawable(getResources().getDrawable(R.drawable.kingprincess));
                goDesc.setText("You were able to finally defeat the powerful wizard, You were allowed to stay in the castle and married the princess living the rest of your days out in peace");
                goPoints.setText("Points: " + points);
                break;
            case 13:// boring end
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.castle));
                image.setImageDrawable(null);
                goDesc.setText("Wow what a boring story.... you didn't take any risks or anything. geez go home and live your boring life");
                goPoints.setText("Points: " + points);
                break;
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.quitGame:
                System.exit(0);
                break;
            case R.id.mainMenu:
                Intent mainmenu = new Intent(GameOver.this, StartScreen.class);
                startActivity(mainmenu);
                break;

        }
    }
}
