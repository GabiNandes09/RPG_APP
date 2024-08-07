package com.nandes.rpgall.interfaces;

import androidx.fragment.app.Fragment;

public interface IMainPresenter {
    boolean onBottonMenuClick(int id);
    void requestPermission();
    Fragment getHomeFragment();
    void onDestroy();
}
