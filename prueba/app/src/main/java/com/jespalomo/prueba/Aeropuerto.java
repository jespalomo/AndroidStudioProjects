package com.jespalomo.prueba;

import java.io.Serializable;

public class Aeropuerto implements Serializable {
    public int id;
    public String nombre;
    public double latVertical;
    public double latHorizontal;
    public String pais;
    public String codigo;

    public Aeropuerto(int id, String nombre, double latVertical, double latHorizontal, String pais, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.latVertical = latVertical;
        this.latHorizontal = latHorizontal;
        this.pais = pais;
        this.codigo = codigo;
    }

    public Aeropuerto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
