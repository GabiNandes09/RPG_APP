package com.nandes.rpgall;

import com.nandes.rpgall.models.Personagens;

public class MyApp {
    private static MyApp mMyapp;
    private Personagens pjAtivo;
    private String nomePerfil = "Gabriel Fernandes";
    private int qtdPj = 2;

    private MyApp(){}
    public static MyApp get(){
        if (mMyapp == null){ mMyapp = new MyApp();}
        return mMyapp;
    }

    public Personagens getPjAtivo() {
        return pjAtivo;
    }

    public static void setPjAtivo(Personagens pjAtivo) {
        mMyapp.pjAtivo = pjAtivo;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public static void setNomePerfil(String nomePerfil) {
        mMyapp.nomePerfil = nomePerfil;
    }

    public int getQtdPj() {
        return qtdPj;
    }

    public static void setQtdPj(int qtdPj) {
        mMyapp.qtdPj = qtdPj;
    }
}
