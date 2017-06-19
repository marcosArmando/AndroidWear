package com.yucatancorp.petagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yucatancorp.petagram.pojo.FotosMascota;
import com.yucatancorp.petagram.restAPI.ConstantesRestApi;
import com.yucatancorp.petagram.restAPI.EndPointApi;
import com.yucatancorp.petagram.restAPI.JsonKeys;
import com.yucatancorp.petagram.restAPI.adapter.RestApiAdapter;
import com.yucatancorp.petagram.restAPI.model.mascotaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn_Activity extends AppCompatActivity {

    private static final String TAG = LogIn_Activity.class.getName();
    private TextView nombreUsuario;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_);

        nombreUsuario = (TextView) findViewById(R.id.nombreUsuario);
        activity = this;

    }


    public void contactoB(final View view) {

        final String nombre = nombreUsuario.getText().toString();

        RestApiAdapter raa = new RestApiAdapter();
        Gson gsonSearch = raa.construyeGsonDeserializadorSearch();
        EndPointApi epa = raa.establecerConexionRestApiInstagram(gsonSearch);

        final Call<mascotaResponse> mascotaResponseCall = epa.search(nombre, ConstantesRestApi.ACCESS_TOKEN);

        mascotaResponseCall.enqueue(new Callback<mascotaResponse>() {
            @Override
            public void onResponse(Call<mascotaResponse> call, Response<mascotaResponse> response) {

                List<FotosMascota> mascotas = response.body().getFotosMascotas();
                Intent intent2 = new Intent(LogIn_Activity.this, MainActivity.class);
                intent2.putExtra("cuenta", nombre);
                startActivity(intent2);

                if (mascotas != null && !mascotas.isEmpty()) {

                    guardarPreferenciasUsuario(response.body().getFotosMascotas().get(0));
                    Intent intent = new Intent(LogIn_Activity.this, MainActivity.class);
                    startActivity(intent);
                    ActivityCompat.finishAffinity(LogIn_Activity.this);
                }else{
                    Snackbar.make(view,"No se encontró el usuario ingresado.",Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<mascotaResponse> call, Throwable t) {
                Toast.makeText(LogIn_Activity.this, "Fallo conexion, intente de nuevo.", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(LogIn_Activity.this, MainActivity.class);
                intent2.putExtra("cuenta", nombre);
                startActivity(intent2);
                Log.e(TAG, "Fallo conexion: " + t.toString());
            }
        });


    }


    public void guardarPreferenciasUsuario(FotosMascota mascota) {

        SharedPreferences preps = getSharedPreferences("datosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preps.edit();

        String profilePicture = mascota.geturlFoto();
        String nombre = mascota.getNombreCompleto();
        String idUsuario = mascota.getId();

        edit.putString(JsonKeys.USER_ID, idUsuario);
        edit.putString(JsonKeys.USER_FULLNAME, nombre);
        edit.putString(JsonKeys.MEDIA_URL, profilePicture);

        edit.commit();

    }



}
