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
    private String nomFIlm;

    public Horaire(JsonObject jsonObject)
    {

        dateHeure = DateParser.ParseIso(jsonObject.getAsJsonPrimitive("dateHeure").getAsString());
        nomFIlm = jsonObject.getAsJsonPrimitive("titre").getAsString();


    }

    public DateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(DateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getNomFIlm() {
        return nomFIlm;
    }

    public void setNomFIlm(String nomFIlm) {
        this.nomFIlm = nomFIlm;
    }
}
