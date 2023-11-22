package org.example;

public class TestePilhaObj {

    public static void main(String[] args) {
        // Cria um objeto pilha
        PilhaObj<String> pilha = new PilhaObj(5);

        // Empilha valores na pilha
        pilha.push("10");
        pilha.push("20");
        pilha.push("30");
        pilha.push("40");
        pilha.push("50");

        try {
            pilha.push("60");
        }
        catch (IllegalStateException erro) {
            System.out.println(erro);
        }

        // Exibe a pilha
        pilha.exibe();

        // Desempilhando um valor
        System.out.println("Desempilhou: " + pilha.pop());

        // Exibe a pilha
        pilha.exibe();

        // Testando o peek
        System.out.println("Valor do topo: " + pilha.peek());
        System.out.println("Valor do topo: " + pilha.peek());

        // Exibe a pilha
        pilha.exibe();

        // Esvaziar a pilha, exibindo um por um
        System.out.println("\nEsvaziando a pilha:");
        while (!pilha.isEmpty()) {
            System.out.println("Desempilhou: " + pilha.pop());
        }


    }

}
