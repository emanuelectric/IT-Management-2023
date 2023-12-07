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
import com.example.itmanagement.modelo.TipoPedido;

import java.util.List;

public class TipoPedidoAdapter extends ArrayAdapter<TipoPedido> {

    public TipoPedidoAdapter(Context context, List<TipoPedido> tiposPedido) {
        super(context, 0, tiposPedido);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoPedido tipoPedido = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tipo_pedido_layout, parent, false);  // Ajusta el nombre del layout
        }

        // Configura las vistas con la información del tipo de pedido
        ImageView imageViewIcono = convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewNombreTipoPedido = convertView.findViewById(R.id.textViewNombreTipoPedido);
        TextView textViewDescripcionTipoPedido = convertView.findViewById(R.id.textViewDescripcionTipoPedido);


        // Asigna los valores del tipo de pedido a las vistas
        textViewNombreTipoPedido.setText(tipoPedido.getNombreTipoPedido());
        textViewDescripcionTipoPedido.setText(tipoPedido.getDescripcionTipoPedido());

        return convertView;
    }

    // Editar
    public void editarTipoPedido(TipoPedido tipoPedido) {
        // Implementar la lógica
    }

    // Eliminar
    public void eliminarTipoPedido(TipoPedido tipoPedido) {
        // Implementar la lógica
    }
}
