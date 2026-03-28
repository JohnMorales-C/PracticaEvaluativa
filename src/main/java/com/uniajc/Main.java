package com.uniajc;

import com.uniajc.controlador.ControladorEstudiante;
import com.uniajc.modelo.Estudiante;
import com.uniajc.vista.VistaEstudiante;

public class Main {
    public static void main(String[] args) {
        System.out.println("Practica MVC - UNIAJC");

        // System.out.println("Creando el modelo...");
        Estudiante estudiante = new Estudiante("Juan Perez", 20);
        
        // System.out.println("Creando la vista...");
        VistaEstudiante vista = new VistaEstudiante();

        // System.out.println("Creando el controlador...");
        ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

        // System.out.println("Actualizando la vista...");
        controlador.actualizarVista();
        
        // System.out.println("Modificando el modelo...");
        estudiante.setNombre("María López");
        estudiante.setEdad(22);

        // System.out.println("Actualizando la vista después de modificar el modelo...");
        controlador.actualizarVista();

    }
}