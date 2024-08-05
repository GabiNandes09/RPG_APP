package com.nandes.rpgall.fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nandes.rpgall.activities.CadastroPersonagemActivity;
import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.databinding.FragmentPersonagensBinding;
import com.nandes.rpgall.interfaces.IPersonagemFragmentPresenter;
import com.nandes.rpgall.interfaces.IPersonagemFragmentView;
import com.nandes.rpgall.modelDAOs.PersonagensDAO;
import com.nandes.rpgall.models.Personagens;

import java.util.List;
import java.util.Objects;

public class PersonagensFragment extends Fragment implements IPersonagemFragmentView {

    FragmentPersonagensBinding binding;

    private IPersonagemFragmentPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPersonagensBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        presenter = new PersonagensFragmentPresenter(this, getContext());

        presenter.criaAdapter();

        binding.btnAddPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtnEditarClick();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.criaAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        binding.rvPersonagens.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPersonagens.setAdapter(adapter);
        binding.rvPersonagens.addItemDecoration(new DividerItemDecoration(this.requireContext(), RecyclerView.HORIZONTAL));
    }


}