package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.modelo.Materia;

public class MateriaDao {

    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materias (nombre_materia, creditos) VALUES (?, ?);";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCreditos());
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Materia> obtenerTodasLasMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT id_materia, nombre_materia, creditos FROM materias;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombreMateria(rs.getString("nombre_materia"));
                materia.setCreditos(rs.getInt("creditos"));
                materias.add(materia);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return materias;
    }

    public Materia obtenerMateriaPorId(int id) {
        String sql = "SELECT id_materia, nombre_materia, creditos FROM materias WHERE id_materia = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombreMateria(rs.getString("nombre_materia"));
                materia.setCreditos(rs.getInt("creditos"));
                return materia;
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return null;
    }

    public void actualizarMateria(Materia materia) {
        String sql = "UPDATE materias SET nombre_materia = ?, creditos = ? WHERE id_materia = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCreditos());
            pstmt.setInt(3, materia.getIdMateria());
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void eliminarMateria(int id) {
        String sql = "DELETE FROM materias WHERE id_materia = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}