package main.ott.game.sadmandmcx.com.onetwothree;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class SplashScreen extends AppCompatActivity {

    private int progressStat = 0, counter = 0, update = 0;
    private Animation animation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        final ImageView dmcx = (ImageView) findViewById(R.id.dmcximage);
        final ProgressBar splashProgress = (ProgressBar) findViewById(R.id.splashscreenprgress);
        Animation animation1 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.zoom_in);
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.zoom_out);
        animation3 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.zoom_out_after);
        dmcx.startAnimation(animation1);
        animation1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dmcx.setAnimation(animation2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                progressStat = 0;
                try {
                    while (progressStat < 100){
                        Thread.sleep(50);
                        progressStat++;
                        splashProgress.setProgress(progressStat);
                        if(progressStat == 100){
                            finish();
                            Intent intent = new Intent(SplashScreen.this, MainMenuActivity.class);
                            
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            startActivity(intent);
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    // Used for home button action
    // This is called when the user intentionally leaves activity (ie: by pressing HOME key)
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.d("Success", "Exited");
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Can't go back at this moment!", Toast.LENGTH_SHORT).show();
    }
}
