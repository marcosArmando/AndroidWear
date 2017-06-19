package com.yucatancorp.petagram.fragment;

import com.yucatancorp.petagram.adapter.PerfilAdaptador;
import com.yucatancorp.petagram.pojo.FotosMascota;

import java.util.ArrayList;

/**
 * Created by marcos on 21/04/2017.
 */

public interface IPerfilFragment {

        public void generarGridLayout();

        public PerfilAdaptador crearAdaptador(ArrayList<FotosMascota> fotosmascotas);

        public void inicializarAdaptadorRV(PerfilAdaptador adaptador);
}
