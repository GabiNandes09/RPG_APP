package com.nandes.rpgall.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Mesa implements Serializable {
    private int id;
    private String nome;
    private int situacao;

    public Mesa() {
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }

    public Mesa(int id, String nome, int situacao) {
        this.id = id;
        this.nome = nome;
        this.situacao = situacao;
    }

    public Mesa(String nome, int situacao) {
        this.nome = nome;
        this.situacao = situacao;
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

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
