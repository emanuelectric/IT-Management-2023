package com.example.itmanagement.data;

// Importaciones
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.itmanagement.modelo.Divisa;
import com.example.itmanagement.modelo.TipoUsuario;
import com.example.itmanagement.modelo.Usuario;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.text.format.DateFormat;

public class DBHelper extends SQLiteOpenHelper {

    // Inicializaciones
        public final Context context;
        public static final String TAG = "MiDBHelper";
        public static final String DB_NAME = "itmanagementdb.sqlite";
        public static final int DB_VERSION = 2;

        private static long usuarioLogueado;

    // Definiciones de secuencias
        public static final String SEQ_FORMULARIO_PERMISO = "formulario_permiso_id_formulario_permiso_seq";
        public static final String SEQ_DIVISA = "public.divisa_id_divisa_seq_1";
        public static final String SEQ_TIPO_PEDIDO = "public.tipo_pedido_id_tipo_pedido_seq";
        public static final String SEQ_ESTADO_PEDIDO = "public.estado_pedido_id_estado_pedido_seq";
        public static final String SEQ_TIPO_USUARIO = "public.tipo_usuario_id_tipo_usuario_seq";
        public static final String SEQ_CATEGORIA_PRODUCTO = "public.categoria_producto_id_categoria_producto_seq";
        public static final String SEQ_PRODUCTO = "public.producto_id_producto_seq";
        public static final String SEQ_INVENTARIO = "public.inventario_id_inventario_seq";
        public static final String SEQ_USUARIO = "public.usuario_id_usuario_seq";
        public static final String SEQ_RESENHA = "public.resenha_id_resenha_seq";
        public static final String SEQ_PEDIDO = "public.pedido_id_pedido_seq";
        public static final String SEQ_DETALLE_PEDIDO = "public.detalle_pedido_id_detalle_pedido_seq";

    // Definiciones de tablas y columnas

        // Tabla Formulario Permiso
            public static final String TABLE_FORMULARIO_PERMISO = "Formulario_Permiso";
            public static final String COLUMN_ID_FORMULARIO_PERMISO = "id_formulario_permiso";
            public static final String COLUMN_OBJETO_PERMISO = "objeto_permiso";
            public static final String COLUMN_DESCRIPCION_OBJETO_PERMISO = "descripcion_objeto_permiso";
            public static final String COLUMN_PERMISO_EDITAR = "permiso_editar";
            public static final String COLUMN_PERMISO_CONSULTAR = "permiso_consultar";
            public static final String COLUMN_PERMISO_AGREGAR = "permiso_agregar";
            public static final String COLUMN_PERMISO_ELIMINAR = "permiso_eliminar";
            public static final String COLUMN_AUDIT_FECHA_INSERT_FORMULARIO_PERMISO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_FORMULARIO_PERMISO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_FORMULARIO_PERMISO = "audit_fecha_modif_";

        // Tabla Divisa
            public static final String TABLE_DIVISA = "Divisa";
            public static final String COLUMN_ID_DIVISA = "id_divisa";
            public static final String COLUMN_NOMBRE_DIVISA = "nombre_divisa";
            public static final String COLUMN_SIMBOLO_DIVISA = "simbolo_divisa";
            public static final String COLUMN_AUDIT_FECHA_INSERT_DIVISA = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_DIVISA = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_DIVISA = "audit_fecha_modif_";

        // Tabla Tipo Pedido
            public static final String TABLE_TIPO_PEDIDO = "Tipo_Pedido";
            public static final String COLUMN_ID_TIPO_PEDIDO = "id_tipo_pedido";
            public static final String COLUMN_NOMBRE_TIPO_PEDIDO = "nombre_tipo_pedido";
            public static final String COLUMN_DESCRIPCION_TIPO_PEDIDO = "descripcion_tipo_pedido";
            public static final String COLUMN_AUDIT_FECHA_INSERT_TIPO_PEDIDO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_TIPO_PEDIDO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_TIPO_PEDIDO = "audit_fecha_modif_";

        // Tabla Estado Pedido
            public static final String TABLE_ESTADO_PEDIDO = "Estado_Pedido";
            public static final String COLUMN_ID_ESTADO_PEDIDO = "id_estado_pedido";
            public static final String COLUMN_NOMBRE_ESTADO_PEDIDO = "nombre_estado_pedido";
            public static final String COLUMN_DESCRIPCION_ESTADO_PEDIDO = "descripcion_estado_pedido";
            public static final String COLUMN_AUDIT_FECHA_INSERT_ESTADO_PEDIDO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_ESTADO_PEDIDO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO = "audit_fecha_modif_";

        // Tabla Tipo Usuario
            public static final String TABLE_TIPO_USUARIO = "Tipo_Usuario";
            public static final String COLUMN_ID_TIPO_USUARIO = "id_tipo_usuario";
            public static final String COLUMN_NOMBRE_TIPO_USUARIO = "nombre_tipo_usuario";
            public static final String COLUMN_DESCRIPCION_TIPO_USUARIO = "descripcion_tipo_usuario";
            public static final String COLUMN_ID_FORMULARIO_PERMISO_TIPO_USUARIO = "id_formulario_permiso";
            public static final String COLUMN_AUDIT_FECHA_INSERT_TIPO_USUARIO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_TIPO_USUARIO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_TIPO_USUARIO = "audit_fecha_modif_";

        // Tabla Categoria Producto
            public static final String TABLE_CATEGORIA_PRODUCTO = "Categoria_Producto";
            public static final String COLUMN_ID_CATEGORIA_PRODUCTO = "id_categoria_producto";
            public static final String COLUMN_NOMBRE_CATEGORIA_PRODUCTO = "nombre_categoria_producto";
            public static final String COLUMN_DESCRIPCION_CATEGORIA_PRODUCTO = "descripcion_categoria_producto";
            public static final String COLUMN_AUDIT_FECHA_INSERT_CATEGORIA_PRODUCTO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_CATEGORIA_PRODUCTO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_CATEGORIA_PRODUCTO = "audit_fecha_modif_";

        // Tabla Producto
            public static final String TABLE_PRODUCTO = "Producto";
            public static final String COLUMN_ID_PRODUCTO = "id_producto";
            public static final String COLUMN_NOMBRE_PRODUCTO = "nombre_producto";
            public static final String COLUMN_IMAGEN_PRODUCTO = "imagen_producto";
            public static final String COLUMN_DESCRIPCION_PRODUCTO = "descripcion_producto";
            public static final String COLUMN_PRECIO_PRODUCTO = "precio_producto";
            public static final String COLUMN_ID_CATEGORIA_PRODUCTO_PRODUCTO = "id_categoria_producto";
            public static final String COLUMN_ID_DIVISA_PRODUCTO = "id_divisa";
            public static final String COLUMN_AUDIT_FECHA_INSERT_PRODUCTO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_PRODUCTO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_PRODUCTO = "audit_fecha_modif_";

        // Tabla Inventario
            public static final String TABLE_INVENTARIO = "Inventario";
            public static final String COLUMN_ID_INVENTARIO = "id_inventario";
            public static final String COLUMN_ID_PRODUCTO_INVENTARIO = "id_producto";
            public static final String COLUMN_CANTIDAD_STOCK_INVENTARIO = "cantidad_stock";
            public static final String COLUMN_AUDIT_FECHA_INSERT_INVENTARIO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_INVENTARIO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_INVENTARIO = "audit_fecha_modif_";

        // Tabla Usuario
            static final String TABLE_USUARIO = "Usuario";
            public static final String COLUMN_ID_USUARIO = "id_usuario";
            public static final String COLUMN_NOMBRE_USUARIO = "nombre_usuario";
            public static final String COLUMN_TELEFONO_USUARIO = "telefono_usuario";
            public static final String COLUMN_ID_TIPO_USUARIO_USUARIO = "id_tipo_usuario";
            public static final String COLUMN_CORREO_ELECTRONICO = "correo_electronico";
            public static final String COLUMN_CONTRASENHA_USUARIO = "contrasenha_usuario";
            public static final String COLUMN_AUDIT_FECHA_INSERT_USUARIO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_USUARIO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_USUARIO = "audit_fecha_modif_";

        // Tabla Resenha
            public static final String TABLE_RESENHA = "Resenha";
            public static final String COLUMN_ID_RESENHA = "id_resenha";
            public static final String COLUMN_CALIFICACION_RESENHA = "calificacion";
            public static final String COLUMN_ID_USUARIO_RESENHA = "id_usuario";
            public static final String COLUMN_COMENTARIO_RESENHA = "comentario";
            public static final String COLUMN_FECHA_HORA_RESENHA = "fech_hora";
            public static final String COLUMN_AUDIT_FECHA_INSERT_RESENHA = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_RESENHA = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_RESENHA = "audit_fecha_modif_";

        // Tabla Pedido
            public static final String TABLE_PEDIDO = "Pedido";
            public static final String COLUMN_ID_PEDIDO = "id_pedido";
            public static final String COLUMN_ID_USUARIO_PEDIDO = "id_usuario";
            public static final String COLUMN_FECHA_HORA_PEDIDO = "fecha_hora_pedido";
            public static final String COLUMN_ID_ESTADO_PEDIDO_PEDIDO = "id_estado_pedido";
            public static final String COLUMN_ID_TIPO_PEDIDO_PEDIDO = "id_tipo_pedido";
            public static final String COLUMN_UBICACION_LATITUD_PEDIDO = "ubicacion_latitud";
            public static final String COLUMN_UBICACION_LONGITUD_PEDIDO = "ubicacion_longitud";
            public static final String COLUMN_AUDIT_FECHA_INSERT_PEDIDO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_PEDIDO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_PEDIDO = "audit_fecha_modif_";

        // Tabla Detalle Pedido
            public static final String TABLE_DETALLE_PEDIDO = "Detalle_Pedido";
            public static final String COLUMN_ID_DETALLE_PEDIDO = "id_detalle_pedido";
            public static final String COLUMN_AUDIT_FECHA_MODIF_DETALLE_PEDIDO = "audit_fecha_modif_";
            public static final String COLUMN_ID_PEDIDO_DETALLE_PEDIDO = "id_pedido";
            public static final String COLUMN_ID_PRODUCTO_DETALLE_PEDIDO = "id_producto";
            public static final String COLUMN_CANTIDAD_DETALLE_PEDIDO = "cantidad";
            public static final String COLUMN_COSTO_DETALLE_PEDIDO = "costo_pedido";
            public static final String COLUMN_AUDIT_FECHA_INSERT_DETALLE_PEDIDO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_DETALLE_PEDIDO = "audit_usuario_modif_";


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context context1) {
        super(context, name, factory, version);
        this.context = context1;
    }

    private static DBHelper dbhelper;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }


    // CREACION DE TABLAS
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Entra tabla");

        // Tabla Usuario
        /* db.execSQL("CREATE TABLE " + TABLE_USUARIO + " (" +
                COLUMN_ID_USUARIO + " INTEGER PRIMARY KEY, " +
                COLUMN_NOMBRE_USUARIO + " VARCHAR NOT NULL, " +
                COLUMN_TELEFONO_USUARIO + " INTEGER NOT NULL, " +
                COLUMN_ID_TIPO_USUARIO + " INTEGER NOT NULL, " +
                COLUMN_CORREO_ELECTRONICO + " VARCHAR NOT NULL, " +
                COLUMN_CONTRASENHA_USUARIO + " VARCHAR NOT NULL, " +
                COLUMN_AUDIT_FECHA_INSERT_USUARIO + " VARCHAR, " +
                COLUMN_AUDIT_USUARIO_MODIF_USUARIO + " VARCHAR, " +
                COLUMN_AUDIT_FECHA_MODIF_USUARIO + " VARCHAR, " +
                "FOREIGN KEY (" + COLUMN_ID_TIPO_USUARIO + ") REFERENCES " + TABLE_TIPO_USUARIO + "(" + COLUMN_ID_TIPO_USUARIO + ")" +
                ")"); */


            db.execSQL("CREATE TABLE Usuario (" +
                    "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_usuario VARCHAR NOT NULL," +
                    "telefono_usuario INTEGER NOT NULL," +
                    "id_tipo_usuario INTEGER," +
                    "correo_electronico VARCHAR NOT NULL," +
                    "contrasenha_usuario VARCHAR NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR," +
                    "FOREIGN KEY (id_tipo_usuario) REFERENCES Tipo_Usuario (id_tipo_usuario)" +
                    ")"
            );

        // Tabla Pedido
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PEDIDO + " (" +
                    COLUMN_ID_PEDIDO + " INTEGER PRIMARY KEY, " +
                    COLUMN_ID_USUARIO + " INTEGER NOT NULL, " +
                    COLUMN_FECHA_HORA_PEDIDO + " TIMESTAMP NOT NULL, " +
                    COLUMN_ID_ESTADO_PEDIDO + " VARCHAR NOT NULL, " +
                    COLUMN_ID_TIPO_PEDIDO + " INTEGER NOT NULL, " +
                    COLUMN_UBICACION_LATITUD_PEDIDO + " REAL NOT NULL, " +
                    COLUMN_UBICACION_LONGITUD_PEDIDO + " REAL NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_PEDIDO + " VARCHAR, " +
                    "FOREIGN KEY (" + COLUMN_ID_USUARIO + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_ID_USUARIO + "), " +
                    "FOREIGN KEY (" + COLUMN_ID_ESTADO_PEDIDO + ") REFERENCES " + TABLE_ESTADO_PEDIDO + "(" + COLUMN_ID_ESTADO_PEDIDO + "), " +
                    "FOREIGN KEY (" + COLUMN_ID_TIPO_PEDIDO + ") REFERENCES " + TABLE_TIPO_PEDIDO + "(" + COLUMN_ID_TIPO_PEDIDO + ")" +
                    ")"); */

            db.execSQL("CREATE TABLE Pedido (" +
                    "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_usuario INTEGER NOT NULL," +
                    "fecha_hora_pedido TIMESTAMP NOT NULL," +
                    "id_estado_pedido VARCHAR NOT NULL," +
                    "id_tipo_pedido INTEGER NOT NULL," +
                    "ubicacion_latitud REAL NOT NULL," +
                    "ubicacion_longitud REAL NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR," +
                    "FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)," +
                    "FOREIGN KEY (id_estado_pedido) REFERENCES Estado_Pedido (id_estado_pedido)," +
                    "FOREIGN KEY (id_tipo_pedido) REFERENCES Tipo_Pedido (id_tipo_pedido)" +
                    ")"
            );

        // Tabla Producto
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTO + " (" +
                    COLUMN_ID_PRODUCTO + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE_PRODUCTO + " VARCHAR NOT NULL, " +
                    COLUMN_DESCRIPCION_PRODUCTO + " VARCHAR NOT NULL, " +
                    COLUMN_PRECIO_PRODUCTO + " NUMERIC(2) NOT NULL, " +
                    COLUMN_ID_CATEGORIA_PRODUCTO + " VARCHAR NOT NULL, " +
                    COLUMN_ID_DIVISA + " INTEGER NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_PRODUCTO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_PRODUCTO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_PRODUCTO + " VARCHAR, " +
                    COLUMN_IMAGEN_PRODUCTO + " VARCHAR, " +
                    "FOREIGN KEY (" + COLUMN_ID_CATEGORIA_PRODUCTO + ") REFERENCES " + TABLE_CATEGORIA_PRODUCTO + "(" + COLUMN_ID_CATEGORIA_PRODUCTO + "), " +
                    "FOREIGN KEY (" + COLUMN_ID_DIVISA + ") REFERENCES " + TABLE_DIVISA + "(" + COLUMN_ID_DIVISA + ")" +
                    ")"); */

            db.execSQL("CREATE TABLE Producto (" +
                    "id_producto INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_producto VARCHAR NOT NULL," +
                    "descripcion_producto VARCHAR NOT NULL," +
                    "precio_producto NUMERIC(2) NOT NULL," +
                    "id_categoria_producto VARCHAR NOT NULL," +
                    "id_divisa INTEGER NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR," +
                    "imagen_producto VARCHAR," +
                    "FOREIGN KEY (id_categoria_producto) REFERENCES Categoria_Producto (id_categoria_producto)," +
                    "FOREIGN KEY (id_divisa) REFERENCES Divisa (id_divisa)" +
                    ")"
            );

        // Tabla Inventario
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_INVENTARIO + " (" +
                    COLUMN_ID_INVENTARIO + " INTEGER PRIMARY KEY, " +
                    COLUMN_ID_PRODUCTO + " INTEGER NOT NULL, " +
                    COLUMN_CANTIDAD_STOCK_INVENTARIO + " INTEGER NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_INVENTARIO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_INVENTARIO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_INVENTARIO + " VARCHAR, " +
                    "FOREIGN KEY (" + COLUMN_ID_PRODUCTO + ") REFERENCES " + TABLE_PRODUCTO + "(" + COLUMN_ID_PRODUCTO + ")" +
                    ")"); */

            db.execSQL("CREATE TABLE Inventario (" +
                    "id_inventario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_producto INTEGER NOT NULL," +
                    "cantidad_stock INTEGER NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR," +
                    "FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)" +
                    ")"
            );

        // Tabla Divisa
        /*    db.execSQL("CREATE TABLE " + TABLE_DIVISA + " (" +
                    COLUMN_ID_DIVISA + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE_DIVISA + " VARCHAR NOT NULL, " +
                    COLUMN_SIMBOLO_DIVISA + " VARCHAR NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_DIVISA + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_DIVISA + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_DIVISA + " VARCHAR" +
                    ")"); */

            db.execSQL("CREATE TABLE Divisa (" +
                    "id_divisa INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_divisa VARCHAR NOT NULL," +
                    "simbolo_divisa VARCHAR NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR" +
                    ")"
            );

        // Tabla Resenha
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_RESENHA + " (" +
                    COLUMN_ID_RESENHA + " INTEGER PRIMARY KEY, " +
                    COLUMN_CALIFICACION_RESENHA + " VARCHAR NOT NULL, " +
                    COLUMN_ID_USUARIO + " INTEGER NOT NULL, " +
                    COLUMN_COMENTARIO_RESENHA + " VARCHAR NOT NULL, " +
                    COLUMN_FECHA_HORA_RESENHA + " TIMESTAMP NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_RESENHA + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_RESENHA + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_RESENHA + " VARCHAR, " +
                    "FOREIGN KEY (" + COLUMN_ID_USUARIO + ") REFERENCES " + TABLE_USUARIO + "(" + COLUMN_ID_USUARIO + ")" +
                    ")"); */

            db.execSQL("CREATE TABLE Resenha (" +
                    "id_resenha INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "calificacion VARCHAR NOT NULL," +
                    "id_usuario INTEGER NOT NULL," +
                    "comentario VARCHAR NOT NULL," +
                    "fech_hora TIMESTAMP NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR," +
                    "FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)" +
                    ")"
            );

        // Tabla Formulario Permiso
        /*    db.execSQL("CREATE TABLE " + TABLE_FORMULARIO_PERMISO + " (" +
                    COLUMN_ID_FORMULARIO_PERMISO + " INTEGER PRIMARY KEY, " +
                    COLUMN_OBJETO_PERMISO + " VARCHAR, " +
                    COLUMN_DESCRIPCION_OBJETO_PERMISO + " VARCHAR NOT NULL, " +
                    COLUMN_PERMISO_EDITAR + " BOOLEAN NOT NULL, " +
                    COLUMN_PERMISO_CONSULTAR + " BOOLEAN NOT NULL, " +
                    COLUMN_PERMISO_AGREGAR + " BOOLEAN NOT NULL, " +
                    COLUMN_PERMISO_ELIMINAR + " BOOLEAN NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_FORMULARIO_PERMISO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_FORMULARIO_PERMISO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_FORMULARIO_PERMISO + " VARCHAR" +
                    ")"); */

            db.execSQL("CREATE TABLE Formulario_Permiso (" +
                    "id_formulario_permiso INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "objeto_permiso VARCHAR," +
                    "descripcion_objeto_permiso VARCHAR NOT NULL," +
                    "permiso_editar BOOLEAN NOT NULL," +
                    "permiso_consultar BOOLEAN NOT NULL," +
                    "permiso_agregar BOOLEAN NOT NULL," +
                    "permiso_eliminar BOOLEAN NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR" +
                    ")"
            );

        // Tabla Tipo_Usuario
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TIPO_USUARIO + " (" +
                    COLUMN_ID_TIPO_USUARIO + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE_TIPO_USUARIO + " VARCHAR NOT NULL, " +
                    COLUMN_DESCRIPCION_TIPO_USUARIO + " VARCHAR NOT NULL, " +
                    COLUMN_ID_FORMULARIO_PERMISO + " INTEGER NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_TIPO_USUARIO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_TIPO_USUARIO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_TIPO_USUARIO + " VARCHAR, " +
                    "FOREIGN KEY (" + COLUMN_ID_FORMULARIO_PERMISO + ") REFERENCES " + TABLE_FORMULARIO_PERMISO + "(" + COLUMN_ID_FORMULARIO_PERMISO + ")" +
                    ")"); */

            db.execSQL("CREATE TABLE Tipo_Usuario (" +
                    "id_tipo_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_tipo_usuario VARCHAR NOT NULL," +
                    "descripcion_tipo_usuario VARCHAR NOT NULL," +
                    "id_formulario_permiso INTEGER NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif VARCHAR," +
                    "FOREIGN KEY (id_formulario_permiso) REFERENCES Formulario_Permiso (id_formulario_permiso)" +
                    ")"
            );

        // Tabla Categoria_Producto
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORIA_PRODUCTO + " (" +
                    COLUMN_ID_CATEGORIA_PRODUCTO + " VARCHAR PRIMARY KEY, " +
                    COLUMN_NOMBRE_CATEGORIA_PRODUCTO + " VARCHAR NOT NULL, " +
                    COLUMN_DESCRIPCION_CATEGORIA_PRODUCTO + " VARCHAR NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_CATEGORIA_PRODUCTO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_CATEGORIA_PRODUCTO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_CATEGORIA_PRODUCTO + " VARCHAR" +
                    ")"); */

            db.execSQL("CREATE TABLE Categoria_Producto (" +
                    "id_categoria_producto INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_categoria_producto VARCHAR NOT NULL," +
                    "descripcion_categoria_producto VARCHAR NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR" +
                    ")"
            );

        // Tabla Estado_Pedido
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_ESTADO_PEDIDO + " (" +
                    COLUMN_ID_ESTADO_PEDIDO + " VARCHAR PRIMARY KEY, " +
                    COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO + " VARCHAR, " +
                    COLUMN_NOMBRE_ESTADO_PEDIDO + " VARCHAR NOT NULL, " +
                    COLUMN_DESCRIPCION_ESTADO_PEDIDO + " VARCHAR NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_ESTADO_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_ESTADO_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO + " VARCHAR NOT NULL" +
                    ")"); */

            db.execSQL("CREATE TABLE Estado_Pedido (" +
                    "id_estado_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "audit_fecha_modif_ VARCHAR," +
                    "nombre_estado_pedido VARCHAR NOT NULL," +
                    "descripcion_estado_pedido VARCHAR NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif VARCHAR NOT NULL" +
                    ")"
            );

        // Tabla Tipo Pedido
        /*    db.execSQL("CREATE TABLE " + TABLE_TIPO_PEDIDO + " (" +
                    COLUMN_ID_TIPO_PEDIDO + " INTEGER PRIMARY KEY, " +
                    COLUMN_NOMBRE_TIPO_PEDIDO + " VARCHAR NOT NULL, " +
                    COLUMN_DESCRIPCION_TIPO_PEDIDO + " VARCHAR NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_TIPO_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_TIPO_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_TIPO_PEDIDO + " VARCHAR" +
                    ")"); */

            db.execSQL("CREATE TABLE Tipo_Pedido (" +
                    "id_tipo_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre_tipo_pedido VARCHAR NOT NULL," +
                    "descripcion_tipo_pedido VARCHAR NOT NULL," +
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif_ VARCHAR" +
                    ")"
            );

        // Tabla Detalle_Pedido
        /*    db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_DETALLE_PEDIDO + " (" +
                    COLUMN_ID_DETALLE_PEDIDO + " INTEGER PRIMARY KEY, " +
                    COLUMN_AUDIT_FECHA_MODIF_DETALLE_PEDIDO + " VARCHAR, " +
                    COLUMN_ID_PEDIDO + " INTEGER NOT NULL, " +
                    COLUMN_ID_PRODUCTO + " INTEGER NOT NULL, " +
                    COLUMN_CANTIDAD_DETALLE_PEDIDO + " INTEGER NOT NULL, " +
                    COLUMN_AUDIT_FECHA_INSERT_DETALLE_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_USUARIO_MODIF_DETALLE_PEDIDO + " VARCHAR, " +
                    COLUMN_AUDIT_FECHA_MODIF_DETALLE_PEDIDO + " VARCHAR, " +
                    "FOREIGN KEY (" + COLUMN_ID_PEDIDO + ") REFERENCES " + TABLE_PEDIDO + "(" + COLUMN_ID_PEDIDO + "), " +
                    "FOREIGN KEY (" + COLUMN_ID_PRODUCTO + ") REFERENCES " + TABLE_PRODUCTO + "(" + COLUMN_ID_PRODUCTO + ")" +
                    ")"); */

            db.execSQL("CREATE TABLE Detalle_Pedido (" +
                    "id_detalle_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "audit_fecha_modif_ VARCHAR," +
                    "id_pedido INTEGER NOT NULL," +
                    "id_producto INTEGER NOT NULL," +
                    "cantidad INTEGER NOT NULL," +
                    "costo_pedido INTEGER," + // nuevo atributo
                    "audit_fecha_insert_ VARCHAR," +
                    "audit_usuario_modif_ VARCHAR," +
                    "audit_fecha_modif__ VARCHAR," +
                    "FOREIGN KEY (id_pedido) REFERENCES Pedido (id_pedido)," +
                    "FOREIGN KEY (id_producto) REFERENCES Producto (id_producto)" +
                    ")"
            );

    }

    // EDITAR Y LEER DATABASE
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Actualizando base de datos de la versión " + oldVersion + " a la versión " + newVersion);

        if (oldVersion < 2) {
            // Crear una nueva tabla sin la restricción NOT NULL
            db.execSQL("CREATE TABLE Detalle_Pedido_Nueva (" +
                    // Agregar todas las columnas excepto la que se modificará
                    "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                    // ... Otras columnas ...
                    "costo_pedido INTEGER);");

            // Copiar datos de la tabla original a la nueva tabla
            db.execSQL("INSERT INTO Detalle_Pedido_Nueva SELECT * FROM Detalle_Pedido;");

            // Eliminar la tabla original
            db.execSQL("DROP TABLE Detalle_Pedido;");

            // Renombrar la nueva tabla al nombre original
            db.execSQL("ALTER TABLE Detalle_Pedido_Nueva RENAME TO Detalle_Pedido;");
        }

        // onCreate(db);
    }


            @Override
            public synchronized void close() {
                super.close();
            }

            public SQLiteDatabase getWritableDatabase() {
                return super.getWritableDatabase();
            }

            public SQLiteDatabase getReadableDatabase() {
                return super.getReadableDatabase();
            }

        //  FUNCIONES

            // CREAR

            // Crear Cuenta
            public void crearCuenta(String nombreUsuario, String correoElectronico, String telefonoUsuario, String contrasenhaUsuario) {
                SQLiteDatabase database = this.getWritableDatabase();
                String insertQuery = "INSERT INTO " + TABLE_USUARIO + " (" +
                        COLUMN_NOMBRE_USUARIO + ", " +
                        COLUMN_CORREO_ELECTRONICO + ", " +
                        COLUMN_TELEFONO_USUARIO + ", " +
                        COLUMN_CONTRASENHA_USUARIO + ", " +
                        COLUMN_ID_TIPO_USUARIO_USUARIO + ") VALUES (?, ?, ?, ?, ?)";

                // Establecer el tipo de usuario por defecto como 3 (Cliente)
                database.execSQL(insertQuery, new Object[]{nombreUsuario, correoElectronico, telefonoUsuario, contrasenhaUsuario, 4});

                database.close();
            }

            // Crear Tipo Usuario
                public void crearTipoUsuario(String nombreTipoUsuario, String descripcionTipoUsuario, int idFormularioPermiso) {
                    SQLiteDatabase database = this.getWritableDatabase();
                    String insertQuery = "INSERT INTO " + TABLE_TIPO_USUARIO + " (" +
                            COLUMN_NOMBRE_TIPO_USUARIO + ", " +
                            COLUMN_DESCRIPCION_TIPO_USUARIO + ", " +
                            COLUMN_ID_FORMULARIO_PERMISO + ") VALUES (?, ?, ?)";
                    database.execSQL(insertQuery, new Object[]{nombreTipoUsuario, descripcionTipoUsuario, idFormularioPermiso});
                    database.close();
                }



            // Crear Divisa con auditoría
            public void crearDivisa(String nombreDivisa, String simboloDivisa) {
                SQLiteDatabase database = this.getWritableDatabase();

                try {
                    // Iniciar transacción
                    database.beginTransaction();

                    // Verificar si la Divisa ya existe por nombre o símbolo
                    if (divisaNoExiste(nombreDivisa, simboloDivisa)) {

                        // Obtener fecha y hora actual en formato String
                        String fechaActual = obtenerFechaActual();

                        // Obtener el ID del usuario de alguna manera
                        long idUsuario = obtenerIdUsuario();

                        // Convertir el ID del usuario a String
                        String idUsuarioString = String.valueOf(idUsuario);

                        // Crear un objeto ContentValues para insertar datos
                        ContentValues values = new ContentValues();
                        values.put(COLUMN_NOMBRE_DIVISA, nombreDivisa);
                        values.put(COLUMN_SIMBOLO_DIVISA, simboloDivisa);

                        // Obtener la fecha y hora de la última modificación de la Divisa
                        String ultimaModificacion = obtenerUltimaModificacionDivisa(nombreDivisa);

                        // Si hay una última modificación, usar esa fecha y hora
                        // de lo contrario, usar la fecha y hora actual
                        if (ultimaModificacion != null) {
                            values.put(COLUMN_AUDIT_FECHA_INSERT_DIVISA, ultimaModificacion);
                        } else {
                            values.put(COLUMN_AUDIT_FECHA_INSERT_DIVISA, fechaActual);
                        }

                        values.put(COLUMN_AUDIT_USUARIO_MODIF_DIVISA, idUsuarioString);
                        values.put(COLUMN_AUDIT_FECHA_MODIF_DIVISA, fechaActual);

                        // Insertar datos en la tabla Divisa
                        database.insert(TABLE_DIVISA, null, values);

                        // Marcar la transacción como exitosa
                        database.setTransactionSuccessful();
                    } else {
                        // La Divisa ya existe, puedes manejarlo como desees, por ejemplo, mostrar un mensaje de error
                        Log.e("Error", "La Divisa con nombre o símbolo ya existe");
                    }
                } catch (Exception e) {
                    // Captura cualquier excepción que pueda ocurrir
                    Log.e("Error", "Error al crear la Divisa", e);
                } finally {
                    // Finalizar la transacción, independientemente de si fue exitosa o no
                    database.endTransaction();
                    // No es necesario cerrar la conexión a la base de datos aquí
                }
            }

            // Método para verificar si la Divisa ya existe por nombre o símbolo
            public boolean divisaNoExiste(String nombreDivisa, String simboloDivisa) {
                SQLiteDatabase database = this.getReadableDatabase();

                String query = "SELECT * FROM " + TABLE_DIVISA +
                        " WHERE " + COLUMN_NOMBRE_DIVISA + " = ? OR " + COLUMN_SIMBOLO_DIVISA + " = ?";

                Cursor cursor = database.rawQuery(query, new String[]{nombreDivisa, simboloDivisa});

                boolean noExiste = cursor.getCount() == 0;

                cursor.close();
                // No es necesario cerrar la conexión a la base de datos aquí

                return noExiste;
            }







            // MÉTODOS PARA AUDITORÍA

            // Método para obtener la fecha actual (para los insert)
            private String obtenerFechaActual() {
                Date fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                return dateFormat.format(fecha);
            }

            // Método para obtener la última fecha y hora de modificación de la Divisa
            private String obtenerUltimaModificacionDivisa(String nombreDivisa) {
                SQLiteDatabase database = this.getReadableDatabase();

                String[] columns = {COLUMN_AUDIT_FECHA_MODIF_DIVISA};
                String selection = COLUMN_NOMBRE_DIVISA + " = ?";
                String[] selectionArgs = {nombreDivisa};

                Cursor cursor = database.query(TABLE_DIVISA, columns, selection, selectionArgs, null, null, COLUMN_AUDIT_FECHA_MODIF_DIVISA + " DESC", "1");

                String ultimaModificacion = null;
                if (cursor.moveToFirst()) {
                    ultimaModificacion = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_MODIF_DIVISA));
                }

                cursor.close();
                return ultimaModificacion;
            }

        // Método para obtener el ID del puto usuario
        private long obtenerIdUsuario() {
            // Verifica si hay un usuario autenticado
            long usuarioID = DBHelper.getUsuarioLogueado();

            if (usuarioID != -1) {
                // Si hay un usuario autenticado, devuelve su ID directamente
                return usuarioID;
            } else {
                // No hay usuario autenticado, realiza la lógica necesaria para obtener el ID
                SQLiteDatabase database = this.getReadableDatabase();

                // Obtener el correo electrónico del usuario de alguna manera
                String correoUsuario = "correo@example.com";  // Cambia este valor

                String[] columns = {COLUMN_ID_USUARIO};
                String selection = COLUMN_CORREO_ELECTRONICO + " = ?";
                String[] selectionArgs = {correoUsuario};

                Cursor cursor = database.query(TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);

                long userID = -1;
                if (cursor.moveToFirst()) {
                    userID = cursor.getLong(cursor.getColumnIndex(COLUMN_ID_USUARIO));
                }

                cursor.close();
                database.close();

                return userID;
            }
        }

        // Método para obtener el correo del puto usuario
    public int obtenerTipoUsuarioPorCorreo(String correoElectronico) {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_ID_TIPO_USUARIO_USUARIO};
        String selection = COLUMN_CORREO_ELECTRONICO + " = ?";
        String[] selectionArgs = {correoElectronico};

        Cursor cursor = database.query(TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);

        int tipoUsuario = -1;

        if (cursor.moveToFirst()) {
            tipoUsuario = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO_USUARIO_USUARIO));
        }

        cursor.close();
        // No es necesario cerrar la conexión a la base de datos aquí

        return tipoUsuario;
    }



    // Comprobar Credenciales

            public boolean comprobarCredenciales(String correoElectronico, String contrasenhaUsuario) {
            SQLiteDatabase database = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USUARIO + " WHERE " + COLUMN_CORREO_ELECTRONICO + " = ? AND " + COLUMN_CONTRASENHA_USUARIO + " = ? ";
            Cursor cursor = database.rawQuery(query, new String[]{correoElectronico, contrasenhaUsuario});

            if (cursor.getCount() > 0) {
                cursor.close();
                return true;

            } else {
                cursor.close();
                return false;

                }
            }

        public boolean comprobarCorreo(String correoElectronico) {
            SQLiteDatabase database = this.getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_USUARIO + " WHERE " + COLUMN_CORREO_ELECTRONICO + " = ?";
            Cursor cursor = database.rawQuery(query, new String[]{correoElectronico});

            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            } else {
                cursor.close();
                return false;
            }
        }

        // Actualizar datos

        public void actualizarContrasenha(String correoElectronico, String nuevaContrasenha) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(COLUMN_CONTRASENHA_USUARIO, nuevaContrasenha);
            db.update(TABLE_USUARIO, valores, COLUMN_CORREO_ELECTRONICO + " = ?", new String[]{correoElectronico});
            db.close();
        }

    public void actualizarDivisa(int idDivisa, String nuevoNombre, String nuevoSimbolo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(COLUMN_NOMBRE_DIVISA, nuevoNombre);
        valores.put(COLUMN_SIMBOLO_DIVISA, nuevoSimbolo);
        db.update(TABLE_DIVISA, valores, COLUMN_ID_DIVISA + " = ?", new String[]{String.valueOf(idDivisa)});
        db.close();
    }

    public void actualizarTipoUsuario(String correoElectronico, int nuevoIdTipoUsuario) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(COLUMN_ID_TIPO_USUARIO_USUARIO, nuevoIdTipoUsuario);
            db.update(TABLE_USUARIO, valores, COLUMN_CORREO_ELECTRONICO + " = ?", new String[]{correoElectronico});
            db.close();
        }



    // Get Usuario ID
            @SuppressLint("Range")
            public long getUsuarioID(String mail) {
            SQLiteDatabase database = this.getReadableDatabase();
            String[] columns = {COLUMN_ID_USUARIO};
            String selection = COLUMN_CORREO_ELECTRONICO + " = ?";
            String[] selectionArgs = {mail};

            Cursor cursor = database.query(TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);

            long userID = -1;
            if (cursor.moveToFirst()) {
                userID = cursor.getLong(cursor.getColumnIndex(COLUMN_ID_USUARIO));

            }
            cursor.close();
            return userID;
        }


         // Usuario Logueado
            public static void setUsuarioLogueado(long userID) {
            usuarioLogueado = userID;
            }

            public static long getUsuarioLogueado() {
            return usuarioLogueado;
            }

        // Get datos de Usuario
        public Cursor getUsuarioData(long userId) {
        String[] columns = {DBHelper.COLUMN_NOMBRE_USUARIO, DBHelper.COLUMN_CORREO_ELECTRONICO, DBHelper.COLUMN_TELEFONO_USUARIO};
        String selection = DBHelper.COLUMN_ID_USUARIO + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        SQLiteDatabase database = getReadableDatabase();

        // Imprime las columnas disponibles en el cursor para ayudar en la depuración
        Cursor cursor = database.query(DBHelper.TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);
        Log.d("Columnas disponibles", Arrays.toString(cursor.getColumnNames()));

        // Vuelve a posicionar el cursor al principio antes de devolverlo
        cursor.moveToFirst();

        return cursor;
    }


        // LISTA DE USUARIOS
        @SuppressLint("Range")
        public List<Usuario> obtenerListaUsuarios() {
            List<Usuario> usuarios = new ArrayList<>();

            SQLiteDatabase database = this.getReadableDatabase();
            String[] columns = {
                    COLUMN_NOMBRE_USUARIO,
                    COLUMN_CORREO_ELECTRONICO,
                    COLUMN_TELEFONO_USUARIO
            };

            Cursor cursor = database.query(TABLE_USUARIO, columns, null, null, null, null, null);

            try {
                while (cursor.moveToNext()) {
                    String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_USUARIO));
                    String correo = cursor.getString(cursor.getColumnIndex(COLUMN_CORREO_ELECTRONICO));
                    String telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO_USUARIO));

                    Usuario usuario = new Usuario(nombre, correo, telefono);
                    usuarios.add(usuario);
                }
            } finally {
                cursor.close();
            }

            // Cierra la base de datos
            database.close();

            // Agrega logs para depuración
            for (Usuario usuario : usuarios) {
                Log.d("Usuario", "Nombre: " + usuario.getNombreUsuario() + ", Correo: " + usuario.getCorreoElectronico() + ", Teléfono: " + usuario.getTelefonoUsuario());
            }

            return usuarios;
        }

    // LISTA DE DIVISAS
    public List<Divisa> obtenerListaDivisas() {
        List<Divisa> divisas = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {
                COLUMN_NOMBRE_DIVISA,
                COLUMN_SIMBOLO_DIVISA
        };

        Cursor cursor = database.query(TABLE_DIVISA, columns, null, null, null, null, null);

        try {
            while (cursor.moveToNext()) {
                String nombreDivisa = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_DIVISA));
                String simboloDivisa = cursor.getString(cursor.getColumnIndex(COLUMN_SIMBOLO_DIVISA));

                Divisa divisa = new Divisa(nombreDivisa, simboloDivisa);
                divisas.add(divisa);
            }
        } finally {
            cursor.close();
        }

        // Cierra la base de datos
        database.close();

        // Agrega logs para depuración
        for (Divisa divisa : divisas) {
            Log.d("Divisa", "Nombre: " + divisa.getNombreDivisa() + ", Símbolo: " + divisa.getSimboloDivisa());
        }

        return divisas;
    }



    // ELIMINAR DIVISA
    // Método para eliminar una divisa por su ID
    public void eliminarDivisa(int idDivisa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIVISA, COLUMN_ID_DIVISA + " = ?",
                new String[]{String.valueOf(idDivisa)});
        db.close();
    }


}

