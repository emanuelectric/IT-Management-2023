package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.UsuarioAdapter;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Usuario;

import java.util.List;

public class ListaUsuariosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_usuarios_layout);
        // Llama al método para mostrar la lista de usuarios
        mostrarListaUsuarios();
    }

    private void mostrarListaUsuarios() {
        // Obtén una referencia al ListView en tu layout
        ListView listViewUsuarios = findViewById(R.id.listView_ListaUsuarios);

        // Obtén la lista de usuarios desde la base de datos
        DBHelper dbHelper = new DBHelper(this);
        List<Usuario> listaUsuarios = dbHelper.obtenerListaUsuarios();

        // Crea un adaptador personalizado para tus usuarios (puedes utilizar tu UsuarioAdapter)
        UsuarioAdapter usuarioAdapter = new UsuarioAdapter(this, listaUsuarios);

        // Asigna el adaptador al ListView
        listViewUsuarios.setAdapter(usuarioAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en un usuario, si es necesario
                Usuario usuarioSeleccionado = listaUsuarios.get(position);
                Toast.makeText(ListaUsuariosActivity.this, "Clic en " + usuarioSeleccionado.getNombreUsuario(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para ir a la pantalla principal
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }
}
