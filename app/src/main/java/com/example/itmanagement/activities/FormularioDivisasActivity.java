package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class FormularioDivisasActivity extends AppCompatActivity {

    private EditText edittextNombreDivisa;
    private EditText edittextSimboloDivisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_divisas_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreDivisa = findViewById(R.id.edittext_nombreDivisa);
        edittextSimboloDivisa = findViewById(R.id.edittext_simboloDivisa);
    }

    // Método para guardar la Divisa
    public void guardarDivisa(View view) {
        // Obtén los valores de los campos
        String nombreDivisa = edittextNombreDivisa.getText().toString();
        String simboloDivisa = edittextSimboloDivisa.getText().toString();

        // Verifica que los campos no estén vacíos
        if (!nombreDivisa.isEmpty() && !simboloDivisa.isEmpty()) {
            // Verifica que nombreDivisa solo contenga letras del abecedario, minúsculas o mayúsculas, con o sin tilde
            if (nombreDivisa.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
                // Verifica que simboloDivisa solo contenga letras mayúsculas y tenga exactamente 3 caracteres
                if (simboloDivisa.matches("[A-Z]{3}")) {
                    try {
                        // Verifica si la divisa ya existe antes de intentar crearla
                        DBHelper dbHelper = new DBHelper(this);
                        if (dbHelper.divisaNoExiste(nombreDivisa, simboloDivisa)) {
                            // La divisa no existe, entonces procedemos a crearla
                            dbHelper.crearDivisa(nombreDivisa, simboloDivisa);

                            // Muestra un Toast indicando que el registro fue exitoso
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                            // Después de guardar, ir al menú principal
                            LanzarVistaMenuPrincipal(view);
                        } else {
                            // Muestra un mensaje indicando que la divisa ya existe
                            Toast.makeText(this, "La Divisa ya existe", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        // Captura cualquier excepción que pueda ocurrir
                        Log.e("Error", "Error al guardar la Divisa", e);
                        Toast.makeText(this, "Error al guardar la Divisa", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Muestra un mensaje de error si simboloDivisa no cumple con el formato
                    Toast.makeText(this, "El símbolo de la Divisa debe tener 3 letras mayúsculas", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Muestra un mensaje de error si nombreDivisa no cumple con el formato
                Toast.makeText(this, "El nombre de la Divisa solo debe contener letras del abecedario", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Muestra un mensaje de error si algún campo está vacío
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }



    // Método para ir a la pantalla principal
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }
}
