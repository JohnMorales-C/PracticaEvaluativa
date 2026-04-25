package com.uniajc.servicios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uniajc.dao.GrupoDao;
import com.uniajc.modelo.Grupo;

@DisplayName("Pruebas del Servicio de Grupo")
class GrupoServiceTest {

    private GrupoService grupoService;
    private GrupoDao grupoDao;

    @BeforeEach
    void setUp() {
        grupoDao = new GrupoDao();
        grupoService = new GrupoService(grupoDao);
    }

    @Test
    @DisplayName("Debe lanzar excepción si el grupo es nulo")
    void testRegistrarGrupoNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(null);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID de materia es inválido")
    void testRegistrarGrupoSinMateria() {
        Grupo grupo = new Grupo(0, null, 1, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo);
        });

        Grupo grupo2 = new Grupo(0, 0, 1, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el ID de docente es inválido")
    void testRegistrarGrupoSinDocente() {
        Grupo grupo = new Grupo(0, 1, null, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo);
        });

        Grupo grupo2 = new Grupo(0, 1, 0, "A101", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el aula es nula o vacía")
    void testRegistrarGrupoSinAula() {
        Grupo grupo = new Grupo(0, 1, 1, null, "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo);
        });

        Grupo grupo2 = new Grupo(0, 1, 1, "", "Lunes 8:00");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("Debe lanzar excepción si el horario es nulo o vacío")
    void testRegistrarGrupoSinHorario() {
        Grupo grupo = new Grupo(0, 1, 1, "A101", null);
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo);
        });

        Grupo grupo2 = new Grupo(0, 1, 1, "A101", "");
        assertThrows(IllegalArgumentException.class, () -> {
            grupoService.registrarGrupo(grupo2);
        });
    }

    @Test
    @DisplayName("Debe registrar un grupo válido")
    void testRegistrarGrupoValido() {
        Grupo grupo = new Grupo(0, 1, 1, "A101", "Lunes 8:00");
        assertDoesNotThrow(() -> {
            grupoService.registrarGrupo(grupo);
        });
    }
}
