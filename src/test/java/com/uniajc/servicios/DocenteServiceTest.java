package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.DocenteDao;
import com.uniajc.modelo.Docente;

@DisplayName("Pruebas del Servicio de Docente")
class DocenteServiceTest {

    private DocenteService docenteService;
    private DocenteDao docenteDao;

    @BeforeEach
    void setUp() {
        docenteDao = new DocenteDao();
        docenteService = new DocenteService(docenteDao);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el docente es nulo")
    void testRegistrarDocenteNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(null);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre es nulo o vacío")
    void testRegistrarDocenteSinNombre() {
        Docente docente = new Docente(0, null, "Matemáticas");
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(docente);
        });

        Docente docente2 = new Docente(0, "", "Matemáticas");
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(docente2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si la especialidad es nula o vacía")
    void testRegistrarDocenteSinEspecialidad() {
        Docente docente = new Docente(0, "Juan", null);
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(docente);
        });

        Docente docente2 = new Docente(0, "Juan", "");
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.registrarDocente(docente2);
        });
    }

    @Test
    @DisplayName("Debe registrar un docente válido")
    void testRegistrarDocenteValido() {
        Docente docente = new Docente(0, "Juan", "Matemáticas");
        assertDoesNotThrow(() -> {
            docenteService.registrarDocente(docente);
        });
    }

    @Test
    @DisplayName("Debe obtener ID válido con valor mayor a 0")
    void testObtenerDocentePorIdValido() {
        assertDoesNotThrow(() -> {
            docenteService.obtenerDocentePorId(1);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID es menor o igual a 0")
    void testObtenerDocentePorIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.obtenerDocentePorId(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            docenteService.obtenerDocentePorId(-1);
        });
    }
}
