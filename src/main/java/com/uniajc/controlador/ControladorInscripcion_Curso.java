package com.uniajc.controlador;

import java.util.List;

import com.uniajc.modelo.Inscripcion_Curso;
import com.uniajc.servicios.Inscripcion_CursoService;
import com.uniajc.vista.VistaInscripcion_Curso;

public class ControladorInscripcion_Curso {

    private VistaInscripcion_Curso vistaInscripcion;
    private Inscripcion_CursoService inscripcionService;

    public ControladorInscripcion_Curso(VistaInscripcion_Curso vistaInscripcion, Inscripcion_CursoService inscripcionService) {
        this.vistaInscripcion = vistaInscripcion;
        this.inscripcionService = inscripcionService;
    }

    public void registrarInscripcion() {
        Inscripcion_Curso nuevaInscripcion = vistaInscripcion.solicitarDatosInscripcion();
        inscripcionService.registrarInscripcion(nuevaInscripcion);
        vistaInscripcion.mostrarMensaje("Inscripción registrada exitosamente.");
    }

    public void mostrarTodasLasInscripciones() {
        List<Inscripcion_Curso> inscripciones = inscripcionService.obtenerTodasLasInscripciones();
        vistaInscripcion.mostrarTodasLasInscripciones(inscripciones);
    }

    public void obtenerInscripcionPorId(int id) {
        Inscripcion_Curso inscripcion = inscripcionService.obtenerInscripcionPorId(id);
        if (inscripcion != null) {
            vistaInscripcion.mostrarDetallesInscripcion(inscripcion);
        } else {
            vistaInscripcion.mostrarMensaje("Inscripción no encontrada.");
        }
    }

    public void mostrarInscripcionesPorEstudiante(int idEstudiante) {
        List<Inscripcion_Curso> inscripciones = inscripcionService.obtenerInscripcionesPorEstudiante(idEstudiante);
        vistaInscripcion.mostrarTodasLasInscripciones(inscripciones);
    }
}
