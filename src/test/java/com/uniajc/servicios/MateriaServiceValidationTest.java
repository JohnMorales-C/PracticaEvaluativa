package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.MateriaDao;
import com.uniajc.modelo.Materia;

/**
 * Tests unitarios para MateriaService
 * Estos tests se enfocan en validar la lógica de negocio sin dependencia de BD
 */
@DisplayName("Pruebas del Servicio de Materia - Validación")
class MateriaServiceValidationTest {

    private MateriaService materiaService;

    @BeforeEach
    void setUp() {
        MateriaDao materiaDao = new MateriaDao();
        materiaService = new MateriaService(materiaDao);
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si la materia es nula")
    void testRegistrarMateriaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(null);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el nombre es nulo")
    void testRegistrarMateriaSinNombre() {
        Materia materia = new Materia(0, null, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si los créditos son nulos")
    void testRegistrarMateriaCreditosNulos() {
        Materia materia = new Materia(0, "Matemáticas", null);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si los créditos son 0 o negativos")
    void testRegistrarMateriaCreditosInvalidos() {
        Materia materia1 = new Materia(0, "Matemáticas", 0);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia1);
        });

        Materia materia2 = new Materia(0, "Matemáticas", -1);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID para obtener es inválido")
    void testObtenerMateriaPorIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.obtenerMateriaPorId(0);
        });
    }
}
