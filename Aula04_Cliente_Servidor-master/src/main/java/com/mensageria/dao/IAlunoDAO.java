package com.mensageria.dao;

import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;

import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    Alunos create(Alunos aluno);

    void update(Alunos aluno);

    void delete(Long id);

    Optional<Alunos> findById(Long id);

    List<Alunos> findAll();

    List<Alunos> findByCurso(String siglaCurso);
}
