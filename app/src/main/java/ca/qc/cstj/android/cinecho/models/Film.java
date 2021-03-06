package ca.qc.cstj.android.cinecho.models;

import android.renderscript.Int2;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * Created by 1240755 on 2014-10-31.
 */
public class Film {
    private String href;
    private String titre;
    private String pays;
    private String genre;
    private String classe;
    private int duree;
    private String realisateur;
    private String imageUrl;

    public Film(JsonObject jsonObject) {
        href = jsonObject.getAsJsonPrimitive("href").getAsString();
        if(jsonObject.has("titre"))
            titre = jsonObject.getAsJsonPrimitive("titre").getAsString();
        if(jsonObject.has("pays"))
            pays = jsonObject.getAsJsonPrimitive("pays").getAsString();
        if(jsonObject.has("genre"))
            genre = jsonObject.getAsJsonPrimitive("genre").getAsString();
        if(jsonObject.has("classe"))
            classe = jsonObject.getAsJsonPrimitive("classe").getAsString();
        if(jsonObject.has("duree"))
            duree = jsonObject.getAsJsonPrimitive("duree").getAsInt();
        if(jsonObject.has("realisateur"))
            realisateur = jsonObject.getAsJsonPrimitive("realisateur").getAsString();
        if(jsonObject.has("imageUrl"))
            imageUrl = jsonObject.getAsJsonPrimitive("imageUrl").getAsString();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        json.addProperty("titre",titre);
        json.addProperty("pays",pays);
        json.addProperty("genre",genre);
        json.addProperty("classe",classe);
        json.addProperty("duree",duree);
        json.addProperty("realisateur",realisateur);
        json.addProperty("imageUrl",imageUrl);

        return json;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
