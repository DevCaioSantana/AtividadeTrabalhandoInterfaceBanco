package com.mensageria.telas;

import com.mensageria.dao.AlunoDAO;
import com.mensageria.dao.CursoDAO;
import com.mensageria.model.Alunos;
import com.mensageria.model.Cursos;
import com.mensageria.model.Cursos.Area;

import java.util.List;

public class Main {
    public static void main(String[] args) {
       
        /*
        // Instâncias de DAOs
        CursoDAO cursoDAO = new CursoDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        // Criando novos cursos
        Cursos curso1 = new Cursos("Ciência da Computação", "CC", Area.EXATAS);
        Cursos curso2 = new Cursos("Psicologia", "PSI", Area.HUMANAS);
        Cursos curso3 = new Cursos("Biomedicina", "BIO", Area.BIOLOGICAS);

        // Inserindo os cursos no banco de dados
        cursoDAO.create(curso1);
        cursoDAO.create(curso2);
        cursoDAO.create(curso3);

        // Exibindo os cursos inseridos
        System.out.println("Cursos inseridos:");
        List<Cursos> cursosList = cursoDAO.findAll();
        cursosList.forEach(System.out::println); // Apenas a sigla será impressa devido ao toString() de Cursos

        // Criando novos alunos e associando cursos
        Alunos aluno1 = new Alunos(null, "João Silva", "123456789", true, curso1, "M");
        Alunos aluno2 = new Alunos(null, "Maria Oliveira", "987654321", false, curso2, "F");

        // Inserindo os alunos no banco de dados
        alunoDAO.create(aluno1);
        alunoDAO.create(aluno2);

        // Exibindo os alunos inseridos
        System.out.println("Alunos inseridos:");
        List<Alunos> alunosList = alunoDAO.findAll();
        alunosList.forEach(aluno -> {
            System.out.println("Matricula: " + aluno.getMatricula() + ", Nome: " + aluno.getNome() + ", Curso: " + aluno.getCurso().toString());
        });


        
        AlunoDAO dao = new AlunoDAO();
        Alunos aluno = new Alunos();

        aluno.setNome("Socorro de Deus");
        aluno.setSexo("feminino");
        aluno.setMaioridade(true);
        aluno.setTelefone("2345678");

        aluno.setCurso(Cursos.ADS);


        AlunoDAO dao1 = new AlunoDAO();
        dao1.create(aluno);

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
        alunos = dao.findByCurso(Cursos.OUTROS);
        Funcoes.printList(alunos);
        */
    }
}