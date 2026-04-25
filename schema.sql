-- Script para crear las tablas en NEON PostgreSQL
-- Ejecuta esto en tu consola Neon o en pgAdmin

-- Crear esquema si no existe
CREATE SCHEMA IF NOT EXISTS "practica-mvc";

-- Tabla de Docentes
CREATE TABLE IF NOT EXISTS "practica-mvc".docentes (
    id_docente SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL
);

-- Tabla de Materias
CREATE TABLE IF NOT EXISTS "practica-mvc".materias (
    id_materia SERIAL PRIMARY KEY,
    nombre_materia VARCHAR(100) NOT NULL,
    creditos INT NOT NULL
);

-- Tabla de Grupos
CREATE TABLE IF NOT EXISTS "practica-mvc".grupos (
    id_grupo SERIAL PRIMARY KEY,
    id_materia INT NOT NULL,
    id_docente INT NOT NULL,
    aula VARCHAR(50) NOT NULL,
    horario VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_materia) REFERENCES "practica-mvc".materias(id_materia),
    FOREIGN KEY (id_docente) REFERENCES "practica-mvc".docentes(id_docente)
);

-- Tabla de Estudiantes
CREATE TABLE IF NOT EXISTS "practica-mvc".estudiantes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100)
);

-- Tabla de Inscripciones
CREATE TABLE IF NOT EXISTS "practica-mvc".inscripciones (
    id_inscripcion SERIAL PRIMARY KEY,
    id_estudiante INT NOT NULL,
    id_grupo INT NOT NULL,
    nota_final FLOAT DEFAULT 0.0,
    estado VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_estudiante) REFERENCES "practica-mvc".estudiantes(id),
    FOREIGN KEY (id_grupo) REFERENCES "practica-mvc".grupos(id_grupo)
);

-- Datos de prueba
INSERT INTO "practica-mvc".docentes (nombre, especialidad) 
VALUES 
    ('Dr. Juan Rodríguez', 'Matemáticas'),
    ('Ing. María González', 'Programación'),
    ('Prof. Carlos López', 'Bases de Datos');

INSERT INTO "practica-mvc".materias (nombre_materia, creditos)
VALUES
    ('Cálculo I', 4),
    ('Programación Java', 3),
    ('Bases de Datos SQL', 3);

INSERT INTO "practica-mvc".grupos (id_materia, id_docente, aula, horario)
VALUES
    (1, 1, 'A101', 'Lunes 8:00 - Viernes 10:00'),
    (2, 2, 'B202', 'Martes 10:00 - Jueves 12:00'),
    (3, 3, 'C303', 'Miércoles 14:00 - Viernes 16:00');

INSERT INTO "practica-mvc".estudiantes (name, lastname, email)
VALUES
    ('Pedro', 'Gómez', 'pedro@email.com'),
    ('Ana', 'Martínez', 'ana@email.com'),
    ('Luis', 'Hernández', 'luis@email.com');
