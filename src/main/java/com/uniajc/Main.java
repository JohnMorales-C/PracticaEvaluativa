package com.uniajc;

import java.sql.Connection;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.config.DatabaseInitializer;
import com.uniajc.controlador.ControladorEstudiante;
import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.VistaEstudiante;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Practica MVC - Sistema Académico UNIAJC");
        System.out.println("------------------------------\n");

        // Verificar conexión a BD antes de continuar
        try {
            Connection testConn = ConexionPostgresDatabase.getConnection();
            testConn.close();
            System.out.println("✓ Conexión a BD exitosa\n");
        } catch (Exception e) {
            System.out.println("\n❌ ERROR: No se puede conectar a PostgreSQL");
            System.out.println("   " + e.getMessage());
            System.out.println("\n💡 Soluciones:");
            System.out.println("   1. Verifica que Neon esté en línea");
            System.out.println("   2. Revisa las credenciales en config.properties");
            System.out.println("   3. Comprueba tu conexión a internet\n");
            return;
        }

        // Inicializar base de datos (crear tablas si no existen)
        try {
            DatabaseInitializer.initializeDatabase();
            DatabaseInitializer.insertSampleData();
        } catch (Exception e) {
            System.out.println("⚠️  Advertencia al inicializar BD: " + e.getMessage());
        }

        // 1. Crear la instancia de la vista
        VistaEstudiante vistaEstudiante = new VistaEstudiante();

        // 2. Crear la instancia del dao
        EstudianteDao estudianteDao = new EstudianteDao();

        // 3. Crear la instancia del servicio
        EstudianteService estudianteService = new EstudianteService(estudianteDao);

        // 4. Crear la instancia del controlador
        ControladorEstudiante controladorEstudiante = new ControladorEstudiante(vistaEstudiante, estudianteService);

        // Probamos la ejecución del flujo de registro de un estudiante

        controladorEstudiante.mostrarTodosLosEstudiantes();

        controladorEstudiante.registrarEstudiante();

        controladorEstudiante.mostrarTodosLosEstudiantes();

    }
}