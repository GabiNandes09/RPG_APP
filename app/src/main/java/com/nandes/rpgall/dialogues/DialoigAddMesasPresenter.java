package com.nandes.rpgall.dialogues;

import android.content.Context;
import android.content.Intent;

import com.nandes.rpgall.interfaces.IDialogAddMesasPresenter;
import com.nandes.rpgall.interfaces.IDialogAddMesasView;
import com.nandes.rpgall.modelDAOs.MesasDAO;
import com.nandes.rpgall.modelDAOs.SituacaoDAO;
import com.nandes.rpgall.models.Mesa;
import com.nandes.rpgall.models.Situacao;

import java.util.ArrayList;
import java.util.List;

public class DialoigAddMesasPresenter implements IDialogAddMesasPresenter {

    private IDialogAddMesasView view;
    private Context context;

    MesasDAO mesasDAO;
    SituacaoDAO situacaoDAO;
    List<Situacao> situacoes;

    public DialoigAddMesasPresenter (IDialogAddMesasView view, Context context){
        this.view = view;
        this.context = context;
        mesasDAO = new MesasDAO(context);
        situacaoDAO = new SituacaoDAO(context);
    }

    @Override
    public void configurarSpinner() {
        situacoes = situacaoDAO.getAllSituacao();
        if(situacoes != null){
            List<Situacao> opcoes = new ArrayList<>();
            for (Situacao situacao : situacoes){
                if (situacao.getId() != 4){
                    opcoes.add(situacao);
                }
            }
            view.setSpinner(opcoes);
        }
    }

    @Override
    public void salvarMesa(int id_Situacao, String nome) {
        Mesa mesa = new Mesa(
                nome,
                id_Situacao);

        try {
            mesasDAO.gravarMesa(mesa);

            Intent intent = new Intent(DialogAddMesas.ACTION_UPDATE_MESAS);
            context.sendBroadcast(intent);

            view.showToast("Mesa salva com sucesso");
        } catch (Exception e){
            view.showToast("Algo deu errado ao salvar a mesa");
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
