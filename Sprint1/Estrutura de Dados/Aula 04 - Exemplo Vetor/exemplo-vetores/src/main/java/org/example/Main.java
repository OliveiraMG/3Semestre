package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void exibeVetor(int[] vetor) {
        for (int i = 0; i < vetor.length; i++) {
            System.out.println("vetor[" + i + "] = " + vetor[i]);
        }
//        Stream.of(vetor).forEach(System.out::println);
//        Arrays.stream(vetor).forEach(System.out::println);
        }

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        // Declaração e criação de um vetor de tamanho 5
        int[] vetor = new int[5];

        // Declaração e criação de um vetor ja inicializado com valores
        int[] vetor2 = {100, 200, 300, 400, 500, 600, 700};

        // Declaração e Criação de um vetor de Strings
        String[] vetor3 = new String[4];

        // Preencher os valores do vetor
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = i * 10;
        }


        // Preenchendo o valor com vslores informados pelo usuário
        for (int i = 0; i < vetor.length; i++) {
            System.out.print("Informe o valor da posição " + i + ": ");
            vetor[i] = leitor.nextInt();
        }

        // Exibir valores do vetor
        System.out.println("Valores do vetor:");
        exibeVetor(vetor);
        //        System.out.println(Arrays.toString(vetor));

        // Exibir valores do vetor
        System.out.println("\nValores do vetor:");
        exibeVetor(vetor2);

        // Preenchendo o valor com vslores informados pelo usuário
        for (int i = 0; i < vetor3.length; i++) {
            System.out.print("Informe o valor da posição " + i + ": ");
            vetor3[i] = leitor.next();
        }

        // Exibindo vetor3
        System.out.println("\nValores do vetor3:");
        for (String s : vetor3) {
            System.out.println(s);
        }
    }
}