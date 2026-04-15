package com.uniajc.controlador;

import java.util.List;

import com.uniajc.modelo.Materia;
import com.uniajc.servicios.MateriaService;
import com.uniajc.vista.VistaMateria;

public class ControladorMateria {

    private VistaMateria vistaMateria;
    private MateriaService materiaService;

    public ControladorMateria(VistaMateria vistaMateria, MateriaService materiaService) {
        this.vistaMateria = vistaMateria;
        this.materiaService = materiaService;
    }

    public void registrarMateria() {
        Materia nuevaMateria = vistaMateria.solicitarDatosMateria();
        materiaService.registrarMateria(nuevaMateria);
        vistaMateria.mostrarMensaje("Materia registrada exitosamente.");
    }

    public void mostrarTodasLasMaterias() {
        List<Materia> materias = materiaService.obtenerTodasLasMaterias();
        vistaMateria.mostrarTodasLasMaterias(materias);
    }

    public void obtenerMateriaPorId(int id) {
        Materia materia = materiaService.obtenerMateriaPorId(id);
        if (materia != null) {
            vistaMateria.mostrarDetallesMateria(materia);
        } else {
            vistaMateria.mostrarMensaje("Materia no encontrada.");
        }
    }
}
