package com.example.itmanagement.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class MenuInicialActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_inicial_layout);

        // Inicializar la base de datos
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        // Ahora puedes utilizar 'database' para realizar operaciones en la base de datos
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
