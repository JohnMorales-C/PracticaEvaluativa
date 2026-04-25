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
        System.out.println("\nRegistrando los datos del docente...");

        System.out.print("Ingrese el nombre del docente: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la especialidad del docente: ");
        String especialidad = scanner.nextLine();

        return new Docente(0, nombre, especialidad);
    }

    public void mostrarDetallesDocente(Docente docente) {
        System.out.println("------------------------------");
        System.out.println("Id: " + docente.getIdDocente());
        System.out.println("Nombre: " + docente.getNombre());
        System.out.println("Especialidad: " + docente.getEspecialidad());
    }

    public void mostrarTodosLosDocentes(List<Docente> docentes) {
        System.out.println("\nLista de Docentes:");
        if (docentes == null || docentes.isEmpty()) {
            System.out.println("No hay docentes registrados todavía.");
            return;
        }

        for (Docente docente : docentes) {
            mostrarDetallesDocente(docente);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}