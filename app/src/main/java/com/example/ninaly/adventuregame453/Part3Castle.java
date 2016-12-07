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
                progress = R.layout.part3_shout;
                setContentView(R.layout.part3_shout);
                break;
            case R.id.doorbell:
                if(emerald){
                    progress = R.layout.part3_doorbell_emerald;
                    setContentView(R.layout.part3_doorbell_emerald);
                }
                else if(sword){
                    progress = R.layout.part3_doorbell_sword;
                    setContentView(R.layout.part3_doorbell_sword);
                }
                else{
                    progress = R.layout.part3_doorbell_other;
                    setContentView(R.layout.part3_doorbell_other);
                }
                break;
            case R.id.moat:
                progress = R.layout.part3_moat;
                setContentView(R.layout.part3_moat);
                break;
            case R.id.showEmerald:
                progress = R.layout.part3_emerald;
                setContentView(R.layout.part3_emerald);
                break;
            case R.id.showSword:
                progress = R.layout.part3_sword;
                setContentView(R.layout.part3_sword);
                break;
            case R.id.appeal:

                break;
            case R.id.threaten:
                progress = R.layout.part3_threaten;
                setContentView(R.layout.part3_threaten);
                break;
            case R.id.walk:
                progress = R.layout.part3_walk;
                setContentView(R.layout.part3_walk);
                break;
            case R.id.greet:
                progress = R.layout.part3_greet;
                setContentView(R.layout.part3_greet);
                break;
            case R.id.help:
                progress = R.layout.part3_askhelp;
                setContentView(R.layout.part3_askhelp);
                break;
            case R.id.listen:
                if(evilPoints > 0){
                    progress = R.layout.part3_listenevil;
                    setContentView(R.layout.part3_listenevil);
                }
                else{
                    progress = R.layout.part3_listengood;
                    setContentView(R.layout.part3_listengood);
                }
                break;
            case R.id.ignore:
                progress = R.layout.part3_ignore;
                setContentView(R.layout.part3_ignore);
                break;
            case R.id.thinkHero1:
                progress = R.layout.part3_thinkhero1;
                setContentView(R.layout.part3_thinkhero1);
                break;
            case R.id.confidentHero:
                progress = R.layout.part3_confidenthero;
                setContentView(R.layout.part3_confidenthero);
                break;
            case R.id.EvilHero:
                progress = R.layout.part3_evilhero;
                setContentView(R.layout.part3_evilhero);
                break;
            case R.id.howGirl:
                progress = R.layout.part3_howgirl;
                setContentView(R.layout.part3_howgirl);
                break;
            case R.id.Yield:
                progress = R.layout.part3_yield;
                setContentView(R.layout.part3_yield);
                break;
            case R.id.fightGirl:
                progress = R.layout.part3_fightgirl;
                setContentView(R.layout.part3_fightgirl);
                break;
            case R.id.attackGirl:
                progress = R.layout.part3_attackgirl;
                setContentView(R.layout.part3_attackgirl);
                break;
            case R.id.aftermagic:
                progress = R.layout.part3_aftermagic;
                setContentView(R.layout.part3_aftermagic);
                break;
            case R.id.reward1:
                progress = R.layout.part3_reward1;
                setContentView(R.layout.part3_reward1);
                break;
            case R.id.moreReward:
                progress = R.layout.part3_morereward;
                setContentView(R.layout.part3_morereward);
                break;
            case R.id.tooMuchReward:
                progress = R.layout.part3_toomuchreward;
                setContentView(R.layout.part3_toomuchreward);
                break;
            case R.id.endNoMagic:
                progress = R.layout.part3_endnomagic;
                setContentView(R.layout.part3_endnomagic);
                break;
            case R.id.evilFight:
                progress = R.layout.part3_fightherevil;
                setContentView(R.layout.part3_fightherevil);
                //register shake listener
                break;
            case R.id.keepFighting:
                progress = R.layout.part3_attackgirl;
                setContentView(R.layout.part3_attackgirl);
                break;
            case R.id.redMagic:
                progress = R.layout.part3_redmagic;
                setContentView(R.layout.part3_redmagic);
                break;
            case R.id.greenMagic:
                progress = R.layout.part3_greenmagic;
                setContentView(R.layout.part3_greenmagic);
                break;
            case R.id.blueMagic:
                progress = R.layout.part3_bluemagic;
                setContentView(R.layout.part3_bluemagic);
                break;


        }
    }

    public void onClickHint(View view){
        switch(progress){
            case R.layout.part3_start:
                break;
            case R.layout.part3_shout:
                break;
            case R.layout.part3_doorbell_emerald:
                break;
            case R.layout.part3_doorbell_sword:
                break;
            case R.layout.part3_doorbell_other:
                break;
            case R.layout.part3_moat:
                break;
            case R.layout.part3_emerald:
                break;
            case R.layout.part3_sword:
                break;
            case R.layout.part3_threaten:
                break;
            case R.layout.part3_walk:
                break;
            case R.layout.part3_greet:
                break;
            case R.layout.part3_askhelp:
                break;
            case R.layout.part3_listenevil:
                break;
            case R.layout.part3_listengood:
                break;
            case R.layout.part3_confidenthero:
                break;
            case R.layout.part3_thinkhero1:
                break;
            case R.layout.part3_evilhero:
                break;
            case R.layout.part3_ignore:
                break;
            case R.layout.part3_howgirl:
                break;
            case R.layout.part3_fightgirl:
                break;
            case R.layout.part3_yield:
                break;
            case R.layout.part3_aftermagic:
                break;
            case R.layout.part3_reward1:
                break;
            case R.layout.part3_morereward:
                break;
            case R.layout.part3_toomuchreward:
                break;
            case R.layout.part3_endnomagic:
                break;
            case R.layout.part3_fightherevil:
                break;
            case R.layout.part3_redmagic:
                break;
            case R.layout.part3_greenmagic:
                break;
            case R.layout.part3_bluemagic:
                break;

        }
    }

}
