package com.jespalomo.prueba;

public class ListElement {
    public String color;
    public String ruta;
    public String duracion;
    public String tipo;

    public ListElement(String color, String ruta, String duracion, String tipo) {
        this.color = color;
        this.ruta = ruta;
        this.duracion = duracion;
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
