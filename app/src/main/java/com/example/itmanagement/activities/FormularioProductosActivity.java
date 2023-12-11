package com.example.itmanagement.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

import java.util.List;

public class FormularioProductosActivity extends AppCompatActivity {

    private EditText edittextNombreProducto;
    private EditText edittextDescripcionProducto;
    private EditText editTextPrecioProducto;
    private EditText editTextCantidadStock;
    private Spinner spinnerCategoria;
    private Button botonGuardarProducto;

    private int idCategoriaProducto = 0; // Supongamos que 0 es el ID por defecto
    private DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_productos_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreProducto = findViewById(R.id.edittext_nombreProducto);
        edittextDescripcionProducto = findViewById(R.id.edittext_descripcionProducto);
        editTextPrecioProducto = findViewById(R.id.edittext_precioProducto);
        editTextCantidadStock = findViewById(R.id.edittext_cantidadStock);
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        botonGuardarProducto = findViewById(R.id.boton_guardarProducto);

        // Inicializa el DBHelper
        dbHelper = new DBHelper(this);

        // Configura el adapter para el Spinner con las categorías de la base de datos
        cargarCategoriasEnSpinner();

        // Maneja la selección del Spinner
        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Guarda el ID de la categoría seleccionada
                idCategoriaProducto = obtenerIdCategoriaSeleccionada();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // No es necesario hacer nada aquí
            }
        });
    }

    private void cargarCategoriasEnSpinner() {
        // Obtiene las categorías desde la base de datos
        List<String> categorias = dbHelper.obtenerCategoriaProducto();

        // Configura el adapter para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Aplica el adapter al Spinner
        spinnerCategoria.setAdapter(adapter);
    }

    private int obtenerIdCategoriaSeleccionada() {
        // Obtiene el ID de la categoría seleccionada desde la base de datos
        String categoriaSeleccionada = (String) spinnerCategoria.getSelectedItem();
        return dbHelper.obtenerIdCategoriaProducto(categoriaSeleccionada);
    }

    // Método para guardar el Producto
    public void guardarProducto(View view) {
        // Obtener los valores de los campos
        String nombreProducto = edittextNombreProducto.getText().toString();
        String descripcionProducto = edittextDescripcionProducto.getText().toString();
        String precioProductoString = editTextPrecioProducto.getText().toString();
        String cantidadStockString = editTextCantidadStock.getText().toString();

        // Verificar que los campos no estén vacíos
        if (!nombreProducto.isEmpty() && !precioProductoString.isEmpty()) {
            // Verificar que nombreProducto solo contenga letras del abecedario, minúsculas o mayúsculas, con o sin tilde
            if (nombreProducto.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+")) {
                // Intentar convertir el precioProductoString a un número entero
                try {
                    int precioProducto = Integer.parseInt(precioProductoString);
                    int cantidadStock = Integer.parseInt(cantidadStockString);


                    // Proceder a guardar el producto
                    if (dbHelper.productoNoExiste(nombreProducto)) {
                        // El producto no existe, entonces procedemos a crearlo
                        dbHelper.crearProducto(nombreProducto, descripcionProducto, precioProducto, cantidadStock, idCategoriaProducto);

                        // Muestra un Toast indicando que el registro fue exitoso
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                        // Después de guardar, ir al menú principal
                        irListaProductos(view);
                    } else {
                        // Muestra un mensaje indicando que el producto ya existe
                        Toast.makeText(this, "El producto ya existe", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Muestra un mensaje de error si el precio no es un número válido
                    Toast.makeText(this, "El precio debe ser un número válido", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Muestra un mensaje de error si nombreProducto no cumple con el formato
                Toast.makeText(this, "El nombre del producto solo debe contener letras del abecedario", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Muestra un mensaje de error si algún campo está vacío
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }


    // Retroceder, ir Lista de Productos
    public void irListaProductos(View view) {
        Intent intent = new Intent(this, ListaProductosActivity.class);
        startActivity(intent);
    }


}
