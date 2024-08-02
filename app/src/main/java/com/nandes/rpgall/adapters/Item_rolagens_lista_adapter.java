package com.nandes.rpgall.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.nandes.rpgall.R;
import com.nandes.rpgall.databinding.ItemRolagensListaBinding;
import com.nandes.rpgall.models.Rolagem;

import java.util.List;

public class Item_rolagens_lista_adapter extends RecyclerView.Adapter<Item_rolagens_lista_adapter.Item_rolagens_lista_ViewHolder> {

    private final List<Rolagem> lista;

    public Item_rolagens_lista_adapter(List<Rolagem> lista) {
        this.lista = lista;
    }

    static class Item_rolagens_lista_ViewHolder extends RecyclerView.ViewHolder {
        private final ItemRolagensListaBinding binding;

        public Item_rolagens_lista_ViewHolder(@NonNull ItemRolagensListaBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public Item_rolagens_lista_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRolagensListaBinding binding = ItemRolagensListaBinding.inflate(layoutInflater, parent, false);
        return new Item_rolagens_lista_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_rolagens_lista_ViewHolder holder, int position) {
        Rolagem dado = lista.get(position);

        String dadoLabel = holder.binding.getRoot().getContext().getString(R.string.dado_label, dado.getNumero());
        switch (dado.getCritico()){
            case 0:
                holder.binding.txtCritico.setText(holder.binding.getRoot().getContext().getString(R.string.falha_label));
                holder.binding.txtCritico.setTextColor(ContextCompat.getColor(holder.binding.getRoot().getContext(), R.color.red));
                holder.binding.txtCritico.setVisibility(View.VISIBLE);
                break;
            case 1:
                holder.binding.txtCritico.setText(holder.binding.getRoot().getContext().getString(R.string.critico_label));
                holder.binding.txtCritico.setTextColor(ContextCompat.getColor(holder.binding.getRoot().getContext(), R.color.green));
                holder.binding.txtCritico.setVisibility(View.VISIBLE);
                break;
            default:
                holder.binding.txtCritico.setVisibility(View.GONE);
                break;
        }

        holder.binding.txtDado.setText(dadoLabel);
        holder.binding.txtResultado.setText(String.valueOf(dado.getResultado()));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}
