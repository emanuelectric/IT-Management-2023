package com.example.itmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.R;
import com.example.itmanagement.activities.EditarProductoActivity;
import com.example.itmanagement.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> {

    private DBHelper dbHelper;

    public ProductoAdapter(Context context, List<Producto> productos, DBHelper dbHelper) {
        super(context, 0, productos);
        this.dbHelper = dbHelper;  // Agregado para inicializar el dbHelper
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Producto producto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_producto_layout, parent, false);
        }

        // Configura las vistas con la información del producto
        TextView textViewNombreProducto = convertView.findViewById(R.id.textViewNombreProducto);
        TextView textViewDescripcionProducto = convertView.findViewById(R.id.textViewDescripcionProducto);
        TextView textViewNombreCategoria = convertView.findViewById(R.id.textViewNombreCategoriaProducto);

        // Asigna los valores del producto a las vistas
        textViewNombreProducto.setText(producto.getNombreProducto());
        textViewDescripcionProducto.setText(producto.getDescripcionProducto());

        // Obtiene el nombre de la categoría por ID
        int idCategoria = producto.getIdCategoriaProducto();  // El ID ya es un entero
        String nombreCategoria = dbHelper.obtenerNombreCategoriaPorId(idCategoria);

        textViewNombreCategoria.setText(nombreCategoria);

        return convertView;
    }

    // Editar
    public void editarProducto(Producto producto) {
        // Crea un Intent para iniciar EditarProductoActivity
        Intent intent = new Intent(getContext(), EditarProductoActivity.class);

        // Puedes pasar información adicional a la actividad, como el ID del producto
        intent.putExtra("idProducto", producto.getIdProducto());

        // Inicia la actividad
        getContext().startActivity(intent);
    }

    // Eliminar
    public void eliminarProducto(Producto producto) {
        // Implementa la lógica para eliminar un producto
    }
}
