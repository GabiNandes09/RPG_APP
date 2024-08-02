package com.nandes.rpgall.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nandes.rpgall.databinding.ItemComentariosListBinding;
import com.nandes.rpgall.models.Comentarios;

import java.util.List;

public class Item_comentario_lista_adapter extends RecyclerView.Adapter<Item_comentario_lista_adapter.Item_comentarios_lista_ViewHolder> {

    private List<Comentarios> comentarios;

    public Item_comentario_lista_adapter(List<Comentarios> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public Item_comentarios_lista_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemComentariosListBinding binding = ItemComentariosListBinding.inflate(layoutInflater, parent, false);
        return new Item_comentarios_lista_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_comentarios_lista_ViewHolder holder, int position) {
        Comentarios comentario = comentarios.get(position);

        holder.binding.txtData.setText(comentario.getData());
        holder.binding.txtComentario.setText(comentario.getComentario());
    }

    @Override
    public int getItemCount() {
        return comentarios.size(); // Corrigido para retornar o tamanho da lista
    }

    class Item_comentarios_lista_ViewHolder extends RecyclerView.ViewHolder {

        private ItemComentariosListBinding binding;

        public Item_comentarios_lista_ViewHolder(@NonNull ItemComentariosListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
