package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.adapters.GenericArrayAdapter;
import ca.qc.cstj.android.cinecho.models.Cinema;

/**
 * Created by 1240755 on 2014-10-24.
 */
public class CinemaAdapter extends GenericArrayAdapter<Cinema> {

    public CinemaAdapter(Context context, int layoutRes, ArrayList<Cinema> cinemas) {
        super(context, layoutRes, cinemas);
    }

    @Override
    public void update(TextView textView, Cinema object) {
        String nomCinema = object.getNom().toString();
        textView.setText(nomCinema);
    }


}
