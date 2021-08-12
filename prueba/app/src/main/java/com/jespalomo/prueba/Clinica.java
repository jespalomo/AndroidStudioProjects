package com.jespalomo.prueba;

import java.io.Serializable;

public class Clinica implements Serializable {
    private int id;
    private String pais;
    private double latVertical;
    private double latHorizontal;
    private String nombre;
    private String telefono;
    private String direccion;



    public Clinica(int id, String pais, double latVertical, double latHorizontal, String nombre, String telefono, String direccion) {
        this.id = id;
        this.pais = pais;
        this.latVertical = latVertical;
        this.latHorizontal = latHorizontal;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Clinica(){

    }
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
