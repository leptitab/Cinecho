package ca.qc.cstj.android.cinecho.models;

import com.google.gson.JsonObject;

import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by 1240755 on 2014-11-18.
 */
public class Commentaire {

    private String href;
    private String auteur;
    private String texte;
    private int note;

    public Commentaire() {
    }

    public Commentaire(JsonObject jsonObject)
    {
        href = jsonObject.getAsJsonPrimitive("href").getAsString();
        auteur = jsonObject.getAsJsonPrimitive("auteur").getAsString();
        texte = jsonObject.getAsJsonPrimitive("texte").getAsString();
        note = jsonObject.getAsJsonPrimitive("note").getAsInt();
    }

    public JsonObject toJson()
    {
        JsonObject json = new JsonObject();

        json.addProperty("auteur",auteur);
        json.addProperty("texte",texte);
        json.addProperty("note",note);

        return json;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
