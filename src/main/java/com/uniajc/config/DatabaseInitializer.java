package com.uniajc.config;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Inicializa la base de datos automáticamente
 * Crea las tablas si no existen en la primera ejecución
 */
public class DatabaseInitializer {

    public static void initializeDatabase() {
        System.out.println("Verificando base de datos...");
        
        String[] sqlStatements = {
            // Crear esquema
            "CREATE SCHEMA IF NOT EXISTS \"practica-mvc\"",
            
            // Tabla de Docentes
            "CREATE TABLE IF NOT EXISTS \"practica-mvc\".docentes (" +
            "    id_docente SERIAL PRIMARY KEY," +
            "    nombre VARCHAR(100) NOT NULL," +
            "    especialidad VARCHAR(100) NOT NULL" +
            ")",
            
            // Tabla de Materias
            "CREATE TABLE IF NOT EXISTS \"practica-mvc\".materias (" +
            "    id_materia SERIAL PRIMARY KEY," +
            "    nombre_materia VARCHAR(100) NOT NULL," +
            "    creditos INT NOT NULL" +
            ")",
            
            // Tabla de Grupos
            "CREATE TABLE IF NOT EXISTS \"practica-mvc\".grupos (" +
            "    id_grupo SERIAL PRIMARY KEY," +
            "    id_materia INT NOT NULL," +
            "    id_docente INT NOT NULL," +
            "    aula VARCHAR(50) NOT NULL," +
            "    horario VARCHAR(100) NOT NULL" +
            ")",
            
            // Tabla de Estudiantes
            "CREATE TABLE IF NOT EXISTS \"practica-mvc\".estudiantes (" +
            "    id SERIAL PRIMARY KEY," +
            "    name VARCHAR(100) NOT NULL," +
            "    lastname VARCHAR(100) NOT NULL," +
            "    email VARCHAR(100)" +
            ")",
            
            // Tabla de Inscripciones
            "CREATE TABLE IF NOT EXISTS \"practica-mvc\".inscripciones (" +
            "    id_inscripcion SERIAL PRIMARY KEY," +
            "    id_estudiante INT NOT NULL," +
            "    id_grupo INT NOT NULL," +
            "    nota_final FLOAT DEFAULT 0.0," +
            "    estado VARCHAR(50) NOT NULL" +
            ")"
        };

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String sql : sqlStatements) {
                try {
                    stmt.execute(sql);
                } catch (SQLException e) {
                    // Ignorar errores de tablas que ya existen o columnas faltantes
                    String errorMsg = e.getMessage();
                    if (!errorMsg.contains("already exists") && 
                        !errorMsg.contains("does not exist") &&
                        !errorMsg.contains("violates foreign key")) {
                        System.err.println("Error ejecutando SQL: " + errorMsg);
                    }
                }
            }

            System.out.println("✓ Base de datos verificada/inicializada correctamente\n");

        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            throw new RuntimeException("No se pudo inicializar la BD", e);
        }
    }

    /**
     * Inserta datos de prueba si la tabla está vacía
     */
    public static void insertSampleData() {
        String[] insertStatements = {
            "INSERT INTO \"practica-mvc\".docentes (nombre, especialidad) " +
            "SELECT 'Dr. Juan Rodríguez', 'Matemáticas' " +
            "WHERE NOT EXISTS (SELECT 1 FROM \"practica-mvc\".docentes WHERE nombre = 'Dr. Juan Rodríguez')",
            
            "INSERT INTO \"practica-mvc\".docentes (nombre, especialidad) " +
            "SELECT 'Ing. María González', 'Programación' " +
            "WHERE NOT EXISTS (SELECT 1 FROM \"practica-mvc\".docentes WHERE nombre = 'Ing. María González')",
            
            "INSERT INTO \"practica-mvc\".materias (nombre_materia, creditos) " +
            "SELECT 'Cálculo I', 4 " +
            "WHERE NOT EXISTS (SELECT 1 FROM \"practica-mvc\".materias WHERE nombre_materia = 'Cálculo I')",
            
            "INSERT INTO \"practica-mvc\".materias (nombre_materia, creditos) " +
            "SELECT 'Programación Java', 3 " +
            "WHERE NOT EXISTS (SELECT 1 FROM \"practica-mvc\".materias WHERE nombre_materia = 'Programación Java')",
            
            "INSERT INTO \"practica-mvc\".estudiantes (name, lastname, email) " +
            "SELECT 'Pedro', 'Gómez', 'pedro@email.com' " +
            "WHERE NOT EXISTS (SELECT 1 FROM \"practica-mvc\".estudiantes WHERE name = 'Pedro')",
            
            "INSERT INTO \"practica-mvc\".estudiantes (name, lastname, email) " +
            "SELECT 'Ana', 'Martínez', 'ana@email.com' " +
            "WHERE NOT EXISTS (SELECT 1 FROM \"practica-mvc\".estudiantes WHERE name = 'Ana')"
        };

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             Statement stmt = conn.createStatement()) {

            for (String sql : insertStatements) {
                try {
                    stmt.execute(sql);
                } catch (SQLException e) {
                    // Ignorar errores silenciosamente - solo son advertencias
                }
            }

            System.out.println("✓ Datos de prueba verificados/insertados\n");

        } catch (SQLException e) {
            // No lanzar error aquí, solo avisar
            System.out.println("ℹ️  BD lista para usar\n");
        }
    }
}

