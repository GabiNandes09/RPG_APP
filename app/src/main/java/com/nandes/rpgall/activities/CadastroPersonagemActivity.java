package com.nandes.rpgall.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.databinding.ActivityCadastroPersonagemBinding;
import com.nandes.rpgall.databinding.FragmentPersonagensBinding;
import com.nandes.rpgall.dialogues.DialogAddMesas;
import com.nandes.rpgall.interfaces.ICadastroPersonagemPresenter;
import com.nandes.rpgall.interfaces.ICadastroPersonagemView;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.*;

import java.util.List;


public class CadastroPersonagemActivity extends AppCompatActivity implements ICadastroPersonagemView {

    private ActivityCadastroPersonagemBinding binding;
    private ICadastroPersonagemPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPersonagemBinding.inflate( getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CadastroPersonagemPresenter(this, this);

        presenter.loadPersonagemDetails(getIntent().getExtras());
        presenter.registerReceiver();

        binding.btnAddMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {presenter.onAddMesaButtonClick();}
        });

        binding.btnGravarNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSaveButtonClick();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showPJDetails(String nome, String nivel, int situacaoIndex, int mesaIndex, int classeIndex) {
        binding.etxtNome.setText(nome);
        binding.etxtNivel.setText(nivel);
        binding.spSituacao.setSelection(situacaoIndex);
        binding.spMesas.setSelection(mesaIndex);
        binding.spClasses.setSelection(classeIndex);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setSpinnerAdapter(List<Situacao> situacoes, List<Mesa> mesas, List<Classe> classes) {
        ArrayAdapter<Situacao> situacaoAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, situacoes);
        binding.spSituacao.setAdapter(situacaoAdapter);

        ArrayAdapter<Mesa> mesaAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mesas);
        binding.spMesas.setAdapter(mesaAdapter);

        ArrayAdapter<Classe> classeAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, classes);
        binding.spClasses.setAdapter(classeAdapter);
    }

    public String getNome() {
        return binding.etxtNome.getText().toString();
    }

    public String getNivel() {
        return binding.etxtNivel.getText().toString();
    }

    public int getSituacaoIndex() {
        return binding.spSituacao.getSelectedItemPosition();
    }

    public int getMesaIndex() {
        return binding.spMesas.getSelectedItemPosition();
    }

    public int getClasseIndex() {
        return binding.spClasses.getSelectedItemPosition();
    }
}

