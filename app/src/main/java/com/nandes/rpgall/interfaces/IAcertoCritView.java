package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Natureza_magia;
import com.nandes.rpgall.models.Tipo_Dano;

import java.util.List;

public interface IAcertoCritView {

    void showEfeito(String nome, String texto);
    void configSpinner(List<Tipo_Dano> tipos, List<Natureza_magia> naturezaMagias);
    void showNaturez_Magia();

}
