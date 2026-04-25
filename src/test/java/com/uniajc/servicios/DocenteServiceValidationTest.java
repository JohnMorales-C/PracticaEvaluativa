package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.DocenteDao;
import com.uniajc.modelo.Docente;

/**
 * Tests unitarios para DocenteService
 * Estos tests se enfocan en validar la lógica de negocio sin dependencia de BD
 */
@DisplayName("Pruebas del Servicio de Docente - Validación")
class DocenteServiceValidationTest {

    private DocenteService docenteService;

    @BeforeEach
    void setUp() {
        DocenteDao docenteDao = new DocenteDao();
        docenteService = new DocenteService(docenteDao);
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el docente es nulo")
    void testRegistrarDocenteNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(null);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el nombre es nulo")
    void testRegistrarDocenteSinNombre() {
        Docente docente = new Docente(0, null, "Matemáticas");
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(docente);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si la especialidad es nula")
    void testRegistrarDocenteSinEspecialidad() {
        Docente docente = new Docente(0, "Juan", null);
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(docente);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID para obtener es inválido")
    void testObtenerDocentePorIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.obtenerDocentePorId(0);
        });
    }
}
