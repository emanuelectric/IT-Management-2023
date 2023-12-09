package com.example.itmanagement.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;

import java.util.HashMap;
import java.util.Map;

public class AdministrarContenidoActivity extends AppCompatActivity implements View.OnClickListener {

    private final Map<Integer, Class<?>> buttonActivityMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrar_contenido_layout);

        // Encuentra los botones por su ID
        Button btnCategoriasProducto = findViewById(R.id.boton_categoriasProducto);
        Button btnInventario = findViewById(R.id.boton_inventario);
        Button btnProductos = findViewById(R.id.boton_productos);
        Button btnTiposPedido = findViewById(R.id.boton_tiposPedido);
        Button btnEstadosPedido = findViewById(R.id.boton_estadosPedido);
        Button btnPedidos = findViewById(R.id.boton_pedidos);
        Button btnDivisas = findViewById(R.id.boton_divisas);
        Button btnResenhas = findViewById(R.id.boton_resenhas);

        // Configura el listener para cada botón
        btnCategoriasProducto.setOnClickListener(this);
        btnInventario.setOnClickListener(this);
        btnProductos.setOnClickListener(this);
        btnTiposPedido.setOnClickListener(this);
        btnEstadosPedido.setOnClickListener(this);
        btnPedidos.setOnClickListener(this);
        btnDivisas.setOnClickListener(this);
        btnResenhas.setOnClickListener(this);

        // Mapea los botones a las actividades correspondientes
        buttonActivityMap.put(R.id.boton_categoriasProducto, ListaCategoriasProductoActivity.class);
        buttonActivityMap.put(R.id.boton_inventario, ListaInventarioActivity.class);
        buttonActivityMap.put(R.id.boton_productos, ListaProductosActivity.class);
        buttonActivityMap.put(R.id.boton_tiposPedido, ListaTiposPedidoActivity.class);
        buttonActivityMap.put(R.id.boton_estadosPedido, ListaEstadosPedidoActivity.class);
        buttonActivityMap.put(R.id.boton_pedidos, ListaPedidosActivity.class);
        buttonActivityMap.put(R.id.boton_divisas, ListaDivisasActivity.class);
        buttonActivityMap.put(R.id.boton_resenhas, ListaResenhasActivity.class);
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


    // Método para ir a la pantalla principal
    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }

}
