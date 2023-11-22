package org.example;

public class Main {
    public static void main(String[] args) {
        // Cria um objeto Fila
        FilaObj<Integer> fila = new FilaObj(5);

        // Insere elementos na fila
        try {
            fila.insert(100);
            fila.insert(200);
            fila.insert(300);
            fila.insert(400);
            fila.insert(500);
            fila.insert(600);
        }
        catch (IllegalStateException erro) {
            System.out.println(erro);
        }
        // Exibe a fila
        fila.exibe();

        // Exibe o primeiro da fila, sem remover
        System.out.println("Primeiro da fila: " + fila.peek());

        // Exibe a fila
        fila.exibe();

        // Remove um elemento da fila
        System.out.println("Removeu da fila: " + fila.poll());

        fila.insert(600);

        // Exibe a fila
        fila.exibe();

        // Esvazia a fila, exibindo um a um
        while (!fila.isEmpty()) {
            System.out.println("Removeu: " + fila.poll());
        }

        fila.exibe();
    }
}