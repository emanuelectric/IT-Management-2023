package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.CategoriaProductoAdapter;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.CategoriaProducto;

import java.util.List;

public class ListaCategoriasProductoActivity extends AppCompatActivity {

    private ListView listViewCategoriasProducto;
    private CategoriaProductoAdapter categoriaProductoAdapter;
    private List<CategoriaProducto> listaCategoriasProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_categorias_producto_layout);
        // Llama al método para mostrar la lista de categorías de productos
        mostrarListaCategoriasProducto();
    }

    private void mostrarListaCategoriasProducto() {
        // Obtén una referencia al ListView en tu layout
        listViewCategoriasProducto = findViewById(R.id.listView_ListaCategoriasProducto);

        // Obtén la lista de categorías de productos desde la base de datos
        DBHelper dbHelper = new DBHelper(this);
        listaCategoriasProducto = dbHelper.obtenerListaCategoriasProducto();

        // Crea un adaptador personalizado para tus categorías de productos (utiliza tu CategoriaProductoAdapter)
        categoriaProductoAdapter = new CategoriaProductoAdapter(this, listaCategoriasProducto);

        // Asigna el adaptador al ListView
        listViewCategoriasProducto.setAdapter(categoriaProductoAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewCategoriasProducto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en una categoría de producto
                mostrarToast("Clic en " + listaCategoriasProducto.get(position).getNombreCategoriaProducto());
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

    // Agregar Categoria Producto
    public void irAgregarCategoriaProducto(View view) {
        Intent intent = new Intent(this, FormularioCategoriaProductoActivity.class);
        startActivity(intent);
    }
}
