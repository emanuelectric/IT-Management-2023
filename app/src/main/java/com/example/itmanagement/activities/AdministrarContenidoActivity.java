package com.example.itmanagement.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

import java.util.HashMap;
import java.util.Map;

public class AdministrarContenidoActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper dbHelper;

    private final Map<Integer, Class<?>> buttonActivityMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_contenido_layout);

        // Encuentra los botones por su ID
        Button btnCategoriasProducto = findViewById(R.id.boton_categoriasProducto);
        Button btnProductos = findViewById(R.id.boton_productos);
        Button btnTiposPedido = findViewById(R.id.boton_tiposPedido);
        Button btnEstadosPedido = findViewById(R.id.boton_estadosPedido);
        Button btnPedidos = findViewById(R.id.boton_pedidos);
        Button btnDivisas = findViewById(R.id.boton_divisas);
        Button btnResenhas = findViewById(R.id.boton_resenhas);

        // Configura el listener para cada botón
        btnCategoriasProducto.setOnClickListener(this);
        btnProductos.setOnClickListener(this);
        btnTiposPedido.setOnClickListener(this);
        btnEstadosPedido.setOnClickListener(this);
        btnPedidos.setOnClickListener(this);
        btnDivisas.setOnClickListener(this);
        btnResenhas.setOnClickListener(this);

        // Mapea los botones a las actividades correspondientes
        buttonActivityMap.put(R.id.boton_categoriasProducto, ListaCategoriasProductoActivity.class);
        buttonActivityMap.put(R.id.boton_productos, ListaProductosActivity.class);
        buttonActivityMap.put(R.id.boton_tiposPedido, ListaTiposPedidoActivity.class);
        buttonActivityMap.put(R.id.boton_estadosPedido, ListaEstadosPedidoActivity.class);
        buttonActivityMap.put(R.id.boton_pedidos, ListaPedidosActivity.class);
        buttonActivityMap.put(R.id.boton_divisas, ListaDivisasActivity.class);
        buttonActivityMap.put(R.id.boton_resenhas, ListaResenhasActivity.class);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View view) {
        // Obtiene la clase de la actividad correspondiente y la abre
        Class<?> activityClass = buttonActivityMap.get(view.getId());
        if (activityClass != null) {
            Intent intent = new Intent(this, activityClass);
            startActivity(intent);
        }
    }



    // RETROCEDER
    public void menuAbrirActividadSegunTipoUsuario(View view) {
        // Obtener el userId de alguna manera
        long userId = dbHelper.getUsuarioLogueado();

        // Obtener el tipo de usuario y la información del usuario usando los métodos existentes
        int tipoUsuario = dbHelper.getTipoUsuario(userId);
        Cursor usuarioCursor = dbHelper.getUsuarioData(userId);

        Intent intent;

        switch (tipoUsuario) {
            case 1:
                intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
                break;
            case 2:
                intent = new Intent(this, MenuPrincipalAdministradorActivity.class);
                break;
            case 3:
                intent = new Intent(this, MenuPrincipalClienteActivity.class);
                break;
            default:
                // Tipo de usuario desconocido, por defecto lleva al menú del superadministrador
                intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
                break;
        }


        if (usuarioCursor != null && usuarioCursor.moveToFirst()) {
            String nombreUsuario = usuarioCursor.getString(usuarioCursor.getColumnIndex(DBHelper.COLUMN_NOMBRE_USUARIO));
            String correoElectronico = usuarioCursor.getString(usuarioCursor.getColumnIndex(DBHelper.COLUMN_CORREO_ELECTRONICO));
            String telefonoUsuario = usuarioCursor.getString(usuarioCursor.getColumnIndex(DBHelper.COLUMN_TELEFONO_USUARIO));

            // Pasar estos datos a la actividad correspondiente si es necesario
            intent.putExtra("nombreUsuario", nombreUsuario);
            intent.putExtra("correoElectronico", correoElectronico);
            intent.putExtra("telefonoUsuario", telefonoUsuario);
        }

        startActivity(intent);
        finish(); // Finaliza la actividad actual
    }



}
