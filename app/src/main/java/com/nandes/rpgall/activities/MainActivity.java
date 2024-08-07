package com.nandes.rpgall.activities;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.nandes.rpgall.R;
import com.nandes.rpgall.databinding.ActivityMainBinding;
import com.nandes.rpgall.fragments.*;
import com.nandes.rpgall.helper.Permissao;
import com.nandes.rpgall.interfaces.IMainPresenter;
import com.nandes.rpgall.interfaces.IMainView;


public class MainActivity extends AppCompatActivity implements IMainView {
    private ActivityMainBinding binding;
    private IMainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainActivityPresenter(this, this);

        startFragment();

       binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return presenter.onBottonMenuClick(menuItem.getItemId());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void selectedFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(
                        com.google.android.material.R.anim.abc_slide_in_bottom,
                        com.google.android.material.R.anim.abc_slide_out_top,
                        com.google.android.material.R.anim.abc_slide_in_bottom,
                        com.google.android.material.R.anim.abc_slide_out_top
                )
                .replace(binding.fragmentContainerView.getId(), fragment)
                .commit();
    }

    @Override
    public void startFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.fragmentContainerView.getId(), presenter.getHomeFragment())
                .commit();
        binding.bottomNavView.setSelectedItemId(R.id.nav_home);
    }
}