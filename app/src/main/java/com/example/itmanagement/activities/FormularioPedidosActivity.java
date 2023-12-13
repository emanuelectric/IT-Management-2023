package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.MenuProductoAdapter;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Producto;

import java.util.List;

public class FormularioPedidosActivity extends AppCompatActivity {

    private ListView listViewMenuProductos;
    private Button botonSiguiente;
    private MenuProductoAdapter menuProductoAdapter;
    private DBHelper dbHelper;
    private Spinner spinnerTipoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_pedidos_layout);

        // Asociar vistas
        listViewMenuProductos = findViewById(R.id.listViewMenuProductos);
        botonSiguiente = findViewById(R.id.boton_siguiente);
        spinnerTipoPedido = findViewById(R.id.spinnerTipoPedido);

        // Configurar ListView
        dbHelper = new DBHelper(this);
        List<Producto> listaMenuProductos = dbHelper.obtenerListaMenuProductos();
        menuProductoAdapter = new MenuProductoAdapter(this, listaMenuProductos, dbHelper);
        listViewMenuProductos.setAdapter(menuProductoAdapter);

        // Configurar el clic del botón Siguiente
        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguientePedido();
            }
        });
    }

    private void cargarTiposPedidoEnSpinner() {
        List<String> tiposPedido = dbHelper.obtenerTiposPedido();

        if (tiposPedido.isEmpty()) {
            tiposPedido.add("Tipo de Pedido Predeterminado");
        }

        ArrayAdapter<String> tipoPedidoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposPedido);
        tipoPedidoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoPedido.setAdapter(tipoPedidoAdapter);
    }

    public void irListaPedidos(View view) {
        Intent intent = new Intent(this, ListaPedidosActivity.class);
        startActivity(intent);
    }

    public List<Producto> obtenerProductosSeleccionados() {
        return menuProductoAdapter.obtenerProductosSeleccionados();
    }

    public void siguientePedido() {
        // Obtener los productos seleccionados y el tipo de pedido
        List<Producto> productosSeleccionados = obtenerProductosSeleccionados();
        String tipoPedido = spinnerTipoPedido.getSelectedItem().toString();

        // Imprimir en el log los productos seleccionados
        for (Producto producto : productosSeleccionados) {
            Log.d("ProductoSeleccionado", "Producto: " + producto.getNombreProducto() +
                    ", Cantidad: " + producto.getCantidadSeleccionada());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarTiposPedidoEnSpinner();
    }
}
