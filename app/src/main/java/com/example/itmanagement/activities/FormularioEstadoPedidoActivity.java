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
import com.example.itmanagement.modelo.EstadoPedido;

public class FormularioEstadoPedidoActivity extends AppCompatActivity {

    private EditText edittextNombreEstadoPedido;
    private EditText edittextDescripcionEstadoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_estado_pedido_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreEstadoPedido = findViewById(R.id.edittext_nombreEstadoPedido);
        edittextDescripcionEstadoPedido = findViewById(R.id.edittext_descripcionEstadoPedido);

        // Agrega un TextWatcher para convertir automáticamente a mayúsculas - Nombre de Divisa
        edittextNombreEstadoPedido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String textoMayusculas = editable.toString().toUpperCase();
                edittextNombreEstadoPedido.removeTextChangedListener(this);
                edittextNombreEstadoPedido.setText(textoMayusculas);
                edittextNombreEstadoPedido.setSelection(textoMayusculas.length());
                edittextNombreEstadoPedido.addTextChangedListener(this);
            }
        });
    }

    public void guardarEstadoPedido(View view) {
        String nombreEstadoPedido = edittextNombreEstadoPedido.getText().toString();
        String descripcionEstadoPedido = edittextDescripcionEstadoPedido.getText().toString();

        if (!nombreEstadoPedido.isEmpty() && !descripcionEstadoPedido.isEmpty()) {
            // Crea una instancia de la clase EstadoPedido
            EstadoPedido estadoPedido = new EstadoPedido(nombreEstadoPedido, descripcionEstadoPedido);

            try {
                DBHelper dbHelper = new DBHelper(this);
                String correoUsuario = "correo@example.com"; // Reemplaza con el correo del usuario logueado
                dbHelper.crearEstadoPedido(nombreEstadoPedido, descripcionEstadoPedido, correoUsuario);
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                irListaEstadosPedido(view);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar el Estado de Pedido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }

    // Retroceder, ir Lista de Estados de Pedido
    public void irListaEstadosPedido(View view) {
        Intent intent = new Intent(this, ListaEstadosPedidoActivity.class);
        startActivity(intent);
    }

}
