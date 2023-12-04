package com.example.itmanagement.activities;

// Importar la clase MD5Util
import com.example.itmanagement.util.MD5Util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itmanagement.data.DBHelper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;

public class FormularioCambiarContrasenha02Activity extends AppCompatActivity {
    private DBHelper dbHelper;

    private EditText nuevaContrasenhaEditText;
    private EditText confirmarNuevaContrasenhaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restablecer_contrasenha_02);

        // Inicializar dbHelper
        dbHelper = new DBHelper(this);

        // Obtener referencias de los EditText
        nuevaContrasenhaEditText = findViewById(R.id.edittext_recuperarContrasenha_nuevaContrasenha);
        confirmarNuevaContrasenhaEditText = findViewById(R.id.edittext_edittext_recuperarContrasenha_confirmarNuevaContrasenha);

        // Obtener referencia del botón
        Button restablecerContrasenhaButton = findViewById(R.id.boton_restablecer_contrasenha);
        restablecerContrasenhaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restablecerContrasenha();
            }
        });
    }

    // Método para manejar el clic en el botón Restablecer
    private void restablecerContrasenha() {
        String correo = getIntent().getStringExtra("correo");
        String nuevaContrasenha = nuevaContrasenhaEditText.getText().toString();
        String confirmarNuevaContrasenha = confirmarNuevaContrasenhaEditText.getText().toString();

        if (!nuevaContrasenha.isEmpty() && !confirmarNuevaContrasenha.isEmpty()) {
            // Verificar si las contraseñas coinciden
            if (nuevaContrasenha.equals(confirmarNuevaContrasenha)) {
                // Contraseñas coinciden, encriptar la nueva contraseña con MD5
                String hashedPassword = MD5Util.encrypt(nuevaContrasenha);

                // Actualizar la contraseña en la base de datos
                dbHelper.actualizarContrasenha(correo, hashedPassword);

                // Muestra un mensaje de éxito
                Toast.makeText(this, "Contraseña restablecida exitosamente", Toast.LENGTH_SHORT).show();

                // Después de restablecer la contraseña con éxito, llamar a LanzarVistaFormularioCambiarContrasenha01
                LanzarVistaFormularioCambiarContrasenha01();
            } else {
                // Contraseñas no coinciden, mostrar Toast
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Mostrar Toast indicando que los campos están vacíos
            Toast.makeText(this, "Por favor, ingresa ambas contraseñas", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para ir a la pantalla anterior
    public void LanzarVistaFormularioCambiarContrasenha01() {
        Intent intent = new Intent(this, FormularioCambiarContrasenha01Activity.class);
        startActivity(intent);
    }
}
