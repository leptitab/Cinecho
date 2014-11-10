package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.models.Film;
import ca.qc.cstj.android.cinecho.models.Horaire;

/**
 * Created by 1132157 on 2014-11-07.
 */
public class HoraireAdapter extends GenericArrayAdapter<Horaire>{

    public HoraireAdapter(Context context, int layoutRes, ArrayList<Horaire> horaires) {
        super(context, layoutRes, horaires);
    }

    @Override
    public void update(TextView textView, Horaire object) {
        String dateHeure = object.getDateHeure().toString();
        textView.setText(dateHeure);
    }
}
