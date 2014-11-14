package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
            horaireviewHolder.horaire = (TextView) convertView.findViewById(R.id.horaires);

            convertView.setTag(horaireviewHolder);

        } else {

            horaireviewHolder = (HoraireViewHolder) convertView.getTag();
        }

        Film film = getItem(position);

        /* rappel /*/
        /*Ion.with(getContext())
                .load(film.getHref() + "/horaires/"+ TimeZone.getTimeZone("GMT"))
                .asJsonObject()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonArray>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonArray> jsonArrayResponse) {
                        if(jsonArrayResponse.getHeaders().getResponseCode() == HttpStatus.SC_OK) {
                             jsonArray = jsonArrayResponse.getResult();

                            if (jsonArray.size() > 0) {
                                Horaire horaireContenu = new Horaire(jsonArray.get(0).getAsJsonObject().getas);
                                horaireviewHolder.horaire.setText(horaireContenu.getDateHeure().toString());
                            }
                            if (jsonArray.size() > 1)
                            {
                                Horaire horaireContenu = new Horaire(jsonArray.get(1).getAsJsonObject());
                                horaireviewHolder.horaire2.setText(horaireContenu.getDateHeure().toString());
                            }

                        }
                        else {
                            // Erreur 404
                        }
                    }
                });*/
        return convertView;
    }

    private static class HoraireViewHolder {
        private TextView nomFilm;
        private TextView horaire;
        private TextView horaire2;
    }

}
