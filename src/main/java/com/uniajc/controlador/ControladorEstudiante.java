package com.uniajc.controlador;

import com.uniajc.modelo.Estudiante;
import com.uniajc.vista.VistaEstudiante;

public class ControladorEstudiante {

    private Estudiante estudiante;
    private VistaEstudiante vista;

    public ControladorEstudiante(Estudiante estudiante, VistaEstudiante vista) {
        this.estudiante = estudiante;
        this.vista = vista;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public VistaEstudiante getVista() {
        return vista;
    }

    public void setVista(VistaEstudiante vista) {
        this.vista = vista;
    }

    public void actualizarVista() {
        vista.mostrarDetallesEstudiante(estudiante);
    }

}
