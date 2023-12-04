package com.example.itmanagement.activities;

// Importar la clase MD5Util
import com.example.itmanagement.util.MD5Util;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.itmanagement.R;
import com.example.itmanagement.data.DBHelper;

public class RegistrarseActivity extends AppCompatActivity{
    private static final String TAG = "";
    private EditText campoMail;
    private EditText campoNombre;
    private EditText campoTelefono;
    private EditText campoPassword;
    private EditText campoPasswordConfirm;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse_layout);
        dbHelper = new DBHelper(this);

        campoMail = (EditText) findViewById(R.id.edittext_registrarse_correoElectronico);
        campoNombre = (EditText) findViewById(R.id.edittext_registrarse_nombreApellido);
        campoTelefono = (EditText) findViewById(R.id.edittext_registrarse_telfono);
        campoPassword = (EditText) findViewById(R.id.edittext_registrarse_contrasenha);
        campoPasswordConfirm = (EditText) findViewById(R.id.edittext_registrarse_confirmarContrasenha);
    }

    // ATRAS, ir pantalla inicio
    public void irAPantallaDos(View view) {
        Intent intent = new Intent(this, MenuInicialActivity.class);
        startActivity(intent);
    }

    // REGISTRAR CUENTA
    public void registrarCuenta(View view) {
        // Obtener los valores de los campos
        String mail = campoMail.getText().toString();
        String nombre = campoNombre.getText().toString();
        String telefono = campoTelefono.getText().toString();
        String password = campoPassword.getText().toString();
        String passwordConfirm = campoPasswordConfirm.getText().toString();

        // Verificar que los campos no estén vacíos
        if (mail.equals("") || nombre.equals("") || telefono.equals("") || password.equals("") || passwordConfirm.equals("")) {
            Toast.makeText(this, "Todos los campos deben estar completos", Toast.LENGTH_SHORT).show();
        } else {
            // Validar el formato del correo electrónico
            if (isValidEmail(mail)) {
                // Validar el formato del nombre de usuario
                if (isValidUsername(nombre)) {
                    // Validar el formato del número de teléfono
                    if (isValidPhoneNumber(telefono)) {
                        // Encriptar la contraseña utilizando MD5
                        String hashedPassword = MD5Util.encrypt(password);

                        // Verificar que las contraseñas coincidan
                        if (hashedPassword.equals(MD5Util.encrypt(passwordConfirm))) {
                            // Verificar si el correo ya está registrado
                            if (!dbHelper.comprobarCorreo(mail)) {
                                // El correo no está registrado, crear la cuenta
                                dbHelper.crearCuenta(nombre, mail, telefono, hashedPassword);
                                Log.d(TAG, "Cuenta Creada");
                                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();

                                // Aquí inicia la actividad IniciarSesionActivity
                                Intent iniciarSesion = new Intent(this, IniciarSesionActivity.class);
                                startActivity(iniciarSesion);

                                // Finalizar la actividad actua
                                finish();
                            } else {
                                // El correo ya está registrado, mostrar Toast
                                Toast.makeText(this, "Este correo ya está registrado", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Contraseñas no coinciden, mostrar Toast
                            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Número de teléfono no válido, mostrar Toast
                        Toast.makeText(this, "Número de teléfono no válido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Nombre de usuario no válido, mostrar Toast
                    Toast.makeText(this, "Nombre de usuario no válido", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Correo electrónico no válido, mostrar Toast
                Toast.makeText(this, "Correo electrónico no válido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Función para validar el formato del correo electrónico
    private boolean isValidEmail(CharSequence target) {
        return (Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    // Función para validar el formato del nombre de usuario
    private boolean isValidUsername(String username) {
        // Verificar que solo contenga letras (con tildes permitidas)
        return username.matches("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+");
    }

    // Función para validar el formato del número de teléfono
    private boolean isValidPhoneNumber(String phoneNumber) {
        // Verificar que tenga 10 dígitos y empiece con 0
        return phoneNumber.matches("0[0-9]{9}");
    }

    // ATRAS, menu inicial
    public void LanzarVistaMenuInicial(View view) {
        Intent intent = new Intent(this, MenuInicialActivity.class);
        startActivity(intent);
    }


    // OBTENER NOMBRE DE USUARIO

    // Método llamado después de que el usuario ha completado el registro
    private void registroExitoso() {
        // Obtener el nombre de usuario
        String nombreUsuario = campoNombre.getText().toString();

        // Iniciar la actividad MenuPrincipalActivity y pasar el nombre de usuario como extra
        Intent intent = new Intent(this, MenuPrincipalSuperadministradorActivity.class);
        intent.putExtra("NOMBRE_USUARIO", nombreUsuario);
        startActivity(intent);

        // Finalizar la actividad actual
        finish();
    }
}
