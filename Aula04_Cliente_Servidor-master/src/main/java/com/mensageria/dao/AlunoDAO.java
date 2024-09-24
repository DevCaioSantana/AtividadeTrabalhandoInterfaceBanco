package com.mensageria.dao;

import com.mensageria.config.ConnectionFactory;
import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAO implements IAlunoDAO {

    @Override
    public Alunos create(Alunos aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "INSERT INTO alunos " +
                    "(nome, telefone, maioridade, curso_id, sexo)" +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getTelefone());
            preparedStatement.setBoolean(3, aluno.isMaioridade());
            preparedStatement.setLong(4, aluno.getCurso().getCodigo());
            preparedStatement.setString(5, aluno.getSexo());
            preparedStatement.executeUpdate();


            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                aluno.setMatricula(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aluno;
    }


    @Override
    public void update(Alunos aluno) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String query = "UPDATE alunos SET " +
                    "nome = ?, telefone = ?, maioridade = ?, curso_id = ?, sexo = ? " +
                    "WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setString(2, aluno.getTelefone());
            preparedStatement.setBoolean(3, aluno.isMaioridade());
            preparedStatement.setLong(4, aluno.getCurso().getCodigo());
            preparedStatement.setString(5, aluno.getSexo());
            preparedStatement.setLong(6, aluno.getMatricula());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM alunos WHERE matricula = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Alunos> findById(Long id) {
        Alunos aluno = null;
        String queryAluno = "SELECT * FROM alunos WHERE matricula = ?";
        String queryCurso = "SELECT * FROM cursos WHERE codigo = ?";  // Buscar o curso na tabela Cursos

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatementAluno = connection.prepareStatement(queryAluno);
            preparedStatementAluno.setLong(1, id);
            ResultSet resultSetAluno = preparedStatementAluno.executeQuery();

            if (resultSetAluno.next()) {
                aluno = new Alunos();
                aluno.setMatricula(resultSetAluno.getLong("matricula"));
                aluno.setNome(resultSetAluno.getString("nome"));
                aluno.setTelefone(resultSetAluno.getString("telefone"));
                aluno.setMaioridade(resultSetAluno.getBoolean("maioridade"));

                // Buscar o curso associado ao aluno
                Long cursoId = resultSetAluno.getLong("curso_id");
                PreparedStatement preparedStatementCurso = connection.prepareStatement(queryCurso);
                preparedStatementCurso.setLong(1, cursoId);
                ResultSet resultSetCurso = preparedStatementCurso.executeQuery();

                if (resultSetCurso.next()) {
                    Cursos curso = new Cursos();
                    curso.setCodigo(resultSetCurso.getLong("codigo"));
                    curso.setNome(resultSetCurso.getString("nome"));
                    curso.setSigla(resultSetCurso.getString("sigla"));
                    curso.setArea(Cursos.Area.valueOf(resultSetCurso.getString("area")));  // Caso "area" seja um enum
                    aluno.setCurso(curso);
                }

                aluno.setSexo(resultSetAluno.getString("sexo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.ofNullable(aluno);
    }



    public List<Alunos> findAll() {
        List<Alunos> alunos = new ArrayList<>();
        String queryAlunos = "SELECT * FROM alunos";
        String queryCurso = "SELECT * FROM cursos WHERE codigo = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatementAlunos = connection.prepareStatement(queryAlunos);
            ResultSet resultSetAlunos = preparedStatementAlunos.executeQuery();

            while (resultSetAlunos.next()) {
                Alunos aluno = new Alunos();
                aluno.setMatricula(resultSetAlunos.getLong("matricula"));
                aluno.setNome(resultSetAlunos.getString("nome"));
                aluno.setTelefone(resultSetAlunos.getString("telefone"));
                aluno.setMaioridade(resultSetAlunos.getBoolean("maioridade"));
                aluno.setSexo(resultSetAlunos.getString("sexo"));

                // Buscar o curso associado ao aluno
                Long cursoId = resultSetAlunos.getLong("curso_id");
                PreparedStatement preparedStatementCurso = connection.prepareStatement(queryCurso);
                preparedStatementCurso.setLong(1, cursoId);
                ResultSet resultSetCurso = preparedStatementCurso.executeQuery();

                if (resultSetCurso.next()) {
                    Cursos curso = new Cursos();
                    curso.setCodigo(resultSetCurso.getLong("codigo"));
                    curso.setNome(resultSetCurso.getString("nome"));
                    curso.setSigla(resultSetCurso.getString("sigla"));
                    curso.setArea(Cursos.Area.valueOf(resultSetCurso.getString("area")));  // Caso "area" seja um enum
                    aluno.setCurso(curso);
                }

                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }


    public List<Alunos> findByCurso(String siglaCurso) {
        List<Alunos> alunos = new ArrayList<>();
        String queryAlunos = "SELECT * FROM alunos WHERE curso_id = (SELECT codigo FROM cursos WHERE sigla = ?)";
        String queryCurso = "SELECT * FROM cursos WHERE sigla = ?";

        try (Connection connection = ConnectionFactory.getConnection()) {
            // Primeiro busca o curso baseado na sigla
            PreparedStatement preparedStatementCurso = connection.prepareStatement(queryCurso);
            preparedStatementCurso.setString(1, siglaCurso);
            ResultSet resultSetCurso = preparedStatementCurso.executeQuery();

            Cursos curso = null;
            if (resultSetCurso.next()) {
                curso = new Cursos();
                curso.setCodigo(resultSetCurso.getLong("codigo"));
                curso.setNome(resultSetCurso.getString("nome"));
                curso.setSigla(resultSetCurso.getString("sigla"));
                curso.setArea(Cursos.Area.valueOf(resultSetCurso.getString("area")));
            }

            if (curso != null) {
                // Buscar os alunos que estão matriculados no curso encontrado
                PreparedStatement preparedStatementAlunos = connection.prepareStatement(queryAlunos);
                preparedStatementAlunos.setString(1, siglaCurso);
                ResultSet resultSetAlunos = preparedStatementAlunos.executeQuery();

                while (resultSetAlunos.next()) {
                    Alunos aluno = new Alunos();
                    aluno.setMatricula(resultSetAlunos.getLong("matricula"));
                    aluno.setNome(resultSetAlunos.getString("nome"));
                    aluno.setTelefone(resultSetAlunos.getString("telefone"));
                    aluno.setMaioridade(resultSetAlunos.getBoolean("maioridade"));
                    aluno.setCurso(curso);  // Define o curso que já foi buscado anteriormente
                    aluno.setSexo(resultSetAlunos.getString("sexo"));
                    alunos.add(aluno);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return alunos;
    }

}
