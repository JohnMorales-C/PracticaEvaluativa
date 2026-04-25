package com.uniajc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.config.DatabaseInitializer;
import com.uniajc.controlador.ControladorDocente;
import com.uniajc.controlador.ControladorEstudiante;
import com.uniajc.controlador.ControladorGrupo;
import com.uniajc.controlador.ControladorInscripcion_Curso;
import com.uniajc.controlador.ControladorMateria;
import com.uniajc.dao.DocenteDao;
import com.uniajc.dao.EstudianteDao;
import com.uniajc.dao.GrupoDao;
import com.uniajc.dao.Inscripcion_CursoDao;
import com.uniajc.dao.MateriaDao;
import com.uniajc.servicios.DocenteService;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.servicios.GrupoService;
import com.uniajc.servicios.Inscripcion_CursoService;
import com.uniajc.servicios.MateriaService;
import com.uniajc.vista.VistaDocente;
import com.uniajc.vista.VistaEstudiante;
import com.uniajc.vista.VistaGrupo;
import com.uniajc.vista.VistaInscripcion_Curso;
import com.uniajc.vista.VistaMateria;

public class Main {
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Practica MVC - Sistema Académico UNIAJC");
        System.out.println("------------------------------\n");

        try {
            Connection testConn = ConexionPostgresDatabase.getConnection();
            testConn.close();
            System.out.println("✓ Conexión a BD exitosa\n");
        } catch (Exception e) {
            System.out.println("\n❌ ERROR: No se puede conectar a la base de datos");
            System.out.println("   " + e.getMessage());
            System.out.println("\n💡 Soluciones:");
            System.out.println("   1. Verifica que el archivo config.properties esté bien configurado");
            System.out.println("   2. Si no lo usas, el programa usará SQLite local automáticamente");
            System.out.println("   3. Comprueba que el archivo de base de datos no esté bloqueado\n");
            return;
        }

        try {
            DatabaseInitializer.initializeDatabase();
            DatabaseInitializer.insertSampleData();
        } catch (Exception e) {
            System.out.println("⚠️  Advertencia al inicializar BD: " + e.getMessage());
        }

        // Verificar conteos de registros
        try (Connection conn = ConexionPostgresDatabase.getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("\n--- CONTEO DE REGISTROS EN BD ---");
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM estudiantes;");
            rs.next();
            System.out.println("Estudiantes: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM docentes;");
            rs.next();
            System.out.println("Docentes: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM materias;");
            rs.next();
            System.out.println("Materias: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM grupos;");
            rs.next();
            System.out.println("Grupos: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM inscripciones_curso;");
            rs.next();
            System.out.println("Inscripciones: " + rs.getInt(1));
            System.out.println("--- FIN CONTEO ---\n");
        } catch (Exception e) {
            System.out.println("Error al verificar conteos: " + e.getMessage());
        }

        // Inicializar vistas
        VistaEstudiante vistaEstudiante = new VistaEstudiante();
        VistaDocente vistaDocente = new VistaDocente();
        VistaMateria vistaMateria = new VistaMateria();
        VistaGrupo vistaGrupo = new VistaGrupo();
        VistaInscripcion_Curso vistaInscripcion = new VistaInscripcion_Curso();

        // Inicializar DAOs
        EstudianteDao estudianteDao = new EstudianteDao();
        DocenteDao docenteDao = new DocenteDao();
        MateriaDao materiaDao = new MateriaDao();
        GrupoDao grupoDao = new GrupoDao();
        Inscripcion_CursoDao inscripcionDao = new Inscripcion_CursoDao();

        // Inicializar servicios
        EstudianteService estudianteService = new EstudianteService(estudianteDao);
        DocenteService docenteService = new DocenteService(docenteDao);
        MateriaService materiaService = new MateriaService(materiaDao);
        GrupoService grupoService = new GrupoService(grupoDao);
        Inscripcion_CursoService inscripcionService = new Inscripcion_CursoService(inscripcionDao);

        // Inicializar controladores
        ControladorEstudiante controladorEstudiante = new ControladorEstudiante(vistaEstudiante, estudianteService);
        ControladorDocente controladorDocente = new ControladorDocente(vistaDocente, docenteService);
        ControladorMateria controladorMateria = new ControladorMateria(vistaMateria, materiaService);
        ControladorGrupo controladorGrupo = new ControladorGrupo(vistaGrupo, grupoService);
        ControladorInscripcion_Curso controladorInscripcion = new ControladorInscripcion_Curso(vistaInscripcion, inscripcionService);

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenuPrincipal();
            int opcionPrincipal = leerOpcion(scanner);

            switch (opcionPrincipal) {
                case 1:
                    menuEstudiantes(controladorEstudiante, scanner);
                    break;
                case 2:
                    menuDocentes(controladorDocente, scanner);
                    break;
                case 3:
                    menuMaterias(controladorMateria, scanner);
                    break;
                case 4:
                    menuGrupos(controladorGrupo, scanner);
                    break;
                case 5:
                    menuInscripciones(controladorInscripcion, scanner);
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo de la aplicación. Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scanner.close();
        vistaEstudiante.cerrarScanner();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n========== MENÚ PRINCIPAL ==========");
        System.out.println("1. Gestión de Estudiantes");
        System.out.println("2. Gestión de Docentes");
        System.out.println("3. Gestión de Materias");
        System.out.println("4. Gestión de Grupos");
        System.out.println("5. Gestión de Inscripciones");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void menuEstudiantes(ControladorEstudiante controlador, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n----- GESTIÓN DE ESTUDIANTES -----");
            System.out.println("1. Ver todos los estudiantes");
            System.out.println("2. Registrar nuevo estudiante");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    controlador.mostrarTodosLosEstudiantes();
                    break;
                case 2:
                    controlador.registrarEstudiante();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuDocentes(ControladorDocente controlador, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n----- GESTIÓN DE DOCENTES -----");
            System.out.println("1. Ver todos los docentes");
            System.out.println("2. Registrar nuevo docente");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    controlador.mostrarTodosLosDocentes();
                    break;
                case 2:
                    controlador.registrarDocente();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuMaterias(ControladorMateria controlador, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n----- GESTIÓN DE MATERIAS -----");
            System.out.println("1. Ver todas las materias");
            System.out.println("2. Registrar nueva materia");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    controlador.mostrarTodasLasMaterias();
                    break;
                case 2:
                    controlador.registrarMateria();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuGrupos(ControladorGrupo controlador, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n----- GESTIÓN DE GRUPOS -----");
            System.out.println("1. Ver todos los grupos");
            System.out.println("2. Registrar nuevo grupo");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    controlador.mostrarTodosLosGrupos();
                    break;
                case 2:
                    controlador.registrarGrupo();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menuInscripciones(ControladorInscripcion_Curso controlador, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n----- GESTIÓN DE INSCRIPCIONES -----");
            System.out.println("1. Ver todas las inscripciones");
            System.out.println("2. Registrar nueva inscripción");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");

            int opcion = leerOpcion(scanner);
            switch (opcion) {
                case 1:
                    controlador.mostrarTodasLasInscripciones();
                    break;
                case 2:
                    controlador.registrarInscripcion();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static int leerOpcion(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
