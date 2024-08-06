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
import com.nandes.rpgall.databinding.ItemPersonagensListaBinding;
import com.nandes.rpgall.interfaces.Iitem_Personagem_Lista_Adapter_Presenter;
import com.nandes.rpgall.interfaces.Iitem_Personagem_Lista_Adapter_View;
import com.nandes.rpgall.models.*;

import java.util.List;

public class Item_personagens_lista_adapter extends RecyclerView.Adapter<Item_personagens_lista_adapter.item_personagens_lista_ViewHolder>  {

    private final Context context;
    public static final String PERSONAGEM = "Personagem";
    private List<Personagens> lista;



    public Item_personagens_lista_adapter(List<Personagens> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    public class item_personagens_lista_ViewHolder extends RecyclerView.ViewHolder implements Iitem_Personagem_Lista_Adapter_View {
        private final ItemPersonagensListaBinding binding;
        private Iitem_Personagem_Lista_Adapter_Presenter presenter;

        public item_personagens_lista_ViewHolder(@NonNull ItemPersonagensListaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            presenter = new item_Personagem_Lista_Adapter_Presenter(this, context);
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
        public void showToast(String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void notifyRecyclerViewDelete(List<Personagens> list, int position) {
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
            showToast("Item deletado");
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
        if (holder.presenter == null || lista == null || position >= lista.size()) {
            return;
        }
        holder.itemView.setOnClickListener(v -> holder.presenter.onItemClick());

        holder.binding.btnDeletarPj.setOnClickListener(v -> holder.presenter.onDeleteClick(lista, holder.getAdapterPosition()));

        holder.binding.btnComentPj.setOnClickListener(v -> holder.presenter.onComentsClick());

        holder.presenter.getPJDetails(lista, position);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

}