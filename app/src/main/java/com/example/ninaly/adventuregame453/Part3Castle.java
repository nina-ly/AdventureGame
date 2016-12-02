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
        evilPoints = progressData.getInt("evil-points", 0);
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
            case R.id.walk:
                setContentView(R.layout.part3_walk);
                break;
            case R.id.greet:
                setContentView(R.layout.part3_greet);
                break;
            case R.id.help:
                setContentView(R.layout.part3_askhelp);
                break;
            case R.id.listen:
                if(evilPoints > 0){
                    setContentView(R.layout.part3_listenevil);
                }
                else{
                    setContentView(R.layout.part3_listengood);
                }
                break;
            case R.id.ignore:
                setContentView(R.layout.part3_ignore);
                break;
            case R.id.thinkHero1:
                setContentView(R.layout.part3_thinkhero1);
                break;
            case R.id.confidentHero:
                setContentView(R.layout.part3_confidenthero);
                break;
            case R.id.EvilHero:
                setContentView(R.layout.part3_evilhero);
                break;
            case R.id.howGirl:
                setContentView(R.layout.part3_howgirl);
                break;
            case R.id.Yield:
                setContentView(R.layout.part3_yield);
                break;
            case R.id.fightGirl:
                setContentView(R.layout.part3_fightgirl);
                break;
            case R.id.attackGirl:
                setContentView(R.layout.part3_attackgirl);
                break;
            case R.id.aftermagic:
                setContentView(R.layout.part3_aftermagic);
                break;
            case R.id.reward1:
                setContentView(R.layout.part3_reward1);
                break;
            case R.id.moreReward:
                setContentView(R.layout.part3_morereward);
                break;
            case R.id.tooMuchReward:
                setContentView(R.layout.part3_toomuchreward);
                break;
            case R.id.endNoMagic:
                setContentView(R.layout.part3_endnomagic);
                break;
            case R.id.evilFight:
                setContentView(R.layout.part3_fightherevil);
                //register shake listener
                break;
            case R.id.keepFighting:
                setContentView(R.layout.part3_attackgirl);
                break;
            case R.id.redMagic:
                setContentView(R.layout.part3_redmagic);
                break;
            case R.id.greenMagic:
                setContentView(R.layout.part3_greenmagic);
                break;
            case R.id.blueMagic:
                setContentView(R.layout.part3_bluemagic);
                break;


        }
    }

}
