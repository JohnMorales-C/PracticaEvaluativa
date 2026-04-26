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


