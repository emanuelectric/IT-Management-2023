package com.example.itmanagement.modelo;

public class EstadoPedido {
    private int idEstadoPedido;
    private String auditFechaModif;
    private String nombreEstadoPedido;
    private String descripcionEstadoPedido;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModifNotNull;

    // Constructor
    public EstadoPedido(String nombreEstadoPedido, String descripcionEstadoPedido) {
        this.nombreEstadoPedido = nombreEstadoPedido;
        this.descripcionEstadoPedido = descripcionEstadoPedido;
    }

    // Métodos Getter y Setter

    public int getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(int idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getAuditFechaModif() {
        return auditFechaModif;
    }

    public void setAuditFechaModif(String auditFechaModif) {
        this.auditFechaModif = auditFechaModif;
    }

    public String getNombreEstadoPedido() {
        return nombreEstadoPedido;
    }

    public void setNombreEstadoPedido(String nombreEstadoPedido) {
        this.nombreEstadoPedido = nombreEstadoPedido;
    }

    public String getDescripcionEstadoPedido() {
        return descripcionEstadoPedido;
    }

    public void setDescripcionEstadoPedido(String descripcionEstadoPedido) {
        this.descripcionEstadoPedido = descripcionEstadoPedido;
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

    public String getAuditFechaModifNotNull() {
        return auditFechaModifNotNull;
    }

    public void setAuditFechaModifNotNull(String auditFechaModifNotNull) {
        this.auditFechaModifNotNull = auditFechaModifNotNull;
    }
}
