package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Estudiante;

public class VistaEstudiante {

    private Scanner scanner;

    public VistaEstudiante() {
        this.scanner = new Scanner(System.in);
    }

    public int mostrarMenuYLeerOpcion() {
        System.out.println("\n--- MENÚ ESTUDIANTES ---");
        System.out.println("1. Mostrar todos los estudiantes");
        System.out.println("2. Registrar nuevo estudiante");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");

        String linea = scanner.nextLine();
        try {
            return Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Estudiante solicitarDatosEstudiante() {
        System.out.println("\nRegistrando los datos del estudiante...");

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el apellido del estudiante: ");
        String apellido = scanner.nextLine();

        System.out.print("Ingrese el correo del estudiante: ");
        String correo = scanner.nextLine();

        return new Estudiante(0, nombre, apellido, correo);
    }

    public void mostrarDetallesEstudiante(Estudiante estudiante) {
        System.out.println("------------------------------");
        System.out.println("Id: " + estudiante.getId());
        System.out.println("Nombre: " + estudiante.getNombre());
        System.out.println("Apellido: " + estudiante.getApellido());
        System.out.println("Correo: " + estudiante.getCorreo());
    }

    public void mostrarTodosLosEstudiantes(List<Estudiante> estudiantes) {
        System.out.println("\nLista de Estudiantes:");
        if (estudiantes == null || estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados todavía.");
            return;
        }

        for (Estudiante estudiante : estudiantes) {
            mostrarDetallesEstudiante(estudiante);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
