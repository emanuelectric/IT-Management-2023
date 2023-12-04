package com.example.itmanagement.modelo;

public class Divisa {
    private int idDivisa;
    private String nombreDivisa;
    private String simboloDivisa;

    public Divisa(String nombreDivisa, String simboloDivisa) {
        this.nombreDivisa = nombreDivisa;
        this.simboloDivisa = simboloDivisa;
    }

    public String getNombreDivisa() {
        return nombreDivisa;
    }

    public void setNombreDivisa(String nombreDivisa) {
        this.nombreDivisa = nombreDivisa;
    }

    public String getSimboloDivisa() {
        return simboloDivisa;
    }

    public void setSimboloDivisa(String simboloDivisa) {
        this.simboloDivisa = simboloDivisa;
    }

    public int getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(int idDivisa) {
        this.idDivisa = idDivisa;
    }

}
