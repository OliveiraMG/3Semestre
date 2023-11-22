package org.example;

public class Pilha {

    // 01) Atributos
    private int[] pilha;
    private int topo;

    // 02) Construtor
    public Pilha(int capacidade) {
        pilha = new int[capacidade];
        topo = -1;
    }

    // 03) Metodo isEmpty
    public Boolean isEmpty() {
        return topo == -1;
    }

    // 04) Metodo isFull
    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    // 05) Metodo push
    public void push(int info) {
        if (isFull()) {
            System.out.println("Pilha cheia!");
            throw new IllegalStateException("Pilha cheia");
        }
        else {
//            topo++;
//            pilha[topo] = info;
            // as 2 instruções acima equivalem a esta abaixo:
            pilha[++topo] = info;
        }
    }

    // 06) Metodo pop
    public int pop() {
        if (isEmpty()) {
            return -1;
        }
//        int retorno = pilha[topo];
//        topo--;
//        return retorno;

        return pilha[topo--];
    }

    // 07) Metodo peek
    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return pilha[topo];
    }

    // 08) Metodo exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        }
        else {
            for (int i = topo; i >= 0; i--) {
                System.out.println(pilha[i]);
            }
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}