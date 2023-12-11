package com.example.itmanagement.modelo;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private int telefonoUsuario;
    private int idTipoUsuario;
    private String correoElectronico;
    private String contrasenhaUsuario;
    private String auditFechaInsert;
    private String auditUsuarioModif;
    private String auditFechaModif;

    // Constructor con todos los atributos
    public Usuario(int idUsuario, String nombreUsuario, int telefonoUsuario, int idTipoUsuario,
                   String correoElectronico, String contrasenhaUsuario, String auditFechaInsert,
                   String auditUsuarioModif, String auditFechaModif) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasenhaUsuario = contrasenhaUsuario;
        this.auditFechaInsert = auditFechaInsert;
        this.auditUsuarioModif = auditUsuarioModif;
        this.auditFechaModif = auditFechaModif;
    }

    // Constructor omitiendo datos de auditoría
    public Usuario(int idUsuario, String nombreUsuario, int telefonoUsuario, int idTipoUsuario,
                   String correoElectronico, String contrasenhaUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.correoElectronico = correoElectronico;
        this.contrasenhaUsuario = contrasenhaUsuario;
    }

    // Constructor que toma nombre, correo, si es admin, y teléfono
    public Usuario(String nombre, String correo, String telefono) {
        this.nombreUsuario = nombre;
        this.correoElectronico = correo;
        try {
            this.telefonoUsuario = Integer.parseInt(telefono);
        } catch (NumberFormatException e) {
            // Manejar la excepción si no se puede convertir a entero
            e.printStackTrace();
        }
    }

    // Getters y setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(int telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasenhaUsuario() {
        return contrasenhaUsuario;
    }

    public void setContrasenhaUsuario(String contrasenhaUsuario) {
        this.contrasenhaUsuario = contrasenhaUsuario;
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
