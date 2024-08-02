package com.nandes.rpgall.modelDAOs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nandes.rpgall.database.DatabaseHelper;
import com.nandes.rpgall.models.Classe;

import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {

    DatabaseHelper dbHelper;
    Context context;

    public ClassesDAO(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Classe> getAllClasses(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<Classe> classes = new ArrayList<>();
        String sql = "SELECT * FROM " + DatabaseHelper.CLASSES_TABLE;

        try (Cursor cursor = db.rawQuery(sql, null)){
            while (cursor.moveToNext()){
                Classe classe = new Classe(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.CLASSES_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.CLASSES_NOME))
                );

                classes.add(classe);
            }
        } catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar todas classes no banco de dados");
            e.printStackTrace();
        }
        return classes;
    }
    public String getClasseNomeByID (int id){
        String nome = "";
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT " + DatabaseHelper.CLASSES_NOME +
                " FROM " + DatabaseHelper.CLASSES_TABLE +
                " WHERE " + DatabaseHelper.CLASSES_ID + " = ?";

        try (Cursor cursor = db.rawQuery(sql, new String[] {String.valueOf(id)})){
            while (cursor.moveToNext()){
                nome = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.SITUACAO_NOME));
            }
        } catch (IllegalArgumentException e){
            Log.i("Nandes", "Erro ao buscar classe no banco de dados");
            e.printStackTrace();
        }

        return nome;
    }
}
