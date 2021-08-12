package com.jespalomo.prueba;

import java.io.Serializable;

public class Clinica implements Serializable {
    private int id;
    private String pais;
    private double latVertical;
    private double latHorizontal;
    private String nombre;

    public Clinica(int id, String pais, double latVertical, double latHorizontal, String nombre) {
        this.id = id;
        this.pais = pais;
        this.latVertical = latVertical;
        this.latHorizontal = latHorizontal;
        this.nombre = nombre;
    }

    public Clinica(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String idPais) {
        this.pais = pais;
    }

    public double getLatVertical() {
        return latVertical;
    }

    public void setLatVertical(double latVertical) {
        this.latVertical = latVertical;
    }

    public double getLatHorizontal() {
        return latHorizontal;
    }

    public void setLatHorizontal(double latHorizontal) {
        this.latHorizontal = latHorizontal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
