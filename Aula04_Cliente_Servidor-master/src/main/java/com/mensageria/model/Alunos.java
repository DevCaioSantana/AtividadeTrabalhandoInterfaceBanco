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

    @Override
    public String toString() {
        return "Aluno: " +
                "Matricula=" + matricula +
                ", Nome='" + nome + '\'' +
                ", Telefone='" + telefone + '\'' +
                ", Maioridade=" + maioridade +
                ", Curso='" + curso + '\'' +
                ", Sexo='" + sexo + '\'';
    }
}
