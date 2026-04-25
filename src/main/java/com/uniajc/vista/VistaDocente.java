package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Docente;

public class VistaDocente {

    private Scanner scanner;

    public VistaDocente() {
        this.scanner = new Scanner(System.in);
    }

    public Docente solicitarDatosDocente() {
        System.out.println("Registrando los datos del docente...");

        System.out.println("Ingrese el nombre del docente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la especialidad del docente:");
        String especialidad = scanner.nextLine();

        return new Docente(0, nombre, especialidad);
    }

    public void mostrarDetallesDocente(Docente docente) {
        System.out.println("ID: " + docente.getIdDocente());
        System.out.println("Nombre: " + docente.getNombre());
        System.out.println("Especialidad: " + docente.getEspecialidad());
        System.out.println();
    }

    public void mostrarTodosLosDocentes(List<Docente> docentes) {
        System.out.println("Lista de Docentes: ");
        if (docentes.isEmpty()) {
            System.out.println("No hay docentes registrados.");
        } else {
            for (Docente docente : docentes) {
                mostrarDetallesDocente(docente);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
