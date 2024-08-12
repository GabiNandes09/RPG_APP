package com.nandes.rpgall.models;

import androidx.annotation.NonNull;

public class Tipo_Dano {
    private int id;
    private String nome;

    public Tipo_Dano(){}
    public Tipo_Dano(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
