package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import java.lang.reflect.Array;
import java.util.Random;

public class Part1 extends Activity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part1_intro);

        /*
        swingButton = (Button) findViewById(R.id.swing);
        askButton = (Button) findViewById(R.id.ask);



        swingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD

=======
                setContentView(R.layout.part1_take_swing);
               // Toast toast = Toast.makeText(Part1.this,"Hey! What's that for?! I'm not harmful.",Toast.LENGTH_SHORT);
               // toast.setGravity(Gravity.CENTER,0,50);
               // toast.show();
>>>>>>> refs/remotes/origin/Nina_branch
            }
        });

        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.part1_ask_fairy);
            }
        });      */

    }

    public void onClick(View view) {

        Button buttonClicked = (Button) view;

        switch (buttonClicked.getId()) {

            // From part1_intro.xml
            // Shoos fairy away
            case R.id.swing:
                setContentView(R.layout.part1_take_swing);
                break;

            // From part1_intro.xml
            // Asks fairy's purpose
            case R.id.ask1:
                setContentView(R.layout.part1_ask_fairy);
                break;

            // From part1_take_swing.xml
            // After fairy said it's not harmless, ask for its purpose
            case R.id.ask2:
                setContentView(R.layout.part1_ask_fairy);
                break;

            // From part1_walk_away.xml
            // Walk away and ignore fairy
            case R.id.startWalkingButton:
                setContentView(R.layout.part1_walk_away);
                break;

            // From part1_walk_away.xml
            // Agree to help fairy guide it home
            case R.id.okButton3:
                setContentView(R.layout.part1_help_fairy);

                // From part1_ask_fairy.xml
                // Agree to help fairy guide it home
            case R.id.okButton:
                setContentView(R.layout.part1_help_fairy);
                break;

            // From part1_help_fairy.xml
            case R.id.okButton2:
                setContentView(R.layout.part1_choosing_paths);
                break;

            // From part1_choosing_paths.xml
            case R.id.sunnyPathButton:
                setContentView(R.layout.part1_sunny_path);
                break;

            // From part1_sunny_path
            // Goes directly to part 2 after this button is clicked
            case R.id.part2Button:
                Intent intent = new Intent(Part1.this, Cave.class);
                startActivity(intent);
                break;

            // From part1_choosing_paths.xml
            case R.id.darkPathButton:
                setContentView(R.layout.part1_dark_path);
                break;

            // From part1_dark_path.xml
            case R.id.runAwayButton:
                setContentView(R.layout.part1_run_away_from_goblin);
                break;

        } //end switch
    } //end onClick


    public void onImageClick(View view) {

        ImageButton imageButtonClicked = (ImageButton) view;

        switch (imageButtonClicked.getId()) {

            case R.id.helpFairyHint:
                Toast fairyHints = Toast.makeText(Part1.this, "I will help us get home.", Toast.LENGTH_LONG);
                fairyHints.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                fairyHints.show();
                break;

            case R.id.choosingPathFairy:
                Toast choosingPath = Toast.makeText(Part1.this, "I hate the dark, but I am able to light up.", Toast.LENGTH_LONG);
                choosingPath.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                choosingPath.show();
                break;

            case R.id.darkPathFairy:
                Toast darkPath = Toast.makeText(Part1.this, "Why don't you ask him for directions?", Toast.LENGTH_LONG);
                darkPath.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                darkPath.show();
                break;

            case R.id.runAwayFairy:
                Toast runAway = Toast.makeText(Part1.this, "Tap on the sword to try to pull it out of the rock. Keep trying until you get the sword. It could be useful!", Toast.LENGTH_LONG);
                runAway.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                runAway.show();
                break;

            case R.id.swordInRock:
                counter++;

                if (counter == 13) {
                    Toast sword = Toast.makeText(Part1.this, "Success! You retrieved the sword!", Toast.LENGTH_LONG);
                    sword.setGravity(Gravity.CENTER, 0, 500);
                    sword.show();
                }

                break;

        } //end switch
    } //end onImageClick

}

