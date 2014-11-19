package ca.qc.cstj.android.cinecho;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.apache.http.HttpStatus;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.adapters.FilmAdapter;
import ca.qc.cstj.android.cinecho.models.Film;
import ca.qc.cstj.android.cinecho.services.ServiceURI;

public class FilmFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private ListView lstFilm;
    private ProgressDialog progressDialog;
    private FilmAdapter filmAdapter;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FilmFragment newInstance(int sectionNumber) {
        FilmFragment fragment = new FilmFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public FilmFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_film, container, false);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        lstFilm = (ListView) getActivity().findViewById(R.id.list_film);


        loadFilms();
        //LE LISTENER EST L'EVENT QUI PREND LE CLICK DANS LE MENU POUR FAIRE L'ACTION

        lstFilm.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String href = filmAdapter.getItem(position).getHref();
                FragmentTransaction transaction =  getFragmentManager().beginTransaction();
                transaction.replace(R.id.container,DetailFilmFragment.newInstance(href))
                        .addToBackStack("");
                transaction.commit();

            }
        });
    }

    private void loadFilms()
    {
        progressDialog = ProgressDialog.show(getActivity(),"Cinecho","Chargement en cours",true,false);

        Ion.with(getActivity())
                .load(ServiceURI.Film_SERVICE_URI)
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
                            filmAdapter = new FilmAdapter(getActivity(), android.R.layout.simple_list_item_1, films);
                            lstFilm.setAdapter(filmAdapter);
                        }
                        else {
                            ArrayList<Film> films = new ArrayList<Film>();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

}