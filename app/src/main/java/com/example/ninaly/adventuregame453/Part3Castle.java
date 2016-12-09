package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Christian on 11/11/2016.
 */

public class Part3Castle extends Activity {
    public static final String Data = "Progress_Data";
    int points, evilPoints, progress, wizardHealth, princessHealth, playerHealth;
    private boolean lantern, sword, emerald, key, treasure;
    private String magic;
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
        magic = progressData.getString("magic", "none");
        progress = R.layout.part3_start;
        playerHealth = 10;
        wizardHealth = 10;
        princessHealth = 7;
        setContentView(progress);
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
                Intent gameover = new Intent(Part3Castle.this, GameOver.class);
                gameover.putExtra("gameover", 7);
                startActivity(gameover);
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
            case R.id.Yield:
                progress = R.layout.part3_yield;
                break;
            case R.id.aftermagic:
                points += 5;
                progress = R.layout.part3_aftermagic;
                break;
            case R.id.reward1:
                points += 5;
                progress = R.layout.part3_reward1;
                break;
            case R.id.moreReward:
                progress = R.layout.part3_morereward;
                break;
            case R.id.tooMuchReward:
                gameover = new Intent(Part3Castle.this, GameOver.class);
                gameover.putExtra("gameover", 8);
                startActivity(gameover);
                break;
            case R.id.evilFight:
                progress = R.layout.part3_fightherevil;
                break;
            case R.id.evilFightM:
                princessHealth = 7;
                progress = R.layout.part3_fightherevil_m;
                break;
            case R.id.redMagic:
                progress = R.layout.part3_redmagic;
                magic = "red";
                break;
            case R.id.greenMagic:
                progress = R.layout.part3_greenmagic;
                magic = "green";
                break;
            case R.id.blueMagic:
                progress = R.layout.part3_bluemagic;
                magic = "blue";
                break;
            case R.id.wizard:
                wizardHealth = 10;
                progress = R.layout.part3_wizard_appears;
                break;
            case R.id.fightM:
                progress = R.layout.part3_fight_m;
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
                toast = Toast.makeText(this, "This is very powerful against someone who is defending. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_greenmagic:
                toast = Toast.makeText(this, "This is very powerful against an enemy's physical attacks. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_bluemagic:
                toast = Toast.makeText(this, "this is very powerful against an enemy's magical attacks. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil_d:
                toast = Toast.makeText(this, "She must be scared but while she is defending we are at a stale mate. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil_m:
                toast = Toast.makeText(this, "She is readying a spell take advantage and attack. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil_p:
                toast = Toast.makeText(this, "that looks strong but slow you should defend and counter!. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fight_d:
                toast = Toast.makeText(this, "He must be scared but while he is defending we are at a stale mate. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fight_m:
                toast = Toast.makeText(this, "He is readying a spell take advantage and attack. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fight_p:
                toast = Toast.makeText(this, "that looks strong but slow you should defend and counter!. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;

        }
    }

    public void onClickFight(View view){
        switch(progress){
            case R.layout.part3_fight_d:
            case R.layout.part3_fightherevil_d:
                switch(view.getId()){
                    case R.id.Defend:
                        nextFight();
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.physicalAttack:
                        playerHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.Dodge:
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                    case R.id.DefendE:
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                    case R.id.physicalAttackE:
                        playerHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.DodgeE:
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                }
                break;
            case R.layout.part3_fight_p:
            case R.layout.part3_fightherevil_p:
                switch(view.getId()){
                    case R.id.Defend:
                        wizardHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.physicalAttack:
                        playerHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.Dodge:
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                    case R.id.DefendE:
                        princessHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.physicalAttackE:
                        playerHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.DodgeE:
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                }
                break;
            case R.layout.part3_fight_m:
            case R.layout.part3_fightherevil_m:
                switch(view.getId()){
                    case R.id.Defend:
                        playerHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.physicalAttack:
                        wizardHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.Dodge://next sequence
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                    case R.id.DefendE:
                        playerHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.physicalAttackE:
                        princessHealth--;
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        checkDeath();
                        nextFight();
                        break;
                    case R.id.DodgeE:
                        Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + princessHealth, Toast.LENGTH_LONG).show();
                        nextFight();
                        break;
                }
                break;
        }
    }

    void nextFight(){
        Random rand = new Random();
        int next = rand.nextInt(10);

        if(progress == R.layout.part3_fight_d || progress == R.layout.part3_fight_p || progress == R.layout.part3_fight_m){
            if(next <=2){
                progress = R.layout.part3_fight_d;
            }else if (next >2 && next <= 6){
                progress = R.layout.part3_fight_m;
            }else{
                progress = R.layout.part3_fight_p;
            }
        }
        else{
            if(next <=2){
                progress = R.layout.part3_fightherevil_d;
            }else if (next >2 && next <= 6){
                progress = R.layout.part3_fightherevil_m;
            }else{
                progress = R.layout.part3_fightherevil_p;
            }
        }
        setContentView(progress);
    }



    void checkDeath(){
        if(wizardHealth <= 0){
            points += 10;
            Intent gameover = new Intent(Part3Castle.this, GameOver.class);
            gameover.putExtra("gameover", 12);
            startActivity(gameover);
        }
        if(princessHealth <= 0){
            points += 10;
            Intent gameover = new Intent(Part3Castle.this, GameOver.class);
            gameover.putExtra("gameover", 11);
            startActivity(gameover);
        }
        if(playerHealth <= 0){
            Intent gameover = new Intent(Part3Castle.this, GameOver.class);
            if(wizardHealth < 10){
                gameover.putExtra("gameover", 10);
            }else{
                gameover.putExtra("gameover", 9);
            }
            startActivity(gameover);
        }
    }

}
