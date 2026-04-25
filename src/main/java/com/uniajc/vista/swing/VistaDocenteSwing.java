package com.uniajc.vista.swing;

import java.util.List;
import javax.swing.*;
import com.uniajc.modelo.Docente;

/**
 * Vista Swing para Docente
 */
public class VistaDocenteSwing extends JFrame {

    private JList<String> listaDocentes;
    private DefaultListModel<String> modeloLista;
    private JTextField nombreField;
    private JTextField especialidadField;

    public VistaDocenteSwing() {
        setTitle("Gestión de Docentes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel listaLabel = new JLabel("Lista de Docentes:");
        listaLabel.setBounds(10, 10, 200, 20);
        mainPanel.add(listaLabel);
        
        modeloLista = new DefaultListModel<>();
        listaDocentes = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaDocentes);
        scrollPane.setBounds(10, 35, 650, 200);
        mainPanel.add(scrollPane);
        
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 250, 100, 20);
        mainPanel.add(nombreLabel);
        
        nombreField = new JTextField();
        nombreField.setBounds(120, 250, 200, 25);
        mainPanel.add(nombreField);
        
        JLabel especialidadLabel = new JLabel("Especialidad:");
        especialidadLabel.setBounds(10, 290, 100, 20);
        mainPanel.add(especialidadLabel);
        
        especialidadField = new JTextField();
        especialidadField.setBounds(120, 290, 200, 25);
        mainPanel.add(especialidadField);
        
        JButton registrarBtn = new JButton("Registrar");
        registrarBtn.setBounds(120, 370, 100, 30);
        registrarBtn.setName("registrar");
        mainPanel.add(registrarBtn);
        
        JButton limpiarBtn = new JButton("Limpiar");
        limpiarBtn.setBounds(230, 370, 100, 30);
        limpiarBtn.addActionListener(e -> limpiarCampos());
        mainPanel.add(limpiarBtn);
        
        setContentPane(mainPanel);
        setVisible(true);
    }

    public Docente obtenerDatosDocente() {
        String nombre = nombreField.getText();
        String especialidad = especialidadField.getText();
        
        if (nombre.isEmpty() || especialidad.isEmpty()) {
            mostrarError("Por favor completa todos los campos");
            return null;
        }
        
        return new Docente(0, nombre, especialidad);
    }

    public void mostrarDocentes(List<Docente> docentes) {
        modeloLista.clear();
        for (Docente doc : docentes) {
            String item = String.format("ID: %d | %s | %s", 
                doc.getIdDocente(), doc.getNombre(), doc.getEspecialidad());
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
        especialidadField.setText("");
        nombreField.requestFocus();
    }
}
