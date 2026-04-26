package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.Inscripcion_CursoDao;
import com.uniajc.modelo.Inscripcion_Curso;

/**
 * Tests unitarios para Inscripcion_CursoService
 * Estos tests se enfocan en validar la lógica de negocio sin dependencia de BD
 */
@DisplayName("Pruebas del Servicio de Inscripción - Validación")
class Inscripcion_CursoServiceValidationTest {

    private Inscripcion_CursoService inscripcionService;

    @BeforeEach
    void setUp() {
        Inscripcion_CursoDao inscripcionDao = new Inscripcion_CursoDao();
        inscripcionService = new Inscripcion_CursoService(inscripcionDao);
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si la inscripción es nula")
    void testRegistrarInscripcionNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(null);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID de estudiante es inválido")
    void testRegistrarInscripcionSinEstudiante() {
        Inscripcion_Curso inscripcion1 = new Inscripcion_Curso(0, null, 1, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion1);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(0, 0, 1, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID de grupo es inválido")
    void testRegistrarInscripcionSinGrupo() {
        Inscripcion_Curso inscripcion1 = new Inscripcion_Curso(0, 1, null, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion1);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(0, 1, 0, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el estado es nulo o vacío")
    void testRegistrarInscripcionSinEstado() {
        Inscripcion_Curso inscripcion1 = new Inscripcion_Curso(0, 1, 1, 0.0f, null);
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion1);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(0, 1, 1, 0.0f, "");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si la nota final está fuera del rango 0-5")
    void testActualizarInscripcionNotaInvalida() {
        Inscripcion_Curso inscripcion1 = new Inscripcion_Curso(1, 1, 1, -1.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.actualizarInscripcion(inscripcion1);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(1, 1, 1, 6.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.actualizarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID de estudiante es inválido para búsqueda")
    void testObtenerInscripcionesPorEstudianteInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.obtenerInscripcionesPorEstudiante(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.obtenerInscripcionesPorEstudiante(-5);
        });
    }
}
