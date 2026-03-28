package com.uniajc.controlador;

import java.util.ArrayList;
import java.util.List;

import com.uniajc.modelo.Estudiante;
import com.uniajc.vista.VistaEstudiante;

public class ControladorEstudiante {

    private Estudiante estudiante;
    private List<Estudiante> estudiantes; // Para manejar múltiples estudiantes
    private VistaEstudiante vista;

    public ControladorEstudiante(Estudiante estudiante, VistaEstudiante vista) {
        this.estudiante = estudiante;
        this.vista = vista;
        this.estudiantes = new ArrayList<Estudiante>(); // Inicializar la lista de estudiantes
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

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
        System.out.println("Estudiante agregado: " + estudiante.getNombre());
    }

    public void actualizarVista() {
        vista.mostrarDetallesEstudiante(estudiante);
    }

    public void mostrarTodosLosEstudiantes() {
        vista.mostrarTodosLosEstudiantes(estudiantes);
    }

}
