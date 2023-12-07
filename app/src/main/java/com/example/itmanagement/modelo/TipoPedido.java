package com.example.itmanagement.modelo;

public class TipoPedido {
    private int idTipoPedido;
    private String nombreTipoPedido;
    private String descripcionTipoPedido;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModif;

    // Constructor
    public TipoPedido(String nombreTipoPedido, String descripcionTipoPedido) {
        this.nombreTipoPedido = nombreTipoPedido;
        this.descripcionTipoPedido = descripcionTipoPedido;
    }

    // Métodos Getter y Setter

    public int getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(int idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public String getNombreTipoPedido() {
        return nombreTipoPedido;
    }

    public void setNombreTipoPedido(String nombreTipoPedido) {
        this.nombreTipoPedido = nombreTipoPedido;
    }

    public String getDescripcionTipoPedido() {
        return descripcionTipoPedido;
    }

    public void setDescripcionTipoPedido(String descripcionTipoPedido) {
        this.descripcionTipoPedido = descripcionTipoPedido;
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
