package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class FormularioRolesActivity extends AppCompatActivity {
    private EditText edittextNombreTipoUsuario;
    private EditText edittextDescripcionTipoUsuario;
    private EditText edittextIdFormulario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_roles_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreTipoUsuario = findViewById(R.id.edittext_nombre_tipoUsuario);
        edittextDescripcionTipoUsuario = findViewById(R.id.edittext_descripcion_tipoUsuario);
        edittextIdFormulario = findViewById(R.id.edittext_id_formulario);
    }

    // Método para guardar el Tipo de Usuario
    public void guardarTipoUsuario(View view) {
        // Obtén los valores de los campos
        String nombreTipoUsuario = edittextNombreTipoUsuario.getText().toString();
        String descripcionTipoUsuario = edittextDescripcionTipoUsuario.getText().toString();
        String idFormularioString = edittextIdFormulario.getText().toString();

        // Verifica que los campos no estén vacíos
        if (!nombreTipoUsuario.isEmpty() && !descripcionTipoUsuario.isEmpty() && !idFormularioString.isEmpty()) {
            // Verifica que nombreTipoUsuario solo contenga letras del abecedario, minúsculas o mayúsculas, con o sin tilde
            if (nombreTipoUsuario.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ]+")) {
                try {
                    // Intenta convertir la cadena del ID del formulario a un entero
                    int idFormulario = Integer.parseInt(idFormularioString);

                    // Crea el nuevo Tipo de Usuario en la base de datos
                    DBHelper dbHelper = new DBHelper(this);
                    dbHelper.crearTipoUsuario(nombreTipoUsuario, descripcionTipoUsuario, idFormulario);

                    // Muestra un Toast indicando que el registro fue exitoso
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                    // Después de guardar, ir al menú principal
                    LanzarVistaMenuPrincipal(view);
                } catch (NumberFormatException e) {
                    // Captura la excepción si hay un error al convertir la cadena a un entero
                    Toast.makeText(this, "El campo ID del formulario no es un número válido", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Muestra un mensaje de error si nombreTipoUsuario no cumple con el formato
                Toast.makeText(this, "El nombre del Tipo de Usuario solo debe contener letras del abecedario", Toast.LENGTH_SHORT).show();
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
