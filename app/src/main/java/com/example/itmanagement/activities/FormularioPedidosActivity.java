package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.MenuProductoAdapter;
import com.example.itmanagement.data.DBHelper;

public class FormularioPedidosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button botonSiguiente;
    private RecyclerView.Adapter menuProductoAdapter;  // Cambiado a RecyclerView.Adapter
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_pedidos_layout);

        // Asociar vistas
        recyclerView = findViewById(R.id.recyclerView);
        botonSiguiente = findViewById(R.id.boton_siguiente);

        // Configurar RecyclerView
        dbHelper = new DBHelper(this);
        menuProductoAdapter = new MenuProductoAdapter(this, dbHelper.obtenerListaMenuProductos(), dbHelper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter((RecyclerView.Adapter) menuProductoAdapter);  // Cambiado a RecyclerView.Adapter

        // Configurar el clic del botón Siguiente
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguientePedido();
            }
        });
    }

    // Método para retroceder e ir a la Lista de Pedidos
    public void irListaPedidos(View view) {
        Intent intent = new Intent(this, ListaPedidosActivity.class);
        startActivity(intent);
    }

    // Método para guardar el tipo de usuario (debes implementar esta lógica según tus necesidades)
    public void siguientePedido() {
        // Implementa la lógica para guardar el tipo de usuario
    }
}
