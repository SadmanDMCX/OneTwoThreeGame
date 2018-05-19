package main.ott.game.sadmandmcx.com.onetwothree;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainMenuActivity extends AppCompatActivity {

    // private
    private TextView scoreText, scoreCounter;
    private ImageView logo;
    private Button about, play, styles, exit;
    public static Button aboutCall, playCall, stylesCall, exitCall;

    // Keys
    public static final String CHANGE_FRAGMENT_KEYS = "com.ott.game.sadmandmcx.changefragment.key";
    public static final String CHANGE_SCORE_KEYS = "com.ott.game.sadmandmcx.changescore.key";
    public static final String CHANGE_STYLE_KEYS = "com.ott.game.sadmandmcx.changestyle.key";
    private int score = 0;

    // Fragments
    public enum chooseFragment {
        PLAY,
        STYLE,
        ABOUT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        // Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        score = GameFragment.scoreCall;

        logo = (ImageView) findViewById(R.id.logoImage);
        scoreText = (TextView) findViewById(R.id.highScore);
        scoreCounter = (TextView) findViewById(R.id.highScoreCounter);
        about = (Button) findViewById(R.id.aboutButton);
        exit = (Button) findViewById(R.id.exitButton);
        play = (Button) findViewById(R.id.playButton);
        styles = (Button) findViewById(R.id.stylesButton);

        // Display
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        double screenSize = Math.sqrt(x + y);


        if(screenSize > 5 && screenSize <= 5.96){
            //logo.getLayoutParams().height = 590;
            scoreText.setTextSize(40);
            scoreCounter.setTextSize(70);
            exit.getLayoutParams().width = 90;
            exit.getLayoutParams().height = 90;
            about.getLayoutParams().width = 90;
            about.getLayoutParams().height = 90;
        }else if(screenSize >= 4 && screenSize <= 5){
            //logo.getLayoutParams().height = 460;
            exit.getLayoutParams().width = 60;
            exit.getLayoutParams().height = 60;
            about.getLayoutParams().width = 70;
            about.getLayoutParams().height = 70;
            scoreText.setTextSize(25);
            scoreCounter.setTextSize(50);
        }else if(screenSize > 3 && screenSize < 4){
            exit.getLayoutParams().width = 40;
            exit.getLayoutParams().height = 40;
            about.getLayoutParams().width = 50;
            about.getLayoutParams().height = 50;
            //logo.getLayoutParams().height = 440;
            scoreText.setTextSize(20);
            scoreCounter.setTextSize(35);
        }else if(screenSize > 6 && screenSize < 7.1){
            //logo.getLayoutParams().height = 890;
            exit.getLayoutParams().width = 100;
            exit.getLayoutParams().height = 100;
            about.getLayoutParams().width = 110;
            about.getLayoutParams().height = 110;
            scoreText.setTextSize(50);
            scoreCounter.setTextSize(90);
        }


        // Animation

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(900); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
        //play.startAnimation(animation);
        // End


        AssetManager am = getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "yu.ttf"));

        scoreText.setTypeface(typeface);
        scoreCounter.setTypeface(typeface);

        // About Button
        aboutCall = about;
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                about.setSelected(true);
                Intent intent = new Intent(MainMenuActivity.this, MainGameActivity.class);
                intent.putExtra(CHANGE_FRAGMENT_KEYS, chooseFragment.ABOUT);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        saveScore();

        // Play Button
        playCall = play;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play.setSelected(true);
                v.clearAnimation();
                Intent intent = new Intent(MainMenuActivity.this, MainGameActivity.class);
                intent.putExtra(CHANGE_FRAGMENT_KEYS, chooseFragment.PLAY);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        stylesCall = styles;
        styles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                styles.setSelected(true);
                v.clearAnimation();
                Intent intent = new Intent(MainMenuActivity.this, MainGameActivity.class);
                intent.putExtra(CHANGE_FRAGMENT_KEYS, chooseFragment.STYLE);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        // Exit Button
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit.setSelected(true);
                openExitDialog();
            }
        });

    }

    private void saveScore(){
        SharedPreferences sharedPreferences = getSharedPreferences(CHANGE_SCORE_KEYS, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(CHANGE_SCORE_KEYS, score);
        int compare = sharedPreferences.getInt(CHANGE_SCORE_KEYS, 0);
        if(compare < score){
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(CHANGE_SCORE_KEYS, score);
            edit.commit();
            int com = sharedPreferences.getInt(CHANGE_SCORE_KEYS, 0);
            scoreCounter.setText(com + "");
        }else {
            int com = sharedPreferences.getInt(CHANGE_SCORE_KEYS, 0);
            scoreCounter.setText(com + "");
        }
    }

    private void openExitDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exit.setSelected(false);
                MainMenuActivity.this.finish();
                System.exit(0);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exit.setSelected(false);
            }
        });

        builder.show();
    }

    @Override
    public void onBackPressed() {
        openExitDialog();
    }
}
