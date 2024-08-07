package com.nandes.rpgall.interfaces;

import com.nandes.rpgall.models.Personagens;

import java.util.List;

public interface Iitem_Personagem_Lista_Adapter_Presenter {

    void getPJDetails(List<Personagens> list, int position);
    void onItemClick();
    void onDeleteClick(List<Personagens> list, int position);
    void onComentsClick();
    void onDestroy();
}
