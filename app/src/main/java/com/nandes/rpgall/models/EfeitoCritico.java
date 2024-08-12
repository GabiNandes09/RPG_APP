package com.nandes.rpgall.models;

public class EfeitoCritico {
    private int Id;
    private int tipo;
    private String nome;
    private String Descricao;

    public EfeitoCritico(){}

    public EfeitoCritico(int id, String nome, String descricao) {
        Id = id;
        this.nome = nome;
        Descricao = descricao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}
