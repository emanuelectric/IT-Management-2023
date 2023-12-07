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
import com.example.itmanagement.activities.MenuPrincipalSuperadministradorActivity;
import com.example.itmanagement.data.DBHelper;

public class FormularioCategoriaProductoActivity extends AppCompatActivity {
    private EditText edittextNombreCategoriaProducto;
    private EditText edittextDescripcionCategoriaProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_categoria_producto_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreCategoriaProducto = findViewById(R.id.edittext_nombreCategoriaProducto);
        edittextDescripcionCategoriaProducto = findViewById(R.id.edittext_descripcionCategoriaProducto);

        // Agrega un TextWatcher para convertir automáticamente a mayúsculas - Nombre de Categoría
        edittextNombreCategoriaProducto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String textoMayusculas = editable.toString().toUpperCase();
                edittextNombreCategoriaProducto.removeTextChangedListener(this);
                edittextNombreCategoriaProducto.setText(textoMayusculas);
                edittextNombreCategoriaProducto.setSelection(textoMayusculas.length());
                edittextNombreCategoriaProducto.addTextChangedListener(this);
            }
        });
    }

    public void guardarCategoriaProducto(View view) {
        String nombreCategoriaProducto = edittextNombreCategoriaProducto.getText().toString();
        String descripcionCategoriaProducto = edittextDescripcionCategoriaProducto.getText().toString();

        if (!nombreCategoriaProducto.isEmpty()) {
            try {
                DBHelper dbHelper = new DBHelper(this);
                // Reemplaza con el correo del usuario logueado
                String correoUsuario = "correo@example.com";

                if (dbHelper.categoriaProductoNoExiste(nombreCategoriaProducto, descripcionCategoriaProducto)) {
                    dbHelper.crearCategoriaProducto(nombreCategoriaProducto, descripcionCategoriaProducto, correoUsuario);
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    irListaCategoriasProducto(view);
                } else {
                    Toast.makeText(this, "La Categoría de Producto ya existe", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("Error", "Error al guardar la Categoría de Producto", e);
                Toast.makeText(this, "Error al guardar la Categoría de Producto", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "El nombre de la Categoría de Producto no puede estar vacío", Toast.LENGTH_SHORT).show();
        }
    }

    // Retroceder, ir a la Lista de Categorias de Producto

    public void irListaCategoriasProducto(View view) {
        Intent intent = new Intent(this, ListaCategoriasProductoActivity.class);
        startActivity(intent);
    }
}
