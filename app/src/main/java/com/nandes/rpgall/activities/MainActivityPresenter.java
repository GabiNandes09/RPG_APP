package com.nandes.rpgall.activities;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.nandes.rpgall.R;
import com.nandes.rpgall.fragments.HomeFragment;
import com.nandes.rpgall.fragments.PersonagensFragment;
import com.nandes.rpgall.fragments.RolagensFragment;
import com.nandes.rpgall.fragments.TabelasFragments;
import com.nandes.rpgall.helper.Permissao;
import com.nandes.rpgall.interfaces.IMainPresenter;
import com.nandes.rpgall.interfaces.IMainView;

public class MainActivityPresenter implements IMainPresenter {

    private IMainView view;
    private final Context context;

    private final HomeFragment homeFragment;
    private final RolagensFragment rolagensFragment;
    private final PersonagensFragment personagensFragment;
    private final TabelasFragments tabelasFragments;
    private final Permissao permissao;

    public MainActivityPresenter (IMainView view, Context context){
        this.view = view;
        this.context = context;
        homeFragment = new HomeFragment();
        rolagensFragment = new RolagensFragment();
        tabelasFragments = new TabelasFragments();
        personagensFragment = new PersonagensFragment();
        permissao = new Permissao();
    }

    @Override
    public boolean onBottonMenuClick(int id) {
        if (view!=null){
            if (id == R.id.nav_home){
                view.selectedFragment(homeFragment);
                return true;
            } else if (id == R.id.nav_rolar){
                view.selectedFragment(rolagensFragment);
                return true;
            } else if (id == R.id.nav_pj){
                view.selectedFragment(personagensFragment);
                return true;
            } else if (id == R.id.nav_tabela){
                view.selectedFragment(tabelasFragments);
                return true;
            }
        }
        return false;
    }

    @Override
    public void requestPermission() {
        if (view!=null){
            permissao.requisitarPermissao((Activity) context);
        }
    }

    @Override
    public Fragment getHomeFragment() {
        return homeFragment;
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
