package com.mensageria.dao;

import com.mensageria.model.Cursos;
import java.util.List;
import java.util.Optional;

public interface ICursoDAO {
    Cursos create(Cursos curso);
    Cursos update(Cursos curso);
    void delete(Long id);
    List<Cursos> findAll();
    Optional<Cursos> findById(Long id);
    List<Cursos> findByArea(Cursos.Area area);
    Optional<Cursos> findBySigla(String sigla);
}
