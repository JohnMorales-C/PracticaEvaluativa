package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;

public class EstudianteService {

    private final EstudianteDao estudianteDao;

    public EstudianteService(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

    public void registrarEstudiante(Estudiante estudiante) {

        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }

        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty() || 
            estudiante.getApellido() == null || estudiante.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("Los campos nombre y apellido del estudiante son obligatorios.");
        }

        estudianteDao.guardarEstudiante(estudiante);
    }

    public List<Estudiante> mostrarTodosLosEstudiantes() {
        return estudianteDao.obtenerTodosLosEstudiantes();
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del estudiante debe ser mayor a 0.");
        }
        return estudianteDao.obtenerEstudiantePorId(id);
    }

    public void actualizarEstudiante(Estudiante estudiante) {
        if (estudiante == null || estudiante.getId() == null || estudiante.getId() <= 0) {
            throw new IllegalArgumentException("El ID del estudiante es requerido y debe ser mayor a 0.");
        }

        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }

        estudianteDao.actualizarEstudiante(estudiante);
    }

    public void eliminarEstudiante(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del estudiante debe ser mayor a 0.");
        }
        estudianteDao.eliminarEstudiante(id);
    }

}
