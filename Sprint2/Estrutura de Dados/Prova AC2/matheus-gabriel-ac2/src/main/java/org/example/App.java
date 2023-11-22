package org.example;

public class App {
    public static void main(String[] args) {
        ListaEstatica lista1 = new ListaEstatica(7);
        lista1.adicionar(10);
        lista1.adicionar(20);
        lista1.adicionar(30);
        lista1.adicionaNoIndice(-1, 111);  // deve dar indice invalido
        lista1.adicionaNoIndice(4, 111);  // deve dar indice invalido
        lista1.adicionaNoIndice(3, 40);  // deve dar certo (exiba para conferir)
        System.out.println("Primeira substituição");
        lista1.exibir();

        lista1.adicionaNoIndice(0, 5);  // deve dar certo (exiba para conferir)
        System.out.println("\nSegunda substituição");
        lista1.exibir();

        lista1.adicionaNoIndice(2, 111);  // deve dar certo (exiba para conferir)
        System.out.println("\nTerceira substituição");
        lista1.exibir();


        System.out.println("\n---------------------Lista estatica Ordenada---------------------\n");
        ListaEstatica lista2 = new ListaEstatica(10);
        lista2.adicionar(8);
        lista2.adicionar(5);
        lista2.adicionar(4);
        lista2.adicionar(7);
        lista2.adicionar(10);
        lista2.adicionar(2);
        lista2.exibir();
        lista2.ordena();
        System.out.println("\nLista ordenada");
        lista2.exibir();

        System.out.println("\n---------------------Pesquisa Binaria---------------------\n");
        System.out.println(lista2.pesquisaBinaria(2));
        System.out.println(lista2.pesquisaBinaria(8));
        System.out.println(lista2.pesquisaBinaria(10));
        System.out.println(lista2.pesquisaBinaria(9));
    }
}
