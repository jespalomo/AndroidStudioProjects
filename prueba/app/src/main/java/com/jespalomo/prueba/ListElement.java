package com.jespalomo.prueba;

import java.io.Serializable;

public class ListElement implements Serializable {
    public String ruta;
    public String duracion;
    public String tipo;

    public ListElement(String ruta, String duracion, String tipo) {
        this.ruta = ruta;
        this.duracion = duracion;
        this.tipo = tipo;
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
