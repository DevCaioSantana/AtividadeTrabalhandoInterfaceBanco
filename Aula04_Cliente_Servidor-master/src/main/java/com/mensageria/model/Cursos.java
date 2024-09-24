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
        this.nome = nome;
        this.sigla = sigla;
        this.area = area;
    }

    public enum Area {
        EXATAS, HUMANAS, BIOLOGICAS, ARTES;
    }

    public Area getArea() {
        return area;
    }
    
    @Override
    public String toString() {
        return sigla;
    }
}
