package main.ott.game.sadmandmcx.com.onetwothree;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainGameActivity extends AppCompatActivity {

    // Private
    private TextView score, quiz;
    private Button button1, button2, button3;
    private ProgressBar progressButton1, progressButton2, progressButton3;
    private FragmentTransaction fragmentTransaction;
    private GameFragment gameFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        // Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        openFragment();

    }

    private void openFragment(){

        MainMenuActivity.chooseFragment chooseFragment = (MainMenuActivity.chooseFragment)
                getIntent().getExtras().getSerializable(MainMenuActivity.CHANGE_FRAGMENT_KEYS);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (chooseFragment) {

            case ABOUT:
                AboutFragment aboutFragment = new AboutFragment();
                fragmentTransaction.add(R.id.fragmentAll, aboutFragment, "About Fragment");
                fragmentTransaction.commit();
                break;
            case PLAY:
                gameFragment = new GameFragment();
                fragmentTransaction.add(R.id.fragmentAll, gameFragment, "Game Fragment");
                fragmentTransaction.commit();
                break;
            case STYLE:
                StyleFragment styleFragment = new StyleFragment();
                fragmentTransaction.add(R.id.fragmentAll, styleFragment, "Style Fragment");
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        MainMenuActivity.chooseFragment fragment = (MainMenuActivity.chooseFragment)
                getIntent().getExtras().getSerializable(MainMenuActivity.CHANGE_FRAGMENT_KEYS);

        switch (fragment){
            case ABOUT:
                NavUtils.navigateUpFromSameTask(this);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                MainMenuActivity.aboutCall.setSelected(false);
                break;
            case PLAY:
                if (GameFragment.scoreCallFor >= 1){
                    Toast.makeText(getBaseContext(), "Can not go back at this stage.", Toast.LENGTH_SHORT).show();
                }
                if (GameFragment.scoreCallFor == 0){
                    NavUtils.navigateUpFromSameTask(this);
                    MainMenuActivity.playCall.setSelected(false);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }


                break;
            case STYLE:
                NavUtils.navigateUpFromSameTask(MainGameActivity.this);
                MainMenuActivity.stylesCall.setSelected(false);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

}
