package com.nandes.rpgall.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nandes.rpgall.databinding.FragmentAcertoCritBinding;
import com.nandes.rpgall.interfaces.IAcertoCritPresenter;
import com.nandes.rpgall.interfaces.IAcertoCritView;
import com.nandes.rpgall.models.Natureza_magia;
import com.nandes.rpgall.models.Tipo_Dano;

import java.util.List;

public class AcertoCritFragment extends Fragment implements IAcertoCritView {
    private FragmentAcertoCritBinding binding;
    private IAcertoCritPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAcertoCritBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        presenter = new AcertoCritFragmentPresenter(this, getContext());
        presenter.popularSpinners();

        binding.spTipoDano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (presenter.checkMagia((Tipo_Dano) parent.getSelectedItem())) {
                    showNaturez_Magia();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return view;
    }

    @Override
    public void showEfeito(String nome, String texto) {

    }

    @Override
    public void configSpinner(List<Tipo_Dano> tipos, List<Natureza_magia> naturezaMagias) {
        ArrayAdapter<Tipo_Dano> tipoAdapter = new ArrayAdapter<>(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                tipos
        );
        binding.spTipoDano.setAdapter(tipoAdapter);

        ArrayAdapter<Natureza_magia> naturezaAdapter = new ArrayAdapter<>(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                naturezaMagias
        );
        binding.spNaturezaDano.setAdapter(naturezaAdapter);
    }

    @Override
    public void showNaturez_Magia() {
        binding.contaninerSecundario.setVisibility(View.VISIBLE);
    }
}