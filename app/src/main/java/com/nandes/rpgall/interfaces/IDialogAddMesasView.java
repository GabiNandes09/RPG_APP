package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Situacao;

import java.util.List;

public interface IDialogAddMesasView {

    void setSpinner(List<Situacao> situacoes);
    void onSalvarClick();
    void showToast(String message);
}
