package com.nandes.rpgall.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.dialogues.DialogAddMesas;
import com.nandes.rpgall.interfaces.ICadastroPersonagemPresenter;
import com.nandes.rpgall.interfaces.ICadastroPersonagemView;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.*;

import java.util.List;

public class CadastroPersonagemPresenter implements ICadastroPersonagemPresenter {
    private ICadastroPersonagemView view;
    private Context context;
    private Personagens pj;
    private PersonagensDAO personagensDAO;
    private SituacaoDAO situacaoDAO;
    private ClassesDAO classesDAO;
    private MesasDAO mesasDAO;
    private List<Situacao> situacoes;
    private List<Classe> classes;
    private List<Mesa> mesas;

    private static final String ACTION_UPDATE_MESAS = "com.nandes.rpgall.UPDATE_MESAS";
    private final BroadcastReceiver mesasReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_UPDATE_MESAS.equals(intent.getAction())) {
                configureSpinners();
            }
        }
    };
    public CadastroPersonagemPresenter(ICadastroPersonagemView view, Context context) {
        this.view = view;
        this.context = context;
        personagensDAO = new PersonagensDAO(context);
        situacaoDAO = new SituacaoDAO(context);
        classesDAO = new ClassesDAO(context);
        mesasDAO = new MesasDAO(context);
        configureSpinners();
    }

    private void configureSpinners() {
        if (view != null){
            situacoes = situacaoDAO.getAllSituacao();
            classes = classesDAO.getAllClasses();
            mesas = mesasDAO.getAllMesas();
            view.setSpinnerAdapter(situacoes, mesas, classes);
        }
    }

    @Override
    public void onAddMesaButtonClick() {
        DialogAddMesas dialog = new DialogAddMesas(context);
        dialog.show();
    }

    @Override
    public void onSaveButtonClick() {
        if (
                !view.getNome().isEmpty() &&
                        !view.getNivel().isEmpty()
        ) {
            Situacao situacao = situacoes.get(view.getSituacaoIndex());
            Mesa mesa = mesas.get(view.getMesaIndex());
            Classe classe = classes.get(view.getClasseIndex());

            personagensDAO.salvarPersonagem(new Personagens(
                    view.getNome(),
                    Integer.parseInt(view.getNivel()),
                    classe.getId(),
                    mesa.getId(),
                    situacao.getId()
            ));

            view.finishActivity();
        } else {
            view.showToast("Preencha todos os dados");
        }
    }

    @Override
    public void loadPersonagemDetails(Bundle bundle) {
        if (bundle != null) {
            pj = (Personagens) bundle.getSerializable(Item_personagens_lista_adapter.PERSONAGEM);
            if (pj != null) {
                int situacaoIndex = setSituacao(pj.getSituacao(), situacoes);
                int mesaIndex = setMesa(pj.getMesa(), mesas);
                int classeIndex = setClasse(pj.getClasse(), classes);
                view.showPJDetails(pj.getNome(), String.valueOf(pj.getNivel()), situacaoIndex, mesaIndex, classeIndex);
            }
        }
    }

    @Override
    public void registerReceiver() {
        IntentFilter filter = new IntentFilter(ACTION_UPDATE_MESAS);
        context.registerReceiver(mesasReceiver, filter);
    }

    @Override
    public void unregisterReceiver() {
        context.unregisterReceiver(mesasReceiver);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    private int setSituacao(int id, List<Situacao> list) {
        int index = -1;
        for (Situacao sit : list) {
            if (sit.getId() == id) {
                index = list.indexOf(sit);
            }
        }
        return index;
    }

    private int setMesa(int id, List<Mesa> list) {
        int index = -1;
        for (Mesa mesa : list) {
            if (mesa.getId() == id) {
                index = list.indexOf(mesa);
            }
        }
        return index;
    }

    private int setClasse(int id, List<Classe> list) {
        int index = -1;
        for (Classe classe : list) {
            if (classe.getId() == id) {
                index = list.indexOf(classe);
            }
        }
        return index;
    }

}
