package org.example;

public class Main {
    public static void main(String[] args) {
        Pilha pilha1 = new Pilha(5);

        pilha1.push(10);
        pilha1.push(20);
        pilha1.push(30);
        pilha1.push(40);
        pilha1.push(50);

        pilha1.exibe();

        Pilha pilha2 = new Pilha(5);
        pilha2.push(1);
        pilha2.push(2);
        pilha2.push(3);
        pilha2.push(2);
        pilha2.push(1);

        System.out.println("\nVerificando se pilha1 é ou não palindromo");
        System.out.println("É palindromo? " + pilha1.isPalindromo());

        System.out.println("\nVerificando se pilha2 é ou não palindromo");
        System.out.println("É palindromo? " + pilha2.isPalindromo());

        System.out.println("\nConvertendo o número 32 para binário");
        pilha1.converteDecimalParaBinario(32);

        PilhaObj<String> pilha3 = new PilhaObj<>(5);

        pilha3.push("A");
        pilha3.push("B");
        pilha3.push("C");
        pilha3.push("D");
        pilha3.push("E");

        pilha3.exibe();

        System.out.println("\nDesempilhando as Strings e exibindo conforme desempilhar");

        while (!pilha3.isEmpty()) {
            System.out.println(pilha3.pop());
        }


        String frase = "A pilha do gato";
        PilhaObj<Character> frase1 = new PilhaObj<>(20);

        for (int i = 0; i < frase.length(); i++) {
            frase1.push(frase.charAt(i));
        }
        frase1.exibe();

        System.out.println("\nDesempilhando as Strings e exibindo conforme desempilhar");

        while (!frase1.isEmpty()) {
            System.out.println(frase1.pop());
        }

        PilhaObj<Character> frase2 = new PilhaObj<>(20);

        frase2.push('r');
        frase2.push('a');
        frase2.push('d');
        frase2.push('a');
        frase2.push('r');

        frase2.exibe();

        System.out.println("\nVerificando se a frase é palindromo");

        System.out.println("É palindromo? " + frase2.isPalindromo());


    }
}