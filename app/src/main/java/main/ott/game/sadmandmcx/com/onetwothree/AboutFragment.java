package main.ott.game.sadmandmcx.com.onetwothree;


import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    // Views
    TextView cradits, production, devoloper, name, graphics, designer, version, thanks, gameName;


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Id sync
        cradits = (TextView) view.findViewById(R.id.cradits);
        production = (TextView) view.findViewById(R.id.production);
        devoloper = (TextView) view.findViewById(R.id.devoloper);
        name = (TextView) view.findViewById(R.id.name);
        graphics = (TextView) view.findViewById(R.id.graphics);
        designer = (TextView) view.findViewById(R.id.designer);
        version = (TextView) view.findViewById(R.id.version);
        thanks = (TextView) view.findViewById(R.id.thanks);
        gameName = (TextView) view.findViewById(R.id.gameName);

        AssetManager assetManager = getContext().getAssets();
        Typeface typeface =
                Typeface.createFromAsset(assetManager, String.format(Locale.US, "fonts/%s", "nr.otf"));
        cradits.setTypeface(typeface);
        production.setTypeface(typeface);
        devoloper.setTypeface(typeface);
        name.setTypeface(typeface);
        graphics.setTypeface(typeface);
        designer.setTypeface(typeface);
        version.setTypeface(typeface);
        thanks.setTypeface(typeface);
        gameName.setTypeface(typeface);

        // Inflate the layout for this fragment
        return view;
    }

}
