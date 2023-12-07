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
import com.example.itmanagement.modelo.EstadoPedido;

import java.util.List;

public class EstadoPedidoAdapter extends ArrayAdapter<EstadoPedido> {

    public EstadoPedidoAdapter(Context context, List<EstadoPedido> estadosPedido) {
        super(context, 0, estadosPedido);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EstadoPedido estadoPedido = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_estado_pedido_layout, parent, false);  // Ajusta el nombre del layout
        }

        // Configura las vistas con la información del estado de pedido
        ImageView imageViewIcono = convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewNombreEstadoPedido = convertView.findViewById(R.id.textViewNombreEstadoPedido);

        // Asigna los valores del estado de pedido a las vistas
        textViewNombreEstadoPedido.setText(estadoPedido.getNombreEstadoPedido());

        return convertView;
    }

    // Editar
    public void editarEstadoPedido(EstadoPedido estadoPedido) {
        // Implementar la lógica
    }

    // Eliminar
    public void eliminarEstadoPedido(EstadoPedido estadoPedido) {
        // Implementar la lógica
    }
}
