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
        setContentView(R.layout.formulario_restablecer_contrasenha_01_layout);

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

    @Override
    public void onBackPressed() {
        // Obtén el tipo de usuario del correo electrónico ingresado
        super.onBackPressed();
        String email = editTextRecuperarContrasenaCorreoElectronico.getText().toString();

        // Verifica si el campo de correo electrónico no está vacío
        if (!email.isEmpty()) {
            int tipoUsuario = dbHelper.obtenerTipoUsuarioPorCorreo(email);

            // Abre la actividad correspondiente basándose en el tipo de usuario
            abrirActividadSegunTipoUsuario(tipoUsuario);
        } else {
            // En caso de correo electrónico vacío, regresa a la pantalla de inicio de sesión sin argumentos
            abrirActividadSegunTipoUsuario(0);  // Proporciona un valor predeterminado o adecuado según tu lógica
        }
    }

    // Método para abrir la actividad según el tipo de usuario
    private void abrirActividadSegunTipoUsuario(int tipoUsuario) {
        Intent intent;

        switch (tipoUsuario) {
            case 1:
                intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
                break;
            case 3:
                intent = new Intent(this, MenuPrincipalAdministradorActivity.class);
                break;
            case 4:
                intent = new Intent(this, MenuPrincipalClienteActivity.class);
                break;
            default:
                intent = new Intent(this, MenuPrincipalClienteActivity.class);
                break;
        }

        // Inicia la nueva actividad y finaliza la actual si no se desea volver atrás
        startActivity(intent);
        finish();
    }
}
