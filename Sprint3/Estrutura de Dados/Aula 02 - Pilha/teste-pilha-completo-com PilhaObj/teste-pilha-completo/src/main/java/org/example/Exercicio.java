package org.example;

public class Exercicio {

    public static Boolean ehPalindromo(int[] v) {
        Pilha pilha = new Pilha(v.length);

        // Percorre o vetor v, empilhando seus elementos
        for (int i = 0; i < v.length; i++) {
            pilha.push(v[i]);
        }

        // Percorre o vetor v novamente, comparando cada elemento
        // do vetor com o pop da pilha
        // Se algum der diferente, é pq não é palíndromo
        for (int i = 0; i < v.length; i++) {
            if (v[i] != pilha.pop()) {
                return false;
            }
        }
        // Se chegou até aqui, é pq o vetor v é palíndromo
        return true;
    }

    public static Boolean ehPalindromoOtimizado(int[] v) {
        Pilha pilha = new Pilha(v.length / 2);
        int i;
        // Percorre metade do vetor empilhando seus elementos
        for (i = 0; i < v.length/2; i++) {
            pilha.push(v[i]);
        }

        // se o vetor tem tamanho impar, "pula" o elemento do meio do vetor
        if (v.length % 2 != 0) {
            i++;
        }

        // percorre a 2a metade do vetor v, comparando seu elemento
        // com o pop da pilha
        for ( ; i < v.length; i++) {
            if (v[i] != pilha.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void converteParaBinario(int num) {
        Pilha pilha = new Pilha((int) (Math.log(num)/Math.log(2)) + 1);
        int numOriginal = num;   // salva o número original para printar no final

        while (num != 0) {
            pilha.push(num % 2);
            num /= 2;
        }

        System.out.print(numOriginal + " em binario: ");
        while (!pilha.isEmpty()) {
            System.out.print(pilha.pop());
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] vetor1 = {1, 3, 3, 1};
        int[] vetor2 = {10, 20, 30, 40};
        int[] vetor3 = {1, 2, 3, 2, 1};

        System.out.println("vetor1 é palíndromo? " + ehPalindromo(vetor1));
        System.out.println("vetor2 é palíndromo? " + ehPalindromo(vetor2));
        System.out.println("vetor3 é palíndromo? " + ehPalindromo(vetor3));
        System.out.println();

        System.out.println("vetor1 é palíndromo? " + ehPalindromoOtimizado(vetor1));
        System.out.println("vetor2 é palíndromo? " + ehPalindromoOtimizado(vetor2));
        System.out.println("vetor3 é palíndromo? " + ehPalindromoOtimizado(vetor3));

        System.out.println();
        converteParaBinario(12);
        converteParaBinario(13);
        converteParaBinario(25);
        converteParaBinario(15);
        converteParaBinario(1024);

    }


}
