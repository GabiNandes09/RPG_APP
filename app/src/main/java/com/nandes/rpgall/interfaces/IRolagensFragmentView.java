package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Rolagem;

import java.util.List;

public interface IRolagensFragmentView {

    void setSpinnerAdapter(List<String> opcoes);
    void configureResult(List<Rolagem> list, int result);
    void startDados();
}
