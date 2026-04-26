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
        System.out.println("Registrando los datos de la materia...");

        System.out.println("Ingrese el nombre de la materia:");
        String nombreMateria = scanner.nextLine();

        System.out.println("Ingrese los créditos de la materia:");
        int creditos = Integer.parseInt(scanner.nextLine());

        return new Materia(0, nombreMateria, creditos);
    }

    public void mostrarDetallesMateria(Materia materia) {
        System.out.println("ID: " + materia.getIdMateria());
        System.out.println("Nombre: " + materia.getNombreMateria());
        System.out.println("Créditos: " + materia.getCreditos());
        System.out.println();
    }

    public void mostrarTodasLasMaterias(List<Materia> materias) {
        System.out.println("Lista de Materias: ");
        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas.");
        } else {
            for (Materia materia : materias) {
                mostrarDetallesMateria(materia);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
