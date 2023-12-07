package com.example.itmanagement.modelo;

public class CategoriaProducto {
    private int idCategoriaProducto;
    private String nombreCategoriaProducto;
    private String descripcionCategoriaProducto;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModif;

    // Constructor
    public CategoriaProducto(String nombreCategoriaProducto, String descripcionCategoriaProducto) {
        this.nombreCategoriaProducto = nombreCategoriaProducto;
        this.descripcionCategoriaProducto = descripcionCategoriaProducto;
    }

    // Métodos Getter y Setter

    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public String getNombreCategoriaProducto() {
        return nombreCategoriaProducto;
    }

    public void setNombreCategoriaProducto(String nombreCategoriaProducto) {
        this.nombreCategoriaProducto = nombreCategoriaProducto;
    }

    public String getDescripcionCategoriaProducto() {
        return descripcionCategoriaProducto;
    }

    public void setDescripcionCategoriaProducto(String descripcionCategoriaProducto) {
        this.descripcionCategoriaProducto = descripcionCategoriaProducto;
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
}
