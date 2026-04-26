package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.Inscripcion_CursoDao;
import com.uniajc.modelo.Inscripcion_Curso;

public class Inscripcion_CursoService {

    private final Inscripcion_CursoDao inscripcionDao;

    public Inscripcion_CursoService(Inscripcion_CursoDao inscripcionDao) {
        this.inscripcionDao = inscripcionDao;
    }

    public void registrarInscripcion(Inscripcion_Curso inscripcion) {
        if (inscripcion == null) {
            throw new IllegalArgumentException("La inscripción no puede ser nula.");
        }

        if (inscripcion.getIdEstudiante() == null || inscripcion.getIdEstudiante() <= 0) {
            throw new IllegalArgumentException("El ID del estudiante es obligatorio y debe ser mayor a 0.");
        }

        if (inscripcion.getIdGrupo() == null || inscripcion.getIdGrupo() <= 0) {
            throw new IllegalArgumentException("El ID del grupo es obligatorio y debe ser mayor a 0.");
        }

        if (inscripcion.getEstado() == null || inscripcion.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }

        inscripcionDao.guardarInscripcion(inscripcion);
    }

    public List<Inscripcion_Curso> obtenerTodasLasInscripciones() {
        return inscripcionDao.obtenerTodasLasInscripciones();
    }

    public Inscripcion_Curso obtenerInscripcionPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la inscripción debe ser mayor a 0.");
        }
        return inscripcionDao.obtenerInscripcionPorId(id);
    }

    public List<Inscripcion_Curso> obtenerInscripcionesPorEstudiante(int idEstudiante) {
        if (idEstudiante <= 0) {
            throw new IllegalArgumentException("El ID del estudiante debe ser mayor a 0.");
        }
        return inscripcionDao.obtenerInscripcionesPorEstudiante(idEstudiante);
    }

    public void actualizarInscripcion(Inscripcion_Curso inscripcion) {
        if (inscripcion == null || inscripcion.getIdInscripcion() == null || inscripcion.getIdInscripcion() <= 0) {
            throw new IllegalArgumentException("El ID de la inscripción es requerido y debe ser mayor a 0.");
        }

        if (inscripcion.getEstado() == null || inscripcion.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("El estado es obligatorio.");
        }

        if (inscripcion.getNotaFinal() != null && (inscripcion.getNotaFinal() < 0 || inscripcion.getNotaFinal() > 5)) {
            throw new IllegalArgumentException("La nota final debe estar entre 0 y 5.");
        }

        inscripcionDao.actualizarInscripcion(inscripcion);
    }

    public void eliminarInscripcion(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la inscripción debe ser mayor a 0.");
        }
        inscripcionDao.eliminarInscripcion(id);
    }
}
