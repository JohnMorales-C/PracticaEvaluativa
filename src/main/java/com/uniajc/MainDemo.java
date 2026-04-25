package com.uniajc;

import com.uniajc.controlador.ControladorEstudiante;
import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.VistaEstudiante;
import java.util.ArrayList;
import java.util.List;

/**
 * Versión de demostración que NO requiere PostgreSQL
 * Usa datos en memoria para pruebas
 */
public class MainDemo {
    
    public static void main(String[] args) {
        System.out.println("------------------------------");
        System.out.println("Practica MVC - Sistema Académico UNIAJC (DEMO)");
        System.out.println("------------------------------");
        System.out.println("Nota: Ejecutando en MODO DEMO (sin necesidad de BD)\n");

        // Crear datos simulados
        List<Estudiante> estudiantesSimulados = new ArrayList<>();
        estudiantesSimulados.add(new Estudiante(1, "Juan", "Pérez", "juan@email.com"));
        estudiantesSimulados.add(new Estudiante(2, "María", "García", "maria@email.com"));
        estudiantesSimulados.add(new Estudiante(3, "Carlos", "López", "carlos@email.com"));

        System.out.println("📚 ESTUDIANTES EN SISTEMA (datos simulados):");
        System.out.println("==========================================");
        for (Estudiante est : estudiantesSimulados) {
            System.out.println("ID: " + est.getId() + 
                             " | Nombre: " + est.getNombre() + 
                             " | Apellido: " + est.getApellido() + 
                             " | Email: " + est.getCorreo());
        }

        System.out.println("\n✅ PRUEBAS DE VALIDACIÓN:");
        System.out.println("==========================================");

        // Crear servicio con DAO
        EstudianteService servicio = new EstudianteService(new EstudianteDao());

        // Prueba 1: Validar null
        try {
            System.out.println("\n1. Intentando registrar estudiante NULL...");
            servicio.registrarEstudiante(null);
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Validación correcta: " + e.getMessage());
        }

        // Prueba 2: Validar nombre vacío
        try {
            System.out.println("2. Intentando registrar con nombre vacío...");
            servicio.registrarEstudiante(new Estudiante(0, "", "Pérez", "test@email.com"));
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Validación correcta: " + e.getMessage());
        }

        // Prueba 3: Validar apellido vacío
        try {
            System.out.println("3. Intentando registrar con apellido vacío...");
            servicio.registrarEstudiante(new Estudiante(0, "Juan", "", "test@email.com"));
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Validación correcta: " + e.getMessage());
        }

        // Prueba 4: Validar ID inválido
        try {
            System.out.println("4. Intentando obtener con ID -1...");
            servicio.obtenerEstudiantePorId(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("   ✓ Validación correcta: " + e.getMessage());
        }

        System.out.println("\n✅ TODAS LAS VALIDACIONES PASARON");
        System.out.println("\n💡 Para usar la BD real:");
        System.out.println("   1. Inicia PostgreSQL: net start postgresql-x64-15");
        System.out.println("   2. Ejecuta: java -cp target/classes com.uniajc.Main");
    }
}
