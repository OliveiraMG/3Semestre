package org.example.Exercicio02;

import java.util.Scanner;

public class Exercicio02 {
    public static void main(String[] args) {

        Scanner leitor = new Scanner(System.in);

        int[] vetor = new int[10];

        for (int i = 0; i < vetor.length; i++) {
            System.out.println("Informe o valor da posição " + i + ": ");
            vetor[i] = leitor.nextInt();
        }

        System.out.println("Calcula a média do vetor:");
        int soma = 0;
        int media = 0;
        for (int i = 0; i < vetor.length; i++) {
            soma += vetor[i];
        }
        media = soma / vetor.length;
        System.out.println("A média do vetor é: " + media);

        System.out.println("Exibe valores do vetor acima da média:");
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] > media) {
                System.out.println("vetor[" + i + "] = " + vetor[i]);
            }
        }
    }
}
