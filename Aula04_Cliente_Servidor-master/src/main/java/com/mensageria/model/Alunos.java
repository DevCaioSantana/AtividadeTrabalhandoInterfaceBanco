package com.mensageria.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alunos {
    Long matricula;
    String nome;
    String telefone;
    boolean maioridade;
    Cursos curso;
    String sexo;

    public String toString() {
        return "Aluno{" + "nome='" + nome + '\'' + ", curso='" + curso.getSigla() + '\'' + '}';
    }
}
