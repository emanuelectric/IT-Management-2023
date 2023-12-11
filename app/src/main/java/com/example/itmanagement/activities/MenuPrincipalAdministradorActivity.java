package com.example.itmanagement.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class MenuPrincipalAdministradorActivity extends AppCompatActivity {

    private TextView textViewBienvenida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal_administrador_layout);

        // Configura y muestra la bienvenida
        setupWelcomeMessage();
    }

    private void setupWelcomeMessage() {
        // Inicializar vistas
        textViewBienvenida = findViewById(R.id.textViewBienvenida);

        // Obtener nombre de usuario (reemplaza "UsuarioEjemplo" con tu lógica para obtener el nombre)
        String nombreUsuario = obtenerNombreUsuario();
        // Mostrar mensaje de bienvenida
        textViewBienvenida.setText("¡Bienvenido " + nombreUsuario + "!");
    }

    private String obtenerNombreUsuario() {
        long usuarioID = DBHelper.getUsuarioLogueado();

        if (usuarioID != -1) {
            DBHelper dbHelper = new DBHelper(this);

            Cursor cursor = dbHelper.getUsuarioData(usuarioID);

            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String nombreUsuario = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE_USUARIO));
                        Log.d("ObtenerNombreUsuario", "Nombre de usuario obtenido correctamente: " + nombreUsuario);
                        return nombreUsuario;
                    } else {
                        Log.e("ObtenerNombreUsuario", "No se pudo mover al primer elemento del cursor");
                    }
                } finally {
                    cursor.close();
                }
            } else {
                Log.e("ObtenerNombreUsuario", "Cursor es nulo");
            }
        } else {
            Log.e("ObtenerNombreUsuario", "No hay usuario autenticado");
        }

        return " ";
    }

    public void cerrarSesion(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Estás seguro de que quieres cerrar sesión?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBHelper.setUsuarioLogueado(-1);
                Intent intent = new Intent(MenuPrincipalAdministradorActivity.this, MenuInicialActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable_administrador_layout, menu);
        return true;
    }

    // menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item_logout) {
            cerrarSesion(findViewById(android.R.id.content));
            return true;
        }

        // Agregado: Si se llega aquí, significa que no es el ítem de cierre de sesión, así que
        // verifica otros elementos del menú.
        if (itemId == R.id.nav_administrar_contenido) {
            abrirLayoutContenido();
        } else if (itemId == R.id.nav_informes) {
            abrirLayoutInformes();
        } else if (itemId == R.id.nav_perfil) {
            abrirLayoutPerfil();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

    // Métodos para abrir los layouts correspondientes

    private void abrirLayoutContenido() {
        Intent intent = new Intent(this, AdministrarContenidoActivity.class);
        startActivity(intent);
    }

    private void abrirLayoutInformes() {
        Intent intent = new Intent(this, InformesAdministradorActivity.class);
        startActivity(intent);
    }

    private void abrirLayoutPerfil() {
        Intent intent = new Intent(this, PerfilActivity.class);
        startActivity(intent);
    }
}
