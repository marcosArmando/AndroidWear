package com.yucatancorp.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yucatancorp.petagram.fragment.IPerfilFragment;
import com.yucatancorp.petagram.pojo.FotosMascota;
import com.yucatancorp.petagram.restAPI.EndPointApi;
import com.yucatancorp.petagram.restAPI.adapter.RestApiAdapter;
import com.yucatancorp.petagram.restAPI.model.mascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marcos on 21/04/2017.
 */

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private IPerfilFragment iPerfilFragment;
    private Context context;
    //private ConstruntorMascotas constructorMascotas;
    private ArrayList<FotosMascota> fotosMascotas;

    public PerfilFragmentPresenter (IPerfilFragment iPerfilFragment, Context context)
    {
        this.iPerfilFragment = iPerfilFragment;
        this.context = context;
        obtenerMediosRecientes();
    }


    @Override
    public void obtenerPeriflMascotaBaseDatos() {

    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeUnGsonDeserializadorMediaRecent();
        EndPointApi endPointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<mascotaResponse> mascotaResponseCall = endPointApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<mascotaResponse>() {
            @Override
            public void onResponse(Call<mascotaResponse> call, Response<mascotaResponse> response) {
                mascotaResponse mascotaresponse = response.body();
                fotosMascotas = mascotaresponse.getFotosMascotas();
                mostrarPerfilMascotaRV();
            }

            @Override
            public void onFailure(Call<mascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_LONG).show();
                Log.e("Falló la conexión", t.toString());
            }
        });

    }

    @Override
    public void mostrarPerfilMascotaRV() {
        iPerfilFragment.inicializarAdaptadorRV(iPerfilFragment.crearAdaptador(fotosMascotas));
        iPerfilFragment.generarGridLayout();
    }
}
