package com.uniajc;

import java.sql.Connection;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.controlador.ControladorEstudiante;
import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.VistaEstudiante;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Practica MVC - Sistema Académico UNIAJC");
        System.out.println("------------------------------");

        // 1. Crear la instancia de la vista
        VistaEstudiante vistaEstudiante = new VistaEstudiante();

        // 2. Crear la instancia del dao
        EstudianteDao estudianteDao = new EstudianteDao();

        // 3. Crear la instancia del servicio
        EstudianteService estudianteService = new EstudianteService(estudianteDao);

        // 4. Crear la instancia del controlador
        ControladorEstudiante controladorEstudiante = new ControladorEstudiante(vistaEstudiante, estudianteService);

        // Probamos la ejeccucion del flujo de registro de un estudiante

        controladorEstudiante.mostrarTodosLosEstudiantes();

        controladorEstudiante.registrarEstudiante();

        controladorEstudiante.mostrarTodosLosEstudiantes();

    }
}