package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Christian on 11/11/2016.
 */

public class Part3Castle extends Activity {
    public static final String Data = "Progress_Data";
    int points, evilPoints, progress;
    private boolean lantern, sword, emerald, key, treasure;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long curTime, lastUpdate;
    private float x, y, z, last_x, last_y, last_z;
    private final static long UPDATEPERIOD = 300;
    private static final int SHAKE_THRESHOLD = 800;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", 0);
        lantern = progressData.getBoolean("lantern", true);
        sword = progressData.getBoolean("sword", true);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", true);
        treasure = progressData.getBoolean("treasure", false);
        setContentView(R.layout.part3_start);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        curTime = lastUpdate = (long) 0.0;
        x = y = z = last_x = last_y = last_z = (float) 0.0;
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.shoutLoud:
                setContentView(R.layout.part3_shout);
                break;
            case R.id.doorbell:
                if(emerald){
                    setContentView(R.layout.part3_doorbell_emerald);
                }
                else if(sword){
                    setContentView(R.layout.part3_doorbell_sword);
                }
                else{
                    setContentView(R.layout.part3_doorbell_other);
                }
                break;
            case R.id.moat:
                setContentView(R.layout.part3_moat);
                break;
            case R.id.showEmerald:
                setContentView(R.layout.part3_emerald);
                break;
            case R.id.showSword:
                setContentView(R.layout.part3_sword);
                break;
            case R.id.appeal:

                break;
            case R.id.threaten:
                setContentView(R.layout.part3_threaten);
                break;
        }
    }

}
