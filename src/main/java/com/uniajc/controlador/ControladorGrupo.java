package com.uniajc.controlador;

import java.util.List;

import com.uniajc.modelo.Grupo;
import com.uniajc.servicios.GrupoService;
import com.uniajc.vista.VistaGrupo;

public class ControladorGrupo {

    private VistaGrupo vistaGrupo;
    private GrupoService grupoService;

    public ControladorGrupo(VistaGrupo vistaGrupo, GrupoService grupoService) {
        this.vistaGrupo = vistaGrupo;
        this.grupoService = grupoService;
    }

    public void registrarGrupo() {
        Grupo nuevoGrupo = vistaGrupo.solicitarDatosGrupo();
        grupoService.registrarGrupo(nuevoGrupo);
        vistaGrupo.mostrarMensaje("Grupo registrado exitosamente.");
    }

    public void mostrarTodosLosGrupos() {
        vistaGrupo.mostrarTodosLosGrupos(grupoService.mostrarTodosLosGrupos());
    }
}