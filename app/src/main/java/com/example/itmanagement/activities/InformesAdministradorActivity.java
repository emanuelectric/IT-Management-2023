package com.example.itmanagement.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.itmanagement.data.DBHelper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class InformesAdministradorActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informes_administrador_layout);

        dbHelper = new DBHelper(this);
    }



    // RETROCEDER
    public void menuAbrirActividadSegunTipoUsuario(View view) {
        // Obtener el userId de alguna manera (puedes adaptar esto según tu lógica)
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

        // Si tienes información adicional del usuario, puedes pasarla a la actividad
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
