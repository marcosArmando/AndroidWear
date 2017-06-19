package com.yucatancorp.petagram.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yucatancorp.petagram.restAPI.ConstantesRestApi;
import com.yucatancorp.petagram.restAPI.EndPointApi;
import com.yucatancorp.petagram.restAPI.deserializador.contactoDeserializador;
import com.yucatancorp.petagram.restAPI.deserializador.mascotaLogInDes;
import com.yucatancorp.petagram.restAPI.model.mascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcos on 21/04/2017.
 */

public class RestApiAdapter {

    public EndPointApi establecerConexionRestApiInstagram(Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPointApi.class);
    }

    public Gson construyeUnGsonDeserializadorMediaRecent()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(mascotaResponse.class, new contactoDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorSearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(mascotaResponse.class,new mascotaLogInDes());

        return   gsonBuilder.create();
    }
}
