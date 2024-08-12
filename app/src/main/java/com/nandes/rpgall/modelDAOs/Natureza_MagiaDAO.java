package com.nandes.rpgall.modelDAOs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nandes.rpgall.database.DatabaseHelper;
import com.nandes.rpgall.models.Natureza_magia;

import java.util.ArrayList;
import java.util.List;

public class Natureza_MagiaDAO {
    private DatabaseHelper dbHelper;
    private Context context;

    public Natureza_MagiaDAO(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Natureza_magia> getAllNatureza_Magia(){
        List<Natureza_magia> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseHelper.NATUREZA_MAGIA_TABLE;

        try (Cursor cursor = db.rawQuery(sql, null)){
            while (cursor.moveToNext()){
                Natureza_magia magia = new Natureza_magia(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.NATUREZA_MAGIA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NATUREZA_MAGIA_NOME))
                );
                list.add(magia);
            }
        }
        return list;
    }
}
