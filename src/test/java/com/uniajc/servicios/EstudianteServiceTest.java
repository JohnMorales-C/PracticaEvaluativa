package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;

@DisplayName("Pruebas del Servicio de Estudiante")
class EstudianteServiceTest {

    private EstudianteService estudianteService;
    private EstudianteDao estudianteDao;

    @BeforeEach
    void setUp() {
        estudianteDao = new EstudianteDao();
        estudianteService = new EstudianteService(estudianteDao);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el estudiante es nulo")
    void testRegistrarEstudianteNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(null);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre es nulo o vacío")
    void testRegistrarEstudianteSinNombre() {
        Estudiante estudiante = new Estudiante(0, null, "Pérez", "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante);
        });

        Estudiante estudiante2 = new Estudiante(0, "", "Pérez", "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el apellido es nulo o vacío")
    void testRegistrarEstudianteSinApellido() {
        Estudiante estudiante = new Estudiante(0, "Juan", null, "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante);
        });

        Estudiante estudiante2 = new Estudiante(0, "Juan", "", "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante2);
        });
    }

    @Test
    @DisplayName("Debe registrar un estudiante válido")
    void testRegistrarEstudianteValido() {
        Estudiante estudiante = new Estudiante(0, "Juan", "Pérez", "juan@email.com");
        assertDoesNotThrow(() -> {
            estudianteService.registrarEstudiante(estudiante);
        });
    }

    @Test
    @DisplayName("Debe obtener ID válido con valor mayor a 0")
    void testObtenerEstudiantePorIdValido() {
        assertDoesNotThrow(() -> {
            estudianteService.obtenerEstudiantePorId(1);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID es menor o igual a 0")
    void testObtenerEstudiantePorIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.obtenerEstudiantePorId(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.obtenerEstudiantePorId(-1);
        });
    }
}
