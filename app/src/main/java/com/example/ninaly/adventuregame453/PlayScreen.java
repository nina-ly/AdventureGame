package com.example.ninaly.adventuregame453;

/**
 * Created by kevin on 12/8/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Calendar;
import java.util.Random;

public class PlayScreen extends Activity implements SensorEventListener{


    public static final String Data = "Progress_Data";
    private boolean lantern, sword, emerald, key, treasure;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long curTime, lastUpdate;
    private float x, y, z, last_x, last_y, last_z;
    private final static long UPDATEPERIOD = 300;
    private static final int SHAKE_THRESHOLD = 800;
    private boolean lit = false;
    private int points, evilPoints, progress, wizardHealth, princessHealth, playerHealth;
    private String magic;
    private MediaPlayer mp;
    Intent gameover;
    SharedPreferences progressData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", R.layout.part1_intro);
        playerHealth = progressData.getInt("player-health", 0);
        princessHealth = progressData.getInt("princess-health", 0);
        wizardHealth = progressData.getInt("wizard-health", 0);
        lantern = progressData.getBoolean("lantern", false);
        sword = progressData.getBoolean("sword", false);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", false);
        treasure = progressData.getBoolean("treasure", false);
        magic = progressData.getString("magic", "none");
        setContentView(progress);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        curTime = lastUpdate = (long) 0.0;
        x = y = z = last_x = last_y = last_z = (float) 0.0;
        playerHealth = 10;
        wizardHealth = 10;
        princessHealth = 7;
        mp = MediaPlayer.create(this, R.raw.forest);
        mp.start();
        mp.setLooping(true);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", R.layout.part1_intro);
        playerHealth = progressData.getInt("player-health", 0);
        princessHealth = progressData.getInt("princess-health", 0);
        wizardHealth = progressData.getInt("wizard-health", 0);
        lantern = progressData.getBoolean("lantern", false);
        sword = progressData.getBoolean("sword", false);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", false);
        treasure = progressData.getBoolean("treasure", false);
        magic = progressData.getString("magic", "none");
        if(progress == R.layout.ogrecave || progress == R.layout.sneakaround || progress == R.layout.keepsneaking || progress == R.layout.attackogrewithsword){
            progress = R.layout.night;
        }
        setContentView(progress);
        mp.start();
        mp.setLooping(true);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        SharedPreferences point = getSharedPreferences(Data,0);
        SharedPreferences.Editor editor = point.edit();

        editor.putInt("points", points);
        editor.putInt("evilPoints", evilPoints);
        editor.putInt("player-health", playerHealth);
        editor.putInt("princess-health", princessHealth);
        editor.putInt("wizard-health", wizardHealth);
        editor.putBoolean("lantern", lantern);
        editor.putBoolean("sword", sword);
        editor.putBoolean("emerald", emerald);
        editor.putBoolean("key", key);
        editor.putBoolean("treasure", treasure);
        editor.putString("magic", magic);
        editor.putInt("storyProgress", progress);

        editor.commit();
        super.onPause();
        mp.stop();
        mSensorManager.unregisterListener(this);

    }

    public void checkIfItsNight() {//Kevin Nguyen
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(currentHour >= 18) {
            setContentView(R.layout.night);
        }
    }

    public void onClick(View view) {//Nina Ly
//        SharedPreferences.Editor editor = progressData.edit();


        switch (view.getId()) {

            // From part1_intro.xml
            // Shoos fairy away
            case R.id.swing:
                evilPoints++;
                progress = R.layout.part1_take_swing;
                break;

            // From part1_intro.xml
            // Asks fairy's purpose
            case R.id.ask1:
                progress = R.layout.part1_ask_fairy;
                break;

            // From part1_take_swing.xml
            // After fairy said it's not harmless, ask for its purpose
            case R.id.ask2:
                progress = R.layout.part1_ask_fairy;
                break;

            // From part1_walk_away.xml
            // Walk away and ignore fairy
            case R.id.startWalkingButton:
                progress = R.layout.part1_walk_away;
                break;

            // From part1_walk_away.xml
            // Agree to help fairy guide it home
            case R.id.okButton3:
                progress = R.layout.part1_help_fairy;

                // From part1_ask_fairy.xml
                // Agree to help fairy guide it home
            case R.id.okButton:
                progress = R.layout.part1_help_fairy;
                break;

            // From part1_help_fairy.xml
            case R.id.okButton2:
                progress = R.layout.part1_choosing_paths;
                break;

            // From part1_choosing_paths.xml
            case R.id.sunnyPathButton:
                progress = R.layout.part1_sunny_path;
                break;

            // From part1_sunny_path
            // Goes directly to part 2 after this button is clicked
            case R.id.part2Button:

//                editor.putInt("points", points);
//                editor.putInt("evil-points", evilPoints);
//                editor.putBoolean("lantern", lantern);
//                editor.putBoolean("sword", sword);
//                editor.putBoolean("emerald", emerald);
//                editor.putInt("part", 2);
//                editor.putInt("storyProgress", R.layout.cavebeginning);
//
//                editor.commit();
                progress = R.layout.cavebeginning;
                break;

            // From part1_choosing_paths.xml
            case R.id.darkPathButton:
                progress = R.layout.part1_dark_path;
                break;

            // From part1_dark_path.xml
            case R.id.runAwayButton:
                progress = R.layout.part1_run_away_from_goblin;
                break;

            // From part1_run_away_from_goblin.xml
            case R.id.pullSwordButton:
                progress = R.layout.part1_sword;
                break;

            // From part1_dark_path.xml
            case R.id.sayHelloButton:
                progress = R.layout.part1_say_hello;
                break;

            // From part1_say_hello.xml (spanish xml)
            case R.id.attackHim:
                evilPoints++;
                points +=5;
                progress = R.layout.part1_attack_goblin;
                break;

            // From part1_say_hello.xml
            case R.id.yesHelp:
                lantern = true;
                points+=5;
                progress = R.layout.part1_goblin_help;
                break;

            // From part1_dark_path.xml
            case R.id.attackButton:
                evilPoints++;
                points +=5;
                progress = R.layout.part1_attack_goblin;
                break;

            // From part1_say_hello.xml
            case R.id.attackGoblin:
                evilPoints++;
                points +=5;
                progress = R.layout.part1_attack_goblin;
                break;

            // From part1_say_hello.xml
            case R.id.leaveButton:
                progress = R.layout.part1_sword;
                break;

            // From part1_say_hello.xml
            case R.id.nodButton:
                progress = R.layout.part1_nod_globin;
                break;

            // From part1_attack_globin.xml
            case R.id.leaveNowButton:
                progress = R.layout.part1_leave_after_finding_gold;
                break;

            // From part1_attack_globin.xml
            case R.id.keepLookingButton:
                progress = R.layout.part1_keep_looking;
                break;

            // From part1_attack_globin.xml
            case R.id.followPath:
                progress = R.layout.part1_sword;
                break;

            // From part1_keep_looking.xml
            case R.id.keepLookingButton2:
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 1);
                startActivity(gameover);
                finish();
                break;

            // From part1_keep_looking.xml
            case R.id.leaveNowButton2:
                progress = R.layout.part1_sword;
                break;

//            // From part1_keep_looking_again.xml
//            case R.id.gameOver:
//                Intent gameover = new Intent(PlayScreen.this, GameOver.class);
//                gameover.putExtra("gameover", 7);
//                startActivity(gameover);
//                break;
//
//            // From part1_gameover.xml
//            case R.id.mainMenu:
//                progress = R.layout.activity_start_screen;
//                break;
//
//            case R.id.quitGame:
//                System.exit(0);
//
//                // From part1_goblin_help.xml
            case R.id.swordHelpFromGoblin:
                progress = R.layout.part1_sword;
                break;

            // From part1_sword.xml
            case R.id.pullSword:
                progress = R.layout.part1_pull_sword;
                break;

            // From part1_sword.xml
            case R.id.readInscription:
                progress = R.layout.part1_read_inscription;
                break;

            // From part1_sword.xml
            case R.id.keepWalking:
                progress = R.layout.part1_keep_walking;
                break;

            // From part1_pull_sword.xml
            case R.id.readInscription1:
                progress = R.layout.part1_read_inscription;
                break;

            // From part1_read_inscription.xml
            case R.id.pullSword1:

                progress = R.layout.part1_pull_sword2;
                break;

            // From part1_read_inscription.xml
            case R.id.leaveInscription:
                progress = R.layout.part1_leave_inscription;
                break;

            // From part1_keep_walking.xml
            case R.id.part2Butt:
//                editor = progressData.edit();
//
//                editor.putInt("points", points);
//                editor.putInt("evil-points", evilPoints);
//                editor.putBoolean("lantern", lantern);
//                editor.putBoolean("sword", sword);
//                editor.putBoolean("emerald", emerald);
//                editor.putInt("part", 2);
//                editor.putInt("storyProgress", R.layout.cavebeginning);
                progress = R.layout.cavebeginning;
//                editor.commit();
                break;

            // From part1_leave_inscription.xml
            case R.id.goToPart2:
//                editor = progressData.edit();
//
//                editor.putInt("points", points);
//                editor.putInt("evil-points", evilPoints);
//                editor.putBoolean("lantern", lantern);
//                editor.putBoolean("sword", sword);
//                editor.putBoolean("emerald", emerald);
//                editor.putInt("part", 2);
//                editor.putInt("storyProgress", R.layout.cavebeginning);
//
//                editor.commit();
                progress = R.layout.cavebeginning;
//                Intent partdos = new Intent(PlayScreen.this, PlayScreen.class);
//                startActivity(partdos);
                break;

            // From part1_pull_sword2.xml
            case R.id.takeTheSword:

                if(evilPoints == 0) {
                    sword = true;
                    points +=5;
                    progress = R.layout.part1_leave_inscription;
                    break;
                }
                else if(evilPoints == 1) {
                    progress = R.layout.part1_punishment1;
                    break;
                }
                else if(evilPoints == 2) {
                    gameover = new Intent(PlayScreen.this, GameOver.class);
                    gameover.putExtra("gameover", 2);
                    startActivity(gameover);
                    finish();
                    break;
                }

                // From part1_punishment1.xml
            case R.id.lightningStrikes:
                progress = R.layout.part1_keep_walking;
                break;



//            // From part1_punishment2.xml
//            case R.id.gameOverButton:
//                gameover = new Intent(PlayScreen.this, GameOver.class);
//                gameover.putExtra("gameover", 7);
//                startActivity(gameover);
//                break;
            //beginning of part 3 by Christian Gumacal
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
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 7);
                startActivity(gameover);
                finish();
                break;
            case R.id.showEmerald:
                progress = R.layout.part3_emerald;
                break;
            case R.id.showSword:
                progress = R.layout.part3_sword;
                break;
            case R.id.appeal:
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 13);
                startActivity(gameover);
                finish();
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
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 13);
                startActivity(gameover);
                finish();
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
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 8);
                startActivity(gameover);
                finish();
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
                mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                progress = R.layout.part3_fight_m;
                break;

        } //end switch
        setContentView(progress);
    } //end onClick

    public void onClickCave(View view){//Part 2 Cave by Kevin Nguyen
        switch(view.getId()){
            case R.id.ogrecave:
                progress = R.layout.ogrecave;
                setContentView(R.layout.ogrecave);
                checkIfItsNight();
                break;
            case R.id.regularcave:
                progress = R.layout.regularcave;
                setContentView(R.layout.regularcave);
                Button button = (Button) findViewById(R.id.highupcave);
                break;
            case R.id.sneakaround:
                progress = R.layout.sneakaround;
                setContentView(R.layout.sneakaround);
                checkIfItsNight();
                break;
            case R.id.keepsneaking:
                progress = R.layout.keepattacking;
                setContentView(R.layout.keepsneaking);
                checkIfItsNight();
                break;
            case R.id.attackogre:
                checkIfItsNight();
                if(sword == true) {
                    progress = R.layout.attackogrewithsword;
                    setContentView(R.layout.attackogrewithsword);}
                else {
                    gameover = new Intent(PlayScreen.this, GameOver.class);
                    gameover.putExtra("gameover", 3);
                    startActivity(gameover);}
                    finish();
                break;
            case R.id.keepattacking:
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 3);
                startActivity(gameover);
                finish();
                break;
            case R.id.giveup:
                progress = R.layout.regularcave;
                setContentView(R.layout.regularcave);
                break;
            case R.id.sneakintocave:
                progress = R.layout.sneakintothecave;
                setContentView(R.layout.sneakintothecave);
                break;
            case R.id.killogre:
                evilPoints++;
                progress = R.layout.killtheogreinhissleep;
                setContentView(R.layout.killtheogreinhissleep);
                break;
            case R.id.takeit:
                progress = R.layout.regularcave;
                emerald = true;
                points+=5;
                setContentView(R.layout.regularcave);
                break;
            case R.id.leaveit:
                progress = R.layout.regularcave;
                setContentView(R.layout.regularcave);
                break;
            case R.id.checkthecave:
                progress = R.layout.sneakintothecave;
                setContentView(R.layout.sneakintothecave);
                break;
            case R.id.leave:
                progress = R.layout.regularcave;
                setContentView(R.layout.regularcave);
                break;
            case R.id.darkcave:
                progress = R.layout.darkcave;
                setContentView(R.layout.darkcave);
                mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.highupcave:
                progress = R.layout.highupcave;
                setContentView(R.layout.highupcave);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                Button throwsword = (Button) findViewById(R.id.throwsword);
                if(sword == false)
                    throwsword.setEnabled(false);
                break;
            case R.id.cavewithabreeze:
                progress = R.layout.cavewithbreeze;
                setContentView(R.layout.cavewithbreeze);
                break;
            case R.id.goback:
                progress = R.layout.regularcave;
                setContentView(R.layout.regularcave);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.travelforward:
                if(lit == true) {
                    progress = R.layout.travelforwardwithlight;
                    setContentView(R.layout.travelforwardwithlight);}
                else {
                    gameover = new Intent(PlayScreen.this, GameOver.class);
                    gameover.putExtra("gameover", 5);
                    startActivity(gameover);
                    finish();
                }
                break;
            case R.id.run:
                progress = R.layout.run;
                setContentView(R.layout.run);
                break;
            case R.id.rummage:
                progress = R.layout.key;
                setContentView(R.layout.key);
                points+=5;
                key = true;
                break;
            case R.id.jump:
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 4);
                startActivity(gameover);
                finish();
                break;
            case R.id.throwsword:
                sword = false;
                progress = R.layout.throwswordtocuttherope;
                setContentView(R.layout.throwswordtocuttherope);
                break;
            case R.id.walkacross:
                progress = R.layout.walkacross;
                setContentView(R.layout.walkacross);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case R.id.opendoor:
                progress = R.layout.opendoor;
                setContentView(R.layout.opendoor);
                Button bkey = (Button) findViewById(R.id.usekey);
                if(key == false)
                    bkey.setEnabled(false);
                break;
            case R.id.picklock:
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 6);
                startActivity(gameover);
                finish();
                break;
            case R.id.usekey:
                progress = R.layout.usekey;
                setContentView(R.layout.usekey);
                ImageView iv = (ImageView) findViewById(R.id.treasure);
                TextView tv = (TextView) findViewById(R.id.text2);
                if((sword == false && treasure == false) || (sword == false && treasure == true)) {
                    iv.setImageResource(R.drawable.sword2);
                    tv.setText("You stumble upon a sword.");
                    sword = true;
                    break;
                }
                if(treasure == false && sword == true) {
                    points+=5;
                    treasure = true;
                    break;
                }
                if(sword == true && treasure == true) {
                    iv.setImageDrawable(null);
                    tv.setText("...");
                    break;
                }

            case R.id.gotocastle:
                progress = R.layout.part3_start;
                setContentView(progress);
                break;
            case R.id.gameover:
                gameover = new Intent(PlayScreen.this, GameOver.class);
                gameover.putExtra("gameover", 7);
                startActivity(gameover);
                finish();
                setContentView(progress);
                break;
        }
    }

    public void onImageClick(View view) {

        ImageButton imageButtonClicked = (ImageButton) view;

        switch (imageButtonClicked.getId()) {//hints for part 1 by Nina Ly

            case R.id.helpFairyHint:
                Toast fairyHints = Toast.makeText(PlayScreen.this, "I will help us get home.", Toast.LENGTH_LONG);
                fairyHints.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                fairyHints.show();
                break;

            case R.id.choosingPathFairy:
                Toast choosingPath = Toast.makeText(PlayScreen.this, "I hate the dark, but I am able to light up.", Toast.LENGTH_LONG);
                choosingPath.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                choosingPath.show();
                break;

            case R.id.darkPathFairy:
                Toast darkPath = Toast.makeText(PlayScreen.this, "Why don't you ask him for directions?", Toast.LENGTH_LONG);
                darkPath.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                darkPath.show();
                break;

            case R.id.runAwayFairy:
                Toast runAway = Toast.makeText(PlayScreen.this, "Tap on the sword to try to pull it out of the rock. Keep trying until you get the sword. It could be useful!", Toast.LENGTH_LONG);
                runAway.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                runAway.show();
                break;

            case R.id.attackGoblinFairy:
                Toast jumpHim = Toast.makeText(PlayScreen.this, "I think I heard something... we should get out of here.", Toast.LENGTH_LONG);
                jumpHim.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                jumpHim.show();
                break;

            case R.id.keepLookingFairy:
                Toast kl = Toast.makeText(PlayScreen.this, "Seriously.. we should leave now!", Toast.LENGTH_LONG);
                kl.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                kl.show();
                break;

            case R.id.keepLookingFairy2:
                Toast kla = Toast.makeText(PlayScreen.this, "I told you we should've left! It's too late now.", Toast.LENGTH_LONG);
                kla.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                kla.show();
                break;

            case R.id.leaveAfter:
                Toast leave = Toast.makeText(PlayScreen.this, "Oooo trying pulling the sword.", Toast.LENGTH_LONG);
                leave.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                leave.show();
                break;

            case R.id.spanishFairy:
                Toast spanish = Toast.makeText(PlayScreen.this, "Is he speaking in Spanish? We need to learn spanish to understand. Change the language settings on your phone to Spanish to get the English translation. Pointing is rude so don't upset him.", Toast.LENGTH_LONG);
                spanish.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                spanish.show();
                break;

            case R.id.angryGoblin:
                Toast angry = Toast.makeText(PlayScreen.this, "Uh oh, your pointing upsetted him. Do you still want to look around?", Toast.LENGTH_LONG);
                angry.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                angry.show();
                break;

            case R.id.settingButton:
                startActivityForResult(new Intent(Settings.ACTION_LOCALE_SETTINGS),0);
                break;

            case R.id.goblinHelp:
                Toast help = Toast.makeText(PlayScreen.this, "The goblin was very friendly. Let's follow the path.", Toast.LENGTH_LONG);
                help.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                help.show();
                break;

            case R.id.swordFairy:
                Toast sword = Toast.makeText(PlayScreen.this, "Hmm.... trying pulling the sword?", Toast.LENGTH_LONG);
                sword.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                sword.show();
                break;

            case R.id.pullSwordFairy:
                Toast fairy = Toast.makeText(PlayScreen.this, "Try reading the inscription.", Toast.LENGTH_LONG);
                fairy.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                fairy.show();
                break;

            case R.id.readInscriptionFairy:
                Toast rf = Toast.makeText(PlayScreen.this, "Have you done anything bad lately? Maybe we should go.", Toast.LENGTH_LONG);
                rf.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                rf.show();
                break;

            case R.id.walkAwayFairy:
                Toast wa = Toast.makeText(PlayScreen.this, "Yay!!!!", Toast.LENGTH_LONG);
                wa.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                wa.show();
                break;

            case R.id.pullSwordFairy2:
                Toast ps = Toast.makeText(PlayScreen.this, "If you didn't attack the goblin or if you didn't shoo me away, leave with the sword." +
                        "If you did attack the goblin or shoo me away, you are about to experience karma.", Toast.LENGTH_LONG);
                ps.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                ps.show();
                break;

            case R.id.leaveFairy:
                Toast leave1 = Toast.makeText(PlayScreen.this, "I think we're almost home!", Toast.LENGTH_LONG);
                leave1.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                leave1.show();
                break;

            case R.id.punishment1:
                Toast p1 = Toast.makeText(PlayScreen.this, "RUN! RUN!", Toast.LENGTH_LONG);
                p1.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                p1.show();
                break;

            case R.id.punishment2:
                Toast p2 = Toast.makeText(PlayScreen.this, "Game over :(", Toast.LENGTH_LONG);
                p2.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                p2.show();
                break;

        } //end switch
    } //end onImageClick

    public void onClick2(View view){// hints for part 2 by Kevin Nguyen
        switch(view.getId()) {
            case R.id.hint1:
                Toast toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. ", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.id.hint2:
                Toast toast2 = Toast.makeText(this, "You can pause and resume gameplay later. ", Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast2.show();
                break;
            case R.id.hint3:
                Toast toast3 = Toast.makeText(this, "He saw us, I'd recommend fighting him. ", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast3.show();
                break;
            case R.id.hint4:
                Toast toast4 = Toast.makeText(this, "My master, you have died! ", Toast.LENGTH_LONG);
                toast4.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast4.show();
                break;
            case R.id.hint5:
                Toast toast5 = Toast.makeText(this, "We're doing pretty well, lets keep going at it. ", Toast.LENGTH_LONG);
                toast5.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast5.show();
                break;
            case R.id.hint6:
                Toast toast6 = Toast.makeText(this, "Oh no we have no choice but to fight without the sword. ", Toast.LENGTH_LONG);
                toast6.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast6.show();
                break;
            case R.id.hint7:
                Toast toast7 = Toast.makeText(this, "He was just too strong for us. ", Toast.LENGTH_LONG);
                toast7.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast7.show();
                break;
            case R.id.hint8:
                Toast toast8 = Toast.makeText(this, "He seems to be asleep from 6PM to 12AM. ", Toast.LENGTH_LONG);
                toast8.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast8.show();
                break;
            case R.id.hint9:
                Toast toast9 = Toast.makeText(this, "Wow! A shiny emerald! We should take it! ", Toast.LENGTH_LONG);
                toast9.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast9.show();
                break;
            case R.id.hint10:
                Toast toast10 = Toast.makeText(this, "Sometimes you have to play smart! Let's explore! ", Toast.LENGTH_LONG);
                toast10.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast10.show();
                break;
            case R.id.hint11:
                Toast toast11 = Toast.makeText(this, "Sorry, even I don't know where to go! ", Toast.LENGTH_LONG);
                toast11.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast11.show();
                break;
            case R.id.hint12:
                if(lantern == false) {
                    Toast toast12 = Toast.makeText(this, "It's dark... Im scared! We should go back... ", Toast.LENGTH_LONG);
                    toast12.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                    toast12.show();
                }
                else {
                    Toast toast12 = Toast.makeText(this, "You have a flashlight, shake your device to turn it on! ", Toast.LENGTH_LONG);
                    toast12.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                    toast12.show();
                }
                break;
            case R.id.hint13:
                Toast toast13 = Toast.makeText(this, "This dark room is dark and creepy, lets leave! ", Toast.LENGTH_LONG);
                toast13.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast13.show();
                break;
            case R.id.hint14:
                Toast toast14 = Toast.makeText(this, "You died! It was by a spider monster. ", Toast.LENGTH_LONG);
                toast14.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast14.show();
                break;
            case R.id.hint15:
                Toast toast15 = Toast.makeText(this, "I see something shiny! ", Toast.LENGTH_LONG);
                toast15.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast15.show();
                break;
            case R.id.hint16:
                Toast toast16 = Toast.makeText(this, "What a nice find! ", Toast.LENGTH_LONG);
                toast16.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast16.show();
                break;
            case R.id.hint17:
                Toast toast17 = Toast.makeText(this, "Hmm try moving your device around to drop the bridge. ", Toast.LENGTH_LONG);
                toast17.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast17.show();
                break;
            case R.id.hint18:
                Toast toast18 = Toast.makeText(this, "Master! Why'd you go and do that?!. ", Toast.LENGTH_LONG);
                toast18.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast18.show();
                break;
            case R.id.hint19:
                Toast toast19 = Toast.makeText(this, "It was fun while it lasted. On to a new master! ", Toast.LENGTH_LONG);
                toast19.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast19.show();
                break;
            case R.id.hint20:
                Toast toast20 = Toast.makeText(this, "Good job you can cross the bridge now! ", Toast.LENGTH_LONG);
                toast20.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast20.show();
                break;
            case R.id.hint21:
                Toast toast21 = Toast.makeText(this, "Look it's a door, wonder what's behind... ", Toast.LENGTH_LONG);
                toast21.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast21.show();
                break;
            case R.id.hint22:
                Toast toast22 = Toast.makeText(this, "Do you have the skills to pick the lock? If not, use the key. ", Toast.LENGTH_LONG);
                toast22.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast22.show();
                break;
            case R.id.hint23:
                Toast toast23 = Toast.makeText(this, "Good thing I can fly. ", Toast.LENGTH_LONG);
                toast23.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast23.show();
                break;
            case R.id.hint24:
                Toast toast24 = Toast.makeText(this, "Wow what a find! ", Toast.LENGTH_LONG);
                toast24.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast24.show();
                break;
            case R.id.hint25:
                Toast toast25 = Toast.makeText(this, "Onwards to our next journey! ", Toast.LENGTH_LONG);
                toast25.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast25.show();
                break;
        }
    }

    public void onClickHint(View view){// part 3 hints by Christian Gumacal
        Toast toast;
        switch(progress){
            case R.layout.part3_start:
                toast = Toast.makeText(this, "Dang thats a big castle! be careful of that moat", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_shout:
                toast = Toast.makeText(this, "Seriously you tried to yell? just ring the door bell.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_doorbell_emerald:
                toast = Toast.makeText(this, "Hey did't you find an emerald? why don't you show that to him?", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_doorbell_sword:
                toast = Toast.makeText(this, "Wow this guy is having a bad day... maybe you can help him with that sword of yours", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_doorbell_other:
                toast = Toast.makeText(this, "Wow maybe we should just leave? I guess theres no harm in asking for help though.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_moat:
                toast = Toast.makeText(this, "Seriously did I not say be careful of the moat? no one ever listens to the fairy.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_emerald:
                toast = Toast.makeText(this, "See I told you he would listen.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_sword:
                toast = Toast.makeText(this, "See I told you he would listen.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_threaten:
                toast = Toast.makeText(this, "Why would you yell at the king!?.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_walk:
                toast = Toast.makeText(this, "This castle looks even bigger on the inside! oh theres the king better show some respect.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_greet:
                toast = Toast.makeText(this, "Very nice, very nice.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_askhelp:
                toast = Toast.makeText(this, "Wow the king needs our help! it would be rude not to at least hear him out.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_listenevil:
                toast = Toast.makeText(this, "Wow what a predicament, oh no whats that look in your eye?", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_listengood:
                toast = Toast.makeText(this, "Wow what a predicament, I bet we could help if we really try.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_confidenthero:
                toast = Toast.makeText(this, "Damn straight show him that you're the hero they need!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_thinkhero1:
                toast = Toast.makeText(this, "Cmon don't sell yourself short, I've seen you do some impressive stuff.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_evilhero:
                toast = Toast.makeText(this, "of course... how'd I end up getting paired with this psycho.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_ignore:
                toast = Toast.makeText(this, "Wow you suck but I guess thats fine.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_attackgirl:
                toast = Toast.makeText(this, "I told you she was strong and now youre dead.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_yield:
                toast = Toast.makeText(this, "Smart choice! alright time to pick our magic.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_aftermagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_reward1:
                toast = Toast.makeText(this, "Alright well at least you got a reward out of it.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_morereward:
                toast = Toast.makeText(this, "Cmon don't get greedy.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_toomuchreward:
                toast = Toast.makeText(this, "Holy dang! she killed you... shoulda listened when I said walk away.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_endnomagic:
                toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil:
                toast = Toast.makeText(this, "Hey I know you're an evil badass and all but be careful she's strong.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_redmagic:
                toast = Toast.makeText(this, "This is very powerful against someone who is defending.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_greenmagic:
                toast = Toast.makeText(this, "This is very powerful against an enemy's physical attacks.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_bluemagic:
                toast = Toast.makeText(this, "this is very powerful against an enemy's magical attacks.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil_d:
                toast = Toast.makeText(this, "She must be scared but while she is defending we are at a stale mate.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil_m:
                toast = Toast.makeText(this, "She is readying a spell take advantage and attack.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fightherevil_p:
                toast = Toast.makeText(this, "that looks strong but slow you should defend and counter!.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fight_d:
                toast = Toast.makeText(this, "He must be scared but while he is defending we are at a stale mate.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fight_m:
                toast = Toast.makeText(this, "He is readying a spell take advantage and attack.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_fight_p:
                toast = Toast.makeText(this, "that looks strong but slow you should defend and counter!.", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.layout.part3_wizard_appears:
                toast = Toast.makeText(this, "Be careful he seems strong!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;

        }
    }

    public void onClickFight(View view){//fight sequence by Christian Gumacal
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

    void nextFight(){//next random sequence
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



    void checkDeath(){//check death
        if(wizardHealth <= 0){
            points += 10;
            gameover = new Intent(PlayScreen.this, GameOver.class);
            gameover.putExtra("gameover", 12);
            startActivity(gameover);
            finish();
        }
        if(princessHealth <= 0){
            points += 10;
            gameover = new Intent(PlayScreen.this, GameOver.class);
            gameover.putExtra("gameover", 11);
            startActivity(gameover);
            finish();
        }
        if(playerHealth <= 0){
            gameover = new Intent(PlayScreen.this, GameOver.class);
            if(wizardHealth < 10){
                gameover.putExtra("gameover", 10);
            }else{
                gameover.putExtra("gameover", 9);
            }
            startActivity(gameover);
            finish();
        }
    }
    
    public void onConfigurationChanged(Configuration newConfig) {//orientation change for part 2 by Kevin
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.changeorientation); //add 5 points here
    }

    @Override
    public void onSensorChanged(SensorEvent event) {//Shake sensors for part 2 and 3 used by Kevin and Christian 
        curTime = System.currentTimeMillis();

        if ((curTime - lastUpdate) > UPDATEPERIOD) {
            long diffTime = (curTime - lastUpdate);
            lastUpdate = curTime;

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

            if (speed > SHAKE_THRESHOLD) {
//                Toast toast = Toast.makeText(this, "shook", Toast.LENGTH_SHORT);
//                toast.show();
                RelativeLayout rl = (RelativeLayout) findViewById(R.id.dc);
                TextView tv = (TextView) findViewById(R.id.text1);
                if (progress == R.layout.darkcave && lantern == true) {
                    rl.setBackgroundResource(R.drawable.ogrecave2);
                    tv.setText("It's bright and lit!");
                    lit = true;
                }

                if(progress == R.layout.part3_fight_d && magic == "red"){
                    wizardHealth-=2;
                    Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                    checkDeath();
                    nextFight();
                }
                if(progress == R.layout.part3_fight_m && magic == "blue"){
                    wizardHealth-=2;
                    Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                    checkDeath();
                    nextFight();
                }
                if(progress == R.layout.part3_fight_p && magic == "green"){
                    wizardHealth-=2;
                    Toast.makeText(this, "Player Health:" + playerHealth + " Enemy Health: " + wizardHealth, Toast.LENGTH_LONG).show();
                    checkDeath();
                    nextFight();
                }



                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}

