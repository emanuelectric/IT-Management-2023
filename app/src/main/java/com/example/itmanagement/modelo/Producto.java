package com.example.itmanagement.modelo;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private int precioProducto;
    private int idCategoriaProducto;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModif;
    private int cantidadStock;
    private boolean seleccionado;
    private int cantidadSeleccionada;


    // Constructor
    public Producto(String nombreProducto, String descripcionProducto, int precioProducto,
                    int idCategoriaProducto, int cantidadStock) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.cantidadStock = cantidadStock;
        this.seleccionado = false;  // Inicializar como no seleccionado
        this.cantidadSeleccionada = 0;  // Inicializar la cantidad seleccionada en 0
    }

    // Constructor
    public Producto(String nombreProducto, String descripcionProducto, int precioProducto,
                    int idCategoriaProducto, String auditFechaInsert, String auditUsuarioModif,
                    String auditFechaModif, String imagenProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.auditFechaInsert = auditFechaInsert;
        this.auditUsuarioModif = auditUsuarioModif;
        this.auditFechaModif = auditFechaModif;
        this.seleccionado = false;  // Inicializar como no seleccionado
        this.cantidadSeleccionada = 0;  // Inicializar la cantidad seleccionada en 0
    }

    public Producto(String nombreProducto, String descripcionProducto, int idCategoriaProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.seleccionado = false;  // Inicializar como no seleccionado
        this.cantidadSeleccionada = 0;  // Inicializar la cantidad seleccionada en 0
    }

    public Producto(String nombreProducto, String descripcionProducto, int precioProducto, int idCategoriaProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.seleccionado = false;  // Inicializar como no seleccionado
        this.cantidadSeleccionada = 0;  // Inicializar la cantidad seleccionada en 0
    }



    // Métodos Getter y Setter

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public int getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public String getAuditFechaInsert() {
        return auditFechaInsert;
    }

    public void setAuditFechaInsert(String auditFechaInsert) {
        this.auditFechaInsert = auditFechaInsert;
    }

    public String getAuditUsuarioModif() {
        return auditUsuarioModif;
    }

    public void setAuditUsuarioModif(String auditUsuarioModif) {
        this.auditUsuarioModif = auditUsuarioModif;
    }

    public String getAuditFechaModif() {
        return auditFechaModif;
    }

    public void setAuditFechaModif(String auditFechaModif) {
        this.auditFechaModif = auditFechaModif;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    // Getter y setter para la propiedad de cantidad seleccionada
    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }


}
