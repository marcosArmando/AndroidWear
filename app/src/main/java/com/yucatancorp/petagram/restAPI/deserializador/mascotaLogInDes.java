package com.yucatancorp.petagram.restAPI.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yucatancorp.petagram.pojo.FotosMascota;
import com.yucatancorp.petagram.restAPI.JsonKeys;
import com.yucatancorp.petagram.restAPI.model.mascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by marcos on 02/05/2017.
 */

public class mascotaLogInDes implements JsonDeserializer<mascotaResponse> {

    @Override
    public mascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        mascotaResponse contactoResponse = gson.fromJson(json,mascotaResponse.class);

        JsonArray contactoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setFotosMascotas(deserializarMascotaDeJson(contactoResponseData));

        return contactoResponse;
    }


    private ArrayList<FotosMascota> deserializarMascotaDeJson(JsonArray contactoResponseData){
        ArrayList<FotosMascota> mascotas = new ArrayList<>();

        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseDataObject = contactoResponseData.get(i).getAsJsonObject();

            String id = contactoResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = contactoResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String profilePicture = contactoResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();

            FotosMascota mascota = new FotosMascota();
            mascota.setId(id);
            mascota.setNombreCompleto(nombreCompleto);
            mascota.seturlFoto(profilePicture);

            mascotas.add(mascota);

        }
        return mascotas;
    }
}
