package com.example.itmanagement.activities;

// Importar la clase MD5Util
import com.example.itmanagement.util.MD5Util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

// RestablecerContrasena01Activity es responsable de manejar la lógica para restablecer la contraseña
public class RestablecerContrasenha01Activity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText editTextRecuperarContrasenaCorreoElectronico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_cambiar_contrasenha_01_layout);

        // Inicializar la instancia de DBHelper
        dbHelper = new DBHelper(this);

        // Obtener la referencia al EditText en el layout
        editTextRecuperarContrasenaCorreoElectronico = findViewById(R.id.edittext_recuperarContrasenha_correoElectronico);
    }

    public void siguienteRestablecerContrasena(View view) {
        String correo = editTextRecuperarContrasenaCorreoElectronico.getText().toString();

        if (!correo.isEmpty()) {
            // Utilizar la instancia de DBHelper para verificar el correo en la base de datos
            if (dbHelper.comprobarCorreo(correo)) {
                // El correo está en la base de datos, iniciar la siguiente actividad
                Intent intent = new Intent(this, RestablecerContrasenha02Activity.class);
                intent.putExtra("correo", correo);

                // Verificar si hay actividades que pueden manejar la intención
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Manejar el caso en el que no hay actividades que puedan manejar la intención
                    Toast.makeText(this, "No hay actividad para manejar la intención", Toast.LENGTH_SHORT).show();
                }
            } else {
                // El correo no está en la base de datos, mostrar Toast
                Toast.makeText(this, "Correo no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mostrar Toast indicando que el campo de correo está vacío
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para ir a la pantalla inicial
    public void LanzarVistaMenuInicialRC(View view) {
        Intent intent = new Intent(this, IniciarSesionActivity.class);
        startActivity(intent);
    }
}
