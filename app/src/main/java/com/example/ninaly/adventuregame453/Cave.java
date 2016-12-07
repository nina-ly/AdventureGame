package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.FloatMath;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class Cave extends Activity implements SensorEventListener {
    public static final String Data = "Progress_Data";
    int points, evilPoints, progress;
    private boolean lantern, sword, emerald, key, treasure;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private long curTime, lastUpdate;
    private float x, y, z, last_x, last_y, last_z;
    private final static long UPDATEPERIOD = 300;
    private static final int SHAKE_THRESHOLD = 800;
    private boolean lit = false;
    SharedPreferences progressData;


    //    int[] part2Layouts = {R.layout.cavebeginning, R.layout.ogrecave, R.layout.sneakaround, R.layout.keepsneaking, R.layout.attackogrewithsword, R.layout.keepattacking, R.layout.attackogrewithoutsword, R.layout.night,
//            R.layout.sneakintothecave, R.layout.killtheogreinhissleep, R.layout.regularcave, R.layout.darkcave, R.layout.travelforwardwithoutlight, R.layout.run, R.layout.travelforwardwithlight, R.layout.key, R.layout.highupcave,
//            R.layout.throwswordtocuttherope, R.layout.jump, R.layout.changeorientation, R.layout.walkacross, R.layout.opendoor, R.layout.usekey, R.layout.picklock, R.layout.cavewithbreeze};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", R.layout.cavebeginning);
        lantern = progressData.getBoolean("lantern", true);
        sword = progressData.getBoolean("sword", true);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", true);
        treasure = progressData.getBoolean("treasure", false);
        setContentView(progress);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        curTime = lastUpdate = (long) 0.0;
        x = y = z = last_x = last_y = last_z = (float) 0.0;


    }

    public void checkIfItsNight() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(currentHour >= 18) {
            setContentView(R.layout.night);
        }
    }

    public void onClick(View view){
        switch(view.getId()) {
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
                    progress = R.layout.attackogrewithoutsword;
                    setContentView(R.layout.attackogrewithoutsword);}
                break;
            case R.id.keepattacking:
                progress = R.layout.keepattacking;
                setContentView(R.layout.keepattacking);
                checkIfItsNight();
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
                progress = R.layout.killtheogreinhissleep;
                setContentView(R.layout.killtheogreinhissleep);
                break;
            case R.id.takeit:
                progress = R.layout.regularcave;
                emerald = true;
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
                    progress = R.layout.travelforwardwithoutlight;
                    setContentView(R.layout.travelforwardwithoutlight);}
                break;
            case R.id.run:
                progress = R.layout.run;
                setContentView(R.layout.run);
                break;
            case R.id.rummage:
                progress = R.layout.key;
                setContentView(R.layout.key);
                key = true;
                break;
            case R.id.jump:
                progress = R.layout.jump;
                setContentView(R.layout.jump);
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
                progress = R.layout.picklock;
                setContentView(R.layout.picklock);
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
                    treasure = true; //add 5 points
                    break;
                }
                if(sword == true && treasure == true) {
                    iv.setImageDrawable(null);
                    tv.setText("...");
                    break;
                }
            case R.id.gotocastle:
                SharedPreferences.Editor editor = progressData.edit();

                editor.putInt("points", points);
                editor.putInt("evil-points", evilPoints);
                editor.putBoolean("lantern", lantern);
                editor.putBoolean("sword", sword);
                editor.putBoolean("emerald", emerald);
                editor.putInt("part", 3);
                editor.putInt("storyProgress", R.layout.part3_start);

                editor.commit();

                Intent startNewGame = new Intent(Cave.this, Part3Castle.class);
                startActivity(startNewGame);
                finish();
                break;
            case R.id.gameover:
                /**
                 * game over screen
                 */
                setContentView(R.layout.part1_gameover);
                break;
        }
    }

    public void onClick2(View view){
        switch(view.getId()) {
            case R.id.hint1:
                Toast toast = Toast.makeText(this, "I sense the presense of a vile being in one of the caves. -Fairy", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast.show();
                break;
            case R.id.hint2:
                Toast toast2 = Toast.makeText(this, "You can pause and resume gameplay later. -Fairy", Toast.LENGTH_LONG);
                toast2.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast2.show();
                break;
            case R.id.hint3:
                Toast toast3 = Toast.makeText(this, "He saw us, I'd recommend fighting him. -Fairy", Toast.LENGTH_LONG);
                toast3.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast3.show();
                break;
            case R.id.hint4:
                Toast toast4 = Toast.makeText(this, "My master, you have died! -Fairy", Toast.LENGTH_LONG);
                toast4.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast4.show();
                break;
            case R.id.hint5:
                Toast toast5 = Toast.makeText(this, "We're doing pretty well, lets keep going at it. -Fairy", Toast.LENGTH_LONG);
                toast5.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast5.show();
                break;
            case R.id.hint6:
                Toast toast6 = Toast.makeText(this, "Oh no we have no choice but to fight without the sword. -Fairy", Toast.LENGTH_LONG);
                toast6.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast6.show();
                break;
            case R.id.hint7:
                Toast toast7 = Toast.makeText(this, "He was just too strong for us. -Fairy", Toast.LENGTH_LONG);
                toast7.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast7.show();
                break;
            case R.id.hint8:
                Toast toast8 = Toast.makeText(this, "He seems to be asleep from 6PM to 12AM. -Fairy", Toast.LENGTH_LONG);
                toast8.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast8.show();
                break;
            case R.id.hint9:
                Toast toast9 = Toast.makeText(this, "Wow! A shiny emerald! We should take it! -Fairy", Toast.LENGTH_LONG);
                toast9.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast9.show();
                break;
            case R.id.hint10:
                Toast toast10 = Toast.makeText(this, "Sometimes you have to play smart! Let's explore! -Fairy", Toast.LENGTH_LONG);
                toast10.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast10.show();
                break;
            case R.id.hint11:
                Toast toast11 = Toast.makeText(this, "Sorry, even I don't know where to go! -Fairy", Toast.LENGTH_LONG);
                toast11.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast11.show();
                break;
            case R.id.hint12:
                if(lantern == false) {
                    Toast toast12 = Toast.makeText(this, "It's dark... Im scared! We should go back... -Fairy", Toast.LENGTH_LONG);
                    toast12.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                    toast12.show();
                }
                else {
                    Toast toast12 = Toast.makeText(this, "You have a flashlight, shake your device to turn it on! -Fairy", Toast.LENGTH_LONG);
                    toast12.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                    toast12.show();
                }
                break;
            case R.id.hint13:
                Toast toast13 = Toast.makeText(this, "This dark room is dark and creepy, lets leave! -Fairy", Toast.LENGTH_LONG);
                toast13.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast13.show();
                break;
            case R.id.hint14:
                Toast toast14 = Toast.makeText(this, "You died! It was by a spider monster. -Fairy", Toast.LENGTH_LONG);
                toast14.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast14.show();
                break;
            case R.id.hint15:
                Toast toast15 = Toast.makeText(this, "I see something shiny! -Fairy", Toast.LENGTH_LONG);
                toast15.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast15.show();
                break;
            case R.id.hint16:
                Toast toast16 = Toast.makeText(this, "What a nice find! -Fairy", Toast.LENGTH_LONG);
                toast16.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast16.show();
                break;
            case R.id.hint17:
                Toast toast17 = Toast.makeText(this, "Hmm try moving your device around to drop the bridge. -Fairy", Toast.LENGTH_LONG);
                toast17.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast17.show();
                break;
            case R.id.hint18:
                Toast toast18 = Toast.makeText(this, "Master! Why'd you go and do that?!. -Fairy", Toast.LENGTH_LONG);
                toast18.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast18.show();
                break;
            case R.id.hint19:
                Toast toast19 = Toast.makeText(this, "It was fun while it lasted. On to a new master! -Fairy", Toast.LENGTH_LONG);
                toast19.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast19.show();
                break;
            case R.id.hint20:
                Toast toast20 = Toast.makeText(this, "Good job you can cross the bridge now! -Fairy", Toast.LENGTH_LONG);
                toast20.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast20.show();
                break;
            case R.id.hint21:
                Toast toast21 = Toast.makeText(this, "Look it's a door, wonder what's behind... -Fairy", Toast.LENGTH_LONG);
                toast21.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast21.show();
                break;
            case R.id.hint22:
                Toast toast22 = Toast.makeText(this, "Do you have the skills to pick the lock? If not, use the key. -Fairy", Toast.LENGTH_LONG);
                toast22.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast22.show();
                break;
            case R.id.hint23:
                Toast toast23 = Toast.makeText(this, "Good thing I can fly. -Fairy", Toast.LENGTH_LONG);
                toast23.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast23.show();
                break;
            case R.id.hint24:
                Toast toast24 = Toast.makeText(this, "Wow what a find! -Fairy", Toast.LENGTH_LONG);
                toast24.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast24.show();
                break;
            case R.id.hint25:
                Toast toast25 = Toast.makeText(this, "Onwards to our next journey! -Fairy", Toast.LENGTH_LONG);
                toast25.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                toast25.show();
                break;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.changeorientation); //add 5 points here
    }

    protected void onResume() {
        progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", R.layout.cavebeginning);
        lantern = progressData.getBoolean("lantern", true);
        sword = progressData.getBoolean("sword", true);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", true);
        treasure = progressData.getBoolean("treasure", false);

        super.onResume();
        setContentView(progress);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
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
                if (lantern == true) {
                    rl.setBackgroundResource(R.drawable.ogrecave2);
                    tv.setText("It's bright and lit!");
                    lit = true;
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

    @Override
    protected void onPause() {

        SharedPreferences.Editor editor = progressData.edit();

        editor.putInt("points", points);
        editor.putInt("evil-points", evilPoints);
        editor.putBoolean("lantern", lantern);
        editor.putBoolean("sword", sword);
        editor.putBoolean("emerald", emerald);
        editor.putInt("part", 2);
        editor.putInt("storyProgress", progress);

        editor.commit();

        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    @Override
    protected void onDestroy() {

        SharedPreferences.Editor editor = progressData.edit();

        editor.putInt("points", points);
        editor.putInt("evil-points", evilPoints);
        editor.putBoolean("lantern", lantern);
        editor.putBoolean("sword", sword);
        editor.putBoolean("emerald", emerald);
        editor.putInt("part", 2);
        editor.putInt("storyProgress", progress);

        editor.commit();

        super.onDestroy();
    }
}
