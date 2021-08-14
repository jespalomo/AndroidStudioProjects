package com.jespalomo.prueba;

import java.io.Serializable;

public class Aeropuerto implements Serializable {
    public int id;
    public String nombre;
    public double latVertical;
    public double latHorizontal;
    public String codigo;
    public int coeficiente;
    public int idPais;
    Pais p;

    public Aeropuerto(int id, String nombre, double latVertical, double latHorizontal, String codigo, int coeficiente, int idPais, Pais p) {
        this.id = id;
        this.nombre = nombre;
        this.latVertical = latVertical;
        this.latHorizontal = latHorizontal;
        this.codigo = codigo;
        this.coeficiente = coeficiente;
        this.idPais = idPais;
        this.p = p;
    }

    public Aeropuerto() {

    }

    public Pais getP() {
        return p;
    }

    public void setP(Pais p) {
        this.p = p;
    }


    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(int coeficiente) {
        this.coeficiente = coeficiente;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
