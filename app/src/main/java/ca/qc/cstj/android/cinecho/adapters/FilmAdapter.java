package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.models.Film;

/**
 * Created by 1240755 on 2014-10-24.
 */
public class FilmAdapter extends GenericArrayAdapter<Film> {

    public FilmAdapter(Context context, int layoutRes, ArrayList<Film> films) {
        super(context, layoutRes, films);
    }

    @Override
    public void update(TextView textView, Film object) {
        String titre = object.getTitre().toString();
        textView.setText(titre);
    }


}
