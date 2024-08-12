package com.nandes.rpgall.models;

import androidx.annotation.NonNull;

public class Natureza_magia {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Natureza_magia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
