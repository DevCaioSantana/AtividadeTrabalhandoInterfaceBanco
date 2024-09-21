package com.mensageria.dao;

import com.mensageria.model.Cursos;

import java.util.List;

public interface ICursoDAO {
    Cursos create(Cursos curso);
    void update(Cursos curso);
    void delete(Long codigo);
    List<Cursos> findAll();
    Cursos findById(Long codigo);
    List<Cursos> findByArea(Cursos.Area area);
    Cursos findBySigla(String sigla);
}

