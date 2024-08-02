package com.nandes.rpgall.models;

import androidx.annotation.NonNull;

public class Situacao {
    private int id;
    private String nome;

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public Situacao(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    public Situacao() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
