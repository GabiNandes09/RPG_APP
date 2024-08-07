package com.nandes.rpgall.interfaces;

import android.os.Bundle;

public interface ICadastroPersonagemPresenter {
    void onAddMesaButtonClick();
    void onSaveButtonClick();
    void loadPersonagemDetails(Bundle bundle);
    void onDestroy();
    void registerReceiver();
    void unregisterReceiver();
}
