package com.jespalomo.prueba;

import java.io.Serializable;

public class ListElement implements Serializable {
    public String ruta;
    public String duracion;
    public String tipo;
    public String nombre1;
    public String nombre2;
    public Double latVertical1;
    public Double latVertical2;
    public Double latHorizontal1;
    public Double latHorizontal2;
    Pais p;

    public ListElement(String ruta, String duracion, String tipo, String nombre1, String nombre2,
                       Double latVertical1, Double latVertical2, Double latHorizontal1, Double latHorizontal2, Pais p) {
        this.ruta = ruta;
        this.duracion = duracion;
        this.tipo = tipo;
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.latVertical1 = latVertical1;
        this.latVertical2 = latVertical2;
        this.latHorizontal1 = latHorizontal1;
        this.latHorizontal2 = latHorizontal2;
        this.p = p;
    }

    public Pais getP() {
        return p;
    }

    public void setP(Pais p) {
        this.p = p;
    }


    public ListElement() {

    }


    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public Double getLatVertical1() {
        return latVertical1;
    }

    public void setLatVertical1(Double latVertical1) {
        this.latVertical1 = latVertical1;
    }

    public Double getLatVertical2() {
        return latVertical2;
    }

    public void setLatVertical2(Double latVertical2) {
        this.latVertical2 = latVertical2;
    }

    public Double getLatHorizontal1() {
        return latHorizontal1;
    }

    public void setLatHorizontal1(Double latHorizontal1) {
        this.latHorizontal1 = latHorizontal1;
    }

    public Double getLatHorizontal2() {
        return latHorizontal2;
    }

    public void setLatHorizontal2(Double latHorizontal2) {
        this.latHorizontal2 = latHorizontal2;
    }





    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
