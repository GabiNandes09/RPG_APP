package com.nandes.rpgall.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.nandes.rpgall.adapters.Item_rolagens_lista_adapter;
import com.nandes.rpgall.databinding.FragmentRolagensBinding;
import com.nandes.rpgall.interfaces.IRolagensFragmentPresenter;
import com.nandes.rpgall.interfaces.IRolagensFragmentView;
import com.nandes.rpgall.models.Rolagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RolagensFragment extends Fragment implements IRolagensFragmentView {

    private FragmentRolagensBinding binding;
    private IRolagensFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRolagensBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        presenter = new RolagensFragmentPresenter(this, getContext());

        presenter.configureSpinner();

        binding.btnRolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startDados();
            }
        });
        return view;
    }
    @Override
    public void configureResult(List<Rolagem> list, int result) {
        Item_rolagens_lista_adapter adapter = new Item_rolagens_lista_adapter(list);
        binding.rvRolagens.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvRolagens.setAdapter(adapter);

        binding.txtResultRolar.setText(String.valueOf(result));
    }

    @Override
    public void startDados() {
        int qtdDado = Integer.parseInt(binding.etxtQtdDados.getText().toString());
        int dado = binding.spOpcoesRolar.getSelectedItemPosition();
        presenter.rolarDado(dado, qtdDado);
    }

    @Override
    public void setSpinnerAdapter(List<String> opcoes) {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                opcoes
        );
        binding.spOpcoesRolar.setAdapter(spinnerAdapter);
    }
}
