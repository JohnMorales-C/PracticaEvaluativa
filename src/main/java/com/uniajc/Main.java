package com.uniajc;

import java.sql.Connection;
import java.util.Scanner;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.config.DatabaseInitializer;

// Controladores
import com.uniajc.controlador.ControladorEstudiante;
import com.uniajc.controlador.ControladorDocente;
import com.uniajc.controlador.ControladorMateria;
import com.uniajc.controlador.ControladorGrupo;
import com.uniajc.controlador.ControladorInscripcion_Curso;

// DAO
import com.uniajc.dao.EstudianteDao;
import com.uniajc.dao.DocenteDao;
import com.uniajc.dao.MateriaDao;
import com.uniajc.dao.GrupoDao;
import com.uniajc.dao.Inscripcion_CursoDao;

// Servicios
import com.uniajc.servicios.EstudianteService;
import com.uniajc.servicios.DocenteService;
import com.uniajc.servicios.MateriaService;
import com.uniajc.servicios.GrupoService;
import com.uniajc.servicios.Inscripcion_CursoService;

// Vistas
import com.uniajc.vista.VistaEstudiante;
import com.uniajc.vista.VistaDocente;
import com.uniajc.vista.VistaMateria;
import com.uniajc.vista.VistaGrupo;
import com.uniajc.vista.VistaInscripcion_Curso;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Conexión inicial
        try {
            Connection testConn = ConexionPostgresDatabase.getConnection();
            testConn.close();
            System.out.println("✓ Conexión a BD exitosa\n");
        } catch (Exception e) {
            System.out.println("❌ ERROR: No se puede conectar a PostgreSQL");
            return;
        }

        try {
            DatabaseInitializer.initializeDatabase();
            DatabaseInitializer.insertSampleData();
        } catch (Exception e) {
            System.out.println("⚠️ Advertencia al inicializar BD: " + e.getMessage());
        }

        // Instancias de controladores
        ControladorEstudiante controladorEstudiante = new ControladorEstudiante(new VistaEstudiante(), new EstudianteService(new EstudianteDao()));
        ControladorDocente controladorDocente = new ControladorDocente(new VistaDocente(), new DocenteService(new DocenteDao()));
        ControladorMateria controladorMateria = new ControladorMateria(new VistaMateria(), new MateriaService(new MateriaDao()));
        ControladorGrupo controladorGrupo = new ControladorGrupo(new VistaGrupo(), new GrupoService(new GrupoDao()));
        ControladorInscripcion_Curso controladorInscripcion = new ControladorInscripcion_Curso(new VistaInscripcion_Curso(), new Inscripcion_CursoService(new Inscripcion_CursoDao()));

        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de Estudiantes");
            System.out.println("2. Gestión de Docentes");
            System.out.println("3. Gestión de Materias");
            System.out.println("4. Gestión de Grupos");
            System.out.println("5. Gestión de Inscripciones");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: menuEstudiantes(scanner, controladorEstudiante); break;
                case 2: menuDocentes(scanner, controladorDocente); break;
                case 3: menuMaterias(scanner, controladorMateria); break;
                case 4: menuGrupos(scanner, controladorGrupo); break;
                case 5: menuInscripciones(scanner, controladorInscripcion); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opción inválida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    // Submenús
    private static void menuEstudiantes(Scanner scanner, ControladorEstudiante controlador) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Estudiantes ---");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Ver Estudiantes");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: controlador.registrarEstudiante(); break;
                case 2: controlador.mostrarTodosLosEstudiantes(); break;
            }
        } while (opcion != 0);
    }

    private static void menuDocentes(Scanner scanner, ControladorDocente controlador) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Docentes ---");
            System.out.println("1. Registrar Docente");
            System.out.println("2. Ver Docentes");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: controlador.registrarDocente(); break;
                case 2: controlador.mostrarTodosLosDocentes(); break;
            }
        } while (opcion != 0);
    }

    private static void menuMaterias(Scanner scanner, ControladorMateria controlador) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Materias ---");
            System.out.println("1. Registrar Materia");
            System.out.println("2. Ver Materias");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: controlador.registrarMateria(); break;
                case 2: controlador.mostrarTodasLasMaterias(); break;
            }
        } while (opcion != 0);
    }

    private static void menuGrupos(Scanner scanner, ControladorGrupo controlador) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Grupos ---");
            System.out.println("1. Registrar Grupo");
            System.out.println("2. Ver Grupos");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: controlador.registrarGrupo(); break;
                case 2: controlador.mostrarTodosLosGrupos(); break;
            }
        } while (opcion != 0);
    }

    private static void menuInscripciones(Scanner scanner, ControladorInscripcion_Curso controlador) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Inscripciones ---");
            System.out.println("1. Registrar Inscripción");
            System.out.println("2. Ver Inscripciones");
            System.out.println("0. Volver");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1: controlador.registrarInscripcion(); break;
                case 2: controlador.mostrarTodasLasInscripciones(); break;
            }
        } while (opcion != 0);
    }
}

