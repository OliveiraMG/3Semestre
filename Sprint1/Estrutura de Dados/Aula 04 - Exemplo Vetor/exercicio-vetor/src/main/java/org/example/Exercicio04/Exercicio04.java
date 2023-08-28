package org.example.Exercicio04;

import java.util.Scanner;

public class Exercicio04 {

    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);
        Scanner leitorPesquisa = new Scanner(System.in);

        int[] vetor = new int[10];

        for (int i = 0; i < vetor.length; i++) {
            System.out.println("Informe o valor da posição " + i + ": ");
            vetor[i] = leitor.nextInt();
        }

        System.out.println("Digite o valor que deseja pesquisar: ");
        int pesquisa = leitorPesquisa.nextInt();

        System.out.println("Resultado de quantas vezes aparece o valor desejado:");
        int contador = 0;
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == pesquisa) {
                contador++;
            }
        }
        System.out.println("O valor " + pesquisa + " aparece " + contador + " vezes");


    }

}
