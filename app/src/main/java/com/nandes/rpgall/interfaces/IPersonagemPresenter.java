package com.nandes.rpgall.interfaces;

import android.os.Bundle;

public interface IPersonagemPresenter {

    void onEditButtonClick();
    void loadPJDetails(Bundle bundle);
    void onDestroy();
    void onAtivarButtonClick();
}
