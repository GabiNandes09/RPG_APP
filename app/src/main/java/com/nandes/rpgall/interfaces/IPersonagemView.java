package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Personagens;

public interface IPersonagemView {

    void showPJDetails(String nome, String nivel, String mesa, String situacao, String classe);
    void navigateToEditScreen(Personagens pj);

    interface onCadastroPersonagem{

    }

}
