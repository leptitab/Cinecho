package ca.qc.cstj.android.cinecho.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.JsonArray;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.R;
import ca.qc.cstj.android.cinecho.models.Commentaire;

/**
 * Created by William on 2014-11-18.
 */
public class CommentaireAdapter extends ArrayAdapter<Commentaire> {

    private LayoutInflater mInflater;
    private ArrayList<Commentaire> commentaires;
    private Commentaire commentaire;

    private TextView commentaireCommentaire;
    private TextView noteCommentaire;
    private TextView auteurCommentaire;
    private TextView dateCommentaire;

    public CommentaireAdapter(Context context, LayoutInflater layoutInflater, ArrayList<Commentaire> listeCommentaire) {
        super(context, R.layout.listitem_commentaire, listeCommentaire);
        this.commentaires = listeCommentaire;
        this.mInflater = layoutInflater;
    }

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final CommentaireViewHolder commentaireViewHolder;

        if(convertView == null) {

            convertView = mInflater.inflate(R.layout.listitem_commentaire, null, true);
            commentaireViewHolder = new CommentaireViewHolder();
            commentaireViewHolder.auteurCommentaire = (TextView) convertView.findViewById(R.id.auteurCommentaire);
            commentaireViewHolder.commentaireCommentaire = (TextView) convertView.findViewById(R.id.commentaireCommentaire);
            commentaireViewHolder.dateCommentaire = (TextView) convertView.findViewById(R.id.dateCommentaire);
            commentaireViewHolder.noteCommentaire = (TextView) convertView.findViewById(R.id.noteCommentaire);

            convertView.setTag(commentaireViewHolder);

        } else {

            commentaireViewHolder = (CommentaireViewHolder) convertView.getTag();
        }

        Commentaire commentaire = getItem(position);

        commentaireViewHolder.noteCommentaire.setText(String.valueOf(commentaire.getNote()));
        commentaireViewHolder.dateCommentaire.setText(commentaire.getDateCommentaire().toString());
        commentaireViewHolder.commentaireCommentaire.setText(commentaire.getTexte());
        commentaireViewHolder.auteurCommentaire.setText(commentaire.getAuteur());

        return convertView;
    }

    private static class CommentaireViewHolder {
        private TextView commentaireCommentaire;
        private TextView noteCommentaire;
        private TextView auteurCommentaire;
        private TextView dateCommentaire;
    }
}
