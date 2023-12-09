package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class RestablecerContrasenha01Activity extends AppCompatActivity {

    private DBHelper dbHelper;
    private EditText editTextRecuperarContrasenaCorreoElectronico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restablecer_contrasenha_01);

        dbHelper = new DBHelper(this);
        editTextRecuperarContrasenaCorreoElectronico = findViewById(R.id.edittext_recuperarContrasenha_correoElectronico);
    }

    public void siguienteRestablecerContrasena(View view) {
        String correo = editTextRecuperarContrasenaCorreoElectronico.getText().toString();

        if (!correo.isEmpty()) {
            if (dbHelper.comprobarCorreo(correo)) {
                Intent intent = new Intent(this, RestablecerContrasenha02Activity.class);
                intent.putExtra("correo", correo);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No hay actividad para manejar la intención", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Correo no encontrado", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa tu correo electrónico", Toast.LENGTH_SHORT).show();
        }
    }

    // Retroceder, pantalla Iniciar Sesion
    public void LanzarVistaIniciarSesion(View view) {
        Intent intent = new Intent(this, IniciarSesionActivity.class);
        startActivity(intent);
    }
}
