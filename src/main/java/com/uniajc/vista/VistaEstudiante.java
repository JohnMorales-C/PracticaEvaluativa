package com.uniajc.vista;

import java.util.List;

import com.uniajc.modelo.Estudiante;

public class VistaEstudiante {

    public void mostrarDetallesEstudiante(Estudiante estudiante) {
        System.out.println("Detalles del Estudiante:");
        System.out.println("Nombre: " + estudiante.getNombre());
        System.out.println("Edad: " + estudiante.getEdad());

    }

    public void mostrarTodosLosEstudiantes(List<Estudiante> estudiantes) {
        System.out.println("Lista de Estudiantes:");
        for (Estudiante estudiante : estudiantes) {
            mostrarDetallesEstudiante(estudiante);
            System.out.println("-------------------");
        }
    }
}
