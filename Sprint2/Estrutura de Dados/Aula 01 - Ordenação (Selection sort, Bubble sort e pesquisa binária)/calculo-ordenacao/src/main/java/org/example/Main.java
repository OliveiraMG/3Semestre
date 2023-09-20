package org.example;

public class Main {

    public static void main(String[] args) {
        int[] v = { 5, 3, 2, 4, 7, 1, 0, 6 };
        int[] v2 = { 5, 3, 2, 4, 7, 1, 0, 6 };
        int[] v3 = { 5, 3, 2, 4, 7, 1, 0, 6 };

        System.out.println("Vetor desordenado:");
        for (int i = 0; i < v.length; i++) {
            System.out.println(v[i]);
        }

        System.out.println("\n" + "Vetor ordenado sort normal:");
        selectionSort(v);
        for (int i = 0; i < v.length; i++) {
            System.out.println(v[i]);
        }

        System.out.println("\n" + "Vetor ordenado sort otimizado:");
        selectionSortOtimizado(v2);
        for (int i = 0; i < v.length; i++) {
            System.out.println(v2[i]);
        }

        System.out.println("\n" + "Vetor ordenado bubble sort:");
        int[] vetorOrganizado = bubbleSort(v3);
        for (int i = 0; i < v.length; i++) {
            System.out.println(v3[i]);
        }

        System.out.println("\n" + "Pesquisa binária:");
        pesquisaBinaria(vetorOrganizado, 7);

    }

    public static void selectionSort(int[] v) {
        int comparacao = 0;
        int troca = 0;
        for (int i = 0; i < v.length - 1; i++) { // Percorre o vetor
            for (int j = i + 1; j < v.length; j++) { // Percorre o vetor a partir da posição i (segundo elemento)
                comparacao++;
                if (v[j] < v[i]) {
                    int aux = v[i]; // O auxiliar serve para armazenar o valor de v[i] antes de ser substituído pelo numero menor encontrado
                    v[i] = v[j];
                    v[j] = aux;
                    troca++;
                }
            }
        }
        System.out.println("\n" + "Número de comparações: " + comparacao);
        System.out.println("\n" + "Número de trocas: " + troca);
    }

    public static void selectionSortOtimizado(int[] v) {
        int comparacao = 0;
        int troca = 0;
        for (int i = 0; i < v.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < v.length; j++) {
                comparacao++;
                if (v[j] < v[min]) {
                    min = j;
                    troca++;
                }
            }
            int aux = v[min];
            v[min] = v[i];
            v[i] = aux;
        }
        System.out.println("\n" + "Número de comparações: " + comparacao);
        System.out.println("\n" + "Número de trocas: " + troca);
    }

    public static int[] bubbleSort(int[] v) {
        int comparacao = 0;
        int troca = 0;
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = 0; j < v.length - 1 - i; j++) {
                comparacao++;
                if (v[j] > v[j + 1]) {
                    int aux = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = aux;
                    troca++;
                }
            }
        }
        System.out.println("\n" + "Número de comparações: " + comparacao);
        System.out.println("\n" + "Número de trocas: " + troca);
        return v;
    }

    public static void pesquisaBinaria(int[] v, int x) {
        int inicio = 0;
        int fim = v.length - 1;
        int meio = (inicio + fim) / 2;
        int comparacao = 0;
        while (inicio <= fim) {
            comparacao++;
            if (x == v[meio]) {
                System.out.println("Número de comparações: " + comparacao);
                System.out.println("Número encontrado na posição: " + meio);
                return;
            } else if (x < v[meio]) {
                fim = meio - 1;
            } else {
                inicio = meio + 1;
            }
            meio = (inicio + fim) / 2;
        }
        System.out.println("Número de comparações: " + comparacao);
        System.out.println("Número não encontrado");
    }

}