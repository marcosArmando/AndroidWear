package com.yucatancorp.petagram.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yucatancorp.petagram.R;
import com.yucatancorp.petagram.adapter.MascotaAdaptador;
import com.yucatancorp.petagram.pojo.Mascota;
import com.yucatancorp.petagram.presentador.IRecyclerViewFragmentPresenter;
import com.yucatancorp.petagram.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by marcos on 11/01/2017.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{

    private ArrayList<Mascota> mascotas;
    private RecyclerView recyclerView;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_mascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());

        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        recyclerView.setAdapter(adaptador);
    }
}