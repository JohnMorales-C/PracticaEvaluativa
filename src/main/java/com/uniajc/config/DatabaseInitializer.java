package com.uniajc.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {
        System.out.println("Verificando base de datos...\n");

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             Statement stmt = conn.createStatement()) {

            boolean isPostgres = isPostgres(conn);
            String[] createTables = getCreateTableStatements(isPostgres);

            for (String sql : createTables) {
                stmt.execute(sql);
            }

            System.out.println("✓ Base de datos verificada/inicializada correctamente\n");
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            throw new RuntimeException("No se pudo inicializar la BD", e);
        }
    }

    private static boolean isPostgres(Connection conn) throws SQLException {
        String url = conn.getMetaData().getURL();
        return url != null && url.startsWith("jdbc:postgresql:");
    }

    private static String[] getCreateTableStatements(boolean isPostgres) {
        if (isPostgres) {
            return new String[] {
                "CREATE TABLE IF NOT EXISTS estudiantes (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "lastname TEXT NOT NULL, " +
                    "email TEXT" +
                    ");",

                "CREATE TABLE IF NOT EXISTS docentes (" +
                    "id_docente SERIAL PRIMARY KEY, " +
                    "nombre TEXT NOT NULL, " +
                    "especialidad TEXT NOT NULL" +
                    ");",

                "CREATE TABLE IF NOT EXISTS materias (" +
                    "id_materia SERIAL PRIMARY KEY, " +
                    "nombre_materia TEXT NOT NULL, " +
                    "creditos INTEGER NOT NULL" +
                    ");",

                "CREATE TABLE IF NOT EXISTS grupos (" +
                    "id_grupo SERIAL PRIMARY KEY, " +
                    "id_materia INTEGER NOT NULL, " +
                    "id_docente INTEGER NOT NULL, " +
                    "aula TEXT NOT NULL, " +
                    "horario TEXT NOT NULL, " +
                    "FOREIGN KEY (id_materia) REFERENCES materias(id_materia), " +
                    "FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)" +
                    ");",

                "CREATE TABLE IF NOT EXISTS inscripciones_curso (" +
                    "id_inscripcion SERIAL PRIMARY KEY, " +
                    "id_estudiante INTEGER NOT NULL, " +
                    "id_grupo INTEGER NOT NULL, " +
                    "nota_final REAL, " +
                    "estado TEXT DEFAULT 'Activa', " +
                    "FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id), " +
                    "FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo)" +
                    ");"
            };
        }

        return new String[] {
            "CREATE TABLE IF NOT EXISTS estudiantes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "lastname TEXT NOT NULL, " +
                "email TEXT" +
                ");",

            "CREATE TABLE IF NOT EXISTS docentes (" +
                "id_docente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "especialidad TEXT NOT NULL" +
                ");",

            "CREATE TABLE IF NOT EXISTS materias (" +
                "id_materia INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_materia TEXT NOT NULL, " +
                "creditos INTEGER NOT NULL" +
                ");",

            "CREATE TABLE IF NOT EXISTS grupos (" +
                "id_grupo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_materia INTEGER NOT NULL, " +
                "id_docente INTEGER NOT NULL, " +
                "aula TEXT NOT NULL, " +
                "horario TEXT NOT NULL, " +
                "FOREIGN KEY (id_materia) REFERENCES materias(id_materia), " +
                "FOREIGN KEY (id_docente) REFERENCES docentes(id_docente)" +
                ");",

            "CREATE TABLE IF NOT EXISTS inscripciones_curso (" +
                "id_inscripcion INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "id_estudiante INTEGER NOT NULL, " +
                "id_grupo INTEGER NOT NULL, " +
                "nota_final REAL, " +
                "estado TEXT DEFAULT 'Activa', " +
                "FOREIGN KEY (id_estudiante) REFERENCES estudiantes(id), " +
                "FOREIGN KEY (id_grupo) REFERENCES grupos(id_grupo)" +
                ");"
        };
    }

    public static void insertSampleData() {
        try (Connection conn = ConexionPostgresDatabase.getConnection();
             Statement stmt = conn.createStatement()) {

            // Insertar estudiantes si no existen
            String checkEstudiantes = "SELECT COUNT(1) AS total FROM estudiantes;";
            var rs = stmt.executeQuery(checkEstudiantes);
            int totalEstudiantes = 0;
            if (rs.next()) {
                totalEstudiantes = rs.getInt("total");
            }
            if (totalEstudiantes == 0) {
                String insertEstudiantes = "INSERT INTO estudiantes (name, lastname, email) VALUES " +
                        "('Pedro', 'Gómez', 'pedro@email.com'), " +
                        "('Ana', 'Martínez', 'ana@email.com'), " +
                        "('Carlos', 'Rodríguez', 'carlos@email.com');";
                stmt.executeUpdate(insertEstudiantes);
            }

            // Insertar docentes si no existen
            String checkDocentes = "SELECT COUNT(1) AS total FROM docentes;";
            rs = stmt.executeQuery(checkDocentes);
            int totalDocentes = 0;
            if (rs.next()) {
                totalDocentes = rs.getInt("total");
            }
            if (totalDocentes == 0) {
                String insertDocentes = "INSERT INTO docentes (nombre, especialidad) VALUES " +
                        "('Dr. Juan Pérez', 'Matemáticas'), " +
                        "('Dra. María López', 'Física'), " +
                        "('Prof. Carlos Sánchez', 'Programación');";
                stmt.executeUpdate(insertDocentes);
            }

            // Insertar materias si no existen
            String checkMaterias = "SELECT COUNT(1) AS total FROM materias;";
            rs = stmt.executeQuery(checkMaterias);
            int totalMaterias = 0;
            if (rs.next()) {
                totalMaterias = rs.getInt("total");
            }
            if (totalMaterias == 0) {
                String insertMaterias = "INSERT INTO materias (nombre_materia, creditos) VALUES " +
                        "('Cálculo I', 3), " +
                        "('Física Mecánica', 4), " +
                        "('Programación Orientada a Objetos', 3);";
                stmt.executeUpdate(insertMaterias);
            }

            // Insertar grupos si no existen
            String checkGrupos = "SELECT COUNT(1) AS total FROM grupos;";
            rs = stmt.executeQuery(checkGrupos);
            int totalGrupos = 0;
            if (rs.next()) {
                totalGrupos = rs.getInt("total");
            }
            if (totalGrupos == 0) {
                String insertGrupos = "INSERT INTO grupos (id_materia, id_docente, aula, horario) VALUES " +
                        "(1, 1, 'Aula 101', 'Lunes 8:00-10:00'), " +
                        "(2, 2, 'Aula 202', 'Martes 10:00-12:00'), " +
                        "(3, 3, 'Aula 303', 'Miércoles 14:00-16:00');";
                stmt.executeUpdate(insertGrupos);
            }

            // Insertar inscripciones si no existen
            String checkInscripciones = "SELECT COUNT(1) AS total FROM inscripciones_curso;";
            rs = stmt.executeQuery(checkInscripciones);
            int totalInscripciones = 0;
            if (rs.next()) {
                totalInscripciones = rs.getInt("total");
            }
            if (totalInscripciones == 0) {
                String insertInscripciones = "INSERT INTO inscripciones_curso (id_estudiante, id_grupo, estado) VALUES " +
                        "(1, 1, 'Activa'), " +
                        "(2, 2, 'Activa'), " +
                        "(3, 3, 'Activa');";
                stmt.executeUpdate(insertInscripciones);
            }

            System.out.println("✓ Datos de prueba verificados/insertados\n");
        } catch (SQLException e) {
            System.out.println("ℹ️  BD lista para usar\n");
        }
    }
}
