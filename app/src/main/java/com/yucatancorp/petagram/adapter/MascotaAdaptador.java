package com.yucatancorp.petagram.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yucatancorp.petagram.db.ConstructorMascotas;
import com.yucatancorp.petagram.pojo.Mascota;
import com.yucatancorp.petagram.R;

import java.util.ArrayList;

/**
 * Created by marcos on 11/12/2016.
 */

public class  MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota , parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.nombreMascota.setText(mascota.getNombre());
        mascotaViewHolder.calificacion.setText(String.valueOf(mascota.getRanking()));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "has dado click a " + mascota.getNombre(), Toast.LENGTH_LONG).show();
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);

                mascotaViewHolder.calificacion.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;
        private TextView nombreMascota;
        private TextView calificacion;
        private ImageView btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFotoMascota);
            nombreMascota = (TextView) itemView.findViewById(R.id.tvNombre_mascota);
            calificacion = (TextView) itemView.findViewById(R.id.calif);
            btnLike = (ImageView) itemView.findViewById(R.id.imgHuesoCalif);
        }
    }

}
