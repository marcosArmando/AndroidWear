package com.yucatancorp.petagram.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yucatancorp.petagram.adapter.PerfilAdaptador;
import com.yucatancorp.petagram.R;
import com.yucatancorp.petagram.pojo.FotosMascota;
import com.yucatancorp.petagram.presentador.IPerfilFragmentPresenter;
import com.yucatancorp.petagram.presentador.PerfilFragmentPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragment {

    private ArrayList<FotosMascota> fotosMascotas;
    private RecyclerView recyclerViewP;
    private IPerfilFragmentPresenter iPerfilFragmentPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v2 = inflater.inflate(R.layout.fragment_perfil, container, false);

        recyclerViewP = (RecyclerView) v2.findViewById(R.id.perfil_fragment);
        iPerfilFragmentPresenter = new PerfilFragmentPresenter(this, getContext());

        // Inflate the layout for this fragment
        return v2;
    }


    @Override
    public void generarGridLayout() {
        GridLayoutManager gmm = new GridLayoutManager(getContext(), 2);
        recyclerViewP.setLayoutManager(gmm);
    }

    @Override
    public PerfilAdaptador crearAdaptador(ArrayList<FotosMascota> fotosmascotas2) {
        PerfilAdaptador perfilAdaptador = new PerfilAdaptador(fotosmascotas2, getActivity());
        return perfilAdaptador;

    }

    @Override
    public void inicializarAdaptadorRV(PerfilAdaptador perfilAdaptador) {
        recyclerViewP.setAdapter(perfilAdaptador);
    }
}
