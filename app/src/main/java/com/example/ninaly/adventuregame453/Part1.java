package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Part1 extends Activity {

    private TextView textView;
    private Button askButton;
    private Button swingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.part1_intro);

        textView = (TextView) findViewById(R.id.text1);
        swingButton = (Button) findViewById(R.id.swing);
        askButton = (Button) findViewById(R.id.ask);

        textView.setText("The merchant starts walking along the road. Suddenly, a tornado picks " +
                "him up and throws him into the middle of the forest. When the dust finally " +
                "clears, he sees that he's surrounded by broken carts. Then, he hears a voice, " +
                "'Wow, that was a doozy'. A blue figure with wings appears...");

    }
}
