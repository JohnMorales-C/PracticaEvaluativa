package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Materia;

public class VistaMateria {

    private Scanner scanner;

    public VistaMateria() {
        this.scanner = new Scanner(System.in);
    }

    public Materia solicitarDatosMateria() {
        System.out.println("\nRegistrando los datos de la materia...");

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreMateria = scanner.nextLine();

        System.out.print("Ingrese los créditos de la materia: ");
        int creditos = Integer.parseInt(scanner.nextLine());

        return new Materia(0, nombreMateria, creditos);
    }

    public void mostrarDetallesMateria(Materia materia) {
        System.out.println("------------------------------");
        System.out.println("Id: " + materia.getIdMateria());
        System.out.println("Nombre: " + materia.getNombreMateria());
        System.out.println("Créditos: " + materia.getCreditos());
    }

    public void mostrarTodasLasMaterias(List<Materia> materias) {
        System.out.println("\nLista de Materias:");
        if (materias == null || materias.isEmpty()) {
            System.out.println("No hay materias registradas todavía.");
            return;
        }

        for (Materia materia : materias) {
            mostrarDetallesMateria(materia);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}