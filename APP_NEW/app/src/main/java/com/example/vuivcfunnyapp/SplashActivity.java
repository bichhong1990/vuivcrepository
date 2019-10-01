package com.example.vuivcfunnyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        String[] permissions = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        };



                List<String> listPermissionsNeeded = new ArrayList<>();
                for (String permission : permissions) {
                    if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                        listPermissionsNeeded.add(permission);
                    }
                    else
                    { Handler handeHandler = new Handler();
                        handeHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                        Intent chuyenLogin = new Intent(SplashActivity.this,ActivityLogin.class);
                        startActivity(chuyenLogin);
                        finish();
                    }
                },2000);
                    }
                }
                if (!listPermissionsNeeded.isEmpty()) {
                    ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
                }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Handler handeHandler = new Handler();
                handeHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent chuyenLogin = new Intent(SplashActivity.this,ActivityLogin.class);
                        startActivity(chuyenLogin);
                        finish();
                    }
                },2000);
            }
        }
    }
}
