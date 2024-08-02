package com.nandes.rpgall.adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nandes.rpgall.R;
import com.nandes.rpgall.activities.ComentariosActivity;
import com.nandes.rpgall.activities.PersonagemActivity;
import com.nandes.rpgall.databinding.ItemPersonagensListaBinding;
import com.nandes.rpgall.modelDAOs.*;
import com.nandes.rpgall.models.*;

import java.util.List;

public class Item_personagens_lista_adapter extends RecyclerView.Adapter<Item_personagens_lista_adapter.item_personagens_lista_ViewHolder> {

    private List<Personagens> lista;
    private PersonagensDAO personagensDAO;
    private MesasDAO mesasDAO;
    private SituacaoDAO situacaoDAO;
    private ClassesDAO classesDAO;
    Context context;

    public static final String PERSONAGEM = "Personagem";

    public Item_personagens_lista_adapter(List<Personagens> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    static class item_personagens_lista_ViewHolder extends RecyclerView.ViewHolder {
        private final ItemPersonagensListaBinding binding;

        public item_personagens_lista_ViewHolder(@NonNull ItemPersonagensListaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public item_personagens_lista_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPersonagensListaBinding binding = ItemPersonagensListaBinding.inflate(layoutInflater, parent, false);
        return new item_personagens_lista_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull item_personagens_lista_ViewHolder holder, int position) {
        personagensDAO = new PersonagensDAO(holder.itemView.getContext());
        mesasDAO = new MesasDAO(holder.itemView.getContext());
        situacaoDAO = new SituacaoDAO(holder.itemView.getContext());
        classesDAO = new ClassesDAO(holder.itemView.getContext());

        Personagens pj = lista.get(holder.getAdapterPosition());

        String nomeLabel = holder.binding.getRoot().getContext().getString(R.string.nome_label, pj.getNome());
        String nivelLabel = holder.binding.getRoot().getContext().getString(R.string.nivel_label, pj.getNivel());
        String mesaLabel = holder.binding.getRoot().getContext().getString(R.string.mesa_label, mesasDAO.getMesaNomeByID(pj.getMesa()));
        String situacaoLabel = holder.binding.getRoot().getContext().getString(R.string.situacao_label, situacaoDAO.getSituacaoNomeByID(pj.getSituacao()));
        String classeLabel = holder.binding.getRoot().getContext().getString(R.string.classe_label, classesDAO.getClasseNomeByID(pj.getClasse()));


        holder.binding.txtNomePersonagem.setText(nomeLabel);
        holder.binding.txtNivelPersonagem.setText(nivelLabel);
        holder.binding.txtMesaPersonagem.setText(mesaLabel);
        holder.binding.txtSituacaoPersonagem.setText(situacaoLabel);
        holder.binding.txtClassePersonagem.setText(classeLabel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PersonagemActivity.class);
                intent.putExtra(PERSONAGEM, pj);
                context.startActivity(intent);
            }
        });
        holder.binding.btnDeletarPj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(R.string.deletar_mensagem)
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                personagensDAO.apagarPersonagem(pj);
                                lista.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemRangeChanged(holder.getAdapterPosition(), lista.size());
                            }
                        })
                        .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(
                                                v.getContext(),
                                                "Cancelado",
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .show();
            }
        });
        holder.binding.btnComentPj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ComentariosActivity.class);
                intent.putExtra(PERSONAGEM, pj);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}