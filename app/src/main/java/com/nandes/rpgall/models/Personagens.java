package com.nandes.rpgall.models;

import java.io.Serializable;

public class Personagens implements Serializable {


    private int id;
    private String nome;
    private int nivel;
    private int classe;
    private int mesa;
    private int situacao;

    public Personagens() {}

    public Personagens(String nome, int nivel, int classe, int mesa, int situacao) {
        this.nome = nome;
        this.nivel = nivel;
        this.classe = classe;
        this.mesa = mesa;
        this.situacao = situacao;
    }

    public Personagens(int id, String nome, int nivel, int classe, int mesa, int situacao) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.classe = classe;
        this.mesa = mesa;
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

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
}
