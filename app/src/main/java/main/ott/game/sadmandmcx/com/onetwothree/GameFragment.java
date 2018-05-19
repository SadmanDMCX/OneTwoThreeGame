package main.ott.game.sadmandmcx.com.onetwothree;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {

    // private views
    private TextView scoreCounter, quiz, goodLuckText, newRec;
    private Button click1, click2, click3;
    public static ProgressBar clickPrgress1, clickPrgress2, clickPrgress3;
    private final int finalTime = 4500;
    private int time = finalTime;
    private Typeface typeface2;
    private Animation animation1, in, out;
    private final CounterClass cTimer = new CounterClass(time, 1000);
    private String combinations[] = {
            "1+2=?", "1+1=?", "2+1=?", "2-1=?", "3-1=?", "3-2=?",
            "1+2-2=?", "1+3-3=?", "2-3+3=?", "2-1+1=?", "3-1+1=?", "3-2+2=?",
            "2+1-2=?", "3+1-3=?", "1+3-1=?", "1+2-1=?", "3+2-3=?", "2+3-2=?",
            "2+2-3=?", "1+1+1-2=?", "3-1-1=?",
            "1+1-1=?", "2+2-2=?", "3+3-3=?",
            // Easy at 24

            // Hard at 25
            "1+3-2=?", "3-2+1=?", "3+1-2=?",
            "3+1+2-3=?", "3+3-2-1=?", "2-1-2+2=?", "1+3-2-1=?",
            "1+3-1-2=?", "3-2+3-2=?", "1-1+3-2=?", "3-1+3-2=?", "1+2+2-2=?",
            "3-1+2-1=?", "1+3-2+1=?", "1-2-1+3=?", "2-2+2-1=?", "3-2+3-2=?", "2-3-1+3=?",
            "3-2+3-1=?", "2-1+3-2=?", "1+1-3+2=?", "3-1+2-2=?", "2+2-3+2=?", "1-2+3=?"
    };
    private String solutions[] = {
            "3", "2", "3", "1", "2", "1",
            "1", "1", "2", "2", "3", "3",
            "1", "1", "3", "2", "2", "3",
            "1", "1", "1",
            "1", "2", "3",
            // Easy at 24

            // Hard after 25
            "2", "2", "2",
            "3", "3", "1", "1",
            "1", "2", "1", "3", "3",
            "3", "3", "1", "1", "2", "1",
            "3", "2", "1", "2", "3", "2"
    };
    private String goodluck[] = {
            "Good Luck", "You Made It.", "Awesome!", "Keep it up.", "You'r Good!", "Perfect!", "That's good!",
            "Great!", "Yeah!", "Don't Loose.", "Deadly", "Good", "Nice", "Fabulous", "Awesome"
    };
    private final int goodluckCount = 15;
    private int score = 0, randomGen = 0, randomGLgen = 0;
    private final int scoreToStore = 0;
    private int lastValue = 0;
    private Random rand = new Random();
    private Random randomGoodluck = new Random();
    private double screenSize;
    public static ObjectAnimator progressAnimator1, progressAnimator2, progressAnimator3;
    public int chooseColor = 10;
    public static int scoreCall = 0, scoreCallFor = 0;
    private int x = 100;


    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);


        // Assigns
        scoreCounter = (TextView) view.findViewById(R.id.scoreCounter);
        quiz = (TextView) view.findViewById(R.id.quiz);
        goodLuckText = (TextView) view.findViewById(R.id.goodLuckText);
        newRec = (TextView) view.findViewById(R.id.newRecShow);

        click1 = (Button) view.findViewById(R.id.click1);
        click2 = (Button) view.findViewById(R.id.click2);
        click3 = (Button) view.findViewById(R.id.click3);

        clickPrgress1 = (ProgressBar) view.findViewById(R.id.clickProgress1);
        clickPrgress2 = (ProgressBar) view.findViewById(R.id.clickProgress2);
        clickPrgress3 = (ProgressBar) view.findViewById(R.id.clickProgress3);
        clickPrgress1.setProgress(time);
        clickPrgress2.setProgress(time);
        clickPrgress3.setProgress(time);

        getStyle();

        if(chooseColor == 1){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#C71F1F"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#C71F1F"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#C71F1F"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_red));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_red));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_red));
        }
        if(chooseColor == 2){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#14A3AF"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#14A3AF"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#14A3AF"));
            click1.setTextColor(Color.WHITE);
            click2.setTextColor(Color.WHITE);
            click3.setTextColor(Color.WHITE);
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_aqua));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_aqua));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_aqua));
        }
        if(chooseColor == 3){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#164C97"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#164C97"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#164C97"));
            click1.setTextColor(Color.WHITE);
            click2.setTextColor(Color.WHITE);
            click3.setTextColor(Color.WHITE);
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_blue));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_blue));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_blue));
        }
        if(chooseColor == 4){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#34218F"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#34218F"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#34218F"));
            click1.setTextColor(Color.WHITE);
            click2.setTextColor(Color.WHITE);
            click3.setTextColor(Color.WHITE);
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_purple));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_purple));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_purple));
        }
        if(chooseColor == 5){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#ABB34A"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#ABB34A"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#ABB34A"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_yellow));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_yellow));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_yellow));
        }
        if(chooseColor == 6){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#232526"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#232526"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#232526"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_gray));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_gray));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_mono_gray));
        }

        if(chooseColor == 10){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#A9A42F"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#A9A42F"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#A9A42F"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_risk_meter));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_risk_meter));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_risk_meter));
        }
        if(chooseColor == 11){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#2d3ac9"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#2d3ac9"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#2d3ac9"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_2));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_2));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_2));
        }
        if(chooseColor == 12){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#514A9D"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#514A9D"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#514A9D"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_3));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_3));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_3));
        }
        if(chooseColor == 13){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#5f2c82"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#5f2c82"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#5f2c82"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_4));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_4));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_4));
        }
        if(chooseColor == 14){
            click1.setShadowLayer(24, 0, 0, Color.parseColor("#DC2424"));
            click2.setShadowLayer(24, 0, 0, Color.parseColor("#DC2424"));
            click3.setShadowLayer(24, 0, 0, Color.parseColor("#DC2424"));
            clickPrgress1.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_5));
            clickPrgress2.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_5));
            clickPrgress3.setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.custom_progress_bar_in_game_gra_5));
        }

        AssetManager assetManager = getContext().getAssets();
        Typeface typeface1 = Typeface.createFromAsset(assetManager, String.format(Locale.US, "fonts/%s", "yu.ttf"));
        typeface2 = Typeface.createFromAsset(assetManager, String.format(Locale.US, "fonts/%s", "nr.otf"));
        scoreCounter.setTypeface(typeface2);
        quiz.setTypeface(typeface2);
        click1.setTypeface(typeface2);
        click2.setTypeface(typeface2);
        click3.setTypeface(typeface2);
        goodLuckText.setTypeface(typeface2);

        levelHard();

        //
        randomGen = rand.nextInt(lastValue+1) + 0;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        screenSize = Math.sqrt(x + y);


        if(screenSize > 5 && screenSize <= 5.96){
            if(combinations[randomGen].length() == 9){
                quiz.setTextSize(75);
            }else if(combinations[randomGen].length() <= 7) {
                quiz.setTextSize(100);
            }

        }else if(screenSize >= 4 && screenSize <= 5){

            if(combinations[randomGen].length() == 9){
                quiz.setTextSize(60);
            }else if(combinations[randomGen].length() <= 7) {
                quiz.setTextSize(80);
            }

        }else if(screenSize > 3 && screenSize < 4){

            if(combinations[randomGen].length() == 9){
                quiz.setTextSize(60);
            }else if(combinations[randomGen].length() <= 7) {
                quiz.setTextSize(75);
            }

        }

        //

        animation1 = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.fade_in);

        in = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.slide_in_left);
        out = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.slide_out_right);


        quiz.setText(combinations[randomGen]);
        scoreCounter.setText(String.valueOf(score));

        // Timer
        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(solutions[randomGen] == "1"){
                   goodLuckTextCall();
                   animStart();
                   cTimer.start();
                   levelHard();
                    scoreCounter.startAnimation(animation1);
                    score = score + 1;
                    scoreCounter.setText(String.valueOf(score));
                    quiz.startAnimation(out);
                    if(quiz.getAnimation() == out){
                        quiz.startAnimation(in);
                        randomGen = rand.nextInt(lastValue) + 0;
                        //

                        if(screenSize > 5 && screenSize <= 5.96){
                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(75);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(100);
                            }

                        }else if(screenSize >= 4 && screenSize <= 5){

                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(60);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(80);
                            }

                        }else if(screenSize > 3 && screenSize < 4){

                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(60);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(75);
                            }

                        }

                        //

                        quiz.setText(combinations[randomGen]);
                    }

                }else {
                   failedTextCall();
                   gameOverDialog ();
                   cTimer.cancel();
                }
            }

        });

        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(solutions[randomGen] == "2"){
                    goodLuckTextCall();
                    animStart();
                    cTimer.start();
                    levelHard();
                    scoreCounter.startAnimation(animation1);
                    score = score + 1;
                    scoreCounter.setText(String.valueOf(score));
                    quiz.startAnimation(out);
                    if(quiz.getAnimation() == out){
                        quiz.startAnimation(in);
                        randomGen = rand.nextInt(lastValue) + 0;

                        //

                        if(screenSize > 5 && screenSize <= 5.96){
                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(75);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(100);
                            }

                        }else if(screenSize >= 4 && screenSize <= 5){

                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(60);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(80);
                            }

                        }else if(screenSize > 3 && screenSize < 4){

                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(60);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(75);
                            }

                        }

                        quiz.setText(combinations[randomGen]);
                    }
                }else {
                    failedTextCall();
                    gameOverDialog ();
                    cTimer.cancel();
                }
            }
        });

        click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(solutions[randomGen] == "3"){
                    goodLuckTextCall();
                    animStart();
                    cTimer.start();
                    levelHard();
                    scoreCounter.startAnimation(animation1);
                    score = score + 1;
                    scoreCounter.setText(String.valueOf(score));
                    quiz.startAnimation(out);
                    if(quiz.getAnimation() == out){
                        quiz.startAnimation(in);
                        randomGen = rand.nextInt(lastValue) + 0;

                        //

                        if(screenSize > 5 && screenSize <= 5.96){
                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(75);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(100);
                            }

                        }else if(screenSize >= 4 && screenSize <= 5){

                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(60);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(80);
                            }

                        }else if(screenSize > 3 && screenSize < 4){

                            if(combinations[randomGen].length() == 9){
                                quiz.setTextSize(60);
                            }else if(combinations[randomGen].length() <= 7) {
                                quiz.setTextSize(75);
                            }

                        }
                        quiz.setText(combinations[randomGen]);
                    }
                }else {
                    failedTextCall();
                    gameOverDialog ();
                    cTimer.cancel();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void getStyle(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainMenuActivity.CHANGE_STYLE_KEYS, Context.MODE_PRIVATE);
        chooseColor = sharedPreferences.getInt(MainMenuActivity.CHANGE_STYLE_KEYS, 0);
    }

    private void goodLuckTextCall(){
        scoreCallFor = score+1;
        setNewRec();
        randomGLgen = randomGoodluck.nextInt(goodluckCount) + 0;
        goodLuckText.setText(goodluck[randomGLgen]);
    }

    private void setNewRec(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MainMenuActivity.CHANGE_SCORE_KEYS, Context.MODE_PRIVATE);
        int compare = sharedPreferences.getInt(MainMenuActivity.CHANGE_SCORE_KEYS, 0);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in_new);

        if(compare < scoreCallFor && x == 100){
            newRec.setVisibility(View.VISIBLE);
            newRec.startAnimation(animation);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    invisibleNewRec();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            x++;
        }
    }

    private void invisibleNewRec() {
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_out_new);
        newRec.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                newRec.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void failedTextCall(){
        goodLuckText.setText("Ha Ha Ha");
    }

    private void animStart(){
        startAnimation1(time);
        startAnimation2(time);
        startAnimation3(time);
    }

    private void showResExx(){
        for(int i = 0; i < combinations[randomGen].length(); i++){
            if(combinations[randomGen] == "?"){

            }
        }
    }

    public void gameOverDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_dialog);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        scoreCall = score;
        scoreCallFor = 0;
        x = 100;


        if(score >= 1){
            progressAnimator1.end();
            progressAnimator1.cancel();
            progressAnimator2.end();
            progressAnimator2.cancel();
            progressAnimator3.end();
            progressAnimator3.cancel();
        }

        clickPrgress1.setProgress(time);
        clickPrgress2.setProgress(time);
        clickPrgress3.setProgress(time);

        TextView playerScoreCount = (TextView) dialog.findViewById(R.id.playerScoreCount);
        TextView playerScoreText = (TextView) dialog.findViewById(R.id.playerScoreText);
        playerScoreCount.setTypeface(typeface2);
        playerScoreText.setTypeface(typeface2);
        playerScoreCount.setText(score + "");

        Button play = (Button) dialog.findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodLuckText.setText("Good Luck");
                time = finalTime;
                //clickPrgress1.setProgress(5);
                score = 0;
                levelHard();
                scoreCounter.setText(String.valueOf(score));
                randomGen = rand.nextInt(lastValue) + 0;
                quiz.startAnimation(in);
                if(screenSize > 5 && screenSize <= 5.96){
                    if(combinations[randomGen].length() == 9){
                        quiz.setTextSize(75);
                    }else if(combinations[randomGen].length() <= 7) {
                        quiz.setTextSize(100);
                    }

                }else if(screenSize >= 4 && screenSize <= 5){

                    if(combinations[randomGen].length() == 9){
                        quiz.setTextSize(60);
                    }else if(combinations[randomGen].length() <= 7) {
                        quiz.setTextSize(80);
                    }

                }else if(screenSize > 3 && screenSize < 4){

                    if(combinations[randomGen].length() == 9){
                        quiz.setTextSize(60);
                    }else if(combinations[randomGen].length() <= 7) {
                        quiz.setTextSize(75);
                    }

                }
                quiz.setText(combinations[randomGen]);
                dialog.dismiss();

            }
        });


        Button exit = (Button) dialog.findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                NavUtils.navigateUpFromSameTask(getActivity());
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        play.setTypeface(typeface2);
        exit.setTypeface(typeface2);

    }

    private void levelHard(){
        if (score <= 20){
            lastValue = 24;
        }else {
            lastValue = combinations.length;
        }
    }

    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long millis = millisUntilFinished;

            String hms = String.format(
                    "%02d",
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));

            //int time = Integer.valueOf(hms);
            //click3.setText(hms);

        }

        @Override
        public void onFinish() {
            gameOverDialog();
            cTimer.cancel();
        }

    }

    private void startAnimation1(int time){
        if(score >= 1){
            progressAnimator1.end();
            progressAnimator1.cancel();
        }
        final int t = time+100;
        progressAnimator1 = ObjectAnimator.ofInt(clickPrgress1, "progress", t, 0);
        progressAnimator1.setDuration(6000);
        progressAnimator1.setInterpolator(new DecelerateInterpolator());
        progressAnimator1.start();


    }

    private void startAnimation2(int time){
        if(score >= 1){
            progressAnimator2.end();
            progressAnimator2.cancel();
        }
        final int t = time+100;
        progressAnimator2 = ObjectAnimator.ofInt(clickPrgress2, "progress", t, 0);
        progressAnimator2.setDuration(6000);
        progressAnimator2.setInterpolator(new DecelerateInterpolator());
        progressAnimator2.start();


    }

    private void startAnimation3(int time){
        if(score >= 1){
            progressAnimator3.end();
            progressAnimator3.cancel();
        }
        final int t = time+100;
        progressAnimator3 = ObjectAnimator.ofInt(clickPrgress3, "progress", t, 0);
        progressAnimator3.setDuration(6000);
        progressAnimator3.setInterpolator(new DecelerateInterpolator());
        progressAnimator3.start();

    }

}