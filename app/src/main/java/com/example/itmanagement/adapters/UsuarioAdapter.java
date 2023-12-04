package com.example.itmanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Usuario;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    public UsuarioAdapter(Context context, List<Usuario> usuarios) {
        super(context, 0, usuarios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario usuario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario_layout, parent, false);
        }

        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewNombreUsuario = convertView.findViewById(R.id.textViewNombreUsuario);
        Switch switchAdmin = convertView.findViewById(R.id.switch2);
        TextView textViewCorreoUsuario = convertView.findViewById(R.id.textViewCorreoUsuario);
        TextView textViewTelefonoUsuario = convertView.findViewById(R.id.textViewTelefonoUsuario);

        // Aquí estableces los valores para cada vista usando los datos del usuario
        textViewNombreUsuario.setText(usuario.getNombreUsuario());
        textViewCorreoUsuario.setText(usuario.getCorreoElectronico());
        textViewTelefonoUsuario.setText(String.valueOf(usuario.getTelefonoUsuario()));

        // Configuración del estado del Switch
        switchAdmin.setChecked(usuario.getIdTipoUsuario() == 3);

        // Agrega un listener para manejar cambios en el estado del Switch
        switchAdmin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Actualiza el id_tipo_usuario en la base de datos según el estado del Switch
            int nuevoIdTipoUsuario = isChecked ? 3 : 4;
            usuario.setIdTipoUsuario(nuevoIdTipoUsuario);
            DBHelper dbHelper = new DBHelper(getContext());
            dbHelper.actualizarTipoUsuario(String.valueOf(usuario.getIdTipoUsuario()), nuevoIdTipoUsuario);

        });

        return convertView;
    }

}
