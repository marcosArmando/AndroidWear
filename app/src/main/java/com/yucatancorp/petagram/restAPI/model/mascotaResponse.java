package com.yucatancorp.petagram.restAPI.model;

import com.yucatancorp.petagram.pojo.FotosMascota;

import java.util.ArrayList;

/**
 * Created by marcos on 20/04/2017.
 */

public class mascotaResponse {

    ArrayList<FotosMascota> fotosMascotas;

    public ArrayList<FotosMascota> getFotosMascotas() {
        return fotosMascotas;
    }

    public void setFotosMascotas(ArrayList<FotosMascota> fotosMascotas) {
        this.fotosMascotas = fotosMascotas;
    }
}
