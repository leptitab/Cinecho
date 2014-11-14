package ca.qc.cstj.android.cinecho;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.helpers.DateParser;
import ca.qc.cstj.android.cinecho.models.Film;
import ca.qc.cstj.android.cinecho.services.ServiceURI;

/**
 * Created by 1240755 on 2014-11-14.
 */
public class DetailFilmFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_HREF = "href";

    // TODO: Rename and change types of parameters
    private String href;
    private Image imgImage;
    private TextView txtTitre;
    private TextView txtPays;
    private TextView txtGenre;
    private TextView txtClasse;
    private TextView txtDuree;
    private TextView txtRealisateur;

    private ProgressDialog progressDialog;

    private Film film;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param href href de l'employé a afficher
     * @return A new instance of fragment DetailEmployeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFilmFragment newInstance(String href) {
        DetailFilmFragment fragment = new DetailFilmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HREF, href);
        fragment.setArguments(args);
        return fragment;
    }
    public DetailFilmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            href = getArguments().getString(ARG_HREF);
        }

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_film, container, false);

        txtTitre = (TextView) view.findViewById(R.id.txtTitre);
        txtPays = (TextView) view.findViewById(R.id.txtPays);
        txtClasse = (TextView) view.findViewById(R.id.txtClasse);
        txtGenre = (TextView) view.findViewById(R.id.txtGenre);
        txtDuree = (TextView)view.findViewById(R.id.txtDuree);
        txtRealisateur = (TextView)view.findViewById(R.id.txtRealisateur);

        return view;
    }

    /*private void sauvegarderEmploye() {

        Employe employe = new Employe();

        employe.setPrenom(txtPrenom.getText().toString());
        employe.setNom(txtNom.getText().toString());
        employe.setDateEmbauche(DateParser.Parse(txtDateEmbauche.getText().toString()));
        employe.setDateEmbauche(DateParser.Parse(txtDateNaissance.getText().toString()));
        employe.setDepartement((Departement)cboDepartement.getSelectedItem());

        JsonObject body = employe.toJson();

        Ion.with(getActivity())
                .load("PATCH",href)
                .addHeader("Content-Type","application/json")
                .setJsonObjectBody(body)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Enregistrement réussi", Toast.LENGTH_LONG).show();
                    }
                });

    }*/

    @Override
    public void onStart() {
        super.onStart();

        progressDialog = ProgressDialog.show(getActivity(), "Cinecho", "En chargement...", true, false);
        Ion.with(getActivity())
                .load(href)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {

                        film = new Film(jsonObject);

                        txtTitre.setText(film.getTitre());
                        txtPays.setText(film.getPays());
                        txtGenre.setText(film.getGenre());
                        txtClasse.setText(film.getClasse());
                        txtRealisateur.setText(film.getRealisateur());
                        txtDuree.setText(film.getDuree());
                        progressDialog.dismiss();

                    }

                });

        progressDialog.dismiss();
    }

    // TODO: Rename method, update argument and hook method into UI event

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
