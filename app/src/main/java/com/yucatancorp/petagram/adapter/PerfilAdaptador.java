package com.yucatancorp.petagram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yucatancorp.petagram.R;
import com.yucatancorp.petagram.RestApi2.EndPoints2;
import com.yucatancorp.petagram.RestApi2.adapter2.RestApiAdapter2;
import com.yucatancorp.petagram.RestApi2.model2.UsuarioResponse;
import com.yucatancorp.petagram.detalle_foto2;
import com.yucatancorp.petagram.pojo.FotosMascota;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marcos on 12/01/2017.
 */

public class PerfilAdaptador extends RecyclerView.Adapter<PerfilAdaptador.PerfilViewHolder> {

    private ArrayList<FotosMascota> fotosMascotas;
    private Activity activity;

    public PerfilAdaptador(ArrayList<FotosMascota> fotosMascotas, Activity activity){
        this.fotosMascotas = fotosMascotas;
        this.activity = activity;
    }

    @Override
    public PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PerfilViewHolder holder, int position) {
        final FotosMascota fotosMascota = fotosMascotas.get(position);
        Picasso.with(activity)
                .load(fotosMascota.geturlFoto())
                .placeholder(R.drawable.chivo_1)
                .into(holder.imgFoto);
        //holder.imgFoto.setImageResource(fotosMascota.geturlFoto());
        holder.calificacion.setText(String.valueOf(fotosMascota.getCalif()));

        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(activity, detalle_foto2.class);
                intent.putExtra("url", fotosMascota.geturlFoto());
                intent.putExtra("likes", fotosMascota.getCalif());
                activity.startActivity(intent);*/
                Log.d("TOQUE_ANIMAL", "toque");
                UsuarioResponse usuarioResponse = new UsuarioResponse("-KlztBrQRpab0UUxOo6V", "123", "perro");
                RestApiAdapter2 restApiAdapter2 = new RestApiAdapter2();
                EndPoints2 endPoints2 = restApiAdapter2.establecerConexionRestAPI();
                Call<UsuarioResponse> usuarioResponseCall = endPoints2.toqueAnimal(usuarioResponse.getId(), usuarioResponse.getUser());
                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse1 = response.body();
                        Log.d("ID_FIREBASE", usuarioResponse1.getId());
                        Log.d("TOKEN_FIREBASE", usuarioResponse1.getToken());
                        Log.d("ANIMAL_FIREBASE", usuarioResponse1.getUser());
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return fotosMascotas.size();
    }

    public static class PerfilViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView calificacion;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotoMascota);
            calificacion = (TextView) itemView.findViewById(R.id.califPerfil);
        }
    }
}
