package org.example;

import java.util.ArrayList;
import java.util.List;

public class Escola {
    private String nome;

    private List<Aluno> listaAlunos;

    public Escola(String nome) {
        this.nome = nome;
        listaAlunos = new ArrayList<>();
    }

    public void addAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public void exibeTodos() {
        System.out.println("\nLista dos alunos: \n");
        for (Aluno aluno : listaAlunos) {
            System.out.println(aluno);
        }
    }

    public void exibeAlunoGraduacao() {
        for (Aluno aluno : listaAlunos) {
            if (aluno instanceof AlunoGraduacao) {
                System.out.println(aluno);
            }
        }
    }

    public void exibeAprovados() {
        for (Aluno aluno : listaAlunos) {
            if (aluno.calcMedia() >= 6.0) {
                System.out.println(aluno);
            }
        }
    }

    public void buscaAluno(Integer ra) {
        for (Aluno aluno : listaAlunos) {
            if (aluno.getRa().equals(ra)) {
                System.out.println(aluno);
            }
        }
    }
}