package com.nandes.rpgall.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //CONSTANTES
    private static final String DATABASE_NAME = "RPG.db";
    private static final int DATABASE_VERSION = 7;
    //CONSTANTES DE PERSONAGENS
    public static final String PJ_TABLE = "PERSONAGENS";
    public static final String PJ_ID = "IDPERSONAGEM";
    public static final String PJ_NOME = "NOME";
    public static final String PJ_NIVEL = "NIVEL";
    public static final String PJ_MESA = "ID_MESA";
    public static final String PJ_SITUACO = "ID_SITUACO";
    public static final String PJ_CLASSE = "ID_CLASSE";
    public static final String PJ_ATIVO = "ID_ATIVO";

    // CONSTANTES SITUACAO
    public static final String SITUACAO_TABLE = "SITUACAO";
    public static final String SITUACAO_ID = "IDSITUACAO";
    public static final String SITUACAO_NOME = "NOME";

    // CONSTANTES CLASSES
    public static final String CLASSES_TABLE = "CLASSES";
    public static final String CLASSES_ID = "IDCLASSES";
    public static final String CLASSES_NOME = "NOME";

    // CONSTANTES MESA
    public static final String MESA_TABLE = "MESAS";
    public static final String MESA_ID = "IDMESAS";
    public static final String MESA_NOME = "NOME";
    public static final String MESA_SITUACAO = "ID_SITUACAO";

    //CONSTANTES TIPO_DANO
    public static final String TIPO_DANO_TABLE = "TIPO_DANO";
    public static final String TIPO_DANO_ID = "ID";
    public static final String TIPO_DANO_NOME = "NOME";

    //CONSTANTES EFEITO_CRITICO
    public static final String EFEITO_CRITICO_TABLE = "EFEITO_CRITICO";
    public static final String EFEITO_CRITICO_ID = "ID";
    public static final String EFEITO_CRITICO_NOME = "NOME";
    public static final String EFEITO_CRITICO_DESCRICAO = "DESCRICAO";
    public static final String EFEITO_CRITICO_TIPO_DANO = "ID_TIPO_DANO";

    //CONSTANTES NATUREZA_MAGIA
    public static final String NATUREZA_MAGIA_TABLE = "NATUREZA_MAGIA";
    public static final String NATUREZA_MAGIA_ID = "ID";
    public static final String NATUREZA_MAGIA_NOME = "NOME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + SITUACAO_TABLE + "(" +
                SITUACAO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SITUACAO_NOME + " VARCHAR(12) NOT NULL UNIQUE" +
                ");";

        try{
            db.execSQL(sql);
            Log.i("Nandes", "Tabela Situacao criada");
        } catch (SQLException e){
            Log.i("Nandes", "Erro ao criar tabela situacao");
        }

        sql = "INSERT INTO " + SITUACAO_TABLE + " (" + SITUACAO_NOME + ") VALUES " +
                "('NUNCA JOGADO')," +
                "('INATIVO')," +
                "('ATIVO')," +
                "('MORTO')," +
                "('CONCLUÍDO');";

        try{
            db.execSQL(sql);
            Log.i("Nandes", "Exito ao popular situacao");
        } catch (Error e){
            Log.i("Nandes", "Erro ao popular situacao");
        }

        sql = "CREATE TABLE " + CLASSES_TABLE + "(" +
                CLASSES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLASSES_NOME + " VARCHAR(9) NOT NULL UNIQUE" +
                ");";

        try{
            db.execSQL(sql);
            Log.i("Nandes", "Tabela classes criada");
        } catch (SQLException e){
            Log.i("Nandes", "Erro ao criar tabela classes");
        }

        sql = "INSERT INTO " + CLASSES_TABLE + " (" + CLASSES_NOME + ") VALUES " +
                "('BÁRBARO')," +
                "('BARDO')," +
                "('CLERIGO')," +
                "('DRÚIDA')," +
                "('GUERREIRO')," +
                "('MONGE')," +
                "('PALADINO')," +
                "('PATRULHEIRO')," +
                "('LADINO')," +
                "('BRUXO')," +
                "('MAGO')," +
                "('FEITICEIRO');";

        try{
            db.execSQL(sql);
            Log.i("Nandes", "Exito ao popular Classes");
        } catch (Error e){
            Log.i("Nandes", "Erro ao popular Classes");
        }

        sql = "CREATE TABLE " + MESA_TABLE + "(" +
                MESA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MESA_NOME + " TEXT NOT NULL UNIQUE, " +
                MESA_SITUACAO + " INTEGER NOT NULL DEFAULT 1, " +
                "FOREIGN KEY (" + MESA_SITUACAO + ") REFERENCES " + SITUACAO_TABLE + "(" + SITUACAO_ID + ")" +
                ");";

        try{
            db.execSQL(sql);
            Log.i("Nandes", "Tabela Mesas criada");
        } catch (SQLException e) {
            Log.i("Nandes", "Erro ao criar tabela Mesas");
        }

        sql = "CREATE TABLE " + PJ_TABLE + "(" +
                PJ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PJ_NOME + " TEXT NOT NULL UNIQUE, " +
                PJ_NIVEL + " INTEGER NOT NULL, " +
                PJ_CLASSE + " INTEGER NOT NULL, " +
                PJ_ATIVO + " INTEGER NOT NULL DEFAULT 0, " +
                PJ_MESA + " INTEGER, " +
                PJ_SITUACO + " INTEGER NOT NULL DEFAULT 1, " +
                "FOREIGN KEY (" + PJ_CLASSE + ") REFERENCES " + CLASSES_TABLE + "(" + CLASSES_ID + "), " +
                "FOREIGN KEY (" + PJ_MESA + ") REFERENCES " + MESA_TABLE + "(" + MESA_ID + "), " +
                "FOREIGN KEY (" + PJ_SITUACO + ") REFERENCES " + SITUACAO_TABLE + "(" + SITUACAO_ID + ") " +
                ");";

        try{
            db.execSQL(sql);
            Log.i("Nandes", "Tabela Personagens criada");
        } catch (SQLException e) {
            Log.i("Nandes", "Erro ao criar tabela Personagens");
        }

        sql = "CREATE TABLE " + TIPO_DANO_TABLE + " ( " +
                TIPO_DANO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TIPO_DANO_NOME + " VARCHAR(12) UNIQUE NOT NULL " +
                " );";

        try {
            db.execSQL(sql);
            Log.i("Nandes", "Tabela TIPO_DANO criada");
        } catch (SQLException e){
            Log.i("Nandes", "Erro ao criar tabela TIPO_DANO");
        }

        sql = "INSERT INTO " + TIPO_DANO_TABLE + " (" + TIPO_DANO_NOME + ") VALUES ('CONCUSSÃO'), ('PERFURANTE'), ('CORTANTE'), ('MÁGICO');";

        try {
            db.execSQL(sql);
            Log.i("Nandes", "Dados TIPO_DANO inseridos");
        } catch (SQLException e) {
            Log.i("Nandes", "Erro ao inserir TIPO_DANO");
        }


        sql = "CREATE TABLE " + EFEITO_CRITICO_TABLE + " ( " +
                EFEITO_CRITICO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EFEITO_CRITICO_NOME + " VARCHAR(20) UNIQUE NOT NULL, " +
                EFEITO_CRITICO_DESCRICAO + " TEXT NOT NULL, " +
                EFEITO_CRITICO_TIPO_DANO + " INTEGER, " +
                "FOREIGN KEY ( " + EFEITO_CRITICO_TIPO_DANO + " ) REFERENCES " + TIPO_DANO_TABLE + " ( " + TIPO_DANO_ID + " )" +
                " );";
        try {
            db.execSQL(sql);
            Log.i("Nandes", "Tabela EFEITO_CRITICO criada");
        } catch (SQLException e){
            Log.i("Nandes", "Erro ao criar tabela EFEITO_CRITICO");
        }

        sql = "CREATE TABLE " + NATUREZA_MAGIA_TABLE + "(" +
                NATUREZA_MAGIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NATUREZA_MAGIA_NOME + " VARCHAR(10) UNIQUE NOT NULL " +
                ")" ;

        try {
            db.execSQL(sql);
            Log.i("Nandes", "Tabela NATUREZA_MAGIA criada");
        } catch (SQLException e){
            Log.i("Nandes", "Erro ao criar tabela NATUREZA_MAGIA");
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "INSERT INTO " + NATUREZA_MAGIA_TABLE + " (" + NATUREZA_MAGIA_NOME + ") VALUES " +
                "('FOGO'), " +
                "('GELO'), " +
                "('ELÉTRICO'), " +
                "('NECRÓTICO');";

        try {
            db.execSQL(sql);
            Log.i("Nandes", "Dados NATUREZA_MAGIA inseridos");
        } catch (SQLException e) {
            Log.i("Nandes", "Erro ao inserir NATUREZA_MAGIA");
        }




    }
}
