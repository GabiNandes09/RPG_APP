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
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.*;

import java.util.List;


public class CadastroPersonagemActivity extends AppCompatActivity {

    ActivityCadastroPersonagemBinding binding;
    FragmentPersonagensBinding fragBinding;
    PersonagensDAO personagensDAO = new PersonagensDAO(this);
    SituacaoDAO situacaoDAO = new SituacaoDAO(this);
    ClassesDAO classesDAO = new ClassesDAO(this);
    MesasDAO mesasDAO = new MesasDAO(this);
    Personagens pj = new Personagens();
    List<Situacao> situacoes;
    List<Classe> classes;
    List<Mesa> mesas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroPersonagemBinding.inflate( getLayoutInflater());
        fragBinding = FragmentPersonagensBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        IntentFilter filter = new IntentFilter(ACTION_UPDATE_MESAS);
        registerReceiver(mesasReceiver, filter);

        configurarSpSituacao();
        configurarSpClasse();
        configurarSpMesa();

        RecuperaPJ(getIntent().getExtras());


        binding.btnAddMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddMesas dialog = new DialogAddMesas(v.getContext());
                dialog.show();
            }
        });


        binding.btnGravarNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SalvarPersonagem();
            }
        });

    }

    public void RecuperaPJ(Bundle bundle){
        if (bundle != null){
            pj = (Personagens)bundle.getSerializable(Item_personagens_lista_adapter.PERSONAGEM);
            preencherEdicao(pj);
        }
    }

    private void configurarSpMesa() {
        mesas = mesasDAO.getAllMesas();

        ArrayAdapter<Mesa> adapter = new ArrayAdapter<>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                mesas);
        binding.spMesas.setAdapter(adapter);
    }

    private void configurarSpClasse() {
        classes = classesDAO.getAllClasses();

        ArrayAdapter<Classe> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                classes);
        binding.spClasses.setAdapter(adapter);
    }
    private void configurarSpSituacao(){
        situacoes = situacaoDAO.getAllSituacao();

        ArrayAdapter<Situacao> adapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                situacoes);
        binding.spSituacao.setAdapter(adapter);
    }

    private void preencherEdicao(Personagens pj) {

        binding.etxtNome.setText(pj.getNome());
        binding.etxtNivel.setText(String.valueOf(pj.getNivel()));
        binding.spSituacao.setSelection(setSituacao(pj.getSituacao(), situacoes));
        binding.spMesas.setSelection(setMesa(pj.getMesa(), mesas));
        binding.spClasses.setSelection(setClasse(pj.getClasse(), classes));

    }

    public int setSituacao(int id, List<Situacao> list){
        int index = -1;
        for (Situacao sit : list){
            if (sit.getId() == id){
                index = list.indexOf(sit);
            }
        }
        return index;
    }
    public int setMesa(int id, List<Mesa> list){
        int index = -1;
        for (Mesa mesa : list){
            if (mesa.getId() == id){
                index = list.indexOf(mesa);
            }
        }
        return index;
    }

    public int setClasse(int id, List<Classe> list){
        int index = -1;
        for (Classe classe : list){
            if (classe.getId() == id){
                index = list.indexOf(classe);
            }
        }
        return index;
    }


    public void SalvarPersonagem(){
        if (
                (!binding.etxtNome.getText().toString().isEmpty())&&
                        (!binding.etxtNivel.getText().toString().isEmpty())&&
                        (binding.spClasses.getSelectedItemPosition()!= 0)&&
                        (binding.spSituacao.getSelectedItemPosition() != 0)
        ){

            Situacao situacao = (Situacao) binding.spSituacao.getSelectedItem();
            Mesa mesa = (Mesa) binding.spMesas.getSelectedItem();
            Classe classe = (Classe) binding.spClasses.getSelectedItem();

            personagensDAO.salvarPersonagem(new Personagens(
                    binding.etxtNome.getText().toString(),
                    Integer.parseInt(binding.etxtNivel.getText().toString()),
                    classe.getId(),
                    mesa.getId(),
                    situacao.getId()
            ));

            finish();

        } else {
            Toast.makeText(
                    this,
                    "Preencha todos os dados",
                    Toast.LENGTH_LONG
            ).show();
        }

    }

    private static final String ACTION_UPDATE_MESAS = "com.nandes.rpgall.UPDATE_MESAS";

    private final BroadcastReceiver mesasReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_UPDATE_MESAS.equals(intent.getAction())) {
                configurarSpMesa();
            }
        }
    };
}

