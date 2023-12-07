package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.EstadoPedidoAdapter;  // Ajusta el nombre del adaptador
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.EstadoPedido;  // Ajusta el nombre del modelo

import java.util.List;

public class ListaEstadosPedidoActivity extends AppCompatActivity {

    private ListView listViewEstadosPedido;
    private EstadoPedidoAdapter estadoPedidoAdapter;
    private List<EstadoPedido> listaEstadosPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_estados_pedido_layout);  // Ajusta el nombre del layout
        // Llama al método para mostrar la lista de estados de pedido
        mostrarListaEstadosPedido();
    }

    private void mostrarListaEstadosPedido() {
        // Obtén una referencia al ListView en tu layout
        listViewEstadosPedido = findViewById(R.id.listView_ListaEstadosPedido);  // Ajusta el ID del ListView

        // Obtén la lista de estados de pedido desde la base de datos
        DBHelper dbHelper = new DBHelper(this);
        listaEstadosPedido = dbHelper.obtenerListaEstadosPedido();

        // Crea un adaptador personalizado para tus estados de pedido (utiliza tu EstadoPedidoAdapter)
        estadoPedidoAdapter = new EstadoPedidoAdapter(this, listaEstadosPedido);

        // Asigna el adaptador al ListView
        listViewEstadosPedido.setAdapter(estadoPedidoAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewEstadosPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en un estado de pedido
                mostrarToast("Clic en " + listaEstadosPedido.get(position).getNombreEstadoPedido());
            }
        });
    }

    // Método para mostrar un mensaje Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    // Método para ir a la pantalla principal
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }

    // Agregar Estado Pedido
    public void irAgregarEstadoPedido(View view) {
        Intent intent = new Intent(this, FormularioEstadoPedidoActivity.class);
        startActivity(intent);
    }

}
