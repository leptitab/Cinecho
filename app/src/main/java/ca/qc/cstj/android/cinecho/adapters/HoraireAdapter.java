package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.TimeZone;

import ca.qc.cstj.android.cinecho.R;
import ca.qc.cstj.android.cinecho.models.Film;
import ca.qc.cstj.android.cinecho.models.Horaire;

/**
 * Created by 1240755 on 2014-10-24.
 */
public class HoraireAdapter extends ArrayAdapter<Film> {

    private LayoutInflater mInflater;
    private ArrayList<Horaire> horaires;
    private ArrayList<Film> films;
    private Horaire horaire;

    private TextView nomFilm;
    private TextView horaire1;
    private TextView horaire2;

    public HoraireAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Film> listeFilm) {
        super(context, R.layout.item_horaire, listeFilm);
        this.films = listeFilm;
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
            horaireviewHolder.horaire1 = (TextView) convertView.findViewById(R.id.horaires);
            horaireviewHolder.horaire2 = (TextView) convertView.findViewById(R.id.horaires2);

            convertView.setTag(horaireviewHolder);

        } else {

            horaireviewHolder = (HoraireViewHolder) convertView.getTag();
        }

        Film film = getItem(position);

        /* rappel /*/
        Ion.with(getContext())
                .load(film.getHref() + "/horaires?timeZone=-05:00")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {
                        horaire = new Horaire(jsonObject);

                        /* set value */
                        horaireviewHolder.nomFilm.setText(horaire.getNomFIlm());
                        if(horaire.getHeure1() != null)
                            horaireviewHolder.horaire1.setText(horaire.getHeure1().toString());
                        else
                            horaireviewHolder.horaire1.setText("Aucune représentation");
                        if(horaire.getHeure2() != null)
                            horaireviewHolder.horaire2.setText(horaire.getHeure2().toString());
                        else
                            horaireviewHolder.horaire2.setVisibility(View.GONE);
                        }

                });
        return convertView;
    }

    private static class HoraireViewHolder {
        private TextView nomFilm;
        private TextView horaire1;
        private TextView horaire2;
    }

}
