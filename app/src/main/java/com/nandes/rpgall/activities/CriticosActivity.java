package com.nandes.rpgall.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationBarView;
import com.nandes.rpgall.R;
import com.nandes.rpgall.databinding.ActivityCriticosBinding;
import com.nandes.rpgall.fragments.AcertoCritFragment;
import com.nandes.rpgall.fragments.ErroCritFragment;
import com.nandes.rpgall.fragments.HomeCritFragment;

public class CriticosActivity extends AppCompatActivity {

    private ActivityCriticosBinding binding;
    private final HomeCritFragment homeCritFragment = new HomeCritFragment();
    private final AcertoCritFragment acertoCritFragment = new AcertoCritFragment();
    private final ErroCritFragment erroCritFragment = new ErroCritFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCriticosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager()
                .beginTransaction()
                .add(binding.fragmentContainerView2.getId(), homeCritFragment)
                .commit();

        binding.btNavBar.setSelectedItemId(R.id.crit_home);

        binding.btNavBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.crit_home) {
                    selectFragment(homeCritFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.crit_acerto) {
                    selectFragment(acertoCritFragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.crit_erro) {
                    selectFragment(erroCritFragment);
                    return true;
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
                .replace(binding.fragmentContainerView2.getId(), fragment)
                .commit();
    }
}