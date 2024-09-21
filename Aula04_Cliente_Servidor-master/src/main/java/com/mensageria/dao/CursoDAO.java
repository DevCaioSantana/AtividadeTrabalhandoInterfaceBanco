package com.mensageria.dao;


import com.mensageria.config.ConnectionFactory;
import com.mensageria.model.Cursos;
import com.mensageria.model.Cursos.Area;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements ICursoDAO {

    @Override
    public Cursos create(Cursos curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
        String sql = "INSERT INTO Curso (nome, sigla, area) VALUES (?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setString(3, curso.getArea().name());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                curso.setCodigo(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return curso;
    }
    @Override
    public void update(Cursos curso) {
        try (Connection connection = ConnectionFactory.getConnection()) {
        String sql = "UPDATE Curso SET nome = ?, sigla = ?, area = ? WHERE codigo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getSigla());
            stmt.setString(3, curso.getArea().name());
            stmt.setLong(4, curso.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long codigo) {
        try (Connection connection = ConnectionFactory.getConnection()) {
        String sql = "DELETE FROM Curso WHERE codigo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cursos> findAll() {
        String sql = "SELECT * FROM Curso";
        List<Cursos> cursos = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
        Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cursos curso = new Cursos(rs.getString("nome"), rs.getString("sigla"), Area.valueOf(rs.getString("area")));
                curso.setCodigo(rs.getLong("codigo"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cursos;
    }

    @Override
    public Cursos findById(Long codigo) {
        String sql = "SELECT * FROM Curso WHERE codigo = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cursos curso = new Cursos(rs.getString("nome"), rs.getString("sigla"), Area.valueOf(rs.getString("area")));
                    curso.setCodigo(rs.getLong("codigo"));
                    return curso;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cursos> findByArea(Area area) {
        String sql = "SELECT * FROM Curso WHERE area = ?";
        List<Cursos> cursos = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection()) {
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, area.name());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cursos curso = new Cursos(rs.getString("nome"), rs.getString("sigla"), Area.valueOf(rs.getString("area")));
                    curso.setCodigo(rs.getLong("codigo"));
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public Cursos findBySigla(String sigla) {
        String sql = "SELECT * FROM Curso WHERE sigla = ?";
        try (Connection connection = ConnectionFactory.getConnection()) {
        PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, sigla);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cursos curso = new Cursos(rs.getString("nome"), rs.getString("sigla"), Area.valueOf(rs.getString("area")));
                    curso.setCodigo(rs.getLong("codigo"));
                    return curso;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
