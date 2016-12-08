package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

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
                break;
            case R.id.doorbell:
                if(emerald){
                    progress = R.layout.part3_doorbell_emerald;
                }
                else if(sword){
                    progress = R.layout.part3_doorbell_sword;
                }
                else{
                    progress = R.layout.part3_doorbell_other;
                }
                break;
            case R.id.moat:
                progress = R.layout.part3_moat;
                break;
            case R.id.showEmerald:
                progress = R.layout.part3_emerald;
                break;
            case R.id.showSword:
                progress = R.layout.part3_sword;
                break;
            case R.id.appeal:

                break;
            case R.id.threaten:
                progress = R.layout.part3_threaten;
                break;
            case R.id.walk:
                progress = R.layout.part3_walk;
                break;
            case R.id.greet:
                progress = R.layout.part3_greet;
                break;
            case R.id.help:
                progress = R.layout.part3_askhelp;
                break;
            case R.id.listen:
                if(evilPoints > 0){
                    progress = R.layout.part3_listenevil;
                }
                else{
                    progress = R.layout.part3_listengood;
                }
                break;
            case R.id.ignore:
                progress = R.layout.part3_ignore;
                break;
            case R.id.thinkHero1:
                progress = R.layout.part3_thinkhero1;
                break;
            case R.id.confidentHero:
                progress = R.layout.part3_confidenthero;
                break;
            case R.id.EvilHero:
                progress = R.layout.part3_evilhero;
                break;
            case R.id.howGirl:
                progress = R.layout.part3_howgirl;
                break;
            case R.id.Yield:
                progress = R.layout.part3_yield;
                break;
            case R.id.fightGirl:
                progress = R.layout.part3_fightgirl;
                break;
            case R.id.attackGirl:
                progress = R.layout.part3_attackgirl;
                break;
            case R.id.aftermagic:
                progress = R.layout.part3_aftermagic;
                break;
            case R.id.reward1:
                progress = R.layout.part3_reward1;
                break;
            case R.id.moreReward:
                progress = R.layout.part3_morereward;
                break;
            case R.id.tooMuchReward:
                progress = R.layout.part3_toomuchreward;
                break;
            case R.id.endNoMagic:
                progress = R.layout.part3_endnomagic;
                break;
            case R.id.evilFight:
                progress = R.layout.part3_fightherevil;
                //register shake listener
                break;
            case R.id.keepFighting:
                progress = R.layout.part3_attackgirl;
                break;
            case R.id.redMagic:
                progress = R.layout.part3_redmagic;
                break;
            case R.id.greenMagic:
                progress = R.layout.part3_greenmagic;
                break;
            case R.id.blueMagic:
                progress = R.layout.part3_bluemagic;
                break;


        }
        setContentView(progress);
    }

    public void onClickHint(View view){
        Toast toast;
        switch(progress){
            case R.layout.part3_start:
                toast = Toast.makeText(this, "Dang thats a big castle! be careful of that moat -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_shout:
                toast = Toast.makeText(this, "Seriously you tried to yell? just ring the door bell. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_doorbell_emerald:
                toast = Toast.makeText(this, "Hey did't you find an emerald? why don't you show that to him?. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_doorbell_sword:
                toast = Toast.makeText(this, "Wow this guy is having a bad day... maybe you can help him with that sword of yours. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_doorbell_other:
                toast = Toast.makeText(this, "Wow maybe we should just leave? I guess theres no harm in asking for help though. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_moat:
                toast = Toast.makeText(this, "Seriously did I not say be careful of the moat? no one ever listens to the fairy. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_emerald:
                toast = Toast.makeText(this, "See I told you he would listen. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_sword:
                toast = Toast.makeText(this, "See I told you he would listen. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_threaten:
                toast = Toast.makeText(this, "Why would you yell at the king!?. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_walk:
                toast = Toast.makeText(this, "This castle looks even bigger on the inside! oh theres the king better show some respect. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_greet:
                toast = Toast.makeText(this, "Very nice, very nice. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_askhelp:
                toast = Toast.makeText(this, "Wow the king needs our help! it would be rude not to at least hear him out. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_listenevil:
                toast = Toast.makeText(this, "Wow what a predicament, oh no whats that look in your eye? -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_listengood:
                toast = Toast.makeText(this, "Wow what a predicament, I bet we could help if we really try. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_confidenthero:
                toast = Toast.makeText(this, "Damn straight show him that you're the hero they need! -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_thinkhero1:
                toast = Toast.makeText(this, "Cmon don't sell yourself short, I've seen you do some impressive stuff. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_evilhero:
                toast = Toast.makeText(this, "of course... how'd I end up getting paired with this psycho. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_ignore:
                toast = Toast.makeText(this, "Wow you suck but I guess thats fine. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_howgirl:
                toast = Toast.makeText(this, "Hey man be careful I'm not sure you want to mess with her. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightgirl:
                toast = Toast.makeText(this, "Oh cmon don't provoke her she'll kill you for sure. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_attackgirl:
                toast = Toast.makeText(this, "I told you she was strong and now youre dead. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_yield:
                toast = Toast.makeText(this, "Smart choice! alright time to pick our magic. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_aftermagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_reward1:
                toast = Toast.makeText(this, "Alright well at least you got a reward out of it. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_morereward:
                toast = Toast.makeText(this, "Cmon don't get greedy. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_toomuchreward:
                toast = Toast.makeText(this, "Holy dang! she killed you... shoulda listened when I said walk away. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_endnomagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil:
                toast = Toast.makeText(this, "Hey I know you're an evil badass and all but be careful she's strong. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_redmagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_greenmagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_bluemagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;

        }
    }

}
