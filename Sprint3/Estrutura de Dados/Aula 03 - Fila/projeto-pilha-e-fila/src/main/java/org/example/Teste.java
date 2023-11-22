package org.example;

public class Teste {
    public static void main(String[] args) {

        int[] v = {2,7,-3,-50,45,-4,30,-21,38};

        separaPositivoNegativoEmOrdem(v);

        separaPositivoNegativoOrdemDiferente(v);

    }

    // Na classe Teste, implemente o método separaPositivoNegativoOrdemDiferente, que
    //recebe um vetor de inteiros e deve utilizar um objeto FilaObj<Integer> e um objeto
    //PilhaObj<Integer> para separar os números positivos dos números negativos e exibi-los
    //separados, mas de forma que os números positivos sejam exibidos na ordem em que
    //estavam no vetor, e os elementos negativos sejam exibidos na ordem inversa em que
    //estavam no vetor.
    //Ao final do método, os objetos fila e pilha deverão estar vazios.

    public static void separaPositivoNegativoOrdemDiferente(int[] v) {
        FilaObj<Integer> filaPositivos = new FilaObj<Integer>(v.length);
        PilhaObj<Integer> pilhaNegativos = new PilhaObj<Integer>(v.length);

        for (int i = 0; i < v.length; i++) {
            if (v[i] >= 0) {
                filaPositivos.insert(v[i]);
            }
            else {
                pilhaNegativos.push(v[i]);
            }
        }


//        SEPARADOS
//        System.out.println("\nFila de positivos:");
//        while (!filaPositivos.isEmpty()) {
//            System.out.print(filaPositivos.poll() + " ");
//        }
//        System.out.println("\nPilha de negativos:");
//        while (!pilhaNegativos.isEmpty()) {
//            System.out.print(pilhaNegativos.pop() + " ");
//        }

        System.out.println("\nFila e Pilha juntas com primeiros positivos e depois negativos em ordem inversa:");
        while (!filaPositivos.isEmpty()) {
            System.out.print(filaPositivos.poll() + " ");
        }
        while (!pilhaNegativos.isEmpty()) {
            System.out.print(pilhaNegativos.pop() + " ");
        }
    }

    public static void separaPositivoNegativoEmOrdem(int[] v) {
        FilaObj<Integer> filaPositivos = new FilaObj<Integer>(v.length);
        FilaObj<Integer> filaNegativos = new FilaObj<Integer>(v.length);

        for (int i = 0; i < v.length; i++) {
            if (v[i] >= 0) {
                filaPositivos.insert(v[i]);
            }
            else {
                filaNegativos.insert(v[i]);
            }
        }

//        FILAS SEPARADAS
//        System.out.println("Fila de positivos:");
//        while (!filaPositivos.isEmpty()) {
//            System.out.print(filaPositivos.poll() + " ");
//        }
//        System.out.println("\nFila de negativos:");
//        while (!filaNegativos.isEmpty()) {
//            System.out.print(filaNegativos.poll() + " ");
//        }

        System.out.println("\nFilas juntas com primeiros positivos e depois negativos:");
        while (!filaPositivos.isEmpty()) {
            System.out.print(filaPositivos.poll() + " ");
        }
        while (!filaNegativos.isEmpty()) {
            System.out.print(filaNegativos.poll() + " ");
        }

    }
}