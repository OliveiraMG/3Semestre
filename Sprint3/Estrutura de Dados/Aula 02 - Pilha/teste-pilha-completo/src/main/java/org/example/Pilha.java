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

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        return topo == -1;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        return topo == pilha.length - 1;
    }

    // 05) MÃ©todo push
    public void push(int info) {
        if (isFull()) {
            throw new IllegalStateException("Pilha cheia");
        }

        pilha[++topo] = info;
    }

    // 06) MÃ©todo pop
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Pilha vazia");
        }

        return pilha[topo--];
    }

    // 07) MÃ©todo peek
    public int peek() {
        if (isEmpty()) {
            return -1;
        }

        return pilha[topo];
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        } else {
            System.out.print("Pilha: ");
            for (int i = 0; i <= topo; i++) {
                System.out.print(pilha[i] + " ");
            }
            System.out.println();
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}