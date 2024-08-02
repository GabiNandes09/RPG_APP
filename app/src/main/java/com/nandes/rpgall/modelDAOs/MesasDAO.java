package com.nandes.rpgall.modelDAOs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.nandes.rpgall.database.DatabaseHelper;
import com.nandes.rpgall.models.Mesa;

import java.util.ArrayList;
import java.util.List;

public class MesasDAO {
    DatabaseHelper dbhelper;
    Context context;

    public MesasDAO(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }

    public List<Mesa> getAllMesas(){
        List<Mesa> mesas = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseHelper.MESA_TABLE;

        try(Cursor cursor = db.rawQuery(sql, null)){
            while (cursor.moveToNext()){
                Mesa mesa = new Mesa(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MESA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MESA_NOME)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.MESA_SITUACAO))
                );

                mesas.add(mesa);
            }
        }catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar todas mesas no banco de dados");
            e.printStackTrace();
        }

        return mesas;
    }

    public String getMesaNomeByID (int id){
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "SELECT " + DatabaseHelper.MESA_NOME + " FROM " + DatabaseHelper.MESA_TABLE + " WHERE " + DatabaseHelper.MESA_ID + " = ?";
        String[] args = new String[]{String.valueOf(id)};
        String mesa = "";

        try(Cursor cursor = db.rawQuery(sql, args)){
            while (cursor.moveToNext()){
                mesa = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MESA_NOME));
            }
        }
        return mesa;
    }

    public void gravarMesa (Mesa mesa){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.MESA_NOME, mesa.getNome());
        contentValues.put(DatabaseHelper.MESA_SITUACAO, mesa.getSituacao());

        try {
            db.insert(DatabaseHelper.MESA_TABLE,
                    null,
                    contentValues);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
