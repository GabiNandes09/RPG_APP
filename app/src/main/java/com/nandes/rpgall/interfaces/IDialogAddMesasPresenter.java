package com.nandes.rpgall.interfaces;

public interface IDialogAddMesasPresenter {
    void configurarSpinner();
    void salvarMesa(int id_Situacao, String nome);
    void onDestroy();
}
