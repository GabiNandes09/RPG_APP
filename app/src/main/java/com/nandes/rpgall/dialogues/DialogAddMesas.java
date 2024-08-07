package com.nandes.rpgall.dialogues;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.nandes.rpgall.databinding.DialogueAddMesasBinding;
import com.nandes.rpgall.interfaces.IDialogAddMesasPresenter;
import com.nandes.rpgall.interfaces.IDialogAddMesasView;
import com.nandes.rpgall.models.*;
import java.util.List;

public class DialogAddMesas extends Dialog implements IDialogAddMesasView {
    private DialogueAddMesasBinding binding;
    private Context mContext;
    private IDialogAddMesasPresenter presenter;
    static final String ACTION_UPDATE_MESAS = "com.nandes.rpgall.UPDATE_MESAS";


    public DialogAddMesas(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DialogueAddMesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new DialoigAddMesasPresenter(this, mContext);

        presenter.configurarSpinner();

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDestroy();
                dismiss();
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSalvarClick();
                dismiss();
            }
        });
    }

    @Override
    public void setSpinner(List<Situacao> situacoes) {
        if (situacoes != null){
            ArrayAdapter<Situacao> adapter = new ArrayAdapter<>(getContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    situacoes);
            binding.spSituacaoAddMesa.setAdapter(adapter);
        }
    }

    @Override
    public void onSalvarClick(){
        Situacao situacao = (Situacao) binding.spSituacaoAddMesa.getSelectedItem();
        String nome = binding.eTxtNome.getText().toString();
        if (situacao != null && !nome.isEmpty()){
            presenter.salvarMesa(situacao.getId(), nome);
        } else {
            showToast("Algo deu errado");
        }

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
