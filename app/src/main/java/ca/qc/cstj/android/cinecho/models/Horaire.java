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
    private Film film;
    private String cinemaHref;

    public Horaire(JsonObject jsonObject)
    {
        dateHeure = DateParser.ParseIso(jsonObject.getAsJsonPrimitive("dateHeure").getAsString());
        film = new Film(jsonObject.getAsJsonObject("film"));
        cinemaHref = jsonObject.getAsJsonPrimitive("cinema").getAsString();
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();

        /*json.addProperty("dateHeure",dateHeure);
        json.addProperty("film",film);
        json.addProperty("cinema",cinema);*/

        return json;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getCinemaHref() {
        return cinemaHref;
    }

    public void setCinemaHref(String cinemaHref) {
        this.cinemaHref = cinemaHref;
    }

    public DateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(DateTime dateHeure) {
        this.dateHeure = dateHeure;
    }
}
