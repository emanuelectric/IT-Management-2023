package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.ProductoAdapter;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Producto;

import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {

    private ListView listViewProductos;
    private ProductoAdapter productoAdapter;
    private List<Producto> listaProductos;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos_layout);

        // Inicializa el DBHelper
        dbHelper = new DBHelper(this);

        // Llama al método para mostrar la lista de productos
        mostrarListaProductos();
    }

    private void mostrarListaProductos() {
        // Obtén una referencia al ListView en tu layout
        listViewProductos = findViewById(R.id.listView_ListaProductos);

        // Obtén la lista de productos desde la base de datos
        listaProductos = dbHelper.obtenerListaProductos();

        // Crea un adaptador personalizado para tus productos (utiliza tu ProductoAdapter)
        productoAdapter = new ProductoAdapter(this, listaProductos, dbHelper);

        // Asigna el adaptador al ListView
        ListAdapter listAdapter = new ProductoAdapter(this, listaProductos, dbHelper);
        listViewProductos.setAdapter(listAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en un producto
                mostrarDetallesProducto(position);
            }
        });
    }

    // Método para mostrar detalles de un producto al hacer clic en él
    private void mostrarDetallesProducto(int position) {
        // Obtén el producto seleccionado
        Producto productoSeleccionado = listaProductos.get(position);

        // Puedes dirigirte a la actividad de edición o cualquier otra actividad según tus necesidades
        Intent intent = new Intent(ListaProductosActivity.this, EditarProductoActivity.class);
        intent.putExtra("idProducto", productoSeleccionado.getIdProducto());
        startActivity(intent);
    }

    // Método para mostrar un mensaje Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    // Método para ir atrás, Administrar Contenido
    public void lanzarVistaAdministrarContenido(View view) {
        Intent intent = new Intent(this, AdministrarContenidoActivity.class);
        startActivity(intent);
    }

    // Método para ir a la actividad de edición al tocar el ImageButton
    public void irEditarProducto(View view) {
        // Implementa el código para ir a la actividad de edición
    }

    // Método para eliminar un producto
    public void eliminarProducto(final View view) {
        // Implementa el código para eliminar un producto
    }

    // Agregar Producto
    public void irAgregarProducto(View view) {
        Intent intent = new Intent(this, FormularioProductosActivity.class);
        startActivity(intent);
    }
}
