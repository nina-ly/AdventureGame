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

    }
}
