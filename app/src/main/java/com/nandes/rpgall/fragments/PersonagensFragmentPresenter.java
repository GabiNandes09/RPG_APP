package com.nandes.rpgall.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;

import com.nandes.rpgall.activities.CadastroPersonagemActivity;
import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.interfaces.IPersonagemFragmentPresenter;
import com.nandes.rpgall.interfaces.IPersonagemFragmentView;
import com.nandes.rpgall.modelDAOs.PersonagensDAO;
import com.nandes.rpgall.models.Personagens;

import java.util.List;

public class PersonagensFragmentPresenter implements IPersonagemFragmentPresenter {

    private IPersonagemFragmentView view;
    private Context context;
    PersonagensDAO personagensDAO;
    Item_personagens_lista_adapter adapter;
    List<Personagens> personagensList;

    public PersonagensFragmentPresenter (IPersonagemFragmentView view, Context context){
        this.view = view;
        this.context = context;
        personagensDAO = new PersonagensDAO(context);
    }

    @Override
    public void criaAdapter() {
        personagensList = personagensDAO.listarTodosPersonagens();
        adapter = new Item_personagens_lista_adapter(personagensList, context);
        view.setAdapter(adapter);
    }
    @Override
    public void onBtnEditarClick() {
        Intent intent = new Intent(context, CadastroPersonagemActivity.class);
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
