package com.nandes.rpgall.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nandes.rpgall.R;
import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.databinding.ActivityPersonagemBinding;
import com.nandes.rpgall.interfaces.IPersonagemPresenter;
import com.nandes.rpgall.interfaces.IPersonagemView;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.Personagens;

public class PersonagemActivity extends AppCompatActivity implements IPersonagemView {

    private ActivityPersonagemBinding binding;
    private IPersonagemPresenter presenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPersonagemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new PersonagemPresenter(this, this);

        presenter.loadPJDetails(getIntent().getExtras());

        binding.btnEditar.setOnClickListener(v -> presenter.onEditButtonClick());

        binding.btnAtivar.setOnClickListener(v -> presenter.onAtivarButtonClick());

    }
    @Override
    public void showPJDetails(String nome, String nivel, String mesa, String situacao, String classe) {
        binding.txtNomePersonagem.setText(nome);
        binding.txtNivelPersonagem.setText(nivel);
        binding.txtMesaPersonagem.setText(mesa);
        binding.txtSituacaoPersonagem.setText(situacao);
        binding.txtClassePersonagem.setText(classe);
    }

    @Override
    public void navigateToEditScreen(Personagens pj) {
        Intent intent = new Intent(this, CadastroPersonagemActivity.class);
        intent.putExtra(Item_personagens_lista_adapter.PERSONAGEM, pj);
        startActivity(intent);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}