package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Inscripcion_Curso;

public class VistaInscripcion_Curso {

    private Scanner scanner;

    public VistaInscripcion_Curso() {
        this.scanner = new Scanner(System.in);
    }

    public Inscripcion_Curso solicitarDatosInscripcion() {
        System.out.println("\nRegistrando los datos de la inscripción...");

        System.out.print("Ingrese el ID del estudiante: ");
        int idEstudiante = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el ID del grupo: ");
        int idGrupo = Integer.parseInt(scanner.nextLine());

        return new Inscripcion_Curso(0, idEstudiante, idGrupo, null, "Activa");
    }

    public void mostrarDetallesInscripcion(Inscripcion_Curso inscripcion) {
        System.out.println("------------------------------");
        System.out.println("Id: " + inscripcion.getIdInscripcion());
        System.out.println("Id Estudiante: " + inscripcion.getIdEstudiante());
        System.out.println("Id Grupo: " + inscripcion.getIdGrupo());
        System.out.println("Nota Final: " + (inscripcion.getNotaFinal() != null ? inscripcion.getNotaFinal() : "No asignada"));
        System.out.println("Estado: " + inscripcion.getEstado());
    }

    public void mostrarTodasLasInscripciones(List<Inscripcion_Curso> inscripciones) {
        System.out.println("\nLista de Inscripciones:");
        if (inscripciones == null || inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas todavía.");
            return;
        }

        for (Inscripcion_Curso inscripcion : inscripciones) {
            mostrarDetallesInscripcion(inscripcion);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}