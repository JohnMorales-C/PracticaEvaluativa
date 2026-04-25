package com.uniajc.modelo;

public class Docente {

    private Integer idDocente;
    private String nombre;
    private String especialidad;

    public Docente() {
    }

    public Docente(Integer idDocente, String nombre, String especialidad) {
        this.idDocente = idDocente;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}