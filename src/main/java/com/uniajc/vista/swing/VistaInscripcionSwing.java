package com.uniajc.vista.swing;

import java.util.List;
import javax.swing.*;
import com.uniajc.modelo.Inscripcion_Curso;

/**
 * Vista Swing para Inscripción de Curso
 */
public class VistaInscripcionSwing extends JFrame {

    private JList<String> listaInscripciones;
    private DefaultListModel<String> modeloLista;
    private JTextField idEstudianteField;
    private JTextField idGrupoField;
    private JTextField estadoField;
    private JTextField notaField;

    public VistaInscripcionSwing() {
        setTitle("Gestión de Inscripciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel listaLabel = new JLabel("Lista de Inscripciones:");
        listaLabel.setBounds(10, 10, 200, 20);
        mainPanel.add(listaLabel);
        
        modeloLista = new DefaultListModel<>();
        listaInscripciones = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaInscripciones);
        scrollPane.setBounds(10, 35, 650, 180);
        mainPanel.add(scrollPane);
        
        JLabel idEstudianteLabel = new JLabel("ID Estudiante:");
        idEstudianteLabel.setBounds(10, 230, 100, 20);
        mainPanel.add(idEstudianteLabel);
        
        idEstudianteField = new JTextField();
        idEstudianteField.setBounds(120, 230, 150, 25);
        mainPanel.add(idEstudianteField);
        
        JLabel idGrupoLabel = new JLabel("ID Grupo:");
        idGrupoLabel.setBounds(10, 270, 100, 20);
        mainPanel.add(idGrupoLabel);
        
        idGrupoField = new JTextField();
        idGrupoField.setBounds(120, 270, 150, 25);
        mainPanel.add(idGrupoField);
        
        JLabel estadoLabel = new JLabel("Estado:");
        estadoLabel.setBounds(10, 310, 100, 20);
        mainPanel.add(estadoLabel);
        
        estadoField = new JTextField();
        estadoField.setBounds(120, 310, 150, 25);
        mainPanel.add(estadoField);
        
        JLabel notaLabel = new JLabel("Nota Final (0-5):");
        notaLabel.setBounds(10, 350, 100, 20);
        mainPanel.add(notaLabel);
        
        notaField = new JTextField();
        notaField.setBounds(120, 350, 150, 25);
        mainPanel.add(notaField);
        
        JButton registrarBtn = new JButton("Registrar");
        registrarBtn.setBounds(120, 410, 100, 30);
        registrarBtn.setName("registrar");
        mainPanel.add(registrarBtn);
        
        JButton limpiarBtn = new JButton("Limpiar");
        limpiarBtn.setBounds(230, 410, 100, 30);
        limpiarBtn.addActionListener(e -> limpiarCampos());
        mainPanel.add(limpiarBtn);
        
        setContentPane(mainPanel);
        setVisible(true);
    }

    public Inscripcion_Curso obtenerDatosInscripcion() {
        try {
            Integer idEstudiante = Integer.parseInt(idEstudianteField.getText());
            Integer idGrupo = Integer.parseInt(idGrupoField.getText());
            String estado = estadoField.getText();
            Float nota = notaField.getText().isEmpty() ? 0.0f : Float.parseFloat(notaField.getText());
            
            if (estado.isEmpty()) {
                mostrarError("Por favor completa todos los campos");
                return null;
            }
            
            return new Inscripcion_Curso(0, idEstudiante, idGrupo, nota, estado);
        } catch (NumberFormatException e) {
            mostrarError("Verifica que los valores sean correctos");
            return null;
        }
    }

    public void mostrarInscripciones(List<Inscripcion_Curso> inscripciones) {
        modeloLista.clear();
        for (Inscripcion_Curso ins : inscripciones) {
            String item = String.format("ID: %d | Est: %d | Grupo: %d | Nota: %.1f | %s", 
                ins.getIdInscripcion(), ins.getIdEstudiante(), ins.getIdGrupo(), 
                ins.getNotaFinal(), ins.getEstado());
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
        idEstudianteField.setText("");
        idGrupoField.setText("");
        estadoField.setText("");
        notaField.setText("");
        idEstudianteField.requestFocus();
    }
}
