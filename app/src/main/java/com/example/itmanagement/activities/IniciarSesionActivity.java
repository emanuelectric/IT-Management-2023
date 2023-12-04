package com.example.itmanagement.activities;

// Importar la clase MD5Util
import com.example.itmanagement.util.MD5Util;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class IniciarSesionActivity extends AppCompatActivity{

    private EditText campoEmail;
    private EditText campoPassword;
    private static final String TAG = "Inicio";
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_sesion_layout);
        dbHelper = new DBHelper(this);


        campoEmail = (EditText) findViewById(R.id.edittext_iniciarSesion_correoElectronico);
        campoPassword = (EditText) findViewById(R.id.edittext_inciarSesion_contrasenha);
    }

    public void iniciarSesion(View view) {
        String email = campoEmail.getText().toString();
        String password = campoPassword.getText().toString();

        if (email.equals("") || password.equals("")) {
            Log.d(TAG, "Campos obligatorios");
            Toast.makeText(this, "Por favor, llene todos los campos.", Toast.LENGTH_SHORT).show();
        } else {
            // Encriptar la contraseña utilizando MD5
            String hashedPassword = MD5Util.encrypt(password);

            if (dbHelper.comprobarCredenciales(email, hashedPassword)) {
                Log.d(TAG, "Correcto");

                // Obtener el tipo de usuario utilizando la función de DBHelper
                int tipoUsuario = dbHelper.obtenerTipoUsuarioPorCorreo(email);

                // Abrir la actividad correspondiente en función del tipo de usuario
                abrirActividadSegunTipoUsuario(tipoUsuario);

                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "Incorrecto");
                Toast.makeText(this, "Credenciales incorrectas.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void abrirActividadSegunTipoUsuario(int tipoUsuario) {
        Intent intent;

        switch (tipoUsuario) {
            case 1:
                intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
                break;
            case 3:
                intent = new Intent(this, MenuPrincipalAdministradorActivity.class);
                break;
            case 4:
                intent = new Intent(this, MenuPrincipalClienteActivity.class);
                break;
            default:
                // Tipo de usuario desconocido
                intent = new Intent(this, MenuPrincipalClienteActivity.class);
                break;
        }

        startActivity(intent);
    }


    public void setUsuarioLogueado (){
        String email = campoEmail.getText().toString();
        DBHelper.setUsuarioLogueado(dbHelper.getUsuarioID(email));
        long userId = dbHelper.getUsuarioID(email);
        Log.d(TAG, "Usuario: " + userId);

    }

    public void LanzarVistaMenuInicial(View view) {
        Intent intent = new Intent(this, MenuInicialActivity.class);
        startActivity(intent);
    }

    public void LanzarVistaRestablecerContrasenha01(View view) {
        Intent intent = new Intent(this, RestablecerContrasenha01Activity.class);
        startActivity(intent);
    }

}
