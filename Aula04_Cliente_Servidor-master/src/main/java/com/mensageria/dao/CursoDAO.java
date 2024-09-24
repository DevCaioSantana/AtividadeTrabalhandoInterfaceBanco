package com.mensageria.dao;

import com.mensageria.config.ConnectionFactory;
import com.mensageria.model.Cursos;
import com.mensageria.model.Cursos.Area;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursoDAO implements ICursoDAO {

    // Método para criar um novo curso
    @Override
    public Cursos create(Cursos curso) {
        String query = "INSERT INTO cursos (nome, sigla, area) VALUES (?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getSigla());
            preparedStatement.setString(3, curso.getArea().name());  // Enum convertido para String
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                curso.setCodigo(resultSet.getLong(1));  // Recupera o ID gerado
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return curso;
    }

    // Método para atualizar um curso existente
    @Override
    public Cursos update(Cursos curso) {
        String query = "UPDATE cursos SET nome = ?, sigla = ?, area = ? WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, curso.getNome());
            preparedStatement.setString(2, curso.getSigla());
            preparedStatement.setString(3, curso.getArea().name());
            preparedStatement.setLong(4, curso.getCodigo());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return curso;
    }

    // Método para deletar um curso pelo ID
    @Override
    public void delete(Long id) {
        String query = "DELETE FROM cursos WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para listar todos os cursos
    @Override
    public List<Cursos> findAll() {
        List<Cursos> cursosList = new ArrayList<>();
        String query = "SELECT * FROM cursos";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(resultSet.getLong("codigo"));
                curso.setNome(resultSet.getString("nome"));
                curso.setSigla(resultSet.getString("sigla"));
                curso.setArea(Cursos.Area.valueOf(resultSet.getString("area")));  // Converte String para enum
                cursosList.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cursosList;
    }

    // Método para buscar um curso pelo ID
    @Override
    public Optional<Cursos> findById(Long id) {
        Cursos curso = null;
        String query = "SELECT * FROM cursos WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                curso = new Cursos();
                curso.setCodigo(resultSet.getLong("codigo"));
                curso.setNome(resultSet.getString("nome"));
                curso.setSigla(resultSet.getString("sigla"));
                curso.setArea(Cursos.Area.valueOf(resultSet.getString("area")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(curso);
    }

    // Método para buscar cursos por área
    @Override
    public List<Cursos> findByArea(Cursos.Area area) {
        List<Cursos> cursosList = new ArrayList<>();
        String query = "SELECT * FROM cursos WHERE area = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, area.name());  // Converte enum para String
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(resultSet.getLong("codigo"));
                curso.setNome(resultSet.getString("nome"));
                curso.setSigla(resultSet.getString("sigla"));
                curso.setArea(Cursos.Area.valueOf(resultSet.getString("area")));
                cursosList.add(curso);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cursosList;
    }

    // Método para buscar um curso pela sigla
    @Override
    public Optional<Cursos> findBySigla(String sigla) {
        Cursos curso = null;
        String query = "SELECT * FROM cursos WHERE sigla = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sigla);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                curso = new Cursos();
                curso.setCodigo(resultSet.getLong("codigo"));
                curso.setNome(resultSet.getString("nome"));
                curso.setSigla(resultSet.getString("sigla"));
                curso.setArea(Cursos.Area.valueOf(resultSet.getString("area")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(curso);
    }
}
