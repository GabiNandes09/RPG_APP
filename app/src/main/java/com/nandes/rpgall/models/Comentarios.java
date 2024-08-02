package com.nandes.rpgall.models;

public class Comentarios {

    private int id;
    private String data;
    private String comentario;

    public Comentarios(int id, String data, String comentario) {
        this.id = id;
        this.data = data;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
