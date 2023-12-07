package com.example.itmanagement.activities;

import android.annotation.SuppressLint;
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

        // Inicializar vistas
        textViewBienvenida = findViewById(R.id.textViewBienvenida);

        // Obtener nombre de usuario (reemplaza "UsuarioEjemplo" con la lógica para obtener el nombre)
         String nombreUsuario = obtenerNombreUsuario();
        // Mostrar mensaje de bienvenida
         textViewBienvenida.setText("¡ Bienvenido " + nombreUsuario + "!");
    }

    // Lógica para cerrar sesión con confirmación
    public void CerrarSesion(View view) {
        // Mostrar un diálogo de confirmación
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Estás seguro de que quieres cerrar sesión?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Implementar aquí la lógica para cerrar sesión
                DBHelper.setUsuarioLogueado(-1); // Indicar que no hay usuario autenticado

                // Por ahora, simplemente regresamos a la pantalla de inicio de sesión
                Intent intent = new Intent(MenuPrincipalAdministradorActivity.this, MenuInicialActivity.class);
                startActivity(intent);
                finish(); // Asegura que no puedas volver atrás con el botón de retorno
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // No hacemos nada si el usuario elige no cerrar sesión
            }
        });
        builder.show();
    }

    // Método para obtener el nombre de usuario
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






    // Menu Desplegable

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable_administrador_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar los clics en los elementos del menú desplegable
        int itemId = item.getItemId();
        if (itemId == R.id.ver_usuarios) {
            // Abre el layout correspondiente para "Usuarios-Roles"
            abrirLayoutUsuarios();
            return true;
        } else if (itemId == R.id.agregar_divisa) {
            // Abre el layout correspondiente para "Divisas"
            abrirLayoutDivisas();
            return true;
        } else if (itemId == R.id.agregar_pedido) {
            // Abre el layout correspondiente para "Estados"
            abrirLayoutPedidos();
            return true;
        } else if (itemId == R.id.agregar_resenha) {
            // Abre el layout correspondiente para "Calificaciones"
            abrirLayoutCalificaciones();
            return true;
        } else if (itemId == R.id.cambiar_contrasenha) {
            // Abre el layout correspondiente para "Cambiar contraseña"
            abrirLayoutCambiarContraseña();
            return true;
        } else if (itemId == R.id.agregar_rol) {
            // Abre el layout correspondiente para "Roles"
            abrirLayoutRoles();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    // Métodos para abrir los layouts correspondientes
    private void abrirLayoutUsuarios() {
        // Lógica para abrir el layout de Roles
        Intent intent = new Intent(this, ListaUsuariosActivity.class);
        startActivity(intent);
    }

    private void abrirLayoutDivisas() {
        // Lógica para abrir el layout de Divisas
        Intent intent = new Intent(this, FormularioDivisasActivity.class);
        startActivity(intent);
    }

    private void abrirLayoutPedidos() {
        // Lógica para abrir el layout de Estados
        Intent intent = new Intent(this, FormularioPedidosActivity.class);
        startActivity(intent);
    }

    private void abrirLayoutCalificaciones() {
        // Lógica para abrir el layout de Calificaciones
        Intent intent = new Intent(this, FormularioResenhasActivity.class);
        startActivity(intent);
    }

    private void abrirLayoutCambiarContraseña() {
        // Lógica para abrir el layout de Cambiar Contraseña
        Intent intent = new Intent(this, FormularioCambiarContrasenha01Activity.class);
        startActivity(intent);
    }

    private void abrirLayoutRoles() {
        // Lógica para abrir el layout de Roles
        Intent intent = new Intent(this, FormularioRolesActivity.class);
        startActivity(intent);
    }

}
