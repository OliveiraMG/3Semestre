package org.example.Exercicio03;

import java.util.Scanner;

public class Exercicio03 {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        Scanner leitorPesquisa = new Scanner(System.in);

        String[] vetor = new String[10];

        for (int i = 0; i < vetor.length; i++) {
            System.out.println("Informe o nome da posição " + i + ": ");
            vetor[i] = leitor.nextLine();
        }

        System.out.println("Digite o nome que deseja pesquisar: ");
        String pesquisa = leitorPesquisa.nextLine();


        System.out.println("Resultado pesquisa nome:");
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].equals(pesquisa)) {
                System.out.println("O nome " + pesquisa + " está na posição " + i);
                break;
            } else {
                System.out.println("O nome " + pesquisa + " não foi encontrado");
                break;
            }
        }

    }

}
