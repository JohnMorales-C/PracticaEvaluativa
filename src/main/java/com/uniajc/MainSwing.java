package com.uniajc;

import java.sql.Connection;
import javax.swing.JOptionPane;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.config.DatabaseInitializer;
import com.uniajc.controlador.swing.ControladorEstudianteSwing;
import com.uniajc.dao.EstudianteDao;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.swing.VistaEstudianteSwing;

/**
 * Versión Swing del aplicativo MVC
 * Ejecuta: java -cp target/classes com.uniajc.MainSwing
 */
public class MainSwing {
    
    public static void main(String[] args) {
        
        // Verificar conexión a BD
        try {
            Connection testConn = ConexionPostgresDatabase.getConnection();
            testConn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "❌ No se puede conectar a la BD:\n" + e.getMessage(),
                "Error de Conexión", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Inicializar BD
        try {
            DatabaseInitializer.initializeDatabase();
            DatabaseInitializer.insertSampleData();
        } catch (Exception e) {
            // Continuar aunque falle
        }

        // Crear componentes
        VistaEstudianteSwing vistaEstudiante = new VistaEstudianteSwing();
        EstudianteDao estudianteDao = new EstudianteDao();
        EstudianteService estudianteService = new EstudianteService(estudianteDao);
        ControladorEstudianteSwing controlador = new ControladorEstudianteSwing(vistaEstudiante, estudianteService);

        // Cargar y mostrar estudiantes
        controlador.mostrarTodosLosEstudiantes();
        
        // Acciones del botón registrar
        vistaEstudiante.obtenerBotonRegistrar().addActionListener(e -> {
            try {
                controlador.registrarEstudiante();
                controlador.mostrarTodosLosEstudiantes();
                vistaEstudiante.limpiarCampos();
            } catch (Exception ex) {
                vistaEstudiante.mostrarError("Error: " + ex.getMessage());
            }
        });
    }
}

