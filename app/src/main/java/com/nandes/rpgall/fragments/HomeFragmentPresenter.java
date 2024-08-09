package com.nandes.rpgall.fragments;

import android.content.Context;

import com.nandes.rpgall.MyApp;
import com.nandes.rpgall.R;
import com.nandes.rpgall.interfaces.IHomeFragmentPresenter;
import com.nandes.rpgall.interfaces.IHomeFragmentView;
import com.nandes.rpgall.modelDAOs.ClassesDAO;
import com.nandes.rpgall.modelDAOs.PersonagensDAO;
import com.nandes.rpgall.models.Personagens;

public class HomeFragmentPresenter implements IHomeFragmentPresenter {
    MyApp mMyApp = MyApp.get();
    IHomeFragmentView view;
    Context context;
    ClassesDAO classesDAO;
    PersonagensDAO personagensDAO;

    public HomeFragmentPresenter(IHomeFragmentView view, Context context){
        this.view = view;
        this.context = context;

        classesDAO = new ClassesDAO(context);
        personagensDAO = new PersonagensDAO(context);
    }

    @Override
    public void getPersonagemAtivo() {
        Personagens pj = mMyApp.getPjAtivo();
        if (pj == null){
            int id = personagensDAO.foundIDAtivo();
            pj = personagensDAO.getPersonagemByID(id);
            if (pj != null){
                MyApp.setPjAtivo(pj);
            }
        }
        if (pj==null){return;}
        String nome = context.getString(R.string.nome_label, pj.getNome());
        String nivel = context.getString(R.string.nivel_label, pj.getNivel());
        String classe = context.getString(R.string.classe_label, classesDAO.getClasseNomeByID(pj.getClasse()));

        view.showPjAtivo(nome, nivel, classe);
    }

    @Override
    public void getInfoMyApp() {

        String nome = mMyApp.getNomePerfil();
        String personagens = String.valueOf(mMyApp.getQtdPj());

        view.showAppInfos(nome, personagens);
    }

    @Override
    public void onDestroy() {
        view = null;
        classesDAO = null;
    }
}
