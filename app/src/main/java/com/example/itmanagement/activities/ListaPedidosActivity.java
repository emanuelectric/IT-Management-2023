package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;

public class ListaPedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_pedidos_layout);

    }


    // Método para ir atrás, Administrar Contenido
    public void lanzarVistaAdministrarContenido(View view) {
        Intent intent = new Intent(this, AdministrarContenidoActivity.class);
        startActivity(intent);
    }



    // Agregar Pedido
    public void irAgregarPedido(View view) {
        Intent intent = new Intent(this, FormularioPedidosActivity.class);
        startActivity(intent);
    }

}
