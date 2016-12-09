package com.example.ninaly.adventuregame453;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;

import java.lang.reflect.Array;
import java.util.Random;

public class Part1 extends Activity {

    int counter = 0;

    public static final String Data = "Progress_Data";
    int points, evilPoints, progress;
    private boolean lantern, sword, emerald, key, treasure;
    SharedPreferences progressData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressData = getSharedPreferences(Data, 0);
        points = progressData.getInt("points", 0);
        evilPoints = progressData.getInt("evilPoints", 0);
        progress = progressData.getInt("storyProgress", R.layout.part1_intro);
        lantern = progressData.getBoolean("lantern", true);
        sword = progressData.getBoolean("sword", true);
        emerald = progressData.getBoolean("emerald", false);
        key = progressData.getBoolean("key", true);
        treasure = progressData.getBoolean("treasure", false);

        setContentView(R.layout.part1_intro);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {

        SharedPreferences.Editor editor = progressData.edit();

        editor.putInt("points", points);
        editor.putInt("evil-points", evilPoints);
        editor.putBoolean("lantern", lantern);
        editor.putBoolean("sword", sword);
        editor.putBoolean("emerald", emerald);
        editor.putInt("part", 1);
        editor.putInt("storyProgress", progress);

        editor.commit();

        super.onPause();

    }

    public void onClick(View view) {

        Button buttonClicked = (Button) view;
        SharedPreferences.Editor editor = progressData.edit();


        switch (buttonClicked.getId()) {

            // From part1_intro.xml
            // Shoos fairy away
            case R.id.swing:
                evilPoints++;
                setContentView(R.layout.part1_take_swing);
                break;

            // From part1_intro.xml
            // Asks fairy's purpose
            case R.id.ask1:
                progress = R.layout.part1_ask_fairy;
                setContentView(R.layout.part1_ask_fairy);
                break;

            // From part1_take_swing.xml
            // After fairy said it's not harmless, ask for its purpose
            case R.id.ask2:
                setContentView(R.layout.part1_ask_fairy);
                break;

            // From part1_walk_away.xml
            // Walk away and ignore fairy
            case R.id.startWalkingButton:
                setContentView(R.layout.part1_walk_away);
                break;

            // From part1_walk_away.xml
            // Agree to help fairy guide it home
            case R.id.okButton3:
                setContentView(R.layout.part1_help_fairy);

                // From part1_ask_fairy.xml
                // Agree to help fairy guide it home
            case R.id.okButton:
                setContentView(R.layout.part1_help_fairy);
                break;

            // From part1_help_fairy.xml
            case R.id.okButton2:
                setContentView(R.layout.part1_choosing_paths);
                break;

            // From part1_choosing_paths.xml
            case R.id.sunnyPathButton:
                setContentView(R.layout.part1_sunny_path);
                break;

            // From part1_sunny_path
            // Goes directly to part 2 after this button is clicked
            case R.id.part2Button:

                editor.putInt("points", points);
                editor.putInt("evil-points", evilPoints);
                editor.putBoolean("lantern", lantern);
                editor.putBoolean("sword", sword);
                editor.putBoolean("emerald", emerald);
                editor.putInt("part", 2);
                editor.putInt("storyProgress", R.layout.cavebeginning);

                editor.commit();
                progress = R.layout.cavebeginning;
                Intent intent = new Intent(Part1.this, Cave.class);
                startActivity(intent);
                break;

            // From part1_choosing_paths.xml
            case R.id.darkPathButton:
                setContentView(R.layout.part1_dark_path);
                break;

            // From part1_dark_path.xml
            case R.id.runAwayButton:
                setContentView(R.layout.part1_run_away_from_goblin);
                break;

            // From part1_run_away_from_goblin.xml
            case R.id.pullSwordButton:
                setContentView(R.layout.part1_sword);
                break;

            // From part1_dark_path.xml
            case R.id.sayHelloButton:
                progress = R.layout.part1_say_hello;
                setContentView(R.layout.part1_say_hello);
                break;

            // From part1_say_hello.xml (spanish xml)
            case R.id.attackHim:
                evilPoints=+2;
                setContentView(R.layout.part1_attack_goblin);
                break;

            // From part1_say_hello.xml
            case R.id.yesHelp:
                setContentView(R.layout.part1_goblin_help);
                break;

            // From part1_dark_path.xml
            case R.id.attackButton:
                evilPoints=+2;
                setContentView(R.layout.part1_attack_goblin);
                break;

            // From part1_say_hello.xml
            case R.id.attackGoblin:
                evilPoints=+2;
                setContentView(R.layout.part1_attack_goblin);
                break;

            // From part1_say_hello.xml
            case R.id.leaveButton:
                setContentView(R.layout.part1_sword);
                break;

            // From part1_say_hello.xml
            case R.id.nodButton:
                setContentView(R.layout.part1_nod_globin);
                break;

            // From part1_attack_globin.xml
            case R.id.leaveNowButton:
                setContentView(R.layout.part1_leave_after_finding_gold);
                break;

            // From part1_attack_globin.xml
            case R.id.keepLookingButton:
                setContentView(R.layout.part1_keep_looking);
                break;

            // From part1_attack_globin.xml
            case R.id.followPath:
                setContentView(R.layout.part1_sword);
                break;

            // From part1_keep_looking.xml
            case R.id.keepLookingButton2:
                setContentView(R.layout.part1_keep_looking_again);
                break;

            // From part1_keep_looking.xml
            case R.id.leaveNowButton2:
                setContentView(R.layout.part1_sword);
                break;

            // From part1_keep_looking_again.xml
            case R.id.gameOver:
                setContentView(R.layout.part1_gameover);
                break;

            // From part1_gameover.xml
            case R.id.mainMenu:
                setContentView(R.layout.activity_start_screen);
                break;

            case R.id.quitGame:
                System.exit(0);

            // From part1_goblin_help.xml
            case R.id.swordHelpFromGoblin:
                setContentView(R.layout.part1_sword);
                break;

            // From part1_sword.xml
            case R.id.pullSword:
                setContentView(R.layout.part1_pull_sword);
                break;

            // From part1_sword.xml
            case R.id.readInscription:
                setContentView(R.layout.part1_read_inscription);
                break;

            // From part1_sword.xml
            case R.id.keepWalking:
                setContentView(R.layout.part1_keep_walking);
                break;

            // From part1_pull_sword.xml
            case R.id.readInscription1:
                setContentView(R.layout.part1_read_inscription);
                break;

            // From part1_read_inscription.xml
            case R.id.pullSword1:
                setContentView(R.layout.part1_pull_sword2);
                break;

            // From part1_read_inscription.xml
            case R.id.leaveInscription:
                setContentView(R.layout.part1_leave_inscription);
                break;

            // From part1_keep_walking.xml
            case R.id.part2Butt:
                editor = progressData.edit();

                editor.putInt("points", points);
                editor.putInt("evil-points", evilPoints);
                editor.putBoolean("lantern", lantern);
                editor.putBoolean("sword", sword);
                editor.putBoolean("emerald", emerald);
                editor.putInt("part", 2);
                editor.putInt("storyProgress", R.layout.cavebeginning);
                progress = R.layout.cavebeginning;
                editor.commit();
                Intent part2 = new Intent(Part1.this, Cave.class);
                startActivity(part2);
                break;

            // From part1_leave_inscription.xml
            case R.id.goToPart2:
                editor = progressData.edit();

                editor.putInt("points", points);
                editor.putInt("evil-points", evilPoints);
                editor.putBoolean("lantern", lantern);
                editor.putBoolean("sword", sword);
                editor.putBoolean("emerald", emerald);
                editor.putInt("part", 2);
                editor.putInt("storyProgress", R.layout.cavebeginning);

                editor.commit();
                progress = R.layout.cavebeginning;
                Intent partdos = new Intent(Part1.this, Cave.class);
                startActivity(partdos);
                break;

            // From part1_pull_sword2.xml
            case R.id.takeTheSword:

                if(evilPoints == 0) {
                    setContentView(R.layout.part1_leave_inscription);
                    break;
                }
                else if(evilPoints == 1) {
                    setContentView(R.layout.part1_punishment1);
                    break;
                }
                else if(evilPoints >= 2) {
                    setContentView(R.layout.part1_punishment2);
                    break;
                }

            // From part1_punishment1.xml
            case R.id.lightningStrikes:
                setContentView(R.layout.part1_keep_walking);
                break;



            // From part1_punishment2.xml
            case R.id.gameOverButton:
                setContentView(R.layout.part1_gameover);
                break;



        } //end switch
    } //end onClick


    public void onImageClick(View view) {

        ImageButton imageButtonClicked = (ImageButton) view;

        switch (imageButtonClicked.getId()) {

            case R.id.helpFairyHint:
                Toast fairyHints = Toast.makeText(Part1.this, "I will help us get home.", Toast.LENGTH_LONG);
                fairyHints.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                fairyHints.show();
                break;

            case R.id.choosingPathFairy:
                Toast choosingPath = Toast.makeText(Part1.this, "I hate the dark, but I am able to light up.", Toast.LENGTH_LONG);
                choosingPath.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                choosingPath.show();
                break;

            case R.id.darkPathFairy:
                Toast darkPath = Toast.makeText(Part1.this, "Why don't you ask him for directions?", Toast.LENGTH_LONG);
                darkPath.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                darkPath.show();
                break;

            case R.id.runAwayFairy:
                Toast runAway = Toast.makeText(Part1.this, "Tap on the sword to try to pull it out of the rock. Keep trying until you get the sword. It could be useful!", Toast.LENGTH_LONG);
                runAway.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                runAway.show();
                break;

            case R.id.attackGoblinFairy:
                Toast jumpHim = Toast.makeText(Part1.this, "I think I heard something... we should get out of here.", Toast.LENGTH_LONG);
                jumpHim.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                jumpHim.show();
                break;

            case R.id.keepLookingFairy:
                Toast kl = Toast.makeText(Part1.this, "Seriously.. we should leave now!", Toast.LENGTH_LONG);
                kl.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                kl.show();
                break;

            case R.id.keepLookingFairy2:
                Toast kla = Toast.makeText(Part1.this, "I told you we should've left! It's too late now.", Toast.LENGTH_LONG);
                kla.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                kla.show();
                break;

            case R.id.leaveAfter:
                Toast leave = Toast.makeText(Part1.this, "Oooo trying pulling the sword.", Toast.LENGTH_LONG);
                leave.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                leave.show();
                break;

            case R.id.spanishFairy:
                Toast spanish = Toast.makeText(Part1.this, "Is he speaking in Spanish? We need to learn spanish to understand. Change the language settings on your phone to Spanish to get the English translation. Pointing is rude so don't upset him.", Toast.LENGTH_LONG);
                spanish.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                spanish.show();
                break;

            case R.id.angryGoblin:
                Toast angry = Toast.makeText(Part1.this, "Uh oh, your pointing upsetted him. Do you still want to look around?", Toast.LENGTH_LONG);
                angry.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                angry.show();
                break;

            case R.id.settingButton:
                startActivityForResult(new Intent(Settings.ACTION_LOCALE_SETTINGS),0);
                break;

            case R.id.goblinHelp:
                Toast help = Toast.makeText(Part1.this, "The goblin was very friendly. Let's follow the path.", Toast.LENGTH_LONG);
                help.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                help.show();
                break;

            case R.id.swordFairy:
                Toast sword = Toast.makeText(Part1.this, "Hmm.... trying pulling the sword?", Toast.LENGTH_LONG);
                sword.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                sword.show();
                break;

            case R.id.pullSwordFairy:
                Toast fairy = Toast.makeText(Part1.this, "Try reading the inscription.", Toast.LENGTH_LONG);
                fairy.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                fairy.show();
                break;

            case R.id.readInscriptionFairy:
                Toast rf = Toast.makeText(Part1.this, "Have you done anything bad lately? Maybe we should go.", Toast.LENGTH_LONG);
                rf.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                rf.show();
                break;

            case R.id.walkAwayFairy:
                Toast wa = Toast.makeText(Part1.this, "Yay!!!!", Toast.LENGTH_LONG);
                wa.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                wa.show();
                break;

            case R.id.pullSwordFairy2:
                Toast ps = Toast.makeText(Part1.this, "If you didn't attack the goblin or if you didn't shoo me away, leave with the sword." +
                        "If you did attack the goblin or shoo me away, you are about to experience karma.", Toast.LENGTH_LONG);
                ps.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                ps.show();
                break;

            case R.id.leaveFairy:
                Toast leave1 = Toast.makeText(Part1.this, "I think we're almost home!", Toast.LENGTH_LONG);
                leave1.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                leave1.show();
                break;

            case R.id.punishment1:
                Toast p1 = Toast.makeText(Part1.this, "RUN! RUN!", Toast.LENGTH_LONG);
                p1.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                p1.show();
                break;

            case R.id.punishment2:
                Toast p2 = Toast.makeText(Part1.this, "Game over :(", Toast.LENGTH_LONG);
                p2.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 110);
                p2.show();
                break;

        } //end switch
    } //end onImageClick
}

