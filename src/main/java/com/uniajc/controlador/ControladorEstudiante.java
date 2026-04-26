package com.uniajc.controlador;

import java.util.ArrayList;
import java.util.List;

import com.uniajc.modelo.Estudiante;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.VistaEstudiante;

public class ControladorEstudiante {

    private VistaEstudiante vistaEstudiante;
    private EstudianteService estudianteService;

    public ControladorEstudiante(VistaEstudiante vistaEstudiante, EstudianteService estudianteService) {
        this.vistaEstudiante = vistaEstudiante;
        this.estudianteService = estudianteService;
    }

    public void registrarEstudiante() {
        // Lógica para registrar un estudiante

        Estudiante nuevoEstudiante = vistaEstudiante.solicitarDatosEstudiante();

        estudianteService.registrarEstudiante(nuevoEstudiante);
        vistaEstudiante.mostrarMensaje("Estudiante registrado exitosamente.");       
    }

    public void mostrarTodosLosEstudiantes() {
        // Lógica para mostrar todos los estudiantes
        vistaEstudiante.mostrarTodosLosEstudiantes(estudianteService.mostrarTodosLosEstudiantes());
    }

}
