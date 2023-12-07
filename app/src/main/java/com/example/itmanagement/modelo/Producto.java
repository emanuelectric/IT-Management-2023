package com.example.itmanagement.modelo;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private double precioProducto;
    private int idCategoriaProducto;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModif;
    private String imagenProducto;

    // Constructor
    public Producto(String nombreProducto, String descripcionProducto, double precioProducto,
                    int idCategoriaProducto, String imagenProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.imagenProducto = imagenProducto;
    }

    // Constructor
    public Producto(String nombreProducto, String descripcionProducto, double precioProducto,
                    int idCategoriaProducto, String auditFechaInsert, String auditUsuarioModif,
                    String auditFechaModif, String imagenProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.idCategoriaProducto = idCategoriaProducto;
        this.auditFechaInsert = auditFechaInsert;
        this.auditUsuarioModif = auditUsuarioModif;
        this.auditFechaModif = auditFechaModif;
        this.imagenProducto = imagenProducto;
    }

    public Producto(String nombreProducto, String descripcionProducto, int idCategoriaProducto) {
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.idCategoriaProducto = idCategoriaProducto;
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

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
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

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }
}
