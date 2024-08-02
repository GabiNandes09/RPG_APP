package com.nandes.rpgall.modelDAOs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nandes.rpgall.database.DatabaseHelper;
import com.nandes.rpgall.models.Situacao;

import java.util.ArrayList;
import java.util.List;

public class SituacaoDAO {
    DatabaseHelper dbHelper;
    Context context;
    public SituacaoDAO (Context context){
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    public List<Situacao> getAllSituacao (){
        List<Situacao> situacoes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + DatabaseHelper.SITUACAO_TABLE + ";";

        try(Cursor cursor = db.rawQuery(sql, null)){
            while (cursor.moveToNext()){
                Situacao situacao = new Situacao(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.SITUACAO_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.SITUACAO_NOME))
                );
                situacoes.add(situacao);
            }
        } catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar todos situacoes no banco de dados");
            e.printStackTrace();
        }

        return situacoes;
    }

    public Situacao getSituacaoByID(int id){
        Situacao situacao = new Situacao();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT * " +
                " FROM " + DatabaseHelper.SITUACAO_TABLE +
                " WHERE " + DatabaseHelper.SITUACAO_ID + " = ?";

        try (Cursor cursor = db.rawQuery(sql, new String[] {String.valueOf(id)})){
            while (cursor.moveToNext()){
                situacao.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.SITUACAO_ID)));
                situacao.setNome(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.SITUACAO_NOME)));
            }
        } catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar situacao no banco de dados");
            e.printStackTrace();
        }
        return situacao;
    }

    public String getSituacaoNomeByID (int id){
        String nome = "";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT " + DatabaseHelper.SITUACAO_NOME +
                " FROM " + DatabaseHelper.SITUACAO_TABLE +
                " WHERE " + DatabaseHelper.SITUACAO_ID + " = ?";

        try (Cursor cursor = db.rawQuery(sql, new String[] {String.valueOf(id)})){
            while (cursor.moveToNext()){
                nome = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.SITUACAO_NOME));
            }
        } catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar situacao no banco de dados");
            e.printStackTrace();
        }

        return nome;
    }
}
