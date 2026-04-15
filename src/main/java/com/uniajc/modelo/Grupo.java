package com.uniajc.modelo;

public class Grupo {

    private Integer idGrupo;
    private Integer idMateria;
    private Integer idDocente;
    private String aula;
    private String horario;

    public Grupo() {
    }

    public Grupo(Integer idGrupo, Integer idMateria, Integer idDocente, String aula, String horario) {
        this.idGrupo = idGrupo;
        this.idMateria = idMateria;
        this.idDocente = idDocente;
        this.aula = aula;
        this.horario = horario;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

}
