package com.nandes.rpgall.helper;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permissao {
    public void requisitarPermissao(Activity activity) {
        String[] permissoes = {
                Manifest.permission.INTERNET,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };

        for (String permissao : permissoes) {
            if (ContextCompat.checkSelfPermission(activity, permissao) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, permissoes, 0);
                break;
            }
        }
    }
}
