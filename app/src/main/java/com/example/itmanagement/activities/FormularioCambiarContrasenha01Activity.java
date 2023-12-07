package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class FormularioCambiarContrasenha01Activity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText edittext_recuperarContrasenha_correoElectronico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_restablecer_contrasenha_01_layout);

        // Inicializar la instancia de DBHelper
        dbHelper = new DBHelper(this);

        // Obtener la referencia al EditText en el layout
        edittext_recuperarContrasenha_correoElectronico = findViewById(R.id.edittext_recuperarContrasenha_correoElectronico);
    }

    public void siguienteRestablecerContrasenha(View view) {
        String correo = edittext_recuperarContrasenha_correoElectronico.getText().toString();

        if (!correo.isEmpty()) {
            // Utilizar la instancia de DBHelper para verificar el correo en la base de datos
            if (dbHelper.comprobarCorreo(correo)) {
                // El correo está en la base de datos, iniciar la siguiente actividad
                Intent intent = new Intent(this, RestablecerContrasenha02Activity.class);
                intent.putExtra("correo", correo);
                startActivity(intent);
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
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }
}