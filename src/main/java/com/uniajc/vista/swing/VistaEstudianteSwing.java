package com.uniajc.vista.swing;

import java.util.List;
import javax.swing.*;
import com.uniajc.modelo.Estudiante;

/**
 * Vista Swing para Estudiante
 * Interfaz gráfica sin modificar la clase Scanner original
 */
public class VistaEstudianteSwing extends JFrame {

    private JPanel mainPanel;
    private JList<String> listaEstudiantes;
    private DefaultListModel<String> modeloLista;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField correoField;
    private JButton registrarBtn;

    public VistaEstudianteSwing() {
        setTitle("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        // Panel de lista
        JLabel listaLabel = new JLabel("Lista de Estudiantes:");
        listaLabel.setBounds(10, 10, 200, 20);
        mainPanel.add(listaLabel);
        
        modeloLista = new DefaultListModel<>();
        listaEstudiantes = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaEstudiantes);
        scrollPane.setBounds(10, 35, 650, 200);
        mainPanel.add(scrollPane);
        
        // Panel de entrada
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 250, 100, 20);
        mainPanel.add(nombreLabel);
        
        nombreField = new JTextField();
        nombreField.setBounds(120, 250, 200, 25);
        mainPanel.add(nombreField);
        
        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 290, 100, 20);
        mainPanel.add(apellidoLabel);
        
        apellidoField = new JTextField();
        apellidoField.setBounds(120, 290, 200, 25);
        mainPanel.add(apellidoField);
        
        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 330, 100, 20);
        mainPanel.add(correoLabel);
        
        correoField = new JTextField();
        correoField.setBounds(120, 330, 200, 25);
        mainPanel.add(correoField);
        
        // Botones
        registrarBtn = new JButton("Registrar");
        registrarBtn.setBounds(120, 370, 100, 30);
        registrarBtn.setName("registrar");
        mainPanel.add(registrarBtn);
        
        JButton limpiarBtn = new JButton("Limpiar");
        limpiarBtn.setBounds(230, 370, 100, 30);
        limpiarBtn.setName("limpiar");
        limpiarBtn.addActionListener(e -> limpiarCampos());
        mainPanel.add(limpiarBtn);
        
        setContentPane(mainPanel);
        setVisible(true);
    }

    public Estudiante obtenerDatosEstudiante() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String correo = correoField.getText();
        
        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarError("Por favor completa todos los campos");
            return null;
        }
        
        return new Estudiante(0, nombre, apellido, correo);
    }

    public void mostrarEstudiantes(List<Estudiante> estudiantes) {
        modeloLista.clear();
        for (Estudiante est : estudiantes) {
            String item = String.format("ID: %d | %s %s | %s", 
                est.getId(), est.getNombre(), est.getApellido(), est.getCorreo());
            modeloLista.addElement(item);
        }
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void limpiarCampos() {
        nombreField.setText("");
        apellidoField.setText("");
        correoField.setText("");
        nombreField.requestFocus();
    }

    public JButton obtenerBotonRegistrar() {
        return registrarBtn;
    }
}
