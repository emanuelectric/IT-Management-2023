package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.TipoUsuarioAdapter;  // Ajusta el nombre del adaptador
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.TipoUsuario;  // Ajusta el nombre del modelo

import java.util.List;

public class ListaTiposUsuarioActivity extends AppCompatActivity {

    private ListView listViewTiposUsuario;
    private TipoUsuarioAdapter tipoUsuarioAdapter;
    private List<TipoUsuario> listaTiposUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tipo_usuario_layout);  // Ajusta el nombre del layout
        // Llama al método para mostrar la lista de tipos de usuario
        mostrarListaTiposUsuario();
    }

    private void mostrarListaTiposUsuario() {
        // Obtén una referencia al ListView en tu layout
        listViewTiposUsuario = findViewById(R.id.listView_ListaTiposUsuario);  // Ajusta el ID del ListView

        // Obtén la lista de tipos de usuario desde la base de datos
        DBHelper dbHelper = new DBHelper(this);
        listaTiposUsuario = dbHelper.obtenerListaTiposUsuario();

        // Crea un adaptador personalizado para tus tipos de usuario (utiliza tu TipoUsuarioAdapter)
        tipoUsuarioAdapter = new TipoUsuarioAdapter(this, listaTiposUsuario);

        // Asigna el adaptador al ListView
        listViewTiposUsuario.setAdapter(tipoUsuarioAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewTiposUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en un tipo de usuario
                mostrarToast("Clic en " + listaTiposUsuario.get(position).getNombreTipoUsuario());
            }
        });
    }

    // Método para mostrar un mensaje Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    // Método para ir atrás, Administrar Contenido
    public void lanzarVistaAdministrarContenido(View view) {
        Intent intent = new Intent(this, AdministrarContenidoActivity.class);
        startActivity(intent);
    }

    // Agregar Tipo Usuario
    public void irAgregarTipoUsuario(View view) {
        Intent intent = new Intent(this, FormularioTiposUsuarioActivity.class);
        startActivity(intent);
    }
}
