package com.example.itmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.modelo.TipoUsuario;

import java.util.List;

public class TipoUsuarioAdapter extends ArrayAdapter<TipoUsuario> {

    public TipoUsuarioAdapter(Context context, List<TipoUsuario> tiposUsuario) {
        super(context, 0, tiposUsuario);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TipoUsuario tipoUsuario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tipo_usuario_layout, parent, false);  // Ajusta el nombre del layout
        }

        // Configura las vistas con la información del tipo de usuario
        ImageView imageViewIcono = convertView.findViewById(R.id.imageViewAvatar);  // Ajusta el ID de la imagen
        TextView textViewNombreTipoUsuario = convertView.findViewById(R.id.textViewNombreTipoUsuario);  // Ajusta el ID del TextView
        TextView textViewDescripcionTipoUsuario = convertView.findViewById(R.id.textViewDescripcionTipoUsuario);  // Ajusta el ID del TextView

        // Asigna los valores del tipo de usuario a las vistas
        textViewNombreTipoUsuario.setText(tipoUsuario.getNombreTipoUsuario());
        textViewDescripcionTipoUsuario.setText(tipoUsuario.getDescripcionTipoUsuario());

        return convertView;
    }

    // Editar
    public void editarTipoUsuario(TipoUsuario tipoUsuario) {
        // Implementar la lógica
    }

    // Eliminar
    public void eliminarTipoUsuario(TipoUsuario tipoUsuario) {
        // Implementar la lógica
    }
}
