package com.uniajc.controlador.swing;

import java.util.List;

import com.uniajc.modelo.Estudiante;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.swing.VistaEstudianteSwing;

/**
 * Controlador Swing para Estudiante
 * Versión específica para interfaz gráfica
 */
public class ControladorEstudianteSwing {

    private VistaEstudianteSwing vistaEstudiante;
    private EstudianteService estudianteService;

    public ControladorEstudianteSwing(VistaEstudianteSwing vistaEstudiante, EstudianteService estudianteService) {
        this.vistaEstudiante = vistaEstudiante;
        this.estudianteService = estudianteService;
    }

    public void registrarEstudiante() {
        Estudiante nuevoEstudiante = vistaEstudiante.obtenerDatosEstudiante();
        
        if (nuevoEstudiante == null) {
            return;
        }
        
        try {
            estudianteService.registrarEstudiante(nuevoEstudiante);
            vistaEstudiante.mostrarMensaje("Estudiante registrado exitosamente.");
        } catch (IllegalArgumentException e) {
            vistaEstudiante.mostrarError(e.getMessage());
        }
    }

    public void mostrarTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.mostrarTodosLosEstudiantes();
        vistaEstudiante.mostrarEstudiantes(estudiantes);
    }
}
