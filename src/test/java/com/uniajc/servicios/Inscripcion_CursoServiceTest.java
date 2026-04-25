package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.Inscripcion_CursoDao;
import com.uniajc.modelo.Inscripcion_Curso;

@DisplayName("Pruebas del Servicio de Inscripción de Curso")
class Inscripcion_CursoServiceTest {

    private Inscripcion_CursoService inscripcionService;
    private Inscripcion_CursoDao inscripcionDao;

    @BeforeEach
    void setUp() {
        inscripcionDao = new Inscripcion_CursoDao();
        inscripcionService = new Inscripcion_CursoService(inscripcionDao);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la inscripción es nula")
    void testRegistrarInscripcionNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(null);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID de estudiante es inválido")
    void testRegistrarInscripcionSinEstudiante() {
        Inscripcion_Curso inscripcion = new Inscripcion_Curso(0, null, 1, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(0, 0, 1, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID de grupo es inválido")
    void testRegistrarInscripcionSinGrupo() {
        Inscripcion_Curso inscripcion = new Inscripcion_Curso(0, 1, null, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(0, 1, 0, 0.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el estado es nulo o vacío")
    void testRegistrarInscripcionSinEstado() {
        Inscripcion_Curso inscripcion = new Inscripcion_Curso(0, 1, 1, 0.0f, null);
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(0, 1, 1, 0.0f, "");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.registrarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("Debe registrar una inscripción válida")
    void testRegistrarInscripcionValida() {
        Inscripcion_Curso inscripcion = new Inscripcion_Curso(0, 1, 1, 0.0f, "Activo");
        assertDoesNotThrow(() -> {
            inscripcionService.registrarInscripcion(inscripcion);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si la nota final está fuera del rango 0-5")
    void testActualizarInscripcionNotaInvalida() {
        Inscripcion_Curso inscripcion = new Inscripcion_Curso(1, 1, 1, -1.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.actualizarInscripcion(inscripcion);
        });

        Inscripcion_Curso inscripcion2 = new Inscripcion_Curso(1, 1, 1, 6.0f, "Activo");
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.actualizarInscripcion(inscripcion2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID de estudiante es menor o igual a 0 para obtener por ID")
    void testObtenerInscripcionesPorEstudianteInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.obtenerInscripcionesPorEstudiante(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            inscripcionService.obtenerInscripcionesPorEstudiante(-1);
        });
    }
}
