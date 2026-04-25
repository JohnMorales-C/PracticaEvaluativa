package com.uniajc.vista.swing;

import java.util.List;
import javax.swing.*;
import com.uniajc.modelo.Grupo;

/**
 * Vista Swing para Grupo
 */
public class VistaGrupoSwing extends JFrame {

    private JList<String> listaGrupos;
    private DefaultListModel<String> modeloLista;
    private JTextField idMateriaField;
    private JTextField idDocenteField;
    private JTextField aulaField;
    private JTextField horarioField;

    public VistaGrupoSwing() {
        setTitle("Gestión de Grupos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 550);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        
        JLabel listaLabel = new JLabel("Lista de Grupos:");
        listaLabel.setBounds(10, 10, 200, 20);
        mainPanel.add(listaLabel);
        
        modeloLista = new DefaultListModel<>();
        listaGrupos = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaGrupos);
        scrollPane.setBounds(10, 35, 650, 180);
        mainPanel.add(scrollPane);
        
        JLabel idMateriaLabel = new JLabel("ID Materia:");
        idMateriaLabel.setBounds(10, 230, 100, 20);
        mainPanel.add(idMateriaLabel);
        
        idMateriaField = new JTextField();
        idMateriaField.setBounds(120, 230, 150, 25);
        mainPanel.add(idMateriaField);
        
        JLabel idDocenteLabel = new JLabel("ID Docente:");
        idDocenteLabel.setBounds(10, 270, 100, 20);
        mainPanel.add(idDocenteLabel);
        
        idDocenteField = new JTextField();
        idDocenteField.setBounds(120, 270, 150, 25);
        mainPanel.add(idDocenteField);
        
        JLabel aulaLabel = new JLabel("Aula:");
        aulaLabel.setBounds(10, 310, 100, 20);
        mainPanel.add(aulaLabel);
        
        aulaField = new JTextField();
        aulaField.setBounds(120, 310, 150, 25);
        mainPanel.add(aulaField);
        
        JLabel horarioLabel = new JLabel("Horario:");
        horarioLabel.setBounds(10, 350, 100, 20);
        mainPanel.add(horarioLabel);
        
        horarioField = new JTextField();
        horarioField.setBounds(120, 350, 150, 25);
        mainPanel.add(horarioField);
        
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

    public Grupo obtenerDatosGrupo() {
        try {
            Integer idMateria = Integer.parseInt(idMateriaField.getText());
            Integer idDocente = Integer.parseInt(idDocenteField.getText());
            String aula = aulaField.getText();
            String horario = horarioField.getText();
            
            if (aula.isEmpty() || horario.isEmpty()) {
                mostrarError("Por favor completa todos los campos");
                return null;
            }
            
            return new Grupo(0, idMateria, idDocente, aula, horario);
        } catch (NumberFormatException e) {
            mostrarError("Los IDs deben ser números");
            return null;
        }
    }

    public void mostrarGrupos(List<Grupo> grupos) {
        modeloLista.clear();
        for (Grupo gr : grupos) {
            String item = String.format("ID: %d | Materia: %d | Docente: %d | Aula: %s | %s", 
                gr.getIdGrupo(), gr.getIdMateria(), gr.getIdDocente(), gr.getAula(), gr.getHorario());
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
        idMateriaField.setText("");
        idDocenteField.setText("");
        aulaField.setText("");
        horarioField.setText("");
        idMateriaField.requestFocus();
    }
}
