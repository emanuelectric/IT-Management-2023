package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Divisa;

public class EditarDivisaActivity extends AppCompatActivity {

    private EditText editTextNombreDivisa;
    private EditText editTextSimboloDivisa;
    private Button botonEditarDivisa;
    private ImageView imageViewVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_divisa_layout);

        // Inicializa las vistas
        editTextNombreDivisa = findViewById(R.id.edittext_editarNombreDivisa);
        editTextSimboloDivisa = findViewById(R.id.edittext_editarSimboloDivisa);
        botonEditarDivisa = findViewById(R.id.boton_editarDivisa);
        imageViewVolver = findViewById(R.id.imageView);

        // Obtén la divisa seleccionada (puedes pasarlo a través de Intent)
        Divisa divisaSeleccionada = obtenerDivisaSeleccionada();

        // Muestra el nombre y símbolo de la divisa seleccionada
        editTextNombreDivisa.setText(divisaSeleccionada.getNombreDivisa());
        editTextSimboloDivisa.setText(divisaSeleccionada.getSimboloDivisa());

        // Configura el evento onClick para el botón de editar divisa
        botonEditarDivisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Agrega verificaciones antes de editar la divisa
                if (verificarCampos()) {
                    editarDivisa(divisaSeleccionada);
                }
            }
        });

        // Configura el evento onClick para el botón de volver
        imageViewVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    // Método para obtener la divisa seleccionada desde la actividad anterior
    private Divisa obtenerDivisaSeleccionada() {
        // Implementa tu lógica para obtener la divisa seleccionada, esto puede variar según tu implementación
        // Puedes pasar la divisa como un extra a través de Intent o cualquier otro método que estés utilizando
        return null;
    }

    // Método para editar la divisa
    private void editarDivisa(Divisa divisa) {
        // Obtiene los nuevos valores del nombre y símbolo desde las vistas
        String nuevoNombre = editTextNombreDivisa.getText().toString();
        String nuevoSimbolo = editTextSimboloDivisa.getText().toString();

        // Actualiza la divisa en la base de datos
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.actualizarDivisa(divisa.getIdDivisa(), nuevoNombre, nuevoSimbolo);

        // Muestra un mensaje de éxito
        Toast.makeText(this, "Divisa editada correctamente", Toast.LENGTH_SHORT).show();
    }

    // Método para verificar que los campos estén completos y cumplan con los requisitos
    private boolean verificarCampos() {
        String nuevoNombre = editTextNombreDivisa.getText().toString();
        String nuevoSimbolo = editTextSimboloDivisa.getText().toString();

        // Verifica que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevoSimbolo.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Verifica que nuevoNombre solo contenga letras del abecedario, minúsculas o mayúsculas, con o sin tilde
        if (!nuevoNombre.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
            Toast.makeText(this, "El nombre de la Divisa solo debe contener letras del abecedario", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Verifica que nuevoSimbolo solo contenga letras mayúsculas y tenga exactamente 3 caracteres
        if (!nuevoSimbolo.matches("[A-Z]{3}")) {
            Toast.makeText(this, "El símbolo de la Divisa debe tener 3 letras mayúsculas", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
