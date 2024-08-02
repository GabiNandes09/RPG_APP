package com.nandes.rpgall.models;

public class Rolagem {
    private int numero;
    private int resultado;
    private int critico;

    public Rolagem() {
    }

    public Rolagem(int numero, int resultado, int critico) {
        this.numero = numero;
        this.resultado = resultado;
        this.critico = critico;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public int getCritico() {
        return critico;
    }

    public void setCritico(int critico) {
        this.critico = critico;
    }
}
