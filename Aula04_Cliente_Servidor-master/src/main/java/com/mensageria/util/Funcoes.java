package com.mensageria.util;

import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Funcoes {
    public Funcoes(){}
    public static void print(Alunos alunos) {
        System.err.println("Nome: " + alunos.getNome());
        System.err.println("Matricula: " + alunos.getMatricula());
        System.err.println("Curso: " + alunos.getCurso());
        System.err.println(alunos.isMaioridade()?"Maior idade":"Menor idade");
        System.err.println("Sexo: " + alunos.getSexo());
        System.err.println("Telefone: " + alunos.getTelefone());
        System.out.println("=====================================================");
    }

    public static void printList(List<Alunos> lista) {
        for (Alunos aluno : lista){
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Matricula: " + aluno.getMatricula());
            System.out.println("Curso: " + aluno.getCurso());
            System.out.println(aluno.isMaioridade() ? "Maior idade" : "Menor idade");
            System.out.println("Sexo: " + aluno.getSexo());
            System.out.println("Telefone: " + aluno.getTelefone());
            System.out.println("=====================================================");
        }
    }
    public static String formatarList(List<Cursos> cursosList) {
    if (cursosList.isEmpty()) {
        return "Nenhum curso encontrado.";
    }

    return cursosList.stream()
            .map(curso -> "Cod.: " + curso.getCodigo()+
                          ", Nome: " + curso.getNome() +
                          ", Sigla: " + curso.getSigla() +
                          ", Área: " + curso.getArea())
            .collect(Collectors.joining("\n"));
}

public static String formatarOptional(Optional<Cursos> cursoOptional) {
    if (cursoOptional.isEmpty()) {
        return "Nenhum curso encontrado.";
    }

    Cursos curso = cursoOptional.get();
    return "Cod.: " + curso.getCodigo()+
                          ", Nome: " + curso.getNome() +
                          ", Sigla: " + curso.getSigla() +
                          ", Área: " + curso.getArea();
}

}
