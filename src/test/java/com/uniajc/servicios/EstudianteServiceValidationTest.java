package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;

/**
 * Tests unitarios para EstudianteService
 * Estos tests se enfocan en validar la lógica de negocio sin dependencia de BD
 */
@DisplayName("Pruebas del Servicio de Estudiante - Validación")
class EstudianteServiceValidationTest {

    private EstudianteService estudianteService;

    @BeforeEach
    void setUp() {
        // Usamos un DAO mock que no intenta conectarse a BD
        EstudianteDao estudianteDao = new EstudianteDao();
        estudianteService = new EstudianteService(estudianteDao);
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el estudiante es nulo")
    void testRegistrarEstudianteNulo() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(null);
        });
        assertEquals("El estudiante no puede ser nulo.", exception.getMessage());
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el nombre es nulo")
    void testRegistrarEstudianteSinNombre() {
        Estudiante estudiante = new Estudiante(0, null, "Pérez", "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el apellido es nulo")
    void testRegistrarEstudianteSinApellido() {
        Estudiante estudiante = new Estudiante(0, "Juan", null, "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el nombre está vacío")
    void testRegistrarEstudianteNombreVacio() {
        Estudiante estudiante = new Estudiante(0, "", "Pérez", "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.registrarEstudiante(estudiante);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID para obtener es 0 o negativo")
    void testObtenerEstudiantePorIdInvalido() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.obtenerEstudiantePorId(0);
        });
        assertEquals("El ID del estudiante debe ser mayor a 0.", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.obtenerEstudiantePorId(-5);
        });
        assertEquals("El ID del estudiante debe ser mayor a 0.", exception2.getMessage());
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si actualizar con ID inválido")
    void testActualizarEstudianteIDInvalido() {
        Estudiante estudiante = new Estudiante(0, "Juan", "Pérez", "test@email.com");
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.actualizarEstudiante(estudiante);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción al eliminar con ID inválido")
    void testEliminarEstudianteIDInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            estudianteService.eliminarEstudiante(-1);
        });
    }
}
