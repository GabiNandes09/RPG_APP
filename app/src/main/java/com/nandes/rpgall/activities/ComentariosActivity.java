package com.nandes.rpgall.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.nandes.rpgall.R;
import com.nandes.rpgall.adapters.Item_comentario_lista_adapter;
import com.nandes.rpgall.adapters.Item_personagens_lista_adapter;
import com.nandes.rpgall.databinding.ActivityComentariosBinding;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.*;

import java.util.ArrayList;
import java.util.List;

public class ComentariosActivity extends AppCompatActivity {

    private ActivityComentariosBinding binding;
    private Item_comentario_lista_adapter adapter;

    MesasDAO mesasDAO;
    SituacaoDAO situacaoDAO;
    ClassesDAO classesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityComentariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mesasDAO = new MesasDAO(this);
        situacaoDAO = new SituacaoDAO(this);
        classesDAO = new ClassesDAO(this);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Personagens pj = (Personagens)bundle.getSerializable(Item_personagens_lista_adapter.PERSONAGEM);
            configurarCabecalho(pj);
        }

        configurarRecyclerView();
        atualizarComentarios();
    }

    private void configurarCabecalho(Personagens pj) {
        String nomeLabel = binding.getRoot().getContext().getString(R.string.nome_label, pj.getNome());
        String nivelLabel = binding.getRoot().getContext().getString(R.string.nivel_label, pj.getNivel());
        String mesaLabel = binding.getRoot().getContext().getString(R.string.mesa_label, mesasDAO.getMesaNomeByID(pj.getMesa()));
        String classeLabel = binding.getRoot().getContext().getString(R.string.classe_label, classesDAO.getClasseNomeByID(pj.getClasse()));
        String situacaoLabel = binding.getRoot().getContext().getString(R.string.situacao_label, situacaoDAO.getSituacaoNomeByID(pj.getSituacao()));

        binding.txtNomePersonagem.setText(nomeLabel);
        binding.txtNivelPersonagem.setText(nivelLabel);
        binding.txtMesaPersonagem.setText(mesaLabel);
        binding.txtSituacaoPersonagem.setText(situacaoLabel);
        binding.txtClasse.setText(classeLabel);
    }

    private void configurarRecyclerView() {
        // Configura o RecyclerView
        binding.rvComentarios.setLayoutManager(new LinearLayoutManager(this));
        binding.rvComentarios.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void atualizarComentarios() {
        List<Comentarios> comentarios = new ArrayList<>();
        comentarios.add(new Comentarios(1, "10/05/2024", "Teste"));
        comentarios.add(new Comentarios(2, "25/01/2024", "Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical.Comentário longo para testar a rolagem no RecyclerView. Esse texto é suficientemente longo para garantir que o item será exibido corretamente na tela e permitirá verificar a rolagem vertical."));
        comentarios.add(new Comentarios(3, "02/04/2024", "Funciona"));

        adapter = new Item_comentario_lista_adapter(comentarios);
        binding.rvComentarios.setAdapter(adapter);
    }
}
