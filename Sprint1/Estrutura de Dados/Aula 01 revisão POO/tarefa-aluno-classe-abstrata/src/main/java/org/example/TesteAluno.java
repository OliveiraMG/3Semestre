package org.example;

public class TesteAluno {
    public static void main(String[] args) {

        Escola escola = new Escola("Escola do Raimundo");

        Aluno aluno1 = new AlunoGraduacao(113456, "João", 7.0, 8.0);
        Aluno aluno2 = new AlunoFundamental(123456, "Maria", 4.0, 2.0, 1.0, 7.0);
        Aluno aluno3 = new AlunoPos(133456, "José", 7.0, 8.0, 9.0);

        System.out.println("Aluno 1: " + aluno1 + "\n");
        System.out.println("Aluno 2: " + aluno2 + "\n");
        System.out.println("Aluno 3: " + aluno3 + "\n");

        escola.addAluno(aluno1);
        escola.addAluno(aluno2);
        escola.addAluno(aluno3);

        escola.exibeTodos();
        escola.exibeAlunoGraduacao();
        escola.exibeAprovados();
        escola.buscaAluno(123456);
    }
}