package com.example.itmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.modelo.CategoriaProducto;

import java.util.List;

public class CategoriaProductoAdapter extends ArrayAdapter<CategoriaProducto> {

    public CategoriaProductoAdapter(Context context, List<CategoriaProducto> categoriasProducto) {
        super(context, 0, categoriasProducto);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoriaProducto categoriaProducto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_categoria_producto_layout, parent, false);  // Ajusta el nombre del layout
        }

        // Configura las vistas con la información de la categoría de producto
        ImageView imageViewIcono = convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewNombreCategoriaProducto = convertView.findViewById(R.id.textViewNombreCategoriaProducto);
        TextView textViewDescripcionCategoriaProducto = convertView.findViewById(R.id.textViewDescripcionCategoriaProducto);

        // Asigna los valores de la categoría de producto a las vistas
        textViewNombreCategoriaProducto.setText(categoriaProducto.getNombreCategoriaProducto());
        textViewDescripcionCategoriaProducto.setText(categoriaProducto.getDescripcionCategoriaProducto());


        return convertView;
    }

    // Editar
    public void editarCategoriaProducto(CategoriaProducto categoriaProducto) {
        // Implementar la lógica
    }

    // Eliminar
    public void eliminarCategoriaProducto(CategoriaProducto categoriaProducto) {
        // Implementar la lógica
    }
}
