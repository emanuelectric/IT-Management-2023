package com.example.itmanagement.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PerfilActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper dbHelper;
    private TextView textViewNombreUsuario;
    private TextView textViewCorreoUsuario;
    private TextView textViewTelefonoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_layout);

        // Inicializar DBHelper
        dbHelper = new DBHelper(this);

        // Inicializar vistas
        textViewNombreUsuario = findViewById(R.id.textViewNombreUsuario);
        textViewCorreoUsuario = findViewById(R.id.textViewCorreoUsuario);
        textViewTelefonoUsuario = findViewById(R.id.textViewTelefonoUsuario);

        // Obtener datos de la base de datos y establecer en los TextView
        obtenerDatosUsuario();

        // Configurar listeners para los botones
        Button botonCambiarNombre = findViewById(R.id.botonCambiarNombre);
        Button botonCambiarCorreo = findViewById(R.id.botonCambiarCorreo);
        Button botonCambiarTelefono = findViewById(R.id.botonCambiarTelefono);
        Button botonCambiarContrasenha = findViewById(R.id.botonCambiarContrasenha);

        botonCambiarNombre.setOnClickListener(this);
        botonCambiarCorreo.setOnClickListener(this);
        botonCambiarTelefono.setOnClickListener(this);
        botonCambiarContrasenha.setOnClickListener(this);
    }

    // Método para obtener datos de la base de datos y establecer en los TextView
    private void obtenerDatosUsuario() {
        // Obtener el ID del usuario logueado
        long idUsuarioAutenticado = getUsuarioLogueado();

        // Obtener datos del usuario
        Cursor cursor = dbHelper.getUsuarioData(idUsuarioAutenticado);

        // Verificar si hay datos en el cursor
        if (cursor != null && cursor.moveToFirst()) {
            // Obtener índices de las columnas
            int columnIndexNombre = cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE_USUARIO);
            int columnIndexCorreo = cursor.getColumnIndex(DBHelper.COLUMN_CORREO_ELECTRONICO);
            int columnIndexTelefono = cursor.getColumnIndex(DBHelper.COLUMN_TELEFONO_USUARIO);

            // Obtener datos de las columnas
            String nombreUsuario = cursor.getString(columnIndexNombre);
            String correoUsuario = cursor.getString(columnIndexCorreo);
            String telefonoUsuario = String.valueOf(cursor.getInt(columnIndexTelefono));

            // Establecer datos en los TextView
            textViewNombreUsuario.setText(nombreUsuario);
            textViewCorreoUsuario.setText(correoUsuario);
            textViewTelefonoUsuario.setText(telefonoUsuario);

            // Cerrar el cursor
            cursor.close();
        }
    }



    // BOTONES PARA AJUSTES DE USUARIO
    @Override
    public void onClick(View view) {
        // Verificar si el ID del botón está en el mapa
        if (buttonActivityMap.containsKey(view.getId())) {
            // Obtener la clase de la actividad correspondiente
            Class<?> activityClass = buttonActivityMap.get(view.getId());

            // Verificar si la clase no es nula antes de abrir la actividad
            if (activityClass != null) {
                Intent intent = new Intent(this, activityClass);
                startActivity(intent);
            }
        }
    }

    // Mapa que asocia ID de botones a clases de actividad
    private final Map<Integer, Class<?>> buttonActivityMap = new HashMap<Integer, Class<?>>() {{
        put(R.id.botonCambiarNombre, FormularioCambiarContrasenha01Activity.class);
        put(R.id.botonCambiarCorreo, FormularioCambiarContrasenha01Activity.class);
        put(R.id.botonCambiarTelefono, FormularioCambiarContrasenha01Activity.class);
        put(R.id.botonCambiarContrasenha, FormularioCambiarContrasenha01Activity.class);
    }};

    // ID USUARIO LOGUEADO

    // Función para obtener el ID del usuario logueado
    private long getUsuarioLogueado() {
        return dbHelper.getUsuarioLogueado();

    }


    // Método para ir a la pantalla principal
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }


}
