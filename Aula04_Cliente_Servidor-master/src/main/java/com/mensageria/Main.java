package com.mensageria;

import com.mensageria.dao.AlunoDAO;
import com.mensageria.dao.CursoDAO; // Importando a classe CursoDAO
import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos; // Mudando para Curso
import com.mensageria.util.Funcoes;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        CursoDAO cursoDAO = new CursoDAO();


        Cursos curso1 = new Cursos("Análise e Desenvolvimento de Sistemas", "ADS", Cursos.Area.EXATAS);
        cursoDAO.create(curso1);

        Cursos curso2 = new Cursos("Engenharia de Computação", "ECMP", Cursos.Area.EXATAS);
        cursoDAO.create(curso2);

        Cursos curso3 = new Cursos("Curso Genérico", "OUTROS", Cursos.Area.HUMANAS);
        cursoDAO.create(curso3);


        AlunoDAO alunoDAO = new AlunoDAO();


        Alunos aluno1 = new Alunos();
        aluno1.setNome("Socorro de Deus");
        aluno1.setSexo("feminino");
        aluno1.setMaioridade(true);
        aluno1.setTelefone("2345678");
        aluno1.setCurso(curso1);
        alunoDAO.create(aluno1);

        Alunos aluno2 = new Alunos();
        aluno2.setNome("Amanda");
        aluno2.setSexo("feminino");
        aluno2.setMaioridade(true);
        aluno2.setTelefone("555666777");
        aluno2.setCurso(curso2);
        alunoDAO.create(aluno2);


        Optional<Alunos> alunoBuscado = alunoDAO.findById(1L);
        alunoBuscado.ifPresent(Funcoes::print);


        List<Alunos> alunos = alunoDAO.findAll();
        Funcoes.printList(alunos);

        System.err.println("=== Find By Curso ===");

    }
}







/*
package com.mensageria;

import com.mensageria.dao.AlunoDAO;
import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;
import com.mensageria.util.Funcoes;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        AlunoDAO dao = new AlunoDAO();
        Alunos aluno = new Alunos();
        /*
        aluno.setNome("Socorro de Deus");
        aluno.setSexo("feminino");
        aluno.setMaioridade(true);
        aluno.setTelefone("2345678");
        aluno.setCurso(Cursos.ADS);


        AlunoDAO dao = new AlunoDAO();
        dao.create(aluno);

        aluno.setNome("Amanda");
        aluno.setSexo("feminino");
        aluno.setMaioridade(true);
        aluno.setTelefone("555666777");
        aluno.setCurso(Cursos.ECMP);
        dao.create(aluno);


        Optional<Alunos> a = dao.findById(3l);

        Funcoes.print(a.get());

        List<Alunos> alunos = dao.findAll();

        Funcoes.printList(alunos);
        /*
        aluno.setNome("Roberto Parametro");
        aluno.setSexo("masculino");
        aluno.setMaioridade(false);
        aluno.setTelefone("33344456");
        aluno.setCurso(Cursos.OUTROS);
        dao.create(aluno);

        Funcoes.print(aluno);

        System.err.println("=== Find By Curso ===");
        //alunos = dao.findByCurso(Cursos.OUTROS);
        Funcoes.printList(alunos);

    }
}*/