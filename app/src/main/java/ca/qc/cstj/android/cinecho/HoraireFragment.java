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
import android.widget.ScrollView;
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
import ca.qc.cstj.android.cinecho.models.Film;
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
    //private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARG_HREF = "href";

    private String href;
    private ProgressDialog progressDialog;
    private OnFragmentInteractionListener mListener;
    private HoraireAdapter horaireAdapter;
    private ListView lstFilms;

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
        View view = inflater.inflate(R.layout.fragment_horaire, container, false);

        //txtNomFilm = (TextView)view.findViewById(R.id.nomFilm);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        lstFilms = (ListView) getActivity().findViewById(R.id.lstFilms);

        progressDialog = ProgressDialog.show(getActivity(), "Cinéma en folie", "En chargement...", true, false);
        Ion.with(getActivity())
                .load(href)
                .asJsonArray()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonArray>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonArray> jsonArrayResponse) {

                        if(jsonArrayResponse.getHeaders().getResponseCode() == HttpStatus.SC_OK) {
                            ArrayList<Film> films = new ArrayList<Film>();
                            JsonArray jsonArray = jsonArrayResponse.getResult();
                            for (JsonElement element : jsonArray) {
                                films.add(new Film(element.getAsJsonObject()));
                            }
                            horaireAdapter = new HoraireAdapter(getActivity(), getActivity().getLayoutInflater() , films);
                            lstFilms.setAdapter(horaireAdapter);
                        }
                        else {
                            ArrayList<Film> Films = new ArrayList<Film>();
                        }
                        progressDialog.dismiss();
                    }
                });

        lstFilms.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,CinemaFragment.newInstance(position))
                        .addToBackStack("");
                transaction.commit();

            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
