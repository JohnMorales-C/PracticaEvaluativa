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
        System.out.println("Registrando inscripción del estudiante...");

        System.out.println("Ingrese el ID del estudiante:");
        int idEstudiante = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el ID del grupo:");
        int idGrupo = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el estado (Activo/Inactivo/Completado):");
        String estado = scanner.nextLine();

        return new Inscripcion_Curso(0, idEstudiante, idGrupo, 0.0f, estado);
    }

    public void solicitarNotaFinal(Inscripcion_Curso inscripcion) {
        System.out.println("Ingrese la nota final (0-5):");
        try {
            float nota = Float.parseFloat(scanner.nextLine());
            if (nota >= 0 && nota <= 5) {
                inscripcion.setNotaFinal(nota);
            } else {
                System.out.println("La nota debe estar entre 0 y 5.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Formato de nota inválido.");
        }
    }

    public void mostrarDetallesInscripcion(Inscripcion_Curso inscripcion) {
        System.out.println("ID Inscripción: " + inscripcion.getIdInscripcion());
        System.out.println("ID Estudiante: " + inscripcion.getIdEstudiante());
        System.out.println("ID Grupo: " + inscripcion.getIdGrupo());
        System.out.println("Nota Final: " + inscripcion.getNotaFinal());
        System.out.println("Estado: " + inscripcion.getEstado());
        System.out.println();
    }

    public void mostrarTodasLasInscripciones(List<Inscripcion_Curso> inscripciones) {
        System.out.println("Lista de Inscripciones: ");
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
        } else {
            for (Inscripcion_Curso inscripcion : inscripciones) {
                mostrarDetallesInscripcion(inscripcion);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
