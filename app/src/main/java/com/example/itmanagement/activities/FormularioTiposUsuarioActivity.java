package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class FormularioTiposUsuarioActivity extends AppCompatActivity {
    private EditText edittextNombreTipoUsuario;
    private EditText edittextDescripcionTipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_roles_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreTipoUsuario = findViewById(R.id.edittext_nombre_tipoUsuario);
        edittextDescripcionTipoUsuario = findViewById(R.id.edittext_descripcion_tipoUsuario);

        // Agrega un TextWatcher para convertir automáticamente a mayúsculas - Nombre de Tipo de Usuario
        edittextNombreTipoUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String textoMayusculas = editable.toString().toUpperCase();
                edittextNombreTipoUsuario.removeTextChangedListener(this);
                edittextNombreTipoUsuario.setText(textoMayusculas);
                edittextNombreTipoUsuario.setSelection(textoMayusculas.length());
                edittextNombreTipoUsuario.addTextChangedListener(this);
            }
        });
    }

    // Método para guardar el Tipo de Usuario
    public void guardarTipoUsuario(View view) {
        // Obtén los valores de los campos
        String nombreTipoUsuario = edittextNombreTipoUsuario.getText().toString();
        String descripcionTipoUsuario = edittextDescripcionTipoUsuario.getText().toString();

        // Verifica que los campos no estén vacíos
        if (!nombreTipoUsuario.isEmpty() && !descripcionTipoUsuario.isEmpty()) {
            try {
                // Crea el nuevo Tipo de Usuario en la base de datos
                DBHelper dbHelper = new DBHelper(this);
                dbHelper.crearTipoUsuario(nombreTipoUsuario, descripcionTipoUsuario);

                // Muestra un Toast indicando que el registro fue exitoso
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                // Después de guardar, ir al menú principal
                LanzarVistaMenuPrincipal(view);
            } catch (NumberFormatException e) {
                // Captura la excepción si hay un error al convertir la cadena a un entero
                Toast.makeText(this, "El campo ID del formulario no es un número válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Muestra un mensaje de error si algún campo está vacío
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para ir al menú principal SUPERADMIN
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }
}
