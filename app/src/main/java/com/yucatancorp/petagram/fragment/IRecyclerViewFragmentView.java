package com.yucatancorp.petagram.fragment;

import com.yucatancorp.petagram.adapter.MascotaAdaptador;
import com.yucatancorp.petagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by marcos on 29/03/2017.
 */

public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
