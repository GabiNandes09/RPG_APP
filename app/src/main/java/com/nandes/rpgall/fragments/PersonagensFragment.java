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
import com.nandes.rpgall.modelDAOs.PersonagensDAO;
import com.nandes.rpgall.models.Personagens;

import java.util.List;

public class PersonagensFragment extends Fragment {

    FragmentPersonagensBinding binding;
    PersonagensDAO personagensDAO;
    Item_personagens_lista_adapter adapter;
    List<Personagens> personagensList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPersonagensBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        personagensDAO = new PersonagensDAO(this.getContext());


        atualizarListaPersonagens();




        binding.btnAddPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CadastroPersonagemActivity.class);


                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });



        return view;
    }

    private void atualizarListaPersonagens() {

        personagensList = personagensDAO.listarTodosPersonagens();
        adapter = new Item_personagens_lista_adapter(personagensList, getContext());
        binding.rvPersonagens.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPersonagens.setAdapter(adapter);
        binding.rvPersonagens.addItemDecoration(new DividerItemDecoration(this.getContext(), RecyclerView.HORIZONTAL));
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizarListaPersonagens();
    }
}