package ca.qc.cstj.android.cinecho.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.R;
import ca.qc.cstj.android.cinecho.models.Cinema;

/**
 * Created by 1240755 on 2014-10-24.
 */
public class CinemaAdapter extends ArrayAdapter<Cinema>{

    private ArrayList<Cinema> cinemas;
    private LayoutInflater mInflater;

    public CinemaAdapter(Context context, LayoutInflater LayoutInflater, ArrayList<Cinema> listeCinemas) {
        super(context, R.layout.listitem_cinema, listeCinemas);
        this.cinemas=listeCinemas;
        this.mInflater=LayoutInflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CinemaViewHolder mCinemaViewHolder;
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem_cinema, null, true);
            mCinemaViewHolder = new CinemaViewHolder();
            mCinemaViewHolder.txtNom=(TextView)convertView.findViewById(R.id.nom);
            mCinemaViewHolder.txtNom.setTypeface(null, Typeface.BOLD);
            mCinemaViewHolder.txtAdresse=(TextView)convertView.findViewById(R.id.adresse);

            convertView.setTag(mCinemaViewHolder);

        } else {

            mCinemaViewHolder = (CinemaViewHolder) convertView.getTag();
        }
        Cinema mCinema = getItem(position);
        mCinemaViewHolder.txtNom.setText(mCinema.getNom());
        mCinemaViewHolder.txtAdresse.setText(mCinema.getAdresse());

        return convertView;
    }

    private static class CinemaViewHolder {
        private TextView txtNom;
        private TextView txtAdresse;
    }

}