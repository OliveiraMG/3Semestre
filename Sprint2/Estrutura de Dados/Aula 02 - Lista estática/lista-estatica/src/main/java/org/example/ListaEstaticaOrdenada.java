package org.example;

public class ListaEstaticaOrdenada extends ListaEstatica {

    public ListaEstaticaOrdenada(int tamanho) {
        super(tamanho);
    }

    @Override
    public void adicionar(int numero) {
        if (numeroElementos < this.vetorNumero.length) {
            int indice = 0;

            while (indice < numeroElementos && vetorNumero[indice] < numero) {
                indice++;
            }

            for (int i = numeroElementos; i > indice; i--) {
                vetorNumero[i] = vetorNumero[i - 1];
            }

            vetorNumero[indice] = numero;
            numeroElementos++;
        } else {
            System.out.println("Lista cheia!");
        }
    }
}