package com.nandes.rpgall.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nandes.rpgall.R;
import com.nandes.rpgall.databinding.FragmentHomeBinding;
import com.nandes.rpgall.interfaces.IHomeFragmentPresenter;
import com.nandes.rpgall.interfaces.IHomeFragmentView;


public class HomeFragment extends Fragment implements IHomeFragmentView {

    private FragmentHomeBinding binding;
    private IHomeFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        presenter = new HomeFragmentPresenter(this, getContext());

        presenter.getInfoMyApp();
        presenter.getPersonagemAtivo();

        return view;
    }

    @Override
    public void showPjAtivo(String nome, String nivel, String classe) {
        binding.txtPjNome.setText(nome);
        binding.txtPjNivel.setText(nivel);
        binding.txtPjClasse.setText(classe);
    }

    @Override
    public void showAppInfos(String nome, String personagens) {
        binding.txtNomePerfil.setText(nome);
        binding.txtQtdPJ.setText(personagens);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}