package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.modelo.Inscripcion_Curso;

public class Inscripcion_CursoDao {

    public void guardarInscripcion(Inscripcion_Curso inscripcion) {
        String sql = "INSERT INTO inscripciones_curso (id_estudiante, id_grupo, nota_final, estado) VALUES (?, ?, ?, ?);";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, inscripcion.getIdEstudiante());
            pstmt.setInt(2, inscripcion.getIdGrupo());
            if (inscripcion.getNotaFinal() != null) {
                pstmt.setFloat(3, inscripcion.getNotaFinal());
            } else {
                pstmt.setNull(3, java.sql.Types.REAL);
            }
            pstmt.setString(4, inscripcion.getEstado());
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Inscripcion_Curso> obtenerTodasLasInscripciones() {
        List<Inscripcion_Curso> inscripciones = new ArrayList<>();
        String sql = "SELECT id_inscripcion, id_estudiante, id_grupo, nota_final, estado FROM inscripciones_curso;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Inscripcion_Curso inscripcion = new Inscripcion_Curso();
                inscripcion.setIdInscripcion(rs.getInt("id_inscripcion"));
                inscripcion.setIdEstudiante(rs.getInt("id_estudiante"));
                inscripcion.setIdGrupo(rs.getInt("id_grupo"));
                float notaFinal = rs.getFloat("nota_final");
                if (rs.wasNull()) {
                    inscripcion.setNotaFinal(null);
                } else {
                    inscripcion.setNotaFinal(notaFinal);
                }
                inscripcion.setEstado(rs.getString("estado"));
                inscripciones.add(inscripcion);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return inscripciones;
    }

    public Inscripcion_Curso obtenerInscripcionPorId(int id) {
        String sql = "SELECT id_inscripcion, id_estudiante, id_grupo, nota_final, estado FROM inscripciones_curso WHERE id_inscripcion = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Inscripcion_Curso inscripcion = new Inscripcion_Curso();
                inscripcion.setIdInscripcion(rs.getInt("id_inscripcion"));
                inscripcion.setIdEstudiante(rs.getInt("id_estudiante"));
                inscripcion.setIdGrupo(rs.getInt("id_grupo"));
                inscripcion.setNotaFinal(rs.getFloat("nota_final"));
                inscripcion.setEstado(rs.getString("estado"));
                return inscripcion;
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return null;
    }

    public void actualizarInscripcion(Inscripcion_Curso inscripcion) {
        String sql = "UPDATE inscripciones SET id_estudiante = ?, id_grupo = ?, nota_final = ?, estado = ? WHERE id_inscripcion = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, inscripcion.getIdEstudiante());
            pstmt.setInt(2, inscripcion.getIdGrupo());
            pstmt.setFloat(3, inscripcion.getNotaFinal());
            pstmt.setString(4, inscripcion.getEstado());
            pstmt.setInt(5, inscripcion.getIdInscripcion());
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void eliminarInscripcion(int id) {
        String sql = "DELETE FROM inscripciones WHERE id_inscripcion = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}