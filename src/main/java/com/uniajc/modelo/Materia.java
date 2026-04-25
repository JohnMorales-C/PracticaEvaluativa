package com.uniajc.modelo;

public class Materia {

    private Integer idMateria;
    private String nombreMateria;
    private Integer creditos;

    public Materia() {
    }

    public Materia(Integer idMateria, String nombreMateria, Integer creditos) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.creditos = creditos;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }
}