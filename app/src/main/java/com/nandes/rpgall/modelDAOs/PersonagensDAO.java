package com.nandes.rpgall.modelDAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nandes.rpgall.database.DatabaseHelper;
import com.nandes.rpgall.models.Personagens;

import java.util.ArrayList;
import java.util.List;

public class PersonagensDAO {
    //CONSTANTES DE PERSONAGENS
    DatabaseHelper dbHelper;
    Context context;
    private static String PJ_TABLE = "PERSONAGENS";

    public PersonagensDAO(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.context = context;
    }


    //Criar um CRUD

    //CREATE - Criar
    public void salvarPersonagem(Personagens pj) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.PJ_NOME, pj.getNome());
        values.put(DatabaseHelper.PJ_NIVEL, pj.getNivel());
        values.put(DatabaseHelper.PJ_MESA, pj.getMesa());
        values.put(DatabaseHelper.PJ_CLASSE, pj.getClasse());
        values.put(DatabaseHelper.PJ_SITUACO, pj.getSituacao());

        try {
            db.insert(DatabaseHelper.PJ_TABLE, null, values);
            Log.i("Nandes", "Personagem salvo com sucesso");
            Toast.makeText(context, "Personagem salvo com sucesso", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    //READ - LISTAR
    public List<Personagens> listarTodosPersonagens(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseHelper.PJ_TABLE;
        List<Personagens> list = new ArrayList<>();
        try(Cursor cursor = db.rawQuery(sql, null)){

            while (cursor.moveToNext()){
                Personagens personagens = new Personagens(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_NOME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_NIVEL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_CLASSE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_MESA)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_SITUACO))
                );
                list.add(personagens);
            }

        } catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar personagens no banco de dados");
            e.printStackTrace();
        }


        return list;
    }
    public Personagens getPersonagemByID(int id){
        Personagens pj = new Personagens();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseHelper.PJ_TABLE + " WHERE " + DatabaseHelper.PJ_ID + " = ?;";
        String[] args = new String[]{String.valueOf(id)};

        try (Cursor cursor = db.rawQuery(sql, args)){
            while (cursor.moveToNext()){
                pj.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_ID)));
                pj.setNome(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_NOME)));
                pj.setNivel(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_NIVEL)));
                pj.setClasse(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_CLASSE)));
                pj.setMesa(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_MESA)));
                pj.setSituacao(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_SITUACO)));
            }
        } catch (Exception e){

        }
        return pj;
    }

    //UPDATE - ATUALIZAR

    //DELETE - APAGAR
    public void apagarPersonagem (Personagens pj){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] args = new String[]{String.valueOf(pj.getId())};

        try{
            db.delete(
                    DatabaseHelper.PJ_TABLE,
                    DatabaseHelper.PJ_ID + " = ?",
                    args);

            Toast.makeText(
                            context,
                            "Deletado com sucesso",
                            Toast.LENGTH_SHORT)
                    .show();
        } catch (Error e){
            Log.i("Nandes", "Erro ao excluir");
        }


    }

    public void changeAtivo (int id, int ativo){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PJ_ATIVO, ativo);

        try {
            db.update(
                    DatabaseHelper.PJ_TABLE,
                    values,
                    DatabaseHelper.PJ_ID + " = ?",
                    new String[]{String.valueOf(id)}
            );
        } catch (Exception e){
            Log.i("Nandes", "Erro ao mudar ativo");
        }
    }
    public int foundIDAtivo(){
        int id = -1;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT " + DatabaseHelper.PJ_ID + " FROM " + DatabaseHelper.PJ_TABLE + " WHERE " + DatabaseHelper.PJ_ATIVO + " = ?";

        try (Cursor cursor = db.rawQuery(sql, new String[]{"1"})){
            while (cursor.moveToNext()){
                id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_ID));
            }
        }

        return id;
    }
    public int getAtivo(int id){
        int ativo = -1;

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT " + DatabaseHelper.PJ_ATIVO + " FROM " + DatabaseHelper.PJ_TABLE + " WHERE " + DatabaseHelper.PJ_ID + " = ?";

        try (Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)})){
            while (cursor.moveToNext()){
                ativo = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PJ_ATIVO));
            }
        }

        return ativo;
    }

}
