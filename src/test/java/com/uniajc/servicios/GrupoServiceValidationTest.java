package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.GrupoDao;
import com.uniajc.modelo.Grupo;

/**
 * Tests unitarios para GrupoService
 * Estos tests se enfocan en validar la lógica de negocio sin dependencia de BD
 */
@DisplayName("Pruebas del Servicio de Grupo - Validación")
class GrupoServiceValidationTest {

    private GrupoService grupoService;

    @BeforeEach
    void setUp() {
        GrupoDao grupoDao = new GrupoDao();
        grupoService = new GrupoService(grupoDao);
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el grupo es nulo")
    void testRegistrarGrupoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(null);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID de materia es inválido")
    void testRegistrarGrupoSinMateria() {
        Grupo grupo1 = new Grupo(0, null, 1, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo1);
        });

        Grupo grupo2 = new Grupo(0, 0, 1, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el ID de docente es inválido")
    void testRegistrarGrupoSinDocente() {
        Grupo grupo1 = new Grupo(0, 1, null, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo1);
        });

        Grupo grupo2 = new Grupo(0, 1, 0, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el aula es nula o vacía")
    void testRegistrarGrupoSinAula() {
        Grupo grupo1 = new Grupo(0, 1, 1, null, "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo1);
        });

        Grupo grupo2 = new Grupo(0, 1, 1, "", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("VALIDACIÓN: Debe lanzar excepción si el horario es nulo o vacío")
    void testRegistrarGrupoSinHorario() {
        Grupo grupo1 = new Grupo(0, 1, 1, "A101", null);
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo1);
        });

        Grupo grupo2 = new Grupo(0, 1, 1, "A101", "");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }
}
