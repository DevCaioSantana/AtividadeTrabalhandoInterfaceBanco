package com.mensageria.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cursos {

    private Long codigo;
    private String nome;
    private String sigla;
    private Area area;

    public Cursos(String nome, String sigla, Area area) {
    }

    public enum Area {
        EXATAS, HUMANAS, BIOLOGICAS, ARTES
    }

    @Override
    public String toString() {
        return sigla;
    }
}
