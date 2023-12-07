package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.TipoPedido;

public class FormularioTipoPedidoActivity extends AppCompatActivity {

    private EditText edittextNombreTipoPedido;
    private EditText edittextDescripcionTipoPedido;
    private Button botonGuardarTipoPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_tipo_pedido_layout);

        // Obtén referencias a los elementos del layout
        edittextNombreTipoPedido = findViewById(R.id.edittext_nombreTipoPedido);
        edittextDescripcionTipoPedido = findViewById(R.id.edittext_descripcionTipoPedido);
        botonGuardarTipoPedido = findViewById(R.id.boton_guardarTipoPedido);

        edittextNombreTipoPedido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String textoMayusculas = editable.toString().toUpperCase();
                edittextNombreTipoPedido.removeTextChangedListener(this);
                edittextNombreTipoPedido.setText(textoMayusculas);
                edittextNombreTipoPedido.setSelection(textoMayusculas.length());
                edittextNombreTipoPedido.addTextChangedListener(this);
            }
        });
    }

    public void guardarTipoPedido(View view) {
        String nombreTipoPedido = edittextNombreTipoPedido.getText().toString();
        String descripcionTipoPedido = edittextDescripcionTipoPedido.getText().toString();

        if (!nombreTipoPedido.isEmpty() && !descripcionTipoPedido.isEmpty()) {
            // Aquí puedes crear una instancia de la clase TipoPedido
            TipoPedido tipoPedido = new TipoPedido(nombreTipoPedido, descripcionTipoPedido);

            try {
                DBHelper dbHelper = new DBHelper(this);
                String correoUsuario = "correo@example.com"; // Reemplaza con el correo del usuario logueado
                if (dbHelper.tipoPedidoNoExiste(nombreTipoPedido, descripcionTipoPedido)) {
                    dbHelper.crearTipoPedido(nombreTipoPedido, descripcionTipoPedido, correoUsuario);
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    irListaTiposPedido(view);
                } else {
                    Toast.makeText(this, "El Tipo de Pedido ya existe", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al guardar el Tipo de Pedido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        }
    }


    // Retroceder, ir Lista de Tipos Pedido
    public void irListaTiposPedido(View view) {
        Intent intent = new Intent(this, ListaTiposPedidoActivity.class);
        startActivity(intent);
    }


}
