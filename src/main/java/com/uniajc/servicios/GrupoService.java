package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.GrupoDao;
import com.uniajc.modelo.Grupo;

public class GrupoService {

    private final GrupoDao grupoDao;

    public GrupoService(GrupoDao grupoDao) {
        this.grupoDao = grupoDao;
    }

    public void registrarGrupo(Grupo grupo) {
        if (grupo == null) {
            throw new IllegalArgumentException("El grupo no puede ser nulo.");
        }
        if (grupo.getIdMateria() == null || grupo.getIdMateria() <= 0) {
            throw new IllegalArgumentException("El ID de la materia es obligatorio.");
        }
        if (grupo.getIdDocente() == null || grupo.getIdDocente() <= 0) {
            throw new IllegalArgumentException("El ID del docente es obligatorio.");
        }
        grupoDao.guardarGrupo(grupo);
    }

    public List<Grupo> mostrarTodosLosGrupos() {
        return grupoDao.obtenerTodosLosGrupos();
    }

    public Grupo obtenerGrupoPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del grupo debe ser mayor a 0.");
        }
        return grupoDao.obtenerGrupoPorId(id);
    }

    public void actualizarGrupo(Grupo grupo) {
        if (grupo == null || grupo.getIdGrupo() == null || grupo.getIdGrupo() <= 0) {
            throw new IllegalArgumentException("El ID del grupo es requerido y debe ser mayor a 0.");
        }
        grupoDao.actualizarGrupo(grupo);
    }

    public void eliminarGrupo(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del grupo debe ser mayor a 0.");
        }
        grupoDao.eliminarGrupo(id);
    }
}