package com.example.itmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itmanagement.R;
import com.example.itmanagement.activities.EditarProductoActivity;
import com.example.itmanagement.modelo.Producto;
import com.example.itmanagement.data.DBHelper;

import java.util.List;

public class ProductoAdapter extends ArrayAdapter<Producto> implements ListAdapter {

    private final Context context;
    private final List<Producto> productos;
    private final DBHelper dbHelper;

    public ProductoAdapter(Context context, List<Producto> productos, DBHelper dbHelper) {
        super(context, 0, productos);
        this.context = context;
        this.productos = productos;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_producto_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Producto producto = productos.get(position);

        holder.textViewNombreProducto.setText(producto.getNombreProducto());
        holder.textViewDescripcionProducto.setText(producto.getDescripcionProducto());

        int idCategoria = producto.getIdCategoriaProducto();
        String nombreCategoria = dbHelper.obtenerNombreCategoriaPorId(idCategoria);
        holder.textViewNombreCategoria.setText(nombreCategoria);

        holder.textViewCantidadStock.setText("Cantidad en Stock: " + String.valueOf(producto.getCantidadStock()));

        if (holder.imageButtonEditar != null) {
            holder.imageButtonEditar.setOnClickListener(view -> editarProducto(producto));
        }

        if (holder.imageButtonEliminar != null) {
            holder.imageButtonEliminar.setOnClickListener(view -> eliminarProducto(producto));
        }

        return convertView;
    }

    private static class ViewHolder {
        public TextView textViewNombreProducto;
        public TextView textViewDescripcionProducto;
        public TextView textViewNombreCategoria;
        public TextView textViewCantidadStock;
        public ImageButton imageButtonEditar;
        public ImageButton imageButtonEliminar;

        public ViewHolder(View view) {
            textViewNombreProducto = view.findViewById(R.id.textViewNombreProducto);
            textViewDescripcionProducto = view.findViewById(R.id.textViewDescripcionProducto);
            textViewNombreCategoria = view.findViewById(R.id.textViewNombreCategoriaProducto);
            textViewCantidadStock = view.findViewById(R.id.textViewCantidadStock);
            imageButtonEditar = view.findViewById(R.id.imageButtonEditar);
            imageButtonEliminar = view.findViewById(R.id.imageButtonEliminar);
        }
    }

    private void editarProducto(Producto producto) {
        Intent intent = new Intent(context, EditarProductoActivity.class);
        intent.putExtra("idProducto", producto.getIdProducto());
        context.startActivity(intent);
    }

    private void eliminarProducto(Producto producto) {
        // Implementa la lógica para eliminar un producto
        // Puedes mostrar un diálogo de confirmación antes de realizar la eliminación
    }
}
