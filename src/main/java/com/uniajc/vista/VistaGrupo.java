package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Grupo;

public class VistaGrupo {

    private Scanner scanner;

    public VistaGrupo() {
        this.scanner = new Scanner(System.in);
    }

    public Grupo solicitarDatosGrupo() {
        System.out.println("Registrando los datos del grupo...");

        System.out.println("Ingrese el ID de la materia:");
        int idMateria = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el ID del docente:");
        int idDocente = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el aula:");
        String aula = scanner.nextLine();

        System.out.println("Ingrese el horario:");
        String horario = scanner.nextLine();

        return new Grupo(0, idMateria, idDocente, aula, horario);
    }

    public void mostrarDetallesGrupo(Grupo grupo) {
        System.out.println("ID Grupo: " + grupo.getIdGrupo());
        System.out.println("ID Materia: " + grupo.getIdMateria());
        System.out.println("ID Docente: " + grupo.getIdDocente());
        System.out.println("Aula: " + grupo.getAula());
        System.out.println("Horario: " + grupo.getHorario());
        System.out.println();
    }

    public void mostrarTodosLosGrupos(List<Grupo> grupos) {
        System.out.println("Lista de Grupos: ");
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos registrados.");
        } else {
            for (Grupo grupo : grupos) {
                mostrarDetallesGrupo(grupo);
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
