package com.nandes.rpgall.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nandes.rpgall.R;
import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.databinding.ActivityPersonagemBinding;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.Personagens;

public class PersonagemActivity extends AppCompatActivity {

    ActivityPersonagemBinding binding;
    Personagens pj = new Personagens();
    MesasDAO mesasDAO;
    SituacaoDAO situacaoDAO;
    ClassesDAO classesDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonagemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        InstanciaObjetos(this);

        InstanciaPersongem(getIntent().getExtras());

        binding.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CadastroPersonagemActivity.class);
                intent.putExtra(Item_personagens_lista_adapter.PERSONAGEM, pj);
                startActivity(intent);
            }
        });
    }

    public void InstanciaPersongem(Bundle bundle){
        if (bundle != null){
            pj = (Personagens)bundle.getSerializable(Item_personagens_lista_adapter.PERSONAGEM);
            configurarCabecalho(pj);
        }
    }

    public void InstanciaObjetos(Context context){
        mesasDAO = new MesasDAO(context);
        situacaoDAO = new SituacaoDAO(context);
        classesDAO = new ClassesDAO(context);
    }

    private void configurarCabecalho(Personagens pj) {


        String nomeLabel = binding.getRoot().getContext().getString(R.string.nome_label, pj.getNome());
        String nivelLabel = binding.getRoot().getContext().getString(R.string.nivel_label, pj.getNivel());
        String mesaLabel = binding.getRoot().getContext().getString(R.string.mesa_label, mesasDAO.getMesaNomeByID(pj.getMesa()));
        String situacaoLabel = binding.getRoot().getContext().getString(R.string.situacao_label, situacaoDAO.getSituacaoNomeByID(pj.getSituacao()));
        String classeLabel = binding.getRoot().getContext().getString(R.string.classe_label, classesDAO.getClasseNomeByID(pj.getClasse()));


        binding.txtNomePersonagem.setText(nomeLabel);
        binding.txtNivelPersonagem.setText(nivelLabel);
        binding.txtMesaPersonagem.setText(mesaLabel);
        binding.txtSituacaoPersonagem.setText(situacaoLabel);
        binding.txtClassePersonagem.setText(classeLabel);
    }
}