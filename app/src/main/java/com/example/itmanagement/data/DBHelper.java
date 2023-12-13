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

import com.example.itmanagement.modelo.CategoriaProducto;
import com.example.itmanagement.modelo.Divisa;
import com.example.itmanagement.modelo.EstadoPedido;
import com.example.itmanagement.modelo.Producto;
import com.example.itmanagement.modelo.TipoPedido;
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
        public static final int DB_VERSION = 11;

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
            public static final String COLUMN_CANTIDAD_STOCK = "cantidad_stock";
            public static final String COLUMN_ID_CATEGORIA_PRODUCTO_PRODUCTO = "id_categoria_producto";
            public static final String COLUMN_AUDIT_FECHA_INSERT_PRODUCTO = "audit_fecha_insert_";
            public static final String COLUMN_AUDIT_USUARIO_MODIF_PRODUCTO = "audit_usuario_modif_";
            public static final String COLUMN_AUDIT_FECHA_MODIF_PRODUCTO = "audit_fecha_modif_";

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
            public static final String COLUMN_UBICACION_PEDIDO_PEDIDO = "ubicacion_pedido";
            public static final String COLUMN_COMENTARIO_PEDIDO_PEDIDO = "comentario_pedido";
            public static final String COLUMN_COSTO_TOTAL_PEDIDO = "costo_total";

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
                "ubicacion_pedido VARCHAR NOT NULL," +
                "comentario_pedido VARCHAR," +
                "costo_total INTEGER," +
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
                "cantidad_stock INTEGER," +
                "audit_fecha_insert_ VARCHAR," +
                "audit_usuario_modif_ VARCHAR," +
                "audit_fecha_modif_ VARCHAR," +
                "imagen_producto VARCHAR," +
                "FOREIGN KEY (id_categoria_producto) REFERENCES Categoria_Producto (id_categoria_producto)" +
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
                "audit_fecha_insert_ VARCHAR," +
                "audit_usuario_modif_ VARCHAR," +
                "audit_fecha_modif VARCHAR" +
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
                    "audit_usuario_modif_ VARCHAR" +
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
    // EDITAR Y LEER DATABASE (Actualizaciones)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Actualizando base de datos de la versión " + oldVersion + " a la versión " + newVersion);

        try {
            if (oldVersion < 2) {
                // Crear una nueva tabla Detalle_Pedido_Nueva sin la restricción NOT NULL
                db.execSQL("CREATE TABLE Detalle_Pedido_Nueva (" +
                        // Agregar todas las columnas excepto la que se modificará
                        "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                        // ... Otras columnas ...
                        "costo_pedido INTEGER);");

                // Copiar datos de la tabla Detalle_Pedido original a la nueva tabla
                db.execSQL("INSERT INTO Detalle_Pedido_Nueva SELECT * FROM Detalle_Pedido;");

                // Eliminar la tabla Detalle_Pedido original
                db.execSQL("DROP TABLE Detalle_Pedido;");

                // Renombrar la nueva tabla al nombre original
                db.execSQL("ALTER TABLE Detalle_Pedido_Nueva RENAME TO Detalle_Pedido;");
            }

            if (oldVersion < 3) {
                // Eliminar la columna id_divisa de la tabla Producto
                db.execSQL("CREATE TABLE ProductoTemp AS SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, id_categoria_producto, audit_fecha_insert_, audit_usuario_modif_, audit_fecha_modif_, imagen_producto FROM Producto;");
                db.execSQL("DROP TABLE Producto;");
                db.execSQL("CREATE TABLE Producto (id_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre_producto VARCHAR NOT NULL, descripcion_producto VARCHAR NOT NULL, precio_producto NUMERIC(2) NOT NULL, id_categoria_producto VARCHAR NOT NULL, audit_fecha_insert_ VARCHAR, audit_usuario_modif_ VARCHAR, audit_fecha_modif_ VARCHAR, imagen_producto VARCHAR, FOREIGN KEY (id_categoria_producto) REFERENCES Categoria_Producto (id_categoria_producto));");
                db.execSQL("INSERT INTO Producto SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, id_categoria_producto, audit_fecha_insert_, audit_usuario_modif_, audit_fecha_modif_, imagen_producto FROM ProductoTemp;");
                db.execSQL("DROP TABLE ProductoTemp;");
            }

            if (oldVersion < 4) {
                // Eliminar la columna duplicada audit_fecha_modif
                db.execSQL("CREATE TABLE Estado_Pedido_Temp AS SELECT id_estado_pedido, audit_fecha_modif_, nombre_estado_pedido, descripcion_estado_pedido, audit_fecha_insert_, audit_usuario_modif_ FROM Estado_Pedido;");
                db.execSQL("DROP TABLE Estado_Pedido;");
                db.execSQL("CREATE TABLE Estado_Pedido (id_estado_pedido INTEGER PRIMARY KEY AUTOINCREMENT, audit_fecha_modif_ VARCHAR, nombre_estado_pedido VARCHAR NOT NULL, descripcion_estado_pedido VARCHAR NOT NULL, audit_fecha_insert_ VARCHAR, audit_usuario_modif_ VARCHAR);");
                db.execSQL("INSERT INTO Estado_Pedido SELECT id_estado_pedido, audit_fecha_modif_, nombre_estado_pedido, descripcion_estado_pedido, audit_fecha_insert_, audit_usuario_modif_ FROM Estado_Pedido_Temp;");
                db.execSQL("DROP TABLE Estado_Pedido_Temp;");
            }

            if (oldVersion < 6) { // yea ommited the fuckin' 5
                // Eliminar la relación foránea en la tabla Tipo_Usuario
                db.execSQL("CREATE TABLE IF NOT EXISTS Tipo_Usuario_temp AS SELECT * FROM Tipo_Usuario;");
                db.execSQL("DROP TABLE IF EXISTS Tipo_Usuario;");
                db.execSQL("CREATE TABLE Tipo_Usuario (" +
                        "id_tipo_usuario INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombre_tipo_usuario VARCHAR NOT NULL," +
                        "descripcion_tipo_usuario VARCHAR NOT NULL," +
                        "audit_fecha_insert_ VARCHAR," +
                        "audit_usuario_modif_ VARCHAR," +
                        "audit_fecha_modif VARCHAR" +
                        ")");
                db.execSQL("INSERT INTO Tipo_Usuario SELECT * FROM Tipo_Usuario_temp;");
                db.execSQL("DROP TABLE IF EXISTS Tipo_Usuario_temp;");

                // Eliminar la tabla Formulario_Permiso
                db.execSQL("DROP TABLE IF EXISTS Formulario_Permiso;");
            }

            if (oldVersion < 7) {
                // Problema con tabla Estado Pedido; rehacerla sin el atributo not null de auditoria
                db.execSQL("CREATE TABLE Estado_Pedido_Temp AS SELECT id_estado_pedido, audit_fecha_modif_, nombre_estado_pedido, descripcion_estado_pedido, audit_fecha_insert_, audit_usuario_modif_ FROM Estado_Pedido;");
                db.execSQL("DROP TABLE Estado_Pedido;");
                db.execSQL("CREATE TABLE Estado_Pedido (id_estado_pedido INTEGER PRIMARY KEY AUTOINCREMENT, audit_fecha_modif_ VARCHAR, nombre_estado_pedido VARCHAR NOT NULL, descripcion_estado_pedido VARCHAR NOT NULL, audit_fecha_insert_ VARCHAR, audit_usuario_modif_ VARCHAR);");
                db.execSQL("INSERT INTO Estado_Pedido SELECT id_estado_pedido, audit_fecha_modif_, nombre_estado_pedido, descripcion_estado_pedido, audit_fecha_insert_, audit_usuario_modif_ FROM Estado_Pedido_Temp;");
                db.execSQL("DROP TABLE Estado_Pedido_Temp;");
            }

            if (oldVersion < 8) {
                try {
                    // Agregar la columna es_administrador a la tabla Usuario
                    db.execSQL("ALTER TABLE Usuario ADD COLUMN es_administrador INTEGER;");

                } catch (Exception e) {
                    Log.e(TAG, "Error al agregar la columna es_administrador a la tabla Usuario", e);
                }
            }

            if (oldVersion < 9) {
                // Eliminar la tabla Inventario si existe
                db.execSQL("DROP TABLE IF EXISTS Inventario");

                // Eliminar la columna es_administrador de la tabla Usuario si existe
                db.execSQL("CREATE TABLE UsuarioTemp AS SELECT * FROM Usuario;");
                db.execSQL("DROP TABLE IF EXISTS Usuario;");
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
                        ")");
                db.execSQL("INSERT INTO Usuario SELECT * FROM UsuarioTemp;");
                db.execSQL("DROP TABLE IF EXISTS UsuarioTemp;");

                // Agregar la columna cantidad_stock a la tabla Producto si no existe
                db.execSQL("ALTER TABLE Producto ADD COLUMN cantidad_stock INTEGER;");
            }

            if (oldVersion < 10) {
                    // Eliminar la columna id_divisa de la tabla Producto (actualizado)
                    db.execSQL("CREATE TABLE ProductoTemp AS SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, id_categoria_producto, audit_fecha_insert_, audit_usuario_modif_, audit_fecha_modif_, imagen_producto FROM Producto;");
                    db.execSQL("DROP TABLE Producto;");
                    db.execSQL("CREATE TABLE Producto (id_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre_producto VARCHAR NOT NULL, descripcion_producto VARCHAR NOT NULL, precio_producto NUMERIC(2) NOT NULL, id_categoria_producto VARCHAR NOT NULL, audit_fecha_insert_ VARCHAR, audit_usuario_modif_ VARCHAR, audit_fecha_modif_ VARCHAR, imagen_producto VARCHAR, FOREIGN KEY (id_categoria_producto) REFERENCES Categoria_Producto (id_categoria_producto));");
                    db.execSQL("INSERT INTO Producto SELECT id_producto, nombre_producto, descripcion_producto, precio_producto, id_categoria_producto, audit_fecha_insert_, audit_usuario_modif_, audit_fecha_modif_, imagen_producto FROM ProductoTemp WHERE 1=0;"); // Asegúrate de que esta línea excluye la columna id_divisa
                    db.execSQL("DROP TABLE ProductoTemp;");
            }

            if (oldVersion < 11) {
                try {
                    if (oldVersion < 11) {
                        // Crear una tabla temporal con los atributos necesarios (sin las columnas de auditoría)
                        db.execSQL("CREATE TABLE IF NOT EXISTS PedidoTemp (" +
                                "id_pedido INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "id_usuario INTEGER NOT NULL," +
                                "fecha_hora_pedido TIMESTAMP NOT NULL," +
                                "id_estado_pedido VARCHAR NOT NULL," +
                                "id_tipo_pedido INTEGER NOT NULL," +
                                "ubicacion_pedido VARCHAR NOT   NULL," +
                                "comentario_pedido VARCHAR," +
                                "costo_total INTEGER," +
                                "FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario)," +
                                "FOREIGN KEY (id_estado_pedido) REFERENCES Estado_Pedido (id_estado_pedido)," +
                                "FOREIGN KEY (id_tipo_pedido) REFERENCES Tipo_Pedido (id_tipo_pedido)" +
                                ");");

                        // Eliminar la tabla original
                        db.execSQL("DROP TABLE IF EXISTS Pedido;");

                        // Cambiar el nombre de la tabla temporal a la original
                        db.execSQL("ALTER TABLE PedidoTemp RENAME TO Pedido;");
                    }

                } catch (Exception e) {
                    Log.e(TAG, "Error durante la actualización de la base de datos", e);
                }
            }



        } catch (Exception e) {
            Log.e(TAG, "Error durante la actualización de la base de datos", e);
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

            @Override
            public void onOpen(SQLiteDatabase db) {
                super.onOpen(db);
                // Habilitar claves foráneas al abrir la base de datos
                db.execSQL("PRAGMA foreign_keys=ON;");
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
                    database.execSQL(insertQuery, new Object[]{nombreUsuario, correoElectronico, telefonoUsuario, contrasenhaUsuario, 3});

                    // database.close();
                }


        // Crear Tipo Usuario
        public void crearTipoUsuario(String nombreTipoUsuario, String descripcionTipoUsuario) {
            SQLiteDatabase database = this.getWritableDatabase();
            String insertQuery = "INSERT INTO " + TABLE_TIPO_USUARIO + " (" +
                    COLUMN_NOMBRE_TIPO_USUARIO + ", " +
                    COLUMN_DESCRIPCION_TIPO_USUARIO + ") VALUES (?, ?)";

            database.execSQL(insertQuery, new Object[]{nombreTipoUsuario, descripcionTipoUsuario});
            // database.close();
        }


        // Crear Divisa con auditoría mejorada
            public void crearDivisa(String nombreDivisa, String simboloDivisa, String correoUsuario) {
                SQLiteDatabase database = this.getWritableDatabase();

                try {
                    // Iniciar transacción
                    database.beginTransaction();

                    // Verificar si la Divisa ya existe por nombre o símbolo
                    if (divisaNoExiste(nombreDivisa, simboloDivisa)) {

                        // Obtener fecha y hora actual en formato String
                        String fechaActual = obtenerFechaActual();

                        // Obtener el ID del usuario por correo
                        long idUsuario = obtenerIdUsuarioPorCorreo(correoUsuario);

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




        // Crear un Producto con auditoría
            public void crearProducto(String nombreProducto, String descripcionProducto, int precioProducto, int cantidadStock, long idCategoriaProducto) {
                SQLiteDatabase database = this.getWritableDatabase();

                try {
                    // Iniciar transacción
                    database.beginTransaction();

                    // Verificar si el Producto ya existe por nombre
                    if (productoNoExiste(nombreProducto)) {

                        // Obtener fecha y hora actual en formato String
                        String fechaActual = obtenerFechaActual();

                        // Crear un objeto ContentValues para insertar datos
                        ContentValues values = new ContentValues();
                        values.put(COLUMN_NOMBRE_PRODUCTO, nombreProducto);
                        values.put(COLUMN_DESCRIPCION_PRODUCTO, descripcionProducto);
                        values.put(COLUMN_PRECIO_PRODUCTO, precioProducto);
                        values.put(COLUMN_CANTIDAD_STOCK, cantidadStock);
                        values.put(COLUMN_ID_CATEGORIA_PRODUCTO, idCategoriaProducto);

                        // Obtener la fecha y hora de la última modificación del Producto
                        String ultimaModificacion = obtenerUltimaModificacionProducto(nombreProducto);

                        // Si hay una última modificación, usar esa fecha y hora
                        // de lo contrario, usar la fecha y hora actual
                        if (ultimaModificacion != null) {
                            values.put(COLUMN_AUDIT_FECHA_INSERT_PRODUCTO, ultimaModificacion);
                        } else {
                            values.put(COLUMN_AUDIT_FECHA_INSERT_PRODUCTO, fechaActual);
                        }

                        // Insertar datos en la tabla Producto
                        database.insert(TABLE_PRODUCTO, null, values);

                        // Marcar la transacción como exitosa
                        database.setTransactionSuccessful();
                    } else {
                        // El Producto ya existe, puedes manejarlo como desees, por ejemplo, mostrar un mensaje de error
                        Log.e("Error", "El Producto con nombre ya existe");
                    }
                } catch (Exception e) {
                    // Captura cualquier excepción que pueda ocurrir
                    Log.e("Error", "Error al crear el Producto", e);
                } finally {
                    // Finalizar transacción
                    database.endTransaction();
                }
            }

    // Crear una Categoría de Producto con auditoría
        public void crearCategoriaProducto(String nombreCategoriaProducto, String descripcionCategoriaProducto, String correoUsuario) {
            SQLiteDatabase database = this.getWritableDatabase();

            try {
                // Iniciar transacción
                database.beginTransaction();

                // Verificar si la Categoría de Producto ya existe por nombre
                if (categoriaProductoNoExiste(nombreCategoriaProducto, descripcionCategoriaProducto)) {

                    // Obtener fecha y hora actual en formato String
                    String fechaActual = obtenerFechaActual();

                    // Obtener el ID del usuario por correo
                    long idUsuario = obtenerIdUsuarioPorCorreo(correoUsuario);

                    // Convertir el ID del usuario a String
                    String idUsuarioString = String.valueOf(idUsuario);

                    // Crear un objeto ContentValues para insertar datos
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_NOMBRE_CATEGORIA_PRODUCTO, nombreCategoriaProducto);
                    values.put(COLUMN_DESCRIPCION_CATEGORIA_PRODUCTO, descripcionCategoriaProducto);

                    // Obtener la fecha y hora de la última modificación de la Categoría de Producto
                    String ultimaModificacion = obtenerUltimaModificacionCategoriaProducto(nombreCategoriaProducto);

                    // Si hay una última modificación, usar esa fecha y hora
                    // de lo contrario, usar la fecha y hora actual
                    if (ultimaModificacion != null) {
                        values.put(COLUMN_AUDIT_FECHA_INSERT_CATEGORIA_PRODUCTO, ultimaModificacion);
                    } else {
                        values.put(COLUMN_AUDIT_FECHA_INSERT_CATEGORIA_PRODUCTO, fechaActual);
                    }

                    values.put(COLUMN_AUDIT_USUARIO_MODIF_CATEGORIA_PRODUCTO, idUsuarioString);
                    values.put(COLUMN_AUDIT_FECHA_MODIF_CATEGORIA_PRODUCTO, fechaActual);

                    // Insertar datos en la tabla Categoria_Producto
                    database.insert(TABLE_CATEGORIA_PRODUCTO, null, values);

                    // Marcar la transacción como exitosa
                    database.setTransactionSuccessful();
                } else {
                    // La Categoría de Producto ya existe, puedes manejarlo como desees, por ejemplo, mostrar un mensaje de error
                    Log.e("Error", "La Categoría de Producto con nombre ya existe");
                }
            } catch (Exception e) {
                // Captura cualquier excepción que pueda ocurrir
                Log.e("Error", "Error al crear la Categoría de Producto", e);
            } finally {
                // Finalizar la transacción, independientemente de si fue exitosa o no
                database.endTransaction();
                // No es necesario cerrar la conexión a la base de datos aquí
            }
        }


    // Crear un Estado de Pedido con auditoría
        public void crearEstadoPedido(String nombreEstadoPedido, String descripcionEstadoPedido, String correoUsuario) {
            SQLiteDatabase database = this.getWritableDatabase();

            try {
                // Iniciar transacción
                database.beginTransaction();

                // Verificar si el Estado de Pedido ya existe por nombre
                if (estadoPedidoNoExiste(nombreEstadoPedido, descripcionEstadoPedido)) {

                    // Obtener fecha y hora actual en formato String
                    String fechaActual = obtenerFechaActual();

                    // Obtener el ID del usuario por correo
                    long idUsuario = obtenerIdUsuarioPorCorreo(correoUsuario);

                    // Convertir el ID del usuario a String
                    String idUsuarioString = String.valueOf(idUsuario);

                    // Crear un objeto ContentValues para insertar datos
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_NOMBRE_ESTADO_PEDIDO, nombreEstadoPedido);
                    values.put(COLUMN_DESCRIPCION_ESTADO_PEDIDO, descripcionEstadoPedido);

                    // Obtener la fecha y hora de la última modificación del Estado de Pedido
                    String ultimaModificacion = obtenerUltimaModificacionEstadoPedido(nombreEstadoPedido);

                    // Si hay una última modificación, usar esa fecha y hora
                    // de lo contrario, usar la fecha y hora actual
                    if (ultimaModificacion != null) {
                        values.put(COLUMN_AUDIT_FECHA_INSERT_ESTADO_PEDIDO, ultimaModificacion);
                    } else {
                        values.put(COLUMN_AUDIT_FECHA_INSERT_ESTADO_PEDIDO, fechaActual);
                    }

                    values.put(COLUMN_AUDIT_USUARIO_MODIF_ESTADO_PEDIDO, idUsuarioString);
                    values.put(COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO, fechaActual);

                    // Insertar datos en la tabla Estado_Pedido
                    database.insert(TABLE_ESTADO_PEDIDO, null, values);

                    // Marcar la transacción como exitosa
                    database.setTransactionSuccessful();
                } else {
                    // El Estado de Pedido ya existe, puedes manejarlo como desees, por ejemplo, mostrar un mensaje de error
                    Log.e("Error", "El Estado de Pedido con nombre ya existe");
                }
            } catch (Exception e) {
                // Captura cualquier excepción que pueda ocurrir
                Log.e("Error", "Error al crear el Estado de Pedido", e);
            } finally {
                // Finalizar la transacción, independientemente de si fue exitosa o no
                database.endTransaction();
                // No es necesario cerrar la conexión a la base de datos aquí
            }
        }


        // Crear un Tipo de Pedido con auditoría
        public void crearTipoPedido(String nombreTipoPedido, String descripcionTipoPedido, String correoUsuario) {
            SQLiteDatabase database = this.getWritableDatabase();

            try {
                // Iniciar transacción
                database.beginTransaction();

                // Verificar si el Tipo de Pedido ya existe por nombre
                if (tipoPedidoNoExiste(nombreTipoPedido, descripcionTipoPedido)) {

                    // Obtener fecha y hora actual en formato String
                    String fechaActual = obtenerFechaActual();

                    // Obtener el ID del usuario por correo
                    long idUsuario = obtenerIdUsuarioPorCorreo(correoUsuario);

                    // Convertir el ID del usuario a String
                    String idUsuarioString = String.valueOf(idUsuario);

                    // Crear un objeto ContentValues para insertar datos
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_NOMBRE_TIPO_PEDIDO, nombreTipoPedido);
                    values.put(COLUMN_DESCRIPCION_TIPO_PEDIDO, descripcionTipoPedido);

                    // Obtener la fecha y hora de la última modificación del Tipo de Pedido
                    String ultimaModificacion = obtenerUltimaModificacionTipoPedido(nombreTipoPedido);

                    // Si hay una última modificación, usar esa fecha y hora
                    // de lo contrario, usar la fecha y hora actual
                    if (ultimaModificacion != null) {
                        values.put(COLUMN_AUDIT_FECHA_INSERT_TIPO_PEDIDO, ultimaModificacion);
                    } else {
                        values.put(COLUMN_AUDIT_FECHA_INSERT_TIPO_PEDIDO, fechaActual);
                    }

                    values.put(COLUMN_AUDIT_USUARIO_MODIF_TIPO_PEDIDO, idUsuarioString);
                    values.put(COLUMN_AUDIT_FECHA_MODIF_TIPO_PEDIDO, fechaActual);

                    // Insertar datos en la tabla Tipo_Pedido
                    database.insert(TABLE_TIPO_PEDIDO, null, values);

                    // Marcar la transacción como exitosa
                    database.setTransactionSuccessful();
                } else {
                    // El Tipo de Pedido ya existe, puedes manejarlo como desees, por ejemplo, mostrar un mensaje de error
                    Log.e("Error", "El Tipo de Pedido con nombre ya existe");
                }
            } catch (Exception e) {
                // Captura cualquier excepción que pueda ocurrir
                Log.e("Error", "Error al crear el Tipo de Pedido", e);
            } finally {
                // Finalizar la transacción, independientemente de si fue exitosa o no
                database.endTransaction();
                // No es necesario cerrar la conexión a la base de datos aquí
            }
        }











// VERIFICAR

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


    // Método para verificar si el Producto ya existe por nombre
        public boolean productoNoExiste(String nombreProducto) {
            SQLiteDatabase database = this.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_PRODUCTO +
                    " WHERE " + COLUMN_NOMBRE_PRODUCTO + " = ? ";

            Cursor cursor = database.rawQuery(query, new String[]{nombreProducto});

            boolean noExiste = cursor.getCount() == 0;

            cursor.close();

            return noExiste;
        }

    // Método para verificar si la Categoría de Producto ya existe por nombre o descripción
        public boolean categoriaProductoNoExiste(String nombreCategoria, String descripcionCategoria) {
            SQLiteDatabase database = this.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_CATEGORIA_PRODUCTO +
                    " WHERE " + COLUMN_NOMBRE_CATEGORIA_PRODUCTO + " = ? OR " + COLUMN_DESCRIPCION_CATEGORIA_PRODUCTO + " = ?";

            Cursor cursor = database.rawQuery(query, new String[]{nombreCategoria, descripcionCategoria});

            boolean noExiste = cursor.getCount() == 0;

            cursor.close();

            return noExiste;
        }

    // Método para verificar si el Estado de Pedido ya existe por nombre o descripción
        public boolean estadoPedidoNoExiste(String nombreEstado, String descripcionEstado) {
            SQLiteDatabase database = this.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_ESTADO_PEDIDO +
                    " WHERE " + COLUMN_NOMBRE_ESTADO_PEDIDO + " = ? OR " + COLUMN_DESCRIPCION_ESTADO_PEDIDO + " = ?";

            Cursor cursor = database.rawQuery(query, new String[]{nombreEstado, descripcionEstado});

            boolean noExiste = cursor.getCount() == 0;

            cursor.close();

            return noExiste;
        }

    // Método para verificar si el Tipo de Pedido ya existe por nombre o descripción
        public boolean tipoPedidoNoExiste(String nombreTipoPedido, String descripcionTipoPedido) {
            SQLiteDatabase database = this.getReadableDatabase();

            String query = "SELECT * FROM " + TABLE_TIPO_PEDIDO +
                    " WHERE " + COLUMN_NOMBRE_TIPO_PEDIDO + " = ? OR " + COLUMN_DESCRIPCION_TIPO_PEDIDO + " = ?";

            Cursor cursor = database.rawQuery(query, new String[]{nombreTipoPedido, descripcionTipoPedido});

            boolean noExiste = cursor.getCount() == 0;

            cursor.close();

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

    // Método para obtener el ID del usuario

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

            // Método para obtener el ID del usuario por su correo electrónico
            public long obtenerIdUsuarioPorCorreo(String correoElectronico) {
                SQLiteDatabase database = this.getReadableDatabase();

                String[] columns = {COLUMN_ID_USUARIO};
                String selection = COLUMN_CORREO_ELECTRONICO + " = ?";
                String[] selectionArgs = {correoElectronico};

                Cursor cursor = database.query(TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);

                long userId = -1;

                if (cursor.moveToFirst()) {
                    userId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID_USUARIO));
                }

                cursor.close();
                // database.close();

                return userId;
            }


  // OBTENER ULTIMAS MODIFICACIONES

    // Método para obtener la última fecha de modificación de un Producto
    public String obtenerUltimaModificacionProducto(String nombreProducto) {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_AUDIT_FECHA_MODIF_PRODUCTO};
        String selection = COLUMN_NOMBRE_PRODUCTO + " = ?";
        String[] selectionArgs = {nombreProducto};

        Cursor cursor = database.query(TABLE_PRODUCTO, columns, selection, selectionArgs, null, null, COLUMN_AUDIT_FECHA_MODIF_PRODUCTO + " DESC", "1");

        String ultimaModificacion = null;
        if (cursor.moveToFirst()) {
            ultimaModificacion = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_MODIF_PRODUCTO));
        }

        cursor.close();
        // database.close();

        return ultimaModificacion;
    }

    // Método para obtener la última fecha de modificación de una Categoría de Producto
    public String obtenerUltimaModificacionCategoriaProducto(String nombreCategoriaProducto) {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_AUDIT_FECHA_MODIF_CATEGORIA_PRODUCTO};
        String selection = COLUMN_NOMBRE_CATEGORIA_PRODUCTO + " = ?";
        String[] selectionArgs = {nombreCategoriaProducto};

        Cursor cursor = database.query(TABLE_CATEGORIA_PRODUCTO, columns, selection, selectionArgs, null, null, COLUMN_AUDIT_FECHA_MODIF_CATEGORIA_PRODUCTO + " DESC", "1");

        String ultimaModificacion = null;
        if (cursor.moveToFirst()) {
            ultimaModificacion = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_MODIF_CATEGORIA_PRODUCTO));
        }

        cursor.close();
        // database.close();

        return ultimaModificacion;
    }

    // Método para obtener la última fecha de modificación de un Estado de Pedido
    public String obtenerUltimaModificacionEstadoPedido(String nombreEstadoPedido) {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO};
        String selection = COLUMN_NOMBRE_ESTADO_PEDIDO + " = ?";
        String[] selectionArgs = {nombreEstadoPedido};

        Cursor cursor = database.query(TABLE_ESTADO_PEDIDO, columns, selection, selectionArgs, null, null, COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO + " DESC", "1");

        String ultimaModificacion = null;
        if (cursor.moveToFirst()) {
            ultimaModificacion = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_MODIF_ESTADO_PEDIDO));
        }

        cursor.close();
        // database.close();

        return ultimaModificacion;
    }


    // Método para obtener la última fecha de modificación de un Tipo de Pedido
    public String obtenerUltimaModificacionTipoPedido(String nombreTipoPedido) {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_AUDIT_FECHA_MODIF_TIPO_PEDIDO};
        String selection = COLUMN_NOMBRE_TIPO_PEDIDO + " = ?";
        String[] selectionArgs = {nombreTipoPedido};

        Cursor cursor = database.query(TABLE_TIPO_PEDIDO, columns, selection, selectionArgs, null, null, COLUMN_AUDIT_FECHA_MODIF_TIPO_PEDIDO + " DESC", "1");

        String ultimaModificacion = null;
        if (cursor.moveToFirst()) {
            ultimaModificacion = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_MODIF_TIPO_PEDIDO));
        }

        cursor.close();
        // database.close();

        return ultimaModificacion;
    }



    // COMPROBAR CREDENCIALES
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

// ACTUALIZAR

            // Actualizar Contraseña
                public void actualizarContrasenha(String correoElectronico, String nuevaContrasenha) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(COLUMN_CONTRASENHA_USUARIO, nuevaContrasenha);
                    db.update(TABLE_USUARIO, valores, COLUMN_CORREO_ELECTRONICO + " = ?", new String[]{correoElectronico});
                    db.close();
                }

            // Actualizar Divisa
                public void actualizarDivisa(int idDivisa, String nuevoNombre, String nuevoSimbolo) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put(COLUMN_NOMBRE_DIVISA, nuevoNombre);
                    valores.put(COLUMN_SIMBOLO_DIVISA, nuevoSimbolo);
                    db.update(TABLE_DIVISA, valores, COLUMN_ID_DIVISA + " = ?", new String[]{String.valueOf(idDivisa)});
                    db.close();
                }

           // Actualizar Tipo Usuario
                public void actualizarTipoUsuario(String correoElectronico, int nuevoIdTipoUsuario) {
                        SQLiteDatabase db = this.getWritableDatabase();
                        ContentValues valores = new ContentValues();
                        valores.put(COLUMN_ID_TIPO_USUARIO_USUARIO, nuevoIdTipoUsuario);
                        db.update(TABLE_USUARIO, valores, COLUMN_CORREO_ELECTRONICO + " = ?", new String[]{correoElectronico});
                        db.close();
                    }


    // Actualizar idTipoUsuario
    public void actualizarIdTipoUsuario(String correoElectronico, int nuevoIdTipoUsuario) {
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

    public int getTipoUsuario(long userId) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {COLUMN_ID_TIPO_USUARIO_USUARIO};
        String selection = COLUMN_ID_USUARIO + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = database.query(TABLE_USUARIO, columns, selection, selectionArgs, null, null, null);

        int tipoUsuario = -1;

        if (cursor != null && cursor.moveToFirst()) {
            tipoUsuario = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO_USUARIO_USUARIO));
            cursor.close();
        }

        return tipoUsuario;
    }





    // OBTENER LISTAS

            // Lista de Usuarios
                @SuppressLint("Range")
                public List<Usuario> obtenerListaUsuarios() {
                    List<Usuario> usuarios = new ArrayList<>();

                    SQLiteDatabase database = this.getReadableDatabase();

                    // Especifica las columnas que deseas recuperar
                    String[] columns = {
                            COLUMN_NOMBRE_USUARIO,
                            COLUMN_CORREO_ELECTRONICO,
                            COLUMN_TELEFONO_USUARIO,
                            COLUMN_ID_TIPO_USUARIO_USUARIO  // Agregamos la columna de id_tipo_usuario
                    };

                    // Ordena los resultados por id_tipo_usuario
                    String sortOrder = COLUMN_ID_TIPO_USUARIO_USUARIO + " ASC";

                    Cursor cursor = database.query(TABLE_USUARIO, columns, null, null, null, null, sortOrder);

                    try {
                        while (cursor.moveToNext()) {
                            String nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_USUARIO));
                            String correo = cursor.getString(cursor.getColumnIndex(COLUMN_CORREO_ELECTRONICO));
                            String telefono = cursor.getString(cursor.getColumnIndex(COLUMN_TELEFONO_USUARIO));
                            int idTipoUsuario = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO_USUARIO_USUARIO));  // Obtenemos el id_tipo_usuario

                            Usuario usuario = new Usuario(nombre, correo, telefono);
                            usuario.setIdTipoUsuario(idTipoUsuario);  // Asignamos el id_tipo_usuario al usuario
                            usuarios.add(usuario);
                        }
                    } finally {
                        cursor.close();
                    }

                    // Cierra la base de datos
                    // database.close();

                    // Agrega logs para depuración
                    for (Usuario usuario : usuarios) {
                        Log.d("Usuario", "Nombre: " + usuario.getNombreUsuario() + ", Correo: " + usuario.getCorreoElectronico() + ", Teléfono: " + usuario.getTelefonoUsuario() + ", Tipo Usuario: " + usuario.getIdTipoUsuario());
                    }

                    return usuarios;
                }




            // Lista de Divisas
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
                    // database.close();

                    // Agrega logs para depuración
                    for (Divisa divisa : divisas) {
                        Log.d("Divisa", "Nombre: " + divisa.getNombreDivisa() + ", Símbolo: " + divisa.getSimboloDivisa());
                    }

                    return divisas;
                }

           // Lista de productos
           public List<Producto> obtenerListaProductos() {
               List<Producto> productos = new ArrayList<>();

               SQLiteDatabase database = this.getReadableDatabase();
               String[] columns = {
                       COLUMN_ID_PRODUCTO,
                       COLUMN_NOMBRE_PRODUCTO,
                       COLUMN_DESCRIPCION_PRODUCTO,
                       COLUMN_PRECIO_PRODUCTO,
                       COLUMN_ID_CATEGORIA_PRODUCTO,
                       COLUMN_AUDIT_FECHA_INSERT_PRODUCTO,
                       COLUMN_AUDIT_USUARIO_MODIF_PRODUCTO,
                       COLUMN_AUDIT_FECHA_MODIF_PRODUCTO,
                       COLUMN_IMAGEN_PRODUCTO,
                       COLUMN_CANTIDAD_STOCK
               };

               Cursor cursor = database.query(TABLE_PRODUCTO, columns, null, null, null, null, null);

               try {
                   while (cursor.moveToNext()) {
                       int idProducto = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_PRODUCTO));
                       String nombreProducto = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_PRODUCTO));
                       String descripcionProducto = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_PRODUCTO));
                       int precioProducto = cursor.getInt(cursor.getColumnIndex(COLUMN_PRECIO_PRODUCTO));
                       int idCategoriaProducto = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CATEGORIA_PRODUCTO));
                       String auditFechaInsert = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_INSERT_PRODUCTO));
                       String auditUsuarioModif = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_USUARIO_MODIF_PRODUCTO));
                       String auditFechaModif = cursor.getString(cursor.getColumnIndex(COLUMN_AUDIT_FECHA_MODIF_PRODUCTO));
                       String imagenProducto = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGEN_PRODUCTO));
                       int cantidadStock = cursor.getInt(cursor.getColumnIndex(COLUMN_CANTIDAD_STOCK));

                       // Aquí puedes llamar a otro método en DBHelper para obtener el nombre de la categoría
                       String nombreCategoria = obtenerNombreCategoriaPorId(idCategoriaProducto);

                       // Crear un objeto Producto y agregarlo a la lista
                       Producto producto = new Producto(nombreProducto, descripcionProducto, precioProducto, idCategoriaProducto, auditFechaInsert, auditUsuarioModif, auditFechaModif, imagenProducto);
                       producto.setIdProducto(idProducto);
                       producto.setCantidadStock(cantidadStock);
                       productos.add(producto);
                   }
               } finally {
                   cursor.close();
               }

               // Agrega logs para depuración
               for (Producto producto : productos) {
                   Log.d("Producto", "ID: " + producto.getIdProducto() + ", Nombre: " + producto.getNombreProducto() + ", Descripción: " + producto.getDescripcionProducto() + ", Categoría: " + producto.getIdCategoriaProducto() + ", Cantidad en Stock: " + producto.getCantidadStock());
               }

               return productos;
           }


    // LISTA MENU PRODUCTOS

    // Lista de productos agrupados por categoría con campos específicos
    public List<Producto> obtenerListaMenuProductos() {
        List<Producto> productos = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        // Definir las columnas que deseas seleccionar
        String[] columns = {
                COLUMN_NOMBRE_PRODUCTO,
                COLUMN_DESCRIPCION_PRODUCTO,
                COLUMN_PRECIO_PRODUCTO,
                COLUMN_ID_CATEGORIA_PRODUCTO
        };

// Realizar la consulta para obtener los productos agrupados por categoría
        Cursor cursor = database.query(
                TABLE_PRODUCTO,
                columns,
                null,
                null,
                null,
                null,
                null,
                null
        );



        try {
            while (cursor.moveToNext()) {
                String nombreProducto = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_PRODUCTO));
                String descripcionProducto = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_PRODUCTO));
                int precioProducto = cursor.getInt(cursor.getColumnIndex(COLUMN_PRECIO_PRODUCTO));
                int idCategoriaProducto = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CATEGORIA_PRODUCTO));

                // Crear un objeto Producto y agregarlo a la lista
                Producto producto = new Producto(nombreProducto, descripcionProducto, precioProducto, idCategoriaProducto);
                productos.add(producto);
            }
        } finally {
            cursor.close();
        }

        // Agrega logs para depuración
        for (Producto producto : productos) {
            Log.d("Producto", "Nombre: " + producto.getNombreProducto() + ", Descripción: " + producto.getDescripcionProducto() + ", Precio: " + producto.getPrecioProducto() + ", Categoría: " + producto.getIdCategoriaProducto());
        }

        return productos;
    }


    // Lista de categorías de productos
        public List<CategoriaProducto> obtenerListaCategoriasProducto() {
            List<CategoriaProducto> categoriasProducto = new ArrayList<>();

            SQLiteDatabase database = this.getReadableDatabase();
            String[] columns = {
                    COLUMN_NOMBRE_CATEGORIA_PRODUCTO,
                    COLUMN_DESCRIPCION_CATEGORIA_PRODUCTO
            };

            Cursor cursor = database.query(TABLE_CATEGORIA_PRODUCTO, columns, null, null, null, null, null);

            try {
                while (cursor.moveToNext()) {
                    String nombreCategoriaProducto = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_CATEGORIA_PRODUCTO));
                    String descripcionCategoriaProducto = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_CATEGORIA_PRODUCTO));

                    // Crear un objeto CategoriaProducto y agregarlo a la lista
                    CategoriaProducto categoriaProducto = new CategoriaProducto(nombreCategoriaProducto, descripcionCategoriaProducto);
                    categoriasProducto.add(categoriaProducto);
                }
            } finally {
                cursor.close();
            }

            // Agrega logs para depuración
            for (CategoriaProducto categoriaProducto : categoriasProducto) {
                Log.d("CategoriaProducto", "Nombre: " + categoriaProducto.getNombreCategoriaProducto() + ", Descripción: " + categoriaProducto.getDescripcionCategoriaProducto());
            }

            return categoriasProducto;
        }

        // Lista de tipos de pedido
        public List<TipoPedido> obtenerListaTiposPedido() {
            List<TipoPedido> tiposPedido = new ArrayList<>();

            SQLiteDatabase database = this.getReadableDatabase();
            String[] columns = {
                    COLUMN_NOMBRE_TIPO_PEDIDO,
                    COLUMN_DESCRIPCION_TIPO_PEDIDO
            };

            Cursor cursor = database.query(TABLE_TIPO_PEDIDO, columns, null, null, null, null, null);

            try {
                while (cursor.moveToNext()) {
                    String nombreTipoPedido = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_TIPO_PEDIDO));
                    String descripcionTipoPedido = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_TIPO_PEDIDO));

                    // Crear un objeto TipoPedido y agregarlo a la lista
                    TipoPedido tipoPedido = new TipoPedido(nombreTipoPedido, descripcionTipoPedido);
                    tiposPedido.add(tipoPedido);
                }
            } finally {
                cursor.close();
            }

            // Agrega logs para depuración
            for (TipoPedido tipoPedido : tiposPedido) {
                Log.d("TipoPedido", "Nombre: " + tipoPedido.getNombreTipoPedido() + ", Descripción: " + tipoPedido.getDescripcionTipoPedido());
            }

            return tiposPedido;
        }


        // Lista de estados de pedido
        public List<EstadoPedido> obtenerListaEstadosPedido() {
            List<EstadoPedido> estadosPedido = new ArrayList<>();

            SQLiteDatabase database = this.getReadableDatabase();
            String[] columns = {
                    COLUMN_NOMBRE_ESTADO_PEDIDO,
                    COLUMN_DESCRIPCION_ESTADO_PEDIDO
            };

            Cursor cursor = database.query(TABLE_ESTADO_PEDIDO, columns, null, null, null, null, null);

            try {
                while (cursor.moveToNext()) {
                    String nombreEstadoPedido = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_ESTADO_PEDIDO));
                    String descripcionEstadoPedido = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_ESTADO_PEDIDO));

                    // Crear un objeto EstadoPedido y agregarlo a la lista
                    EstadoPedido estadoPedido = new EstadoPedido(nombreEstadoPedido, descripcionEstadoPedido);
                    estadosPedido.add(estadoPedido);
                }
            } finally {
                cursor.close();
            }

            // Agrega logs para depuración
            for (EstadoPedido estadoPedido : estadosPedido) {
                Log.d("EstadoPedido", "Nombre: " + estadoPedido.getNombreEstadoPedido() + ", Descripción: " + estadoPedido.getDescripcionEstadoPedido());
            }

            return estadosPedido;
        }

    // Lista de tipos de usuario
    public List<TipoUsuario> obtenerListaTiposUsuario() {
        List<TipoUsuario> tiposUsuario = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {
                COLUMN_NOMBRE_TIPO_USUARIO,
                COLUMN_DESCRIPCION_TIPO_USUARIO
        };

        Cursor cursor = database.query(TABLE_TIPO_USUARIO, columns, null, null, null, null, null);

        try {
            while (cursor.moveToNext()) {
                String nombreTipoUsuario = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_TIPO_USUARIO));
                String descripcionTipoUsuario = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPCION_TIPO_USUARIO));

                // Crear un objeto TipoUsuario y agregarlo a la lista
                TipoUsuario tipoUsuario = new TipoUsuario(nombreTipoUsuario, descripcionTipoUsuario);
                tiposUsuario.add(tipoUsuario);
            }
        } finally {
            cursor.close();
        }

        // Agrega logs para depuración
        for (TipoUsuario tipoUsuario : tiposUsuario) {
            Log.d("TipoUsuario", "Nombre: " + tipoUsuario.getNombreTipoUsuario() + ", Descripción: " + tipoUsuario.getDescripcionTipoUsuario());
        }

        return tiposUsuario;
    }

    // Obtener una lista de los Tipos de Usuario para un Spinner
    public List<String> obtenerOpcionesTipoUsuario() {
        List<String> opcionesTipoUsuario = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {COLUMN_NOMBRE_TIPO_USUARIO};

        Cursor cursor = database.query(TABLE_TIPO_USUARIO, columns, null, null, null, null, null);

        try {
            while (cursor.moveToNext()) {
                String nombreTipoUsuario = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_TIPO_USUARIO));
                opcionesTipoUsuario.add(nombreTipoUsuario);
            }
        } finally {
            cursor.close();
        }

        return opcionesTipoUsuario;
    }





    // ELIMINAR

            // Método para eliminar una divisa por su ID
                public void eliminarDivisa(int idDivisa) {
                    SQLiteDatabase db = this.getWritableDatabase();
                    db.delete(TABLE_DIVISA, COLUMN_ID_DIVISA + " = ?",
                            new String[]{String.valueOf(idDivisa)});
                    db.close();
                }



    // OBTENER



        // Categoria Producto

            // Método para obtener la lista de nombres de categorías
                    public List<String> obtenerCategoriaProducto() {
                        SQLiteDatabase database = this.getReadableDatabase();

                        List<String> categorias = new ArrayList<>();

                        // Obtén solo los nombres de las categorías
                        String[] columns = {COLUMN_NOMBRE_CATEGORIA_PRODUCTO};
                        Cursor cursor = database.query(TABLE_CATEGORIA_PRODUCTO, columns, null, null, null, null, null);

                        // Itera a través del cursor y agrega los nombres de categorías a la lista
                        while (cursor.moveToNext()) {
                            String nombreCategoria = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_CATEGORIA_PRODUCTO));
                            categorias.add(nombreCategoria);
                        }

                        cursor.close();
                        // database.close();

                        return categorias;
                    }


            // Método para obtener el ID de la categoría de producto por nombre
            public int obtenerIdCategoriaProducto(String nombreCategoria) {
                SQLiteDatabase database = this.getReadableDatabase();

                String[] columns = {COLUMN_ID_CATEGORIA_PRODUCTO};
                String selection = COLUMN_NOMBRE_CATEGORIA_PRODUCTO + " = ?";
                String[] selectionArgs = {nombreCategoria};

                Cursor cursor = database.query(TABLE_CATEGORIA_PRODUCTO, columns, selection, selectionArgs, null, null, null);

                int categoriaID = -1;
                if (cursor.moveToFirst()) {
                    categoriaID = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CATEGORIA_PRODUCTO));
                }

                cursor.close();
                // database.close();

                return categoriaID;
            }

            // Método para obtener el nombre de la categoría por ID
            public String obtenerNombreCategoriaPorId(int idCategoria) {
                SQLiteDatabase db = this.getReadableDatabase();
                String[] projection = {COLUMN_NOMBRE_CATEGORIA_PRODUCTO}; // Reemplaza con el nombre real de la columna de nombre de categoría

                Cursor cursor = db.query(TABLE_CATEGORIA_PRODUCTO, projection, COLUMN_ID_CATEGORIA_PRODUCTO + " = ?",
                        new String[]{String.valueOf(idCategoria)}, null, null, null);

                String nombreCategoria = null;

                if (cursor != null && cursor.moveToFirst()) {
                    nombreCategoria = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_CATEGORIA_PRODUCTO));
                    cursor.close();
                }

                return nombreCategoria;
            }

            // Método para obtener el nombre del tipo de usuario por ID
            public String obtenerNombreTipoUsuarioPorID(int idTipoUsuario) {
                SQLiteDatabase db = this.getReadableDatabase();
                String[] projection = {COLUMN_NOMBRE_TIPO_USUARIO}; // Reemplaza con el nombre real de la columna de nombre de tipo de usuario

                Cursor cursor = db.query(TABLE_TIPO_USUARIO, projection, COLUMN_ID_TIPO_USUARIO + " = ?",
                        new String[]{String.valueOf(idTipoUsuario)}, null, null, null);

                String nombreTipoUsuario = null;

                if (cursor != null && cursor.moveToFirst()) {
                    nombreTipoUsuario = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_TIPO_USUARIO));
                    cursor.close();
                }

                return nombreTipoUsuario;
            }

        // Método para obtener el Id del tipo de usuario por nombre
        public int obtenerIdTipoUsuarioPorNombre(String nombreTipoUsuario) {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] projection = {COLUMN_ID_TIPO_USUARIO}; // Reemplaza con el nombre real de la columna de ID de tipo de usuario
            String selection = COLUMN_NOMBRE_TIPO_USUARIO + " = ?";
            String[] selectionArgs = {nombreTipoUsuario};

            Cursor cursor = db.query(TABLE_TIPO_USUARIO, projection, selection, selectionArgs, null, null, null);

            int idTipoUsuario = 0;  // Valor por defecto

            if (cursor != null && cursor.moveToFirst()) {
                idTipoUsuario = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO_USUARIO));
                cursor.close();
            }

            return idTipoUsuario;
        }



        // TIPOS DE PEDIDO

    // Método para obtener la lista de nombres de tipos de pedido
    public List<String> obtenerTiposPedido() {
        SQLiteDatabase database = this.getReadableDatabase();

        List<String> tiposPedido = new ArrayList<>();

        // Obtén solo los nombres de los tipos de pedido
        String[] columns = {COLUMN_NOMBRE_TIPO_PEDIDO};
        Cursor cursor = database.query(TABLE_TIPO_PEDIDO, columns, null, null, null, null, null);

        // Itera a través del cursor y agrega los nombres de tipos de pedido a la lista
        while (cursor.moveToNext()) {
            String nombreTipoPedido = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE_TIPO_PEDIDO));
            tiposPedido.add(nombreTipoPedido);
        }

        cursor.close();
        // database.close();

        return tiposPedido;
    }

    // Método para obtener el ID del tipo de pedido por nombre
    public int obtenerIdTipoPedido(String nombreTipoPedido) {
        SQLiteDatabase database = this.getReadableDatabase();

        String[] columns = {COLUMN_ID_TIPO_PEDIDO};
        String selection = COLUMN_NOMBRE_TIPO_PEDIDO + " = ?";
        String[] selectionArgs = {nombreTipoPedido};

        Cursor cursor = database.query(TABLE_TIPO_PEDIDO, columns, selection, selectionArgs, null, null, null);

        int tipoPedidoID = -1;
        if (cursor.moveToFirst()) {
            tipoPedidoID = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_TIPO_PEDIDO));
        }

        cursor.close();
        // database.close();

        return tipoPedidoID;
    }






}

