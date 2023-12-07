package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

        // Agrega un TextWatcher para convertir automáticamente a mayúsculas - Nombre de Divisa
        edittextNombreDivisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String textoMayusculas = editable.toString().toUpperCase();
                edittextNombreDivisa.removeTextChangedListener(this);
                edittextNombreDivisa.setText(textoMayusculas);
                edittextNombreDivisa.setSelection(textoMayusculas.length());
                edittextNombreDivisa.addTextChangedListener(this);
            }
        });

        // Agrega un TextWatcher para convertir automáticamente a mayúsculas - Símbolo de Divisa
        edittextSimboloDivisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String textoMayusculas = editable.toString().toUpperCase();
                edittextSimboloDivisa.removeTextChangedListener(this);
                edittextSimboloDivisa.setText(textoMayusculas);
                edittextSimboloDivisa.setSelection(textoMayusculas.length());
                edittextSimboloDivisa.addTextChangedListener(this);
            }
        });
    }

    public void guardarDivisa(View view) {
        String nombreDivisa = edittextNombreDivisa.getText().toString();
        String simboloDivisa = edittextSimboloDivisa.getText().toString();

        if (!nombreDivisa.isEmpty() && !simboloDivisa.isEmpty()) {
            if (nombreDivisa.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
                if (simboloDivisa.matches("[A-Z]{3}")) {
                    try {
                        DBHelper dbHelper = new DBHelper(this);
                        String correoUsuario = "correo@example.com"; // Reemplaza con el correo del usuario logueado
                        if (dbHelper.divisaNoExiste(nombreDivisa, simboloDivisa)) {
                            dbHelper.crearDivisa(nombreDivisa, simboloDivisa, correoUsuario);
                            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            irListaDivisas(view);
                        } else {
                            Toast.makeText(this, "La Divisa ya existe", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Log.e("Error", "Error al guardar la Divisa", e);
                        Toast.makeText(this, "Error al guardar la Divisa", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "El símbolo de la Divisa debe tener 3 letras mayúsculas", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "El nombre de la Divisa solo debe contener letras del abecedario", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }

    // Retroceder, ir Lista de Divisas
    public void irListaDivisas(View view) {
        Intent intent = new Intent(this, ListaDivisasActivity.class);
        startActivity(intent);
    }

}
