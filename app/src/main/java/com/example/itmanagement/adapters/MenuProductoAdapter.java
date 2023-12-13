package com.example.itmanagement.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class MenuProductoAdapter extends ArrayAdapter<Producto> {

    private final Context context;
    private final List<Producto> menuProductos;
    private final DBHelper dbHelper;

    public MenuProductoAdapter(Context context, List<Producto> menuProductos, DBHelper dbHelper) {
        super(context, 0, menuProductos);
        this.context = context;
        this.menuProductos = menuProductos;
        this.dbHelper = dbHelper;
    }

    // Método para obtener los productos seleccionados
    public List<Producto> obtenerProductosSeleccionados() {
        List<Producto> productosSeleccionados = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            Producto producto = getItem(i);
            if (producto != null && producto.isSeleccionado()) {
                productosSeleccionados.add(producto);
            }
        }
        return productosSeleccionados;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_menu_producto_layout, parent, false);
        }

        Producto menuProducto = menuProductos.get(position);

        TextView textViewNombreProducto = convertView.findViewById(R.id.textViewNombreProducto);
        TextView textViewNombreCategoria = convertView.findViewById(R.id.textViewNombreCategoriaProducto);
        TextView textViewPrecioProducto = convertView.findViewById(R.id.textViewPrecioProducto);
        CheckBox checkBoxProducto = convertView.findViewById(R.id.checkBoxProducto);
        EditText editTextCantidad = convertView.findViewById(R.id.editTextCantidad);

        textViewNombreProducto.setText(menuProducto.getNombreProducto());

        int idCategoria = menuProducto.getIdCategoriaProducto();
        String nombreCategoria = dbHelper.obtenerNombreCategoriaPorId(idCategoria);
        textViewNombreCategoria.setText(nombreCategoria);

        textViewPrecioProducto.setText("Precio: " + String.valueOf(menuProducto.getPrecioProducto()));

        // Establecer el estado del checkbox
        checkBoxProducto.setOnCheckedChangeListener(null);
        checkBoxProducto.setChecked(menuProducto.isSeleccionado());

        // Establecer el valor del EditText
        editTextCantidad.setText(String.valueOf(menuProducto.getCantidadSeleccionada()));

        // Listener para el cambio de estado del checkbox
        checkBoxProducto.setOnCheckedChangeListener((buttonView, isChecked) -> {
            menuProducto.setSeleccionado(isChecked);
            // Actualizar la vista al cambiar la selección
            notifyDataSetChanged();
        });

        // Listener para el cambio de la cantidad
        editTextCantidad.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                int cantidad = obtenerCantidadSeleccionada(editTextCantidad);
                menuProducto.setCantidadSeleccionada(cantidad);
            }
        });

        // Logs adicionales para rastrear el flujo de ejecución
        Log.d("AdapterLogs", "getView - Producto: " + menuProducto.getNombreProducto() +
                ", Cantidad: " + menuProducto.getCantidadSeleccionada() +
                ", Seleccionado: " + menuProducto.isSeleccionado());

        return convertView;
    }

    private int obtenerCantidadSeleccionada(EditText editTextCantidad) {
        try {
            return Integer.parseInt(editTextCantidad.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
