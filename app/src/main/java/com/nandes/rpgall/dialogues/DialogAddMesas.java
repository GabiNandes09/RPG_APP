package com.nandes.rpgall.dialogues;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.nandes.rpgall.databinding.DialogueAddMesasBinding;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.*;

import java.util.ArrayList;
import java.util.List;

public class DialogAddMesas extends Dialog {
    private Context mContext;
    MesasDAO mesasDAO;
    SituacaoDAO situacaoDAO;
    private DialogueAddMesasBinding binding;
    List<Situacao> situacoes;

    private static final String ACTION_UPDATE_MESAS = "com.nandes.rpgall.UPDATE_MESAS";


    public DialogAddMesas(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogueAddMesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mesasDAO = new MesasDAO(getContext());
        situacaoDAO = new SituacaoDAO(getContext());

        configurarSpSituacao();

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravarMesa();
                dismiss();
            }
        });
    }
    private void configurarSpSituacao(){
        situacoes = situacaoDAO.getAllSituacao();
        List<String> opcoes = new ArrayList<>();
        for (Situacao situacao : situacoes){
            if (situacao.getId() != 4){
                opcoes.add(situacao.getNome());
            }
        }
        ArrayAdapter<Situacao> adapter = new ArrayAdapter<>(getContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                situacoes);
        binding.spSituacaoAddMesa.setAdapter(adapter);
    }

    private void gravarMesa() {

        Situacao situacao = (Situacao) binding.spSituacaoAddMesa.getSelectedItem();

        Mesa mesa = new Mesa(
                binding.eTxtNome.getText().toString(),
                situacao.getId());
        mesasDAO.gravarMesa(mesa);

        Intent intent = new Intent(ACTION_UPDATE_MESAS);
        mContext.sendBroadcast(intent);
    }
}
