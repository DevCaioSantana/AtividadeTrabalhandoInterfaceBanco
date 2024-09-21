package com.mensageria;

import com.mensageria.dao.CursoDAO;
import com.mensageria.model.Cursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {
    private JTextField cursoField;
    private JTextField siglaField;
    private JTextField areaField;
    private JTextField codigoField;
    private JButton inserirButton;
    private JButton atualizarButton;
    private JButton deletarButton;
    private CursoDAO cursoDAO;

    public Tela() {

        cursoDAO = new CursoDAO();


        setTitle("Gerenciar Cursos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));


        add(new JLabel("Nome do Curso:"));
        cursoField = new JTextField();
        add(cursoField);

        add(new JLabel("Sigla do Curso:"));
        siglaField = new JTextField();
        add(siglaField);

        add(new JLabel("Área do Curso:"));
        areaField = new JTextField();
        add(areaField);

        add(new JLabel("Código do Curso:"));
        codigoField = new JTextField();
        add(codigoField);


        inserirButton = new JButton("Inserir");
        add(inserirButton);
        inserirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inserirCurso();
            }
        });

        atualizarButton = new JButton("Atualizar");
        add(atualizarButton);
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCurso();
            }
        });

        deletarButton = new JButton("Deletar");
        add(deletarButton);
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarCurso();
            }
        });

        setVisible(true); // Exibe a janela
    }


    private void inserirCurso() {
        String nome = cursoField.getText();
        String sigla = siglaField.getText();
        String area = areaField.getText();

        Cursos.Area areaEnum = Cursos.Area.valueOf(area.toUpperCase());

        Cursos curso = new Cursos(nome, sigla, areaEnum);
        cursoDAO.create(curso);

        JOptionPane.showMessageDialog(this, "Curso inserido com sucesso!");
    }


    private void atualizarCurso() {
        try {
            Long codigo = Long.parseLong(codigoField.getText());
            String nome = cursoField.getText();
            String sigla = siglaField.getText();
            String area = areaField.getText();

            Cursos.Area areaEnum = Cursos.Area.valueOf(area.toUpperCase());

            Cursos curso = new Cursos(nome, sigla, areaEnum);
            curso.setCodigo(codigo);

            cursoDAO.update(curso);

            JOptionPane.showMessageDialog(this, "Curso atualizado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código inválido!");
        }
    }


    private void deletarCurso() {
        try {
            Long codigo = Long.parseLong(codigoField.getText());
            cursoDAO.delete(codigo);

            JOptionPane.showMessageDialog(this, "Curso deletado com sucesso!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código inválido!");
        }
    }

    public static void main(String[] args) {
        new Tela();
    }
}
