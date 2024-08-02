package com.nandes.rpgall.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nandes.rpgall.activities.CriticosActivity;
import com.nandes.rpgall.databinding.FragmentTabelasFragmentsBinding;

public class TabelasFragments extends Fragment {

    private FragmentTabelasFragmentsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTabelasFragmentsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnCriticos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CriticosActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}