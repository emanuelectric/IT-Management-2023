package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;

public class MenuInicialActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_inicial_layout);
    }

    public void LanzarVistaIniciarSesion(View view) {
        Intent intent = new Intent(this, IniciarSesionActivity.class);
        startActivity(intent);
    }

    public void LanzarVistaRegistrarse(View view) {
        Intent intent = new Intent(this, RegistrarseActivity.class);
        startActivity(intent);
    }
}
