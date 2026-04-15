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

        if (estudiante.getNombre() == null || estudiante.getApellido() == null) {
            throw new IllegalArgumentException("Los campos nombre y apellido del estudiante son obligatorios.");
        }

        estudianteDao.guardarEstudiante(estudiante);
    }

    public List<Estudiante> mostrarTodosLosEstudiantes() {
        return estudianteDao.obtenerTodosLosEstudiantes();
    }

}
