package com.example.itmanagement.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.activities.EditarProductoActivity;
import com.example.itmanagement.modelo.Producto;

import java.util.List;

public class MenuProductoAdapter extends BaseAdapter {

    private final Context context;
    private final List<Producto> menuProductos;

    public MenuProductoAdapter(Context context, List<Producto> menuProductos) {
        this.context = context;
        this.menuProductos = menuProductos;
    }

    @Override
    public int getCount() {
        return menuProductos.size();
    }

    @Override
    public Object getItem(int position) {
        return menuProductos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Producto menuProducto = (Producto) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_menu_producto_layout, parent, false);
        }

        // Configura las vistas con la información del producto
        TextView textViewNombreProducto = convertView.findViewById(R.id.textViewNombreProducto);
        TextView textViewNombreCategoria = convertView.findViewById(R.id.textViewNombreCategoriaProducto);
        TextView textViewPrecioProducto = convertView.findViewById(R.id.textViewPrecioProducto);

        // Asigna los valores del producto a las vistas
        textViewNombreProducto.setText(menuProducto.getNombreProducto());
        // Suponiendo que hay un método getIdCategoriaProducto en el modelo Producto
        int idCategoria = menuProducto.getIdCategoriaProducto();
        // Puedes agregar la lógica para obtener el nombre de la categoría según el ID
        String nombreCategoria = obtenerNombreCategoriaPorId(idCategoria);
        textViewNombreCategoria.setText(nombreCategoria);
        textViewPrecioProducto.setText("Precio: " + String.valueOf(menuProducto.getPrecioProducto()));

        return convertView;
    }

    // Editar
    public void editarProducto(Producto menuProducto) {
        // Crea un Intent para iniciar EditarProductoActivity
        Intent intent = new Intent(context, EditarProductoActivity.class);

        // Puedes pasar información adicional a la actividad, como el ID del producto
        intent.putExtra("idProducto", menuProducto.getIdProducto());

        // Inicia la actividad
        context.startActivity(intent);
    }

    // Eliminar
    public void eliminarProducto(Producto menuProducto) {
        // Implementa la lógica para eliminar un producto
    }

    // Método para obtener el nombre de la categoría por ID (puedes ajustar según tus necesidades)
    private String obtenerNombreCategoriaPorId(int idCategoria) {
        // Lógica para obtener el nombre de la categoría según el ID
        // ...

        return "Nombre de la Categoría";  // Reemplazar con la lógica real
    }
}
