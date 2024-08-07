package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Personagens;

import java.util.List;

public interface Iitem_Personagem_Lista_Adapter_View {

    void showPJDetails(String nome, String nivel, String mesa, String situacao, String classe);
    void showToast(String message);
    void notifyRecyclerViewDelete(List<Personagens> list, int position);
}
