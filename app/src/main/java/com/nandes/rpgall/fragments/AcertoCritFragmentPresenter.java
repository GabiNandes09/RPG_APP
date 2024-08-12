package com.nandes.rpgall.fragments;

import android.content.Context;

import com.nandes.rpgall.interfaces.IAcertoCritPresenter;
import com.nandes.rpgall.interfaces.IAcertoCritView;
import com.nandes.rpgall.modelDAOs.Natureza_MagiaDAO;
import com.nandes.rpgall.modelDAOs.Tipo_danoDAO;
import com.nandes.rpgall.models.Natureza_magia;
import com.nandes.rpgall.models.Tipo_Dano;

import java.util.List;

public class AcertoCritFragmentPresenter implements IAcertoCritPresenter {

    private IAcertoCritView view;
    private Context context;
    private Tipo_danoDAO tipo_danoDAO;
    private Natureza_MagiaDAO naturezaMagiaDAO;



    public AcertoCritFragmentPresenter (IAcertoCritView view, Context context){
        this.view = view;
        this.context = context;
        tipo_danoDAO = new Tipo_danoDAO(context);
        naturezaMagiaDAO = new Natureza_MagiaDAO(context);
    }
    @Override
    public void onRolarButtonClick() {

    }

    @Override
    public void popularSpinners() {
        List<Tipo_Dano> tipos = tipo_danoDAO.getAllTipo_Dano();
        List<Natureza_magia> natureza = naturezaMagiaDAO.getAllNatureza_Magia();

        if (tipos==null||natureza==null){return;}

        view.configSpinner(tipos, natureza);
    }

    @Override
    public boolean checkMagia(Tipo_Dano tipoDano) {
        if (tipoDano.getId() == 4){
            return true;
        }
        return false;
    }
}
