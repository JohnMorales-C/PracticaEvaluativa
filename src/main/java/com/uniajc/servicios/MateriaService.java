package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.MateriaDao;
import com.uniajc.modelo.Materia;

public class MateriaService {

    private final MateriaDao materiaDao;

    public MateriaService(MateriaDao materiaDao) {
        this.materiaDao = materiaDao;
    }

    public void registrarMateria(Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede ser nula.");
        }
        if (materia.getNombreMateria() == null || materia.getNombreMateria().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio.");
        }
        if (materia.getCreditos() == null || materia.getCreditos() <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser mayor a 0.");
        }
        materiaDao.guardarMateria(materia);
    }

    public List<Materia> mostrarTodasLasMaterias() {
        return materiaDao.obtenerTodasLasMaterias();
    }

    public Materia obtenerMateriaPorId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la materia debe ser mayor a 0.");
        }
        return materiaDao.obtenerMateriaPorId(id);
    }

    public void actualizarMateria(Materia materia) {
        if (materia == null || materia.getIdMateria() == null || materia.getIdMateria() <= 0) {
            throw new IllegalArgumentException("El ID de la materia es requerido y debe ser mayor a 0.");
        }
        materiaDao.actualizarMateria(materia);
    }

    public void eliminarMateria(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID de la materia debe ser mayor a 0.");
        }
        materiaDao.eliminarMateria(id);
    }
}