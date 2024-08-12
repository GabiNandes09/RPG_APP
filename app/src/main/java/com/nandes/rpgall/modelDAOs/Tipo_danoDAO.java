package com.nandes.rpgall.modelDAOs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nandes.rpgall.database.DatabaseHelper;
import com.nandes.rpgall.models.Tipo_Dano;

import java.util.ArrayList;
import java.util.List;

public class Tipo_danoDAO {

    private DatabaseHelper dbHelper;
    private Context context;

    public Tipo_danoDAO(Context context){
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    public List<Tipo_Dano> getAllTipo_Dano(){
        List<Tipo_Dano> tipos = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + DatabaseHelper.TIPO_DANO_TABLE;

        try (Cursor cursor = db.rawQuery(sql, null)){
            while (cursor.moveToNext()){
                Tipo_Dano tipo = new Tipo_Dano(
                        cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.TIPO_DANO_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.TIPO_DANO_NOME))
                );
                tipos.add(tipo);
            }
        }  catch (Exception e){
            Log.i("Nandes", "Erro ao buscar tipo_dano");
            e.printStackTrace();
        }
        return tipos;
    }
}
