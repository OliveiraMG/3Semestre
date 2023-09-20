package org.example;

public class Main {
    public static void main(String[] args) {
        ListaEstatica lista = new ListaEstatica(10);

        lista.adicionar(10);
        lista.adicionar(20);
        lista.adicionar(30);
        lista.adicionar(20);
        lista.adicionar(30);
        lista.adicionar(20);
        lista.adicionar(70);
        lista.adicionar(80);
        lista.adicionar(100);

        lista.exibir();

        System.out.println("\n--------------------Método Buscar----------------------\n");

        lista.buscar(70);

        System.out.println("\n--------------------Método Remove Pelo Indice----------------------\n");

        lista.removePeloIndice(3);

        lista.exibir();

        System.out.println("\n---------------------Método remove pelo valor---------------------\n");

        lista.removePeloValor(50);

        lista.exibir();

        System.out.println("\n---------------------Método substitui valor---------------------\n");

        lista.substituiValor(100, 200);

        lista.exibir();

        System.out.println("\n---------------------Método conta ocorrencias---------------------\n");

        lista.contaOcorrencias(20);

        System.out.println("\n---------------------Método adiciona no inicio---------------------\n");

        lista.adicionaNoInicio(50);

        lista.exibir();
    }
}