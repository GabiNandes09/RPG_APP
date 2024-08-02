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


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private final HomeFragment homeFragment = new HomeFragment();
    RolagensFragment rolagensFragment = new RolagensFragment();
    PersonagensFragment personagensFragment = new PersonagensFragment();
    TabelasFragments tabelasFragments = new TabelasFragments();
    private Permissao permissao;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.fragmentContainerView.getId(), homeFragment)
                .commit();

        binding.bottomNavView.setSelectedItemId(R.id.nav_home);

        permissao = new Permissao();
        permissao.requisitarPermissao(this);

       binding.bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.nav_home){
                    selectFragment(homeFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_rolar){
                    selectFragment(rolagensFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_pj){
                    selectFragment(personagensFragment);
                } else if (menuItem.getItemId() == R.id.nav_tabela){
                    selectFragment(tabelasFragments);
                }
                return true;
            }
        });

    }

    public void selectFragment (Fragment fragment){
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
}