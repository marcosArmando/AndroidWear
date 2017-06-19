package com.yucatancorp.petagram.RestApi2.adapter2;

import com.yucatancorp.petagram.RestApi2.ConstantesRestAPI2;
import com.yucatancorp.petagram.RestApi2.EndPoints2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcos on 28/05/2017.
 */

public class RestApiAdapter2 {

    public EndPoints2 establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI2.ROOT_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndPoints2.class);
    }
}
