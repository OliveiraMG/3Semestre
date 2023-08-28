package org.example.Exercicio01;

import java.util.Scanner;

public class Exercicio01 {
    public static void main(String[] args) {


        Scanner leitor = new Scanner(System.in);

        int[] vetor = new int[7];

            for (int i = 0; i < vetor.length; i++) {
                System.out.println("Informe o valor da posição " + i + ": ");
                vetor[i] = leitor.nextInt();
            }


            System.out.println("Exibe vetor normal:");
            for (int i = 0; i < vetor.length; i++) {
                System.out.println("vetor[" + i + "] = " + vetor[i]);
            }


            System.out.println("Exibe vetor inverso:");
            for (int i = vetor.length - 1; i >= 0; i--) {
                System.out.println("vetor[" + i + "] = " + vetor[i]);
        }
    }
}
