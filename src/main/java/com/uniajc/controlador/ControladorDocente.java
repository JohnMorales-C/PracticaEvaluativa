package com.uniajc.controlador;

import java.util.List;

import com.uniajc.modelo.Docente;
import com.uniajc.servicios.DocenteService;
import com.uniajc.vista.VistaDocente;

public class ControladorDocente {

    private VistaDocente vistaDocente;
    private DocenteService docenteService;

    public ControladorDocente(VistaDocente vistaDocente, DocenteService docenteService) {
        this.vistaDocente = vistaDocente;
        this.docenteService = docenteService;
    }

    public void registrarDocente() {
        Docente nuevoDocente = vistaDocente.solicitarDatosDocente();
        docenteService.registrarDocente(nuevoDocente);
        vistaDocente.mostrarMensaje("Docente registrado exitosamente.");
    }

    public void mostrarTodosLosDocentes() {
        vistaDocente.mostrarTodosLosDocentes(docenteService.mostrarTodosLosDocentes());
    }
}