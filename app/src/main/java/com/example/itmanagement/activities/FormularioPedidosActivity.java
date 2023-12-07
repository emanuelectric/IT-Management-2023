package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;

public class FormularioPedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_pedidos_layout);
    }

    // Método para ir a la pantalla principal
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }
}
