package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.R;
import ca.qc.cstj.android.cinecho.models.Horaire;

/**
 * Created by 1240755 on 2014-10-24.
 */
public class HoraireAdapter extends ArrayAdapter<Horaire> {

    private LayoutInflater mInflater;
    private ArrayList<Horaire> horaires;

    public HoraireAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Horaire> listeHoraire) {
        super(context, R.layout.item_horaire, listeHoraire);
        this.horaires = listeHoraire;
        this.mInflater = layoutInflater;
    }

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final HoraireViewHolder horaireviewHolder;

        if(convertView == null) {

            convertView = mInflater.inflate(R.layout.item_horaire, null, true);
            horaireviewHolder = new HoraireViewHolder();
            horaireviewHolder.nomFilm = (TextView) convertView.findViewById(R.id.nomFilm);
            horaireviewHolder.horaire = (TextView) convertView.findViewById(R.id.horaires);

            convertView.setTag(horaireviewHolder);

        } else {

            horaireviewHolder = (HoraireViewHolder) convertView.getTag();
        }

        Horaire horaire = getItem(position);

        horaireviewHolder.nomFilm.setText(horaire.getFilmHref());
        horaireviewHolder.horaire.setText(horaire.getDateHeure().toString());

        return convertView;
    }

    private static class HoraireViewHolder {
        private TextView nomFilm;
        private TextView horaire;

    }

}
