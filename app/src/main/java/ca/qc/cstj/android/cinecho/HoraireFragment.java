package ca.qc.cstj.android.cinecho;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.adapters.CinemaAdapter;
import ca.qc.cstj.android.cinecho.adapters.HoraireAdapter;
import ca.qc.cstj.android.cinecho.models.Cinema;
import ca.qc.cstj.android.cinecho.models.Horaire;
import ca.qc.cstj.android.cinecho.services.ServiceURI;

/**
 * Created by 1132157 on 2014-11-10.
 */
public class HoraireFragment extends Fragment{

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_HREF = "href";

    private String href;
    private ListView lstHoraire;
    private ProgressDialog progressDialog;
    private HoraireAdapter horaireAdapter;
    private TextView txtNomFilm;
    private Horaire horaire;
    private OnFragmentInteractionListener mListener;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static HoraireFragment newInstance(String href) {
        HoraireFragment fragment = new HoraireFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HREF, href);
        fragment.setArguments(args);
        return fragment;
    }

    public HoraireFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            href = getArguments().getString(ARG_HREF);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_horaire, container, false);

        txtNomFilm = (TextView) rootView.findViewById(R.id.nomFilm);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        progressDialog = ProgressDialog.show(getActivity(), "Cinéma en folie", "En chargement...", true, false);
        Ion.with(getActivity())
                .load(href)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {

                        horaire = new Horaire(jsonObject);

                        txtNomFilm.setText(horaire.getFilm().getTitre());
                        progressDialog.dismiss();

                    }

                });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
