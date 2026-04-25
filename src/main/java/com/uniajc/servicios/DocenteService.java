package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.DocenteDao;
import com.uniajc.modelo.Docente;

public class DocenteService {

    private final DocenteDao docenteDao;

    public DocenteService(DocenteDao docenteDao) {
        this.docenteDao = docenteDao;
    }

    public void registrarDocente(Docente docente) {
        if (docente == null) {
            throw new IllegalArgumentException("El docente no puede ser nulo.");
        }
        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del docente es obligatorio.");
        }
        docenteDao.guardarDocente(docente);
    }

    public List<Docente> mostrarTodosLosDocentes() {
        return docenteDao.obtenerTodosLosDocentes();
    }

    public Docente obtenerDocentePorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del docente debe ser mayor a 0.");
        }
        return docenteDao.obtenerDocentePorId(id);
    }

    public void actualizarDocente(Docente docente) {
        if (docente == null || docente.getIdDocente() == null || docente.getIdDocente() <= 0) {
            throw new IllegalArgumentException("El ID del docente es requerido y debe ser mayor a 0.");
        }
        docenteDao.actualizarDocente(docente);
    }

    public void eliminarDocente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del docente debe ser mayor a 0.");
        }
        docenteDao.eliminarDocente(id);
    }
}