package ca.qc.cstj.android.cinecho;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

import org.joda.time.DateTime;
import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import ca.qc.cstj.android.cinecho.adapters.CommentaireAdapter;
import ca.qc.cstj.android.cinecho.helpers.DateParser;
import ca.qc.cstj.android.cinecho.models.Commentaire;
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
    private Button btnSauvegarder;
    private ListView listCommentaire;

    private EditText txtAuteur;
    private EditText txtCommentaireAjoute;
    private EditText txtNote;

    private ProgressDialog progressDialog;

    private Film film;

    private OnFragmentInteractionListener mListener;
    private CommentaireAdapter commentaireAdapter;

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
        btnSauvegarder = (Button)view.findViewById(R.id.btnAjouter);

        txtAuteur = (EditText) view.findViewById(R.id.txtAuteur);
        txtCommentaireAjoute = (EditText) view.findViewById(R.id.txtCommentaireAjoute);
        txtNote = (EditText) view.findViewById(R.id.txtNote);

        btnSauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ajouterCommentaire();
            }
        });

        return view;
    }

    private void loadCommentaires()
    {
        listCommentaire = (ListView) getActivity().findViewById(R.id.listCommentaires);

        //On affiche les commentaires du film
        Ion.with(getActivity())
                .load(href+"/commentaires?order=yes")
                .asJsonArray()
                .withResponse()
                .setCallback(new FutureCallback<Response<JsonArray>>() {
                    @Override
                    public void onCompleted(Exception e, Response<JsonArray> jsonArrayResponse) {
                        ArrayList<Commentaire> commentaires = new ArrayList<Commentaire>();
                        JsonArray jsonArray = jsonArrayResponse.getResult();
                        for (JsonElement element : jsonArray) {
                            commentaires.add(new Commentaire(element.getAsJsonObject()));
                        }
                        commentaireAdapter = new CommentaireAdapter(getActivity(), getActivity().getLayoutInflater(), commentaires);
                        listCommentaire.setAdapter(commentaireAdapter);
                    }
                });
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        DateTime date = new DateTime();
        return dateFormat.format(date);
    }

    private void ajouterCommentaire() {

        Commentaire commentaire = new Commentaire();

        commentaire.setAuteur(txtAuteur.getText().toString());
        commentaire.setTexte(txtCommentaireAjoute.getText().toString());
        commentaire.setNote(Integer.parseInt(txtNote.getText().toString()));
        commentaire.setDateCommentaire(DateParser.Parse(getDateTime()));
        if (commentaire.getNote() <= 10 || commentaire.getAuteur().equals("")|| commentaire.getTexte().equals("")) {
            JsonObject body = commentaire.toJson();

            Ion.with(getActivity())
                    .load(href + "/commentaires")
                    .addHeader("Content-Type", "application/json")
                    .setJsonObjectBody(body)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject jsonObject) {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Commentaire ajouté", Toast.LENGTH_LONG).show();
                            txtAuteur.setText("");
                            txtNote.setText("");
                            txtCommentaireAjoute.setText("");
                        }
                    });
        } else {
            txtNote.setText("");
            Toast.makeText(getActivity().getApplicationContext(),
                    "La note doit être inferieur à 10", Toast.LENGTH_LONG).show();
        }
    }

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

                        ImageView ionImage = (ImageView)getActivity().findViewById(R.id.imgFilm);
                        Ion.with(ionImage).placeholder(R.drawable.ic_launcher).load(film.getImageUrl());

                        txtTitre.setText(film.getTitre());
                        txtPays.setText(film.getPays());
                        txtGenre.setText(film.getGenre());
                        txtClasse.setText(film.getClasse());
                        txtRealisateur.setText(film.getRealisateur());

                        String temps = String.valueOf((film.getDuree() / 60));

                        txtDuree.setText(temps);

                        progressDialog.dismiss();

                    }

                });
        loadCommentaires();

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
