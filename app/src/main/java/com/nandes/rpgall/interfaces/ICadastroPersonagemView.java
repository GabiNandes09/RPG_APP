package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.*;

import java.util.List;

public interface ICadastroPersonagemView {
    void showPJDetails(String nome, String nivel, int situacaoIndex, int mesaIndex, int classeIndex);
    void showToast(String message);
    void finishActivity();
    void setSpinnerAdapter(List<Situacao> situacoes, List<Mesa> mesas, List<Classe> classes);
    String getNome();
    String getNivel();
    int getSituacaoIndex();
    int getMesaIndex();
    int getClasseIndex();
}
