package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void onNewGameClick(View view){
        Toast.makeText(StartScreen.this,"New Game click", Toast.LENGTH_SHORT).show();
    }

    public void onContinueClick(View view){
        Toast.makeText(StartScreen.this,"Continue click", Toast.LENGTH_SHORT).show();

    }

    public void onExitClick(View view){
        System.exit(0);
    }
}
