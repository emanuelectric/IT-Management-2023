package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.TipoPedidoAdapter;  // Ajusta el nombre del adaptador
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.TipoPedido;  // Ajusta el nombre del modelo

import java.util.List;

public class ListaTiposPedidoActivity extends AppCompatActivity {

    private ListView listViewTiposPedido;
    private TipoPedidoAdapter tipoPedidoAdapter;
    private List<TipoPedido> listaTiposPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tipos_pedido_layout);  // Ajusta el nombre del layout
        // Llama al método para mostrar la lista de tipos de pedido
        mostrarListaTiposPedido();
    }

    private void mostrarListaTiposPedido() {
        // Obtén una referencia al ListView en tu layout
        listViewTiposPedido = findViewById(R.id.listView_ListaTiposPedido);  // Ajusta el ID del ListView

        // Obtén la lista de tipos de pedido desde la base de datos
        DBHelper dbHelper = new DBHelper(this);
        listaTiposPedido = dbHelper.obtenerListaTiposPedido();

        // Crea un adaptador personalizado para tus tipos de pedido (utiliza tu TipoPedidoAdapter)
        tipoPedidoAdapter = new TipoPedidoAdapter(this, listaTiposPedido);

        // Asigna el adaptador al ListView
        listViewTiposPedido.setAdapter(tipoPedidoAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewTiposPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en un tipo de pedido
                mostrarToast("Clic en " + listaTiposPedido.get(position).getNombreTipoPedido());
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

    // Agregar Tipo Pedido
    public void irAgregarTipoPedido(View view) {
        Intent intent = new Intent(this, FormularioTipoPedidoActivity.class);
        startActivity(intent);
    }


}
