package org.example;

import java.util.Arrays;

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
            System.out.println(Arrays.toString(pilha));
        }
    }

    // 09) MÃ©todo isPalindromo
    public Boolean isPalindromo() {
        if (isEmpty()) {
            return false;
        }

        int i = 0;
        int j = topo;

        while (i < j) {
            if (pilha[i++] != pilha[j--]) {
                return false;
            }
        }

        return true;
    }

    // 10) MÃ©todo converteDecimalParaBinario


        public void converteDecimalParaBinario(int num) {
        // O tamanho da pilha nesse caso é feito assim por não haver o log na base dois,
            // então deve-se dividir o log do número que é recebido na base e (que seria o número de euler) pelo log na base 2 + 1
        Pilha pilha = new Pilha((int) (Math.log(num) / Math.log(2)) + 1);

        while (num > 0) {
            pilha.push(num % 2);
            num /= 2;
        }

        Pilha pilhaInvertida = new Pilha(pilha.pilha.length);
        while (!pilha.isEmpty()) {
            pilhaInvertida.push(pilha.pop());
        }

        pilhaInvertida.exibe();
    }

    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }
}