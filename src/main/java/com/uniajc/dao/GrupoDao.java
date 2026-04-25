package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionPostgresDatabase;
import com.uniajc.modelo.Grupo;

public class GrupoDao {

    public void guardarGrupo(Grupo grupo) {
        String sql = "INSERT INTO \"practica-mvc\".grupos (id_materia, id_docente, aula, horario) VALUES (?, ?, ?, ?);";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, grupo.getIdMateria());
            pstmt.setInt(2, grupo.getIdDocente());
            pstmt.setString(3, grupo.getAula());
            pstmt.setString(4, grupo.getHorario());

            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Grupo> obtenerTodosLosGrupos() {
        List<Grupo> grupos = new ArrayList<>();

        String sql = "SELECT id_grupo, id_materia, id_docente, aula, horario FROM \"practica-mvc\".grupos;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setIdGrupo(rs.getInt("id_grupo"));
                grupo.setIdMateria(rs.getInt("id_materia"));
                grupo.setIdDocente(rs.getInt("id_docente"));
                grupo.setAula(rs.getString("aula"));
                grupo.setHorario(rs.getString("horario"));
                grupos.add(grupo);
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return grupos;
    }

    public Grupo obtenerGrupoPorId(int id) {
        String sql = "SELECT id_grupo, id_materia, id_docente, aula, horario FROM \"practica-mvc\".grupos WHERE id_grupo = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setIdGrupo(rs.getInt("id_grupo"));
                grupo.setIdMateria(rs.getInt("id_materia"));
                grupo.setIdDocente(rs.getInt("id_docente"));
                grupo.setAula(rs.getString("aula"));
                grupo.setHorario(rs.getString("horario"));
                return grupo;
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }

        return null;
    }

    public void actualizarGrupo(Grupo grupo) {
        String sql = "UPDATE \"practica-mvc\".grupos SET id_materia = ?, id_docente = ?, aula = ?, horario = ? WHERE id_grupo = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, grupo.getIdMateria());
            pstmt.setInt(2, grupo.getIdDocente());
            pstmt.setString(3, grupo.getAula());
            pstmt.setString(4, grupo.getHorario());
            pstmt.setInt(5, grupo.getIdGrupo());

            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void eliminarGrupo(int id) {
        String sql = "DELETE FROM \"practica-mvc\".grupos WHERE id_grupo = ?;";

        try (Connection conn = ConexionPostgresDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
