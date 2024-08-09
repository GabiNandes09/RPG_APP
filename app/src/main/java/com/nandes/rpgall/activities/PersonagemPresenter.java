package com.nandes.rpgall.activities;

import android.content.Context;
import android.os.Bundle;

import com.nandes.rpgall.MyApp;
import com.nandes.rpgall.R;
import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.interfaces.IPersonagemPresenter;
import com.nandes.rpgall.interfaces.IPersonagemView;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.Personagens;

public class PersonagemPresenter implements IPersonagemPresenter {

    private IPersonagemView view;
    private Context context;
    private Personagens pj;
    private MesasDAO mesasDAO;
    private SituacaoDAO situacaoDAO;
    private ClassesDAO classesDAO;
    private PersonagensDAO personagensDAO;

    public PersonagemPresenter (IPersonagemView view, Context context){
        this.view = view;
        this.context = context;
        mesasDAO = new MesasDAO(context);
        situacaoDAO = new SituacaoDAO(context);
        classesDAO = new ClassesDAO(context);
        personagensDAO = new PersonagensDAO(context);
    }

    @Override
    public void onEditButtonClick() {
        view.navigateToEditScreen(pj);
    }

    @Override
    public void loadPJDetails(Bundle bundle) {
        if (bundle != null){
            pj = (Personagens)bundle.getSerializable(Item_personagens_lista_adapter.PERSONAGEM);
            if (pj != null){
                String nome = context.getString(R.string.nome_label, pj.getNome());
                String nivel = context.getString(R.string.nivel_label, pj.getNivel());
                String mesa = context.getString(R.string.mesa_label, mesasDAO.getMesaNomeByID(pj.getMesa()));
                String situacao = context.getString(R.string.situacao_label, situacaoDAO.getSituacaoNomeByID(pj.getSituacao()));
                String classe = context.getString(R.string.classe_label, classesDAO.getClasseNomeByID(pj.getClasse()));

                view.showPJDetails(nome, nivel, mesa, situacao, classe);
            }
        }
    }

    @Override
    public void onDestroy() {
        view = null;
        situacaoDAO = null;
        mesasDAO = null;
        classesDAO = null;
        pj = null;
    }

    @Override
    public void onAtivarButtonClick() {
        if (pj != null){
            int ativo = personagensDAO.getAtivo(pj.getId());
            if (ativo == 0){
                int ativoAtual = personagensDAO.foundIDAtivo();

                personagensDAO.changeAtivo(ativoAtual, 0);
                personagensDAO.changeAtivo(pj.getId(), 1);

                MyApp.setPjAtivo(pj);
                view.showToast("Personagem ativado");
                return;
            }
            view.showToast("Personagem ativo");
            return;
        }
        view.showToast("Algo deu errado");
    }
}
