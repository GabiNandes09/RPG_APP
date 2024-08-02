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
import com.nandes.rpgall.models.Rolagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RolagensFragment extends Fragment {

    private FragmentRolagensBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRolagensBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        String[] opcoes = {
                "D4 (4 lados)",
                "D6 (6 lados)",
                "D8 (8 lados)",
                "D10 (10 lados)",
                "D12 (12 lados)",
                "D20 (20 lados)",
                "D100 (100 lados)"
        };
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                opcoes
        );
        binding.spOpcoesRolar.setAdapter(spinnerAdapter);

        binding.btnRolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dice = 0;
                switch (binding.spOpcoesRolar.getSelectedItemPosition()) {
                    case 0:
                        dice = 4;
                        break;
                    case 1:
                        dice = 6;
                        break;
                    case 2:
                        dice = 8;
                        break;
                    case 3:
                        dice = 10;
                        break;
                    case 4:
                        dice = 12;
                        break;
                    case 5:
                        dice = 20;
                        break;
                    case 6:
                        dice = 100;
                        break;
                    default:
                        Toast.makeText(getContext(), "Selecione uma opção", Toast.LENGTH_SHORT).show();
                        return;
                }

                if (dice != 0) {
                    Random random = new Random();
                    int resultTotal = 0;
                    int qtd;
                    try {
                        qtd = Integer.parseInt(binding.etxtQtdDados.getText().toString());
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Quantidade inválida", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    List<Rolagem> dados = new ArrayList<>();


                    for (int i = 1; i <= qtd; i++) {
                        int critico = -1;
                        int result = random.nextInt(dice) + 1;
                        if (result == 1) {
                            critico = 0;
                        } else if (result == dice) {
                            critico = 1;
                        }
                        dados.add(new Rolagem(i, result, critico));
                        resultTotal += result;
                    }

                    Item_rolagens_lista_adapter adapter = new Item_rolagens_lista_adapter(dados);
                    binding.rvRolagens.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.rvRolagens.setAdapter(adapter);

                    binding.txtResultRolar.setText(String.valueOf(resultTotal));
                }
            }
        });

        return view;
    }
}
