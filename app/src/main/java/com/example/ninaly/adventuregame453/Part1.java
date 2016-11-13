package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import java.lang.reflect.Array;

public class Part1 extends Activity {


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
                setContentView(R.layout.part1_take_swing);
               // Toast toast = Toast.makeText(Part1.this,"Hey! What's that for?! I'm not harmful.",Toast.LENGTH_SHORT);
               // toast.setGravity(Gravity.CENTER,0,50);
               // toast.show();
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

       switch(buttonClicked.getId()) {

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

           // From part1_ask_fairy.xml
           // Agree to help fairy guide it home
           case R.id.okButton:
               setContentView(R.layout.part1_help_fairy);
               break;

           // From part1_help_fairy.xml
           case R.id.okButton2:
               setContentView(R.layout.part1_choosing_paths);
               break;
       }
    }
}
