package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.modelo.Docente;

public class DocenteDao {

    public void guardarDocente(Docente docente) {
        String sql = "INSERT INTO \"practica-mvc\".docentes (nombre, especialidad) VALUES (?, ?);";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getEspecialidad());

            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Docente> obtenerTodosLosDocentes() {
        List<Docente> docentes = new ArrayList<>();

        String sql = "SELECT id_docente, nombre, especialidad FROM \"practica-mvc\".docentes;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docentes.add(docente);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return docentes;
    }

    public Docente obtenerDocentePorId(int id) {
        String sql = "SELECT id_docente, nombre, especialidad FROM \"practica-mvc\".docentes WHERE id_docente = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setEspecialidad(rs.getString("especialidad"));
                return docente;
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return null;
    }

    public void actualizarDocente(Docente docente) {
        String sql = "UPDATE \"practica-mvc\".docentes SET nombre = ?, especialidad = ? WHERE id_docente = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getEspecialidad());
            pstmt.setInt(3, docente.getIdDocente());

            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void eliminarDocente(int id) {
        String sql = "DELETE FROM \"practica-mvc\".docentes WHERE id_docente = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
