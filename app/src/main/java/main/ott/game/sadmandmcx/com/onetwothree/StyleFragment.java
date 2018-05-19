package main.ott.game.sadmandmcx.com.onetwothree;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class StyleFragment extends Fragment {

    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4,
            radioButton5, radioButton6, radioButtonGra1, radioButtonGra2, radioButtonGra3,
            radioButtonGra4, radioButtonGra5;
    private TextView stylesText, multiText, singleText;
    private Typeface typeface;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;


    public StyleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View styleView = inflater.inflate(R.layout.fragment_style, container, false);

        radioButton1 = (RadioButton) styleView.findViewById(R.id.radioRed);
        radioButton2 = (RadioButton) styleView.findViewById(R.id.radioAqua);
        radioButton3 = (RadioButton) styleView.findViewById(R.id.radioBlue);
        radioButton4 = (RadioButton) styleView.findViewById(R.id.radioPurple);
        radioButton5 = (RadioButton) styleView.findViewById(R.id.radioYellow);
        radioButton6 = (RadioButton) styleView.findViewById(R.id.radioGray);
        radioButtonGra1 = (RadioButton) styleView.findViewById(R.id.radioGraRisk);
        radioButtonGra2 = (RadioButton) styleView.findViewById(R.id.radioGra2);
        radioButtonGra3 = (RadioButton) styleView.findViewById(R.id.radioGra3);
        radioButtonGra4 = (RadioButton) styleView.findViewById(R.id.radioGra4);
        radioButtonGra5 = (RadioButton) styleView.findViewById(R.id.radioGra5);
        stylesText = (TextView) styleView.findViewById(R.id.stylesText);
        singleText = (TextView) styleView.findViewById(R.id.singleText);
        multiText = (TextView) styleView.findViewById(R.id.multiText);

        AssetManager assetManager = getContext().getAssets();
        typeface = Typeface.createFromAsset(assetManager, String.format(Locale.US, "fonts/%s", "nr.otf"));

        // Setting Typeface
        stylesText.setTypeface(typeface);
        multiText.setTypeface(typeface);
        singleText.setTypeface(typeface);
        radioButton1.setTypeface(typeface);
        radioButton2.setTypeface(typeface);
        radioButton3.setTypeface(typeface);
        radioButton4.setTypeface(typeface);
        radioButton5.setTypeface(typeface);
        radioButton6.setTypeface(typeface);
        radioButtonGra1.setTypeface(typeface);
        radioButtonGra2.setTypeface(typeface);
        radioButtonGra3.setTypeface(typeface);
        radioButtonGra4.setTypeface(typeface);
        radioButtonGra5.setTypeface(typeface);


        sharedPreferences = getActivity().getSharedPreferences(MainMenuActivity.CHANGE_STYLE_KEYS
                , Context.MODE_PRIVATE);


        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GameFragment.chooseColor = 1;
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 1);
                editor.commit();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 2);
                editor.commit();
                //GameFragment.chooseColor = 2;
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 3);
                editor.commit();
                //GameFragment.chooseColor = 3;
            }
        });
        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 4);
                editor.commit();
                //GameFragment.chooseColor = 4;
            }
        });
        radioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 5);
                editor.commit();
                //GameFragment.chooseColor = 5;
            }
        });
        radioButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 6);
                editor.commit();
                //GameFragment.chooseColor = 11;
            }
        });


        radioButtonGra1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 10);
                editor.commit();
                //GameFragment.chooseColor = 10;
            }
        });
        radioButtonGra2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 11);
                editor.commit();
                //GameFragment.chooseColor = 11;
            }
        });
        radioButtonGra3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 12);
                editor.commit();
                //GameFragment.chooseColor = 11;
            }
        });
        radioButtonGra4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 13);
                editor.commit();
                //GameFragment.chooseColor = 11;
            }
        });
        radioButtonGra5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt(MainMenuActivity.CHANGE_STYLE_KEYS, 14);
                editor.commit();
                //GameFragment.chooseColor = 11;
            }
        });



        int chooseColor = sharedPreferences.getInt(MainMenuActivity.CHANGE_STYLE_KEYS, 0);

        if(chooseColor == 0){
            radioButtonGra1.setChecked(true);
        }
        if(chooseColor == 1){
            radioButton1.setChecked(true);
        }
        if(chooseColor == 2){
            radioButton2.setChecked(true);
        }
        if(chooseColor == 3){
            radioButton3.setChecked(true);
        }
        if(chooseColor == 4){
            radioButton4.setChecked(true);
        }
        if(chooseColor == 5){
            radioButton5.setChecked(true);
        }
        if(chooseColor == 6){
            radioButton6.setChecked(true);
        }
        if(chooseColor == 10){
            radioButtonGra1.setChecked(true);
        }
        if(chooseColor == 11){
            radioButtonGra2.setChecked(true);
        }
        if(chooseColor == 12){
            radioButtonGra3.setChecked(true);
        }
        if(chooseColor == 13){
            radioButtonGra4.setChecked(true);
        }
        if(chooseColor == 14){
            radioButtonGra5.setChecked(true);
        }


        return styleView;
    }

}
