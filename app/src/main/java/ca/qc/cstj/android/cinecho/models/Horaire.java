package ca.qc.cstj.android.cinecho.models;

import com.google.gson.JsonObject;

import org.joda.time.DateTime;
import org.json.JSONObject;

import ca.qc.cstj.android.cinecho.helpers.DateParser;

/**
 * Created by 1132157 on 2014-11-07.
 */
public class Horaire {

    private String href;
    private DateTime dateHeure;
    private Film film;

    public Horaire(JsonObject jsonObject)
    {
        href = jsonObject.getAsJsonPrimitive("href").getAsString();
        dateHeure = DateParser.ParseIso(jsonObject.getAsJsonPrimitive("dateHeure").getAsString());
        film = new Film(jsonObject.getAsJsonObject("film"));
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public DateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(DateTime dateHeure) {
        this.dateHeure = dateHeure;
    }
}
