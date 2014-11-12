package ca.qc.cstj.android.cinecho.models;

import com.google.gson.JsonObject;

import org.joda.time.DateTime;
import org.json.JSONObject;

import ca.qc.cstj.android.cinecho.helpers.DateParser;

/**
 * Created by 1132157 on 2014-11-07.
 */
public class Horaire {

    private DateTime dateHeure;
    private String filmHref;

    public Horaire(JsonObject jsonObject)
    {
        dateHeure = DateParser.ParseIso(jsonObject.getAsJsonPrimitive("dateHeure").getAsString());
        JsonObject film = jsonObject.getAsJsonObject("film");
        filmHref = film.getAsJsonPrimitive("href").getAsString();
    }

    public String getFilmHref() {
        return filmHref;
    }

    public void setFilmHref(String filmHref) {
        this.filmHref = filmHref;
    }

    public DateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(DateTime dateHeure) {
        this.dateHeure = dateHeure;
    }
}
