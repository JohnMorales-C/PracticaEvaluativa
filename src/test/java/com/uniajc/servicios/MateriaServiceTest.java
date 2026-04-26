package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.MateriaDao;
import com.uniajc.modelo.Materia;

@DisplayName("Pruebas del Servicio de Materia")
class MateriaServiceTest {

    private MateriaService materiaService;
    private MateriaDao materiaDao;

    @BeforeEach
    void setUp() {
        materiaDao = new MateriaDao();
        materiaService = new MateriaService(materiaDao);
    }

    @Test
    @DisplayName("Debe lanzar excepción si la materia es nula")
    void testRegistrarMateriaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(null);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el nombre es nulo o vacío")
    void testRegistrarMateriaSinNombre() {
        Materia materia = new Materia(0, null, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia);
        });

        Materia materia2 = new Materia(0, "", 3);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si los créditos son nulos o menor/igual a 0")
    void testRegistrarMateriaCreditosInvalidos() {
        Materia materia = new Materia(0, "Matemáticas", null);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia);
        });

        Materia materia2 = new Materia(0, "Matemáticas", 0);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia2);
        });

        Materia materia3 = new Materia(0, "Matemáticas", -1);
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.registrarMateria(materia3);
        });
    }

    @Test
    @DisplayName("Debe registrar una materia válida")
    void testRegistrarMateriaValida() {
        Materia materia = new Materia(0, "Matemáticas", 3);
        assertDoesNotThrow(() -> {
            materiaService.registrarMateria(materia);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID es menor o igual a 0")
    void testObtenerMateriaPorIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.obtenerMateriaPorId(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            materiaService.obtenerMateriaPorId(-1);
        });
    }
}
