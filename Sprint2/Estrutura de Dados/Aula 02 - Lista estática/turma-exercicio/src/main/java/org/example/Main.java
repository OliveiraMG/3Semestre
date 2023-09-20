package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        String[] turma1 = new String[10];
        String[] turma2 = new String[10];

        int contadorTurma1 = 0;
        int contadorTurma2 = 0;

        for (int i = 0; i < 10; i++) {
            System.out.println("Digite o nome do aluno " + (i + 1) + ":");
            String nome = leitor.nextLine();

            System.out.println("Digite a turma do aluno " + (i + 1) + ":");
            String turma = leitor.nextLine();

            if (turma.equalsIgnoreCase("T1")) {
                turma1[contadorTurma1++] = nome;
//                contadorTurma1++;
            } else if (turma.equalsIgnoreCase("T2")) {
                turma2[contadorTurma2++] = nome;
//                contadorTurma2++;
            } else {
                System.out.println("Turma inválida!");
            }
        }

        System.out.println("Alunos da turma 1:");
        if (contadorTurma1 > 0) {
            System.out.print(turma1[0]);

            for (int i = 1; i < contadorTurma1; i++) {
                System.out.print(", " + turma1[i]);
            }
        }

        System.out.println("\n------------------------------------------");

        System.out.println("Alunos da turma 2:");
        if (contadorTurma2 > 0) {
            System.out.print(turma2[0]);

            for (int i = 1; i < contadorTurma2; i++) {
                System.out.print(", " + turma2[i]);
            }
        }
    }
}