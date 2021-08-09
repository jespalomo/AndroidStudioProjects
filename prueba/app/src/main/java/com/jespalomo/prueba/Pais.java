package com.jespalomo.prueba;

import java.io.Serializable;

public class Pais implements Serializable {
    private int id;
    private int numClinicas;
    private double latVertical;
    private double latHorizontal;
    private double latVertClinica;
    private double latHorClinica;
    private String restricciones;
    private String nombre;

    public Pais(){
        id=0;
        numClinicas=0;
        latVertical=0.0;
        latHorizontal=0.0;
        latVertClinica=0.0;
        latHorClinica=0.0;
        restricciones="";
        nombre="";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getLatVertClinica() {
        return latVertClinica;
    }

    public void setLatVertClinica(double latVertClinica) {
        this.latVertClinica = latVertClinica;
    }

    public double getLatHorClinica() {
        return latHorClinica;
    }

    public void setLatHorClinica(double latHorClinica) {
        this.latHorClinica = latHorClinica;
    }

    public String getRestricciones() {
        return restricciones;
    }

    public void setRestricciones(String restricciones) {
        this.restricciones = restricciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumClinicas() {
        return numClinicas;
    }

    public void setNumClinicas(int numClinicas) {
        this.numClinicas = numClinicas;
    }
}
