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
 * Created by marcos on 23/04/2017.
 */

public class contactoDeserializador implements JsonDeserializer<mascotaResponse> {

    @Override
    public mascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        mascotaResponse mascotaresponse = gson.fromJson(json, mascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaresponse.setFotosMascotas(deserializadorMascotaDeJson(mascotaResponseData));

        return mascotaresponse;
    }

    private ArrayList<FotosMascota> deserializadorMascotaDeJson(JsonArray mascotaResponseData)
    {
        ArrayList<FotosMascota> fotosMascotas = new ArrayList<>();

        for (int i = 0; i < mascotaResponseData.size(); i++) {
            JsonObject fotoMascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject userJson   = fotoMascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id             = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imgJson           = fotoMascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson = imgJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto               = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = fotoMascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            FotosMascota fotoActual = new FotosMascota();
            fotoActual.setId(id);
            fotoActual.setNombreCompleto(nombreCompleto);
            fotoActual.seturlFoto(urlFoto);
            fotoActual.setCalif(likes);

            fotosMascotas.add(fotoActual);
        }

        return fotosMascotas;
    }
}
