package com.example.itmanagement.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.adapters.DivisaAdapter;
import com.example.itmanagement.data.DBHelper;
import com.example.itmanagement.modelo.Divisa;

import java.util.List;

public class ListaDivisasActivity extends AppCompatActivity {

    private ListView listViewDivisas;
    private DivisaAdapter divisaAdapter;
    private List<Divisa> listaDivisas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_divisas_layout);
        // Llama al método para mostrar la lista de divisas
        mostrarListaDivisas();
    }

    private void mostrarListaDivisas() {
        // Obtén una referencia al ListView en tu layout
        listViewDivisas = findViewById(R.id.listView_ListaDivisas);

        // Obtén la lista de divisas desde la base de datos
        DBHelper dbHelper = new DBHelper(this);
        listaDivisas = dbHelper.obtenerListaDivisas();

        // Crea un adaptador personalizado para tus divisas (utiliza tu DivisaAdapter)
        divisaAdapter = new DivisaAdapter(this, listaDivisas);

        // Asigna el adaptador al ListView
        listViewDivisas.setAdapter(divisaAdapter);

        // Agrega un listener para manejar clics en elementos del ListView
        listViewDivisas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Acciones al hacer clic en una divisa
                mostrarToast("Clic en " + listaDivisas.get(position).getNombreDivisa());
            }
        });
    }

    // Método para mostrar un mensaje Toast
    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    // Método para ir a la pantalla principal
    public void lanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }

    // Método para ir a la actividad de edición al tocar el ImageButton
    public void irEditarDivisa(View view) {
        int position = listViewDivisas.getPositionForView(view);
        Divisa divisaSeleccionada = listaDivisas.get(position);

        // Código para ir a la actividad de edición
        Intent intent = new Intent(this, EditarDivisaActivity.class);
        // Agrega cualquier dato adicional que necesites pasar a la actividad de edición
        intent.putExtra("divisaSeleccionada", (CharSequence) divisaSeleccionada);
        startActivity(intent);
    }

    // Método para eliminar una divisa
    public void eliminarDivisa(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar Eliminación");
        builder.setMessage("¿Estás seguro de que deseas eliminar esta divisa?");

        // Agregar botón de confirmación
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Obtener la divisa seleccionada
                int position = listViewDivisas.getPositionForView(view);
                Divisa divisaSeleccionada = listaDivisas.get(position);

                // Llama al método en DBHelper para eliminar la divisa
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.eliminarDivisa(divisaSeleccionada.getIdDivisa());

                // Actualiza la lista después de la eliminación
                listaDivisas.remove(divisaSeleccionada);
                divisaAdapter.notifyDataSetChanged();

                // Imprime la lista para verificar
                for (Divisa divisa : listaDivisas) {
                    Log.d("ListaDivisas", "ID Divisa: " + divisa.getIdDivisa());
                }

                mostrarToast("Divisa eliminada correctamente");
            }
        });

        // Agregar botón de cancelación
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // No hacer nada, simplemente cerrar el cuadro de diálogo
            }
        });

        // Mostrar el cuadro de diálogo
        builder.show();
    }



    public void LanzarVistaMenuPrincipal(View view) {
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        startActivity(intent);
    }

    // Agregar Divisa
    public void irAgregarDivisa(View view) {
        Intent intent = new Intent(this, FormularioDivisasActivity.class);
        startActivity(intent);
    }

}
