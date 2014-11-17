package ca.qc.cstj.android.cinecho.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import ca.qc.cstj.android.cinecho.helpers.DateParser;

/**
 * Created by 1132157 on 2014-11-07.
 */
public class Horaire {

    private DateTime heure1;
    private DateTime heure2;
    private String nomFIlm;

    public Horaire(JsonObject jsonObject)
    {
        if(jsonObject != null) {
            if(jsonObject.has("listHoraires")) {
                JsonArray array = jsonObject.getAsJsonArray("listHoraires");
                // faire la boucle
                /*ArrayList<DateTime> presentation = new ArrayList<DateTime>();
                for (JsonElement element : array) {
                    presentation.add(new DateTime(DateParser.ParseIso(jsonObject.getAsJsonPrimitive("dateHeure").getAsString())));
                }*/
                String var = array.get(0).getAsJsonObject().getAsJsonPrimitive("dateHeure").toString();
                if (array.size() > 0)
                    heure1 = new DateTime(array.get(0).getAsJsonObject().getAsJsonPrimitive("dateHeure").getAsString());
                if (array.size() > 1)
                    heure2 = new DateTime(array.get(1).getAsJsonObject().getAsJsonPrimitive("dateHeure").getAsString());
            }
            nomFIlm = jsonObject.getAsJsonPrimitive("titre").getAsString();
        }else{
            nomFIlm = "Aucun titre";
        }


    }


    public DateTime getHeure2() {
        return heure2;
    }

    public void setHeure2(DateTime heure2) {
        this.heure2 = heure2;
    }

    public DateTime getHeure1() {
        return heure1;
    }

    public void setHeure1(DateTime heure1) {
        this.heure1 = heure1;
    }

    public String getNomFIlm() {
        return nomFIlm;
    }

    public void setNomFIlm(String nomFIlm) {
        this.nomFIlm = nomFIlm;
    }
}
