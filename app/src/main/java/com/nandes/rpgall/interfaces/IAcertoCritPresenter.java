package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Tipo_Dano;

public interface IAcertoCritPresenter {
    void onRolarButtonClick();
    void popularSpinners();
    boolean checkMagia(Tipo_Dano tipoDano);

}
