package com.nandes.rpgall.fragments;

import android.content.Context;
import android.widget.Toast;

import com.nandes.rpgall.interfaces.IRolagensFragmentPresenter;
import com.nandes.rpgall.interfaces.IRolagensFragmentView;
import com.nandes.rpgall.models.Rolagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class RolagensFragmentPresenter implements IRolagensFragmentPresenter {

    private IRolagensFragmentView view;
    private Context context;


    ArrayList<String> opcoes = new ArrayList<>(Arrays.asList(
            "D4 (4 lados)",
            "D6 (6 lados)",
            "D8 (8 lados)",
            "D10 (10 lados)",
            "D12 (12 lados)",
            "D20 (20 lados)",
            "D100 (100 lados)"
    ));


    public RolagensFragmentPresenter(IRolagensFragmentView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void configureSpinner() {
        view.setSpinnerAdapter(opcoes);
    }

    @Override
    public void rolarDado(int dado, int qtdDado) {
        int dice = 0;
        switch (dado) {
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
                Toast.makeText(context, "Selecione uma opção", Toast.LENGTH_SHORT).show();
                return;
        }

        if (dice != 0) {
            Random random = new Random();
            int resultTotal = 0;

            if (qtdDado <= 0) {
                Toast.makeText(context, "Quantidade inválida", Toast.LENGTH_SHORT).show();
                return;
            }

            List<Rolagem> dados = new ArrayList<>();


            for (int i = 1; i <= qtdDado; i++) {
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
            view.configureResult(dados, resultTotal);
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}