package com.example.itmanagement.modelo;

public class TipoUsuario {
    private int idTipoUsuario;
    private String nombreTipoUsuario;
    private String descripcionTipoUsuario;
    private int idFormularioPermiso;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModif;

    // Constructor sin atributos de auditoría
    public TipoUsuario(String nombreTipoUsuario, String descripcionTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
        this.descripcionTipoUsuario = descripcionTipoUsuario;
    }

    // Constructor con atributos de auditoría
    public TipoUsuario(int idTipoUsuario, String nombreTipoUsuario, String descripcionTipoUsuario, int idFormularioPermiso, String auditFechaInsert, String auditUsuarioModif, String auditFechaModif) {
        this.idTipoUsuario = idTipoUsuario;
        this.nombreTipoUsuario = nombreTipoUsuario;
        this.descripcionTipoUsuario = descripcionTipoUsuario;
        this.auditFechaInsert = auditFechaInsert;
        this.auditUsuarioModif = auditUsuarioModif;
        this.auditFechaModif = auditFechaModif;
    }

    // Getters y setters
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public String getDescripcionTipoUsuario() {
        return descripcionTipoUsuario;
    }

    public void setDescripcionTipoUsuario(String descripcionTipoUsuario) {
        this.descripcionTipoUsuario = descripcionTipoUsuario;
    }

    public int getIdFormularioPermiso() {
        return idFormularioPermiso;
    }

    public void setIdFormularioPermiso(int idFormularioPermiso) {
        this.idFormularioPermiso = idFormularioPermiso;
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
