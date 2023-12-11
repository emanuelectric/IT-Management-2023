package com.example.itmanagement.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Usuario;

import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {
    private DBHelper dbHelper;

    public UsuarioAdapter(Context context, List<Usuario> usuarios, DBHelper dbHelper) {
        super(context, 0, usuarios);
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario usuario = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario_layout, parent, false);
        }

        ImageView imageViewAvatar = convertView.findViewById(R.id.imageViewAvatar);
        TextView textViewNombreUsuario = convertView.findViewById(R.id.textViewNombreUsuario);
        Spinner spinnerTipoUsuario = convertView.findViewById(R.id.spinnerTipoUsuario);
        TextView textViewCorreoUsuario = convertView.findViewById(R.id.textViewCorreoUsuario);
        TextView textViewTelefonoUsuario = convertView.findViewById(R.id.textViewTelefonoUsuario);

        textViewNombreUsuario.setText(usuario.getNombreUsuario());
        textViewCorreoUsuario.setText(usuario.getCorreoElectronico());
        textViewTelefonoUsuario.setText(String.valueOf(usuario.getTelefonoUsuario()));

        // Configuración del Spinner
        List<String> opcionesTipoUsuario = dbHelper.obtenerOpcionesTipoUsuario();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, opcionesTipoUsuario);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoUsuario.setAdapter(adapter);

        // Asigna el tipo de usuario actual al Spinner
        spinnerTipoUsuario.setSelection(obtenerPosicionTipoUsuario(usuario.getIdTipoUsuario(), opcionesTipoUsuario));

        // Agrega el listener al Spinner
        spinnerTipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int selectedPosition, long id) {
                // Al seleccionar una opción diferente, muestra el cuadro de diálogo de confirmación
                mostrarDialogoConfirmacion(usuario, opcionesTipoUsuario.get(selectedPosition));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No es necesario hacer nada aquí
            }
        });

        return convertView;
    }

    private int obtenerPosicionTipoUsuario(int idTipoUsuario, List<String> opcionesTipoUsuario) {
        // Implementa la lógica para obtener la posición del tipo de usuario en la lista
        // Se puede recorrer la lista de opcionesTipoUsuario y comparar con el idTipoUsuario
        for (int i = 0; i < opcionesTipoUsuario.size(); i++) {
            if (idTipoUsuario == dbHelper.obtenerIdTipoUsuarioPorNombre(opcionesTipoUsuario.get(i))) {
                return i;
            }
        }
        return 0;  // Por defecto, retorna la primera posición
    }

    private void mostrarDialogoConfirmacion(final Usuario usuario, final String nuevaOpcion) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("¿Estás seguro de cambiar el rol de este usuario?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Realiza la acción de cambiar el rol del usuario aquí
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Si el usuario elige "No", no realiza ninguna acción
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
