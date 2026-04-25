package com.uniajc.vista.swing;

import java.util.List;
import javax.swing.*;
import com.uniajc.modelo.Materia;

/**
 * Vista Swing para Materia
 */
public class VistaMateriaSwing extends JFrame {

    private JList<String> listaMaterias;
    private DefaultListModel<String> modeloLista;
    private JTextField nombreField;
    private JTextField creditosField;

    public VistaMateriaSwing() {
        setTitle("Gestión de Materias");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel listaLabel = new JLabel("Lista de Materias:");
        listaLabel.setBounds(10, 10, 200, 20);
        mainPanel.add(listaLabel);
        
        modeloLista = new DefaultListModel<>();
        listaMaterias = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaMaterias);
        scrollPane.setBounds(10, 35, 650, 200);
        mainPanel.add(scrollPane);
        
        JLabel nombreLabel = new JLabel("Nombre Materia:");
        nombreLabel.setBounds(10, 250, 100, 20);
        mainPanel.add(nombreLabel);
        
        nombreField = new JTextField();
        nombreField.setBounds(120, 250, 200, 25);
        mainPanel.add(nombreField);
        
        JLabel creditosLabel = new JLabel("Créditos:");
        creditosLabel.setBounds(10, 290, 100, 20);
        mainPanel.add(creditosLabel);
        
        creditosField = new JTextField();
        creditosField.setBounds(120, 290, 200, 25);
        mainPanel.add(creditosField);
        
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

    public Materia obtenerDatosMateria() {
        String nombre = nombreField.getText();
        String creditosStr = creditosField.getText();
        
        if (nombre.isEmpty() || creditosStr.isEmpty()) {
            mostrarError("Por favor completa todos los campos");
            return null;
        }
        
        try {
            Integer creditos = Integer.parseInt(creditosStr);
            return new Materia(0, nombre, creditos);
        } catch (NumberFormatException e) {
            mostrarError("Los créditos deben ser un número");
            return null;
        }
    }

    public void mostrarMaterias(List<Materia> materias) {
        modeloLista.clear();
        for (Materia mat : materias) {
            String item = String.format("ID: %d | %s | Créditos: %d", 
                mat.getIdMateria(), mat.getNombreMateria(), mat.getCreditos());
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
        creditosField.setText("");
        nombreField.requestFocus();
    }
}
